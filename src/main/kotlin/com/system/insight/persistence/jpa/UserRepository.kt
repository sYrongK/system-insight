package com.system.insight.persistence.jpa

import com.system.insight.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByUserId(userId: String): UserEntity?
}