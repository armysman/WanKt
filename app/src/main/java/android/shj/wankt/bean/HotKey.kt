package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */
data class HotKeyEntity(
    val `data`: List<HotKeyData>,
    val errorCode: Int,
    val errorMsg: String
)

data class HotKeyData(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)