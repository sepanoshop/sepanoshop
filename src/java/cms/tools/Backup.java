/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import cms.cms.Category_Forum;
import cms.cms.Category_Gallery;
import cms.cms.Category_News;
import cms.cms.Category_Poll;
import cms.cms.Category_Product;
import cms.cms.Comment;
import cms.cms.Content;
import cms.cms.Enrolment;
import cms.cms.Enrolment3;
import cms.cms.Forum;
import cms.cms.News;
import cms.cms.Pic;
import cms.cms.Poll;
import cms.cms.Product;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jj.jjDatabaseWeb;
import jj.jjFile;
import jj.jjFileTxt;
import jj.jjFileZip;
import jj.jjNumber;


/**
 *
 * @author Milad
 */
public class Backup {

    public static void main(String[] args) {

//        Server.setSettingProject("IranSepano.com", "iransepano_user", "m123456", "aaa", "fa");

//        jjDatabaseWeb db = new jjDatabaseWeb(Server.userName, Server.password, Server.databaseName, Server.serverHostIP, Server.port);
//        db.ConnectCustom();
//        List<String> readLine = jjFileTxt.readLine("D:\\backup.sql");
//        for (int i = 0; i < readLine.size(); i++) {
//            db.otherStatement(readLine.get(i));
//        }
//        backup(null, db, true);

        File f = new File("D:\\aa.txt");
        //        File[] listFiles = f.listFiles();
        //        for (int i = 0; i < listFiles.length; i++) {
        //            File file = listFiles[i];
        //            String name = file.getName();
        //            int indexOf = file.getName().indexOf(".");
        //            String parent = file.getParent();
        //            listFiles[i].renameTo(new File(parent + "\\" + name.substring(0, indexOf)  + ".png"));
        //        }
        String[] split = jjFileTxt.read(f).split("END:VCARD");
        int a = 0;
        String b = "";
        for (int i = 0; i < split.length; i++) {
            a += 1;
            b += split[i] + "END:VCARD";
            if (a > 10) {
                jjFileTxt.write(new File("D:\\a" + a + ".txt"), b);
                b = "";
                a = 0;
            }
        }

    }

    public static void restore(jjDatabaseWeb db, String address) {
        List<String> readLine = jjFileTxt.readLine(new File(address));
        for (int i = 0; i < readLine.size(); i++) {
            db.otherStatement(readLine.get(i));
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            File folderAddress = new File(request.getServletContext().getRealPath("/backup"));
            File f = new File(folderAddress.getAbsolutePath() + "/backup.sql.zip");
            File f2 = new File(folderAddress.getAbsolutePath() + "/backup.zip");
            f.delete();
            f2.delete();
            if (!f.exists() && !f2.exists()) {
                return Js.dialog("فایل ها به درستی از روی هاست حذف شد.");
            } else {
                return Js.dialog("در حذف فایل ها مشکلی پیش آمده");
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String backup(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
//            StringBuffer backup = new StringBuffer();
            File uploadFolder = new File(request.getServletContext().getRealPath("/upload"));
            File backupFolder = new File(request.getServletContext().getRealPath("/backup"));
            File f = new File(backupFolder.getAbsolutePath() + "/backup.sql.zip");
            f.delete();
            jjFileTxt.write(f, backup(db, Content.tableName));
            jjFileTxt.write(f, backup(db, Category_Forum.tableName), true, true);
            jjFileTxt.write(f, backup(db, Category_Poll.tableName), true, true);
            jjFileTxt.write(f, backup(db, Poll.tableName), true, true);
            jjFileTxt.write(f, backup(db, Comment.tableName), true, true);
            jjFileTxt.write(f, backup(db, Enrolment3.tableName), true, true);
            jjFileTxt.write(f, backup(db, Forum.tableName), true, true);
            jjFileTxt.write(f, backup(db, Category_Gallery.tableName), true, true);
            jjFileTxt.write(f, backup(db, Pic.tableName), true, true);
            jjFileTxt.write(f, backup(db, Category_News.tableName), true, true);
            jjFileTxt.write(f, backup(db, Category_Product.tableName), true, true);
            jjFileTxt.write(f, backup(db, Enrolment.tableName), true, true);
            jjFileTxt.write(f, backup(db, News.tableName), true, true);
            jjFileTxt.write(f, backup(db, Product.tableName), true, true);

            return "$('#linkDownloadSql').html('دانلود دیتابیس (" + (f.length() / 1024) + "KB)');\n";
//                    + "$('#linkDownloadZip').append('(" + (jjNumber.getFormattedNumber((zipFile.length() / 1024) + "")) + "KB)');\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String backup(jjDatabaseWeb db, String table) {
        return db.getBackup(table);
    }
//    public static String backup(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
//        try {
//            StringBuffer backup = new StringBuffer();
//
////            backup.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>");
////            backup.append(db.getBackup(Content.tableName));
////            backup.append(db.getBackup(Category_Forum.tableName));
////            backup.append(db.getBackup(Category_Poll.tableName));
////            backup.append(db.getBackup(Category_Gallery.tableName));
////            backup.append(db.getBackup(Category_News.tableName));
////            backup.append(db.getBackup(Category_Product.tableName));
////            backup.append(db.getBackup(Poll.tableName));
////            backup.append(db.getBackup(Comment.tableName));
////            backup.append(db.getBackup(Enrolment.tableName));
////            backup.append(db.getBackup(Enrolment3.tableName));
////            backup.append(db.getBackup(Forum.tableName));
////            backup.append(db.getBackup(News.tableName));
//            backup.append(db.getBackup(Pic.tableName));
////            backup.append(db.getBackup(Product.tableName));
////            backup.append(db.getBackup(Category_Forum.tableName));
////            backup.append(db.getBackup(tice.Tice_books.tableName));
////            backup.append(db.getBackup(tice.Tice_class.tableName));
////            backup.append(db.getBackup(tice.Tice_daily_incom.tableName));
////            backup.append(db.getBackup(tice.Tice_level.tableName));
////            backup.append(db.getBackup(tice.Tice_rooms.tableName));
////            backup.append(db.getBackup(tice.Tice_enrol.tableName));
////            backup.append(db.getBackup(tice.Tice_student.tableName));
////            backup.append(db.getBackup(tice.Tice_teacher.tableName));
////            backup.append(db.getBackup(tice.Tice_teacher_salary.tableName));
////            backup.append(db.getBackup(tice.Tice_term.tableName));
//            backup.append(db.getBackup(tice.Tice_workbook.tableName));
//            File folderAddress = new File(request.getServletContext().getRealPath("/backup"));
////            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
//            File f = new File(folderAddress.getAbsolutePath() + "/backup.sql.zip");
//            File f2 = new File(folderAddress.getAbsolutePath() + "/backup.zip");
//            jjFileTxt.write(f, backup.toString());
////            jjFileZip.doZip(folderAddressUpload, f2);
//            return "$('#linkDownloadSql').append('(" + (f.length() / 1024) + "KB)');\n"
//                    + "$('#linkDownloadZip').append('(" + (jjNumber.getFormattedNumber((f2.length() / 1024) + "")) + "KB)');\n";
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
}
