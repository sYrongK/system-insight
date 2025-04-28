package com.system.insight.controller.response

class RankingResponse(
    var ranking: Int,
    var nickname: String?,
    var userId: String,
    var profileImageUrl: String?,
    var score: Int
) {
    constructor(rank : Int, userId : String, score: Int) : this(
        ranking = rank,
        nickname = null,
        userId = userId,
        profileImageUrl = null,
        score = score)
}
