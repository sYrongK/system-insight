package com.system.insight.domain.entity

import java.time.LocalDateTime

class UserEntity(
    var id: Long,
    var nickName: String,
    var profileImageUrl: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {

}