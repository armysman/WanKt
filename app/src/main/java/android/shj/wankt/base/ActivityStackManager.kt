package android.shj.wankt.base

import android.app.Activity

/**********************************************************
 *  ActivityStackManager.java  2019-11-18
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
object ActivityStackManager {
    private val activitys = mutableListOf<Activity>()

    @JvmStatic
    fun addActivity(activity: Activity) = activitys.add(activity)

    @JvmStatic
    fun removeActivity(activity: Activity) {
        if (activitys.contains(activity)) {
            activitys.remove(activity)
            activity.finish()
        }
    }

    @JvmStatic
    fun getTopActivity(): Activity? =
        if (activitys.isEmpty()) null else activitys[activitys.size - 1]

    @JvmStatic
    fun finishAll() = activitys.filter { it.isFinishing }.forEach { it.finish() }
}