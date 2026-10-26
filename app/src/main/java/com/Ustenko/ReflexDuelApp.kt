package com.Ustenko

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.Ustenko.data.SettingsManager
import com.Ustenko.ui.base.BaseActivity

class ReflexDuelApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val settings = SettingsManager(this)
        AppCompatDelegate.setDefaultNightMode(BaseActivity.nightModeFor(settings.theme))
    }
}
