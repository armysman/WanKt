package android.shj.wankt.bean

/**
 * @author kuky.
 * @description
 */

data class TopArticleEntity(
    val `data`: List<ArticleDetail>,
    val errorCode: Int,
    val errorMsg: String
)