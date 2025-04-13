package com.system.insight.persistence.redis

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Repository

@Repository
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val log = LoggerFactory.getLogger(RedisRepository::class.java)

    fun addScore(key: String, member: String, score: Double) {
        try {
            redisTemplate.opsForZSet().add(key, member, score)
        } catch (e: Exception) {
            log.error("Redis Add Failed ::: [key={}] [member={}] [score={}]", key, member, score)
            throw e
        }
    }

    fun addScores(key: String, tuple: Set<TypedTuple<String>>) {
        redisTemplate.opsForZSet().add(key, tuple);
    }

    fun getRangeWithScoreList(key: String, rangeStartIdx: Long, rangeEndIdx: Long): List<TypedTuple<String>> {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, rangeStartIdx, rangeEndIdx)?.toList() ?: emptyList()
    }
}