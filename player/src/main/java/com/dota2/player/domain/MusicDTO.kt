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
import java.io.Serializable

/**
 * Create by IrocksCc at 23/12/11
 */
class MusicDTO<B : BaseAlbumItem<M, A>?, M : BaseMusicItem<A>?, A : BaseArtistItem?> :
    Serializable {
    var title: String? = null
    var summary: String? = null
    var artist: A? = null
        private set
    var albumId: String? = null
    var musicId: String? = null
    var img: String? = null
    var nowTime = "00:00"
    var allTime = "00:00"
    var duration = 0
    var progress = 0
    var isPaused = true
    var repeatMode: Enum<PlayingInfoManager.RepeatMode> = PlayingInfoManager.RepeatMode.LIST_CYCLE
    fun setBaseInfo(musicAlbum: B?, music: M?) {
        title = music?.title
        summary = musicAlbum?.summary
        albumId = musicAlbum?.albumId
        musicId = music?.musicId
        img = music?.coverImg
        artist = music?.artist as A
    }

    fun setArtist(artist: A) {
        this.artist = artist
    }
}