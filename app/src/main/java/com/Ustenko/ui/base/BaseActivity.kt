package com.Ustenko.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.Ustenko.data.SettingsManager

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var settings: SettingsManager

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        settings = SettingsManager(this)
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun nightModeFor(theme: String): Int = when (theme) {
            SettingsManager.THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            SettingsManager.THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }
}
