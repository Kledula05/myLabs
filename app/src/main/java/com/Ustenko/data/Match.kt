package com.Ustenko.data

data class Match(
    val id: Long = 0,
    val player1Name: String,
    val player2Name: String,
    val score1: Int,
    val score2: Int,
    val targetScore: Int,
    val avgReaction1: Long,
    val avgReaction2: Long,
    val bestReaction: Long,
    val falseStarts: String,
    val playedAt: Long = System.currentTimeMillis()
) {
    val isSingleMode: Boolean get() = player2Name.isEmpty()

    val falseStarts1: Int get() = falseStarts.substringBefore(',').toIntOrNull() ?: 0
    val falseStarts2: Int get() = falseStarts.substringAfter(',', "0").toIntOrNull() ?: 0
}
