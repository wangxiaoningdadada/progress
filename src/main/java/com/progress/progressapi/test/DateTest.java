package com.progress.progressapi.test;

import java.util.Calendar;

/**
 * description: DateTest
 * date: 2023/8/31 9:12
 *
 * @author: wangxiaoning
 */
public class DateTest {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        //当前时间与晚上十二点的毫秒差
        Long timeOut = calendar.getTimeInMillis()-System.currentTimeMillis();
        //当前时间与晚上十二点的小时差
        Long hourTimeOut = (calendar.getTimeInMillis()-System.currentTimeMillis()) / (1000*60*60);

        System.out.println("当前时间与晚上十二点的毫秒差"+timeOut);
        System.out.println("当前时间与晚上十二点的小时差"+hourTimeOut);

        System.out.println("");
    }
}
