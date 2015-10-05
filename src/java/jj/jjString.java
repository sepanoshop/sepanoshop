/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.util.ArrayList;
import java.util.List;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjString {

    public static String persian = "ابپتثجچ‌حخدذرز‌ژس‌شصضطظعغفقکگلمنوهی";
    public static char AllAlphaNumericArray[] = {' ',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'آ',
        'ا',
        'ب',
        'پ',
        'ت',
        'ث',
        'ج',
        'چ',
        'ح',
        'خ',
        'د',
        'ذ',
        'ر',
        'ز',
        'ژ',
        'س',
        'ش',
        'ص',
        'ض',
        'ط',
        'ظ',
        'ع',
        'غ',
        'ف',
        'ق',
        'ک',
        'گ',
        'ل',
        'م',
        'ن',
        'و',
        'ه',
    'ی',
    'ي',
    };
    public static String arabic = "ءآاًهٔةئًٌُّؤإي";
    public static String calculate = "+=-%*/";
    public static String number = "1234567890";
    public static String notInFileName = "/\\:*?\"<>|";

    /**
     *
     * replace Persian Character To English Character
     */
    public static List<String> split(String all, String regex) {
        List<String> spl = new ArrayList<String>();
        int size = 0;
        int exp = all.toLowerCase().indexOf(regex.toLowerCase(), size);
        while (exp > -1) {
            String scr = all.substring(size, exp);
            spl.add(scr);
            size += scr.length() + regex.length();
            exp = all.toLowerCase().indexOf(regex.toLowerCase(), size);
            if (exp == -1) {
                String scr2 = all.substring(size);
                spl.add(scr2);
            }
        }
        return spl;
    }

    public static String replacePersianCharToEnglish(String Word) {
        char c[] = Word.toCharArray();
        String newWord = "";
        for (int i = 0; i < c.length; i++) {
            char lastChar = c[i];
            ServerLog.Print((int) lastChar);
            switch ((int) lastChar) {
                case 1632:
                    c[i] = (char) 48;
                    break;
                case 1633:
                    c[i] = (char) 49;
                    break;
                case 1634:
                    c[i] = (char) 50;
                    break;
                case 1635:
                    c[i] = (char) 51;
                    break;
                case 1636:
                    c[i] = (char) 52;
                    break;
                case 1781:
                    c[i] = (char) 53;
                    break;
                case 1638:
                    c[i] = (char) 54;
                    break;
                case 1639:
                    c[i] = (char) 55;
                    break;
                case 1640:
                    c[i] = (char) 56;
                    break;
                case 1641:
                    c[i] = (char) 57;
                    break;
                case 1567:
                    c[i] = (char) 63;
                    break;
                case 1570:
                    c[i] = (char) 72;
                    break;
                case 1575:
                    c[i] = (char) 104;
                    break;
                case 1576:
                    c[i] = (char) 102;
                    break;
                case 1662:
                    c[i] = (char) 124;
                    break;
                case 1578:
                    c[i] = (char) 106;
                    break;
                case 1579:
                    c[i] = (char) 101;
                    break;
                case 1580:
                    c[i] = (char) 91;
                    break;
                case 1670:
                    c[i] = (char) 93;
                    break;
                case 1581:
                    c[i] = (char) 112;
                    break;
                case 1582:
                    c[i] = (char) 111;
                    break;
                case 1583:
                    c[i] = (char) 110;
                    break;
                case 1584:
                    c[i] = (char) 98;
                    break;
                case 1585:
                    c[i] = (char) 118;
                    break;
                case 1586:
                    c[i] = (char) 99;
                    break;
                case 1688:
                    c[i] = (char) 67;
                    break;
                case 1587:
                    c[i] = (char) 115;
                    break;
                case 1588:
                    c[i] = (char) 97;
                    break;
                case 1589:
                    c[i] = (char) 119;
                    break;
                case 1590:
                    c[i] = (char) 113;
                    break;
                case 1591:
                    c[i] = (char) 120;
                    break;
                case 1592:
                    c[i] = (char) 122;
                    break;
                case 1593:
                    c[i] = (char) 117;
                    break;
                case 1594:
                    c[i] = (char) 121;
                    break;
                case 1601:
                    c[i] = (char) 116;
                    break;
                case 1602:
                    c[i] = (char) 114;
                    break;
                case 1711:
                    c[i] = (char) 39;
                    break;
                case 1705:
                    c[i] = (char) 59;
                    break;
                case 1604:
                    c[i] = (char) 103;
                    break;
                case 1605:
                    c[i] = (char) 108;
                    break;
                case 1606:
                    c[i] = (char) 107;
                    break;
                case 1608:
                    c[i] = (char) 44;
                    break;
                case 1607:
                    c[i] = (char) 105;
                    break;
                case 1740:
                    c[i] = (char) 100;
                    break;
                case 1574:
                    c[i] = (char) 109;
                    break;
            }
        }
        for (int i = 0; i < c.length; i++) {
            newWord += c[i];
        }
        return newWord;
    }

    /**
     *
     * replace English Character To Persian Character
     */
    public static String replaceEnglishCharToPersian(String Word) {
        char c[] = Word.toCharArray();
        String newWord = "";
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
                case 72:
                    c[i] = (char) 1570;
                    break;
                case 104:
                    c[i] = (char) 1575;
                    break;
                case 102:
                    c[i] = (char) 1576;
                    break;
                case 124:
                    c[i] = (char) 1662;
                    break;
                case 106:
                    c[i] = (char) 1578;
                    break;
                case 101:
                    c[i] = (char) 1579;
                    break;
                case 91:
                    c[i] = (char) 1580;
                    break;
                case 93:
                    c[i] = (char) 1670;
                    break;
                case 112:
                    c[i] = (char) 1581;
                    break;
                case 111:
                    c[i] = (char) 1582;
                    break;
                case 110:
                    c[i] = (char) 1583;
                    break;
                case 98:
                    c[i] = (char) 1584;
                    break;
                case 118:
                    c[i] = (char) 1585;
                    break;
                case 99:
                    c[i] = (char) 1586;
                    break;
                case 67:
                    c[i] = (char) 1688;
                    break;
                case 115:
                    c[i] = (char) 1587;
                    break;
                case 97:
                    c[i] = (char) 1588;
                    break;
                case 119:
                    c[i] = (char) 1589;
                    break;
                case 113:
                    c[i] = (char) 1590;
                    break;
                case 120:
                    c[i] = (char) 1591;
                    break;
                case 122:
                    c[i] = (char) 1592;
                    break;
                case 117:
                    c[i] = (char) 1593;
                    break;
                case 121:
                    c[i] = (char) 1594;
                    break;
                case 116:
                    c[i] = (char) 1601;
                    break;
                case 114:
                    c[i] = (char) 1602;
                    break;
                case 39:
                    c[i] = (char) 1711;
                    break;
                case 59:
                    c[i] = (char) 1705;
                    break;
                case 103:
                    c[i] = (char) 1604;
                    break;
                case 108:
                    c[i] = (char) 1605;
                    break;
                case 107:
                    c[i] = (char) 1606;
                    break;
                case 44:
                    c[i] = (char) 1608;
                    break;
                case 105:
                    c[i] = (char) 1607;
                    break;
                case 100:
                    c[i] = (char) 1740;
                    break;
                case 109:
                    c[i] = (char) 1574;
                    break;
            }
        }
        for (int i = 0; i < c.length; i++) {
            newWord += c[i];
        }
        return newWord;
    }

    public static String getUperCaseFormater(String txt) {
        String s0[] = txt.split(" ");
        List<String> s1 = new ArrayList<String>();
        for (int i = 0; i < s0.length; i++) {
            if (s0[i] != null && s0[i].length() > 0) {
                s1.add(s0[i].substring(0, 1).toUpperCase() + s0[i].substring(1).toLowerCase());
            }
        }
        String string = "";
        for (int i = 0; i < s1.size(); i++) {
            string += (i == 0 ? s1.get(i) : " " + s1.get(i));
        }
        return string.trim();
    }

    public static String getUperCaseFirstWord(String txt) {
        if (txt != null) {
            if (txt.length() > 0) {
                return (txt.substring(0, 1).toUpperCase() + txt.substring(1).toLowerCase());
            }
        }
        return txt;
    }

    public static String getOnlyDigit(String txt) {
        String s = "";
        char[] toCharArray = txt.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            char c = toCharArray[i];
            if (jjNumber.isDigit(c + "")) {
                s += c;
            }
        }
        return s;
    }
}
