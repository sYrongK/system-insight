package com.system.insight.domain.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "user_score")
@EntityListeners(AuditingEntityListener::class)
open class ScoreEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    open var userId: String? = null,

    @Column(name = "score", nullable = false)
    open var score: Int? = 0,

    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "updated_at")
    open var updatedAt: LocalDateTime? = null
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        return this.id == (other as UserEntity).id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 31
    }
}