package com.system.insight.persistence.mongo

import com.system.insight.domain.document.ScoreDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface ScoreRepository : MongoRepository<ScoreDocument, String> {

    fun findByUserId(userId: String): ScoreDocument?
}