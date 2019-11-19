package android.shj.wankt.base

/**********************************************************
 *  PermissionListener.java  2019-11-19
 *  <p>
 *  描述
 *  </p>
 *  Copyright2019 by GNNT Company. All Rights Reserved.
 *
 *  @author:shuhj
 ***********************************************************/
interface PermissionListener {

    fun onGranted()

    fun onDenied(deniedPermission: List<String>)
}