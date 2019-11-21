package android.shj.wankt.ui.home

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.*
import android.shj.wankt.bean.ArticleDetail
import android.shj.wankt.databinding.FragmentHomeBinding
import android.shj.wankt.view.ErrorReload
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.toast

/**********************************************************
 *  HomeFragment.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val adapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter()
    }
    private val mViewMode: HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), HomeViewModelFactory(HomeRepository())).get(
            HomeViewModel::class.java
        )
    }


    override fun getLayoutId(): Int = R.layout.fragment_home


    override fun iniFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.refreshColor = R.color.colorAccent
        mBinding.refreshListener = SwipeRefreshLayout.OnRefreshListener {
            getData()
        }
        mBinding.adapter = adapter
        mBinding.itemClick = OnItemClickListener { position, _ ->
            adapter.getItemData(position = position)?.let {

            }
        }

        mBinding.itemLongClick = OnItemLongClickListener { position, _ ->
            adapter.getItemData(position)?.let {

            }
            true
        }

        mBinding.gesture = DoubleClickListener(null, {
            mBinding.recyclerHome.scrollToTop()
        })

        mBinding.reload = ErrorReload {
            getData()
        }

        getData()
    }

    private fun getData() {
        mViewMode.getHomeArticle { code, _ ->
            when (code) {
                PAGING_THROWABLE_LOAD_CODE_INITIAL -> {
                    mBinding.errorStatus = true
                    mBinding.title = resources.getString(R.string.text_place_holder)
                }
                PAGING_THROWABLE_LOAD_CODE_AFTER -> requireContext().toast("加载更多数据出错啦~请检查网络")
            }
        }
        mBinding.refreshing = true
        mBinding.errorStatus = false
        mViewMode.articles?.observe(this, Observer<PagedList<ArticleDetail>> {
            adapter.submitList(it)
            mBinding.title = resources.getString(R.string.blog_articles)
            delayLaunch(1000) { mBinding.refreshing = false }
        })
    }

}