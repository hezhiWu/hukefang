package com.yunwei.easydear.utils;

import java.text.SimpleDateFormat;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.utils
 * @Description:日期时间工具类
 * @date 2016/8/11 8:45
 */
public class IDateTimeUtils {
    /**
     * 时间计数器，最多只能到99小时，如需要更大小时数需要改改方法
     *
     * @param time
     * @return
     */
    public static String showTimeCount(long time) {
        if (time >= 360000000) {
            return "00:00:00";
        }
        String timeCount = "";
        long hourc = time / 3600000;
        String hour = "0" + hourc;
        hour = hour.substring(hour.length() - 2, hour.length());

        long minuec = (time - hourc * 3600000) / (60000);
        String minue = "0" + minuec;
        minue = minue.substring(minue.length() - 2, minue.length());

        long secc = (time - hourc * 3600000 - minuec * 60000) / 1000;
        String sec = "0" + secc;
        sec = sec.substring(sec.length() - 2, sec.length());
        timeCount = hour + ":" + minue + ":" + sec;
        return timeCount;
    }

    /**
     * 时间戳格式化
     *
     * @param time
     * @param str
     * @return
     */
    public static String formatDate(long time, String str) {
        return new SimpleDateFormat(str).format(time);
    }

    /**
     * 获取当前时间
     *
     * @param str
     * @return
     */
    public static String getCurrentDate(String str) {
        return formatDate(System.currentTimeMillis(), str);
    }

    public static String getTimes(long time) {
        long second = time / 1000;
        long minute = second / 60;
        long hour = minute / 60;
        String h = hour < 10 ? ("0" + hour) : hour + "";
        String m = (minute - (hour * 60)) < 10 ? "0" + (minute - (hour * 60)) : (minute - (hour * 60)) + "";
        String s = (second - (minute * 60)) < 10 ? "0" + (second - (minute * 60)) : (second - (minute * 60)) + "";
        return h + "时" + m + "分" + s + "秒";
    }

    public static String getDateNow() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    public static String transformTimeFormat(String timeAdds) {
        String[] times=timeAdds.split(" ");
        return times[0].replace(":","/")+" "+times[1];
    }
}
