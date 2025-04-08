package com.system.insight.controller

import com.system.core.wrapper.ResultResponse
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.application.service.GameService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apis/game")
class GameController(
    private val gameService: GameService//val: 변경 불가능한 참조, var: 변경 가능한 참조
) {

    @PostMapping("/scores")
    fun playing(@RequestBody playingRequest: PlayingRequest): ResultResponse<Void> {
        gameService.playing(playingRequest)
        return ResultResponse()
    }
}
