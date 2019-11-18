package android.shj.wankt.ui

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.shj.wankt.R
import android.shj.wankt.base.BaseActivity
import android.shj.wankt.databinding.ActivitySplashBinding
import com.airbnb.lottie.LottieAnimationView

/**********************************************************
 *  SplashActivity.java  2019-11-18
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initActivity(savedInstanceState: Bundle?) {
        lottieAnimationView = findViewById(R.id.splash)
        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        })
    }

}