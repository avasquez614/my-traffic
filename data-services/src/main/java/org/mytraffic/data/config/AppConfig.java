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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
    private Environment environment;
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        String host = environment.getRequiredProperty("mongo.host");
        int port = Integer.parseInt(environment.getRequiredProperty("mongo.port"));

        return new MongoClient(host, port);
    }

    @Bean
    public Jongo jongo() throws Exception {
        JongoFactoryBean factoryBean = new JongoFactoryBean();
        factoryBean.setMongo(mongoClient());
        factoryBean.setDbName(environment.getRequiredProperty("mongo.dbName"));
        factoryBean.setUsername(environment.getProperty("mongo.username"));
        factoryBean.setUsername(environment.getProperty("mongo.password"));
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
