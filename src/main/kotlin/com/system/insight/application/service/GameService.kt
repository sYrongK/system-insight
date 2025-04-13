package com.system.insight.application.service

import com.system.core.error.exception.RankingException
import com.system.insight.application.eventListener.RankingEventListener
import com.system.insight.application.eventListener.event.RankingScoreRecordedEvent
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.domain.entity.ScoreEntity
import com.system.insight.domain.entity.UserEntity
import com.system.insight.persistence.jpa.ScoreRepository
import com.system.insight.persistence.jpa.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    var userRepository: UserRepository,
    var scoreRepository: ScoreRepository,
    var rankingEventListener: RankingEventListener
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun playing(playingRequest: PlayingRequest): Long {
        var user = UserEntity()
        userRepository.findByUserId(playingRequest.userId)?.let {
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
        scoreRepository.findByUserId(playingRequest.userId)?.let {
            it.score = it.score?.plus(playingRequest.score)
            score = it
        } ?: run{
            score = ScoreEntity(userId = user.userId, score = playingRequest.score)
        }

        userRepository.save(user)
        val scoreEntity = scoreRepository.save(score)
        return scoreEntity.id ?: 0
    }

    fun recordScore(scoreId: Long) {
        val userScore: ScoreEntity = scoreRepository.findById(scoreId).get()

        val member: String = userScore.userId ?: throw IllegalArgumentException("Redis member [UserEntity > userId] does not exist")
        val value: Double = userScore.score?.toDouble() ?: 0.0
        rankingEventListener.handleScoreRecordedEvent(RankingScoreRecordedEvent(member, value))
    }
}
