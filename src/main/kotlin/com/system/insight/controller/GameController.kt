package com.system.insight.controller

import com.system.core.wrapper.ResultResponse
import com.system.insight.application.game.service.GameService
import com.system.insight.application.ranking.service.RankingService
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.controller.response.RankingResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/apis/game")
class GameController(
    private val gameService: GameService,
    private val rankingService: RankingService
) {

    @PostMapping("/scores")
    fun playing(@RequestBody playingRequest: PlayingRequest): ResultResponse<Void> {
        gameService.playing(playingRequest)
        return ResultResponse()
    }

    @GetMapping("/ranking/top10")
    fun getTop10(): ResultResponse<List<RankingResponse>> {
        return ResultResponse(rankingService.getTop10())
    }
}
