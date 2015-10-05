/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.lang.reflect.Method;

/**
 *
 * @author Milad
 */
public class jjAnnotaion {

    public static String getColumnAnnotaion(Class clazz, String rowNameFromDB) {
        String s = "";
        try {
//            Method methods[] = clazz.getMethods();
//            for (int i = 0; i < methods.length; i++) {
//                Column column = methods[i].getAnnotation(Column.class);
//                if (column != null) {
//                    return column;
//                }
//            }
            Method method = clazz.getMethod(rowNameFromDB);
//            Column column = method.getAnnotation(Column.class);
//            System.out.print("===== " + method.getName() + " ======  >> ");
//            ServerLog.Print(method.toGenericString());
//            ServerLog.Print("Name      = " + column.name());
//            ServerLog.Print("Nullable  = " + column.nullable());
//            ServerLog.Print("Precision = " + column.precision());
//            ServerLog.Print("Length    = " + column.length());
//            ServerLog.Print("Unique    = " + column.unique());
//            ServerLog.Print("Updatable = " + column.updatable());
//            ServerLog.Print("=================================");

//            javax.persistence.Table tbl = (javax.persistence.Table) clazz.getAnnotation(Table.class);
//            s += tbl.name() + "-" + column.name() + "-" + method.getReturnType().getSimpleName() + "-" + column.length() + "-" + column.nullable();
            s = "name=\"" + s + "\" id=\"" + s + "\"";
        } catch (Exception ex) {
            return null;
        }
        return s;
    }
//    public static String get1(Column column) {
//        String s = "";
//        String s2 = "";
//        try {
////            Method method = clazz.getMethod(rowNameFromDB);
////            Column column = method.getAnnotation(Column.class);
////            System.out.print("===== " + method.getName() + " ======  >> ");
////            ServerLog.Print(method.toGenericString());
////            ServerLog.Print("Name      = " + column.name());
////            ServerLog.Print("Nullable  = " + column.nullable());
////            ServerLog.Print("Precision = " + column.precision());
////            ServerLog.Print("Length    = " + column.length());
////            ServerLog.Print("Unique    = " + column.unique());
////            ServerLog.Print("Updatable = " + column.updatable());
////            ServerLog.Print("=================================");
//
////            javax.persistence.Table tbl = (javax.persistence.Table) method.getClass().getAnnotation(Table.class);
//            s = column.table() + "-" + column.name();
//            s2 = " maxLength=" + column.length() + " nullable=" + column.nullable();
//            s = "name=\"" + s + "\" id=\"" + s + "\"" + s2;
//            ServerLog.Print(">>>>>" + s);
//        } catch (Exception ex) {
//            return null;
//        }
//        return s;
//    }
}
