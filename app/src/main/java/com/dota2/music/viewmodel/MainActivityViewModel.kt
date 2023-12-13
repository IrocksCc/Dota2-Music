package com.dota2.music.viewmodel

import androidx.lifecycle.ViewModel
import com.dota2.music.tools.peek.UnPeekLiveData

class MainActivityViewModel: ViewModel(){

    val allowDrawerOpen: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData<Boolean>() }

    val openDrawer: UnPeekLiveData<Boolean> by lazy { UnPeekLiveData<Boolean>() }
}