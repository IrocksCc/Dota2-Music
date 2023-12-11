/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kunminx.player.domain

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dota2.player.bean.base.BaseAlbumItem
import com.dota2.player.bean.base.BaseArtistItem
import com.dota2.player.bean.base.BaseMusicItem
import com.dota2.player.contract.ICacheProxy
import com.dota2.player.contract.IServiceNotifier
import com.dota2.player.domain.MusicDTO
import com.dota2.player.domain.PlayingInfoManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

/**
 * Create by IrocksCc at 23/12/11
 */
class PlayerController<B : BaseAlbumItem<M, A>?, M : BaseMusicItem<A>?, A : BaseArtistItem?> {
    private val mPlayingInfoManager: PlayingInfoManager<B, M, A> = PlayingInfoManager()
    private var mIsChangingPlayingMusic = false
    private var mICacheProxy: ICacheProxy? = null
    private var mIServiceNotifier: IServiceNotifier? = null
    private val mCurrentPlay = MusicDTO<B, M, A>()
    private val mUiStates = MutableLiveData<MusicDTO<B, M, A>>()
    private var mPlayer: ExoPlayer? = null
    private val mProgressAction = Runnable { updateProgress() }
    fun init(context: Context?, iServiceNotifier: IServiceNotifier?, iCacheProxy: ICacheProxy?) {
        mIServiceNotifier = iServiceNotifier
        mICacheProxy = iCacheProxy
        mPlayer = ExoPlayer.Builder(context!!).build()
    }

    val isInit: Boolean
        get() = mPlayingInfoManager.isInit

    fun loadAlbum(musicAlbum: B) {
        setAlbum(musicAlbum, 0)
    }

    private fun updateProgress() {
        mCurrentPlay.nowTime = calculateTime(mPlayer!!.currentPosition / 1000)
        mCurrentPlay.allTime = calculateTime(mPlayer!!.duration / 1000)
        mCurrentPlay.duration = mPlayer!!.duration.toInt()
        mCurrentPlay.progress = mPlayer!!.currentPosition.toInt()
        mUiStates.value = mCurrentPlay
        if (mCurrentPlay.allTime == mCurrentPlay.nowTime) {
            if (repeatMode === PlayingInfoManager.RepeatMode.SINGLE_CYCLE) playAgain() else playNext()
        }
        mHandler.postDelayed(mProgressAction, 1000)
    }

    private fun setAlbum(musicAlbum: B, albumIndex: Int) {
        mPlayingInfoManager.setMusicAlbum(musicAlbum)
        mPlayingInfoManager.albumIndex = albumIndex
        setChangingPlayingMusic(true)
    }

    fun loadAlbum(musicAlbum: B, albumIndex: Int) {
        setAlbum(musicAlbum, albumIndex)
        playAudio()
    }

    val isPlaying: Boolean
        get() = mPlayer!!.isPlaying
    val isPaused: Boolean
        get() = !mPlayer!!.playWhenReady

    fun playAudio(albumIndex: Int) {
        if (isPlaying && albumIndex == mPlayingInfoManager.albumIndex) {
            return
        }
        mPlayingInfoManager.albumIndex = albumIndex
        setChangingPlayingMusic(true)
        playAudio()
    }

    fun playAudio() {
        if (mIsChangingPlayingMusic) urlAndPlay else if (isPaused) resumeAudio()
    }

    private val urlAndPlay: Unit
        get() {
            val url: String
            val freeMusic: M? = mPlayingInfoManager.currentPlayingMusic
            url = freeMusic?.url !!
            if (TextUtils.isEmpty(url)) {
                pauseAudio()
            } else {
                val item: MediaItem
                if (url.contains("http:") || url.contains("ftp:") || url.contains("https:")) {
                    item = MediaItem.fromUri(mICacheProxy?.getCacheUrl(url)!!)
                } else if (url.contains("storage")) {
                    item = MediaItem.fromUri(url)
                } else {
                    item = MediaItem.fromUri(Uri.parse("file:///android_asset/$url"))
                }
                mPlayer!!.setMediaItem(item, true)
                mPlayer!!.prepare()
                mPlayer!!.play()
                afterPlay()
            }
        }

