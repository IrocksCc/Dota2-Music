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
package com.dota2.player

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import com.dota2.player.bean.DefaultAlbum
import com.dota2.player.contract.ICacheProxy
import com.dota2.player.contract.IPlayController
import com.dota2.player.contract.IServiceNotifier
import com.dota2.player.domain.MusicDTO
import com.dota2.player.domain.PlayingInfoManager
import com.kunminx.player.domain.PlayerController

/**
 * Create by IrocksCc at 23/12/11
 */
class DefaultPlayerManager private constructor() :
    IPlayController<DefaultAlbum?, DefaultAlbum.DefaultMusic?, DefaultAlbum.DefaultArtist?> {
    private val mController: PlayerController<DefaultAlbum?, DefaultAlbum.DefaultMusic?, DefaultAlbum.DefaultArtist?>

    init {
        mController = PlayerController<DefaultAlbum?, DefaultAlbum.DefaultMusic?, DefaultAlbum.DefaultArtist?>()
    }

    private var mIsInit: Boolean = false

    fun init(context: Context?) {
        if (!mIsInit) {
            init(context, null, null)
            mIsInit = true
        }
    }

    override fun init(context: Context?, iServiceNotifier: IServiceNotifier?, iCacheProxy: ICacheProxy?) {
        mController.init(context?.applicationContext, iServiceNotifier, iCacheProxy)
    }

    override fun loadAlbum(musicAlbum: DefaultAlbum?) {
        mController.loadAlbum(musicAlbum)
    }

    override fun loadAlbum(musicAlbum: DefaultAlbum?, playIndex: Int) {
        mController.loadAlbum(musicAlbum, playIndex)
    }

    override fun playAudio() {
        mController.playAudio()
    }

    override fun playAudio(albumIndex: Int) {
        mController.playAudio(albumIndex)
    }

    override fun playNext() {
        mController.playNext()
    }

    override fun playPrevious() {
        mController.playPrevious()
    }

    override fun playAgain() {
        mController.playAgain()
    }

    override fun pauseAudio() {
        mController.pauseAudio()
    }

    override fun resumeAudio() {
        mController.resumeAudio()
    }

    override fun clear() {
        mController.clear()
    }

    override fun changeMode() {
        mController.changeMode()
    }

    override val isPlaying: Boolean
        get() = mController.isPlaying
    override val isPaused: Boolean
        get() = mController.isPaused
    override val isInit: Boolean
        get() = mController.isInit

    override fun setSeek(progress: Int) {
        mController.setSeek(progress)
    }

    override fun getTrackTime(progress: Int): String {
        return mController.getTrackTime(progress)
    }

    override val uiStates: LiveData<MusicDTO<DefaultAlbum?, DefaultAlbum.DefaultMusic?, DefaultAlbum.DefaultArtist?>?>
        get() = mController.uiStates

    override fun getAlbum(): DefaultAlbum? {
        return mController.album
    }

    override fun getAlbumMusics(): List<DefaultAlbum.DefaultMusic?> {
        return mController.albumMusics
    }

    override fun setChangingPlayingMusic(changingPlayingMusic: Boolean) {
        mController.setChangingPlayingMusic(changingPlayingMusic)
    }

    override fun getAlbumIndex(): Int {
        return mController.albumIndex
    }

    override fun getRepeatMode(): Enum<PlayingInfoManager.RepeatMode> {
        return mController.repeatMode
    }

    override fun getCurrentPlayingMusic(): DefaultAlbum.DefaultMusic? {
        return mController.currentPlayingMusic
    }

    override fun togglePlay() {
        mController.togglePlay()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        val instance = DefaultPlayerManager()
    }
}