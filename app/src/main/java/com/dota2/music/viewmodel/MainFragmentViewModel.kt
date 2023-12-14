package com.dota2.music.viewmodel

import androidx.lifecycle.ViewModel
import com.dota2.music.bean.BannerBean
import com.dota2.music.tools.peek.UnPeekLiveData

class MainFragmentViewModel: ViewModel() {

    // banner有没有发生变化
    val banner: UnPeekLiveData<MutableList<BannerBean>> by lazy { UnPeekLiveData<MutableList<BannerBean>>() }

    fun getBanner() {
        // 通过repo从网络获取banner数据url
//        banner?.value = repo.getBanner
    }
}