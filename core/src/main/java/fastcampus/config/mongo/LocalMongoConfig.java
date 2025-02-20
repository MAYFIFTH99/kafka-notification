package fastcampus.config.mongo;

import com.mongodb.ConnectionString;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
@Slf4j
// Mongo DB 생성
public class LocalMongoConfig {

    private static final String MONGODB_IMAGE_NAME = "mongo:5.0";
    private static final int MONGODB_INNER_PORT = 27017; // 컨테이너 내부 port
    private static final String DATABASE_NAME = "notification";
    private static final GenericContainer mongo = createMongoInstance();

    // testContainer 라이브러리에서 제공
    private static GenericContainer createMongoInstance() {
        return new GenericContainer(DockerImageName.parse(MONGODB_IMAGE_NAME))
                .withExposedPorts(MONGODB_INNER_PORT)
                .withReuse(true);
    }

    @PostConstruct // LocalMongoConfig 객체가 생성된 후 1회 실행 (빈 생성 이후)
    public void startMongo() {
        try {
            mongo.start();
        } catch (Exception e) {
            log.error("Failed to start MongoDB", e);
        }
    }

    @PreDestroy
    public void stopMongo() {
        try {
            mongo.stop();
        } catch (Exception e) {
            log.error("Failed to stop MongoDB", e);
        }
    }

    @Bean(name = "notificationMongoFactory")
    public MongoDatabaseFactory notificationMongoFactory(){
        return new SimpleMongoClientDatabaseFactory(connectionString());
    }

    private ConnectionString connectionString(){
        String host = mongo.getHost();
        Integer port = mongo.getMappedPort(MONGODB_INNER_PORT);
        return new ConnectionString("mongodb://" + host + ":" + port + "/" + DATABASE_NAME);
    }
}
