package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */
data class CoinRank(
    val `data`: CoinRankData,
    val errorCode: Int,
    val errorMsg: String
)

data class CoinRankData(
    val curPage: Int,
    val datas: List<CoinRankDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class CoinRankDetail(
    val coinCount: Int,
    val level: Int,
    val rank: Int,
    val userId: Int,
    val username: String
)