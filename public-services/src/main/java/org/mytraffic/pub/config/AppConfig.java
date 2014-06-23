package org.mytraffic.pub.config;

import org.mytraffic.priv.config.ClientConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;

/**
 * Spring application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@Import(ClientConfig.class)
@PropertySource("classpath:server.properties")
@ImportResource("classpath:security-context.xml")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setOrder(Ordered.HIGHEST_PRECEDENCE);
        configurer.setIgnoreUnresolvablePlaceholders(true);

        return configurer;
    }

}
