/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.Component;

/**
 *
 * @author Milad
 */
public class jjError {

    /**
     *
     * Exception write in WRITE_HANDLER_FILE file without alert
     */
    public static void Handler(Exception exception) {
        Handler(exception, "", "", null);
    }

    /**
     *
     * Exception write in WRITE_HANDLER_FILE file and alert message
     */
    public static void Handler(Exception exception, String message) {
        Handler(exception, message, "", null);
    }

    /**
     *
     * Exception write in WRITE_HANDLER_FILE file and alert message with title
     */
    public static void Handler(Exception exception, String message, String title) {
        Handler(exception, message, title, null);
    }

    /**
     *
     * Exception write in WRITE_HANDLER_FILE file and alert message with title on parentComponent
     */
    public static void Handler(Exception exception, String message, String title, Component parentComponent) {
        exception.printStackTrace();
        if (Setting.getWriteHandlerFileAddress() != null) {
            StackTraceElement[] stackTrace = exception.getStackTrace();
            String mess = " \r\n";
            for (int i = 0; i < stackTrace.length; i++) {
                mess += "Class:" + stackTrace[i].getClassName()
                        + "     Line:" + stackTrace[i].getLineNumber()
                        + "     Method:" + stackTrace[i].getMethodName()
                        + "     Message:" + exception + " \r\n";
                mess += " \r\n";

            }

            jjCalendar_IR date = new jjCalendar_IR();
            String fileName = date.getYear_4length() + "." + date.getMonth_2length() + "." + date.getDay_2length();
            fileName += " - " + date.getHours_2length() + "." + date.getMinutes_2length() + "." + date.getSeconds_2length();
            fileName += ".txt";
//            jjFileTxt.write(Setting.getWriteHandlerFileAddress() + "\\" + fileName, mess, true, true);
            if (message != null && !message.equals("")) {
                jjDialog.error_Message(parentComponent, title, message);
            }
        }
        exception.printStackTrace();
    }
}
