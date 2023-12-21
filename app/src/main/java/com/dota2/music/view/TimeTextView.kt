package com.dota2.music.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.dota2.music.R
import com.dota2.music.tools.util.TimeUtil
import com.dota2.music.tools.util.Utils

class TimeTextView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    companion object {
        const val TEXT_SIZE = 12
    }

    private var text = "00:00"
    private var align = Paint.Align.LEFT
    var textColor = ContextCompat.getColor(this@TimeTextView.context, R.color.colorTextForeground)
        set(value) {
            textPaint.color = value
            invalidate()
            field = value
        }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        textSize = TEXT_SIZE.dp().toFloat()
        color = textColor
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textPaint.textAlign = align
        var x = 0f
        if (textPaint.textAlign == Paint.Align.RIGHT) {
            x = width.toFloat()
        }
        canvas?.drawText(text, x, TEXT_SIZE.dp().toFloat(), textPaint)
    }

    fun setText(newProcess: Int) {
        text = TimeUtil.parseDuration(newProcess)
        invalidate()
    }

    fun setAlignRight() {
        align = Paint.Align.RIGHT
    }

}

fun Int.dp(): Int {
    return dp2px(this.toFloat()).toInt()
}

fun dp2px(dp: Float): Float = dp * Utils.getApplication()?.applicationContext?.resources?.displayMetrics?.density!!
