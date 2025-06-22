package com.imkaem.android.upuli

import android.app.Application
import android.content.Context

class UPuliApplication : Application() {

    init {
        app = this
    }

    companion object {
        /* TODO this is just temp - until introduce hilt */
        private lateinit var app: UPuliApplication

        fun getApplicationContext(): Context = app.applicationContext
    }
}