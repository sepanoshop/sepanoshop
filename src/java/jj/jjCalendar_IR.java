package jj;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Milad.Jamalzadeh@yahoo.com (tell: 09132686053)
 */
public class jjCalendar_IR {

    private int myPersianYear;
    private int myPersianMonth;
    private int myPersianDay;
    private int myHours;
    private int myMinutes;
    private int mySeconds;
    private int myEnglishYear;
    private int myEnglishMonth;
    private int myEnglishDay;
    private String myDayNameOfWeek = "";
    private static String P_Month_Name[] = {"", "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
    private static String P_Weekdays[] = {"یک شنبه", "دو شنبه", "سه شنبه", "چهار شنبه", "پنج شنبه", "جمعه", "شنبه"};

    /**
     * this method get persian date by date of now
     */
    public jjCalendar_IR() {
        setConvertDateToPersian(new java.util.Date());
    }

    /**
     * this method get persian date enter date (by int year, int month, int day)
     */
    public jjCalendar_IR(int year, int month, int day) {
        setDate(year, month, day);
    }

    /**
     * @param YYYYMMDD must be like "13900130" or "1390/01/30" After set you can
     * use getYear() and other get methods
     */
    public jjCalendar_IR(String YYYYMMDD) {
        setDate(YYYYMMDD);
    }

    private jjCalendar_IR(int dbDate) {
        this(String.valueOf(dbDate));
    }

    public int getYear() {
        return myPersianYear;
    }

    /**
     *
     * @return int month from year
     */
    public int getMonth() {
        return myPersianMonth;
    }

    /**
     *
     * @return int day from month
     */
    public int getDay() {
        return myPersianDay;
    }

    public String getDayNameOfWeek() {
        JOptionPane.showMessageDialog(null, myDayNameOfWeek);
        return myDayNameOfWeek;
    }

    public String getMonthName() {
        return P_Month_Name[myPersianMonth];
    }

    public String getYear_4length() {
        if (myPersianYear > 999) {
            return "" + myPersianYear;
        } else {
            if (myPersianYear < 10) {
                return "000" + myPersianYear;
            } else if (myPersianYear < 100) {
                return "00" + myPersianYear;
            } else {
                return "0" + myPersianYear;
            }
        }
    }

    public String getMonth_2length() {
        return (getMonth() < 10 ? "0" + getMonth() : getMonth() + "");
    }

    public String getDay_2length() {
        return (getDay() < 10 ? "0" + getDay() : getDay() + "");
    }

    /**
     *
     * @return int like 13900125 by 8 character of now Time
     */
    public int getDBFormat_8length() {
        return Integer.parseInt(getYear_4length() + getMonth_2length() + getDay_2length());
    }

    /**
     *
     * @return String like 1389/02/30 by 10 character of now Time
     */
    public String getViewFormat_10length() {
        return getYear_4length() + "/" + getMonth_2length() + "/" + getDay_2length();
    }

    public static String getViewFormat_10length(int dbDate) {
        return new jjCalendar_IR(dbDate).getViewFormat_10length();
    }

    public static String getViewFormat(int date) {
        return getViewFormat(date + "");
    }

    public static String getViewFormat(Object date) {
        return getViewFormat(date.toString());
    }

    public static String getViewFormat(String date) {
        if (date.length() == 8) {
            return date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
        }
        return "";
    }

    public static String getViewFormatRtlLeft(String date) {
        if (date.length() == 8) {
            return date.substring(6, 8) + "/" + date.substring(4, 6) + "/" + date.substring(0, 4);
        }
        return "";
    }

    public static int getDatabaseFormat_8length(String Date, boolean isDefualtToday) {
        Date = Date == null ? "" : Date;
        String[] split = Date.split("/");
        if (split.length == 3) {
            if (split[0].length() == 3) {
                split[0] = "1" + split[0];
            } else if (split[0].length() == 2) {
                split[0] = "13" + split[0];
            }
            if (split[1].length() == 1) {
                split[1] = "0" + split[1];
            }
            if (split[2].length() == 1) {
                split[2] = "0" + split[2];
            }
            Date = split[0] + split[1] + split[2];
        }
        Date = Date.length() == 4 ? Date + "0000" : Date;
        Date = Date.replace("/", "").replace(".", "").replace(",", "").replace("\\", "");
        if (Date.length() == 8) {
            if (jjNumber.isDigit(Date)) {
                return Integer.parseInt(Date);
            }
        }
        if (!isDefualtToday) {
            return 10000000;
        }
        jjCalendar_IR ir = new jjCalendar_IR();
        return ir.getDBFormat_8length();
    }

    /**
     *
     * @param YYYYMMDD must be like "13900130" or "1390/01/30" and only number
     * After set you can use getYear() and other get methods
     */
    public boolean setDate(String YYYYMMDD) {
        if (YYYYMMDD == null) {
            return false;
        }
        YYYYMMDD = YYYYMMDD.replace("/", "");
        if (YYYYMMDD.length() == 8) {
            if (jjNumber.isDigit(YYYYMMDD)) {
                int year = Integer.parseInt(YYYYMMDD.substring(0, 4));
                int month = Integer.parseInt(YYYYMMDD.substring(4, 6));
                int day = Integer.parseInt(YYYYMMDD.substring(6, 8));
                setDate(year, month, day);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @param YYYYMMDD must be like "13900130" or "1390/01/30" and only number
     * After set you can use getYear() and other get methods
     */
    public boolean setDate(int YYYYMMDD) {
        return setDate(String.valueOf(YYYYMMDD));
    }

    /**
     *
     * @param month is between 1-12
     * @param day is between 1-31 After set you can use getYear() and other get
     * methods
     */
    public void setDate(int year, int month, int day) {
        wPersianCalendarClassHelper b = new wPersianCalendarClassHelper();
        b.setYear(year);
        b.setMonth(month);
        b.setDay(day);
        b.convertPersianToGregorian();
        Calendar c = Calendar.getInstance();
        c.set(b.getYear(), b.getMonth() - 1, b.getDay());
        setConvertDateToPersian(c.getTime());
    }

    public jjCalendar_EN convertPersianToGregorian() {
        wPersianCalendarClassHelper b = new wPersianCalendarClassHelper();
        b.setYear(getYear());
        b.setMonth(getMonth());
        b.setDay(getDay());
        b.convertPersianToGregorian();
//        Calendar c = Calendar.getInstance();
        jjCalendar_EN calendar_EN = new jjCalendar_EN(b.getYear(), b.getMonth() - 1, b.getDay());
//        c.set(b.getYear(), b.getMonth() - 1, b.getDay());
//        setDate(b.getYear(), (b.getMonth() - 1), b.getDay());
        return calendar_EN;
    }

    /**
     *
     * @param add must be - or + number
     */
    public void addDay(int add) {
        setAddsToDate(0, 0, add);
    }

    /**
     *
     * @param add must be - or + number
     */
    public void addMonth(int add) {
        int day = getDay();
        setDate(getYear(), getMonth() + add, getDay());
        if (day != getDay()) {
            setDate(getYear(), getMonth(), day);
        }
    }

    /**
     *
     * @param add must be - or + number
     */
    public void addYear(int add) {
        setAddsToDate(add, 0, 0);
    }

    /**
     *
     * @return true if year parameter is Kabise(Leap) year
     */
    public boolean isKabiseYear(int year) {
        return wPersianCalendarClassHelper.isKabiseYear(year);
    }

    /**
     *
     * @param dateEnd
     * @return long day number: (this - dateEnd)
     */
    public long different(jjCalendar_IR dateEnd) {
        wPersianCalendarClassHelper b = new wPersianCalendarClassHelper();
        b.setYear(getYear());
        b.setMonth(getMonth());
        b.setDay(getDay());
        b.convertPersianToGregorian();
        Calendar c = Calendar.getInstance();
        c.set(b.getYear(), b.getMonth() - 1, b.getDay());
        long first = c.getTimeInMillis();
        // 
        b.setYear(dateEnd.getYear());
        b.setMonth(dateEnd.getMonth());
        b.setDay(dateEnd.getDay());
        b.convertPersianToGregorian();
        c.set(b.getYear(), b.getMonth() - 1, b.getDay());
        long end = c.getTimeInMillis();
        double doubleDif = new Double(end - first) / (24 * 60 * 60 * 1000);
        if (String.valueOf(doubleDif).contains(".9")) {
            doubleDif++;
        }
        return (long) doubleDif;
    }

    /**
     *
     * @param date set for current date
     */
    private void setConvertDateToPersian(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        myEnglishYear = Integer.parseInt(sdf.format(date));
        sdf = new SimpleDateFormat("MM");
        myEnglishMonth = Integer.parseInt(sdf.format(date));
        sdf = new SimpleDateFormat("dd");
        myEnglishDay = Integer.parseInt(sdf.format(date));
        wPersianCalendarClassHelper c = new wPersianCalendarClassHelper();
        c.setYear(myEnglishYear);
        c.setDay(myEnglishDay);
        c.setMonth(myEnglishMonth);
        c.convertGregorianToPersian();
        myPersianYear = c.getYear();
        myPersianMonth = c.getMonth();
        myPersianDay = c.getDay();
        myDayNameOfWeek = P_Weekdays[date.getDay()];
        myHours = date.getHours();
        myMinutes = date.getMinutes();
        mySeconds = date.getSeconds();

    }

    /**
     *
     * @param year set for current year
     * @param month set for current month
     * @param day set for current day
     */
    private void setAddsToDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(myEnglishYear + year, myEnglishMonth - 1 + month, myEnglishDay + day);
        setConvertDateToPersian(c.getTime());
    }

    public boolean isAfter(int year, int month, int day) {
        long different = different(new jjCalendar_IR(year, month, day));
        if (different < 0) {
            return true;
        }
        return false;
    }

    public int getHours() {
        return myHours;
    }

    public int getMinutes() {
        return myMinutes;
    }

    public int getSeconds() {
        return mySeconds;
    }

    public String getHours_2length() {
        return myHours < 10 ? "0" + myHours : myHours + "";
    }

    public String getMinutes_2length() {
        return myMinutes < 10 ? "0" + myMinutes : myMinutes + "";
    }

    public String getSeconds_2length() {
        return mySeconds < 10 ? "0" + mySeconds : mySeconds + "";
    }

    public String getTimeFormat_5length() {
        return getHours_2length() + ":" + getMinutes_2length();
    }

    public String getTimeFormat_4length() {
        return jjNumber.getFormaterIntegerLength(Integer.parseInt(getHours_2length()), 2) + jjNumber.getFormaterIntegerLength(Integer.parseInt(getMinutes_2length()), 2);
    }

    public String getTimeFormat_8length() {
        return getHours_2length() + ":" + getMinutes_2length() + ":" + getSeconds_2length();
    }

    @Override
    public String toString() {
        return getViewFormat_10length() + " - " + getTimeFormat_5length();
    }
}
