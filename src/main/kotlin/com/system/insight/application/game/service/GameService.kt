package com.system.insight.application.game.service

import com.system.insight.controller.request.PlayingRequest
import com.system.insight.domain.document.ScoreDocument
import com.system.insight.domain.document.UserDocument
import com.system.insight.persistence.mongo.ScoreDocumentRepository
import com.system.insight.persistence.mongo.UserDocumentRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    var userDocumentRepository: UserDocumentRepository,
    var scoreDocumentRepository: ScoreDocumentRepository,
) {

    fun playing(playingRequest: PlayingRequest) {
        var user = UserDocument()
        userDocumentRepository.findByUserId(playingRequest.userId)?.let {
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
        scoreDocumentRepository.findByUserId(playingRequest.userId)?.let {
            it.score = it.score?.plus(playingRequest.score)
            score = it
        } ?: run{
            score = ScoreDocument(userId = user.userId, score = playingRequest.score)
        }

        userDocumentRepository.save(user)
        scoreDocumentRepository.save(score)
    }
}
