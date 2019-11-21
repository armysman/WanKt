package android.shj.wankt.ui.home

import android.shj.wankt.base.PagingThrowableHandler
import android.shj.wankt.bean.ArticleDetail
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**********************************************************
 *  HomeViewModel.java  2019-11-21
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    var articles: LiveData<PagedList<ArticleDetail>>? = null

    fun getHomeArticle(hanler: PagingThrowableHandler) {
        articles = LivePagedListBuilder(
            HomeArticleDataSourceFactory(homeRepository, hanler),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()

    }
}