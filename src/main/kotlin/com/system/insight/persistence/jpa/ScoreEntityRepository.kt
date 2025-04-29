package com.system.insight.persistence.jpa

import com.system.insight.domain.entity.ScoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ScoreEntityRepository : JpaRepository<ScoreEntity, Long> {
    fun findByUserId(userId: String): ScoreEntity?

    fun findByScoreGreaterThanEqual(score: Int): List<ScoreEntity>
}