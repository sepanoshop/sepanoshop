/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.io.File;
import jj.Enum.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milad
 */
public class jjDatabase {

    private static DatabaseType dbType = DatabaseType.Access;
    private static String url;
    private static String databasePath;
    private static String userName;
    private static String password;
    private static Connection con;
    private static Connection con2;
    private static ResultSet resultSet;
    private static String serverHost = "127.0.0.1"; //localhost";
    private static String port = "3306";
    private static String databaseName = "db";
    static String url2;

    private jjDatabase() {
    }

    public static jjDatabase Db_Access(String userName, String password, String databasePath) {
        jjDatabase database = new jjDatabase();
        try {
            jjDatabase.dbType = DatabaseType.Access;
            jjDatabase.databasePath = databasePath;
            if (!new File(jjDatabase.databasePath).exists()) {
                ServerLog.Print("database is not exists in " + databasePath);
                return null;
            }
            jjDatabase.userName = userName == null ? "" : userName;
            jjDatabase.password = password == null ? "" : password;
            boolean isExtensionACCDB = new File(databasePath).getName().toLowerCase().endsWith(".accdb");
            jjDatabase.url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb" + (isExtensionACCDB ? ", *.accdb" : "") + ")};DBQ=" + databasePath + ";characterEncoding=UTF-8;";
            Properties p = new Properties();
            p.put("charSet", "utf-8");
            p.put("lc_ctype", "utf-8");
            p.put("encoding", "utf-8");
            p.put("characterEncoding", "utf-8");
            p.put("password", jjDatabase.password);
            p.put("user", jjDatabase.userName);
            con = DriverManager.getConnection(url, p);
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return database;
    }
