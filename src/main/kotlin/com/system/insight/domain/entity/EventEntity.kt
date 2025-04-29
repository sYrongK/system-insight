package com.system.insight.domain.entity

import com.system.core.code.EventType
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "event")
@EntityListeners(AuditingEntityListener::class)
open class EventEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    open var type: EventType? = null,

    @Column(name = "start_date", nullable = false)
    open var startDate: LocalDateTime? = null,

    @Column(name = "end_date", nullable = false)
    open var endDate: LocalDateTime? = null,

    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "updated_at")
    open var updatedAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        return this.id == (other as EventEntity).id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 31
    }
}