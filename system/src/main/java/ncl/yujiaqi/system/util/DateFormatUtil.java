package ncl.yujiaqi.system.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Author yujiaqi
 * @Since 07/02/2025
 */
@Slf4j
public class DateFormatUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_CONVERT_ERROR_MESSAGE = "Date convert error：{}";
    private static final TimeZone TIME_ZONE = TimeZone.getDefault();

    public static Date dateFormat(String dateStr) {
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(DATE_CONVERT_ERROR_MESSAGE, e);
        }
    }

    public static Date dateTimeFormat(String dateTimeStr) {
        try {
            return new SimpleDateFormat(DATETIME_PATTERN).parse(dateTimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(DATE_CONVERT_ERROR_MESSAGE, e);
        }
    }

    public static Date dateNumFormat(String dateStr) {
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(DATE_CONVERT_ERROR_MESSAGE, e);
        }
    }

    public static Date dateNumFormat(int date, int month, int year) {
        Calendar calendar = Calendar.getInstance(TIME_ZONE, Locale.UK);
        calendar.set(year, month, date, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
