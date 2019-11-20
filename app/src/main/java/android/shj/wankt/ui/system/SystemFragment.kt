package android.shj.wankt.ui.system

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseFragment
import android.shj.wankt.databinding.FragmentSystemBinding
import android.view.View

/**********************************************************
 *  SystemFragment.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class SystemFragment:BaseFragment<FragmentSystemBinding>() {
    override fun iniFragment(view: View, savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_system
}