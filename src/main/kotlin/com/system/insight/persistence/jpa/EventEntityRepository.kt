package com.system.insight.persistence.jpa

import com.system.core.code.EventType
import com.system.insight.domain.entity.EventEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface EventEntityRepository : JpaRepository<EventEntity, Long> {

    fun findFirstByTypeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(type: EventType, start: LocalDateTime, end: LocalDateTime): EventEntity?
}