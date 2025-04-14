package com.system.insight.application.event.listener

import com.system.insight.application.event.RankingScoreRecordedEvent
import com.system.insight.application.service.RankingService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RankingEventListener(
    var rankingService: RankingService
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleScoreRecordedEvent(event: RankingScoreRecordedEvent) {
        rankingService.recordScore(event.member, event.value)
    }
}