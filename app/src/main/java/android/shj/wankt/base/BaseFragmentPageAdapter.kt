package android.shj.wankt.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**********************************************************
 *  BaseFragmentPageAdapter.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class BaseFragmentPageAdapter(
    fragmentManager: FragmentManager,
    fragments: ArrayList<out Fragment>,
    titles: Array<String>? = null
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mFragments = fragments
    private var mTitles = titles

    init {
        if (mTitles.isNullOrEmpty())
            mTitles = Array(fragments.size) { "" }
    }

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount(): Int = mFragments.size

    override fun getPageTitle(position: Int): CharSequence? =
        if (mTitles.isNullOrEmpty()) super.getPageTitle(position) else mTitles!![position]

}