package org.mytraffic.pub.config;

import org.mytraffic.priv.config.ClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@Import(ClientConfig.class)
@ImportResource("classpath:security-context.xml")
public class AppConfig {

}
