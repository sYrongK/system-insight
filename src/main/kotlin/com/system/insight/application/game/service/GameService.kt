package com.system.insight.application.game.service

import com.system.insight.application.event.listener.RankingEventListener
import com.system.insight.application.event.RankingScoreRecordedEvent
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.domain.entity.ScoreEntity
import com.system.insight.domain.entity.UserEntity
import com.system.insight.persistence.jpa.ScoreEntityRepository
import com.system.insight.persistence.jpa.UserEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    var userEntityRepository: UserEntityRepository,
    var scoreEntityRepository: ScoreEntityRepository,
    var rankingEventListener: RankingEventListener,
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun playing(playingRequest: PlayingRequest): Long {
        var user = UserEntity()
        userEntityRepository.findByUserId(playingRequest.userId)?.let {
            user = UserEntity(
                id = it.id,
                userId = it.userId,
                nickname = playingRequest.nickname,
                profileImageUrl = playingRequest.profileImageUrl,
                createdAt = it.createdAt)
        } ?: run{
            user = UserEntity(
                userId = playingRequest.userId,
                nickname = playingRequest.nickname,
                profileImageUrl = playingRequest.profileImageUrl)
        }


        var score = ScoreEntity()
        scoreEntityRepository.findByUserId(playingRequest.userId)?.let {
            it.score = it.score?.plus(playingRequest.score)
            score = it
        } ?: run{
            score = ScoreEntity(userId = user.userId, score = playingRequest.score)
        }

        userEntityRepository.save(user)
        val scoreEntity = scoreEntityRepository.save(score)
        return scoreEntity.id ?: 0
    }

    fun recordScore(scoreId: Long) {
        val userScore: ScoreEntity = scoreEntityRepository.findById(scoreId).get()

        val member: String = userScore.userId ?: throw IllegalArgumentException("Redis member [UserEntity > userId] does not exist")
        val value: Double = userScore.score?.toDouble() ?: 0.0
        rankingEventListener.handleScoreRecordedEvent(RankingScoreRecordedEvent(member, value))
    }
}
