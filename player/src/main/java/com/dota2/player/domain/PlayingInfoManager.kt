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
package com.dota2.player.domain

import com.dota2.player.bean.base.BaseAlbumItem
import com.dota2.player.bean.base.BaseArtistItem
import com.dota2.player.bean.base.BaseMusicItem
import java.util.Collections

/**
 * Create by IrocksCc at 23/12/11
 */
class PlayingInfoManager<B : BaseAlbumItem<M, A>?, M : BaseMusicItem<A>?, A : BaseArtistItem?> {
    var musicAlbum: B? = null
        private set
    private var mPlayIndex = 0
    private var mAlbumIndex = 0
    private val mOriginPlayingList: MutableList<M?> = ArrayList()
    private val mShufflePlayingList: MutableList<M?> = ArrayList()
    var repeatMode: Enum<RepeatMode> = RepeatMode.LIST_CYCLE
        private set

    enum class RepeatMode {
        SINGLE_CYCLE, LIST_CYCLE, RANDOM
    }

    val isInit: Boolean
        get() = musicAlbum != null

    private fun fitShuffle() {
        mShufflePlayingList.clear()
        mShufflePlayingList.addAll(mOriginPlayingList)
        Collections.shuffle(mShufflePlayingList)
    }

    fun changeMode(): Enum<RepeatMode> {
        if (repeatMode === RepeatMode.LIST_CYCLE) {
            repeatMode = RepeatMode.SINGLE_CYCLE
        } else if (repeatMode === RepeatMode.SINGLE_CYCLE) {
            repeatMode = RepeatMode.RANDOM
        } else {
            repeatMode = RepeatMode.LIST_CYCLE
        }
        return repeatMode
    }

    fun setMusicAlbum(musicAlbum: B) {
        this.musicAlbum = musicAlbum
        mOriginPlayingList.clear()
        mOriginPlayingList.addAll(musicAlbum?.musics!!)
        fitShuffle()
    }

    val playingList: List<M?>
        get() = if (repeatMode === RepeatMode.RANDOM) {
            mShufflePlayingList
        } else {
            mOriginPlayingList
        }
    val originPlayingList: List<M?>
        get() = mOriginPlayingList
    val currentPlayingMusic: M?
        get() = if (playingList.isEmpty()) {
            null
        } else playingList[mPlayIndex]

    fun countPreviousIndex() {
        if (mPlayIndex == 0) {
            mPlayIndex = playingList.size - 1
        } else {
            --mPlayIndex
        }
        mAlbumIndex = mOriginPlayingList.indexOf(currentPlayingMusic)
    }

    fun countNextIndex() {
        if (mPlayIndex == playingList.size - 1) {
            mPlayIndex = 0
        } else {
            ++mPlayIndex
        }
        mAlbumIndex = mOriginPlayingList.indexOf(currentPlayingMusic)
    }

    var albumIndex: Int
        get() = mAlbumIndex
        set(albumIndex) {
            mAlbumIndex = albumIndex
            mPlayIndex = playingList.indexOf(mOriginPlayingList[mAlbumIndex])
        }
}