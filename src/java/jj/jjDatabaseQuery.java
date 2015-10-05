package jj;

import cms.tools.ServerLog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author Milad Jamalzadeh 09132686053
 */
public class jjDatabaseQuery {

    //------------------------------------------------------------------------------
    /**
     * insert to tbl Map<String, Object> params = new HashMap();
     * params.put("columnName", "Value");
     *
     */
    public static String insert(String tableName, Map<String, Object> columnNameAndValues) {
        Object obj[] = columnNameAndValues.keySet().toArray();
        String fieldName = "";
        String value = "";
        for (int i = 0; i < obj.length; i++) {
            fieldName += (fieldName.equals("") ? "" : ",") + obj[i].toString();
            boolean isText = columnNameAndValues.get(obj[i]).getClass().isInstance("String");
//            boolean isText = columnNameAndValues.get(obj[i]).getClass().toString().endsWith("String");
            String val = columnNameAndValues.get(obj[i]) == null ? "" : columnNameAndValues.get(obj[i]).toString();
            val = htmlToStatement(val);
            value += (value.equals("") ? "" : ",") + (isText ? ("'" + val + "'") : val);
        }
        String SQL = "INSERT INTO " + tableName + " (" + fieldName + ") VALUES (" + value + ");";
        return SQL;
    }

    public static String htmlToStatement(String str) {

//        StringBuffer sb = new StringBuffer();
//        int len = str.length();
//        char c;
//        for (int i = 0; i < len; i++) {
//            c = str.charAt(i);
//            switch (c) {
//                case '&':
//                    sb.append("!and!");
//                    break;
//                case '<':
//                    sb.append("!small!");
//                    break;
//                case '>':
//                    sb.append("!big!");
//                    break;
//                case '"':
//                    sb.append("!2quot!");
//                    break;
//                case '\'':
//                    sb.append("!1quot!");
//                    break;
//                default:
//                    sb.append(c);
//                    break;
//            }
//        }
//
//        return sb.toString();
//        return str.replace("'", "''");
//        return str.replace("\\", "/");
//        return str.replace("\\", "/").replace("'", "\\'").replace("\n", "").replace("!and!", "&").replace("!dr!", "%").replace("!small!", "<").replace("!big!", ">");
        return str.replace("\\", "/").replace("'", "\\'").replace("\n", "").replace("\r\n", "");
    }

    public static String statementToHtml(String src) {
//        src = src.replace("!1quot!", "'");
//        src = src.replace("!2quot!", "\\\"");
//        src = src.replace("!and!", "&");
//        src = src.replace("!dr!", "%");
//        src = src.replace("\"", "");
//        src = src.replace("'", "\'");
//        src = src.replace("\'", "\\'");
//        src = src.replace("\"", "\\\"");
        src = src.replace("ي", "ی");
        return src.replace("\n", "");
    }

    public static String MapToString(Map<String, Object> map) {
        String keyValue = "";
        Object obj[] = map.keySet().toArray();
        for (int i = 0; i < obj.length; i++) {
            boolean isText = map.get(obj[i]).getClass().toString().endsWith("String");
            keyValue += (keyValue.equals("") ? "" : ",") + obj[i].toString() + "="
                    + (isText ? ("'" + htmlToStatement(map.get(obj[i]).toString()) + "'") : htmlToStatement(map.get(obj[i]).toString()));
        }
        return keyValue;
    }

