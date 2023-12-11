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
package com.dota2.player.bean

import com.dota2.player.bean.base.BaseAlbumItem
import com.dota2.player.bean.base.BaseArtistItem
import com.dota2.player.bean.base.BaseMusicItem


/**
 * Create by IrocksCc at 23/12/11
 */
class DefaultAlbum(
    albumId: String?,
    title: String?,
    summary: String?,
    artist: DefaultArtist?,
    coverImg: String?,
    musics: List<DefaultMusic?>?
) : BaseAlbumItem<DefaultAlbum.DefaultMusic?, DefaultAlbum.DefaultArtist?>(
    albumId,
    title,
    summary,
    artist,
    coverImg,
    musics
) {
    class DefaultMusic(
        musicId: String?,
        coverImg: String?,
        url: String?,
        title: String?,
        artist: DefaultArtist?
    ) : BaseMusicItem<DefaultArtist?>(musicId, coverImg, url, title, artist)

    class DefaultArtist(name: String?) : BaseArtistItem(name)
}