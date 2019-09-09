package cn.miki.config;

import cn.miki.util.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(MyProperties.class)
public class RedisConfig {

    @Autowired
    private MyProperties myProperties;

    @Bean
    public RedisTools redisTools(@Qualifier("stringRedisTemplate") RedisTemplate redisTemplate) {
        myProperties.getPassword();
        return new RedisTools(redisTemplate);
    }
}