package com.system.insight.application.facade

import com.system.insight.application.service.GameService
import com.system.insight.application.service.RankingService
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.controller.response.RankingResponse
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Service

@Service
class GameFacade(
    var gameService: GameService,
    var rankingService: RankingService
) {

    fun playing(playingRequest: PlayingRequest) {
        val scoreId = gameService.playing(playingRequest)
        gameService.recordScore(scoreId)
    }

    fun getTop10(): List<RankingResponse> {
        var ranking: List<RankingResponse> = emptyList();
        val top10: List<TypedTuple<String>> = rankingService.getTop10()
        if (top10.isEmpty()) {
            ranking = rankingService.getTop10WithRecovery()
        } else {
            ranking =rankingService.getRankingList(top10)
        }
        return ranking;
    }
}