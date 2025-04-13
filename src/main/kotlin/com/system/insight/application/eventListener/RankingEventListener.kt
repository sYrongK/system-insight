package com.system.insight.application.eventListener

import com.system.insight.application.eventListener.event.RankingScoreRecordedEvent
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RankingEventListener(
    var redisTemplate: RedisTemplate<String, Any>
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleScoreRecordedEvent(event: RankingScoreRecordedEvent) {
        redisTemplate.opsForZSet().add("ranking", event.member, event.value)
    }
}