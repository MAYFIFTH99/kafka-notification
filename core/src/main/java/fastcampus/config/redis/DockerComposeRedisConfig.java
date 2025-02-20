package fastcampus.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@Profile("local")
public class DockerComposeRedisConfig {

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;


    @Bean(name = "notificationRedisFactory")
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                REDIS_HOST, REDIS_PORT);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}
