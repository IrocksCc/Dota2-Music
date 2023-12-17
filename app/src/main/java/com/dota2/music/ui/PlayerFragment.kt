package com.dota2.music.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.dota2.music.R
import com.dota2.music.base.BaseFragment
import com.dota2.music.databinding.FragmentMainBinding
import com.dota2.music.databinding.FragmentPlayerBinding
import com.dota2.music.viewmodel.MainFragmentViewModel
import com.dota2.music.viewmodel.PlayerFragmentViewModel
import com.dota2.player.DefaultPlayerManager

class PlayerFragment : BaseFragment() {

    private var mFragmentPlayerBinding: FragmentPlayerBinding? = null
    private var mPlayerFragmentViewModel: PlayerFragmentViewModel? = null
//    private val mMusic:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        // 首次进入设置为当前播放的歌曲
        setMusicInfo()
    }

    private fun setMusicInfo() {
        val currentPlayingMusic = DefaultPlayerManager.instance.getCurrentPlayingMusic()
        mPlayerFragmentViewModel?.songImage?.value = currentPlayingMusic?.coverImg
        mPlayerFragmentViewModel?.songTitle?.value = currentPlayingMusic?.title
        mPlayerFragmentViewModel?.songArtist?.value = currentPlayingMusic?.artist?.name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        mFragmentPlayerBinding = DataBindingUtil.bind<FragmentPlayerBinding?>(view)
        mPlayerFragmentViewModel = getFragmentViewModel(this, PlayerFragmentViewModel::class.java)
        mFragmentPlayerBinding?.vm = mPlayerFragmentViewModel
        mFragmentPlayerBinding?.click = ClickProxy()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 监听音乐变化，实时更新图片
        DefaultPlayerManager.instance.uiStates.observe(viewLifecycleOwner) {
            mPlayerFragmentViewModel?.songImage?.value = it?.img
            mPlayerFragmentViewModel?.songTitle?.value = it?.title
            mPlayerFragmentViewModel?.songArtist?.value = it?.artist?.name
        }
    }

    inner class ClickProxy {
        fun openPlayerScreen() {
            // 必须打开一个新的fragment
            // 不能使用navigaiton跳转=
            NavHostFragment.findNavController(this@PlayerFragment).navigate(R.id.action_playerFragment_to_playerScreenFragment)
        }


        fun pause() {

        }

        fun next() {

        }
    }
}