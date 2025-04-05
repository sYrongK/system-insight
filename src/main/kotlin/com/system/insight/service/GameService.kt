package com.system.insight.service

import com.system.insight.controller.model.PlayingModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService {

    @Transactional
    fun playing(playingModel: PlayingModel) {
        TODO("Not yet implemented")
    }

}
