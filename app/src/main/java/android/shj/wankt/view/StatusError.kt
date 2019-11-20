package android.shj.wankt.view

import android.content.Context
import android.shj.wankt.R
import android.util.AttributeSet
import androidx.core.content.ContextCompat


/**
 * @author kuky.
 * @description
 */

class StatusError @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CenterDrawableTextView(context, attrs, defStyleAttr) {

    var errorReload: ErrorReload? = null

    init {
        val errDrawable = ContextCompat.getDrawable(context, R.drawable.ic_empty)
        errDrawable?.setBounds(0, 0, errDrawable.minimumWidth, errDrawable.minimumHeight)
        setCompoundDrawables(null, errDrawable, null, null)
        text = resources.getString(R.string.reload_data)
        setTextColor(ContextCompat.getColor(context,R.color.half_black))
        setOnClickListener { errorReload?.reload() }
    }
}