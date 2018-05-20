package com.yunwei.easydear.common.eventbus;

/**
 * @Package: com.yunwei.zaina.common.eventbus
 * @Description:event消息常量類
 * @author: Aaron
 * @date: 2016-06-02
 * @Time: 11:31
 * @version: V1.0
 */
public class EventConstant {

    /**
     * 网络
     */
    public static final int NET_MOBILE_STATE=0x10;
    public static final int NET_WIFI_STATE=0x11;
    public static final int NET_UNAVAILABLE=0x12;

    /**
     * 足迹采集
     */
    public static final int TRACK_RECORD_STOP=0x20;

    public static final int EXIT_APP_VALUE=0x30;

    /**
     * 用户签到签退
     */
    public static final int USER_SIGN_REMAINDE=0x40;
    public static final int USER_SIGN_SUCCESS=0x4;

    public static final int NOTICE1=1;
    public static final int NOTICE2=2;
    public static final int NOTICE3=3;
    public static final int NOTICE4=4;
    public static final int NOTICE5=5;
    public static final int NOTICE6=6;
    public static final int NOTICE7=7;
    public static final int NOTICE8=8;
    public static final int NOTICE9=9;
    public static final int NOTICE10=10;
    public static final int NOTICE11=11;  /*获取位置所在城市后通知HomeFragment更新*/
    public static final int NOTICE12=12;  /*通知 ChildTabFragment 获取文章列表*/
    public static final int NOTICE_HOME_UPDATE_CITY = 13;  /*通知 HomeFragment 更新选择城市*/
    public static final int NOTICE_HOME_SEARCH = 14;  /*通知 HomeFragment 按关键字搜索*/

}
