/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milad
 */
public class jjDbAccess {

    static String databasePath;
    static String userName;
    static String password;
    Connection con;
    ResultSet resultSet;

    public jjDbAccess(String databasePath, String userName, String password) {
        this.databasePath = databasePath == null ? "" : databasePath;
        if (IsDatabaseExists()) {
            return;
        }
        this.userName = userName == null ? "" : userName;
        this.password = password == null ? "" : password;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        } catch (Exception ex) {
            jjError.Handler(ex, "فایل های کتابخانه اتصال به دیتابیس در دسترس نمی باشد.");
            return;
        }
    }
//==============================================================================

    public void Connect() {
        try {
            if (con == null || con.isClosed()) {
                Properties p = new Properties();
                p.put("charSet", "utf-8");
                p.put("lc_ctype", "utf-8");
                p.put("encoding", "utf-8");
                String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + databasePath + ";characterEncoding=UTF-8;";
                if (new File(databasePath).getName().toUpperCase().endsWith(".accdb")) {
                    url = "jdbc:odbc:Driver={Microsoft Access Driver " + "(*.mdb, *.accdb)};DBQ=" + databasePath + ";";
                }
                con = DriverManager.getConnection(url, userName, password);
                //con = DriverManager.getConnection(url, p);
            }
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
//==============================================================================

//    public boolean insert(String tableName, Map<String, Object> columnNameAndValues) {
//        String SQL = jjDatabase.insert(tableName, columnNameAndValues);
//        return otherStatement(SQL);
//    }
//==============================================================================
//    public boolean update(String tableName, Map<String, Object> columnNameAndValues, String WHERE) {
//        String SQL = jjDatabase.update(tableName, columnNameAndValues, WHERE);
//        return otherStatement(SQL);
//    }
//==============================================================================
//    public boolean delete(String tableName, String WHERE) {
//        String SQL = jjDatabase.delete(tableName, WHERE);
//        return otherStatement(SQL);
//    }
//==============================================================================
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

    public boolean IsDatabaseExists() {
        if (!new File(this.databasePath).exists()) {
            ServerLog.Print("database is not exists in " + databasePath);
            return false;
        }
        return true;
    }

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
//    public class  {
//    private static DBAccessor accessor;
    /**
     * @param args the command line arguments
     */
    public static void ExportPicFromAccess(String databasePath, String userName, String password, String tableName, String idFieldName, String picFieldName, File exportFolderLocation, String prefix) {
        String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + databasePath;
        try {
            ServerLog.Print(password);
            Connection connection = DriverManager.getConnection(myDB, userName, password);
            PreparedStatement ps = connection.prepareStatement(
                    "select * from " + tableName);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                InputStream in = result.getBinaryStream(picFieldName);
                String no = result.getString(idFieldName);
                exportFolderLocation.mkdirs();
                String address = exportFolderLocation.getAbsolutePath() + "/" + prefix + "_" + no + ".jpg";
                OutputStream out = new FileOutputStream(address);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                if (new File(address).length() == 0) {
                    new File(address).delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
//    }
//==============================================================================
}// end of class Access

