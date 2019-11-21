package android.shj.wankt.http

import android.shj.wankt.bean.HomeArticleEntity
import android.shj.wankt.bean.HomeBannerEntity
import android.shj.wankt.bean.TopArticleEntity
import android.shj.wankt.bean.WanUserEntity
import retrofit2.Response
import retrofit2.http.*

/**********************************************************
 *  ApiService.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
interface ApiService {
    @GET("/banner/json")
    suspend fun homeBanner(): HomeBannerEntity

    @POST("/user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Response<WanUserEntity>

    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<WanUserEntity>

    // 首页文章
    @GET("/article/list/{page}/json")
    suspend fun homeArticles(@Path("page") page: Int): HomeArticleEntity

    // 置顶文章
    @GET("/article/top/json")
    suspend fun topArticle(@Header("Cookie") cookie: String): TopArticleEntity
}