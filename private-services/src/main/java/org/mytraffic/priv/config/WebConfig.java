package org.mytraffic.priv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;
import org.craftercms.commons.jackson.ObjectIdDeserializer;
import org.craftercms.commons.jackson.ObjectIdSerializer;
import org.mytraffic.utils.jackson.LocalTimeDeserializer;
import org.mytraffic.utils.jackson.LocalTimeSerializer;
import org.mytraffic.utils.jackson.ZonedDateTimeDeserializer;
import org.mytraffic.utils.jackson.ZonedDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring web application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.mytraffic.priv.rest.controllers")
public class WebConfig extends WebMvcConfigurerAdapter {

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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
    }

}
