package com.dota2.music.tools.ui

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class ApplicationInstance private constructor(): ViewModelStoreOwner {

//    private override var mViewModelStore: ViewModelStore? = null

    override val viewModelStore: ViewModelStore by lazy { ViewModelStore() }

   companion object {
       val mInstance: ApplicationInstance = ApplicationInstance()
   }
}