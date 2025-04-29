package com.system.insight.application.scheduler

import com.system.insight.application.gameEvent.EventService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

//@Component
class RankingEventScheduler(
    var eventService: EventService
) {

    @Scheduled(cron = "0 * * * * *")
    fun processScoreRewardEvent() {
        eventService.processScoreRewardEvent()
    }
}