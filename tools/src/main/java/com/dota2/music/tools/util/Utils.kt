package com.dota2.music.tools.util

import android.app.Application
import android.util.Log

class Utils {
    companion object {
        private var sApplication: Application? = null

        fun init(application: Application) {
            if (null == sApplication) {
                Log.e("LDX", "Utils init: ${application}");
                sApplication = application
            }
        }

        fun getApplication(): Application? {
            return sApplication
        }
    }
}