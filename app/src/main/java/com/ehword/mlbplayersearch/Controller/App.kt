package com.ehword.mlbplayersearch.Controller

import android.app.Application
import android.content.pm.ApplicationInfo
import com.ehword.mlbplayersearch.Controller.Utilities.SharedPrefs

class App: Application() {

    companion object {
        lateinit var sharedPreferences: SharedPrefs
    }

    override fun onCreate() {

        sharedPreferences = SharedPrefs(applicationContext)
        super.onCreate()
    }
}