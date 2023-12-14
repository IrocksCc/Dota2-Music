package com.dota2.music.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.dota2.music.tools.ui.ViewModelScope
import com.dota2.music.viewmodel.ShareViewModel

open class BaseFragment: Fragment() {

    private val mViewModelScope: ViewModelScope? by lazy { ViewModelScope() }
    var mSharedViewModel: ShareViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSharedViewModel = getApplicationViewModel(ShareViewModel::class.java)
    }

    fun <T: ViewModel> getFragmentViewModel(fragment: Fragment, modeClass: Class<T>): T? {
        return mViewModelScope?.getFragmentViewModel(fragment, modeClass)
    }

    fun <T: ViewModel> getApplicationViewModel(modeClass: Class<T>): T? {
        return mViewModelScope?.getApplicationViewModel(modeClass)
    }
}