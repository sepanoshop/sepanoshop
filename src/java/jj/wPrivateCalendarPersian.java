package jj;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 *
 * @author Milad.Jamalzadeh@yahoo.com (tell: 09132686053)
 */
public  class wPrivateCalendarPersian {

    //an attribute that specifies julian day
    private double julianDay = 0;
    private int year = 0;
    private int month = 1;
    private int day = 1;
    public static final Long MILLIS_IN_DAY = 86400000L;
    private JulianDayConvertor julianDayConvertor = new JulianDayConvertor();

    /**
     * This method converts date from Gregorian to Persian
     */
    public void convertGregorianToPersian() {
        //This part converts Gregorian date to Julian Day
        this.julianDay = julianDayConvertor.gregorian_to_jd(this.year, this.month, this.day);
        //This part converts Julian Day to Persian date
        int arrYear[] = julianDayConvertor.jd_to_persian(this.julianDay);
        this.year = arrYear[0];
        this.month = arrYear[1];
        this.day = arrYear[2];
    }

    /**
     * @return true if year parameter is Kabise(Leap) year
     */
    public static boolean isKabiseYear(int year) {
        JulianDayConvertor b = new JulianDayConvertor();
        return b.isLeap_gregorian(year);
    }

    /**
     * This method converts date from Persian to Gregorian
     */
    public void convertPersianToGregorian() {
        //This part converts Persian date to Julian Day
        this.julianDay = julianDayConvertor.persian_to_jd(this.year, this.month, this.day);

        //This part converts Julian Day to Gregorian date
        int arrYear[] = julianDayConvertor.jd_to_gregorian(this.julianDay);
        this.year = arrYear[0];
        this.month = arrYear[1];
        this.day = arrYear[2];
    }

