package com.dota2.music.repository

import android.util.Log
import com.dota2.music.R
import com.dota2.music.tools.peek.UnPeekLiveData
import com.dota2.music.tools.util.Utils
import com.dota2.player.bean.DefaultAlbum
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RequestManager {

    fun getMusicData(musicData: UnPeekLiveData<DefaultAlbum?>) {
        Log.e("LDX", "RequestManager getMusicData")
        val gson = Gson()
        val type = object : TypeToken<DefaultAlbum?>() {}.type
        val testAlbum =
            gson.fromJson<DefaultAlbum>(Utils.getApplication()?.getString(R.string.music_data_json), type)


        Log.e("LDX", "RequestManager testAlbum: ${testAlbum}")
        Log.e("LDX", "RequestManager getApp: ${Utils.getApplication()}")

        musicData.value = testAlbum
    }

    companion object {
        val mInstance = RequestManager()
    }
}