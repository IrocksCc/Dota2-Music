package com.dota2.music.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dota2.music.tools.peek.UnPeekLiveData
import com.dota2.player.domain.PlayingInfoManager

class PlayerScreenActivityViewModel: ViewModel() {
    val songImage: UnPeekLiveData<String> by lazy { UnPeekLiveData() }

    val songTitle: UnPeekLiveData<String> by lazy { UnPeekLiveData() }

    val songArtist: UnPeekLiveData<String> by lazy { UnPeekLiveData() }

    val currentSeekbar: ObservableField<Int> by lazy { ObservableField() }

    val maxSeekbar: ObservableField<Int> by lazy { ObservableField() }

    val repeatMode: ObservableField<Enum<PlayingInfoManager.RepeatMode>> by lazy { ObservableField() }

    val openSongsMenu: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData() }

    val exitPlayerScreen: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData() }
}