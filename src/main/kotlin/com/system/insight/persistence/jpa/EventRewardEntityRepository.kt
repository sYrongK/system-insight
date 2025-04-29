package com.system.insight.persistence.jpa

import com.system.insight.domain.entity.EventRewardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EventRewardEntityRepository : JpaRepository<EventRewardEntity, Long> {

    fun findByEventIdAndUserId(eventId: Long, userId: String): EventRewardEntity?
}