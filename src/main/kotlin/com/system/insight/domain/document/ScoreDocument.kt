package com.system.insight.domain.document

import com.system.insight.domain.entity.UserEntity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "user_score")
open class ScoreDocument(
    @Id
    open var id: String? = null,

    open var userId: String? = null,

    open var score: Int? = 0,

    open var createdAt: LocalDateTime? = LocalDateTime.now(),

    open var updatedAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        return this.id == (other as ScoreDocument).id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 31
    }
}