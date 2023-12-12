package com.dota2.music.tools.ui

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class ApplicationInstance private constructor(): ViewModelStoreOwner {

    private var mViewModelStore: ViewModelStore? = null

   companion object {
       val mInstance: ApplicationInstance = ApplicationInstance()
   }

    public fun getInstance() = mInstance

    override fun getViewModelStore(): ViewModelStore {
        if (null == mViewModelStore) {
            mViewModelStore = ViewModelStore()
        }

        return mViewModelStore as ViewModelStore
    }
}