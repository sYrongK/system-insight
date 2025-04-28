package com.system.insight.application.game.service

import com.system.insight.application.event.RankingScoreRecordedEvent
import com.system.insight.application.event.listener.RankingEventListener
import com.system.insight.controller.request.PlayingRequest
import com.system.insight.domain.document.ScoreDocument
import com.system.insight.domain.document.UserDocument
import com.system.insight.persistence.mongo.ScoreRepository
import com.system.insight.persistence.mongo.UserRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    var userRepository: UserRepository,
    var scoreRepository: ScoreRepository,
    var rankingEventListener: RankingEventListener,
) {

    fun playing(playingRequest: PlayingRequest) {
        var user = UserDocument()
        userRepository.findByUserId(playingRequest.userId)?.let {
            user = UserDocument(
                id = it.id,
                userId = it.userId,
                nickname = playingRequest.nickname,
                profileImageUrl = playingRequest.profileImageUrl,
                createdAt = it.createdAt)
        } ?: run{
            user = UserDocument(
                userId = playingRequest.userId,
                nickname = playingRequest.nickname,
                profileImageUrl = playingRequest.profileImageUrl)
        }


        var score = ScoreDocument()
        scoreRepository.findByUserId(playingRequest.userId)?.let {
            it.score = it.score?.plus(playingRequest.score)
            score = it
        } ?: run{
            score = ScoreDocument(userId = user.userId, score = playingRequest.score)
        }

        userRepository.save(user)
        val scoreDocument = scoreRepository.save(score)
        //todo 배치가 낫겠는데?
        rankingEventListener.handleScoreRecordedEvent(RankingScoreRecordedEvent(scoreDocument.id!!))
    }
}
