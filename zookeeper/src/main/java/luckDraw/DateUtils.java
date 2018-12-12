package luckDraw;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ZhouXiang on 2018/1/15 0015 9:23.
 */
public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    public static final SimpleDateFormat yyyyMMddsdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTodayDate());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Long nowDateTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Long todayRemainingSconds() {
        return (getTomorrowDate().getTime() - nowDateTimeMillis()) / 1000L;
    }
}