    public static boolean isDigit(String forCheck) {
        char c[] = forCheck.toCharArray();
        if (c.length < 1) {
            return false;
        }
        for (int i = 0; i < c.length; i++) {
            if (!Character.isDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getJulianDay() {
        return this.julianDay;
    }

    public void setJulianDay(int julianDay) {
        this.julianDay = julianDay;
    }

    public void setPersianDate(String date) throws Exception {
        StringTokenizer st = new StringTokenizer(date, "/");
        if (st.countTokens() != 3) {
            throw new Exception("Wrong date format");
        }
        this.setDay(Integer.parseInt(st.nextToken()));
        this.setMonth(Integer.parseInt(st.nextToken()));
        this.setYear(Integer.parseInt(st.nextToken()));
        if (String.valueOf(this.year).length() != 4) {
            throw new Exception("Wrong date format");
        }
    }

    public static String convertToPersian(java.util.Date date) {

        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        String year = simpleDateformat.format(date);
        simpleDateformat = new SimpleDateFormat("MM");
        String month = simpleDateformat.format(date);
        simpleDateformat = new SimpleDateFormat("dd");
        String day = simpleDateformat.format(date);
        wPrivateCalendarPersian c = new wPrivateCalendarPersian();
        c.setDay(Integer.parseInt(day));
        c.setYear(Integer.parseInt(year));
        c.setMonth(Integer.parseInt(month));
        c.convertGregorianToPersian();
        return (c.getDay() + "/" + c.getMonth() + "/" + c.getYear());
    }

    public static java.util.Date convertToGregorian(int Year, int Month, int Day) throws Exception {

        wPrivateCalendarPersian c = new wPrivateCalendarPersian();
        c.setDay(Day);
        c.setMonth(Month);
        c.setYear(Year);
        if (String.valueOf(c.year).length() != 4) {
            throw new Exception("Wrong date format");
        }
        c.convertPersianToGregorian();
        int day = c.getDay();
        int month = c.getMonth();
        int year = c.getYear();
        String converDate = day + "/" + month + "/" + year;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(converDate);
    }

    public static java.util.Date convertToGregorian(String date) throws Exception {

        wPrivateCalendarPersian c = new wPrivateCalendarPersian();

        StringTokenizer st = new StringTokenizer(date, "/");
        if (st.countTokens() != 3) {
            throw new Exception("Wrong date format");
        }
        c.setDay(Integer.parseInt(st.nextToken()));
        c.setMonth(Integer.parseInt(st.nextToken()));
        c.setYear(Integer.parseInt(st.nextToken()));
        if (String.valueOf(c.year).length() != 4) {
            throw new Exception("Wrong date format");
        }

        c.convertPersianToGregorian();
        int day = c.getDay();
        int month = c.getMonth();
        int year = c.getYear();
        String converDate = day + "/" + month + "/" + year;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(converDate);
    }

    public static java.util.Date getPreviousWorkingDay(java.util.Date date) {
        if (date.toString().startsWith("Fri")) {
            date = new java.util.Date(date.getTime() - (MILLIS_IN_DAY * 2));
        }
        if (date.toString().startsWith("Thu")) {
            date = new java.util.Date(date.getTime() - MILLIS_IN_DAY);
        }
        return date;
    }

    public static String getTSETMCFormat(java.util.Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        String year = simpleDateformat.format(date);
        simpleDateformat = new SimpleDateFormat("MM");
        String month = simpleDateformat.format(date);
        simpleDateformat = new SimpleDateFormat("dd");
        String day = simpleDateformat.format(date);
        return year + month + day;

    }

    public static class JulianDayConvertor {

        private CalendarArithmetic calendarArithmetic = new CalendarArithmetic();
        private final double J0000 = 1721424.5;         // Julian date of Gregorian epoch: 0000-01-01
        private final double JMJD = 2400000.5;                // Epoch of Modified Julian Date system
        private final double GREGORIAN_EPOCH = 1721425.5;
        private final double PERSIAN_EPOCH = 1948320.5;

        /**
         * WEEKDAY_BEFORE  --  Return Julian date of given weekday (0 = Sunday)
         *                     in the seven days ending on jd.
         * @param weekday
         * @param jd
         * @return
         */
        private double weekday_before(int weekday, double jd) {
            return jd - calendarArithmetic.jwday(jd - weekday);
        }

        /**
         * SEARCH_WEEKDAY  --  Determine the Julian date for
         * @param weekday:  Day of week desired, 0 = Sunday
         * @param jd:  Julian date to begin search
         * @param direction:  1 = next weekday, -1 = last weekday
         * @param offset:  Offset from jd to begin search
         * @return
         */
        private double search_weekday(int weekday, double jd, int direction, double offset) {
            return weekday_before(weekday, jd + (direction * offset));
        }

        /**
         * Utility weekday functions, just wrappers for search_weekday
         * @param weekday
         * @param jd
         * @return
         */
        private double nearest_weekday(int weekday, double jd) {
            return search_weekday(weekday, jd, 1, 3);
        }

        private double next_weekday(int weekday, double jd) {
            return search_weekday(weekday, jd, 1, 7);
        }

        private double next_or_current_weekday(int weekday, double jd) {
            return search_weekday(weekday, jd, 1, 6);
        }

        private double previous_weekday(int weekday, double jd) {
            return search_weekday(weekday, jd, -1, 1);
        }

        private double previous_or_current_weekday(int weekday, double jd) {
            return search_weekday(weekday, jd, 1, 0);
        }

        /**
         * LEAP_GREGORIAN  --  Is a given year in the Gregorian calendar a leap year ?
         * @param year
         * @return
         */
        public boolean isLeap_gregorian(int year) {
            return ((year % 4) == 0) && (!(((year % 100) == 0) && ((year % 400) != 0)));
        }

        /**
         * LEAP_PERSIAN  --  Is a given year a leap year in the Persian calendar ?
         * @param year
         * @return
         */
        private boolean leap_persian(int year) {
            return ((((((year - ((year > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682;
        }

        /**
         * GREGORIAN_TO_JD  --  Determine Julian day number from Gregorian calendar date
         * @param year
         * @param month
         * @param day
         * @return
         */
        public double gregorian_to_jd(int year, int month, int day) {
            return (GREGORIAN_EPOCH - 1)
                    + (365 * (year - 1))
                    + Math.floor((year - 1) / 4)
                    + (-Math.floor((year - 1) / 100))
                    + Math.floor((year - 1) / 400)
                    + Math.floor((((367 * month) - 362) / 12)
                    + ((month <= 2) ? 0 : (isLeap_gregorian(year) ? -1 : -2))
                    + day);
        }

        /**
         * JD_TO_GREGORIAN  --  Calculate Gregorian calendar date from Julian day
         * @param jd
         * @return
         */
        public int[] jd_to_gregorian(double jd) {
            double wjd, depoch, quadricent, dqc, cent, dcent, quad, dquad,
                    yindex, dyindex, yearday, leapadj;
            int year, month, day;

            wjd = Math.floor(jd - 0.5) + 0.5;
            depoch = (wjd - GREGORIAN_EPOCH);
            quadricent = Math.floor(depoch / 146097);
            dqc = calendarArithmetic.mod(depoch, 146097);
            cent = Math.floor(dqc / 36524);
            dcent = calendarArithmetic.mod(dqc, 36524);
            quad = Math.floor(dcent / 1461);
            dquad = calendarArithmetic.mod(dcent, 1461);
            yindex = Math.floor(dquad / 365);
            year = (int) ((quadricent * 400) + (cent * 100) + (quad * 4) + yindex);
            if (!((cent == 4) || (yindex == 4))) {
                year++;
            }
            yearday = wjd - gregorian_to_jd(year, 1, 1);
            leapadj = ((wjd < gregorian_to_jd(year, 3, 1)) ? 0 : (isLeap_gregorian(year) ? 1 : 2));
            month = (int) Math.floor((((yearday + leapadj) * 12) + 373) / 367);
            day = (int) (wjd - gregorian_to_jd(year, month, 1)) + 1;

            int arrYear[] = {year, month, day};
            return arrYear;
        }

        /**
         * PERSIAN_TO_JD  --  Determine Julian day from Persian date
         * @param year
         * @param month
         * @param day
         * @return
         */
        public double persian_to_jd(int year, int month, int day) {
            int epbase, epyear;

            epbase = year - ((year >= 0) ? 474 : 473);
            epyear = 474 + calendarArithmetic.mod(epbase, 2820);

            return day
                    + ((month <= 7)
                    ? ((month - 1) * 31)
                    : (((month - 1) * 30) + 6))
                    + Math.floor(((epyear * 682) - 110) / 2816)
                    + (epyear - 1) * 365
                    + Math.floor(epbase / 2820) * 1029983
                    + (PERSIAN_EPOCH - 1);
        }

        /**
         * JD_TO_PERSIAN  --  Calculate Persian date from Julian day
         * @param jd
         * @return
         */
        public int[] jd_to_persian(double jd) {
            double depoch, aux1, aux2, yday;
            int year, month, day, cycle, cyear, ycycle;

            jd = Math.floor(jd) + 0.5;

            depoch = jd - persian_to_jd(475, 1, 1);
            cycle = (int) Math.floor(depoch / 1029983);
            cyear = calendarArithmetic.mod(depoch, 1029983);
            if (cyear == 1029982) {
                ycycle = 2820;
            } else {
                aux1 = Math.floor(cyear / 366);
                aux2 = calendarArithmetic.mod(cyear, 366);
                ycycle = (int) (Math.floor(((2134 * aux1) + (2816 * aux2) + 2815) / 1028522) + aux1 + 1);
            }
            year = ycycle + (2820 * cycle) + 474;
            if (year <= 0) {
                year--;
            }
            yday = (jd - persian_to_jd(year, 1, 1)) + 1;
            month = (int) ((yday <= 186) ? Math.ceil(yday / 31) : Math.ceil((yday - 6) / 30));
            day = (int) ((jd - persian_to_jd(year, month, 1)) + 1);
            int arrYear[] = {year, month, day};

            return arrYear;
        }
    }

    public static class CalendarArithmetic {

        /**
         * Frequently-used constants
         */
        public double J2000 = 2451545.0;              // Julian day of J2000 epoch
        public double JulianCentury = 36525.0;                // Days in Julian century
        public double JulianMillennium = (JulianCentury * 10);   // Days in Julian millennium
        public double AstronomicalUnit = 149597870.0;            // Astronomical unit in kilometres
        public double TropicalYear = 365.24219878;           // Mean solar tropical year
        /**
         * Days of week
         */
        public String Weekdays[] = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};
        /**
         * Laskar's tenth-degree polynomial fit
         */
        public double oterms[] = {-4680.93, -1.55, 1999.25, -51.38, -249.67, -39.05,
            7.12, 27.87, 5.79, 2.45};
        /**
         * Periodic terms for nutation in longiude (delta \Psi) and
         * obliquity (delta \Epsilon) as given in table 21.A of
         * Meeus, "Astronomical Algorithms", first edition.
         */
        public int nutArgMult[] = {
            0, 0, 0, 0, 1, -2, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2,
            0, 1, 0, 0, 0, 0, 0, 1, 0, 0, -2, 1, 0, 2, 2, 0, 0, 0, 2, 1,
            0, 0, 1, 2, 2, -2, -1, 0, 2, 2, -2, 0, 1, 0, 0, -2, 0, 0, 2, 1,
            0, 0, -1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 1, 0, 1, 2, 0, -1, 2, 2,
            0, 0, -1, 0, 1, 0, 0, 1, 2, 1, -2, 0, 2, 0, 0, 0, 0, -2, 2, 1,
            2, 0, 0, 2, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, -2, 0, 1, 2, 2,
            0, 0, 0, 2, 0, -2, 0, 0, 2, 0, 0, 0, -1, 2, 1, 0, 2, 0, 0, 0,
            2, 0, -1, 0, 1, -2, 2, 0, 2, 2, 0, 1, 0, 0, 1, -2, 0, 1, 0, 1,
            0, -1, 0, 0, 1, 0, 0, 2, -2, 0, 2, 0, -1, 2, 1, 2, 0, 1, 2, 2,
            0, 1, 0, 2, 2, -2, 1, 1, 0, 0, 0, -1, 0, 2, 2, 2, 0, 0, 2, 1,
            2, 0, 1, 0, 0, -2, 0, 2, 2, 2, -2, 0, 1, 2, 1, 2, 0, -2, 0, 1,
            2, 0, 0, 0, 1, 0, -1, 1, 0, 0, -2, -1, 0, 2, 1, -2, 0, 0, 0, 1,
            0, 0, 2, 2, 1, -2, 0, 2, 0, 1, -2, 1, 0, 2, 1, 0, 0, 1, -2, 0,
            -1, 0, 1, 0, 0, -2, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 2, 0,
            -1, -1, 1, 0, 0, 0, 1, 1, 0, 0, 0, -1, 1, 2, 2, 2, -1, -1, 2, 2,
            0, 0, -2, 2, 2, 0, 0, 3, 2, 2, 2, -1, 0, 2, 2
        };
        public int nutArgCoeff[] = {
            -171996, -1742, 92095, 89, /*  0,  0,  0,  0,  1 */
            -13187, -16, 5736, -31, /* -2,  0,  0,  2,  2 */
            -2274, -2, 977, -5, /*  0,  0,  0,  2,  2 */
            2062, 2, -895, 5, /*  0,  0,  0,  0,  2 */
            1426, -34, 54, -1, /*  0,  1,  0,  0,  0 */
            712, 1, -7, 0, /*  0,  0,  1,  0,  0 */
            -517, 12, 224, -6, /* -2,  1,  0,  2,  2 */
            -386, -4, 200, 0, /*  0,  0,  0,  2,  1 */
            -301, 0, 129, -1, /*  0,  0,  1,  2,  2 */
            217, -5, -95, 3, /* -2, -1,  0,  2,  2 */
            -158, 0, 0, 0, /* -2,  0,  1,  0,  0 */
            129, 1, -70, 0, /* -2,  0,  0,  2,  1 */
            123, 0, -53, 0, /*  0,  0, -1,  2,  2 */
            63, 0, 0, 0, /*  2,  0,  0,  0,  0 */
            63, 1, -33, 0, /*  0,  0,  1,  0,  1 */
            -59, 0, 26, 0, /*  2,  0, -1,  2,  2 */
            -58, -1, 32, 0, /*  0,  0, -1,  0,  1 */
            -51, 0, 27, 0, /*  0,  0,  1,  2,  1 */
            48, 0, 0, 0, /* -2,  0,  2,  0,  0 */
            46, 0, -24, 0, /*  0,  0, -2,  2,  1 */
            -38, 0, 16, 0, /*  2,  0,  0,  2,  2 */
            -31, 0, 13, 0, /*  0,  0,  2,  2,  2 */
            29, 0, 0, 0, /*  0,  0,  2,  0,  0 */
            29, 0, -12, 0, /* -2,  0,  1,  2,  2 */
            26, 0, 0, 0, /*  0,  0,  0,  2,  0 */
            -22, 0, 0, 0, /* -2,  0,  0,  2,  0 */
            21, 0, -10, 0, /*  0,  0, -1,  2,  1 */
            17, -1, 0, 0, /*  0,  2,  0,  0,  0 */
            16, 0, -8, 0, /*  2,  0, -1,  0,  1 */
            -16, 1, 7, 0, /* -2,  2,  0,  2,  2 */
            -15, 0, 9, 0, /*  0,  1,  0,  0,  1 */
            -13, 0, 7, 0, /* -2,  0,  1,  0,  1 */
            -12, 0, 6, 0, /*  0, -1,  0,  0,  1 */
            11, 0, 0, 0, /*  0,  0,  2, -2,  0 */
            -10, 0, 5, 0, /*  2,  0, -1,  2,  1 */
            -8, 0, 3, 0, /*  2,  0,  1,  2,  2 */
            7, 0, -3, 0, /*  0,  1,  0,  2,  2 */
            -7, 0, 0, 0, /* -2,  1,  1,  0,  0 */
            -7, 0, 3, 0, /*  0, -1,  0,  2,  2 */
            -7, 0, 3, 0, /*  2,  0,  0,  2,  1 */
            6, 0, 0, 0, /*  2,  0,  1,  0,  0 */
            6, 0, -3, 0, /* -2,  0,  2,  2,  2 */
            6, 0, -3, 0, /* -2,  0,  1,  2,  1 */
            -6, 0, 3, 0, /*  2,  0, -2,  0,  1 */
            -6, 0, 3, 0, /*  2,  0,  0,  0,  1 */
            5, 0, 0, 0, /*  0, -1,  1,  0,  0 */
            -5, 0, 3, 0, /* -2, -1,  0,  2,  1 */
            -5, 0, 3, 0, /* -2,  0,  0,  0,  1 */
            -5, 0, 3, 0, /*  0,  0,  2,  2,  1 */
            4, 0, 0, 0, /* -2,  0,  2,  0,  1 */
            4, 0, 0, 0, /* -2,  1,  0,  2,  1 */
            4, 0, 0, 0, /*  0,  0,  1, -2,  0 */
            -4, 0, 0, 0, /* -1,  0,  1,  0,  0 */
            -4, 0, 0, 0, /* -2,  1,  0,  0,  0 */
            -4, 0, 0, 0, /*  1,  0,  0,  0,  0 */
            3, 0, 0, 0, /*  0,  0,  1,  2,  0 */
            -3, 0, 0, 0, /* -1, -1,  1,  0,  0 */
            -3, 0, 0, 0, /*  0,  1,  1,  0,  0 */
            -3, 0, 0, 0, /*  0, -1,  1,  2,  2 */
            -3, 0, 0, 0, /*  2, -1, -1,  2,  2 */
            -3, 0, 0, 0, /*  0,  0, -2,  2,  2 */
            -3, 0, 0, 0, /*  0,  0,  3,  2,  2 */
            -3, 0, 0, 0 /*  2, -1,  0,  2,  2 */};
        /**
         * Table of observed Delta T values at the beginning of
         * even numbered years from 1620 through 2002.
         */
        public double deltaTtab[] = {
            121, 112, 103, 95, 88, 82, 77, 72, 68, 63, 60, 56, 53, 51, 48, 46,
            44, 42, 40, 38, 35, 33, 31, 29, 26, 24, 22, 20, 18, 16, 14, 12,
            11, 10, 9, 8, 7, 7, 7, 7, 7, 7, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10,
            10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13,
            13, 14, 14, 14, 14, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16,
            16, 16, 15, 15, 14, 13, 13.1, 12.5, 12.2, 12, 12, 12, 12, 12, 12,
            11.9, 11.6, 11, 10.2, 9.2, 8.2, 7.1, 6.2, 5.6, 5.4, 5.3, 5.4, 5.6,
            5.9, 6.2, 6.5, 6.8, 7.1, 7.3, 7.5, 7.6, 7.7, 7.3, 6.2, 5.2, 2.7,
            1.4, -1.2, -2.8, -3.8, -4.8, -5.5, -5.3, -5.6, -5.7, -5.9, -6,
            -6.3, -6.5, -6.2, -4.7, -2.8, -0.1, 2.6, 5.3, 7.7, 10.4, 13.3, 16,
            18.2, 20.2, 21.1, 22.4, 23.5, 23.8, 24.3, 24, 23.9, 23.9, 23.7,
            24, 24.3, 25.3, 26.2, 27.3, 28.2, 29.1, 30, 30.7, 31.4, 32.2,
            33.1, 34, 35, 36.5, 38.3, 40.2, 42.2, 44.5, 46.5, 48.5, 50.5,
            52.2, 53.8, 54.9, 55.8, 56.9, 58.3, 60, 61.6, 63, 65, 66.6
        };
        /**
         * Periodic terms to obtain true time
         */
        public double EquinoxpTerms[] = {
            485, 324.96, 1934.136,
            203, 337.23, 32964.467,
            199, 342.08, 20.186,
            182, 27.85, 445267.112,
            156, 73.14, 45036.886,
            136, 171.52, 22518.443,
            77, 222.54, 65928.934,
            74, 296.72, 3034.906,
            70, 243.58, 9037.513,
            58, 119.81, 33718.147,
            52, 297.17, 150.678,
            50, 21.02, 2281.226,
            45, 247.54, 29929.562,
            44, 325.15, 31555.956,
            29, 60.93, 4443.417,
            18, 155.12, 67555.328,
            17, 288.79, 4562.452,
            16, 198.04, 62894.029,
            14, 199.76, 31436.921,
            12, 95.39, 14577.848,
            12, 287.11, 31931.756,
            12, 320.81, 34777.259,
            9, 227.73, 1222.114,
            8, 15.45, 16859.074
        };
        public double JDE0tab1000[][] = {
            {1721139.29189, 365242.13740, 0.06134, 0.00111, -0.00071},
            {1721233.25401, 365241.72562, -0.05323, 0.00907, 0.00025},
            {1721325.70455, 365242.49558, -0.11677, -0.00297, 0.00074},
            {1721414.39987, 365242.88257, -0.00769, -0.00933, -0.00006}
        };
        public double JDE0tab2000[][] = {
            {2451623.80984, 365242.37404, 0.05169, -0.00411, -0.00057},
            {2451716.56767, 365241.62603, 0.00325, 0.00888, -0.00030},
            {2451810.21715, 365242.01767, -0.11575, 0.00337, 0.00078},
            {2451900.05952, 365242.74049, -0.06223, -0.00823, 0.00032}
        };

        /**
         * ASTOR  --  Arc-seconds to radians.
         * @param
         * @return
         */
        public double astor(double a) {
            return a * (Math.PI / (180.0 * 3600.0));
        }

        /**
         * DTR  --  Degrees to radians.
         * @param
         * @return
         */
        public double dtr(double d) {
            return (d * Math.PI) / 180.0;
        }

        /**
         * RTD  --  Radians to degrees.
         * @param
         * @return
         */
        public double rtd(double r) {
            return (r * 180.0) / Math.PI;
        }

        /**
         * FIXANGLE  --  Range reduce angle in degrees.
         * @param
         * @return
         */
        public double fixangle(double a) {
            return a - 360.0 * (Math.floor(a / 360.0));
        }

        /**
         * FIXANGR  --  Range reduce angle in radians.
         * @param
         */
        public double fixangr(double a) {
            return a - (2 * Math.PI) * (Math.floor(a / (2 * Math.PI)));
        }

        /**
         * DSIN  --  Sine of an angle in degrees
         * @param
         * @return
         */
        public double dsin(double d) {
            return Math.sin(dtr(d));
        }

        /**
         * DCOS  --  Cosine of an angle in degrees
         * @param d
         * @return
         */
        public double dcos(double d) {
            return Math.cos(dtr(d));
        }

        /**
         * MOD  --  Modulus function which works for non-integers.
         * @param a
         * @param b
         * @return
         */
        public int mod(double a, double b) {
            return (int) (a - (b * Math.floor(a / b)));
        }

        /**
         * AMOD  --  Modulus function which returns numerator if modulus is zero
         * @param a
         * @param b
         * @return
         */
        public double amod(double a, double b) {
            return mod(a - 1, b) + 1;
        }

        /**
         * Convert Julian time to hour, minutes, and seconds,
         * returned as a three-element array.
         * @param j
         * @return
         */
        public double[] jhms(double j) {
            double ij;

            j += 0.5;                 /* Astronomical to civil */
            ij = (j - Math.floor(j)) * 86400.0;
            double arrTime[] = {Math.floor(ij / 3600),
                Math.floor((ij / 60) % 60),
                Math.floor(ij % 60)};
            return arrTime;
        }

        /**
         * JWDAY  --  Calculate day of week from Julian day
         * @param j
         * @return
         */
        public int jwday(double j) {
            return (int) mod(Math.floor((j + 1.5)), 7);
        }

        /**
         * OBLIQEQ  --  Calculate the obliquity of the ecliptic for a given
         *           Julian date.  This uses Laskar's tenth-degree
         *           polynomial fit (J. Laskar, Astronomy and
         *           Astrophysics, Vol. 157, page 68 [1986]) which is
         *           accurate to within 0.01 arc second between AD 1000
         *           and AD 3000, and within a few seconds of arc for
         *           +/-10000 years around AD 2000.  If we're outside the
         *           range in which this fit is valid (deep time) we
         *           simply return the J2000 value of the obliquity, which
         *           happens to be almost precisely the mean.
         * @param jd
         * @return
         */
        public double obliqeq(double jd) {
            double eps, u, v;
            v = u = (jd - J2000) / (JulianCentury * 100);
            eps = 23 + (26 / 60.0) + (21.448 / 3600.0);
            if (Math.abs(u) < 1.0) {
                for (int i = 0; i < 10; i++) {
                    eps += (oterms[i] / 3600.0) * v;
                    v *= u;
                }
            }
            return eps;
        }

        /**
         * NUTATION  --  Calculate the nutation in longitude, deltaPsi, and
         *               obliquity, deltaEpsilon for a given Julian date
         *               jd.  Results are returned as a two element Array
         *               giving (deltaPsi, deltaEpsilon) in degrees.
         * @param jd
         * @return
         */
        public double[] nutation(double jd) {
            double deltaPsi, deltaEpsilon;
            double t = (jd - 2451545.0) / 36525.0, t2, t3, to10;
            double ta[] = new double[5];
            double dp = 0, de = 0, ang;
            t3 = t * (t2 = t * t);
            /* Calculate angles.  The correspondence between the elements
            of our array and the terms cited in Meeus are:
            ta[0] = D  ta[0] = M  ta[2] = M'  ta[3] = F  ta[4] = \Omega
             */
            ta[0] = dtr(297.850363 + 445267.11148 * t - 0.0019142 * t2 + t3 / 189474.0);
            ta[1] = dtr(357.52772 + 35999.05034 * t - 0.0001603 * t2 - t3 / 300000.0);
            ta[2] = dtr(134.96298 + 477198.867398 * t + 0.0086972 * t2 + t3 / 56250.0);
            ta[3] = dtr(93.27191 + 483202.017538 * t - 0.0036825 * t2 + t3 / 327270);
            ta[4] = dtr(125.04452 - 1934.136261 * t + 0.0020708 * t2 + t3 / 450000.0);

            /* Range reduce the angles in case the sine and cosine functions
            don't do it as accurately or quickly. */
            for (int i = 0; i < 5; i++) {
                ta[i] = fixangr(ta[i]);
            }

            to10 = t / 10.0;
            for (int i = 0; i < 63; i++) {
                ang = 0;
                for (int j = 0; j < 5; j++) {
                    if (nutArgMult[(i * 5) + j] != 0) {
                        ang += nutArgMult[(i * 5) + j] * ta[j];
                    }
                }
                dp += (nutArgCoeff[(i * 4) + 0] + nutArgCoeff[(i * 4) + 1] * to10) * Math.sin(ang);
                de += (nutArgCoeff[(i * 4) + 2] + nutArgCoeff[(i * 4) + 3] * to10) * Math.cos(ang);
            }

            /* Return the result, converting from ten thousandths of arc
            seconds to radians in the process. */
            deltaPsi = dp / (3600.0 * 10000.0);
            deltaEpsilon = de / (3600.0 * 10000.0);

            double arrDelta[] = {deltaPsi, deltaEpsilon};
            return arrDelta;
        }

        /**
         * ECLIPTOEQ  --  Convert celestial (ecliptical) longitude and
         *                latitude into right ascension (in degrees) and
         *                declination.  We must supply the time of the
         *                conversion in order to compensate correctly for the
         *                varying obliquity of the ecliptic over time.
         *                The right ascension and declination are returned
         *                as a two-element Array in that order.
         * @param jd
         * @param Lambda
         * @param Beta
         * @return
         */
        public double[] ecliptoeq(double jd, double Lambda, double Beta) {
            double eps, Ra, Dec;
            /* Obliquity of the ecliptic. */
            eps = dtr(obliqeq(jd));
            //What is the purpose of this?
            //String log = log + "Obliquity: " + rtd(eps) + "\n";
            Ra = rtd(Math.atan2((Math.cos(eps) * Math.sin(dtr(Lambda))
                    - (Math.tan(dtr(Beta)) * Math.sin(eps))),
                    Math.cos(dtr(Lambda))));
            //log += "RA = " + Ra + "\n";
            Ra = fixangle(rtd(Math.atan2((Math.cos(eps) * Math.sin(dtr(Lambda))
                    - (Math.tan(dtr(Beta)) * Math.sin(eps))),
                    Math.cos(dtr(Lambda)))));
            Dec = rtd(Math.asin((Math.sin(eps) * Math.sin(dtr(Lambda)) * Math.cos(dtr(Beta)))
                    + (Math.sin(dtr(Beta)) * Math.cos(eps))));
            double arrDegrees[] = {Ra, Dec};
            return arrDegrees;
        }

        /**
         * DELTAT  --  Determine the difference, in seconds, between
         *             Dynamical time and Universal time.
         * @param year
         * @return
         */
        public double deltat(int year) {
            int i;
            double dt, f, t;

            if ((year >= 1620) && (year <= 2000)) {
                i = (int) Math.floor((year - 1620) / 2);
                f = ((year - 1620) / 2) - i;  /* Fractional part of year */
                dt = deltaTtab[i] + ((deltaTtab[i + 1] - deltaTtab[i]) * f);
            } else {
                t = (year - 2000) / 100;
                if (year < 948) {
                    dt = 2177 + (497 * t) + (44.1 * t * t);
                } else {
                    dt = 102 + (102 * t) + (25.3 * t * t);
                    if ((year > 2000) && (year < 2100)) {
                        dt += 0.37 * (year - 2100);
                    }
                }
            }
            return dt;
        }

        /**
         * EQUINOX  --  Determine the Julian Ephemeris Day of an
         *              equinox or solstice.  The "which" argument
         *              selects the item to be computed:
         *                 0   March equinox
         *                 1   June solstice
         *                 2   September equinox
         *                 3   December solstice
         * @param year
         * @param which
         * @return
         */
        public double equinox(int year, int which) {
            double deltaL, JDE0, JDE, JDE0tab[][], S, T, W, Y;
            /*  Initialise terms for mean equinox and solstices.  We
            have two sets: one for years prior to 1000 and a second
            for subsequent years.  */
            if (year < 1000) {
                JDE0tab = JDE0tab1000;
                Y = year / 1000;
            } else {
                JDE0tab = JDE0tab2000;
                Y = (year - 2000) / 1000;
            }
            JDE0 = JDE0tab[which][0]
                    + (JDE0tab[which][1] * Y)
                    + (JDE0tab[which][2] * Y * Y)
                    + (JDE0tab[which][3] * Y * Y * Y)
                    + (JDE0tab[which][4] * Y * Y * Y * Y);
            T = (JDE0 - 2451545.0) / 36525;
            W = (35999.373 * T) - 2.47;
            deltaL = 1 + (0.0334 * dcos(W)) + (0.0007 * dcos(2 * W));
            //  Sum the periodic terms for time T
            S = 0;
            for (int i = 0, j = 0; i < 24; i++) {
                S += EquinoxpTerms[j] * dcos(EquinoxpTerms[j + 1] + (EquinoxpTerms[j + 2] * T));
                j += 3;
            }
            JDE = JDE0 + ((S * 0.00001) / deltaL);
            return JDE;
        }

        /**
         * SUNPOS  --  Position of the Sun.  Please see the comments
         *             on the return statement at the end of this function
         *             which describe the array it returns.  We return
         *             intermediate values because they are useful in a
         *             variety of other contexts.
         * @param jd
         * @return
         */
        public double[] sunpos(double jd) {
            double T, T2, L0, M, e, C, sunLong, sunAnomaly, sunR,
                    Omega, Lambda, epsilon, epsilon0, Alpha, Delta,
                    AlphaApp, DeltaApp;
            T = (jd - J2000) / JulianCentury;
            T2 = T * T;
            L0 = 280.46646 + (36000.76983 * T) + (0.0003032 * T2);
            L0 = fixangle(L0);
            M = 357.52911 + (35999.05029 * T) + (-0.0001537 * T2);
            M = fixangle(M);
            e = 0.016708634 + (-0.000042037 * T) + (-0.0000001267 * T2);
            C = ((1.914602 + (-0.004817 * T) + (-0.000014 * T2)) * dsin(M))
                    + ((0.019993 - (0.000101 * T)) * dsin(2 * M))
                    + (0.000289 * dsin(3 * M));
            sunLong = L0 + C;
            sunAnomaly = M + C;
            sunR = (1.000001018 * (1 - (e * e))) / (1 + (e * dcos(sunAnomaly)));
            Omega = 125.04 - (1934.136 * T);
            Lambda = sunLong + (-0.00569) + (-0.00478 * dsin(Omega));
            epsilon0 = obliqeq(jd);
            epsilon = epsilon0 + (0.00256 * dcos(Omega));
            Alpha = rtd(Math.atan2(dcos(epsilon0) * dsin(sunLong), dcos(sunLong)));
            Alpha = fixangle(Alpha);
            Delta = rtd(Math.asin(dsin(epsilon0) * dsin(sunLong)));
            AlphaApp = rtd(Math.atan2(dcos(epsilon) * dsin(Lambda), dcos(Lambda)));
            AlphaApp = fixangle(AlphaApp);
            DeltaApp = rtd(Math.asin(dsin(epsilon) * dsin(Lambda)));
            double arrQuantities[] = { //  Angular quantities are expressed in decimal degrees
                L0, //  [0] Geometric mean longitude of the Sun
                M, //  [1] Mean anomaly of the Sun
                e, //  [2] Eccentricity of the Earth's orbit
                C, //  [3] Sun's equation of the Centre
                sunLong, //  [4] Sun's true longitude
                sunAnomaly, //  [5] Sun's true anomaly
                sunR, //  [6] Sun's radius vector in AU
                Lambda, //  [7] Sun's apparent longitude at true equinox of the date
                Alpha, //  [8] Sun's true right ascension
                Delta, //  [9] Sun's true declination
                AlphaApp, // [10] Sun's apparent right ascension
                DeltaApp // [11] Sun's apparent declination
            };
            return arrQuantities;
        }

        /**
         * EQUATIONOFTIME  --  Compute equation of time for a given moment.
         *                     Returns the equation of time as a fraction of
         *                     a day.
         * @param jd
         * @return
         */
        public double equationOfTime(double jd) {
            double alpha, deltaPsi, E, epsilon, L0, tau;
            tau = (jd - J2000) / JulianMillennium;
            L0 = 280.4664567 + (360007.6982779 * tau)
                    + (0.03032028 * tau * tau)
                    + ((tau * tau * tau) / 49931)
                    + (-((tau * tau * tau * tau) / 15300))
                    + (-((tau * tau * tau * tau * tau) / 2000000));
            L0 = fixangle(L0);
            alpha = sunpos(jd)[10];
            deltaPsi = nutation(jd)[0];
            epsilon = obliqeq(jd) + nutation(jd)[1];
            E = L0 + (-0.0057183) + (-alpha) + (deltaPsi * dcos(epsilon));
            E = E - 20.0 * (Math.floor(E / 20.0));
            E = E / (24 * 60);
            return E;
        }
    }
}
