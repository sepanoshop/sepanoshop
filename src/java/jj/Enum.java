/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author Milad
 */
public class Enum {

    public static enum Dir {

        RIGHT, LEFT, Center
    }

    public static enum ColumnWidth {

        LAST_CHANGE, DEFUALT_SETTING
    }

    public static enum DatabaseType {

        Access, File, Excel, MySql, SQL_Server, Postgre, Oracle, Xml, HSql
    }

    public static enum DatabaseFieldType {

        Varchar_11, //Date,
        Varchar_255,
        Short,
        String,
        Integer,
        BigInteger,
        Double,
        Boolean,
        BigDecimal,
        Byte,
        Long
    }

    public static enum IdCounter {

        MANUAL, AUTO
    }

    public static enum Mode {

        Full, TabStyle, InsertStyle, Select
    }

    public static enum FileDialogType {

        SaveFile, SaveFolder, OpenFile, OpenFolder
    }
    public static enum DateDefaultType {

        Now, Manual
    }
}
