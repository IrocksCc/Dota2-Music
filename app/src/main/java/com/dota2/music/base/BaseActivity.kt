package com.dota2.music.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.dota2.music.tools.ui.ViewModelScope
import com.dota2.music.viewmodel.ShareViewModel
import kotlin.reflect.KProperty

open class BaseActivity: AppCompatActivity() {

    private val mViewModelScope: ViewModelScope? by lazy { ViewModelScope() }
    var mSharedViewModel: ShareViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSharedViewModel = getApplicationViewModel(ShareViewModel::class.java)
    }

    fun <T: ViewModel> getActivityViewModel(activity: AppCompatActivity, modeClass: Class<T>): T? {
        return mViewModelScope?.getActivityViewModel(activity, modeClass)
    }

    fun <T: ViewModel> getApplicationViewModel(modeClass: Class<T>): T? {
        return mViewModelScope?.getApplicationViewModel(modeClass)
    }
}
