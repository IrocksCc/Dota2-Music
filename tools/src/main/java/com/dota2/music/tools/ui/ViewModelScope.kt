package com.dota2.music.tools.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// 限定ViewModel的作用域, Application, Activity, Fragment
class ViewModelScope {
    private var mFragmentViewModelProvider: ViewModelProvider? = null
    private var mActivityViewModelProvider: ViewModelProvider? = null
    private var mApplicationViewModelProvider: ViewModelProvider? = null

    fun  <T: ViewModel> getApplicationViewModel(modeClass: Class<T>): T? {
        if (null == mApplicationViewModelProvider) {
            mApplicationViewModelProvider = ViewModelProvider(ApplicationInstance.mInstance)
        }

        return mApplicationViewModelProvider?.get(modeClass)
    }

    fun  <T: ViewModel> getActivityViewModel(activity: AppCompatActivity, modeClass: Class<T>): T? {
        if (null == mActivityViewModelProvider) {
            mActivityViewModelProvider = ViewModelProvider(activity)
        }

        return mActivityViewModelProvider?.get(modeClass)
    }

    fun  <T: ViewModel> getFragmentViewModel(fragment: Fragment, modeClass: Class<T>): T? {
        if (null == mFragmentViewModelProvider) {
            mFragmentViewModelProvider = ViewModelProvider(fragment)
        }

        return mFragmentViewModelProvider?.get(modeClass)
    }
}