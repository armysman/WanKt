package android.shj.wankt.db

import android.content.Context
import android.shj.wankt.utils.SharePreferencesUtils

/**********************************************************
 *  PreferencesHelper.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
object PreferencesHelper {
    private const val USER_KEY_ID = "wan.user.id"
    private const val USER_KEY_NAME = "wan.user.name"
    private const val USER_KEY_COOKIE = "wan.user.cookie"
    private const val USER_KEY_COIN = "wan.user.coin"
    private const val CACHE_KEY_BANNER = "wan.cache.banner"
    private const val CACHE_KEY_HOME_ARTICLES = "wan.cache.home.articles"

    fun saveUserId(context: Context, id: Int) {
        SharePreferencesUtils.saveInteger(context, USER_KEY_ID, id)
    }
    fun hasLogin(context: Context) =
        SharePreferencesUtils.getInteger(context, USER_KEY_ID) > 0

    fun saveUserName(context: Context, name: String) =
        SharePreferencesUtils.saveString(context, USER_KEY_NAME, name)

    fun fetchUserName(context: Context) =
        SharePreferencesUtils.getString(context, USER_KEY_NAME)

    fun saveCookie(context: Context, cookie: String) =
        SharePreferencesUtils.saveString(context, USER_KEY_COOKIE, cookie)

    fun fetchCookie(context: Context) =
        SharePreferencesUtils.getString(context, USER_KEY_COOKIE)

    fun saveCoin(context: Context, coin: Int) =
        SharePreferencesUtils.saveInteger(context, USER_KEY_COIN, coin)

    fun fetchCoin(context: Context) =
        SharePreferencesUtils.getInteger(context, USER_KEY_COIN, 0)

    // =======================> LOCAL CACHES <=================================

    fun saveBannerCache(context: Context, bannerJson: String) =
        SharePreferencesUtils.saveString(context, CACHE_KEY_BANNER, bannerJson)

    fun fetchBannerCache(context: Context) =
        SharePreferencesUtils.getString(context, CACHE_KEY_BANNER)

    fun saveHomeArticleCache(context: Context, articleJson: String) =
        SharePreferencesUtils.saveString(context, CACHE_KEY_HOME_ARTICLES, articleJson)

    fun fetchHomeArticleCache(context: Context) =
        SharePreferencesUtils.getString(context, CACHE_KEY_HOME_ARTICLES)
}