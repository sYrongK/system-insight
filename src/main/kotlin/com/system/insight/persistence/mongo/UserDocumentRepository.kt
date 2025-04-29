package com.system.insight.persistence.mongo

import com.system.insight.domain.document.UserDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface UserDocumentRepository : MongoRepository<UserDocument, String> {

    fun findByUserId(userId: String): UserDocument?
}