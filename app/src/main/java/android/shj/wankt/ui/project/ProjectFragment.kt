package android.shj.wankt.ui.project

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseFragment
import android.shj.wankt.databinding.FragmentProjectBinding
import android.view.View

/**********************************************************
 *  ProjectFragment.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class ProjectFragment:BaseFragment<FragmentProjectBinding>() {
    override fun iniFragment(view: View, savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int = R.layout.fragment_project
}