package com.tyj.craftshow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author create by kyle_2019 on 2019/5/17 17:20
 * @package com.tyj.craftshow.util
 * @fileName TimeUtil
 */
public class TimeUtil {
    /**
     * 获取当前时间 以 “2017-12-19 14:20:20” 形式字符串返回
     */
    public static final String TIME_PATTERN_H_M_S = "yyyy-MM-dd HH:mm:ss",TIME_PATTERN = "yyyy-MM-dd";

    /**
     * @param pattern 自己控制获取时间的格式
     * @return 时间字符串
     */
    public static String getCurrentTimeSeconds(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 根据时间字符串返回date对象
     *
     * @param date
     * @return
     */
    public static Date stringContertDate(String date) {
        SimpleDateFormat formatDate = new SimpleDateFormat(TIME_PATTERN_H_M_S, Locale.CHINA);
        try {
            return formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间date对象转换成字符串
     *
     * @param date
     * @return
     */
    public static String dateConvertString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN_H_M_S, Locale.CHINA);
        return formatter.format(date);
    }

    /**
     * 将时间mills转换成字符串
     *
     * @param mills
     * @return
     */
    public static String dateConvertString(Long mills) {
        return dateConvertString(new Date(mills));
    }
}
