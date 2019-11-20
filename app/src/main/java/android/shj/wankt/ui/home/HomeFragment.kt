package android.shj.wankt.ui.home

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseFragment
import android.shj.wankt.databinding.FragmentHomeBinding
import android.view.View

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
    override fun iniFragment(view: View, savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
}