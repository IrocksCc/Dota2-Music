package com.dota2.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dota2.music.databinding.ActivityPlayerScreenBinding

class PlayerScreenActivity : AppCompatActivity() {

    private var mActivityPlayerScreenBinding: ActivityPlayerScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityPlayerScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_screen)
    }
}