package com.system.insight.application.ranking.service

import com.system.insight.controller.response.RankingResponse
import com.system.insight.persistence.jpa.ScoreRepository
import com.system.insight.persistence.jpa.UserRepository
import com.system.insight.persistence.redis.RedisRepository
import org.springframework.data.redis.core.DefaultTypedTuple
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RankingService(
    var redisRepository: RedisRepository,
    var userRepository: UserRepository,
    var scoreRepository: ScoreRepository,
) {

    @Transactional
    fun recordScore(member: String, score: Double) {
        redisRepository.addScore("ranking", member, score)
    }

    @Transactional(readOnly = true)
    fun getTop10(): List<TypedTuple<String>> {
        return redisRepository.getRangeWithScoreList("ranking", 0, 9)
    }

    @Transactional
    fun getTop10WithRecovery(): List<RankingResponse> {
        var ranking: List<RankingResponse> = emptyList()
        val recovered = recoverScore()
        if (recovered) {
            val top10 = redisRepository.getRangeWithScoreList("ranking", 0, 9)

            ranking = top10.mapIndexed { index, tuple ->
                val member = tuple.value!!
                val score = tuple.score!!
                val user = userRepository.findByUserId(member) ?: throw IllegalArgumentException("User not found")//todo exception
                RankingResponse(index + 1, user.nickname!!, tuple.value!!, user.profileImageUrl!!, score.toInt())
            }
        }
        return ranking
    }

    @Transactional
    fun recoverScore(): Boolean {//todo 복구 batch 동작시 없으면 recoverScore()
        var recovered = false
        val tuples: Set<TypedTuple<String>> = scoreRepository.findAll()
            .map {
                DefaultTypedTuple(it.userId, it.score?.toDouble() ?:0.0)
            }.toSet()

        if (tuples.isNotEmpty()) {
            redisRepository.addScores("ranking", tuples)
            recovered = true
        }
        return recovered
    }

    fun setRankingList(data: List<TypedTuple<String>>): List<RankingResponse> {
        return data.mapIndexed { index, tuple ->
            val member = tuple.value!!
            val score = tuple.score!!
            val user = userRepository.findByUserId(member) ?: throw IllegalArgumentException("User not found")//todo exception
            RankingResponse(index + 1, user.nickname!!, tuple.value!!, user.profileImageUrl!!, score.toInt())
        }
    }
}