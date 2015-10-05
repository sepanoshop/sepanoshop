/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Milad
 */
public class jjCollections {

    /**
     *
     * sort a Array String
     */
    public static void sort(String[] s) {
        Arrays.sort(s);
    }

    public static int getCharNo(char c) {
        for (int i = 0; i < jjString.AllAlphaNumericArray.length; i++) {
            if (jjString.AllAlphaNumericArray[i] == c) {
                return i + 1;
            };
        }
        return 0;
    }

    public static boolean isS1BeforeS2(String s1, String s2) {
        int length = s1.length() < s2.length() ? s1.length() : s2.length();
        char[] ar1 = s1.toCharArray();
        char[] ar2 = s2.toCharArray();
        for (int i = 0; i < length; i++) {
//            jjFileTxt.write("D:\\b.txt", getCharNo(ar1[i]) + "," + ar1[i] + "-" + getCharNo(ar2[i]) + "," + ar2[i], true, true);
            if (getCharNo(ar1[i]) < getCharNo(ar2[i])) {
                return true;
            } else if (getCharNo(ar1[i]) != getCharNo(ar2[i])) {
                return false;
            }
        }
        return false;
    }

    /**
     *
     * sort a Vector String
     */
    public static void sortStr(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (isS1BeforeS2(list.get(j), list.get(i))) {
                    String ff = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, ff);
                }
            }
        }
    }

    public static List<Map<String, Object>> sort(List<Map<String, Object>> list, String keyMapSorted) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (isS1BeforeS2(list.get(j).get(keyMapSorted).toString(), list.get(i).get(keyMapSorted).toString())) {
                    Map<String, Object> ff = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, ff);
                }
            }
        }
        return list;
    }

    /**
     *
     * sort a Vector String
     */
    public static void sortByLenghFirstLarg(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).length() >= list.get(i).length()) {
                    String ff = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, ff);
                }
            }
        }
    }

    /**
     *
     * sort a Vector String
     */
    public static void sortByLenghFirstSmall(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).length() <= list.get(i).length()) {
                    String ff = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, ff);
                }
            }
        }
    }

    /**
     *
     * sort a Vector Integer
     */
    public static void sortInt(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(i)) < 0) {
                    int ff = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, ff);
                }
            }
        }
    }

    public static File[] sortBySize(File path[]) {
        List<Long> l1 = new ArrayList<Long>();
        List<File> l2 = new ArrayList<File>();
        for (int i = 0; i < path.length; i++) {
            l1.add(path[i].length());
            l2.add(path[i]);
        }
        for (int i = 0; i < l1.size(); i++) {
            for (int j = i + 1; j < l1.size(); j++) {
                if (l1.get(j).compareTo(l1.get(i)) < 0) {
                    Long ff = l1.get(i);
                    File fd = l2.get(i);
                    l1.set(i, l1.get(j));
                    l2.set(i, l2.get(j));
                    l1.set(j, ff);
                    l2.set(j, fd);
                }
            }
        }
        for (int i = 0; i < l2.size(); i++) {
            path[i] = l2.get(i);
        }
        return path;
    }
}
