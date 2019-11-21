package android.shj.wankt.base

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


/**********************************************************
 *  CoroutineLaunchExtension.java  2019-11-18
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
private fun coroutineExceptionHandler(
    throwableHandler: CoroutineThrowableHandler? = null
): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwableHandler?.invoke(throwable)
}

private fun coroutineExceptionContext(
    throwableHandler: CoroutineThrowableHandler = DEFAULT_HANDLER
): CoroutineContext = coroutineExceptionHandler(throwableHandler) + GlobalScope.coroutineContext

fun CoroutineScope.safeLaunch(
    block: suspend () -> Unit,
    throwableHandler: CoroutineThrowableHandler = DEFAULT_HANDLER
): Job = launch(coroutineExceptionContext(throwableHandler)) {
    block()
}

fun CoroutineScope.delayLaunch(
    timeMills: Long,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend () -> Unit
): Job = launch(context, start) {
    check(timeMills > 0) {
        "timeMills must be positive"
    }
    delay(timeMills)
    block()
}

fun CoroutineScope.repeatLaunch(
    interval: Long,
    count: Int = Int.MAX_VALUE,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend (Int) -> Unit
): Job = launch(context, start) {
    check(interval >= 0) { "interval must be positive" }
    check(count > 0) { "repeat count must larger than 0" }

    repeat(count) { index ->
        block(index)
        delay(interval)
    }
}


