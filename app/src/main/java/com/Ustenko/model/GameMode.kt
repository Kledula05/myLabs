package com.Ustenko.model

enum class GameMode {
    SINGLE, PAIR;

    companion object {
        fun fromValue(value: String?): GameMode = if (value == PAIR.name) PAIR else SINGLE
    }
}

object GameExtras {
    const val MODE = "extra_mode"
    const val PLAYER1_NAME = "extra_player1_name"
    const val PLAYER2_NAME = "extra_player2_name"
    const val TARGET_SCORE = "extra_target_score"

    const val SCORE1 = "extra_score1"
    const val SCORE2 = "extra_score2"
    const val AVG_REACTION1 = "extra_avg_reaction1"
    const val AVG_REACTION2 = "extra_avg_reaction2"
    const val BEST_REACTION = "extra_best_reaction"
    const val BEST_REACTION1 = "extra_best_reaction1"
    const val BEST_REACTION2 = "extra_best_reaction2"
    const val FALSE_STARTS1 = "extra_false_starts1"
    const val FALSE_STARTS2 = "extra_false_starts2"
}
