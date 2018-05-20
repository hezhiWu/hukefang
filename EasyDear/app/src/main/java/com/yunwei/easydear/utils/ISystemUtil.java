package com.yunwei.easydear.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.utils
 * @Description:系统工具类
 * @date 2016/11/28 10:44
 */

public class ISystemUtil {

    /**
     * 获取版本名
     *
     * @return 当前应用的版本名
     */
    public static String getVersionName(Activity activity) {
        String versionName = "";
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Activity activity) {
        int versionCode = 0;
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 打电话
     * @param context
     * @param tel
     */
    public static void callTelAction(Context context, String tel){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tel));
        context.startActivity(intent);
    }
}
