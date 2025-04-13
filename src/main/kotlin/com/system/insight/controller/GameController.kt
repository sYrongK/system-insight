package com.system.insight.controller

import com.system.core.wrapper.ResultResponse
import com.system.insight.application.service.GameFacade
import com.system.insight.controller.request.PlayingRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apis/game")
class GameController(
    private val gameFacade: GameFacade
) {

    @PostMapping("/scores")
    fun playing(@RequestBody playingRequest: PlayingRequest): ResultResponse<Void> {
        gameFacade.playing(playingRequest)
        return ResultResponse()
    }
}
