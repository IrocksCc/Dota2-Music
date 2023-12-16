package com.dota2.music.viewmodel

import androidx.lifecycle.ViewModel
import com.dota2.music.repository.RequestManager
import com.dota2.music.tools.peek.UnPeekLiveData
import com.dota2.player.bean.DefaultAlbum

class MusicRequestViewModel: ViewModel() {
    val musicData: UnPeekLiveData<DefaultAlbum?> by lazy { UnPeekLiveData<DefaultAlbum?>() }


    fun requstMusicData() {
        RequestManager.mInstance.getMusicData(musicData)
    }
}