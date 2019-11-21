package android.shj.wankt.ui.dialog

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseDialogFragment
import android.shj.wankt.databinding.DialogLoginBinding
import android.shj.wankt.ui.main.MainModelFactory
import android.shj.wankt.ui.main.MainRepository
import android.shj.wankt.ui.main.MainViewModel
import android.view.View
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.toast

/**********************************************************
 *  LoginDialogFragment.java  2019-11-21
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class LoginDialogFragment : BaseDialogFragment<DialogLoginBinding>() {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            MainModelFactory(MainRepository())
        ).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.dialog_login

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.holder = this@LoginDialogFragment
    }

    fun login(view: View) {
        val username = mBinding.userName.text.toString()
        val password = mBinding.password.text.toString()
        if (username.isBlank() || password.isBlank()) {
            requireContext().toast("请输入完整")
        } else {
            mViewModel.login(username, password, {
                requireContext().toast("登录成功")
                dialog?.dismiss()
            }, { message ->
                requireContext().toast(message)
                dialog?.dismiss()
            })
        }

    }

    fun register(view: View) {
        dialog?.dismiss()
        RegisterDialogFragment().show(requireActivity().supportFragmentManager, "register")
    }

    fun close(view: View) {
        dialog?.dismiss()
    }

}