/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Milad
 */
public class jjConvert {

    public static int toInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public static boolean toBoolean(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof String) {
            return !obj.toString().trim().equals("false");
        }

        if (obj instanceof Number) {
            return !obj.equals(0);
        }
        try {
            return Boolean.parseBoolean((String) obj);
        } catch (Exception ex) {
            return false;
        }
    }

    public static String toString(Object obj) {
        try {
            return obj.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public static BigDecimal toBigDecimal(Object obj) {
        try {
            return new BigDecimal((String) obj);
        } catch (Exception ex) {
            return new BigDecimal(0);
        }
    }

    public static byte toByte(Object obj) {
        try {
            return new Byte((String) obj);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static short toShort(Object obj) {
        try {

            return new Short(toString(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Short("0");
        }
    }

    public static long toLong(Object obj) {
        try {
            return Long.parseLong((String) obj);
        } catch (Exception ex) {
            return new Long("0");
        }
    }

    public static Double toDouble(Object value) {
        try {
            return new Double((String) value);
        } catch (Exception ex) {
            return new Double("0");
        }
    }

    public static BigInteger toBigInteger(Object value) {
        try {
            return new BigInteger((String) value);
        } catch (Exception ex) {
            return new BigInteger("0");
        }
    }

    public static String toString(String... list) {
        String src = "";
        for (int i = 0; i < list.length; i++) {
            src += (src.equals("") ? "" : ",") + list[i];
        }
        return src;
    }
    public static String toString(java.util.List<String> list) {
        String src = "";
        for (int i = 0; i < list.size(); i++) {
            src += (src.equals("") ? "" : ",") + list.get(i);
        }
        return src;
    }

    public static Integer[] toIntegerArray(java.util.List<Integer> widthList) {
        Integer titleName[] = new Integer[widthList.size()];
        for (int i = 0; i < titleName.length; i++) {
            titleName[i] = widthList.get(i);
        }
        return titleName;
    }

    public static char[] toCharArray(java.util.List<String> alignList) {
        char titleName[] = new char[alignList.size()];
        for (int i = 0; i < titleName.length; i++) {
            if (alignList.get(i).equals("l")) {
                titleName[i] = 'l';
            } else if (alignList.get(i).equals("r")) {
                titleName[i] = 'r';
            } else {
                titleName[i] = 'c';
            }
        }
        return titleName;
    }

    public static String[] toArray(java.util.List<String> list) {
        String titleName[] = new String[list.size()];
        for (int i = 0; i < titleName.length; i++) {
            titleName[i] = list.get(i);
        }
        return titleName;
    }

    public static java.util.List<String> toList(String... list) {
        java.util.List<String> l = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {
            l.add(list[i]);

        }
        return l;
    }
}
