package android.shj.wankt.http

import android.shj.wankt.bean.HomeBannerEntity
import retrofit2.http.GET

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
    suspend fun homeBanner():HomeBannerEntity
}