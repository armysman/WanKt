package android.shj.wankt.ui.main

import android.shj.wankt.MyApplication
import android.shj.wankt.base.safeLaunch
import android.shj.wankt.bean.BannerData
import android.shj.wankt.bean.CoinsData
import android.shj.wankt.bean.WanUserEntity
import android.shj.wankt.db.PreferencesHelper
import android.text.TextUtils
import android.util.SparseArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import retrofit2.Response
import java.lang.StringBuilder

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

    fun register(
        username: String,
        password: String,
        repeatePassword: String,
        success: () -> Unit,
        fail: (String) -> Unit
    ) {
        viewModelScope.safeLaunch({
            repository.register(username, password, repeatePassword).let {
                if (it.body()?.errorCode == 0) {
                    success()
                    //保存用户信息
                    saveUser(it)
                    hasLogin.value = true
                } else {
                    fail(it.body()?.errorMsg ?: "注册失败")
                    hasLogin.value = false
                }
            }
        }, { fail("注册出错，请检查网络") })
    }

    fun login(
        username: String,
        password: String,
        success: () -> Unit,
        fail: (String) -> Unit
    ) {
        viewModelScope.safeLaunch({
            repository.login(username, password).let {
                if (it.body()?.errorCode == 0) {
                    success()
                    //保存用户信息
                    saveUser(it)
                    hasLogin.value = true
                } else {
                    fail(it.body()?.errorMsg ?: "登录失败")
                    hasLogin.value = false
                }
            }
        }, { fail("登录失败，请检查网络") })
    }

    private fun saveUser(info: Response<WanUserEntity>) {
        if (info.body()?.errorCode == 0) {
            val cookies = StringBuilder()


            info.headers().filter {
                TextUtils.equals(it.first, "Set-Cookie")
            }.forEach {
                cookies.append(it.second).append(";")
            }

            val strCookie =
                if (cookies.endsWith(";")) cookies.substring(0, cookies.length - 1).toString()
                else cookies.toString()
            PreferencesHelper.saveCookie(MyApplication.instance, strCookie)
            PreferencesHelper.saveUserId(MyApplication.instance, info.body()?.data?.id ?: 0)
            PreferencesHelper.saveUserName(
                MyApplication.instance,
                info.body()?.data?.username ?: ""
            )

        }
    }
}