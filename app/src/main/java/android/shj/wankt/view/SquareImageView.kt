package android.shj.wankt.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import kotlin.random.Random

/**********************************************************
 *  SquareImageView.java  2019-11-22
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
class SquareImageView(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    ImageView(context, attributeSet, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        var wid = measuredWidth
//        var hig = measuredHeight
//        if (wid > hig) wid = hig else hig = wid
//        setMeasuredDimension(wid, hig)

        val wSize = MeasureSpec.getSize(widthMeasureSpec)
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        var wid = 0
        when (wMode) {
            MeasureSpec.AT_MOST ->
                if (wSize < measuredWidth) {
                    wid = wSize
                } else {
                    wid = measuredWidth
                }
            MeasureSpec.EXACTLY ->
                wid = wSize
            MeasureSpec.UNSPECIFIED ->
                wid = measuredWidth
            else ->
                wid = measuredWidth
        }

        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        var hig = 0
        when (hMode) {
            MeasureSpec.AT_MOST ->
                if (hSize < measuredHeight) hig = hSize else hig = measuredHeight
            MeasureSpec.UNSPECIFIED -> hig = measuredHeight
            MeasureSpec.EXACTLY -> hig = hSize
            else -> hig = hSize
        }

        setMeasuredDimension(wid, hig)
    }
}