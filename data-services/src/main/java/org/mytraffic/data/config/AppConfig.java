package org.mytraffic.data.config;

import com.mongodb.MongoClient;
import org.craftercms.commons.mongo.JongoFactoryBean;
import org.craftercms.commons.mongo.JongoQueries;
import org.jongo.Jongo;
import org.mytraffic.data.repositories.FavoriteRouteRepository;
import org.mytraffic.data.repositories.TrafficIncidentRepository;
import org.mytraffic.data.repositories.impl.FavoriteRouteRepositoryImpl;
import org.mytraffic.data.repositories.impl.TrafficIncidentRepositoryImpl;
import org.mytraffic.utils.jackson.JongoLocalTimeDeserializer;
import org.mytraffic.utils.jackson.JongoLocalTimeSerializer;
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
import java.util.Arrays;
import java.util.Collections;

/**
 * Spring application configuration.
 *
 * @author avasquez
 * @author mariobarque
 */
@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan("org.mytraffic.data.services")
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
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean
    public Jongo jongo() throws Exception {
        JongoFactoryBean factoryBean = new JongoFactoryBean();
        factoryBean.setMongo(mongoClient());
        factoryBean.setDbName(mongoDbName);
        factoryBean.setUsername(mongoUsername);
        factoryBean.setUsername(mongoPassword);
        factoryBean.setSerializers(Arrays.asList(new JongoLocalTimeSerializer()));
        factoryBean.setDeserializers(Collections.singletonMap(LocalTime.class, new JongoLocalTimeDeserializer()));
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public JongoQueries jongoQueries() {
        JongoQueries queries = new JongoQueries();
        queries.setResources(Arrays.asList(resourceLoader.getResource("classpath:config.properties")));

        return queries;
    }

    @Bean
    public TrafficIncidentRepository trafficIncidentRepository() throws Exception {
        TrafficIncidentRepositoryImpl repository = new TrafficIncidentRepositoryImpl();
        repository.setJongo(jongo());
        repository.setQueries(jongoQueries());

        return repository;
    }

    @Bean
    public FavoriteRouteRepository favoriteRouteRepository() throws Exception {
        FavoriteRouteRepositoryImpl repository = new FavoriteRouteRepositoryImpl();
        repository.setJongo(jongo());
        repository.setQueries(jongoQueries());

        return repository;
    }

}
