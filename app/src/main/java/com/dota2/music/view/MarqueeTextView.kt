package com.dota2.music.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

// 跑马灯TextView，可以做到不获取焦点也能一直跑
class MarqueeTextView: AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    // 如果不重写，那么TextView在获得焦点再失去焦点后就会停止跑
    override fun isFocused(): Boolean {
        return true
    }
}