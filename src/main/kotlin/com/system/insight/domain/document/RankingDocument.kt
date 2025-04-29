package com.system.insight.domain.document

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ranking")
class RankingDocument(
    @Id
    open var id: String? = null,

    @Indexed
    open var rank: Int? = 0,

    @Indexed
    open var userId: String? = null,

    open var score: Int? = 0
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false;
        return this.id == (other as RankingDocument).id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 31
    }
}