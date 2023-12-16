package com.dota2.music

import android.app.Application
import android.util.Log
import com.dota2.music.tools.util.Utils

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.e("LDX", "MyApplication onCreate")
        Utils.init(this)
    }
}