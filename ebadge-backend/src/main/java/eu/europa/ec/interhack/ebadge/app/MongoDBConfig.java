package eu.europa.ec.interhack.ebadge.app;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by rromero on 10/10/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = "eu.europa.ec.interhack.ebadge.repository")
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private int port;

    @Override
    protected String getDatabaseName() {
        return "ebadge";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }
}
