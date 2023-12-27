package com.dota2.music

import android.app.Application
import android.util.Log
import com.dota2.music.tools.util.Utils
import com.dota2.player.DefaultPlayerManager

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.e("LDX", "MyApplication onCreate")
        Utils.init(this)
        DefaultPlayerManager.instance.init(this)
    }
}