    public static String update(String tableName, Map<String, Object> columnNameAndValues, String WHERE) {
        String set = MapToString(columnNameAndValues);
        String SQL = "UPDATE " + tableName + " SET " + set + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String delete(String tableName, String WHERE) {
        String SQL = "DELETE FROM " + tableName + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String selectAll(String tableName) {
        String SQL = "SELECT * FROM " + tableName + "  ;";
        return SQL;
    }

    public static String selectAll(String tableName, String whichField) {
        String SQL = "SELECT " + (whichField.equals("") ? "*" : whichField) + " FROM " + tableName + " ;";
        return SQL;
    }

    public static String select(String tableName, String WHERE) {
        String SQL = "SELECT * FROM " + tableName + " " + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String select(String tableName, String whichField, String WHERE) {
        whichField = whichField == null ? "" : whichField;
        WHERE = WHERE == null ? "" : WHERE;
        String SQL = "SELECT " + (whichField.equals("") ? "*" : whichField)
                + " FROM " + tableName + " "
                + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String selectDistinct(String tableName, String columns) {//add by Mohammad
        String SQL = "SELECT DISTINCT " + columns + " FROM " + tableName + ";";
        return SQL;
    }

    public static String selectDESC(String tableName, String WHERE, String DESC_sort_column) {//add by Mohammad
        String SQL = "SELECT * FROM " + tableName + ((WHERE.equals("") ? "" : (" WHERE " + WHERE)) + " ORDER BY " + DESC_sort_column + " DESC;");
        return SQL;
    }

    public static String selectDESC(String tableName, String whichField, String WHERE, String DESC_sort_column) {//add by Mohammad
        String SQL = "SELECT " + whichField + " FROM " + tableName + ((WHERE.equals("") ? "" : (" WHERE " + WHERE)) + " ORDER BY " + DESC_sort_column + " DESC;");
        return SQL;
    }

    public static String selectDESCLimit(String tableName, String whichField, String WHERE, String DESC_sort_column, String Limit) {//add by Mohammad
        String SQL = "SELECT " + whichField + " FROM " + tableName + ((WHERE.equals("") ? "" : (" WHERE " + WHERE)) + " ORDER BY " + DESC_sort_column + " DESC LIMIT " + Limit + " ;");
        return SQL;
    }
//============ BY RASHIDI ========>
//========== JOIN ====>

    public static String join(String firtTableName, String secondTableName, String whichField, String onFirstColumn, String onSecondcolumn) {
        String SQL = "SELECT " + whichField + " FROM " + firtTableName + " JOIN " + secondTableName + " ON " + firtTableName + "." + onFirstColumn + " = " + secondTableName + "." + onSecondcolumn + ";";
        return SQL;
    }

    public static String joinInner(String firtTableName, String secondTableName, String whichField, String onFirstColumn, String onSecondcolumn) {
        String SQL = "SELECT " + whichField + " FROM " + firtTableName + " INNER JOIN " + secondTableName + " ON " + firtTableName + "." + onFirstColumn + " = " + secondTableName + "." + onSecondcolumn + ";";
        return SQL;
    }

    public static String joinRight(String firtTableName, String secondTableName, String whichField, String onFirstColumn, String onSecondcolumn) {
        String SQL = "SELECT " + whichField + " FROM " + firtTableName + " RIGHT JOIN " + secondTableName + " ON " + firtTableName + "." + onFirstColumn + " = " + secondTableName + "." + onSecondcolumn + ";";
        return SQL;
    }

    public static String joinLeft(String firtTableName, String secondTableName, String whichField, String onFirstColumn, String onSecondcolumn) {
        String SQL = "SELECT " + whichField + " FROM " + firtTableName + " LEFT JOIN " + secondTableName + " ON " + firtTableName + "." + onFirstColumn + " = " + secondTableName + "." + onSecondcolumn + ";";
        return SQL;
    }
//<========== JOIN ====
//<============ BY RASHIDI ========

    public static String count(String tblName) {
        String SQL = "SELECT count(*) FROM " + tblName;
        return SQL;
    }

    public static String max(String tblName, String fieldName) {
        String SQL = "SELECT MAX(" + fieldName + ") FROM " + tblName + " ; ";
        return SQL;
    }

    public static void doImportDatabase(String databaseName, boolean DropIfExist,
            String getRelationTable[], db_Table... tbls) {
        try {
//            db_MyConnection.databaseName = databaseName;
//            db_MyConnection.isDatabaseExists();
//            Statement sCreate = db_MyConnection.testConnection.createStatement();
//            if (DropIfExist) {
//                sCreate.execute("DROP DATABASE IF EXISTS `" + databaseName + "` ;");
//                Help.doDeleteFolder(Help.getProgramAddress() + "\\src\\database");
//            }
//            sCreate.execute("CREATE DATABASE `" + databaseName + "` ;");
//            MyConnection.Connect();
            String url;
            String Data = jjFileTxt.readLine(jjOsInfo.getProgramAddress() + "\\Connection.txt").get(0);
            String[] conData = Data.split("=");
            databaseName = conData[0];
            String userName = conData[1];
            String password = conData[2];
            String port = conData[3];
            String serverHost = conData[4];
            url = "jdbc:mysql://" + serverHost + ":" + port + "/" + databaseName;
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement sCreate2 = con.createStatement();
            for (int i = 0; i < tbls.length; i++) {
                if (tbls[i].DropIfExist) {
                    sCreate2.execute("DROP TABLE IF EXISTS `" + tbls[i].tableName + "` ;");
                }
                String tableName = tbls[i].tableName;
                tableName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length()).toLowerCase();
                String s = "CREATE TABLE `" + tbls[i].tableName + "` " + tbls[i].dbCodeForRowsInTable;
                ServerLog.Print(s);
                sCreate2.execute(s);

            }
            for (int i = 0; i < getRelationTable.length; i++) {
                getRelationTable[i] = getRelationTable[i].replace("databaseName", databaseName);
                sCreate2.execute(getRelationTable[i]);
            }
//            sCreate.close();
//            MyConnection.Disconnect();
        } catch (Exception ex) {
//            jjDialog.dialog_ERROR_MESSAGE(ex, null, "doImportDatabase()", ex.getMessage());
        }
    }

    public static String getTableRelationCode(String relationName,
            db_Table tableWithForeign,
            db_Row ForeignRow,
            db_Table tableWithPrimary,
            db_Row primaryRrow) {

        return "ALTER TABLE `databaseName`.`" + tableWithForeign.tableName
                + "` MODIFY COLUMN `" + ForeignRow.RowName
                + "` INTEGER  ,"//INTEGER NOT NULL DEFAULT 0
                + " ADD CONSTRAINT `" + relationName + "` FOREIGN KEY `" + relationName
                + "` (`" + ForeignRow.RowName + "`)"
                + " REFERENCES `" + tableWithPrimary.tableName + "` (`" + primaryRrow.RowName + "`)"
                + " ON DELETE RESTRICT"
                + " ON UPDATE RESTRICT";

    }

    public static class db_Table {

        public String tableName;
        public boolean DropIfExist;
        public String dbCodeForRowsInTable = "";
        public String tableRowsInClass = "";

        public db_Table(String tableName, boolean DropIfExist, db_Row... row) {
            this.tableName = tableName;
            this.DropIfExist = DropIfExist;
            tableName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length()).toLowerCase();
            // ==================================================================
            String PRIMARYs = "";
            int counter = 0;
            for (int i = 0; i < row.length; i++) {
                if (row[i].isPRIMARY) {
                    if (counter == 0) {
                        PRIMARYs = "PRIMARY KEY  (`" + row[i].RowName + "`";
                        counter++;
                    } else {
                        PRIMARYs += ",`" + row[i].RowName + "`";
                    }
                }
            }
            PRIMARYs += ")";
            // ==================================================================
            String lineSep = jjOsInfo.getLineSeparator();
            tableRowsInClass += "    static DefaultTableModel dtm;" + lineSep;
            tableRowsInClass += "    static boolean isDtmChange = true;" + lineSep;
            tableRowsInClass += "    static int maxID;" + lineSep;
            tableRowsInClass += "    public static Vector<tbl_" + tableName + "> v" + tableName + " = new Vector<tbl_" + tableName + ">();" + lineSep;
            String selectAll = "    public static Vector<tbl_" + tableName + "> SelectAll(String where) {" + lineSep;
            tableRowsInClass += "    static String tblName = \"`" + tableName + "`\";" + lineSep;
            selectAll += "        if (isDtmChange) {" + lineSep
                    + "            v" + tableName + ".clear();" + lineSep
                    + "            String trueSrc = \"SELECT * FROM \" + tblName + \" ; \";" + lineSep
                    + "            String query = where == null ? trueSrc : \"SELECT * FROM \" + tblName + \" WHERE \" + where + \" ; \";" + lineSep
                    + "            dtm = select(query);" + lineSep
                    + "            if (dtm != null) {" + lineSep
                    + "                for (int i = 0; i < dtm.getRowCount(); i++) {" + lineSep;
            String selectAll2 = "                    v" + tableName + ".add(new tbl_" + tableName + "(" + lineSep;
            String selectAll3 = "                }" + lineSep;
            selectAll3 += "            }" + lineSep;
            selectAll3 += "            dtm = null;" + lineSep;
            selectAll3 += "            isDtmChange = false;" + lineSep;
            selectAll3 += "        }" + lineSep;
            selectAll3 += "        return v" + tableName + ";" + lineSep;
            selectAll3 += "    }" + lineSep + lineSep;
            String insert1 = "    public static boolean Insert(" + lineSep;
            String insert2 = "";
            String insert3 = "";
            String insert4 = "";
            String insert5 = "";
            String insert6 = "                + \" ); \";" + lineSep;
            insert6 += "        isDtmChange = true;" + lineSep;
            insert6 += "        return execute(query);" + lineSep + "    }" + lineSep + lineSep;
            for (int i = 0; i < row.length; i++) {
                if (i == 0) {
                    insert3 += "        String id1 = " + row[i].RowName + " != 0 ? \"\" : My" + tableName + "." + row[i].RowName + " + \" , \";" + lineSep;
                    insert3 += "        String id2 = " + row[i].RowName + " != 0 ? \"\" : getMaxID(My" + tableName + "." + row[i].RowName + ") + \" , \";" + lineSep;
                    insert3 += "        String query = \"INSERT INTO \" + tblName + \"(\"" + lineSep;
                    insert3 += "                + id1" + lineSep;
                    dbCodeForRowsInTable = "( `" + row[i].RowName + "` " + row[i].SQL_Type + row[i].SQL_Properties + row[i].defaultValue + " , ";
                } else if (i == (row.length - 1)) {
                    dbCodeForRowsInTable += " `" + row[i].RowName + "` " + row[i].SQL_Type + row[i].SQL_Properties + row[i].defaultValue + " , "
                            + PRIMARYs + " ) ;";
                    insert4 += "                + My" + tableName + "." + row[i].RowName + lineSep;
                    insert4 += "                + \") VALUES (\"" + lineSep;
                    insert4 += "                + id2" + lineSep;
                    if (row[i].SQL_Type.toString().toLowerCase().contains("varchar")) {
                        insert5 += "                +\" '\" + " + row[i].RowName + "+ \"' \"" + lineSep;
                    } else if (row[i].SQL_Type.toString().contains("tinyint(1)")) {
                        insert5 += "                + (" + row[i].RowName + " ? 1 : 0) " + lineSep;
                    } else {
                        insert5 += "                + " + row[i].RowName + lineSep;
                    }
                } else {
                    dbCodeForRowsInTable += " `" + row[i].RowName + "` " + row[i].SQL_Type + row[i].SQL_Properties + row[i].defaultValue + " , ";
                    if (row[i].SQL_Type.toString().toLowerCase().contains("varchar")) {
                        insert5 += "                +\" '\" + " + row[i].RowName + "+ \"' \"" + " + \" ,\"" + lineSep;
                    } else if (row[i].SQL_Type.toString().contains("tinyint(1)")) {
                        insert5 += "                + (" + row[i].RowName + " ? 1 : 0) " + " + \" ,\"" + lineSep;
                    } else {
                        insert5 += "                + " + row[i].RowName + " + \" ,\"" + lineSep;
                    }

                    insert4 += "                + My" + tableName + "." + row[i].RowName + " + \" , \"" + lineSep;
                }
                tableRowsInClass += "    public static String " + row[i].RowName + " = \"`" + row[i].RowName
                        + "`\";" + lineSep;
            }
            tableRowsInClass += lineSep + "    public static class tbl_" + tableName + " {" + lineSep + lineSep;
            String constructor = lineSep + "        public " + "tbl_" + tableName + "(";
            for (int i = 0; i < row.length; i++) {
                String type = "String";
                String def = row[i].defaultValue.toString();
                String defaultVal = "";
                if (def.contains("'")) {
                    defaultVal = def.substring(def.indexOf("'") + 1, def.lastIndexOf("'"));
                }
                String name = row[i].RowName;
                if (row[i].SQL_Type.toString().contains("varchar")) {
                    type = "String ";
                    tableRowsInClass += "        public " + type + name + "=" + "\"" + defaultVal + "\"" + ";";
                    tableRowsInClass += lineSep;
                    selectAll2 += "                            String.valueOf(dtm.getValueAt(i, " + i + "))      ";
                } else if (row[i].SQL_Type.toString().contains("blob")) {
                    type = "String ";
                    selectAll2 += "                            String.valueOf(dtm.getValueAt(i, " + i + "))      ";
                    tableRowsInClass += "        public " + type + name + " = " + "\"" + defaultVal + "\"" + ";";
                    tableRowsInClass += lineSep;
                } else if (row[i].SQL_Type.toString().contains("int(11)")) {
                    type = "int ";
                    if (defaultVal.equals("")) {
                        defaultVal = "0";
                    }
                    selectAll2 += "                            Integer.parseInt(String.valueOf(dtm.getValueAt(i, " + i + ")))";
                    tableRowsInClass += "        public " + type + name + " = " + defaultVal + ";";
                    tableRowsInClass += lineSep;
                } else if (row[i].SQL_Type.toString().contains("int(10)")) {
                    type = "int ";
                    selectAll2 += "                            Integer.parseInt(String.valueOf(dtm.getValueAt(i, " + i + ")))";
                    if (defaultVal.equals("")) {
                        defaultVal = "0";
                    }
                    tableRowsInClass += "        public " + type + name + " = " + defaultVal + ";";
                    tableRowsInClass += lineSep;
                } else if (row[i].SQL_Type.toString().contains("tinyint(1)")) {
                    type = "boolean ";
                    selectAll2 += "                            Integer.parseInt(String.valueOf(dtm.getValueAt(i, " + i + ")))==0?false:true";
                    defaultVal = defaultVal.trim();
                    if (defaultVal.equals("")) {
                        defaultVal = "true";
                    } else {
                        if (defaultVal.equals("1")) {
                            defaultVal = "true";
                        } else {
                            defaultVal = "false";
                        }
                    }
                    tableRowsInClass += "        public " + type + name + " = " + defaultVal + ";";
                    tableRowsInClass += lineSep;
                }
                if (i == 0) {
                    constructor += type + name;
                    insert2 += "            " + type + name + lineSep;
                    selectAll2 += ", " + lineSep;
                } else if (i == (row.length - 1)) {
                    constructor += ", " + type + name;
                    insert2 += "            , " + type + name + " ) {" + lineSep;
                    selectAll2 += ")); " + lineSep;
                } else {
                    constructor += ", " + type + name;
                    insert2 += "            , " + type + name + lineSep;
                    selectAll2 += ", " + lineSep;
                }
            }
            // ================== constructor ================= //
            constructor += ") {" + lineSep;
            for (int i = 0; i < row.length; i++) {
                if (row[i].SQL_Type.toString().toLowerCase().contains("varchar")) {
                    constructor += "            this." + row[i].RowName + " = " + row[i].RowName + ".replace(\"'\", \"''\");" + lineSep;
                } else {
                    constructor += "            this." + row[i].RowName + " = " + row[i].RowName + ";" + lineSep;
                }
            }
            constructor += "        }" + lineSep;
            tableRowsInClass += constructor;
            tableRowsInClass += "    }" + lineSep + lineSep;
            // ================== insert ================= //
            tableRowsInClass += insert1 + insert2 + insert3 + insert4 + insert5 + insert6;
            // ================== Select ================= //
            tableRowsInClass += selectAll + selectAll2 + selectAll3;
            // ================== Delete ================= //
            tableRowsInClass += "    public static boolean Delete(String where) {" + lineSep
                    + "        isDtmChange = true;" + lineSep
                    + "        String query = \"DELETE FROM \" + tblName + \" WHERE \" + where + \" ; \";" + lineSep
                    + "        return execute(query);" + lineSep
                    + "    }" + lineSep + lineSep;
            // ================== Update ================= //
            tableRowsInClass += "    public static boolean Update(String set, String where) {" + lineSep
                    + "        isDtmChange = true;" + lineSep
                    + "        String query = \"UPDATE \" + tblName + \" SET \" + set + \" WHERE \" + where + \" ; \";" + lineSep
                    + "        return execute(query);" + lineSep
                    + "    }" + lineSep + lineSep;
            // ================== execute ================= //

            tableRowsInClass += "    public static boolean MyExecute(String query) {" + lineSep
                    + "        isDtmChange = true;" + lineSep
                    + "        return execute(query);" + lineSep
                    + "    }" + lineSep + lineSep;
            // ================== Last ID ================= //
            tableRowsInClass += "    public static int getMaxID(String idField) {" + lineSep
                    + "        if (isDtmChange) {" + lineSep
                    + "            maxID = MaxID(tblName, idField);" + lineSep
                    + "        }" + lineSep
                    + "        return maxID;" + lineSep
                    + "    }" + lineSep;
            ServerLog.Print(dbCodeForRowsInTable);
        }
    }

//    public static enum java_Type {
//        System, Windows, Windows_Classic, Metal, Nimbus, CDE_Motif, Napkin;
//    }
    public static enum db_Row_Properties {

        PrimaryKey, OutoIncreament, NotNull;
    }
//

    public static class db_Row {

        String RowName;
        Object java_Type;
        Object SQL_Type;
        Object defaultValue = "";
        String SQL_Properties = "";
        public boolean isPRIMARY = false;

        /**
         *
         * in boolean type 0 default value is false and 1 is true <br/>
         */
        public db_Row(String RowName, Object TypeInstance, Object defaultValue, db_Row_Properties... properties) {
            this.RowName = RowName;
            if (TypeInstance.getClass().toString().toUpperCase().contains("Boolean")) {
                this.SQL_Type = "tinyint(1)";
                this.java_Type = "boolean";
            } else if (TypeInstance.getClass().toString().endsWith("String")) {
                this.java_Type = "String";
                int typ = 255;
                if (TypeInstance == null) {
                    this.SQL_Type = "varchar(" + typ + ")";
                } else if (TypeInstance.toString().equals("")) {
                    this.SQL_Type = "varchar(" + typ + ")";
                } else if (jjNumber.isDigit(TypeInstance.toString())) {
                    typ = Integer.parseInt(TypeInstance.toString());
                    if (typ > 255) {
                        this.SQL_Type = "blob";
                    } else {
                        this.SQL_Type = "varchar(" + typ + ")";
                    }
                } else {
                    this.SQL_Type = "varchar(" + typ + ")";
                }
            } else if (TypeInstance.getClass().toString().endsWith("Integer")) {
                this.SQL_Type = "int(11)";
                this.java_Type = "int";
            } else if (TypeInstance.getClass().toString().endsWith("Date")) {
                this.java_Type = "Help.MyDate";
                this.SQL_Type = "int(11)";
            }
            for (int i = 0; i < properties.length; i++) {
                if (properties[i] == db_Row_Properties.PrimaryKey) {
                    this.SQL_Properties += " PRIMARY KEY";
                    isPRIMARY = true;
                } else if (properties[i] == db_Row_Properties.NotNull) {
                    this.SQL_Properties += " NOT NULL";
                } else if (properties[i] == db_Row_Properties.OutoIncreament) {
                    this.SQL_Properties += " auto_increment";
                }
            }
            if (defaultValue == null || defaultValue.toString().equals("")) {
                this.defaultValue = "";
            } else if (defaultValue.getClass().toString().endsWith("Boolean")) {
                this.defaultValue = "";

            }
            this.defaultValue = " default '" + defaultValue.toString() + "'";
        }
    }
}
