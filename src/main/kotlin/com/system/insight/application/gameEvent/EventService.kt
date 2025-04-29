package com.system.insight.application.gameEvent

import com.system.core.code.EventType
import com.system.core.error.exception.RankingException
import com.system.insight.application.mail.MailSender
import com.system.insight.domain.entity.ScoreEntity
import com.system.insight.persistence.jpa.EventEntityRepository
import com.system.insight.persistence.jpa.EventRewardEntityRepository
import com.system.insight.persistence.jpa.ScoreEntityRepository
import com.system.insight.persistence.jpa.UserEntityRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EventService(
    var eventEntityRepository: EventEntityRepository,
    var scoreEntityRepository: ScoreEntityRepository,
    var userEntityRepository: UserEntityRepository,
    var eventRewardEntityRepository: EventRewardEntityRepository,
    var mailSender: MailSender,
) {

    fun processScoreRewardEvent() {
        val event = eventEntityRepository.findFirstByTypeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(EventType.SCORE_REWARD, LocalDateTime.now(), LocalDateTime.now())
            ?: return

        val scores: List<ScoreEntity> = scoreEntityRepository.findByScoreGreaterThanEqual(200)
        scores.forEach { score ->
            val eventId = event.id ?: throw RankingException("Event 정보가 잘못됐습니다.")
            val userId = score.userId ?: throw RankingException("score > userId 정보가 잘못됐습니다.")

            val alreadyRewarded = eventRewardEntityRepository.findByEventIdAndUserId(eventId, userId)
            if (alreadyRewarded != null) return@forEach

            userEntityRepository.findByUserId(userId)?.let { user ->
                val email = user.email ?: throw RankingException("회원의 메일 정보가 누락됐습니다.")
                mailSender.sendMail(email, "시즌 보상 지급", "${user.nickname}님 시즌 score 달성하셨어요!!! 보상으로 아이템 보내드립니다 :)")
            } ?: run {
                throw RankingException("이벤트 보상 지급 회원 정보 조회 도중 오류가 발생했습니다.")
            }
        }
    }
}