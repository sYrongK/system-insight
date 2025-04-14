package com.system.core

import com.system.insight.application.facade.GameFacade
import com.system.insight.controller.request.PlayingRequest
import io.github.serpro69.kfaker.Faker
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class A
    (private val gameFacade: GameFacade
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val faker = Faker()

        // 1부터 100까지 루프를 돌며 서비스 메서드를 호출합니다.
        for (i in 1..100) {
            val request = PlayingRequest(
                "user" + i,
                "user" + i,
                "https://cdn.example.com/images/player1.png",
                Random.nextInt(0, 201)
            )
            gameFacade.playing(request)
        }
    }
}