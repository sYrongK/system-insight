package com.system.core.config

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    var host: String,
    @Value("\${spring.data.redis.port}")
    var port: Int
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory? {
        val poolConfig = GenericObjectPoolConfig<Any>().apply {
            maxTotal = 100  // == max-active 동시 할당 최대 커넥션
            maxIdle = 30 //사용하지 않아도 유지할 커넥션 수(항상 대기상태로 둘 커넥션 수) 최대값
            minIdle = 20 //사용하지 않아도 유지할 커넥션 수(항상 대기상태로 둘 커넥션 수) 최소값
//            maxWaitMillis = 1500
        }

        val clientConfig = LettucePoolingClientConfiguration.builder()
            .poolConfig(poolConfig)
            .build()

        return LettuceConnectionFactory(RedisStandaloneConfiguration(host, port), clientConfig)
    }

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, String> {
        //todo [TEN] 어떻게 했었지?
        val template = RedisTemplate<String, String>()
        template.connectionFactory = factory
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        template.hashValueSerializer = StringRedisSerializer()
        return template
    }
}