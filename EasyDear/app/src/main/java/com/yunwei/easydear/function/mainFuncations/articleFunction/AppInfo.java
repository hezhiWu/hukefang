package com.yunwei.easydear.function.mainFuncations.articleFunction;

import android.graphics.drawable.Drawable;

/**
 * Created by LJH on 2017/2/26.
 */

public class AppInfo {
    private String packageName;
    private String activityName;
    private String loadLabel;
    private Drawable loadIcon;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getLoadLabel() {
        return loadLabel;
    }

    public void setLoadLabel(String loadLabel) {
        this.loadLabel = loadLabel;
    }

    public Drawable getLoadIcon() {
        return loadIcon;
    }

    public void setLoadIcon(Drawable loadIcon) {
        this.loadIcon = loadIcon;
    }
}
