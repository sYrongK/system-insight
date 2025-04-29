package com.system.insight.application.mail

import com.system.insight.persistence.redis.RedisRepository
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service("customMailSender")
class MailSender(private val mailSender: JavaMailSender) {

    private val log = LoggerFactory.getLogger(RedisRepository::class.java)

    fun sendMail(to: String, subject: String, content: String) {
        try {
            val message = SimpleMailMessage()
            message.setTo(to)
            message.setSubject(subject)
            message.setText(content)
            mailSender.send(message)
        } catch (e: Exception) {
            log.error("메일 발송 실패 ::: [to=$to], [subject=$subject], [content=$content]")
            throw e
        }
    }
}