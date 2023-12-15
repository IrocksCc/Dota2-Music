package com.dota2.music.tools.adapter

import android.content.Context
import androidx.databinding.ViewDataBinding

abstract class SongsItemAdapter<T, B: ViewDataBinding>(mContext: Context?): BaseAdapter<T, B>(mContext) {

    private var layoutId: Int = 0

    constructor(mContext: Context?, layoutId: Int) : this(mContext) {
        this.layoutId = layoutId
    }

    override fun getLayoutId(): Int {
        return this.layoutId
    }
}