package android.shj.wankt.base

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**********************************************************
 *  BaseActivity.java  2019-11-15
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(),
    CoroutineScope by MainScope() {

    private var mPermissionListener: PermissionListener? = null

    protected val mBinding: VB by lazy {
        DataBindingUtil.setContentView(this, getLayoutId()) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStackManager.addActivity(this)
        if (needTransparentStatus()) transparentStatusBar()
        mBinding.lifecycleOwner = this
        initActivity(savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    protected open fun needTransparentStatus(): Boolean = false

    abstract fun initActivity(savedInstanceState: Bundle?)

    open fun transparentStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
    }

    fun <T : ViewModel> getViewModel(clazz: Class<T>): T = ViewModelProvider(this).get(clazz)

    fun onRuntimePermissionAsk(permissions: Array<String>, listener: PermissionListener) {
        this.mPermissionListener = listener
        val activity = ActivityStackManager.getTopActivity()
        val deniedPermissions: MutableList<String> = mutableListOf()

        permissions
            .filterNot {
                ContextCompat.checkSelfPermission(
                    activity!!,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }
            .forEach { deniedPermissions.add(it) }

        if (deniedPermissions.isEmpty())
            mPermissionListener!!.onGranted()
        else
            ActivityCompat.requestPermissions(activity!!, deniedPermissions.toTypedArray(), 1)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            val deniedPermissions: MutableList<String> = mutableListOf()
            if (grantResults.isNotEmpty()) {
                for (i in grantResults.indices) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                        deniedPermissions.add(permissions[i])
                }
                if (deniedPermissions.isEmpty()) {
                    mPermissionListener!!.onGranted()
                } else {
                    mPermissionListener!!.onDenied(deniedPermissions)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityStackManager.removeActivity(this)
        cancel()
        mBinding.unbind()
    }
}