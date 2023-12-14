package com.dota2.music.viewmodel

import androidx.lifecycle.ViewModel
import com.dota2.music.tools.peek.UnPeekLiveData

class ShareViewModel: ViewModel() {
    val toOpenOrCloseDrawer: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData() }

    // 设置sliding的监听
    val toAddSlidingListener: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData() }

    //
    val toCloseDrawerIfExpand: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData() }
}