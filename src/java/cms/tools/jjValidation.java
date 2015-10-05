/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import jj.jjNumber;

/**
 *
 * @author Milad
 */
public class jjValidation {

//ا ب پ ت ث ج چ ‌ ح خ د ذ ر ز ‌ ژ س ‌ ش ص ض ط ظ ع غ ف ق ک گ ل م ن و ه ی
//
//ء آ اً هٔ ة ئ  ٌ  ً  ّ  ُ  ؤ إ ي
//+=-%*/
//1234567890
///\:*?"<>|
    public enum Rules {

        lenghtEqual, lenghtMax, lenghtMin, nullable, email, numeric, date;
    }

    // =========================================================================
    public static boolean isEmail(String value) {
        return value.toLowerCase().matches("([a-z0-9\\+_\\-]+)(\\.[a-z0-9\\+_\\-]+)*@([a-z0-9\\-]+\\.)+[a-z]{2,6}$");
    }

    public static String isEmailMessageFa(String value, String lableName) {
        if (!isEmail(value)) {
            return "لطفا در فیلد  "
                    + lableName
                    + " یک ایمیل معتبر وارد کنید.";
        }
        return "";
    }

    public static String isEmailMessageEn(String value, String lableName) {
        if (!isEmail(value)) {
            return "please insert currect email in " + lableName + " field";
        }
        return "";
    }

    // =========================================================================
    /**
     *
     * @return true if value be like "1389/12/08" or "13891208"
     */
    public static boolean isDateFa(String value) {
        String[] date = value.split("/");
        if (date.length != 3) {
            return false;
        }
        if (!jjNumber.isDigit(value.replaceAll("/", ""))) {
            return false;
        }
        if (date[0].length() != 4) {
            return false;
        }

        if (Integer.parseInt(date[1]) > 12) {
            return false;
        }
        if (Integer.parseInt(date[2]) > 31) {
            return false;
        }
        return true;
    }

    public static String isDateMessageFa(String value, String lableName) {
        if (!isDateFa(value)) {
            return "لطفا در فیلد "
                    + lableName
                    + " یک تاریخ معتبر وارد کنید."
                    + "";
        }
        return "";
    }

    public static String isDateMessageEn(String value, String lableName) {
        if (!isDateFa(value)) {
            return "please insert currect date in " + lableName + " field";
        }
        return "";
    }

    // =========================================================================
    /**
     *
     * @return true if value Not be null or ""
     */
    public static boolean isFill(String value) {
        return !value.trim().isEmpty();
    }

    public static String isFillMessageFa(String value, String lableName) {
        if (!isFill(value)) {
            return "لطفا در فیلد "
                    + lableName
                    + " مقداری را وارد کنید."
                    + "";
        }
        return "";
    }

    public static String isFillMessageEn(String value, String lableName) {
        if (!isFill(value)) {
            return "please insert data in " + lableName + " field";
        }
        return "";
    }

    public static String isFillScript(String tagId, String lableName) {
        String script = "";
        script += "if(main.getValue(\"" + tagId + "\").length<" + 1 + "){\n"
                + "jjDialog.ok(\"\",\"" + isFillMessageFa("", lableName) + "\");\n"
                + "return false;\n"
                + "}";

        return script;
    }
    // =========================================================================

    /**
     *
     * @return true if value be a-z without any number
     */
    public static boolean isOnlyAlpha(String value) {
        return value.matches("([a-z])+$");
    }

    public static String isOnlyAlphaMessageFa(String value, String lableName) {
        if (!isOnlyAlpha(value)) {
            return "لطفا در فیلد "
                    + lableName
                    + " عدد وارد نکنید."
                    + "";
        }
        return "";
    }

    public static String isOnlyAlphaMessageEn(String value, String lableName) {
        if (!isOnlyAlpha(value)) {
            return "please don't insert number in " + lableName + " field";
        }
        return "";
    }
    // =========================================================================

    /**
     *
     * @return true if value length be equal exactLength
     */
    public static boolean isLengthExact(String value, int exactLength) {
        return (value.length() == exactLength);
    }

    public static String isLengthExactMessageFa(String value, int exactLength, String lableName) {
        if (!isLengthExact(value, exactLength)) {
            return "لطفا در فیلد "
                    + lableName
                    + " تنها از "
                    + exactLength
                    + " حرف استفاده نمایید.";
        }
        return "";
    }

    public static String isLengthExactMessageEn(String value, int exactLength, String lableName) {
        if (!isLengthExact(value, exactLength)) {
            return "please insert " + exactLength + " charachter in " + lableName + " field";
        }
        return "";
    }

    public static String isLengthExactScript(String tagId, int exactLength, String lableName) {
        String script = "";
        script += "if(main.getValue(\"" + tagId + "\").length!=" + exactLength + "){\n"
                + "jjDialog.ok(\"\",\"" + isLengthExactMessageFa(jjNumber.getFormaterIntegerLength(0, exactLength + 1), exactLength, lableName) + "\");\n"
                + "return false;\n"
                + "}";

        return script;
    }
    // =========================================================================

