package com.system.insight.controller

import com.system.core.wrapper.ResultResponse
import com.system.insight.application.game.facade.GameFacade
import com.system.insight.application.ranking.facade.RankingFacade
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.controller.response.RankingResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/apis/game")
class GameController(
    private val gameFacade: GameFacade,
    private val rankingFacade: RankingFacade
) {

    @PostMapping("/scores")
    fun playing(@RequestBody playingRequest: PlayingRequest): ResultResponse<Void> {
        gameFacade.playing(playingRequest)
        return ResultResponse()
    }

    @GetMapping("/ranking/top10")
    fun getTop10(): ResultResponse<List<RankingResponse>> {
        return ResultResponse(rankingFacade.getTop10())
    }
}
