package com.system.insight.persistence.jpa

import com.system.insight.domain.entity.ScoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ScoreRepository : JpaRepository<ScoreEntity, Long> {
    fun findByUserId(userId: String): ScoreEntity?
}