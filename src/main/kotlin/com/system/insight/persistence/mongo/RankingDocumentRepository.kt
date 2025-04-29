package com.system.insight.persistence.mongo

import com.system.insight.domain.document.RankingDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface RankingDocumentRepository : MongoRepository<RankingDocument, String> {
}