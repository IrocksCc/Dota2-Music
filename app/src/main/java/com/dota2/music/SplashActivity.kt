package com.dota2.music

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dota2.music.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var mActivitySplashBinding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySplashBinding = DataBindingUtil.setContentView<ActivitySplashBinding?>(this, R.layout.activity_splash)

        init()
    }

    private fun init() {
        initTypeFace()
    }

    private fun initTypeFace() {
        val puHuiTiFont = Typeface.createFromAsset(assets, PUHUITIF_FONT)
        val hyChaoCuYuanJFont = Typeface.createFromAsset(assets, HYChaoCuYuanJ_FONT)
        mActivitySplashBinding?.splashText1?.typeface = puHuiTiFont
        mActivitySplashBinding?.splashText2?.typeface = hyChaoCuYuanJFont
    }

    companion object {
        private const val PUHUITIF_FONT = "fonts/Alibaba-PuHuiTi-Heavy.ttf"
        private const val HYChaoCuYuanJ_FONT = "fonts/HYChaoCuYuanJ.ttf"
    }
}