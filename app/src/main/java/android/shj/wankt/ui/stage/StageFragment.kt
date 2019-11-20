package android.shj.wankt.ui.stage

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseFragment
import android.shj.wankt.databinding.FragmentStateBinding
import android.view.View

/**********************************************************
 *  StageFragment.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class StageFragment:BaseFragment<FragmentStateBinding>() {
    override fun iniFragment(view: View, savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_state
}