package com.system.insight.application.service

import com.system.insight.controller.request.PlayingRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService {

    @Transactional
    fun playing(playingRequest: PlayingRequest) {
        TODO("Not yet implemented")
        /*
        todo
        1. nickname, userId, profileImageUrl → RDB (이 경우엔 nosql도 괜찮나?)
            - RDB 먼저 저장 (에러 발생시 async 취소할 일 없게)
            - 에러 발생시 로그 남기기 json string 그대로
        2. score 누적 데이터 생성
        3. score 누적 데이터 저장 → RDB (원본이자 실시간)
            - 1~3번 까지 sync로!
        4. score 누적 데이터 저장 → Redis
            - 재시도 3번 반복하게 넣을까 최종실패시 로그 남기기
         */

    }

}
