package android.shj.wankt.ui.home

import android.shj.wankt.MyApplication
import android.shj.wankt.R
import android.shj.wankt.base.*
import android.shj.wankt.bean.ArticleDetail
import android.shj.wankt.databinding.RecyclerHomeArticleBinding
import android.shj.wankt.db.PreferencesHelper
import android.shj.wankt.http.RetrofitManager
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.Gson
import kotlinx.coroutines.*

/**********************************************************
 *  HomeRepository.java  2019-11-21
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class HomeRepository {

    suspend fun loadData(page: Int): List<ArticleDetail>? = withContext(Dispatchers.IO) {
        val result = RetrofitManager.apiService.homeArticles(page).data.datas
        if (page == 0) {
            PreferencesHelper.saveHomeArticleCache(MyApplication.instance, Gson().toJson(result))
        }
        result
    }

    suspend fun loadTops(): List<ArticleDetail>? = withContext(Dispatchers.IO) {
        RetrofitManager.apiService.topArticle(PreferencesHelper.fetchCookie(MyApplication.instance))
            .data
    }
}

class HomeDataRource(
    private val homeRepository: HomeRepository,
    private val handler: PagingThrowableHandler
) : PageKeyedDataSource<Int, ArticleDetail>(), CoroutineScope by MainScope() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleDetail>
    ) {
        safeLaunch({
            val result = ArrayList<ArticleDetail>()
            val top = homeRepository.loadTops()
            val data = homeRepository.loadData(0)
            result.addAll(top ?: arrayListOf())
            result.addAll(data ?: arrayListOf())
            callback.onResult(result, null, -1)
        }, {
            handler.invoke(PAGING_THROWABLE_LOAD_CODE_INITIAL, it)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleDetail>) {
        safeLaunch({
            val data = homeRepository.loadData(params.key)
            data?.let {
                callback.onResult(it, params.key + 1)
            }
        }, {
            handler.invoke(PAGING_THROWABLE_LOAD_CODE_AFTER, it)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleDetail>) {
        safeLaunch({
            val data = homeRepository.loadData(params.key)
            data?.let {
                callback.onResult(it, params.key - 1)
            }
        }, {
            handler.invoke(PAGING_THROWABLE_LOAD_CODE_BEFORE, it)
        })
    }

    override fun invalidate() {
        super.invalidate()
        cancel()
    }
}

class HomeArticleDataSourceFactory(
    private val homeRepository: HomeRepository,
    private val handler: PagingThrowableHandler
) : DataSource.Factory<Int, ArticleDetail>() {
    override fun create(): DataSource<Int, ArticleDetail> = HomeDataRource(homeRepository, handler)
}

class HomeArticleAdapter : BasePageListAdapter<ArticleDetail, RecyclerHomeArticleBinding>(
    DIFF_CALLBACK
) {
    override fun setVariable(
        data: ArticleDetail,
        position: Int,
        holder: BaseViewHolder<RecyclerHomeArticleBinding>
    ) {
        holder.binding.detail = data
        holder.binding.description = data.title
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.recycler_home_article

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleDetail>() {
            override fun areItemsTheSame(oldItem: ArticleDetail, newItem: ArticleDetail): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: ArticleDetail,
                newItem: ArticleDetail
            ): Boolean = oldItem == newItem
        }
    }
}