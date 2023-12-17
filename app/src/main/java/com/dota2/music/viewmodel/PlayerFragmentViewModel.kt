package com.dota2.music.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dota2.music.tools.peek.UnPeekLiveData

class PlayerFragmentViewModel: ViewModel() {
    val songImage: UnPeekLiveData<String> by lazy { UnPeekLiveData() }

    val songTitle: UnPeekLiveData<String> by lazy { UnPeekLiveData() }

    val songArtist: UnPeekLiveData<String> by lazy { UnPeekLiveData() }

    val currentSeekbar: ObservableField<Int> by lazy { ObservableField() }

    val maxSeekbar: ObservableField<Int> by lazy { ObservableField() }
}