package android.shj.wankt.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseActivity
import android.shj.wankt.databinding.ActivityMainBinding
import android.shj.wankt.ui.main.MainFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var availabelCount = 0

    override fun getLayoutId(): Int = R.layout.activity_main
    private val manner: ConnectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    private val request: NetworkRequest by lazy {
        NetworkRequest.Builder().build()
    }

    private val netWorkCallResult: ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                availabelCount++
                checkState()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                availabelCount--
                checkState()
            }
        }
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        manner.registerNetworkCallback(request, netWorkCallResult)
    }

    private fun checkState() {
        mBinding.netAvailabel = availabelCount > 0
    }

    override fun onDestroy() {
        super.onDestroy()
        manner.unregisterNetworkCallback(netWorkCallResult)
    }

    override fun needTransparentStatus(): Boolean = true

    override fun onBackPressed() {
        supportFragmentManager.fragments.first()
            .childFragmentManager.fragments.last().let {
//            if (it is MainFragment) {
//                startActivity(Intent(Intent.ACTION_MAIN).apply {
//                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                    addCategory(Intent.CATEGORY_HOME)
//                })
//                return
//            }
        }
        super.onBackPressed()
    }

}
