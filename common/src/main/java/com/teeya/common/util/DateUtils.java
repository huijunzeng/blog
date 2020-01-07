package com.teeya.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * 日期工具类
 */

public class DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DATESTAMP_PATTERN = "yyyyMMdd";

    public DateUtils() {
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat format = null;
        if (StringUtils.isBlank(dateStr)) {
            return null;
        } else {
            String _dateStr = dateStr.trim();

            try {
                if (_dateStr.matches("\\d{1,2}[A-Z]{3}")) {
                    _dateStr = _dateStr + (Calendar.getInstance().get(1) - 2000);
                }

                if (_dateStr.matches("\\d{1,2}[A-Z]{3}\\d{2}")) {
                    format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
                } else if (_dateStr.matches("\\d{1,2}[A-Z]{3}\\d{4}.*")) {
                    _dateStr = _dateStr.replaceAll("[^0-9A-Z]", "").concat("000000").substring(0, 15);
                    format = new SimpleDateFormat("ddMMMyyyyHHmmss", Locale.ENGLISH);
                } else {
                    StringBuffer sb = new StringBuffer(_dateStr);
                    String[] tempArr = _dateStr.split("\\s+");
                    tempArr = tempArr[0].split("-|\\/");
                    if (tempArr.length == 3) {
                        if (tempArr[1].length() == 1) {
                            sb.insert(5, "0");
                        }

                        if (tempArr[2].length() == 1) {
                            sb.insert(8, "0");
                        }
                    }

                    _dateStr = sb.append("000000").toString().replaceAll("[^0-9]", "").substring(0, 14);
                    if (_dateStr.matches("\\d{14}")) {
                        format = new SimpleDateFormat("yyyyMMddHHmmss");
                    }
                }

                Date date = format.parse(_dateStr);
                return date;
            } catch (Exception var5) {
                throw new RuntimeException("无法解析日期字符[" + dateStr + "]");
            }
        }
    }

    public static Date parseDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat format = null;
            if (StringUtils.isBlank(dateStr)) {
                return null;
            } else if (StringUtils.isNotBlank(pattern)) {
                format = new SimpleDateFormat(pattern);
                return format.parse(dateStr);
            } else {
                return parseDate(dateStr);
            }
        } catch (Exception var3) {
            throw new RuntimeException("无法解析日期字符[" + dateStr + "]");
        }
    }

    public static Date getDayBegin(Date date) {
        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
        return parseDate(format.concat(" 00:00:00"));
    }

    public static Date getDayEnd(Date date) {
        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
        return parseDate(format.concat(" 23:59:59"));
    }

    public static Date timestamp2Date(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String format2ddMMMyy(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
        return format.format(date).toUpperCase();
    }

    public static String format2ddMMMyy(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
        return format.format(parseDate(dateStr)).toUpperCase();
    }

    public static String formatDateStr(String dateStr, String... patterns) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        if (patterns != null && patterns.length > 0 && StringUtils.isNotBlank(patterns[0])) {
            pattern = patterns[0];
        }

        return DateFormatUtils.format(parseDate(dateStr), pattern);
    }

    public static String format(Date date, String... patterns) {
        if (date == null) {
            return "";
        } else {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            if (patterns != null && patterns.length > 0 && StringUtils.isNotBlank(patterns[0])) {
                pattern = patterns[0];
            }

            return DateFormatUtils.format(date, pattern);
        }
    }

    public static String format2DateStr(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static Date formatDate(Date orig, String... patterns) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        if (patterns != null && patterns.length > 0 && StringUtils.isNotBlank(patterns[0])) {
            pattern = patterns[0];
        }

        return parseDate(DateFormatUtils.format(orig, pattern));
    }

    public static long getDiffSeconds(Date d1, Date d2) {
        return Math.abs((d2.getTime() - d1.getTime()) / 1000L);
    }

    public static long getDiffMinutes(Date d1, Date d2) {
        long diffSeconds = getDiffSeconds(d1, d2);
        return diffSeconds / 60L;
    }

    public static long getDiffDay(Date d1, Date d2) {
        long between = Math.abs((d2.getTime() - d1.getTime()) / 1000L);
        long day = between / 60L / 60L / 24L;
        return (long)Math.floor((double)day);
    }

    public static long getDiffDay3(Date d1, Date d2) {
        long between = (d2.getTime() - d1.getTime()) / 1000L;
        long day = between / 60L / 60L / 24L;
        return (long)Math.floor((double)day);
    }

    public static int getDiffDay2(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(6);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(6);
        return day2 - day1;
    }

    public static Date firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(5);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.set(5, value);
        return cal.getTime();
    }

    public static Date addFirstDayOfMonth(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(1), cal.get(2) + amount, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(5);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(5, value);
        return cal.getTime();
    }

    public static Date addLastDayOfMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(1), calendar.get(2) + amount, 1);
        calendar.roll(5, -1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public static Date firstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return firstDayOfQuarter(calendar.get(1), getQuarterOfYear(date));
    }

    public static Date firstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(1);
        }

        if (month == null) {
            month = calendar.get(2);
        }

        calendar.set(year, month, 1);
        return getBeginDate(calendar.getTime());
    }

    public static Date firstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            month = 0;
        } else if (quarter == 2) {
            month = 3;
        } else if (quarter == 3) {
            month = 6;
        } else if (quarter == 4) {
            month = 9;
        } else {
            month = calendar.get(2);
        }

        return firstDayOfMonth(year, month);
    }

    public static Date firstDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            year = year - 1;
            month = 9;
        } else if (quarter == 2) {
            month = 0;
        } else if (quarter == 3) {
            month = 3;
        } else if (quarter == 4) {
            month = 6;
        } else {
            month = calendar.get(2);
        }

        return firstDayOfMonth(year, month);
    }

    public static Date lastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return lastDayOfQuarter(calendar.get(1), getQuarterOfYear(date));
    }

    public static Date lastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            month = 2;
        } else if (quarter == 2) {
            month = 5;
        } else if (quarter == 3) {
            month = 8;
        } else if (quarter == 4) {
            month = 11;
        } else {
            month = calendar.get(2);
        }

        return lastDayOfMonth(year, month);
    }

    public static Date lastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            year = year - 1;
            month = 11;
        } else if (quarter == 2) {
            month = 2;
        } else if (quarter == 3) {
            month = 5;
        } else if (quarter == 4) {
            month = 8;
        } else {
            month = calendar.get(2);
        }

        return lastDayOfMonth(year, month);
    }

    public static Date lastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(1);
        }

        if (month == null) {
            month = calendar.get(2);
        }

        calendar.set(year, month, 1);
        calendar.roll(5, -1);
        return getEndDate(calendar.getTime());
    }

    public static Date firstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(6);
        cal.set(11, 0);
        cal.set(5, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.set(6, value);
        return cal.getTime();
    }

    public static Date addFirstDayOfYear(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(1, getYear(date) + amount);
        int value = cal.getActualMinimum(6);
        cal.set(11, 0);
        cal.set(5, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.set(6, value);
        return cal.getTime();
    }

    public static Date lastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(6);
        cal.set(11, 23);
        cal.set(5, 0);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 0);
        cal.set(6, value);
        return cal.getTime();
    }

    public static Date addLastDayOfYear(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(1, getYear(date) + amount);
        int value = cal.getActualMaximum(6);
        cal.set(11, 23);
        cal.set(5, 0);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 0);
        cal.set(6, value - 1);
        return cal.getTime();
    }

    public static Date firstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(7);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.set(7, value);
        return cal.getTime();
    }

    public static int getDiffMonth(Date start, Date end) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        return (endCalendar.get(1) - startCalendar.get(1)) * 12 + endCalendar.get(2) - startCalendar.get(2);
    }

    public static String formatTimeConsumingInfo(long startPoint) {
        StringBuffer buff = new StringBuffer();
        long totalMilTimes = System.currentTimeMillis() - startPoint;
        int hour = (int)Math.floor((double)(totalMilTimes / 3600000L));
        int mi = (int)Math.floor((double)(totalMilTimes / 60000L));
        int se = (int)Math.floor((double)((totalMilTimes - (long)('\uea60' * mi)) / 1000L));
        if (hour > 0) {
            buff.append(hour).append("小时");
        }

        if (mi > 0) {
            buff.append(mi).append("分");
        }

        if (hour == 0) {
            buff.append(se).append("秒");
        }

        return buff.toString();
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return add(date, 2, amount);
    }

    public static Date addYear(Date date, int amount) {
        return add(date, 1, amount);
    }

    public static int getWeekNoForYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        cal.setTime(date);
        int weekno = cal.get(3);
        return weekno;
    }

    public static int getYear(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int year = cal.get(1);
        return year;
    }

    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) / 3 + 1;
    }

    public static int getMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int month = cal.get(2) + 1;
        return month;
    }

    public static int getDay(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int day = cal.get(5);
        return day;
    }

    public static int getWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int week = cal.get(7);
        return week;
    }

    public static int getHourOfDay(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int day = cal.get(11);
        return day;
    }

    public static int getMinuteOfDay(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int day = cal.get(12);
        return day;
    }

    public static int getHour(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }

        int day = cal.get(10);
        return day;
    }

    public static Date getFirstDateOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(7, firstDayOfWeek);
        return getBeginDate(cal.getTime());
    }

    public static Date getBeginDate(Date date1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(11, cal.getActualMinimum(11));
        cal.set(12, cal.getActualMinimum(12));
        cal.set(13, cal.getActualMinimum(13));
        cal.set(14, cal.getActualMinimum(14));
        return cal.getTime();
    }

    public static Date getEndDate(Date date1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(11, cal.getActualMaximum(11));
        cal.set(12, cal.getActualMaximum(12));
        cal.set(13, cal.getActualMaximum(13));
        cal.set(14, cal.getActualMaximum(14));
        return cal.getTime();
    }

    public static Date getBeforeDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, calendar.get(6) - days);
        return calendar.getTime();
    }

    public static int getAge(Date birthDay, Date currentDate) {
        if (birthDay == null) {
            birthDay = new Date();
        }

        Calendar birth;
        Calendar cal;
        int birthYear;
        int currentYear;
        int age;
        if (currentDate.after(birthDay)) {
            birth = Calendar.getInstance();
            birth.setTime(birthDay);
            cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.set(2, birth.get(2));
            cal.set(5, birth.get(5));
            birthYear = birth.get(1);
            currentYear = cal.get(1);
            age = currentYear - birthYear;
            if (currentDate.before(cal.getTime())) {
                --age;
            }

            return age;
        } else {
            birth = Calendar.getInstance();
            birth.setTime(birthDay);
            cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.set(2, birth.get(2));
            cal.set(5, birth.get(5));
            birthYear = birth.get(1);
            currentYear = cal.get(1);
            age = currentYear - birthYear;
            if (currentDate.after(cal.getTime())) {
                ++age;
            }

            return age;
        }
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(2);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(7, 2);
        return cal.getTime();
    }

    public static Date getEndDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(2);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(7, 1);
        return cal.getTime();
    }

    public static Date addFirstDateOfWeek(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        cal.setTime(date);
        cal.set(3, cal.get(3) + amount);
        cal.set(7, 2);
        return getBeginDate(cal.getTime());
    }

    public static Date addLastDateOfWeek(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        cal.setTime(date);
        cal.set(3, cal.get(3) + amount);
        cal.set(7, 1);
        return getEndDate(cal.getTime());
    }

    /** @deprecated */
    @Deprecated
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        return getFirstDayOfWeek(date);
    }

    /** @deprecated */
    @Deprecated
    public static Date getEndDayOfWeek(Date date, int firstDayOfWeek) {
        return getEndDayOfWeek(date);
    }

    public static Date getAfterDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, calendar.get(6) + days);
        return calendar.getTime();
    }

    public static String formatTemplateDate(Date date, String template) {
        String val = "";

        try {
            if (date == null) {
                date = new Date();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            val = MessageFormat.format(template, calendar.get(2) + 1, calendar.get(5), get_hh(calendar), get_mm(calendar));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return val;
    }

    public static String get_hh(Calendar calendar) {
        Integer hour = calendar.get(11);
        String hs = hour.toString();
        if (hour < 10) {
            hs = "0" + hs;
        }

        return hs;
    }

    public static String get_mm(Calendar calendar) {
        int minute = calendar.get(12);
        return minute < 10 ? "0" + minute : String.valueOf(minute);
    }

    public static Long getBirthdayByAgeParam(Integer age) {
        if (age == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(1, -age);
            return calendar.getTimeInMillis();
        }
    }

    public static Long getMaxBirthdayByAgeParam(Integer age) {
        if (age == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(1, -age - 1);
            c.add(6, 1);
            return c.getTimeInMillis();
        }
    }

    public static int calcDayOffset(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(6);
        int day2 = cal2.get(6);
        int year1 = cal1.get(1);
        int year2 = cal2.get(1);
        if (year1 == year2) {
            return day2 - day1;
        } else {
            int timeDistance = 0;

            for(int i = year1; i < year2; ++i) {
                if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                    timeDistance += 365;
                } else {
                    timeDistance += 366;
                }
            }

            return timeDistance + (day2 - day1);
        }
    }

    public static Integer countTwoDayWeek(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int dayOfWeek = cal.get(7);
        --dayOfWeek;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        int dayOffset = calcDayOffset(startDate, endDate);
        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = dayOffset % 7 + dayOfWeek > 7 ? 1 : 0;
        } else {
            a = dayOfWeek + dayOffset % 7 < 1 ? -1 : 0;
        }

        weekOffset += a;
        return weekOffset;
    }

    public static LocalDateTime uTimeLong2LocalDateTime(long timeLong) {
        return uDateToLocalDateTime(new Date(timeLong));
    }

    public static long uLocalDateTime2TimeLong(LocalDateTime localDateTime) {
        Long milliSecond = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return milliSecond;
    }

    public static LocalDateTime uDateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            //todo
            //throw new CustomizeException(BaseResultCodeEnum.DATE_PARAM_EMPTY);
            return LocalDateTime.now();
        } else {
            Instant instant = date.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            return localDateTime;
        }
    }

    public static Date uLocalDateToDate(LocalDate localDateTime) {
        if (Objects.isNull(localDateTime)) {
            //todo
            //throw new CustomizeException(BaseResultCodeEnum.DATE_PARAM_EMPTY);
            return new Date();
        } else {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atStartOfDay(zone).toInstant();
            Date date = Date.from(instant);
            return date;
        }
    }

    public static Date uLocalDateTimeToDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            //todo
            //throw new CustomizeException(BaseResultCodeEnum.DATE_PARAM_EMPTY);
            return new Date();
        } else {
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zone).toInstant();
            Date date = Date.from(instant);
            return date;
        }
    }

    public static Date getLastWeedStartDate() {
        LocalDate today = LocalDate.now();
        int weekValue = today.getDayOfWeek().getValue();
        Date begin = uLocalDateTimeToDate(LocalDateTime.of(today, LocalTime.of(0, 0, 0, 0)).minusWeeks(1L).minusDays((long)weekValue).plusDays(1L));
        return begin;
    }

    public static Date getLastWeedEndDate() {
        LocalDate today = LocalDate.now().minusDays(1L);
        int weekValue = today.getDayOfWeek().getValue();
        Date end = uLocalDateTimeToDate(LocalDateTime.of(today, LocalTime.of(23, 59, 59, 99999)).minusDays((long)weekValue));
        return end;
    }
}
