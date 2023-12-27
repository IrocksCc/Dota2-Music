package com.dota2.music.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dota2.music.R
import com.dota2.music.base.BaseActivity
import com.dota2.music.databinding.ActivityPlayerScreenBinding
import com.dota2.music.viewmodel.PlayerScreenActivityViewModel
import com.dota2.player.DefaultPlayerManager

class PlayerScreenActivity : BaseActivity() {

    private var mActivityPlayerScreenBinding: ActivityPlayerScreenBinding? = null
    private var mPlayerScreenActivityViewModel: PlayerScreenActivityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPlayerScreenBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_player_screen
        )
        mPlayerScreenActivityViewModel = getActivityViewModel(this, PlayerScreenActivityViewModel::class.java)
        mActivityPlayerScreenBinding?.vm = mPlayerScreenActivityViewModel
        mActivityPlayerScreenBinding?.click = ClickProxy()
    }

    override fun onResume() {
        super.onResume()

        // 按下back按钮退出播放界面
        mPlayerScreenActivityViewModel?.exitPlayerScreen?.observe(this) {
            if (it) {
                finish()
                overridePendingTransition(
                    R.anim.anim_no_anim,
                    R.anim.anim_slide_exit_bottom
                )
            }
        }

        // 点击封面切换到歌词
    }


    inner class ClickProxy {
        fun playNext() = DefaultPlayerManager.instance.playNext()

        fun playPrevious() = DefaultPlayerManager.instance.playPrevious()

        fun togglePlay() = DefaultPlayerManager.instance.togglePlay()

        fun changeRepeatMode() = DefaultPlayerManager.instance.changeMode()

        // 拖动播放条
        fun dragSeekBar() {

        }

        fun openSongsMenu() {

        }

        fun clickBack() {
            mPlayerScreenActivityViewModel?.exitPlayerScreen?.value = true
        }

        fun download() {

        }

        fun store() {

        }

        fun detail() {

        }

        // 点击封面
        fun clickCoverImage() {

        }
    }
}