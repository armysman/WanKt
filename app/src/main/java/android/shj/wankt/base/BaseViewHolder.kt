package android.shj.wankt.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**********************************************************
 *  BaseViewHolder.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
open class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root)