package com.dota2.music.tools.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

class BaseFragment: Fragment() {

    private val mViewModelScope: ViewModelScope? by lazy { ViewModelScope() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun <T: ViewModel> getFragmentViewModel(fragment: Fragment, modeClass: Class<T>): T? {
        return mViewModelScope?.getFragmentViewModel(fragment, modeClass)
    }
}