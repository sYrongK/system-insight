package com.system.insight.application.scheduler

import com.system.insight.application.ranking.service.RankingService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

//@Component
class RankingScheduler(
    var rankingService: RankingService
) {

    @Scheduled(cron = "0 * * * * *")//매 분
    fun recordScore() {
        rankingService.recordScore()
    }
}