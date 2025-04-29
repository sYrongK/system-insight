package com.system.insight.persistence.mongo

import com.system.insight.domain.document.ScoreDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface ScoreDocumentRepository : MongoRepository<ScoreDocument, String> {

    fun findByUserId(userId: String): ScoreDocument?
    fun findTop10ByOrderByScoreDesc(): List<ScoreDocument>
}