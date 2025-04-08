package com.system.insight.domain.entity

import java.time.LocalDateTime

class ScoreEntity(
    var id: Long,
    var userId: Long,
    var score: Int,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {
}