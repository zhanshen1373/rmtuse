package com.hayden.hap.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期和时间工具类
 * Created by liuyang on 2017/3/13.
 */

public class TimeUtil {
    public static Date fomatTime(String timeStr) {
        if (timeStr == null || timeStr.equals(""))
            return new Date();
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Calendar fomatCal(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date getNowDate() {
        return new Date();
    }
}
