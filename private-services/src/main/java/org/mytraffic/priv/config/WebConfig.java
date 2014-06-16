package org.mytraffic.priv.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring web application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.mytraffic.priv.rest.controllers")
public class WebConfig {
}
