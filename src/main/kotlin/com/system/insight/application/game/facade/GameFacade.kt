package com.system.insight.application.game.facade

import com.system.insight.application.game.service.GameService
import com.system.insight.controller.request.PlayingRequest
import org.springframework.stereotype.Service

@Service
class GameFacade(
    var gameService: GameService,
) {

    fun playing(playingRequest: PlayingRequest) {
        val scoreId = gameService.playing(playingRequest)
        gameService.recordScore(scoreId)
    }
}