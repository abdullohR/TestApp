package com.rasulovabdullokh.testapp.core.app

import android.app.Application
import com.rasulovabdullokh.testapp.core.database.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
    }
}