//==============================================================================

    public static jjDatabase Db_Excel(String userName, String password, String databasePath) {
        jjDatabase database = new jjDatabase();
        try {
            database.dbType = DatabaseType.Excel;
            database.databasePath = databasePath == null ? "" : databasePath;
            if (!new File(database.databasePath).exists()) {
                ServerLog.Print("database is not exists in " + databasePath);
                return null;
            }
            database.userName = userName == null ? "" : userName;
            database.password = password == null ? "" : password;
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            database.url = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + databasePath
                    + ";characterEncoding=UTF-8";
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return database;
    }
//==============================================================================

    public static jjDatabase Db_MySQL(String userName, String password, String databaseName, String serverHostIP, String port) {
        jjDatabase database = new jjDatabase();
        try {
            database.dbType = DatabaseType.MySql;
            database.port = port;
            database.serverHost = serverHostIP;
            database.databaseName = databaseName;
            database.userName = userName;
            database.password = password;
            ServerLog.Print("Class.forName(\"com.mysql.jdbc.Driver\").newInstance();");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            database.url = "jdbc:mysql://" + database.serverHost + ":" + database.port + "/" + database.databaseName + "?characterEncoding=UTF-8";
            database.url2 = "jdbc:mysql://" + database.serverHost + ":" + database.port + "/information_schema" + "?characterEncoding=UTF-8";
            if (!IsDbExists(databaseName)) {
                ServerLog.Print("database is not exists in " + databasePath);
                return null;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return database;
    }

    public static boolean IsDbExists(String databaseName) {
        try {
            if (dbType != DatabaseType.MySql) {
                return false;
            }
            Connect2();
            String SQL = "SELECT * FROM `information_schema`.`SCHEMATA` WHERE schema_name='" + databaseName + "';";
            return con2.createStatement().executeQuery(SQL).next();
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        } finally {
            if (Setting.IS_Host) {
                disConnect2();
            }
        }
    }

    public static boolean createDatabase(String databaseName, DatabaseType dbType, String userName, String password, String serverHost, String port) {
        boolean b = false;
        try {
            if (dbType == DatabaseType.MySql) {
                String SQL1 = "CREATE DATABASE IF NOT EXISTS " + databaseName + ";";
                ServerLog.Print(SQL1);
                String url2 = "jdbc:mysql://" + serverHost + ":" + port + "/information_schema";
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con2 = DriverManager.getConnection(url2, userName, password);
                b = con2.createStatement().executeUpdate(SQL1) > 0;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return b;
    }

    public static boolean createTable(String tableName, String idName) {
        boolean b = false;
        try {
            if (dbType == DatabaseType.MySql) {
                String SQL1 = "SELECT * FROM `information_schema`.`TABLES` WHERE TABLE_SCHEMA='" + databaseName + "' AND TABLE_NAME='" + tableName + "';";
                ServerLog.Print(SQL1);
                Connect2();
                if (!con2.createStatement().executeQuery(SQL1).next()) {
                    Connect();
                    String SQL2 = "CREATE TABLE `" + databaseName + "`.`" + tableName + "` (`" + idName
                            + "` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,PRIMARY KEY(`" + idName
                            + "`))ENGINE = InnoDB;";
                    ServerLog.Print(SQL2);
                    b = con.createStatement().executeUpdate(SQL2) > 0;
                    disConnect();
                };
                disConnect2();
            } else if (dbType == DatabaseType.Access) {
                ServerLog.Print(databasePath);
                boolean isExtensionACCDB = new File(databasePath).getName().toLowerCase().endsWith(".accdb");
                String url1 = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb" + (isExtensionACCDB ? ", *.accdb" : "")
                        + ")};DBQ=" + databasePath + ";characterEncoding=UTF-8;";
                Connection con1 = DriverManager.getConnection(url1, userName, password);
                //CREATE TABLE table1(id1 AUTOINCREMENT, name1 TEXT(50), CONSTRAINT table1_PK PRIMARY KEY(id1));
                //CREATE TABLE table2(id2 AUTOINCREMENT, id1 INT NOT NULL, name2 TEXT(100) NOT NULL, 
                //CONSTRAINT table2_FK FOREIGN KEY (id1) REFERENCES table1 (id1), 
                //CONSTRAINT table2_PK PRIMARY KEY(id2)); 
                String SQL2 = "CREATE TABLE " + tableName + "(" + idName + " AUTOINCREMENT, CONSTRAINT " + tableName + "_PK PRIMARY KEY("
                        + idName + "));";
                ServerLog.Print(SQL2);
                b = con1.createStatement().executeUpdate(SQL2) > 0;
            } else {
                return false;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return b;
    }

    public static boolean dropColumn(String tableName, String columnName) {
        boolean b = false;
        try {
            if (dbType == DatabaseType.MySql) {
                String SQL1 = "ALTER TABLE `" + tableName + "` DROP COLUMN `" + columnName + "`;";
                ServerLog.Print(SQL1);
                Connect();
                b = con.createStatement().executeUpdate(SQL1) > 0;
                disConnect();
            } else if (dbType == DatabaseType.Access) {
            } else {
                return false;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return b;
    }

    public static List<String> getColumnList(String tableName) {
        List<String> colName = new ArrayList<String>();
        try {
            if (dbType == DatabaseType.MySql) {
                String SQL1 = "SELECT COLUMN_NAME FROM `information_schema`.`COLUMNS` WHERE TABLE_NAME='" + tableName + "';";
                ServerLog.Print(SQL1);
                DefaultTableModel otherSelect = jjDatabase.otherSelect(SQL1);
                for (int i = 0; i < otherSelect.getRowCount(); i++) {
                    colName.add(otherSelect.getValueAt(i, 0).toString());
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return colName;
        }
        return colName;
    }

    public static boolean isColumnExist(String tableName, String columnName) {
        boolean b = false;
        try {
            if (dbType == DatabaseType.MySql) {
                Connect2();
                String SQL1 = "SELECT * FROM `information_schema`.`COLUMNS` WHERE TABLE_NAME='" + tableName + "' AND COLUMN_NAME='" + columnName + "';";
                ServerLog.Print(SQL1);
                b = con2.createStatement().executeQuery(SQL1).next();
                disConnect2();
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return b;
    }

    public static boolean addColumnVarchar(String tableName, String columnName) {
        return addColumnVarchar(tableName, columnName, 255);
    }

    public static boolean addColumnVarchar(String tableName, String columnName, int VarcharSize) {
        boolean b = false;
        try {
            if (dbType == DatabaseType.MySql) {
                if (isColumnExist(tableName, columnName)) {
                    return false;
                }
                String SQL = "ALTER TABLE `" + databaseName + "`.`" + tableName + "` ADD COLUMN `"
                        + columnName + "` VARCHAR(" + VarcharSize + ") DEFAULT '';";
                ServerLog.Print(SQL);
                Connect();
                b = con.createStatement().executeUpdate(SQL) > 0;
            } else if (dbType == DatabaseType.Access) {
                Connect();
                String SQL = "ALTER TABLE `" + tableName + "` ADD COLUMN `" + columnName + "` TEXT(" + VarcharSize + ");";
                ServerLog.Print(SQL);
                b = con.createStatement().executeUpdate(SQL) > 0;
                disConnect();
            } else {
                return false;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return b;
    }

    public static boolean addColumnInteger(String tableName, String columnName) {
        if (dbType != DatabaseType.MySql) {
            return false;
        }
        if (isColumnExist(tableName, columnName)) {
            return false;
        }
        boolean b = false;
        try {
            String SQL = "ALTER TABLE `" + databaseName + "`.`" + tableName + "` ADD COLUMN `"
                    + columnName + "` INTEGER UNSIGNED;";
            ServerLog.Print(SQL);
            Connect();
            b = con.createStatement().executeUpdate(SQL) > 0;
            disConnect();
            return b;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
    }

    public static boolean addColumnBoolean(String tableName, String columnName) {
        if (dbType != DatabaseType.MySql) {
            return false;
        }
        if (isColumnExist(tableName, columnName)) {
            return false;
        }
        boolean b = false;
        try {
            String SQL = "ALTER TABLE `" + databaseName + "`.`" + tableName + "` ADD COLUMN `"
                    + columnName + "` BOOLEAN ;";
            ServerLog.Print(SQL);
            Connect();
            b = con.createStatement().executeUpdate(SQL) > 0;
            disConnect();
            return b;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
    }

    /**
     *
     * @param databaseName of mySql
     * @param userName of mySql
     * @param password of mySql
     * @param backupPath is a file path that write backup in that
     */
    public static void getMySQLBackup(String userName, String password, String databaseName, File backupPath) {
        try {
            Runtime.getRuntime().exec(
                    "\"" + Setting.getMySqlBackuper() + "\" -u "
                    + userName + " -p" + password + " " + databaseName + " -r "
                    + "\"" + backupPath.getAbsolutePath() + "\"");
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }
//==============================================================================

    public static jjDatabase Db_HSQL(String userName, String password, String databaseName, String serverHostIP, String port) {
        jjDatabase database = new jjDatabase();
        try {
            database.dbType = DatabaseType.HSql;
            database.port = port;
            database.serverHost = serverHostIP;
            database.databaseName = databaseName;
            database.userName = userName;
            database.password = password;
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            database.url = "jdbc:hsqldb:hsql://" + database.serverHost + ":" + database.port + "/" + database.databaseName;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return database;
    }
//==============================================================================

    public static jjDatabase Db_File(String databaseName) {
        jjDatabase database = new jjDatabase();
        try {
            database.dbType = DatabaseType.File;
            database.databasePath = jjOsInfo.getProgramAddress() + "\\Setting\\Database\\" + databaseName + "\\Table's";
            new File(database.databasePath).mkdirs();
            database.databaseName = databaseName;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return database;
    }
//==============================================================================

    public static void Connect() {
        try {
            if (con == null || con.isClosed()) {
                if (dbType != DatabaseType.File) {
                    con = DriverManager.getConnection(url, userName, password);
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }

    public static void Connect2() {
        try {
            if (con2 == null || con2.isClosed()) {
                if (dbType != DatabaseType.File) {
                    con2 = DriverManager.getConnection(url2, userName, password);
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }
//==============================================================================

    public static void disConnect() {
        try {
            if (con != null || !con.isClosed()) {
                con.close();
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }

    public static void disConnect2() {
        try {
            if (con2 != null || !con2.isClosed()) {
                con2.close();
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }
//==============================================================================

    public static DefaultTableModel insert(String tableName, Map<String, Object> columnNameAndValues) {
        try {
            if (dbType == DatabaseType.File) {
                int counterFromFile = getCounterFromFile(databasePath + "\\" + tableName + "\\Counter ID.dll");
                File rowFile = new File(databasePath + "\\" + tableName + "\\row" + "-" + counterFromFile + ".txt");
                jjFileTxt.writeKeyValue(rowFile, columnNameAndValues);
            } else {
                String SQL = jjDatabaseQuery.insert(tableName, columnNameAndValues);
                if (otherStatement(SQL)) {
                    DefaultTableModel otherSelect = otherSelect(jjDatabaseQuery.max(tableName, "id"));
                    if (otherSelect == null) {
                        return Select(tableName, "id=" + 1);
                    }
                    if (otherSelect.getRowCount() > 0) {
                        return Select(tableName, "id=" + otherSelect.getValueAt(0, 0).toString());
                    } else {
                        return Select(tableName, "id=" + 1);
                    }
                };
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return null;
    }

//==============================================================================
    public static boolean update(String tableName, Map<String, Object> columnNameAndValues, String WHERE) {
        try {
            if (dbType == DatabaseType.File) {
                File rowFile = new File(databasePath + "\\" + tableName + "\\row" + "-" + WHERE + ".txt");
                if (!rowFile.exists()) {
                    return false;
                }
                Map<String, Object> readKeyValue = jjFileTxt.readKeyValue(rowFile);
                readKeyValue.putAll(readKeyValue);
                jjFileTxt.writeKeyValue(rowFile, readKeyValue);
            } else {
                String SQL = jjDatabaseQuery.update(tableName, columnNameAndValues, WHERE);
                return otherStatement(SQL);
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return true;
    }
//==============================================================================

    public static boolean delete(String tableName, String WHERE) {
        try {
            if (dbType == DatabaseType.File) {
                File rowFile = new File(databasePath + "\\" + tableName + "\\row" + "-" + WHERE + ".txt");
                if (!rowFile.exists()) {
                    return false;
                }
                jjFileTxt.write(rowFile, "");
                jjFile.delete(rowFile);
            } else {
                String SQL = jjDatabaseQuery.delete(tableName, WHERE);
                return otherStatement(SQL);
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return true;
    }
//==============================================================================

    public static int count(String tableName) {
        String SQL = jjDatabaseQuery.count(tableName);
        DefaultTableModel dtm = otherSelect(SQL);
        if (dtm.getRowCount() > 0) {
            return Integer.parseInt(dtm.getValueAt(0, 0).toString());
        }
        return 0;
    }

    public static DefaultTableModel SelectAll(String tableName) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, "", "");
            return otherSelect(SQL);
        }
    }

    public static DefaultTableModel SelectAll(String tableName, String whichField) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, whichField, "");
            return otherSelect(SQL);
        }
    }

    public static DefaultTableModel SelectAll(String tableName, String whichField, int everyPage, int pageNo) {
        String SQL = jjDatabaseQuery.select(tableName, whichField, "");
        return otherSelect(SQL, everyPage, pageNo);
    }

    public static DefaultTableModel Select(String tableName, String WHERE) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, "", WHERE);
            return otherSelect(SQL);
        }
    }

    public static DefaultTableModel Select(String tableName, String whichField, String WHERE) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, whichField, WHERE);
            return otherSelect(SQL);
        }
    }
//==============================================================================

    public static DefaultTableModel SelectFromFile(String tableName) {
        Vector<String> vColumns = new Vector<String>();
        Vector rows, row;
        rows = new Vector();
        try {
            List<File> fileList = jjFile.getAllFileList(databasePath + "\\" + tableName, ".txt", "row-");
            List<List<String[]>> l = new ArrayList<List<String[]>>();
            for (int i = 0; i < fileList.size(); i++) {
                List<String> readLine = jjFileTxt.readLine(fileList.get(i));
                List<String[]> keyValue = jjFileTxt.getKeyValue(readLine);
                l.add(keyValue);
                row = new Vector();
                for (int j = 0; j < keyValue.size(); j++) {
                    if (!vColumns.contains(keyValue.get(j)[0])) {
                        vColumns.addElement(keyValue.get(j)[0]);
                    }
                }
            }
            for (int i = 0; i < l.size(); i++) {
                row = new Vector();
                for (int j = 0; j < vColumns.size(); j++) {
                    row.add("");
                }
                rows.addElement(row);
            }
            DefaultTableModel dtm = new DefaultTableModel(rows, vColumns);
            for (int i = 0; i < l.size(); i++) {
                for (int j = 0; j < l.get(i).size(); j++) {
                    dtm.setValueAt(l.get(i).get(j)[1], i, getColumnNumber(dtm, l.get(i).get(j)[0]));
                }
            }
            return dtm;
        } catch (Exception ex) {
            ex.printStackTrace();
            jjError.Handler(ex);
            return new DefaultTableModel();
        }
    }

    public static int getColumnNumber(JTable tbl, String ColumnName) {
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            if (tbl.getColumnName(i).equals(ColumnName)) {
                return i;
            }
        }
        return -1;
    }

    public static int getColumnNumber(DefaultTableModel dtm, String ColumnName) {
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            if (dtm.getColumnName(i).equals(ColumnName)) {
                return i;
            }
        }
        return -1;
    }

    public static List<Map<String, Object>> separateRow(DefaultTableModel dtm) {
        java.util.List<Map<String, Object>> listFromMap = new java.util.ArrayList<Map<String, Object>>();
        if (dtm == null) {
            return listFromMap;
        }
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                map.put(dtm.getColumnName(j), dtm.getValueAt(i, j));
            }
            listFromMap.add(map);
        }
        return listFromMap;
    }


    public static boolean otherStatement(String query_insert_update_delete) {
        boolean b = true;
        try {
            Connect();
            ServerLog.Print(query_insert_update_delete);
            if (dbType == DatabaseType.Excel) {// if excel is not open
                PreparedStatement prepareStatement = con.prepareStatement(query_insert_update_delete);
                prepareStatement.executeUpdate();
            } else {
                b = con.createStatement().executeUpdate(query_insert_update_delete) > 0;
            }
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

    public static DefaultTableModel otherSelect(String query_select_join) {
        try {
            ServerLog.Print(query_select_join);
            Connect();
            Vector rows, row;
            resultSet = con.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            rows = new Vector();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
            }
            while (resultSet.next()) {
                row = new Vector();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    if (metaData.getColumnType(i + 1) == 4) {
                        String string = resultSet.getString(i + 1);
                        string = string == null ? "" : string;
                        row.addElement(jjNumber.isDigit(string) ? new Integer(string) : -1);
                    } else if (metaData.getColumnType(i + 1) == 12) {
                        byte[] bytes = resultSet.getBytes(i + 1);
                        if (bytes != null) {
                            row.addElement(new String(bytes, "windows-1256"));
                        } else {
                            row.addElement("");
                        }
//                    } else if (metaData.getColumnType(i + 1) == 93) {
//                        row.addElement(resultSet.getDate(i + 1));
                    } else {
                        String string = resultSet.getString(i + 1);
                        row.addElement(string == null ? "" : string);
                    }
                }
                if (row.size() > 0) {
                    rows.addElement(row);
                }
            }
            return new DefaultTableModel(rows, vColumns);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return new DefaultTableModel();
        } finally {
            if (Setting.IS_Host) {
                disConnect();
            }
        }
    }

    public static DefaultTableModel otherSelect2(String query_select_join) {
        try {
            ServerLog.Print(query_select_join);
            Connect2();
            Vector rows, row;
            resultSet = con2.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            rows = new Vector();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
            }
            while (resultSet.next()) {
                row = new Vector();
                for (int i = 0; i < vColumns.size(); i++) {
                    // byte[] bytes = resultSet.getBytes(i + 1);
                    // row.addElement(new String(bytes, "windows-1256"));
                    // row.addElement(resultSet.getString(i + 1).replace("?", "ی"));
                    String string = resultSet.getString(i + 1);
                    row.addElement(string == null ? "" : string);
                }
                rows.addElement(row);
            }
            return new DefaultTableModel(rows, vColumns);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return new DefaultTableModel();
        } finally {
            if (Setting.IS_Host) {
                disConnect2();
            }
        }
    }

    public static DefaultTableModel otherSelect(String query_select_join, int everyPageRowNo, int whitchPage) {
        try {
            Connect();
            Vector rows, row;
            ServerLog.Print(query_select_join);
            resultSet = con.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            rows = new Vector();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
            }
            int j = 0;
            while (resultSet.next()) {
                row = new Vector();
                for (int i = 0; i < vColumns.size(); i++) {
                    String string = resultSet.getString(i + 1);
                    row.addElement(string == null ? "" : string);
                }
                if (whitchPage * everyPageRowNo <= j && (whitchPage + 1) * everyPageRowNo > j) {
                    rows.addElement(row);
                }
                j += 1;
            }
            return new DefaultTableModel(rows, vColumns);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return new DefaultTableModel();
        } finally {
            if (Setting.IS_Host) {
                disConnect();
            }
        }
    }
//==============================================================================

    /**
     *
     * get counter for Id of Tables
     */
    public static int getCounterFromFile(String counterPath) {
        try {
            File f = new File(counterPath);
            int ID = 1001;
            if (f.exists()) {
                String num = jjFileTxt.read(f);
                if (jjNumber.isDigit(num.trim())) {
                    ID = Integer.parseInt(num.trim());
                }
                jjFileTxt.write(f, "" + (ID + 1), false, false);
                return ID;
            } else {
                jjFileTxt.write(f, "" + (ID + 1), false, false);
                return ID;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return 0;
        }
    }

    /**
     *
     * get counter for Id of Tables
     */
    public static void createEntity(String userName, String password, String databaseName, String serverHostIP, String port) {
        jjDatabase db = jjDatabase.Db_MySQL(userName, password, "information_schema", serverHostIP, port);
        List<Map<String, Object>> separateRow = jjDatabase.separateRow(db.Select("`TABLES`", "`TABLE_SCHEMA`='" + databaseName + "'"));
        File f = new File(jjOsInfo.getProgramAddress() + "\\src\\java\\entity" + databaseName);
        f.mkdirs();
        for (int i = 0; i < separateRow.size(); i++) {
            List<String> l = new ArrayList<String>();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select("`COLUMNS`", "`TABLE_SCHEMA`='db_golesorkh_01' AND `table_name`='" + separateRow.get(i).get("TABLE_NAME") + "'"));
            l.add("package entity" + databaseName + ";");
            l.add("public class " + separateRow.get(i).get("TABLE_NAME") + "{");
            l.add("public static String tableName = \"" + separateRow.get(i).get("TABLE_NAME") + "\";");
            for (int j = 0; j < row.size(); j++) {
                l.add("public static String " + row.get(j).get("COLUMN_NAME") + " = \"" + row.get(j).get("COLUMN_NAME") + "\";");
            }
            l.add("}");
            jjFileTxt.writeLine(f.getAbsolutePath() + "\\" + separateRow.get(i).get("TABLE_NAME") + ".java", l, false, false);
        }
    }
}
