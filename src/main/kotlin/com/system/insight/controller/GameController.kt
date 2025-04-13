package com.system.insight.controller

import com.system.core.wrapper.ResultResponse
import com.system.insight.application.facade.GameFacade
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.controller.response.RankingResponse
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/ranking/top10")
    fun getTop10(): ResultResponse<List<RankingResponse>> {
        return ResultResponse(gameFacade.getTop10())
    }
}
