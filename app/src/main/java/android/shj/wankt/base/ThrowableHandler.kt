package android.shj.wankt.base

import android.util.Log

/**********************************************************
 *  ThrowableHandler.java  2019-11-18
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
const val PAGING_THROWABLE_LOAD_CODE_INITIAL = 0xFF01

const val PAGING_THROWABLE_LOAD_CODE_AFTER = 0xFF10

const val PAGING_THROWABLE_LOAD_CODE_BEFORE = 0xFF11
typealias PagingThrowableHandler = (Int, Throwable) -> Unit

typealias CoroutineThrowableHandler = (Throwable) -> Unit

val DEFAULT_HANDLER = object : CoroutineThrowableHandler {
    override fun invoke(p1: Throwable) {
        Log.e("TAG", "Throwable", p1)
    }
}