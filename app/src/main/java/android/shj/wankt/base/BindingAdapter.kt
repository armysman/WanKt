package android.shj.wankt.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.shj.wankt.R
import android.shj.wankt.bean.BannerData
import android.shj.wankt.view.ErrorReload
import android.shj.wankt.view.StatusError
import android.text.Html
import android.text.method.MovementMethod
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader

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

@BindingAdapter(value = ["bind:banners", "bind:bannerClick"], requireAll = false)
fun loadBannerImg(banner: Banner, banners: List<BannerData>?, listener: OnBannerListener) {
    if (banners.isNullOrEmpty()) return
    val imags = arrayListOf<String>()
    banners.forEach { imags.add(it.imagePath) }
    banner.setImages(imags)
        .setImageLoader(GlideLoader())
        .setBannerStyle(BannerConfig.RIGHT)
        .setDelayTime(5000).start()
    banner.setOnBannerListener(listener)
}

class GlideLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path)
            .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.image_place_holder))
            .into(imageView)
    }
}

@BindingAdapter("bind:gesture")
fun bindViewGesture(view: View, doubleClickListener: DoubleClickListener) {
    view.setOnTouchListener(doubleClickListener)
}

@BindingAdapter("bind:pageItemClick")
fun bindPagingItemClick(recyclerView: RecyclerView, listener: OnItemClickListener?) {
    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is BasePageListAdapter<*, *>) return
    adapter.onItemClickListener(listener)
}

@BindingAdapter("bind:pageItemLongClick")
fun bindPagingItemLongClick(recyclerView: RecyclerView, listener: OnItemLongClickListener?) {
    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is BasePageListAdapter<*, *>) return
    adapter.onItemLongClickListener(listener)
}

@BindingAdapter("bind:recyclerItemClick")
fun bindRecyclerItemClick(recyclerView: RecyclerView, listener: OnItemClickListener?) {
    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is BaseRecyclerAdapter<*>) return
    adapter.onItemClickListener(listener)
}

@BindingAdapter("bind:recyclerItemLongClick")
fun bindRecyclerItemLongClick(recyclerView: RecyclerView, listener: OnItemLongClickListener?) {
    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is BaseRecyclerAdapter<*>) return
    adapter.onItemLongClickListener(listener)
}

@BindingAdapter("bind:divider")
fun bindRecyclerDivider(recyclerView: RecyclerView, divider: RecyclerView.ItemDecoration) {
    recyclerView.addItemDecoration(divider)
}

/**
 * recyclerView 是否固定高度
 */
@BindingAdapter("bind:hasFixedSize")
fun bindRecyclerHasFixedSize(recyclerView: RecyclerView, hasFixedSize: Boolean) {
    recyclerView.setHasFixedSize(hasFixedSize)
}

@BindingAdapter(value = ["bind:scrollTo", "bind:offset"])
fun bindScrollTo(recyclerView: RecyclerView, position: Int, offset: Int) {
    recyclerView.layoutManager.let {
        when (it) {
            is LinearLayoutManager -> it.scrollToPositionWithOffset(position, offset)

            is GridLayoutManager -> it.scrollToPositionWithOffset(position, offset)

            is StaggeredGridLayoutManager -> it.scrollToPositionWithOffset(position, offset)
        }
    }
}


@BindingAdapter("bind:scrollListener")
fun bindRecyclerScrollListener(
    recyclerView: RecyclerView,
    listener: RecyclerView.OnScrollListener
) {
    recyclerView.addOnScrollListener(listener)
}

/**
 * 绑定 SwipeRefreshLayout 颜色，刷新状态，监听事件
 */
@BindingAdapter(
    value = ["bind:refreshColor", "bind:refreshState", "bind:refreshListener"],
    requireAll = false
)
fun bindRefreshColor(
    swipeRefreshLayout: SwipeRefreshLayout,
    color: Int,
    refreshState: Boolean,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    swipeRefreshLayout.setColorSchemeColors(color)
    swipeRefreshLayout.isRefreshing = refreshState
    swipeRefreshLayout.setOnRefreshListener(listener)
}


@BindingAdapter("bind:refreshEnable")
fun bindRefreshEnable(swipeRefreshLayout: SwipeRefreshLayout, enable: Boolean) {
    swipeRefreshLayout.isEnabled = enable
}

/**
 * 绑定 ViewPager 的一些属性
 */
@BindingAdapter("bind:limitOffset")
fun bindOffscreenPageLimit(viewPager: ViewPager, limit: Int) {
    viewPager.offscreenPageLimit = limit
}

@BindingAdapter(value = ["bind:reversed", "bind:transformer"], requireAll = false)
fun bindTransformer(
    viewPager: ViewPager,
    reversed: Boolean,
    transformer: ViewPager.PageTransformer
) {
    viewPager.setPageTransformer(reversed, transformer)
}

@BindingAdapter(value = ["bind:currentItem", "bind:smoothScroll"])
fun bindCurrentItem(viewPager: ViewPager, current: Int, smoothScroll: Boolean) {
    viewPager.setCurrentItem(current, smoothScroll)
}

/**
 * 绑定 EditText 一些属性
 */
@BindingAdapter("bind:editAction")
fun bindEditAction(editText: EditText, editorActionListener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(editorActionListener)
}

/**
 * 绑定 webview 的 url
 */
@BindingAdapter("bind:url")
fun bindWebUrl(webView: WebView, url: String?) {
    if (url.isNullOrBlank()) return
    webView.loadUrl(url)
}

/**
 * 绑定 TextView 的一些属性
 */
@BindingAdapter("bind:movementMethod")
fun bindMovementMethod(textView: TextView, method: MovementMethod) {
    textView.movementMethod = method
}

@Suppress("DEPRECATION")
@BindingAdapter("bind:renderHtml")
fun bindRenderHtml(textView: TextView, description: String) {
    textView.text = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    else Html.fromHtml(description)
}


@BindingAdapter("bind:reload")
fun bindReloadHandler(statusError: StatusError, handler: ErrorReload?) {
    statusError.errorReload = handler
}