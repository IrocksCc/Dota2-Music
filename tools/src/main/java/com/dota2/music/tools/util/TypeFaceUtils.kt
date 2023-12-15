package com.dota2.music.tools.util

import android.content.Context
import android.graphics.Typeface

class TypeFaceUtils {
    companion object {
        @JvmStatic
        fun getTypeFace(context: Context?, typeFace: String): Typeface {
            return Typeface.createFromAsset(context?.assets, typeFace)
        }
    }
}