    private fun afterPlay() {
        setChangingPlayingMusic(false)
        mHandler.post(mProgressAction)
        mCurrentPlay.isPaused = false
        mUiStates.value = mCurrentPlay
        if (mIServiceNotifier != null) mIServiceNotifier!!.notifyService(true)
    }

    fun setSeek(progress: Int) {
        mPlayer!!.seekTo(progress.toLong())
    }

    fun getTrackTime(progress: Int): String {
        return calculateTime((progress / 1000).toLong())
    }

    private fun calculateTime(_time: Long): String {
        val time = _time.toInt()
        val minute: Int
        val second: Int
        return if (time >= 60) {
            minute = time / 60
            second = time % 60
            (if (minute < 10) "0$minute" else "" + minute) + if (second < 10) ":0$second" else ":$second"
        } else {
            second = time
            if (second < 10) "00:0$second" else "00:$second"
        }
    }

    fun playNext() {
        mPlayingInfoManager.countNextIndex()
        setChangingPlayingMusic(true)
        playAudio()
    }

    fun playPrevious() {
        mPlayingInfoManager.countPreviousIndex()
        setChangingPlayingMusic(true)
        playAudio()
    }

    fun playAgain() {
        setChangingPlayingMusic(true)
        playAudio()
    }

    fun pauseAudio() {
        mPlayer!!.pause()
        mHandler.removeCallbacks(mProgressAction)
        mCurrentPlay.isPaused = true
        mUiStates.value = mCurrentPlay
        if (mIServiceNotifier != null) mIServiceNotifier!!.notifyService(true)
    }

    fun resumeAudio() {
        mPlayer!!.play()
        mHandler.post(mProgressAction)
        mCurrentPlay.isPaused = false
        mUiStates.value = mCurrentPlay
        if (mIServiceNotifier != null) mIServiceNotifier!!.notifyService(true)
    }

    fun clear() {
        mPlayer!!.stop()
        mPlayer!!.clearMediaItems()
        mCurrentPlay.isPaused = true
        mUiStates.value = mCurrentPlay
        resetIsChangingPlayingChapter()
        if (mIServiceNotifier != null) mIServiceNotifier!!.notifyService(false)
    }

    fun resetIsChangingPlayingChapter() {
        mIsChangingPlayingMusic = true
        setChangingPlayingMusic(true)
    }

    fun changeMode() {
        mCurrentPlay.repeatMode = mPlayingInfoManager.changeMode()
        mUiStates.value = mCurrentPlay
    }

    val album: B?
        get() = mPlayingInfoManager.musicAlbum
    val albumMusics: List<M?>
        get() = mPlayingInfoManager.originPlayingList

    fun setChangingPlayingMusic(changingPlayingMusic: Boolean) {
        mIsChangingPlayingMusic = changingPlayingMusic
        if (mIsChangingPlayingMusic) {
            mCurrentPlay.setBaseInfo(mPlayingInfoManager.musicAlbum, currentPlayingMusic)
            mCurrentPlay.nowTime = "00:00"
            mCurrentPlay.allTime = "00:00"
            mCurrentPlay.progress = 0
            mCurrentPlay.duration = 0
            mUiStates.value = mCurrentPlay
        }
    }

    val albumIndex: Int
        get() = mPlayingInfoManager.albumIndex
    val repeatMode: Enum<PlayingInfoManager.RepeatMode>
        get() = mPlayingInfoManager.repeatMode

    fun togglePlay() {
        if (isPlaying) pauseAudio() else playAudio()
    }

    val currentPlayingMusic: M?
        get() = mPlayingInfoManager.currentPlayingMusic
    val uiStates: LiveData<MusicDTO<B, M, A>?>
        get() = mUiStates

    companion object {
        private val mHandler = Handler()
    }
}