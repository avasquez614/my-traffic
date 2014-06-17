package org.mytraffic.priv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.craftercms.commons.rest.RestTemplate;
import org.mytraffic.priv.api.exceptions.PrivateApiErrorDetails;
import org.mytraffic.priv.services.impl.FavoriteRouteRestClient;
import org.mytraffic.priv.services.impl.TrafficIncidentRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Spring client configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan("org.mytraffic.priv.services")
public class ClientConfig {

    @Value("${url.extension}")
    private String urlExtension;
    @Value("${url.base}")
    private String urlBase;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());

        return objectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());

        return converter;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, jacksonMessageConverter());
        restTemplate.setErrorResponseType(PrivateApiErrorDetails.class);

        return restTemplate;
    }

    @Bean
    public TrafficIncidentRestClient trafficIncidentRestClient() {
        TrafficIncidentRestClient client = new TrafficIncidentRestClient();
        client.setBaseUrl(urlBase);
        client.setExtension(urlExtension);
        client.setRestTemplate(restTemplate());

        return client;
    }

    @Bean
    public FavoriteRouteRestClient favoriteRouteRestClient() {
        FavoriteRouteRestClient client = new FavoriteRouteRestClient();
        client.setBaseUrl(urlBase);
        client.setExtension(urlExtension);
        client.setRestTemplate(restTemplate());

        return client;
    }

}
