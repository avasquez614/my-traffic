package org.mytraffic.priv.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.mongodb.MongoClient;
import org.craftercms.commons.mongo.JongoFactoryBean;
import org.craftercms.commons.mongo.JongoQueries;
import org.jongo.Jongo;
import org.mytraffic.priv.repositories.FavoriteRouteRepository;
import org.mytraffic.priv.repositories.TrafficIncidentRepository;
import org.mytraffic.priv.repositories.impl.FavoriteRouteRepositoryImpl;
import org.mytraffic.priv.repositories.impl.TrafficIncidentRepositoryImpl;
import org.mytraffic.utils.jackson.JongoLocalTimeDeserializer;
import org.mytraffic.utils.jackson.JongoLocalTimeSerializer;
import org.mytraffic.utils.jackson.JongoZonedDateTimeDeserializer;
import org.mytraffic.utils.jackson.JongoZonedDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ResourceLoader;

import java.net.UnknownHostException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Spring application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@PropertySource("classpath:server.properties")
@ComponentScan("org.mytraffic.priv.services")
public class AppConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${mongo.host}")
    private String mongoHost;
    @Value("${mongo.port}")
    private int mongoPort;
    @Value("${mongo.dbName}")
    private String mongoDbName;
    @Value("${mongo.username}")
    private String mongoUsername;
    @Value("${mongo.password}")
    private String mongoPassword;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean
    public Jongo jongo() throws Exception {
        List<JsonSerializer<?>> serializers = new ArrayList<>(2);
        serializers.add(new JongoZonedDateTimeSerializer());
        serializers.add(new JongoLocalTimeSerializer());

        Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>(2);
        deserializers.put(ZonedDateTime.class, new JongoZonedDateTimeDeserializer());
        deserializers.put(LocalTime.class, new JongoLocalTimeDeserializer());

        JongoFactoryBean factoryBean = new JongoFactoryBean();
        factoryBean.setMongo(mongoClient());
        factoryBean.setDbName(mongoDbName);
        factoryBean.setUsername(mongoUsername);
        factoryBean.setUsername(mongoPassword);
        factoryBean.setSerializers(serializers);
        factoryBean.setDeserializers(deserializers);
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean(initMethod = "init")
    public JongoQueries jongoQueries() {
        JongoQueries queries = new JongoQueries();
        queries.setResources(Arrays.asList(resourceLoader.getResource("classpath:queries.xml")));

        return queries;
    }

    @Bean(initMethod = "init")
    public TrafficIncidentRepository trafficIncidentRepository() throws Exception {
        TrafficIncidentRepositoryImpl repository = new TrafficIncidentRepositoryImpl();
        repository.setJongo(jongo());
        repository.setQueries(jongoQueries());

        return repository;
    }

    @Bean(initMethod = "init")
    public FavoriteRouteRepository favoriteRouteRepository() throws Exception {
        FavoriteRouteRepositoryImpl repository = new FavoriteRouteRepositoryImpl();
        repository.setJongo(jongo());
        repository.setQueries(jongoQueries());

        return repository;
    }

}
