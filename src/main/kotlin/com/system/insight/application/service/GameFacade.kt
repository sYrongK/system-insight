package com.system.insight.application.service

import com.system.insight.controller.request.PlayingRequest
import org.springframework.stereotype.Service

@Service
class GameFacade(
    var gameService: GameService
) {

    fun playing(playingRequest: PlayingRequest) {
        val scoreId = gameService.playing(playingRequest)
        gameService.recordScore(scoreId)
    }
}