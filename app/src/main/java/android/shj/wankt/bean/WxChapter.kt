package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */

data class WxChapterEntity(
    val `data`: MutableList<WxChapterData>,
    val errorCode: Int,
    val errorMsg: String
)

data class WxChapterData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)