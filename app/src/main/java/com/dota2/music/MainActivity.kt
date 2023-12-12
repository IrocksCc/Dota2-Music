package com.dota2.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dota2.music.databinding.ActivityMainBinding
import com.dota2.music.tools.ui.BaseActivity
import com.dota2.music.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity() {

    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mMainActivityViewModel = getActivityViewModel(this, MainActivityViewModel::class.java)
    }
}