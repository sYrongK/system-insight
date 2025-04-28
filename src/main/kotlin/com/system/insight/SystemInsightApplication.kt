package com.system.insight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableMongoAuditing
@SpringBootApplication(scanBasePackages = ["com.system.*"])
class SystemInsightApplication

fun main(args: Array<String>) {
    runApplication<SystemInsightApplication>(*args)
}
