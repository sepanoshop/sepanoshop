/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjObject {

    /**
     *
     * write a Object to file like a.dat
     *  if use your class implements Serializable for class
     */
    public static void doWriteObject(String path, Object object) {
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(path));
            outStream.writeObject(object);
            outStream.flush();
            outStream.close();
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doWriteObject()", ex.getMessage());

            return;
        }
    }

    /**
     *
     * read a Object from file like vectorIn = (Vector<Personnel>) doReadObject("C:\\a.dat");
     *  if use your class implements Serializable for class
     *
     */
    public static Object doReadObject(String path) {
        if (!new File(path).exists()) {
            return null;
        }
        try {
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(path));
            return inStream.readObject();
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doReadObject()", ex.getMessage());
            return null;
        }
    }

    public static Object getFieldValue(Object obj, String field) {
        //StackTraceElement[] ste = new Throwable().getStackTrace();
        //Logger.log("field " + field+" of object" + obj+" called from "  + ste[0].getClassName() + " line " + ste[0].getLineNumber());
        return getFieldValue(obj, field, null);
    }

    public static Object getFieldValue(Object obj, String field, Object defaultValue) {
        try {

            Field f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return f.get(obj);

            //return obj.getClass().getDeclaredField(field).get(obj);
        } catch (Exception ex) {
            try {
                Class[] classes = {};
                String methodNamde = "get" + jjString.getUperCaseFirstWord(field);
                if (defaultValue != null) {
                    if (defaultValue instanceof Boolean) {
                        methodNamde = "is" + jjString.getUperCaseFirstWord(field);
                    }
                }

                Method m = obj.getClass().getDeclaredMethod(methodNamde, classes);
                m.setAccessible(true);
                return m.invoke(obj);
            } catch (Exception ex1) {
                jjError.Handler(ex1);
            }
        }
        return defaultValue;
    }

    public static void setFieldValue(Object obj, String field, Object value) {
        try {

            Field f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(obj, value);

            //return obj.getClass().getDeclaredField(field).get(obj);
        } catch (Exception ex) {
            try {
                Class[] classes = {};
                String methodNamde = "set" + jjString.getUperCaseFirstWord(field);
                Method m = obj.getClass().getDeclaredMethod(methodNamde, classes);
                m.setAccessible(true);
                m.invoke(obj, value);
            } catch (Exception ex1) {
                jjError.Handler(ex1);
            }
        }
    }

    public static void out(Object obj) {
        ServerLog.Print(obj);
    }
}
