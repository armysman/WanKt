package android.shj.wankt

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**********************************************************
 *  MyApplication.java  2019-11-15
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Context
    }

}