package android.shj.wankt.ui.dialog

import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseDialogFragment
import android.shj.wankt.databinding.DialogRegisterBinding
import android.shj.wankt.ui.main.MainModelFactory
import android.shj.wankt.ui.main.MainRepository
import android.shj.wankt.ui.main.MainViewModel
import android.view.View
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.toast

/**********************************************************
 *  RegisterDialogFragment.java  2019-11-21
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class RegisterDialogFragment : BaseDialogFragment<DialogRegisterBinding>() {

    private val mMainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            MainModelFactory(MainRepository())
        ).get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.dialog_register

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.holder = this@RegisterDialogFragment
    }

    fun register(view: View) {
        val username = mBinding.userName.text.toString()
        val password = mBinding.password.text.toString()
        val repeatePassword = mBinding.repeatPassword.text.toString()
        if (username.isBlank() ||
            password.isBlank() ||
            repeatePassword.isBlank()
        ) {
            requireContext().toast("请输入完整")
        } else {
            mMainViewModel.register(username, password, repeatePassword, {
                requireContext().toast("注册成功")
                dialog?.dismiss()
            }, { message ->
                requireContext().toast(message)
                dialog?.dismiss()
            })
        }
    }

    fun close(view: View) {
        dialog?.dismiss()
    }
}