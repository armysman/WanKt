package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */

data class UserCollectEntity(
    val `data`: UserCollectData,
    val errorCode: Int,
    val errorMsg: String
)

data class UserCollectData(
    val curPage: Int,
    val datas: List<UserCollectDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class UserCollectDetail(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)