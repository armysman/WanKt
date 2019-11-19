package android.shj.wankt.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.utils.ScreenUtils
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**********************************************************
 *  BaseDialogFragment.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
abstract class BaseDialogFragment<VB : ViewDataBinding> : DialogFragment(),
    CoroutineScope by MainScope() {
    protected lateinit var mBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Material_Dialog_Alert)
        dialog?.window?.let {
            it.requestFeature(Window.FEATURE_NO_TITLE)
            it.setWindowAnimations(R.style.DialogPushInOutAnimation)
        }
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        val attrs = dialog?.window?.attributes?.apply {
            width = (ScreenUtils.getScreenWidth(requireContext()) * 0.8f).toInt()
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
        }

        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(0))
            attributes = attrs
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.lifecycleOwner = this
        initFragment(view, savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    abstract fun initFragment(view: View, savedInstanceState: Bundle?)
}