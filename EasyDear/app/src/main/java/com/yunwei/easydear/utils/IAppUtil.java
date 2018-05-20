package com.yunwei.easydear.utils;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.function.mainFuncations.articleFunction.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/2/26.
 */

public class IAppUtil {

    public static List<ResolveInfo> getShareAppInfos() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        PackageManager pm = DataApplication.getInstance().getPackageManager();
        return pm.queryIntentActivities(intent, 0);
    }

    public static List<AppInfo> getArticleShareAppInfos(List<ResolveInfo> resolveInfos) {
        if (resolveInfos == null) {
            return null;
        }
        PackageManager pm = DataApplication.getInstance().getPackageManager();
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        for (ResolveInfo resolveInfo : resolveInfos) {
            String packageName = resolveInfo.activityInfo.packageName;
            if (packageName.equals("com.tencent.mm") && resolveInfo.loadLabel(pm).toString().contains("发送给")
                    || packageName.equals("com.tencent.mobileqq") && resolveInfo.loadLabel(pm).toString().contains("发送给")
                    || packageName.equals("com.qzone")
                    || packageName.equals("com.sina.weibo")) {
                AppInfo appInfo = new AppInfo();
                appInfo.setPackageName(resolveInfo.activityInfo.packageName);
                appInfo.setActivityName(resolveInfo.activityInfo.name);
                appInfo.setLoadLabel(resolveInfo.loadLabel(pm).toString());
                appInfo.setLoadIcon(resolveInfo.loadIcon(pm));
                appInfos.add(appInfo);
            }
        }
        return appInfos;
    }

}
