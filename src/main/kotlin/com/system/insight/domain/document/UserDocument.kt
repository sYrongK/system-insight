package com.system.insight.domain.document

import com.system.insight.domain.entity.UserEntity
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime


@Document(collection = "user")
class UserDocument(
    @Id
    open var id: String? = null,//MongoDB 기본 _id타입 ObjectId (24자리 hex 문자열)

    open var userId: String? = null,

    open var nickname: String? = null,

    open var profileImageUrl: String? = null,

    @CreatedDate
    open var createdAt: LocalDateTime? = LocalDateTime.now(),

    @LastModifiedDate
    open var updatedAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        return this.id == (other as UserDocument).id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 31
    }
}