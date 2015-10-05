/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author Milad
 */
public class jjNumber {

//+=-%*/
//1234567890
    /**
     *
     * get Hex from number
     */
    public static String getHex(int no) {
        return "0x" + Integer.toHexString(no);
    }

    /**
     *
     * get Octal from number
     */
    public static String getOctal(int no) {
        return Integer.toOctalString(no);
    }

    /**
     *
     * get Char from number
     */
    public static String getChar(int no) {
        return ("" + (char) (no));
    }

    public static String getRandom(int length) {
        double d = Math.random();
        String s = String.valueOf(d);
        int index = s.indexOf(".");
        return s.substring(index + 1, index + 1 + length);
    }

    /**
     *
     * get Binary from number
     */
    public static String getBinary(int number) {
        String Result = new String();
        int Reminder;
        do {
            Reminder = number % 2;
            Result = Result.concat(Integer.toString(Reminder));
            number = (int) (number / 2);
        } while (number > 0);
        StringBuilder Temp = new StringBuilder(Result);
        Temp = Temp.reverse();
        Result = Temp.toString();
        return Result;
    }

    /**
     *
     * check and turn true if String is digit
     */
    public static boolean isDigit(String forCheck) {
        if (forCheck.length() < 1) {
            return false;
        }
        char c[] = forCheck.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (!Character.isDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOdd(int forCheck) {
        return forCheck % 2 != 0;
    }

    /**
     *
     * check and turn true if String is digit
     */
    public static boolean isDigitDouble(String forCheck) {
        if (forCheck.length() < 1) {
            return false;
        }
        char c[] = forCheck.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if ((!Character.isDigit(c[i])) && (c[i] != '.')) {
                return false;
            }
        }
        if (forCheck.equals(".")) {
            return false;
        }
        return true;
    }

    /**
     *
     * get number and give you name of number
     */
    public static String getNameOfNumber(long number) {
        if (number < 10) {
            return getNameOfNumber_lessThan_10((int) number);
        } else if (number < 20) {
            return getNameOfNumber_lessThan_20((int) number);
        } else if (number < 100) {
            return getNameOfNumber_lessThan_100((int) number);
        } else if (number < 1000) {
            return getNameOfNumber_lessThan_1000((int) number);
        } else if (number < 1000000) {
            return getNameOfNumber_lessThan_1_Hezar((int) number);
        } else if (number < 1000000000l) {
            return getNameOfNumber_lessThan_1_Miliun(number);
        } else if (number < 1000000000000l) {
            return getNameOfNumber_lessThan_1_Milyard(number);
        }
        return "";
    }

    private static String getNameOfNumber_lessThan_10(int number) {
        switch (number) {
            case 0:
                return "صفر";
            case 1:
                return "یک";
            case 2:
                return "دو";
            case 3:
                return "سه";
            case 4:
                return "چهار";
            case 5:
                return "پنج";
            case 6:
                return "شش";
            case 7:
                return "هفت";
            case 8:
                return "هشت";
            case 9:
                return "نه";
        }
        return "";
    }

    private static String getNameOfNumber_lessThan_20(int number) {
        switch (number) {
            case 10:
                return "ده";
            case 11:
                return "یازده";
            case 12:
                return "دوازده";
            case 13:
                return "سیزده";
            case 14:
                return "چهارده";
            case 15:
                return "پانزده";
            case 16:
                return "شانزده";
            case 17:
                return "هفده";
            case 18:
                return "هجده";
            case 19:
                return "نانزده";
        }
        return "";
    }

    private static String getNameOfNumber_lessThan_100(int number) {
        switch (number) {
            case 20:
                return "بیست";
            case 30:
                return "سی";
            case 40:
                return "چهل";
            case 50:
                return "پنجاه";
            case 60:
                return "شصد";
            case 70:
                return "هفتاد";
            case 80:
                return "هشتاد";
            case 90:
                return "نود";
        }
        if (String.valueOf(number).startsWith("2")) {
            return "بیست و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("3")) {
            return "سی و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("4")) {
            return "چهل و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("5")) {
            return "پنجاه و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("6")) {
            return "شصد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("7")) {
            return "هفتاد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("8")) {
            return "هشتاد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("9")) {
            return "نود و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        }
        return "";
    }

    private static String getNameOfNumber_lessThan_1000(int number) {
        switch (number) {
            case 100:
                return "یک صد";
            case 200:
                return "دویست";
            case 300:
                return "سی صد";
            case 400:
                return "چهار صد";
            case 500:
                return "پانصد";
            case 600:
                return "شش صد";
            case 700:
                return "هفت صد";
            case 800:
                return "هشت صد";
            case 900:
                return "نه صد";
        }
        if (String.valueOf(number).startsWith("1")) {
            return "یک صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("2")) {
            return "دویست و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("3")) {
            return "سی صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("4")) {
            return "چهار صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("5")) {
            return "پانصد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("6")) {
            return "شش صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("7")) {
            return "هفت صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("8")) {
            return "هشت صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        } else if (String.valueOf(number).startsWith("9")) {
            return "نه صد و " + getNameOfNumber(Integer.parseInt(String.valueOf(number).substring(1)));
        }
        return "";
    }

    private static String getNameOfNumber_lessThan_1_Hezar(int number) {
        switch (number) {
            case 1000:
                return "هزار";
            case 2000:
                return "دو هزار";
            case 3000:
                return "سه هزار";
            case 4000:
                return "چهار هزار";
            case 5000:
                return "پنج هزار";
            case 6000:
                return "شش هزار";
            case 7000:
                return "هفت هزار";
            case 8000:
                return "هشت هزار";
            case 9000:
                return "نه هزار";
        }
        String s = String.valueOf(number);
        String s2 = getNameOfNumber(Long.parseLong(s.substring(0, s.length() - 3)));
        String s3 = getNameOfNumber(Long.parseLong(s.substring(s.length() - 3, s.length())));
        String s4 = s2 + " هزار و " + (s3.equalsIgnoreCase("صفر") ? "" : s3);
        return s4.endsWith(" و ") ? s4.substring(0, s4.length() - 3) : s4;

    }

    private static String getNameOfNumber_lessThan_1_Miliun(long number) {
//        switch (number) {
        if (number == 1000) {
            return "یک میلیون";
        } else if (number == 2000) {
            return "دو میلیون";
        } else if (number == 3000) {
            return "سه میلیون";
        } else if (number == 4000) {
            return "چهار میلیون";
        } else if (number == 5000) {
            return "پنج میلیون";
        } else if (number == 6000) {
            return "شش میلیون";
        } else if (number == 7000) {
            return "هفت میلیون";
        } else if (number == 8000) {
            return "هشت میلیون";
        } else if (number == 9000) {
            return "نه میلیون";
        }
        String s = String.valueOf(number);
        String s2 = getNameOfNumber(Long.parseLong(s.substring(0, s.length() - 6)));
        String s3 = getNameOfNumber(Long.parseLong(s.substring(s.length() - 6, s.length())));
        String s4 = s2 + " میلیون و " + (s3.equalsIgnoreCase("صفر") ? "" : s3);
        return s4.endsWith(" و ") ? s4.substring(0, s4.length() - 3) : s4;
    }

    private static String getNameOfNumber_lessThan_1_Milyard(long number) {
        if (number == 1000) {
            return "یک میلیارد";
        } else if (number == 2000) {
            return "دو میلیارد";
        } else if (number == 3000) {
            return "سه میلیارد";
        } else if (number == 4000) {
            return "چهار میلیارد";
        } else if (number == 5000) {
            return "پنج میلیارد";
        } else if (number == 6000) {
            return "شش میلیارد";
        } else if (number == 7000) {
            return "هفت میلیارد";
        } else if (number == 8000) {
            return "هشت میلیارد";
        } else if (number == 9000) {
            return "نه میلیارد";
        }
        String s = String.valueOf(number);
        String s2 = getNameOfNumber(Long.parseLong(s.substring(0, s.length() - 9)));
        String s3 = getNameOfNumber(Long.parseLong(s.substring(s.length() - 9, s.length())));
        String s4 = s2 + " میلیارد و " + (s3.equalsIgnoreCase("صفر") ? "" : s3);
        return s4.endsWith(" و ") ? s4.substring(0, s4.length() - 3) : s4;
    }

    /**
     *
     * set "," between number Sample: 123456789 = 123,456,789
     *
     */
    public static String getFormattedNumber(String number) {
        String ret = "";
        int index = number.indexOf(".");
        if (index == -1) {
            char s2[] = number.toCharArray();
            String s3 = "";
            int counter = 0;
            for (int i = s2.length; i > 0; i--) {
                s3 += s2[i - 1];
                counter++;
                if (counter % 3 == 0) {
                    s3 += ",";
                }
            }
            char s4[] = s3.toCharArray();
            String s5 = "";
            for (int i = s4.length; i > 0; i--) {
                s5 += s4[i - 1];
            }
            if (s5.startsWith(",")) {
                ret = s5.substring(1);
            } else if (s5.endsWith(",")) {
                ret = s5.substring(0, s5.length() - 1);
            } else {
                ret = s5;
            }
        } else {
            int lastIndexOf = number.lastIndexOf(".");
            return (getFormattedNumber(number.substring(0, lastIndexOf).replace(".", "")) + number.substring(lastIndexOf));
        }
        return ret;
    }

    /**
     *
     * replace English Number To Persian Number
     */
    public static String doReplaceEnglishNoToPersianNo(String no) {
        if (!isDigit(no)) {
            return "This is'nt a digith";
        }
        String newNo = "";
        char c[] = no.toCharArray();
        for (int i = 0; i < c.length; i++) {
            char lastChar = c[i];
            switch ((int) lastChar) {
                case 48:
                    c[i] = (char) 1632;
                    break;
                case 49:
                    c[i] = (char) 1633;
                    break;
                case 50:
                    c[i] = (char) 1634;
                    break;
                case 51:
                    c[i] = (char) 1635;
                    break;
                case 52:
                    c[i] = (char) 1636;
                    break;
                case 53:
                    c[i] = (char) 1781;
                    break;
                case 54:
                    c[i] = (char) 1638;
                    break;
                case 55:
                    c[i] = (char) 1639;
                    break;
                case 56:
                    c[i] = (char) 1640;
                    break;
                case 57:
                    c[i] = (char) 1641;
                    break;
            }
        }
        for (int i = 0; i < c.length; i++) {
            newNo += c[i];
        }
        return newNo;
    }

    /**
     *
     * get Integer with my request Length
     */
    public static String getFormaterIntegerLength(int number, int lengthOfNumber) {
        String no = String.valueOf(number);
        if (no.length() > lengthOfNumber) {
            return no;
        } else {
            String s = "";
            for (int i = 0; i < (lengthOfNumber - no.length()); i++) {
                s += "0";
            }
            return (s + no);
        }
    }

    public static String getFormaterLongLength(Long number, int lengthOfNumber) {
        String no = String.valueOf(number);
        if (no.length() > lengthOfNumber) {
            return no;
        } else {
            String s = "";
            for (int i = 0; i < (lengthOfNumber - no.length()); i++) {
                s += "0";
            }
            return (s + no);
        }
    }

    /**
     *
     * less than 0.5 return No without .5 <br/> more than 0.5 return No without
     * .5 + 1
     */
    public static String getRound(double number, int numberAfterDot) {
        try {
            numberAfterDot++;
            double Flo = 0.5;
            int IntNumber = (int) number;
            double FloatNumber = number - IntNumber;
            double NewNumber = 0d;
            if (numberAfterDot == 0) {
                if (FloatNumber >= Flo) {
                    NewNumber = IntNumber + (Math.ceil(FloatNumber));
                } else {
                    NewNumber = IntNumber + (Math.floor(FloatNumber));
                }
                return String.valueOf(NewNumber);
            } else {
                String s = String.valueOf(FloatNumber);
                if (s.contains(".")) {
                    if (numberAfterDot < s.length()) {
                        String m = "";
                        String n = "." + s.substring(s.lastIndexOf(".") + 1, s.lastIndexOf(".") + numberAfterDot);//numberAfterDot
                        if (n.length() > 1) {
                            m = n;
                        }
                        return String.valueOf(IntNumber) + m;
                    } else {
                        String m = "";
                        String n = "." + s.substring(s.lastIndexOf(".") + 1, s.length());
                        if (n.length() > 1) {
                            m = n;
                        }
                        return String.valueOf(IntNumber) + m;
                    }
                } else {
                    if (numberAfterDot <= s.length()) {
                        String m = "";
                        String n = "." + s.substring(0, s.lastIndexOf(".") + numberAfterDot);//
                        if (n.length() > 1) {
                            m = n;
                        }
                        return String.valueOf(IntNumber) + m;
                    } else {
                        String m = "";
                        String n = "." + s.substring(0, s.length());
                        if (n.length() > 1) {
                            m = n;
                        }
                        return String.valueOf(IntNumber) + m;
                    }
                }
            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getRound()", ex.getMessage());
            return "";
        }
    }

    /**
     *
     * get resolution and convert Pixel To Cm
     */
    public static String doConvert_Pixel_To_Cm(int pixel, int resolution) {
        //100 inche = 254 cm
        float inch = Float.valueOf(new Float(pixel) / new Float(resolution));
        double cm = inch * 2.54d;
        String returnValue = getRound(cm, 0);
        return returnValue;
    }

    /**
     *
     * get resolution and convert Cm To Pixel
     */
    public static String doConvert_Cm_To_Pixel(double cm, int resolution) {
        double inch = 0.393700787401575f * cm;//0.393700787401575f
        double pixel = (int) (inch * resolution);
        String dbsw = String.valueOf(pixel);
        if (dbsw.contains(".")) {
            dbsw = dbsw.substring(0, dbsw.indexOf("."));
        }
        return dbsw;
    }

    /**
     *
     * get Max
     */
    public static long getMax(long... yourDoubles) {
        long max = 0;
        if (yourDoubles.length > 0) {
            max = yourDoubles[0];
        }
        for (int i = 1; i < yourDoubles.length; i++) {
            if (yourDoubles[i] > max) {
                max = yourDoubles[i];
            }
        }
        return max;
    }

    /**
     *
     * get Max
     */
    public static int getMax(int... yourDoubles) {
        int max = 0;
        if (yourDoubles.length > 0) {
            max = yourDoubles[0];
        }
        for (int i = 1; i < yourDoubles.length; i++) {
            if (yourDoubles[i] > max) {
                max = yourDoubles[i];
            }
        }
        return max;
    }

    /**
     *
     * get Min
     */
    public static long getMin(long... yourDoubles) {
        long min = 0;
        if (yourDoubles.length > 0) {
            min = yourDoubles[0];
        }
        for (int i = 1; i < yourDoubles.length; i++) {
            if (yourDoubles[i] < min) {
                min = yourDoubles[i];
            }
        }
        return min;
    }

    /**
     *
     * get Min
     */
    public static int getMin(int... yourDoubles) {
        int min = 0;
        if (yourDoubles.length > 0) {
            min = yourDoubles[0];
        }
        for (int i = 1; i < yourDoubles.length; i++) {
            if (yourDoubles[i] < min) {
                min = yourDoubles[i];
            }
        }
        return min;
    }
}
