package com.system.insight.application.ranking.facade

import com.system.insight.application.ranking.service.RankingService
import com.system.insight.controller.response.RankingResponse
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Service

@Service
class RankingFacade(
    var rankingService: RankingService
) {

    fun getTop10(): List<RankingResponse> {
        var ranking: List<RankingResponse> = emptyList();
        val top10: List<TypedTuple<String>> = rankingService.getTop10()
        if (top10.isEmpty()) {
            ranking = rankingService.getTop10WithRecovery()
        } else {
            ranking =rankingService.setRankingList(top10)
        }
        return ranking;
    }
}