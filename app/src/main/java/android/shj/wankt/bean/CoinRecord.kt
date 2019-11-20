package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */
data class CoinRecord(
    val `data`: CoinrRecordData,
    val errorCode: Int,
    val errorMsg: String
)

data class CoinrRecordData(
    val curPage: Int,
    val datas: List<CoinRecordDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class CoinRecordDetail(
    val coinCount: Int,
    val date: Long,
    val desc: String,
    val id: Int,
    val reason: String,
    val type: Int,
    val userId: Int,
    val userName: String
)