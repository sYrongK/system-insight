package com.system.insight.persistence.jpa

import com.system.insight.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository : JpaRepository<UserEntity, Long> {

    fun findByUserId(userId: String): UserEntity?
}