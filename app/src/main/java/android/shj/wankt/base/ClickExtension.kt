package android.shj.wankt.base

import android.os.Handler
import android.view.MotionEvent
import android.view.View

/**********************************************************
 *  ClickExtension.java  2019-11-20
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
typealias OnSingleTap = () -> Unit

typealias OnDoubleTap = () -> Unit

private const val INTERVAL = 300L

class DoubleClickListener(
    private val onSingleTap: OnSingleTap?,
    private val onDoubleTap: OnDoubleTap?
) : View.OnTouchListener {
    private var count = 0
    private val handler = Handler()
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            count++
            handler.postDelayed({
                if (count == 1) {
                    onSingleTap?.invoke()
                } else if (count == 2) {
                    onDoubleTap?.invoke()
                    handler.removeCallbacksAndMessages(0)
                    count = 0
                }
            }, INTERVAL)
        }
        return false
    }
}