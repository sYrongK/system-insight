package com.system.insight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages = ["com.system.*"])
class SystemInsightApplication

fun main(args: Array<String>) {
    runApplication<SystemInsightApplication>(*args)
}
