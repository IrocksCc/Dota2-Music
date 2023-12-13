package com.dota2.music

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.dota2.music.databinding.ActivityMainBinding
import com.dota2.music.base.BaseActivity
import com.dota2.music.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity() {

    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mMainActivityViewModel = getActivityViewModel(this, MainActivityViewModel::class.java)
        mActivityMainBinding?.lifecycleOwner = this
        mActivityMainBinding?.vm = mMainActivityViewModel

        mSharedViewModel?.toOpenOrCloseDrawer?.observe(this) {
            mMainActivityViewModel?.openDrawer?.value = it
        }

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_home1)

        val navController = navHostFragment?.findNavController()
        navController?.let {
            mActivityMainBinding?.navBottom?.let { navView ->
                NavigationUI.setupWithNavController(
                    navView,
                    it
                )
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }
}