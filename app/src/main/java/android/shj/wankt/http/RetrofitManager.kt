package android.shj.wankt.http

import android.shj.wankt.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**********************************************************
 *  RetrofitManager.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
object RetrofitManager {
    private const val BASE_URL = "https://www.wanandroid.com"

    val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOk3Http())
            .build().create(ApiService::class.java)
    }

    private fun getOk3Http(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    // 如果是 json 格式内容则打印 json
                    if ((message.startsWith("{") && message.endsWith("}")) ||
                        (message.startsWith("[") && message.endsWith("]"))
                    )
                        LogUtils.json(message)
                    else
                        LogUtils.verbose(message)
                }

            }
        )
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
            .readTimeout(10_000L, TimeUnit.MILLISECONDS)
            .writeTimeout(30_000L, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}