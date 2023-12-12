package com.dota2.music.tools.ui

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

open class BaseActivity: AppCompatActivity() {

    private val mViewModelScope: ViewModelScope? by lazy { ViewModelScope() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun <T: ViewModel> getActivityViewModel(activity: AppCompatActivity, modeClass: Class<T>): T? {
        return mViewModelScope?.getActivityViewModel(activity, modeClass)
    }

    fun <T: ViewModel> getApplicationViewModel(modeClass: Class<T>): T? {
        return mViewModelScope?.getApplicationViewModel(modeClass)
    }
}