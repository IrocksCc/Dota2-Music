package com.dota2.music

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.dota2.music.databinding.ActivitySplashBinding
import com.dota2.music.tools.constans.TypeFaceConstans
import com.dota2.music.tools.util.TypeFaceUtils
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private var mDisposable: Disposable? = null
    private var mActivitySplashBinding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySplashBinding = DataBindingUtil.setContentView<ActivitySplashBinding?>(this, R.layout.activity_splash)

        init()
        startAnimation()
        startIntent()
    }

    private fun startAnimation() {
        val imageAnimation =
            ObjectAnimator.ofFloat(mActivitySplashBinding?.splashImage, "translationX", -500f, 0f)
        imageAnimation.duration = ANIMATION_DURATION
        imageAnimation.start()

        val titleAnimation =
            ObjectAnimator.ofFloat(mActivitySplashBinding?.splashTitle, "translationX", 500f, 0f)
        titleAnimation.duration = ANIMATION_DURATION
        titleAnimation.start()

        val summaryAnimation =
            ObjectAnimator.ofFloat(mActivitySplashBinding?.splashSummary, "translationX", 500f, 0f)
        summaryAnimation.duration = ANIMATION_DURATION
        summaryAnimation.start()

        summaryAnimation.addListener(object : AnimatorListener{
            override fun onAnimationStart(animation: Animator) {
                // to do nothing
            }

            override fun onAnimationEnd(animation: Animator) {
                mActivitySplashBinding?.splashSummaryDivide1?.visibility = View.VISIBLE
                mActivitySplashBinding?.splashSummaryDivide2?.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator) {
                // to do nothing
            }

            override fun onAnimationRepeat(animation: Animator) {
                // to do nothing
            }
        })
    }

    private fun startIntent() {
        mDisposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
            .subscribe {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
    }

    private fun init() {
        initTypeFace()
    }

    private fun initTypeFace() {
        // TODO 设置字体 将来抽到TypeFaceUtils
        val puHuiTiFont = TypeFaceUtils.getTypeFace(this, TypeFaceConstans.PUHUITIF_FONT)
        val shuHeiFont = TypeFaceUtils.getTypeFace(this, TypeFaceConstans.SHUHEI_FONT)
        mActivitySplashBinding?.splashTitle?.typeface = puHuiTiFont
        mActivitySplashBinding?.splashSummary?.typeface = shuHeiFont
    }

    override fun onDestroy() {
        super.onDestroy()

        mDisposable?.dispose()
    }

    companion object {
        private const val ANIMATION_DURATION = 1000L
    }
}