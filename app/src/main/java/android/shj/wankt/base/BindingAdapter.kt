package android.shj.wankt.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**********************************************************
 *  BindingAdapter.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
@BindingAdapter("bind:img")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .into(view)
}


@BindingAdapter(value = ["bind:imgUrl", "bind:placeHolder", "bind:error"], requireAll = false)
fun loadImageWithPlaceHolder(
    view: ImageView,
    url: String,
    placeholder: Drawable,
    errorHolder: Drawable
) {
    Glide.with(view.context)
        .load(url)
        .apply(
            RequestOptions.centerCropTransform()
                .placeholder(placeholder).error(errorHolder)
        )
        .into(view)
}

@BindingAdapter("bind:circleImg")
fun bindCircleImage(view: ImageView, drawable: Drawable) {
    Glide.with(view.context)
        .load(drawable)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(360)))
        .into(view)
}



