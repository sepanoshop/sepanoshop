package jj;

import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Milad.Jamalzadeh@yahoo.com (tell: 09132686053)
 */
public class jjCalendar_EN {
    
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
    private static String E_Month_Name[] = {"Junuary", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static String E_Weekdays[] = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    /**
     * this method get persian date by date of now
     */
    public jjCalendar_EN() {
        setDate(new java.util.Date());
    }

    /**
     * this method get persian date enter date (by int year, int month, int day)
     */
    public jjCalendar_EN(int year, int month, int day) {
        setDate(year, month, day);
    }

    /**
     * @param YYYYMMDD must be like "13900130" or "1390/01/30" After set you can
     * use getYear() and other get methods
     */
    public jjCalendar_EN(String YYYYMMDD) {
        setDate(YYYYMMDD);
    }
    
    private jjCalendar_EN(int dbDate) {
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
        return E_Month_Name[myPersianMonth];
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
        return new jjCalendar_EN(dbDate).getViewFormat_10length();
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
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        setDate(c.getTime());
    }
    
    public jjCalendar_IR convertGregorianToPersian() {
        wPersianCalendarClassHelper b = new wPersianCalendarClassHelper();
        b.setYear(getYear());
        b.setMonth(getMonth());
        b.setDay(getDay());
        b.convertGregorianToPersian();
//        Calendar c = Calendar.getInstance();
//        c.set(b.getYear(), b.getMonth() - 1, b.getDay());
//        setDate(b.getYear(), (b.getMonth() - 1), b.getDay());
        return new jjCalendar_IR(b.getYear(), b.getMonth() - 1, b.getDay());
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
    public long different(jjCalendar_EN dateEnd) {
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
    private void setDate(java.util.Date myDate) {
        Calendar date = Calendar.getInstance();
        date.setTime(myDate);
        myPersianYear = date.get(Calendar.YEAR);
        myPersianDay = date.get(Calendar.DAY_OF_MONTH);;
        myPersianMonth = date.get(Calendar.MONTH);
//        myPersianMonth = myDate.getMonth();
        myDayNameOfWeek = E_Weekdays[date.get(Calendar.DAY_OF_WEEK)];
        myHours = date.get(Calendar.HOUR_OF_DAY);
        myMinutes = date.get(Calendar.MINUTE);
        mySeconds = date.get(Calendar.SECOND);
        
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
        setDate(c.getTime());
    }
    
    public boolean isAfter(int year, int month, int day) {
        long different = different(new jjCalendar_EN(year, month, day));
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
    
    public String getTimeFormat_7length() {
        return getHours_2length() + ":" + getMinutes_2length() + ":" + getSeconds_2length();
    }
    
    @Override
    public String toString() {
        return getViewFormat_10length() + " - " + getTimeFormat_5length();
    }
}
