package android.shj.wankt.base

import android.view.View

/**********************************************************
 *  OnItemClickListener.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
interface OnItemLongClickListener {
    fun onItemLongClick(position: Int, view: View):Boolean
}