package com.Ustenko.data

import android.content.Context

class SettingsManager(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var theme: String
        get() = prefs.getString(KEY_THEME, DEFAULT_THEME) ?: DEFAULT_THEME
        set(value) = prefs.edit().putString(KEY_THEME, value).apply()

    var vibration: Boolean
        get() = prefs.getBoolean(KEY_VIBRATION, true)
        set(value) = prefs.edit().putBoolean(KEY_VIBRATION, value).apply()

    var sound: Boolean
        get() = prefs.getBoolean(KEY_SOUND, false)
        set(value) = prefs.edit().putBoolean(KEY_SOUND, value).apply()

    var targetScore: Int
        get() = prefs.getInt(KEY_TARGET_SCORE, 5)
        set(value) = prefs.edit().putInt(KEY_TARGET_SCORE, value).apply()

    var player1Name: String
        get() = prefs.getString(KEY_PLAYER1_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_PLAYER1_NAME, value).apply()

    var player2Name: String
        get() = prefs.getString(KEY_PLAYER2_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_PLAYER2_NAME, value).apply()

    companion object {
        private const val PREFS_NAME = "reflex_duel_prefs"
        private const val KEY_THEME = "theme"
        private const val KEY_VIBRATION = "vibration"
        private const val KEY_SOUND = "sound"
        private const val KEY_TARGET_SCORE = "targetScore"
        private const val KEY_PLAYER1_NAME = "player1Name"
        private const val KEY_PLAYER2_NAME = "player2Name"

        const val DEFAULT_THEME = "system"

        const val THEME_LIGHT = "light"
        const val THEME_DARK = "dark"
        const val THEME_SYSTEM = "system"
    }
}
