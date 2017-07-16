package com.gachaland.api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String NON_MILISEC_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.0";
    public static final String TIMEZONE_SEOUL = "Asia/Seoul";
    private static final Locale DEFAULT_LOCALE = Locale.KOREAN;

    /**
     * 현재 시간을 주어진 형식에 따른 스트링으로 반환
     *
     * @param format
     * @return
     */
    public static String getFormattedStringForNow(String format) {
        return getFormattedStringForNow(format, DEFAULT_LOCALE);
    }

    public static String getFormattedStringForNow(String format, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        Date date = new Date(System.currentTimeMillis());

        return sdf.format(date);
    }

    public static long getTimestampOnTimezone(LocalDateTime localDateTime) {
        return getTimestampOnTimezone(localDateTime, TIMEZONE_SEOUL);
    }

    public static long getTimestampOnTimezone(LocalDateTime localDateTime, String timezone) {
        return localDateTime.atZone(ZoneId.of(timezone)).toInstant().toEpochMilli();
    }

    /**
     * 현재 시간을 기본형식(DEFAULT_DATE_FORMAT)에 따른 스트링으로 반환
     *
     * @return
     */
    public static String getFormattedStringForNow() {
        return getFormattedStringForNow(DEFAULT_DATE_FORMAT);
    }

    /**
     * 현재 시간을 기본형식(DEFAULT_DATE_FORMAT)에 따른 UTC+0 시 스트링으로 반환
     *
     * @return
     */
    public static String getUTC0FormattedStringForNow() {
        SimpleDateFormat sdf = new SimpleDateFormat(NON_MILISEC_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }

    public static String getFormattedString(Date date, String format) {
        return getFormattedString(date, format, DEFAULT_LOCALE);
    }

    /**
     * Date > String with Locale
     *
     * @param date
     * @param format
     * @param locale
     * @return
     */
    public static String getFormattedString(Date date, String format, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(date);
    }

    /**
     * Date > String, 형식이 지정되지 않은 경우에 기본 형식(DEFAULT_DATE_FORMAT)으로 변환
     *
     * @param date
     * @return
     */
    public static String getFormattedString(Date date) {
        return getFormattedString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * timestamp > String
     *
     * @param timestamp
     * @return
     */
    public static String getFormattedString(long timestamp) {
        return getFormattedString(new Date(timestamp));
    }

    /**
     * String > Date
     *
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static Date getDateFromFormattedString(String dateStr, String dateFormat) {
        return getDateFromFormattedString(dateStr, dateFormat, DEFAULT_LOCALE);
    }

    /**
     * String > Date with Locale
     *
     * @param dateStr
     * @param dateFormat
     * @param locale
     * @return
     */
    public static Date getDateFromFormattedString(String dateStr, String dateFormat, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.warn("[날짜변환 오류] 현재의 날짜로 설정합니다.");
            date = new Date(System.currentTimeMillis());
        }

        return date;
    }

    /**
     * String > Date, 형식이 지정되지 않은 경우에 기본 형식(DEFAULT_DATE_FORMAT)으로 변환
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromFormattedString(String dateStr) {
        return getDateFromFormattedString(dateStr, DEFAULT_DATE_FORMAT);
    }

    public static boolean isEqualToFormat(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 날짜로부터 개별 시간 단위를 구한다.
     *
     * @param date
     * @param field
     * @return
     */
    public static int getTimeUnitFromDate(Date date, int field) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        return day.get(field);
    }

    /**
     * 현재 날짜에서 값만큼 연산된 date 리턴.
     */
    public static Date getAddDate(int cal) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, cal);
        return day.getTime();
    }

    /**
     * 주어진 날짜에서 값만큼 연산된 date 리턴.
     */
    public static Date getAddFromDate(Date date, int cal) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.add(Calendar.DATE, cal);
        return day.getTime();
    }

    /**
     * 주어진 날짜에서 값만큼 연산된 date 리턴. - timeUnit 지정
     *
     * @param date
     * @param timeUnit
     * @param cal
     * @return
     */
    public static Date getAddFromDateByUnit(Date date, int timeUnit, int cal) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.add(timeUnit, cal);
        return day.getTime();
    }

    /**
     * 주어진 날짜의 시간이하 필드를 모두 리셋하여 반환
     *
     * @param date
     * @return
     */
    public static Date getTimeSectionZeroedDate(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);

        return day.getTime();
    }

    /**
     * from과 to 사이의 날짜 차이 계산
     *
     * @param from
     * @param to
     * @param format
     * @return
     */
    public static int getDateInterval(String from, String to, String format) {
        Date fromDate = stringToDate(from, format);
        Date toDate = stringToDate(to, format);

        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * @param datestr
     * @param format
     * @return
     */
    public static Date stringToDate(String datestr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
            return sdf.parse(datestr, new ParsePosition(0));
        } catch (Exception ex) {
            throw new RuntimeException("Date format not valid.", ex);
        }
    }

    /**
     * @param datestr
     * @param format
     * @return
     */
    public static Date stringUTC0ToDate(String datestr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sdf.parse(datestr, new ParsePosition(0));
        } catch (Exception ex) {
            throw new RuntimeException("Date format not valid.", ex);
        }
    }

    /**
     * @param datestr
     * @return
     */
    public static Date stringToDate(String datestr) {
        return stringToDate(datestr, DEFAULT_DATE_FORMAT);
    }

    /**
     * @param datestr
     * @return
     */
    public static Date stringUTC0ToDate(String datestr) {
        return stringUTC0ToDate(datestr, DEFAULT_DATE_FORMAT);
    }

    /**
     * Date 문자열 형식 변경
     *
     * @param dateStr
     * @param orgFormat
     * @param targetFormat
     * @return
     */
    public static String convertDateFormat(String dateStr, String orgFormat, String targetFormat) {
        return convertDateFormat(dateStr, orgFormat, targetFormat, DEFAULT_LOCALE);
    }

    public static String convertDateFormat(String dateStr, String orgFormat, String targetFormat, Locale locale) {
        if (StringUtils.isEmpty(dateStr)) {
            return dateStr;
        }

        Date date = DateUtils.getDateFromFormattedString(dateStr, orgFormat, locale);

        return DateUtils.getFormattedString(date, targetFormat, locale);
    }

    /**
     * 현재 날짜를 ISO 8601 날짜 포맷으로 변경
     *
     * @return
     */
    public static String getISO8601FormattedStringForNow() {
        return getISO8601FormattedString(new Date());
    }

    /**
     * 조어진 날짜를 ISO 8601 날짜 포맷으로 변경
     *
     * @param date
     * @return
     */
    public static String getISO8601FormattedString(Date date) {
//		TimeZone tz = TimeZone.getTimeZone("UTC");
        TimeZone tz = TimeZone.getDefault();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        df.setTimeZone(tz);

        return df.format(date);
    }


    /**
     * 주어진 시각이 from ~ to 시 사이에 속하는지 여부를 조회
     *
     * @param date
     * @param from
     * @param to
     * @return
     */
    public static boolean belongToHourPeriod(Date date, int from, int to) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);

        int hours = day.get(Calendar.HOUR_OF_DAY);

        if (hours >= from && hours < to) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * db에 utc로 저장된 utcDate가 fromDay ~ 현재 이내에 속하는지 여부 확인
     * NOTE, utc timestamp 관련 참고 http://currentmillis.com/tutorials/system-currentTimeMillis.html
     *
     * @param utcDate
     * @param fromDay
     * @return
     */
    public static boolean belongToDayPeriod(Date utcDate, int fromDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(utcDate); // utc시 값을 타임존 고려 없이 지역 시간으로 변환한 값. 이 값은 seoul(GMT+9)의 경우 -9h인 상태이다.
        long offsetMiliSec = c.getTimeZone().getRawOffset(); // 현재 타임존의 milisec offset. seoul의 경우 +9h(+3240s 혹은 +3240000ms)
        long localEndAtMiliSec = c.getTimeInMillis() + offsetMiliSec; // offset만큼 지역시간으로 보정 해 준다. seoul의 경우 +9h 만큼을 더해준다.
        long localCurrentMiliSec = System.currentTimeMillis(); // 지역시간 기준 milisec
        return (long) (86400 * fromDay) >= ((localCurrentMiliSec - localEndAtMiliSec) / 1000L);
    }

    public static boolean isToday(Date date) {
        Calendar calendarToday = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendarToday.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR) && calendarToday.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
    }

    public static Date getFirstDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getFirstDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getFirstDayOfBeforeThreeMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getDayBeforeDaysFrom(int days, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getDayMinTimeBeforeDaysFrom(int days, Date date) {
        LocalDate beforeLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(days);
        LocalDateTime localDateTime = beforeLocalDate.atTime(LocalTime.MIN);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }

    public static Date getDayMaxTimeBeforeDaysFrom(int days, Date date) {
        LocalDate beforeLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(days);
        LocalDateTime localDateTime = beforeLocalDate.atTime(LocalTime.MAX);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date getTodayMidnight() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getTomorrowMidnight() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
