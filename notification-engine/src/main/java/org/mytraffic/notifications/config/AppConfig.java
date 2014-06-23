package org.mytraffic.notifications.config;

import freemarker.template.TemplateException;
import org.craftercms.commons.mail.EmailFactory;
import org.craftercms.commons.mail.impl.EmailFactoryImpl;
import org.mytraffic.priv.config.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Spring application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@EnableScheduling
@PropertySource("classpath:notifications.properties")
@ComponentScan("org.mytraffic.notifications.jobs")
@Import(ClientConfig.class)
@ImportResource("classpath:crafter/profile/client-context.xml")
public class AppConfig {

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private int mailPort;
    @Value("${mail.protocol}")
    private String mailProtocol;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.encoding}")
    private String mailEncoding;
    @Value("${mail.smtp.auth}")
    private boolean mailSmtpAuth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean mailSmtpStartTls;
    @Value("${mail.templates.path}")
    private String mailTemplatesPath;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setOrder(Ordered.HIGHEST_PRECEDENCE);
        configurer.setIgnoreUnresolvablePlaceholders(true);

        return configurer;
    }

    @Bean
    public JavaMailSender mailSender() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", String.valueOf(mailSmtpAuth));
        properties.setProperty("mail.smtp.starttls.enable", String.valueOf(mailSmtpStartTls));

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setProtocol(mailProtocol);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setDefaultEncoding(mailEncoding);
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    @Bean
    public freemarker.template.Configuration mailFreeMarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setTemplateLoaderPath(mailTemplatesPath);
        factoryBean.setDefaultEncoding(mailEncoding);
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public EmailFactory emailFactory() throws IOException, TemplateException {
        EmailFactoryImpl emailFactory = new EmailFactoryImpl();
        emailFactory.setMailSender(mailSender());
        emailFactory.setFreeMarkerConfig(mailFreeMarkerConfig());
        emailFactory.setTemplateEncoding(mailEncoding);

        return emailFactory;
    }

}
