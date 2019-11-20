package android.shj.wankt.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**********************************************************
 *  BaseRecyclerAdapter.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
abstract class BaseRecyclerAdapter<T>(var mData: MutableList<T>?) :
    RecyclerView.Adapter<BaseViewHolder<ViewDataBinding>>() {
    protected var mSelectPosition = -1
    private var itemClickListener: OnItemClickListener? = null
    private var itemLongClickListener: OnItemLongClickListener? = null

    fun onItemClickListener(listener: OnItemClickListener?) {
        this.itemClickListener = listener
    }

    fun onItemLongClickListener(listener: OnItemLongClickListener?) {
        this.itemLongClickListener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutId(viewType),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        val data = getItemData(position) ?: return
        setVariable(data, position, holder)
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener { v -> itemClickListener?.onItemClick(position, v) }
        holder.binding.root.setOnLongClickListener { v ->
            return@setOnLongClickListener itemLongClickListener?.onItemLongClick(position, v)
                ?: false
        }
    }

    fun updateSelectionPosition(position: Int) {
        this.mSelectPosition = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mData?.size ?: 0
    /**
     * 获取对应 position 下的数据
     * @param position
     */
    fun getItemData(position: Int): T? = mData!![position]

    /**
     * 根据 viewType 返回不同布局
     * @param viewType, 通过重写 getItemViewType 支持多布局
     */
    abstract fun getLayoutId(viewType: Int): Int

    /**
     * 与 dataBinding 互相绑定的数据操作
     * @param data 列表中当前 position 的数据
     * @param position 数据的位置
     * @param holder
     */
    abstract fun setVariable(data: T, position: Int, holder: BaseViewHolder<ViewDataBinding>)

    fun removeItem(position: Int) {
        if (position in 0 until itemCount)
            mData?.let {
                it.removeAt(position)
                notifyItemRemoved(position)
                if (position != itemCount) {
                    notifyItemRangeRemoved(position, itemCount - position)
                }
            }
    }

}