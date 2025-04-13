package com.system.insight.persistence.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Repository

@Repository
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun addScore(key: String, member: String, score: Double) {
        redisTemplate.opsForZSet().add(key, member, score)
    }

    fun addScores(key: String, tuple: Set<TypedTuple<String>>) {
        redisTemplate.opsForZSet().add(key, tuple);
    }

    fun getRangeWithScoreList(key: String, rangeStartIdx: Long, rangeEndIdx: Long): List<TypedTuple<String>> {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, rangeStartIdx, rangeEndIdx)?.toList() ?: emptyList()
    }
}