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
package com.dota2.player.contract

import android.content.Context
import androidx.lifecycle.LiveData
import com.dota2.player.bean.base.BaseAlbumItem
import com.dota2.player.bean.base.BaseArtistItem
import com.dota2.player.bean.base.BaseMusicItem
import com.dota2.player.domain.MusicDTO

/**
 * Create by IrocksCc at 23/12/11
 */
interface IPlayController<B: BaseAlbumItem<M, A>?, M : BaseMusicItem<A>?, A : BaseArtistItem?> :
    IPlayInfoManager<B, M, A> {
    fun init(context: Context?, iServiceNotifier: IServiceNotifier?, iCacheProxy: ICacheProxy?)
    fun loadAlbum(musicAlbum: B)
    fun loadAlbum(musicAlbum: B, playIndex: Int)
    fun playAudio()
    fun playAudio(albumIndex: Int)
    fun playNext()
    fun playPrevious()
    fun playAgain()
    fun togglePlay()
    fun pauseAudio()
    fun resumeAudio()
    fun clear()
    fun changeMode()
    val isPlaying: Boolean
    val isPaused: Boolean
    val isInit: Boolean
    fun setSeek(progress: Int)
    fun getTrackTime(progress: Int): String?
    val uiStates: LiveData<MusicDTO<B, M, A>?>?
}