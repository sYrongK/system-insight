package com.system.insight.domain.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "event_reward")
@EntityListeners(AuditingEntityListener::class)
open class EventRewardEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "event_id", nullable = false)
    open var eventId: Long? = null,

    @Column(name = "user_id", nullable = false)
    open var userId: String? = null,

    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now(),
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        return this.id == (other as EventRewardEntity).id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 31
    }
}