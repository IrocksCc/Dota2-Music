package com.dota2.music.tools.adapter

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

class SongsItemAdapter<T, B: ViewDataBinding>(mContext: Context?,
                                                       diffCallback: DiffUtil.ItemCallback<T>
): BaseAdapter<T, B>(mContext, diffCallback) {

    private var layoutId: Int = 0

    constructor(mContext: Context?, layoutId: Int, diffCallback: DiffUtil.ItemCallback<T>) : this(mContext, diffCallback) {
        this.layoutId = layoutId
    }

    override fun onBindItem(binding: B?, item: T?, holder: BaseViewHolder) {

    }

    override fun getLayoutId(): Int {
        return this.layoutId
    }
}