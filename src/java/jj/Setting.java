/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author Milad
 */
public class Setting {
// btn cbo chk lbl lst mnu rdb pic txt

    public static String getSettingFileAddress() {
        return jjOsInfo.getProgramAddress() + "\\Setting";
    }

    /**
     * jjError class write exception in this file <br/>
     * if this file be null class don't write exception to file
     */
    public static String getWriteHandlerFileAddress() {
        return getSettingFileAddress() + "/Errors";
    }

    public static String getMySqlBackuper() {
        return getSettingFileAddress() + "/Backup/MySqlBackuper.exe";
    }
    /**
     * if is true all setting for font and others set for Persian 
     */
    private static boolean languagePersian = true;

    public static boolean isLanguagePersian() {
        return languagePersian;
    }

    public static void setLanguagePersian(boolean IsLanguagePersian) {
        Setting.setDirRtl(true);
        Setting.languagePersian = IsLanguagePersian;
    }
    private static boolean dirRtl = true;

    public static boolean isDirRtl() {
        if (isLanguagePersian()) {
            return true;
        }
        return dirRtl;
    }

    public static void setDirRtl(boolean dirRtl) {
        Setting.dirRtl = dirRtl;
    }
    /**
     * if is true Program is web and if is false program is application
     * in true style in database class disConnect() after every statement
     */
    public static final boolean IS_Host = false;
    /**
     * for Expire time
     */
    public static final String CONTACT_NUMBER = " 09132686053 ";
    /**
     * if is true documentListener act
     */
    public static boolean IS_DOCUMENT_LISTENER_ACTIVE = true;
    /**
     * if is true keyReleased act
     */
    public static boolean IS_KEY_RELEASED_ACTIVE = true;
    public static String YES = Setting.isLanguagePersian() ? "بله" : "Yes";
    public static String NO = Setting.isLanguagePersian() ? "خیر" : "No";
    // dialog's for before act
    public static boolean askAreYouSureInsert = false;
    public static boolean askAreYouSureEdit = true;
    public static boolean askAreYouSureDelete = true;
    // dialog's for after act
    public static boolean failInsert = false;
    public static boolean failEdit = false;
    public static boolean failDelete = false;
    public static boolean successInsert = false;
    public static boolean successEdit = false;
    public static boolean successDelete = false;
    //  dialog messages
    public static String areYouSoureMessageInsert = Setting.isLanguagePersian() ? "آیا از درج این رکورد مطمئن هستید؟" : "Are You Soure For Insert this record?";
    public static String areYouSoureMessageEdit = Setting.isLanguagePersian() ? "آیا از ویرایش این رکورد مطمئن هستید؟" : "Are You Soure For Edit this record?";
    public static String areYouSoureMessageDelete = Setting.isLanguagePersian() ? "آیا از حذف این رکورد مطمئن هستید؟" : "Are You Soure For Delete this record?";
    public static String failMessageInsert = Setting.isLanguagePersian() ? "عمل درج به درستی صورت نگرفت." : "fail Insert";
    public static String failMessageEdit = Setting.isLanguagePersian() ? "عمل ویرایش به درستی صورت نگرفت." : "fail Edit";
    public static String failMessageDelete = Setting.isLanguagePersian() ? "عمل حذف به درستی صورت نگرفت." : "fail Delete";
    public static String successMessageInsert = Setting.isLanguagePersian() ? "درج این رکورد به درستی انجام شد." : "success Insert";
    public static String successMessageEdit = Setting.isLanguagePersian() ? "ویرایش این رکورد به درستی انجام شد." : "success Edit";
    public static String successMessageDelete = Setting.isLanguagePersian() ? "حذف این رکورد به درستی انجام شد." : "success Delete";
}
