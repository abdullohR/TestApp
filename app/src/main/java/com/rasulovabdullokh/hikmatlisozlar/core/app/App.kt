package com.rasulovabdullokh.hikmatlisozlar.core.app

import android.app.Application
import com.rasulovabdullokh.hikmatlisozlar.core.database.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
    }
}