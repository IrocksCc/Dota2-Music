package com.dota2.music.viewmodel

import androidx.lifecycle.ViewModel
import com.dota2.music.tools.peek.UnPeekLiveData

class ShareViewModel: ViewModel() {
    val toOpenOrCloseDrawer: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData() }
}