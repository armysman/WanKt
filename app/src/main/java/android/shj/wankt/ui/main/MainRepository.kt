package android.shj.wankt.ui.main

import android.shj.wankt.MyApplication
import android.shj.wankt.bean.BannerData
import android.shj.wankt.db.PreferencesHelper
import android.shj.wankt.http.RetrofitManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**********************************************************
 *  MainRepository.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class MainRepository {

    fun getCachedBanners(): List<BannerData>? =
        Gson().fromJson(
            PreferencesHelper.fetchBannerCache(MyApplication.instance),
            object : TypeToken<List<BannerData>>() {}.type
        )

    //获取积分
    suspend fun getCoins() = withContext(Dispatchers.IO) {

    }

    suspend fun getHomeBanners() = withContext(Dispatchers.IO) {
        RetrofitManager.apiService.homeBanner().data
    }

    suspend fun register(username: String, password: String, repeatPassword: String) =
        withContext(Dispatchers.IO) {
            RetrofitManager.apiService.register(username, password, repeatPassword)
        }

    suspend fun login(username: String, password: String) = withContext(Dispatchers.IO) {
        RetrofitManager.apiService.login(username, password)
    }
}