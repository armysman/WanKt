package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */
data class WebsiteEntity(
    val `data`: List<WebsiteData>?,
    val errorCode: Int,
    val errorMsg: String
)

data class WebsiteData(
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)