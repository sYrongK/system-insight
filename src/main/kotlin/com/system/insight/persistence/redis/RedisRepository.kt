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

    /*
    executePipelined로 일정 사이즈만큼씩 flush하게 처리
     */
    fun addScoreByPipeline(key: String, tuple: Set<TypedTuple<String>>) {
        log.info("호출했다!!! ::: $tuple.size")
        redisTemplate.executePipelined{connection ->
            val keySerializer = redisTemplate.stringSerializer
            val valueSerializer = redisTemplate.stringSerializer
            tuple.toList()
                .chunked(35)//size만큼 list를 조각냄
                .forEachIndexed{index, batch ->//잘린 조각(batch) 하나하나 순회
                    batch.forEach{ tuple ->
                        connection.zSetCommands().zAdd(keySerializer.serialize(key)!!, tuple.score!!, valueSerializer.serialize(tuple.value)!!)
                    }
                }
            null // 중요! executePipelined 블록 안에서는 null 리턴
        }
    }

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