    public static boolean isLengthMax(String value, int maxLength) {
        return value.length() <= maxLength;
    }

    public static String isLengthMaxMessageFa(String value, int maxLength, String lableName) {
        if (!isLengthMax(value, maxLength)) {
            return "لطفا در فیلد "
                    + lableName
                    + " کمتر از "
                    + maxLength
                    + " حرف استفاده نمایید.";
        }
        return "";
    }

    public static String isLengthMaxMessageEn(String value, int maxLength, String lableName) {
        if (!isLengthMax(value, maxLength)) {
            return "please insert less than " + maxLength + " charachter in " + lableName + " field";
        }
        return "";
    }

    public static String isLengthMaxScript(String tagId, int maxLength, String lableName) {
        String script = "";
        script += "if(main.getValue(\"" + tagId + "\").length>" + maxLength + "){\n"
                + "jjDialog.ok(\"\",\"" + isLengthMaxMessageFa(jjNumber.getFormaterIntegerLength(0, maxLength + 1), maxLength, lableName) + "\");\n"
                + "return false;\n"
                + "}";

        return script;
    }
    // =========================================================================

    public static boolean isLengthMin(String value, int minLength) {
        return value.length() > minLength;
    }

    public static String isLengthMinMessageFa(String value, int minLength, String lableName) {
        if (!isLengthMin(value, minLength)) {
            return "لطفا در فیلد "
                    + lableName
                    + " بیشتر از "
                    + minLength
                    + " حرف استفاده نمایید.";
        }
        return "";
    }

    public static String isLengthMinMessageEn(String value, int maxLength, String lableName) {
        if (!isLengthMin(value, maxLength)) {
            return "please insert larger than " + maxLength + " charachter in " + lableName + " field";
        }
        return "";
    }

    public static String isLengthMinScript(String tagId, int minLength, String lableName) {
        String script = "";
        script += "if(main.getValue(\"" + tagId + "\").length<" + minLength + "){\n"
                + "jjDialog.ok(\"\",\"" + isLengthMinMessageFa("", minLength, lableName) + "\");\n"
                + "return false;\n"
                + "}";

        return script;
    }
    // =========================================================================

    public static boolean isDigit(String value) {
        return jjNumber.isDigit(value);
    }

    public static String isDigitMessageFa(String value, String lableName) {
        if (!isDigit(value)) {
            return "لطفا در فیلد "
                    + lableName + " "
                    + "یک عدد وارد کنید."
                    + "";
        }
        return "";
    }

    public static String isDigitMessageEn(String value, String lableName) {
        if (!isDigit(value)) {
            return "please a number in " + lableName + " field";
        }
        return "";
    }

    public static String isDigitScript(String tagId, String lableName) {
        String script = "";
        script += "if(!jjValidation.isDigit(main.getValue(\"" + tagId + "\"))){\n"
                + "jjDialog.ok(\"\",\"" + isDigitMessageFa("$", lableName) + "\");\n"
                + "return false;\n"
                + "}";

        return script;
    }
    // =========================================================================

    public static boolean isDigitDouble(String value) {
        return jjNumber.isDigitDouble(value);
    }

    public static String isDigitDoubleMessageFa(String value, String lableName) {
        if (!isDigitDouble(value)) {
            return "لطفا در فیلد "
                    + lableName + " "
                    + "یک عدد وارد کنید."
                    + "";
        }
        return "";
    }

    public static String isDigitDoubleMessageEn(String value, String lableName) {
        if (!isDigitDouble(value)) {
            return "please a number in " + lableName + " field";
        }
        return "";
    }

    public static String isDigitDoubleScript(String tagId, String lableName) {
        String script = "";
        script += "if(!jjValidation.isDigit(main.getValue(\"" + tagId + "\"))){\n"
                + "jjDialog.ok(\"\",\"" + isDigitDoubleMessageFa("$", lableName) + "\");\n"
                + "return false;\n"
                + "}";

        return script;
    }
    // =========================================================================

    public static boolean isMatchField(String value1, String value2) {
        return value1.toString().equals(value2.toString());
    }

    public static String isMatchFieldMessageFa(String value1, String value2, String lableName1, String lableName2) {
        if (!isMatchField(value1, value2)) {
            return "لطفا در فیلد "
                    + lableName1 + " و "
                    + lableName2
                    + " مقدار مساوی وارد کنید."
                    + "";
        }
        return "";
    }

    public static String isMatchFieldMessageEn(String value1, String value2, String lableName1, String lableName2) {
        if (!isMatchField(value1, value2)) {
            return "please insert equal value in " + lableName1 + " and " + lableName2 + " field";
        }
        return "";
    }

    /**
     *
     * @return true if value be only number
     */
    public static boolean isValidNumeric(String value) {
        return value.matches("[\\-+]?[0-9]*\\.?[0-9]+$");
    }
}
