package com.zxc.roomkotlin

import android.content.Context
import com.ansh.CoreApp

class GcApp : CoreApp() {

    override fun onCreate() {
        super.onCreate()
        gcApp = this
    }

    companion object {
        private var gcApp: GcApp? = null
        val appCtx: Context
            get() = gcApp!!.applicationContext
    }
}