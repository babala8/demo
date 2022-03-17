package com.example.demo.skill.apimethod.dateutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获取当前日期 yyyy-MM-dd
     * @return
     */
    public static Date getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);// 不解析非法日期字符串
        return getDateFormat(simpleDateFormat.format(new Date()));
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static Date getDateFormat(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);// 不解析非法日期字符串
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较日期大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("入参非法");
        }
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }


    public static void main(String[] args) {
        Date date = new Date();
        if (compareDate(getDateFormat("2022-03-09"), getNowDate()) > 0) {
            System.out.println("日期不能晚于当前日期," + date+","+getNowDate());
        }
    }
}
