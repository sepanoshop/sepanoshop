package jj;

import cms.tools.ServerLog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Milad Jamalzadeh 09132686053
 */
public class jjFileTxt {

    ///\:*?"<>|
    public static String US_ASCII = "US-ASCII";
    public static String ISO646_US = "ISO646-US";
    public static String ISO_8859_1 = "ISO-8859-1";
    public static String UTF_8 = "UTF-8";
    public static String ISO_LATIN_1 = "ISO-LATIN-1";
    public static String UTF_16BE = "UTF-16BE";
    public static String UTF_16LE = "UTF-16LE";
    public static String UTF_16 = "UTF-16";
    private static Scanner input = null;

    // =========================================================================
    /**
     *
     * read String file by scanner(UTF-8) and return String
     */
    public static String read(File path) {
        StringBuilder src = new StringBuilder();
        if (path.canRead()) {
            try {
                input = new Scanner(path, "UTF-8");
                while (input.hasNext()) {
                    src.append(input.nextLine());
                }

            } catch (Exception ex) {
                jjError.Handler(ex);
                return src.toString();
            } finally {
                input.close();
            }
        } else {
            ServerLog.Print("File not exist in:" + " " + path.getAbsolutePath());
        }
        if (src.toString().startsWith("﻿")) {            
            return src.substring(1);
        }
        input.close();
        return src.toString();
    }

    /**
     *
     * read String file by scanner(UTF-8) and return String
     */
    public static String read(String path) {
        return read(new File(path));
    }

    // =========================================================================
    /**
     *
     * @return line of file by scanner(UTF-8)and return List<String> <br/> "line
     * 1" == list.get(0); <br/> "line 2" == list.get(1); <br/>
     */
    public static List<String> readLine(File path) {
        List<String> list = new ArrayList<String>();
        if (path.exists()) {
            try {
                input = new Scanner(path, "UTF-8");
                while (input.hasNext()) {
                    list.add(input.nextLine());
                }
                if (list.size() > 0 && list.get(0).startsWith("﻿")) {
                    list.set(0, list.get(0).substring(1));
                }
            } catch (Exception ex) {
                jjError.Handler(ex);
                return list;
            } finally {
                input.close();
            }
        } else {
            ServerLog.Print("File not exist in:" + " " + path.getAbsolutePath());
        }
        return list;
    }

    /**
     *
     * @return line of file by scanner(UTF-8)and return List<String> <br/> "line
     * 1" == list.get(0); <br/> "line 2" == list.get(1); <br/>
     */
    public static List<String> readLine(String path) {
        return readLine(new File(path));
    }

    // =========================================================================
    /**
     *
     * write String by BufferedWriter(UTF-8)
     */
    public static void write(File path, String str) {
        write(path, str, false, false);
    }
    // =========================================================================

    /**
     *
     * write String by BufferedWriter(UTF-8)
     */
    public static void write(String path, String str) {
        write(new File(path), str, false, false);
    }
    // =========================================================================

    /**
     *
     * write String by BufferedWriter(UTF-8)
     */
    public static void write(String path, String str, boolean append, boolean stratWriteInNewLine) {
        write(new File(path), str, append, stratWriteInNewLine);
    }

    /**
     *
     * write String by BufferedWriter(UTF-8)
     */
    public static void write(File path, String str, boolean append, boolean stratWriteInNewLine) {
        BufferedWriter out = null;
        try {
            path.getParentFile().mkdirs();
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, append), "UTF-8"));
            if (!path.exists() || path.length() == 0 || !stratWriteInNewLine) {
                out.write(str);
            } else {
                out.write("\r\n" + str);
            }
        } catch (Exception ex) {
//            jjError.Handler(ex);
            return;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (Exception ex) {
                jjError.Handler(ex);
            }
        }
    }
    // =========================================================================

    /**
     *
     * write String by BufferedWriter(UTF-8) line by line
     */
    public static void writeLine(String path, List<String> listStr, boolean append, boolean stratWriteInNewLine) {
        writeLine(new File(path), listStr, append, stratWriteInNewLine);
    }

    /**
     *
     * write String by BufferedWriter(UTF-8) line by line
     */
    public static void writeLine(File path, List<String> listStr, boolean append, boolean stratWriteInNewLine) {
        String str = (listStr.size() > 0) ? str = listStr.get(0) : "";
        for (int i = 1; i < listStr.size(); i++) {
            str += "\r\n" + listStr.get(i);
        }
        write(path, str, append, stratWriteInNewLine);
    }
    // =========================================================================

    public static Map<String, Object> readKeyValue(String path) {
        return readKeyValue(new File(path));
    }

    public static Map<String, Object> readKeyValue(File path) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> readLine = jjFileTxt.readLine(path);
        for (int i = 0; i < readLine.size(); i++) {
            String[] keyValue = getKeyValue(readLine.get(i));
            if (!keyValue[0].equals("")) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }

    public static List<String[]> getKeyValue(List<String> line) {
        List<String[]> l = new ArrayList<String[]>();
        for (int i = 0; i < line.size(); i++) {
            int indexOf = line.get(i).indexOf("=");
            String[] array = {"", ""};
            if (indexOf > -1) {
                array[0] = line.get(i).substring(0, indexOf);
                array[1] = line.get(i).substring(indexOf + 1);
            }
            if (!array[0].equals("")) {
                l.add(array);
            }
        }
        return l;
    }

    public static String[] getKeyValue(String line) {
        int indexOf = line.indexOf("=");
        String[] array = {"", ""};
        if (indexOf > -1) {
            array[0] = line.substring(0, indexOf);
            array[1] = line.substring(indexOf + 1);
        }
        return array;
    }

    public static void writeKeyValue(File path, Map<String, Object> map) {
        Object[] keySet = map.keySet().toArray();
        List<String> l = new ArrayList<String>();
        for (int i = 0; i < keySet.length; i++) {
            l.add(keySet[i].toString() + "=" + map.get(keySet[i]));
        }
        jjFileTxt.writeLine(path, l, false, false);
    }

    public static void writeKeyValue(String path, Map<String, Object> map) {
        writeKeyValue(new File(path), map);
    }
}
