/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.io.File;
import java.util.List;

/**
 *
 * @author MD
 */
public class ServerLog {

    static boolean DebugMod = true;
//    static boolean DebugMod = false;

    public static void Print(String Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }

    /**
     *
     * @param Log
     */
    public static void Print(StringBuffer Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }

    public static void Print(int Log) {
        if (DebugMod) {
            System.out.println(String.valueOf(Log));
        }
    }

    public static void Print(File Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }

    public static void Print(org.w3c.dom.Node Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }

    public static void Print(Exception Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }

    public static void Print(List<String> Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }

    public static void Print(Object Log) {
        if (DebugMod) {
            System.out.println(Log);
        }
    }
}
