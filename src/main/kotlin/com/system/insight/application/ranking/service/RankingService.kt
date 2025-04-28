package com.system.insight.application.ranking.service

import com.system.insight.controller.response.RankingResponse
import com.system.insight.domain.document.RankingDocument
import com.system.insight.domain.document.ScoreDocument
import com.system.insight.persistence.mongo.RankingRepository
import com.system.insight.persistence.mongo.ScoreRepository
import com.system.insight.persistence.mongo.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RankingService(
    var userRepository: UserRepository,
    var scoreRepository: ScoreRepository,
    var rankingRepository: RankingRepository
) {

    @Transactional
    fun recordScore(scoreId: String) {
        val userScore: ScoreDocument = scoreRepository.findById(scoreId).get()
        val member: String = userScore.userId ?: throw IllegalArgumentException("Redis member [UserEntity > userId] does not exist")
        rankingRepository.save(RankingDocument(userId = member, score = userScore.score))
    }

    @Transactional(readOnly = true)
    fun getTop10(): List<RankingResponse> {
        val list = rankingRepository.findAll().map {
            RankingResponse(it.rank!!, it.userId!!, it.score!!)
        }.toList()

        if (list.isEmpty()) {
            return getTop10WithRecovery()
        } else {
            list.forEach{
                val user = userRepository.findByUserId(it.userId) ?: throw IllegalArgumentException("User not found")//todo exception
                it.nickname = user.nickname
                it.profileImageUrl = user.profileImageUrl
            }
        }
        return list;
    }

    @Transactional
    fun getTop10WithRecovery(): List<RankingResponse> {
        var ranking: List<RankingResponse> = emptyList()
        //todo score 전체 가져와서 top10 세팅해서 저장하고 return
        scoreRepository.findAll();
        val recovered = recoverScore()
        if (recovered) {
            val top10 = redisRepository.getRangeWithScoreList("ranking", 0, 9)//todo 다시 가져와서 재계싼

            ranking = top10.mapIndexed { index, tuple ->
                val member = tuple.value!!
                val score = tuple.score!!
                val user = userRepository.findByUserId(member) ?: throw IllegalArgumentException("User not found")//todo exception
                RankingResponse(index + 1, user.nickname!!, tuple.value!!, user.profileImageUrl!!, score.toInt())
            }
        }
        return ranking
    }
}