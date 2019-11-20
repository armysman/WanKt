package android.shj.wankt.ui.main

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseFragment
import android.shj.wankt.base.BaseFragmentPageAdapter
import android.shj.wankt.databinding.FragmentMainBinding
import android.shj.wankt.databinding.HeaderMainBinding
import android.shj.wankt.db.PreferencesHelper
import android.shj.wankt.ui.home.HomeFragment
import android.shj.wankt.ui.project.ProjectFragment
import android.shj.wankt.ui.stage.StageFragment
import android.shj.wankt.ui.system.SystemFragment
import android.shj.wankt.ui.wxpublic.WxPublicFragment
import android.shj.wankt.utils.GalleryTransformer
import android.shj.wankt.utils.ScreenUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youth.banner.listener.OnBannerListener
import org.jetbrains.anko.toast

/**********************************************************
 *  MainFragment.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val madapter: BaseFragmentPageAdapter by lazy {
        BaseFragmentPageAdapter(
            childFragmentManager,
            arrayListOf(
                HomeFragment(),
                ProjectFragment(),
                SystemFragment(),
                StageFragment(),
                WxPublicFragment()
            )
        )
    }

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            MainModelFactory(MainRepository())
        ).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun iniFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.holder = this@MainFragment
        mBinding.viewMolde = mViewModel
        mBinding.adapter = madapter
        mBinding.limit = madapter.count
        mBinding.transformer = GalleryTransformer()
        mBinding.listener = OnBannerListener {

        }
        mBinding.banner.let {
            it.layoutParams = it.layoutParams.apply {
                width = ScreenUtils.getScreenWidth(requireContext())
                height = (width * 0.45f).toInt()
            }
        }

        val headerBindind = DataBindingUtil.inflate<HeaderMainBinding>(
            layoutInflater,
            R.layout.header_main, mBinding.navLeft, false
        )
        headerBindind.holder = this@MainFragment
        mBinding.navLeft.addHeaderView(headerBindind.root)

        mViewModel.hasLogin.observe(this, Observer<Boolean> {
            headerBindind.name = if (it) PreferencesHelper.fetchUserName(requireContext())
            else requireContext().getString(R.string.click_to_login)
        })

        mViewModel.coins.observe(this, Observer {
            it?.let { }
        })
        mViewModel.getBanners()

        setMenuClick()
    }

    private fun setMenuClick() {
        mBinding.navLeft.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favourite_article -> showMenu()

                R.id.favourite_website -> showMenu()

                R.id.share_list -> showMenu()

                R.id.todo_list -> showMenu()

                R.id.about -> showMenu()

                R.id.go_star -> showMenu()

                R.id.helper -> showMenu()

                R.id.login_out -> showMenu()
            }
            true
        }
    }

    private fun showMenu() {
        requireContext().toast("todo")
    }


    fun showHeaderDialog(view: View) {}

    fun headerLogin(view: View) {}

    fun userCoins(view: View) {}

}