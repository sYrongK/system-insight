package com.system.insight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@EnableMongoAuditing
@SpringBootApplication(scanBasePackages = ["com.system.*"], exclude = [DataSourceAutoConfiguration::class])
class SystemInsightApplication

fun main(args: Array<String>) {
    runApplication<SystemInsightApplication>(*args)
}
