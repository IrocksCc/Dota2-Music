package com.dota2.music.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dota2.music.R
import com.dota2.music.base.BaseFragment
import com.dota2.music.databinding.FragmentPlayerScreenBinding
import com.dota2.music.viewmodel.PlayerFragmentViewModel
import com.dota2.music.viewmodel.PlayerScreenFragmentViewModel
import com.dota2.player.DefaultPlayerManager

class PlayerScreenFragment : BaseFragment() {

    private var mFragmentPlayerBinding: FragmentPlayerScreenBinding? = null
    private var mPlayerFragmentViewModel: PlayerScreenFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_screen, container, false)
        mFragmentPlayerBinding = DataBindingUtil.bind<FragmentPlayerScreenBinding?>(view)
        mPlayerFragmentViewModel = getFragmentViewModel(this, PlayerScreenFragmentViewModel::class.java)
        mFragmentPlayerBinding?.vm = mPlayerFragmentViewModel
        mFragmentPlayerBinding?.click = ClickProxy()

        return view
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
    }
}