package android.shj.wankt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import java.lang.RuntimeException

/**********************************************************
 *  BaseFragment.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), CoroutineScope by MainScope() {

    protected lateinit var mBinding: VB
    protected lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mNavController = NavHostFragment.findNavController(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.lifecycleOwner = this
        iniFragment(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        mBinding.unbind()
    }

    abstract fun iniFragment(view: View, savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int

    fun <T : ViewModel> getViewModel(clazz: Class<T>): T = ViewModelProvider(this).get(clazz)

    fun <T : ViewModel> getShareViewModel(clazz: Class<T>): T =
        ViewModelProvider(requireActivity()).get(clazz)

    fun onRunTimePermissionAsk(permissions: Array<String>, listener: PermissionListener) =
        if (activity != null && activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).onRuntimePermissionAsk(permissions, listener)
        } else {
            throw RuntimeException("attach activity is not [android.shj.wankt.base.BaseActivity],check it")
        }
}