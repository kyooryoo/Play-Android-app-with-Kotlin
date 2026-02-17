package com.example.kmp.getstarted.android

import android.app.Application
import com.test.shared.AndroidPlatform
import com.test.shared.setupApp

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setupApp(AndroidPlatform())
    }
}