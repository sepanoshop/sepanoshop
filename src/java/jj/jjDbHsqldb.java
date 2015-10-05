/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Milad
 */
public class jjDbHsqldb {

    public static void Connect() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost");
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }
}
