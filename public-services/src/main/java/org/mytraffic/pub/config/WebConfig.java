package org.mytraffic.pub.config;

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
@ComponentScan("org.mytraffic.pub.rest.controllers.")
public class WebConfig {
}
