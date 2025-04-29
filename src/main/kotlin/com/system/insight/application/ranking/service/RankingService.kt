package com.system.insight.application.ranking.service

import com.system.insight.controller.response.RankingResponse
import com.system.insight.domain.document.RankingDocument
import com.system.insight.persistence.mongo.RankingDocumentRepository
import com.system.insight.persistence.mongo.ScoreDocumentRepository
import com.system.insight.persistence.mongo.UserDocumentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RankingService(
    var userDocumentRepository: UserDocumentRepository,
    var scoreDocumentRepository: ScoreDocumentRepository,
    var rankingRepository: RankingDocumentRepository
) {

    @Transactional
    fun recordScore() {
        val top10 = scoreDocumentRepository.findTop10ByOrderByScoreDesc()
        if (!top10.isEmpty()) {
            val rankingTop10 = top10.mapIndexed { index, scoreDocument ->
                RankingDocument(rank = index + 1, userId = scoreDocument.userId, score = scoreDocument.score)
            }
            rankingRepository.saveAll(rankingTop10)
        }
    }

    @Transactional(readOnly = true)
    fun getTop10(): List<RankingResponse> {
        val list = rankingRepository.findAll().map {
            RankingResponse(it.rank!!, it.userId!!, it.score!!)
        }

        if (list.isEmpty()) {
            return getTop10WithRecovery()
        } else {
            list.forEach{
                val user = userDocumentRepository.findByUserId(it.userId) ?: throw IllegalArgumentException("User not found")//todo exception
                it.nickname = user.nickname
                it.profileImageUrl = user.profileImageUrl
            }
        }
        return list;
    }

    @Transactional
    fun getTop10WithRecovery(): List<RankingResponse> {
        var ranking: List<RankingResponse> = emptyList()

        val top10 = scoreDocumentRepository.findTop10ByOrderByScoreDesc()
        if (!top10.isEmpty()) {
            val rankingTop10 = top10.mapIndexed { index, scoreDocument ->
                RankingDocument(rank = index + 1, userId = scoreDocument.userId, score = scoreDocument.score)
            }
            rankingRepository.saveAll(rankingTop10)

            ranking = top10.mapIndexed { index, scoreDocument ->
                val user = userDocumentRepository.findByUserId(scoreDocument.userId!!) ?: throw IllegalArgumentException("User not found")//todo exception
                RankingResponse(index + 1, user.nickname, scoreDocument.userId!!, user.profileImageUrl, scoreDocument.score!!)
            }
        }
        return ranking
    }
}