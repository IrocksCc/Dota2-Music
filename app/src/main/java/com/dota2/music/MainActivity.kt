package com.dota2.music

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dota2.music.databinding.ActivityMainBinding
import com.dota2.music.base.BaseActivity
import com.dota2.music.viewmodel.HomeFragmentViewModel

class MainActivity : BaseActivity() {

    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainActivityViewModel: HomeFragmentViewModel? = null
    private var mListened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mMainActivityViewModel = getActivityViewModel(this, HomeFragmentViewModel::class.java)
        mActivityMainBinding?.lifecycleOwner = this
        mActivityMainBinding?.vm = mMainActivityViewModel

        mSharedViewModel?.toOpenOrCloseDrawer?.observe(this) {
            mMainActivityViewModel?.openDrawer?.value = it
        }

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_home) as NavHostFragment?
        val navController = navHostFragment?.navController
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

        if (!mListened) {
            mSharedViewModel?.toAddSlidingListener?.value = true
        }

        mListened = true
    }

    // 按back键需要收回Drawer
    override fun onBackPressed() {
        super.onBackPressed()

        mSharedViewModel?.toCloseDrawerIfExpand?.value = true
    }
}