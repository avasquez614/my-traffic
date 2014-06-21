package org.mytraffic.priv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;
import org.craftercms.commons.jackson.ObjectIdDeserializer;
import org.craftercms.commons.jackson.ObjectIdSerializer;
import org.craftercms.commons.rest.RestTemplate;
import org.mytraffic.priv.api.exceptions.PrivateApiErrorDetails;
import org.mytraffic.priv.services.impl.FavoriteRouteServiceRestClient;
import org.mytraffic.priv.services.impl.LocationServiceRestClient;
import org.mytraffic.priv.services.impl.TrafficIncidentServiceRestClient;
import org.mytraffic.utils.jackson.LocalTimeDeserializer;
import org.mytraffic.utils.jackson.LocalTimeSerializer;
import org.mytraffic.utils.jackson.ZonedDateTimeDeserializer;
import org.mytraffic.utils.jackson.ZonedDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * Spring client configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@PropertySource("classpath:client.properties")
public class ClientConfig {

    @Value("${url.extension}")
    private String urlExtension;
    @Value("${url.base}")
    private String urlBase;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new ObjectIdSerializer());
        module.addSerializer(new LocalTimeSerializer());
        module.addSerializer(new ZonedDateTimeSerializer());
        module.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(module);

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
    public TrafficIncidentServiceRestClient trafficIncidentServiceRestClient() {
        TrafficIncidentServiceRestClient client = new TrafficIncidentServiceRestClient();
        client.setBaseUrl(urlBase);
        client.setExtension(urlExtension);
        client.setRestTemplate(restTemplate());

        return client;
    }

    @Bean
    public FavoriteRouteServiceRestClient favoriteRouteServiceRestClient() {
        FavoriteRouteServiceRestClient client = new FavoriteRouteServiceRestClient();
        client.setBaseUrl(urlBase);
        client.setExtension(urlExtension);
        client.setRestTemplate(restTemplate());

        return client;
    }

    @Bean
    public LocationServiceRestClient locationServiceRestClient() {
        LocationServiceRestClient client = new LocationServiceRestClient();
        client.setBaseUrl(urlBase);
        client.setExtension(urlExtension);
        client.setRestTemplate(restTemplate());

        return client;
    }

}
