/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.sql.*;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milad
 */
public class jjDbSql {

    String userName;
    String password;
    Connection con;
    ResultSet resultSet;
    String serverHost = "127.0.0.1"; //localhost";
    String port = "3306";
    String databaseName = "db";

    public jjDbSql(String userName, String password, String databaseName, String serverHost, String port) {
        this.userName = userName;
        this.password = password;
        try {
            Class.forName("").newInstance();
        } catch (Exception ex) {
            jjError.Handler(ex, "فایل های کتابخانه اتصال به دیتابیس در دسترس نمی باشد.");
            return;
        }
    }
//==============================================================================

    public void Connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + serverHost + ":"
                    + port + "/" + databaseName, userName, password);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }
//==============================================================================

    public void disConnect() {
        try {
            if (con != null || !con.isClosed()) {
                con.close();
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }
//==============================================================================
//
//    public boolean insert(String tableName, Map<String, Object> columnNameAndValues) {
//        String SQL = jjDatabase.insert(tableName, columnNameAndValues);
//        return otherStatement(SQL);
//    }
////==============================================================================
//
//    public boolean update(String tableName, Map<String, Object> columnNameAndValues, String WHERE) {
//        String SQL = jjDatabase.update(tableName, columnNameAndValues, WHERE);
//        return otherStatement(SQL);
//    }
////==============================================================================
//
//    public boolean delete(String tableName, String WHERE) {
//        String SQL = jjDatabase.delete(tableName, WHERE);
//        return otherStatement(SQL);
//    }
////==============================================================================
//
//    public DefaultTableModel Select(String tableName, String whichField, String WHERE) {
//        String SQL = jjDatabase.select(tableName, whichField, WHERE);
//        return otherSelect(SQL);
//    }
//==============================================================================

    public boolean otherStatement(String query_insert_update_delete) {
        boolean b = true;
        try {
            Connect();
            b = con.createStatement().executeUpdate(query_insert_update_delete) > 0 ? true : false;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        } finally {
            if (Setting.IS_Host) {
                disConnect();
            }
        }
        return b;
    }
//==============================================================================

    public DefaultTableModel otherSelect(String query_select_join) {
        try {
            Connect();
            Vector rows, row;
            resultSet = con.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            rows = new Vector();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
            }
            int rowIndex2 = 0;
            while (resultSet.next()) {
                row = new Vector();
                for (int i = 0; i < vColumns.size(); i++) {
//                        byte[] bytes = resultSet.getBytes(i + 1);
//                        row.addElement(new String(bytes, "windows-1256"));
                    row.addElement(resultSet.getString(i + 1).replace("?", "ی"));
                }
                rows.addElement(row);
                rowIndex2++;
            }
            return new DefaultTableModel(rows, vColumns);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        } finally {
            if (Setting.IS_Host) {
                disConnect();
            }
        }
    }

    public boolean IsDatabaseExists(String databaseName) {
        try {
//            Connect();
//            String SQL = "SELECT * FROM `information_schema`.`SCHEMATA` "
//                    + "WHERE schema_name='" + databaseName + "';";
//            return otherSelect(SQL).getRowCount() > 0;
            return false;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        } finally {
            if (Setting.IS_Host) {
                disConnect();
            }
        }
    }
}
