package android.shj.wankt.ui.main

import android.shj.wankt.MyApplication
import android.shj.wankt.base.safeLaunch
import android.shj.wankt.bean.BannerData
import android.shj.wankt.bean.CoinsData
import android.shj.wankt.db.PreferencesHelper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson

/**********************************************************
 *  MainViewModel.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val hasLogin = MutableLiveData<Boolean>()
    val banners = MutableLiveData<List<BannerData>>()
    val coins = MutableLiveData<CoinsData?>()

    init {
        hasLogin.value = PreferencesHelper.hasLogin(MyApplication.instance)
        banners.value = repository.getCachedBanners()
    }

    fun getBanners() {
        viewModelScope.safeLaunch({
            banners.value = repository.getHomeBanners().apply {
                PreferencesHelper.saveBannerCache(MyApplication.instance, Gson().toJson(this))
            }
        })
    }
}