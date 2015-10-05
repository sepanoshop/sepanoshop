/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import static cms.tools.Server.databaseName;
import static cms.tools.Server.password;
import static cms.tools.Server.port;
import static cms.tools.Server.serverHostIP;
import static cms.tools.Server.userName;
import java.io.File;
import java.util.List;
import java.util.Map;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjPicture;

/**
 *
 * @author Milad
 */
public class CovertTiceToMySql {

    public static void main(String[] args) {
//        File[] listFiles = new File("D:\\vv").listFiles();
//        for (int i = 0; i < listFiles.length; i++) {
//            File file = listFiles[i];
//            jjPicture.doChangeSizeOfPic(file, new File("D:\\vv\\" + (i + 1) + ".jpg"), 100);
////            jjPicture.doChangeSizeOfPic(file, new File("D:\\bb\\" + (i + 1) + ".jpg"), 140);
//
//        }
        jjPicture.doChangeSizeOfPic(new File("d:\\a.png"), new File("D:\\b.png"), 1430);
        if (true) {
            return;
        }
        String accessDbAddress = "D:\\tice.mdb";
        String accessDbUser = "Admin";
        String accessDbPass = "mtpnk42035";

        File f[] = new File("D:\\upload").listFiles();
        for (int i = 0; i < f.length; i++) {
            String name2 = f[i].getName().substring(0, f[i].getName().lastIndexOf("."));
            String extension2 = f[i].getName().substring(f[i].getName().lastIndexOf(".") + 1, f[i].getName().length());
            File file2 = new File(f[i].getParent() + "2/" + name2 + "_small." + extension2);
            if (extension2.toLowerCase().equals("jpg") || extension2.toLowerCase().equals("png") || extension2.toLowerCase().equals("gif")) {
                jjPicture.doChangeSizeOfPic(f[i], file2, 250);
            }
        }

        jjDatabase db1 = jjDatabase.Db_Access(accessDbUser, accessDbPass, accessDbAddress);

//        setSettingProject("IranSepano.com", "iransepano_user", "m123456", "iransepano", "fa");
        jjDatabaseWeb db2 = new jjDatabaseWeb(userName, password, databaseName, serverHostIP, port);
        db2.ConnectCustom();

// ----------------------------------------------------------------------------- book
//        db2.emptyTable(Tice_books.tableName);
//        List<Map<String, Object>> book = jjDatabase.separateRow(db1.SelectAll("Textbooks"));
//        for (int i = 0; i < book.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_books._id, Integer.parseInt(book.get(i).get("Code").toString()));
//            map.put(Tice_books._title, book.get(i).get("Textbook").toString().trim());
//            map.put(Tice_books._des, book.get(i).get("Notes").toString().equals("-") ? "" : book.get(i).get("Notes").toString().trim());
//            db2.insert(Tice_books.tableName, map);
//        }
        // ----------------------------------------------------------------------------- term
//        db2.emptyTable(Tice_term.tableName);
//        List<Map<String, Object>> term = jjDatabase.separateRow(db1.SelectAll("Term"));
//        for (int i = 0; i < term.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_term._id, Integer.parseInt(term.get(i).get("TermID").toString()));
//            map.put(Tice_term._title, term.get(i).get("Term").toString());
//            map.put(Tice_term._des, term.get(i).get("Description").toString());
//            map.put(Tice_term._start_date, jjCalendar_IR.getDatabaseFormat_8length(term.get(i).get("StartingDate").toString(), false));
//            map.put(Tice_term._end_date, jjCalendar_IR.getDatabaseFormat_8length(term.get(i).get("EndingDate").toString(), false));
//            map.put(Tice_term._midterm_date, jjCalendar_IR.getDatabaseFormat_8length(term.get(i).get("EndingDate").toString(), false));
//            db2.insert(Tice_term.tableName, map);
//        }
        // ----------------------------------------------------------------------------- teacher
//        db2.emptyTable(Tice_teacher.tableName);
        // befor convert check the birthdate
//        List<Map<String, Object>> teacher = jjDatabase.separateRow(db1.SelectAll("Teacher", "FirstName,LastName,Father,TeacherID,IdCardNo,BirthDate,Education,HomePhone,MobilePhone,Address,Payment,LastNameEng"));//
//        for (int i = 0; i < teacher.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_teacher._id, Integer.parseInt(teacher.get(i).get("TeacherID").toString()));
//            map.put(Tice_teacher._password, teacher.get(i).get("TeacherID").toString());
//            ServerLog.Print(teacher.get(i).get("TeacherID").toString() + ">>>>>>>" + teacher.get(i).get("Address").toString().trim());
//            map.put(Tice_teacher._first_name, teacher.get(i).get("FirstName").toString());
//            map.put(Tice_teacher._last_name, teacher.get(i).get("LastName").toString());
//            map.put(Tice_teacher._father_name, teacher.get(i).get("Father").toString());
//            map.put(Tice_teacher._id_card_no, teacher.get(i).get("IdCardNo").toString());
//            map.put(Tice_teacher._birthdate, jjCalendar_IR.getDatabaseFormat_8length(teacher.get(i).get("BirthDate").toString(), false));
//            map.put(Tice_teacher._education, teacher.get(i).get("Education").toString());
//            map.put(Tice_teacher._home_phone, teacher.get(i).get("HomePhone").toString());
//            map.put(Tice_teacher._mobile_phone, teacher.get(i).get("MobilePhone").toString());
//            map.put(Tice_teacher._address, teacher.get(i).get("Address").toString().trim().replace("\n", "").replace("\r\n", ""));
//            map.put(Tice_teacher._payment, teacher.get(i).get("Payment").toString());
//            map.put(Tice_teacher._last_name_en, teacher.get(i).get("LastNameEng").toString());
//            map.put(Tice_teacher._first_name_en, "");
//            map.put(Tice_teacher._accountBank1, "");
//            map.put(Tice_teacher._accountBank2, "");
//            map.put(Tice_teacher._accountNo1, "");
//            map.put(Tice_teacher._accountNo2, "");
//            map.put(Tice_teacher._course, "");
//            map.put(Tice_teacher._otherFiles, "");
//            map.put(Tice_teacher._sex, "");
//            map.put(Tice_teacher._picName, "TTT_" + teacher.get(i).get("TeacherID").toString() + ".jpg");
//            db2.insert(Tice_teacher.tableName, map);
//        }
//        jjDbAccess.ExportPicFromAccess(accessDbAddress, accessDbUser, accessDbPass, "Teacher", "TeacherID", "Photo", new File("D:\\upload"), "TTT");
        // ----------------------------------------------------------------------------- level
//        db2.emptyTable(Tice_level.tableName);
//        List<Map<String, Object>> level = jjDatabase.separateRow(db1.SelectAll("Level2"));
//        for (int i = 0; i < level.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_level._id, Integer.parseInt(level.get(i).get("LevelCode").toString()));
//            map.put(Tice_level._name, level.get(i).get("Level").toString().trim());
//            map.put(Tice_level._des, level.get(i).get("Notes").toString().equals("-") ? "" : level.get(i).get("Notes").toString().trim());
//            map.put(Tice_level._score1_number, jjNumber.isDigit(level.get(i).get("Out1").toString().trim()) ? Integer.parseInt(level.get(i).get("Out1").toString().trim()) : 0);
//            map.put(Tice_level._score2_number, jjNumber.isDigit(level.get(i).get("Out2").toString().trim()) ? Integer.parseInt(level.get(i).get("Out2").toString().trim()) : 0);
//            map.put(Tice_level._score3_number, jjNumber.isDigit(level.get(i).get("Out3").toString().trim()) ? Integer.parseInt(level.get(i).get("Out3").toString().trim()) : 0);
//            map.put(Tice_level._score4_number, jjNumber.isDigit(level.get(i).get("Out4").toString().trim()) ? Integer.parseInt(level.get(i).get("Out4").toString().trim()) : 0);
//            map.put(Tice_level._score5_number, jjNumber.isDigit(level.get(i).get("Out5").toString().trim()) ? Integer.parseInt(level.get(i).get("Out5").toString().trim()) : 0);
//            map.put(Tice_level._score6_number, jjNumber.isDigit(level.get(i).get("Out6").toString().trim()) ? Integer.parseInt(level.get(i).get("Out6").toString().trim()) : 0);
//            map.put(Tice_level._score7_number, jjNumber.isDigit(level.get(i).get("Out7").toString().trim()) ? Integer.parseInt(level.get(i).get("Out7").toString().trim()) : 0);
//
//            map.put(Tice_level._score1_name, level.get(i).get("Des1").toString().trim().replace("--", ""));
//            map.put(Tice_level._score2_name, level.get(i).get("Des2").toString().trim().replace("--", ""));
//            map.put(Tice_level._score3_name, level.get(i).get("Des3").toString().trim().replace("--", ""));
//            map.put(Tice_level._score4_name, level.get(i).get("Des4").toString().trim().replace("--", ""));
//            map.put(Tice_level._score5_name, level.get(i).get("Des5").toString().trim().replace("--", ""));
//            map.put(Tice_level._score6_name, level.get(i).get("Des6").toString().trim().replace("--", ""));
//            map.put(Tice_level._score7_name, level.get(i).get("Des7").toString().trim().replace("--", ""));
//
//            map.put(Tice_level._fail, "65");
//            // you must fill these description for karname
//            map.put(Tice_level._fail_des, "So sorry. It looks like you have to repeat the term and level above. Never mind, try again. Don\"t worry; ask for help, you deserve more.");
//            map.put(Tice_level._conditional, "75");
//            map.put(Tice_level._conditional_des, "Your score is incomplete. You need to either repeat the above level or ask for ther supervisor\"s permission to see if you can go to a higher level.");
//            map.put(Tice_level._pass, "75");
//            map.put(Tice_level._pass_des, "This is to certify that you have participated in the term and level above. Your success in learning a new language is our strong desire.");
//            map.put(Tice_level._good, "90");
//            map.put(Tice_level._good_des, "GREATE WORK! This is to certify that you have participated in the term and level above. Your success in learning a new language is our strong desire.");
//            map.put(Tice_level._book, level.get(i).get("TextBook").toString().trim());
//            if (db2.Select(Tice_books.tableName, Tice_books._title + "='" + level.get(i).get("TextBook").toString().replace("'", "\\'").trim() + "'").getRowCount() < 1) {
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_books._title, level.get(i).get("TextBook").toString().replace("'", "\'").trim());
//                map2.put(Tice_books._des, "");
//                jjFileTxt.write(new File("D:\\a.txt"), "Book '" + level.get(i).get("TextBook").toString().replace("'", "\\'").trim() + "' in Level is not being.", true, true);
//                db2.insert(Tice_books.tableName, map2);
//            }
//            map.put(Tice_level._book, level.get(i).get("TextBook").toString().replace("'", "\'"));
//            db2.insert(Tice_level.tableName, map);
//        }
        // ----------------------------------------------------------------------------- student
//        db2.emptyTable(Tice_student.tableName);
//        List<Map<String, Object>> student = jjDatabase.separateRow(db1.SelectAll("Students"));
//        for (int i = 0; i < student.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_student._id, Integer.parseInt(student.get(i).get("StudentID").toString()));
//            map.put(Tice_student._password, Integer.parseInt(student.get(i).get("StudentID").toString()));
//            map.put(Tice_student._first_name, student.get(i).get("FirstName").toString().trim());
//            map.put(Tice_student._last_name, student.get(i).get("LastName").toString().trim());
//            map.put(Tice_student._father_name, student.get(i).get("FatherName").toString().trim());
//            map.put(Tice_student._nationalId, student.get(i).get("IDNumber").toString().trim());
//            map.put(Tice_student._birthdate, jjCalendar_IR.getDatabaseFormat_8length(student.get(i).get("Birthdate").toString().trim(), false));
//            map.put(Tice_student._job, student.get(i).get("Job").toString().trim());
//            String education = student.get(i).get("Education").toString().trim();
//            education = education.contains("ابتدایی") ? "دبستان" : education;
//            education = education.contains("دبیرستان") ? "دبیرستان" : education;
//            education = education.contains("راهنمایی") ? "راهنمایی" : education;
//            map.put(Tice_student._education, education);
//            map.put(Tice_student._home_phone, student.get(i).get("HomePhone").toString().trim());
//            map.put(Tice_student._mobile_phone, student.get(i).get("MobilePhone").toString().trim());
//            map.put(Tice_student._address, student.get(i).get("Address").toString().trim().replace("\n", "").replace("\r\n", ""));
//            map.put(Tice_student._email_address, student.get(i).get("EmailAddress").toString().trim());
//            map.put(Tice_student._placement_test, student.get(i).get("PlacementTest").toString().trim());
//            map.put(Tice_student._starting_level, student.get(i).get("StartingLevel").toString().trim());
//            map.put(Tice_student._registratin_date, jjCalendar_IR.getDatabaseFormat_8length(student.get(i).get("RegistratinDate").toString().trim(), false));
//            map.put(Tice_student._regist_number, student.get(i).get("RegistratinDates").toString().trim());
//            map.put(Tice_student._sex, student.get(i).get("Sex").toString().equals("پسر") ? "1" : "0");
//            map.put(Tice_student._first_name_en, student.get(i).get("FirstNameEng").toString().trim());
//            map.put(Tice_student._last_name_en, student.get(i).get("LastNameEng").toString().trim());
//            map.put(Tice_student._lastTerm, "");
//            map.put(Tice_student._lastClass, "");
//            map.put(Tice_student._lastLevel, "");
//            map.put(Tice_student._comments, "");
//            map.put(Tice_student._otherFiles, "");
//            map.put(Tice_student._username, student.get(i).get("LastNameEng").toString().trim());
//            map.put(Tice_student._picName, "SSS_" + student.get(i).get("StudentID").toString() + ".jpg");
//            db2.insert(Tice_student.tableName, map);
//        }
//        jjDbAccess.ExportPicFromAccess(accessDbAddress, accessDbUser, accessDbPass, "Students", "StudentID", "Photograph", new File("D:\\upload"), "SSS");
        // ----------------------------------------------------------------------------- class
//        db2.emptyTable(Tice_class.tableName);
//        List<Map<String, Object>> clazz = jjDatabase.separateRow(db1.SelectAll("ClassCode"));
//
//        for (int i = 0; i < clazz.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_class._id, Integer.parseInt(clazz.get(i).get("No").toString()));
//            map.put(Tice_class._starting_date, jjCalendar_IR.getDatabaseFormat_8length(clazz.get(i).get("StartingDate").toString().startsWith("13") ? clazz.get(i).get("StartingDate").toString() : "13" + clazz.get(i).get("StartingDate").toString(), false));
//            map.put(Tice_class._final_exam_date, jjCalendar_IR.getDatabaseFormat_8length(clazz.get(i).get("FinalExamDate").toString().startsWith("13") ? clazz.get(i).get("FinalExamDate").toString() : "13" + clazz.get(i).get("FinalExamDate").toString(), false));
//            map.put(Tice_class._midTerm_exam_date, jjCalendar_IR.getDatabaseFormat_8length(clazz.get(i).get("FinalExamDate").toString().startsWith("13") ? clazz.get(i).get("FinalExamDate").toString() : "13" + clazz.get(i).get("FinalExamDate").toString(), false));
//
//            List<Map<String, Object>> ter = jjDatabase.separateRow(db2.Select(Tice_term.tableName, Tice_term._title + "='" + clazz.get(i).get("Term").toString() + "'"));
//            if (ter.size() > 0) {
//                map.put(Tice_class._termId, ter.get(0).get(Tice_term._id));
//            } else {
////                if (true) {
////                    ServerLog.Print(">>> Term '" + clazz.get(i).get("Term").toString() + "' not Exist" + " in " + clazz.get(i).get("No").toString());
////                    return;
////                }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_term._des, "unknown term");
//                map2.put(Tice_term._title, clazz.get(i).get("Term").toString());
//                map2.put(Tice_term._start_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_term._end_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_term._midterm_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                jjFileTxt.write(new File("D:\\a.txt"), "Term '" + clazz.get(i).get("Term").toString().trim() + "' in Class '" + clazz.get(i).get("No").toString() + "' is not being.", true, true);
//                List<Map<String, Object>> terNew = jjDatabase.separateRow(db2.insert(Tice_term.tableName, map2));
//                if (terNew.size() > 0) {
//                    map.put(Tice_class._termId, terNew.get(0).get(Tice_term._id));
//                } else {
//                    map.put(Tice_class._termId, 0);
//                }
//            }
//            map.put(Tice_class._termName, clazz.get(i).get("Term").toString());
//
//            List<Map<String, Object>> ll = jjDatabase.separateRow(db2.Select(Tice_level.tableName, Tice_level._name + "='" + clazz.get(i).get("Level").toString() + "'"));
//            if (ll.size() > 0) {
//                map.put(Tice_class._levelId, ll.get(0).get(Tice_level._id));
//            } else {
////                        if (true) {
////                            ServerLog.Print(">>> Level '" + clazz.get(i).get("Level").toString() + "' not Exist" + " in " + clazz.get(i).get("No").toString());
////                            return;
////                        }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_level._name, clazz.get(i).get("Level").toString());
//                map2.put(Tice_level._book, "");
//                map2.put(Tice_level._des, "unknown level");
//                map2.put(Tice_level._fail, "65");
//                // you must fill these description for karname
//                map2.put(Tice_level._fail_des, "So sorry. It looks like you have to repeat the term and level above. Never mind, try again. Don\"t worry; ask for help, you deserve more.");
//                map2.put(Tice_level._conditional, "75");
//                map2.put(Tice_level._conditional_des, "Your score is incomplete. You need to either repeat the above level or ask for ther supervisor\"s permission to see if you can go to a higher level.");
//                map2.put(Tice_level._pass, "75");
//                map2.put(Tice_level._pass_des, "This is to certify that you have participated in the term and level above. Your success in learning a new language is our strong desire.");
//                map2.put(Tice_level._good, "90");
//                map2.put(Tice_level._good_des, "GREATE WORK! This is to certify that you have participated in the term and level above. Your success in learning a new language is our strong desire.");
//
//                map2.put(Tice_level._score1_name, "");
//                map2.put(Tice_level._score2_name, "");
//                map2.put(Tice_level._score3_name, "");
//                map2.put(Tice_level._score4_name, "");
//                map2.put(Tice_level._score5_name, "");
//                map2.put(Tice_level._score6_name, "");
//                map2.put(Tice_level._score7_name, "");
//
//                map2.put(Tice_level._score1_number, 10);
//                map2.put(Tice_level._score2_number, 10);
//                map2.put(Tice_level._score3_number, 10);
//                map2.put(Tice_level._score4_number, 10);
//                map2.put(Tice_level._score5_number, 10);
//                map2.put(Tice_level._score6_number, 10);
//                map2.put(Tice_level._score7_number, 10);
//                jjFileTxt.write(new File("D:\\a.txt"), "Level '" + clazz.get(i).get("Level").toString().trim() + "' is not exist.", true, true);
//                List<Map<String, Object>> insert = jjDatabase.separateRow(db2.insert(Tice_level.tableName, map2));
//                if (insert.size() > 0) {
//                    map.put(Tice_class._levelId, insert.get(0).get(Tice_level._id));
//                } else {
//                    map.put(Tice_class._levelId, 0);
//                }
//            }
//            map.put(Tice_class._levelName, clazz.get(i).get("Level").toString());
//
//
//            List<Map<String, Object>> tt = jjDatabase.separateRow(db2.Select(Tice_teacher.tableName, Tice_teacher._last_name + "='" + clazz.get(i).get("Teacher").toString().replace("ي", "ی").trim() + "'"));
//            if (tt.size() > 0) {
//                map.put(Tice_class._teacherId, tt.get(0).get(Tice_teacher._id));
//            } else if (jjNumber.isDigit(clazz.get(i).get("Teacher").toString())) {
//                map.put(Tice_class._teacherId, clazz.get(i).get("Teacher").toString());
//            } else {
////                        if (true) {
////                            ServerLog.Print("Teacher '" + clazz.get(i).get("Teacher").toString() + "' not Exist" + " in " + clazz.get(i).get("No").toString());
////                            return;
////                        }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_teacher._accountBank1, "");
//                map2.put(Tice_teacher._accountBank2, "");
//                map2.put(Tice_teacher._accountNo1, "");
//                map2.put(Tice_teacher._accountNo2, "");
//                map2.put(Tice_teacher._address, "");
//                map2.put(Tice_teacher._birthdate, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_teacher._education, "");
//                map2.put(Tice_teacher._father_name, "");
//                map2.put(Tice_teacher._first_name, "");
//                map2.put(Tice_teacher._first_name_en, "");
//                map2.put(Tice_teacher._last_name, clazz.get(i).get("Teacher").toString());
//                map2.put(Tice_teacher._last_name_en, "");
//                map2.put(Tice_teacher._home_phone, "");
//                map2.put(Tice_teacher._id_card_no, "");
//                map2.put(Tice_teacher._mobile_phone, "");
//                map2.put(Tice_teacher._otherFiles, "");
//                map2.put(Tice_teacher._payment, 1);
//                map2.put(Tice_teacher._picName, "");
//                map2.put(Tice_teacher._sex, "");
//                map2.put(Tice_teacher._course, "");
//                jjFileTxt.write(new File("D:\\a.txt"), "Teacher '" + clazz.get(i).get("Teacher").toString() + "' in Class '" + clazz.get(i).get("No").toString() + "' is not being.", true, true);
//                List<Map<String, Object>> insert = jjDatabase.separateRow(db2.insert(Tice_teacher.tableName, map2));
//                if (insert.size() > 0) {
//                    map.put(Tice_class._teacherId, insert.get(0).get(Tice_teacher._id));
//                } else {
//                    map.put(Tice_class._teacherId, 0);
//                }
//                map.put(Tice_class._teacherId, 0);
//            }
//            map.put(Tice_class._teacherName, clazz.get(i).get("Teacher").toString());
//
//            map.put(Tice_class._book, clazz.get(i).get("Textbook").toString());
//            if (db2.Select(Tice_books.tableName, Tice_books._title + "='" + clazz.get(i).get("Textbook").toString().replace("'", "\\'") + "'").getRowCount() == 0) {
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_books._title, clazz.get(i).get("Textbook").toString().replace("'", "\'"));
//                map2.put(Tice_books._des, "");
//                db2.insert(Tice_books.tableName, map2);
//                jjFileTxt.write(new File("D:\\a.txt"), "Book '" + clazz.get(i).get("Textbook").toString().trim() + "'  in class is not being.", true, true);
//            }
//
//            String[] spSaturday = clazz.get(i).get("Saturday").toString().split(":");
//            if (spSaturday.length > 1) {
//                map.put(Tice_class._saturday_1, jjNumber.isDigit(spSaturday[0].trim()) ? Integer.parseInt(spSaturday[0].trim()) : 0);
//                map.put(Tice_class._saturday_2, jjNumber.isDigit(spSaturday[1].trim()) ? Integer.parseInt(spSaturday[1].trim()) : 0);
//                map.put(Tice_class._saturday_3, 0);
//                map.put(Tice_class._saturday_4, 0);
//            } else {
//                map.put(Tice_class._saturday_1, 0);
//                map.put(Tice_class._saturday_2, 0);
//                map.put(Tice_class._saturday_3, 0);
//                map.put(Tice_class._saturday_4, 0);
//            }
//
//            String[] spSunday = clazz.get(i).get("Sunday").toString().split(":");
//            if (spSunday.length > 1) {
//                map.put(Tice_class._sunday_1, jjNumber.isDigit(spSunday[0].trim()) ? Integer.parseInt(spSunday[0].trim()) : 0);
//                map.put(Tice_class._sunday_2, jjNumber.isDigit(spSunday[1].trim()) ? Integer.parseInt(spSunday[1].trim()) : 0);
//                map.put(Tice_class._sunday_3, 0);
//                map.put(Tice_class._sunday_4, 0);
//            } else {
//                map.put(Tice_class._sunday_1, 0);
//                map.put(Tice_class._sunday_2, 0);
//                map.put(Tice_class._sunday_3, 0);
//                map.put(Tice_class._sunday_4, 0);
//            }
//
//            String[] spMonday = clazz.get(i).get("Monday").toString().split(":");
//            if (spMonday.length > 1) {
//                map.put(Tice_class._monday_1, jjNumber.isDigit(spMonday[0].trim()) ? Integer.parseInt(spMonday[0].trim()) : 0);
//                map.put(Tice_class._monday_2, jjNumber.isDigit(spMonday[1].trim()) ? Integer.parseInt(spMonday[1].trim()) : 0);
//                map.put(Tice_class._monday_3, 0);
//                map.put(Tice_class._monday_4, 0);
//            } else {
//                map.put(Tice_class._monday_1, 0);
//                map.put(Tice_class._monday_2, 0);
//                map.put(Tice_class._monday_3, 0);
//                map.put(Tice_class._monday_4, 0);
//            }
//
//            String[] spTuesday = clazz.get(i).get("Tuesday").toString().split(":");
//            if (spTuesday.length > 1) {
//                map.put(Tice_class._tuesday_1, jjNumber.isDigit(spTuesday[0].trim()) ? Integer.parseInt(spTuesday[0].trim()) : 0);
//                map.put(Tice_class._tuesday_2, jjNumber.isDigit(spTuesday[1].trim()) ? Integer.parseInt(spTuesday[1].trim()) : 0);
//                map.put(Tice_class._tuesday_3, 0);
//                map.put(Tice_class._tuesday_4, 0);
//            } else {
//                map.put(Tice_class._tuesday_1, 0);
//                map.put(Tice_class._tuesday_2, 0);
//                map.put(Tice_class._tuesday_3, 0);
//                map.put(Tice_class._tuesday_4, 0);
//            }
//
//            String[] spWednesday = clazz.get(i).get("Wednesday").toString().split(":");
//            if (spWednesday.length > 1) {
//                map.put(Tice_class._wednesday_1, jjNumber.isDigit(spWednesday[0].trim()) ? Integer.parseInt(spWednesday[0].trim()) : 0);
//                map.put(Tice_class._wednesday_2, jjNumber.isDigit(spWednesday[1].trim()) ? Integer.parseInt(spWednesday[1].trim()) : 0);
//                map.put(Tice_class._wednesday_3, 0);
//                map.put(Tice_class._wednesday_4, 0);
//            } else {
//                map.put(Tice_class._wednesday_1, 0);
//                map.put(Tice_class._wednesday_2, 0);
//                map.put(Tice_class._wednesday_3, 0);
//                map.put(Tice_class._wednesday_4, 0);
//            }
//
//            String[] spThursday = clazz.get(i).get("Thursday").toString().split(":");
//            if (spThursday.length > 1) {
//                map.put(Tice_class._thursday_1, jjNumber.isDigit(spThursday[0].trim()) ? Integer.parseInt(spThursday[0].trim()) : 0);
//                map.put(Tice_class._thursday_2, jjNumber.isDigit(spThursday[1].trim()) ? Integer.parseInt(spThursday[1].trim()) : 0);
//                map.put(Tice_class._thursday_3, 0);
//                map.put(Tice_class._thursday_4, 0);
//            } else {
//                map.put(Tice_class._thursday_1, 0);
//                map.put(Tice_class._thursday_2, 0);
//                map.put(Tice_class._thursday_3, 0);
//                map.put(Tice_class._thursday_4, 0);
//            }
//
//            String[] spFriday = clazz.get(i).get("Friday").toString().split(":");
//            if (spFriday.length > 1) {
//                map.put(Tice_class._friday_1, jjNumber.isDigit(spFriday[0].trim()) ? Integer.parseInt(spFriday[0].trim()) : 0);
//                map.put(Tice_class._friday_2, jjNumber.isDigit(spFriday[1].trim()) ? Integer.parseInt(spFriday[1].trim()) : 0);
//                map.put(Tice_class._friday_3, 0);
//                map.put(Tice_class._friday_4, 0);
//            } else {
//                map.put(Tice_class._friday_1, 0);
//                map.put(Tice_class._friday_2, 0);
//                map.put(Tice_class._friday_3, 0);
//                map.put(Tice_class._friday_4, 0);
//            }
//
//            map.put(Tice_class._roomId, jjNumber.isDigit(clazz.get(i).get("Classroom").toString()) ? Integer.parseInt(clazz.get(i).get("Classroom").toString()) : 0);
//            map.put(Tice_class._roomName, clazz.get(i).get("Classroom").toString());
//            map.put(Tice_class._protestPermision, false);
//            map.put(Tice_class._registerLock, false);
//
//            map.put(Tice_class._fee, jjNumber.isDigit(clazz.get(i).get("Fee").toString().trim()) ? Integer.parseInt(clazz.get(i).get("Fee").toString().trim()) : 0);
//            //            map.put(Tice_class._, clazz.get(i).get("Note").toString());
//            map.put(Tice_class._cap, jjNumber.isDigit(clazz.get(i).get("Cap").toString().trim()) ? Integer.parseInt(clazz.get(i).get("Cap").toString().trim()) : 15);
//            map.put(Tice_class._no_of_sessions, jjNumber.isDigit(clazz.get(i).get("NoOfSessions").toString().trim()) ? Integer.parseInt(clazz.get(i).get("NoOfSessions").toString().trim()) : 20);
//            map.put(Tice_class._prerequisite, jjNumber.isDigit(clazz.get(i).get("Prerequisite").toString().trim()) ? Integer.parseInt(clazz.get(i).get("Prerequisite").toString().trim()) : 0);
//            map.put(Tice_class._classCode, clazz.get(i).get("ClassCode").toString().trim());
//            map.put(Tice_class._observatuib, "--Select--");
//            db2.insert(Tice_class.tableName, map);
//        }
        // ----------------------------------------------------------------------------- teacger salary
//        db2.emptyTable(Tice_teacher_salary.tableName);
//        List<Map<String, Object>> salary = jjDatabase.separateRow(db1.SelectAll("TeacherSalary"));
//        for (int i = 0; i < salary.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_teacher_salary._id, Integer.parseInt(salary.get(i).get("No").toString()));
//
//
//            List<Map<String, Object>> term = jjDatabase.separateRow(db2.Select(Tice_term.tableName, Tice_term._title + "='" + salary.get(i).get("Term").toString() + "'"));
//            if (term.size() > 0) {
//                map.put(Tice_teacher_salary._termId, term.get(0).get(Tice_term._id).toString());
//            } else {
//                if (true) {
//                    ServerLog.Print(">>> unknow Term '" + salary.get(i).get("Term").toString() + "' in teacher salary " + salary.get(i).get("No").toString());
//                    return;
//                }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_term._des, "unknown term");
//                map2.put(Tice_term._title, salary.get(i).get("Term").toString());
//                map2.put(Tice_term._start_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_term._end_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_term._midterm_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                List<Map<String, Object>> terNew = jjDatabase.separateRow(db2.insert(Tice_term.tableName, map2));
//                jjFileTxt.write(new File("D:\\a.txt"), "Term '" + salary.get(i).get("Term").toString().trim() + "' in Salary '" + salary.get(i).get("No").toString() + "' is not being.", true, true);
//                if (terNew.size() > 0) {
//                    map.put(Tice_teacher_salary._termId, terNew.get(0).get(Tice_term._id));
//                } else {
//                    map.put(Tice_teacher_salary._termId, 0);
//                }
//            }
//
//            map.put(Tice_teacher_salary._date, jjCalendar_IR.getDatabaseFormat_8length(salary.get(i).get("Dates").toString(), false));
//            map.put(Tice_teacher_salary._number_of_classes_in_term, (salary.get(i).get("NoOfClasses").toString()));
//            map.put(Tice_teacher_salary._number_of_session_in_term, (salary.get(i).get("NoOfSessions").toString()));
//
//            String extr = salary.get(i).get("ExtraSessoins").toString();
//            map.put(Tice_teacher_salary._extra_session, extr);
//            if (extr.contains("-")) {
//                map.put(Tice_teacher_salary._extra_session, 0);
//                map.put(Tice_teacher_salary._subOfSessions, jjString.getOnlyDigit(extr));
//            }
//            map.put(Tice_teacher_salary._sum_session, (salary.get(i).get("TotalSessions").toString()));
//            map.put(Tice_teacher_salary._price_of_every_session, (salary.get(i).get("SalaryPerSession").toString()));
//            map.put(Tice_teacher_salary._total_salary, (salary.get(i).get("TotalSalary").toString()));
//            map.put(Tice_teacher_salary._padash, (salary.get(i).get("PadashExtra").toString()));
//            map.put(Tice_teacher_salary._other_extra, (salary.get(i).get("OtherExtra").toString()));
//            map.put(Tice_teacher_salary._tax, (salary.get(i).get("Tax").toString()));
//            map.put(Tice_teacher_salary._bime, (salary.get(i).get("Enssurance").toString()));
//            map.put(Tice_teacher_salary._other_subtrack, (salary.get(i).get("OtherSubtrack").toString()));
//            map.put(Tice_teacher_salary._final_total, (salary.get(i).get("FinalTotal").toString()));
//            map.put(Tice_teacher_salary._final_total_strings, (salary.get(i).get("FinalTotal").toString()));
//            map.put(Tice_teacher_salary._notes, (salary.get(i).get("Notes").toString()));
//            map.put(Tice_teacher_salary._operator, (salary.get(i).get("Operator").toString()));
//            map.put(Tice_teacher_salary._check_no, (salary.get(i).get("CheckNo").toString()));
//            map.put(Tice_teacher_salary._check_howmuch, (salary.get(i).get("CheckHowMuch").toString()));
//            map.put(Tice_teacher_salary._check_date, jjCalendar_IR.getDatabaseFormat_8length(salary.get(i).get("CheckDate").toString(), false));
//            if (jjNumber.isDigit(salary.get(i).get("TeacherID").toString())) {
//                if (db2.Select(Tice_teacher.tableName, Tice_teacher._id + "=" + salary.get(i).get("TeacherID").toString()).getRowCount() == 0) {
//                    if (true) {
//                        ServerLog.Print(">>> unknow Teacher ID '" + salary.get(i).get("TeacherID").toString() + "' in teacher salary " + salary.get(i).get("No").toString());
//                        return;
//                    }
//                } else {
//                    //                    jjFileTxt.write(new File("D:\\a.txt"), "in Teacher Salary teacher:'" + salary.get(i).get("FirstName").toString().trim() + " " + salary.get(i).get("LastName").toString().trim() + "' is not being.", true, true);
//                    map.put(Tice_teacher_salary._teacher_id, Integer.parseInt(salary.get(i).get("TeacherID").toString()));
//                }
//
//            } else {
//                ServerLog.Print(">>> unknow Teacher ID '" + salary.get(i).get("TeacherID").toString() + "' in teacher salary " + salary.get(i).get("No").toString());
//                return;
//            }
//
//            db2.insert(Tice_teacher_salary.tableName, map);
//        }
        // ----------------------------------------------------------------------------- enrol
//        db2.emptyTable(Tice_enrol.tableName);
//        List<Map<String, Object>> enrol = jjDatabase.separateRow(db1.SelectAll("TermRegister"));
//        clazz_in_access_tice = jjDatabase.separateRow(db1.SelectAll("ClassCode"));
//        for (int i = 0; i < enrol.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_enrol._id, Integer.parseInt(enrol.get(i).get("No").toString()));
//
//            List<Map<String, Object>> term = jjDatabase.separateRow(db2.Select(Tice_term.tableName, Tice_term._title + "='" + enrol.get(i).get("Term").toString() + "'"));
//            if (term.size() > 0) {
//                map.put(Tice_enrol._termId, term.get(0).get(Tice_term._id));
//            } else {
////                if (true) {
////                    ServerLog.Print(">>> unknow Term'" + enrol.get(i).get("Term").toString() + "' in enrol " + enrol.get(i).get("No").toString());
////                    return;
////                }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_term._des, "unknown term");
//                map2.put(Tice_term._title, enrol.get(i).get("Term").toString());
//                map2.put(Tice_term._start_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_term._end_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_term._midterm_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                List<Map<String, Object>> terNew = jjDatabase.separateRow(db2.insert(Tice_term.tableName, map2));
//                jjFileTxt.write(new File("D:\\a.txt"), "Term '" + enrol.get(i).get("Term").toString().trim() + "' in Enrol '" + enrol.get(i).get("No").toString() + "' is not being.", true, true);
//                if (terNew.size() > 0) {
//                    map.put(Tice_enrol._termId, terNew.get(0).get(Tice_term._id));
//                } else {
//                    map.put(Tice_enrol._termId, 0);
//                }
//            }
//            map.put(Tice_enrol._termName, enrol.get(i).get("Term").toString());
//            List<Map<String, Object>> st = jjDatabase.separateRow(db2.Select(Tice_student.tableName, Tice_student._id + "='" + enrol.get(i).get("StudentID").toString() + "'"));
//            if (st.size() > 0) {
//                map.put(Tice_enrol._studentId, st.get(0).get(Tice_student._id));
//            } else {
////                if (true) {
////                    ServerLog.Print(">>> unknow Student '" + enrol.get(i).get("StudentID").toString() + "' in enrol " + enrol.get(i).get("No").toString());
////                    return;
////                }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//
//                map2.put(Tice_student._first_name, enrol.get(i).get("FirstName").toString());
//                map2.put(Tice_student._last_name, enrol.get(i).get("LastName").toString());
//                map2.put(Tice_student._home_phone, enrol.get(i).get("HomePhone").toString());
//                map2.put(Tice_student._mobile_phone, enrol.get(i).get("MobilePhone").toString());
//                map2.put(Tice_student._address, "");
//                map2.put(Tice_student._education, "");
//                map2.put(Tice_student._email_address, "");
//                map2.put(Tice_student._father_name, "");
//                map2.put(Tice_student._first_name_en, enrol.get(i).get("FirstName").toString());
//                map2.put(Tice_student._job, "");
//                map2.put(Tice_student._comments, "unknown Student");
//                map2.put(Tice_student._last_name_en, enrol.get(i).get("LastName").toString());
//                map2.put(Tice_student._sex, enrol.get(i).get("Sex").toString());
//                map2.put(Tice_student._nationalId, "");
//                map2.put(Tice_student._picName, "");
//                map2.put(Tice_student._birthdate, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_student._registratin_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_student._placement_test, "");
//                map2.put(Tice_student._starting_level, "");
//                map2.put(Tice_student._username, enrol.get(i).get("LastName").toString());
//                map2.put(Tice_student._password, enrol.get(i).get("LastName").toString());
//
//                List<Map<String, Object>> insert = jjDatabase.separateRow(db2.insert(Tice_student.tableName, map2));
//                jjFileTxt.write(new File("D:\\a.txt"), "Student '" + enrol.get(i).get("StudentID").toString().trim() + "' in Enrol '" + enrol.get(i).get("No").toString() + "' is not being.", true, true);
//                if (insert.size() > 0) {
//                    map.put(Tice_enrol._studentId, insert.get(0).get(Tice_student._id));
//                } else {
//                    map.put(Tice_enrol._studentId, 0);
//                }
//            }
//            map.put(Tice_enrol._fatherName, "");
//            map.put(Tice_enrol._firstName, enrol.get(i).get("FirstName").toString());
//            map.put(Tice_enrol._lastName, enrol.get(i).get("LastName").toString());
//            map.put(Tice_enrol._mobile, enrol.get(i).get("MobilePhone").toString());
//            map.put(Tice_enrol._tell, enrol.get(i).get("HomePhone").toString());
//
//
//            String Date = enrol.get(i).get("RegistrationDate").toString() == null ? "" : enrol.get(i).get("RegistrationDate").toString();
//            String[] split = Date.split(" ")[0].split("-");
//            if (split.length > 2) {
//                if (split[0].length() == 3) {
//                    split[0] = "1" + split[2];
//                } else if (split[0].length() == 2) {
//                    split[0] = "13" + split[2];
//                }
//                if (split[1].length() == 1) {
//                    split[1] = "0" + split[1];
//                }
//                if (split[2].length() == 1) {
//                    split[2] = "0" + split[2];
//                }
//                Date = split[0] + split[1] + split[2];
//            }
//            Date = Date.replace("/", "").replace(".", "").replace(",", "").replace("\\", "").trim();
//            Date = Date.length() == 4 ? Date + "0000" : Date;
//            if (Date.length() == 8) {
//                if (jjNumber.isDigit(Date)) {
//                    if (Date.startsWith("13")) {
//                        map.put(Tice_enrol._registratinDates, Integer.parseInt(Date));
//                        map.put(Tice_enrol._date, Integer.parseInt(Date));
//                    } else {
//                        jjCalendar_EN calendar_EN = new jjCalendar_EN(Date);
//                        jjCalendar_IR convertGregorianToPersian = calendar_EN.convertGregorianToPersian();
//                        map.put(Tice_enrol._registratinDates, convertGregorianToPersian.getDBFormat_8length());
//                        map.put(Tice_enrol._date, convertGregorianToPersian.getDBFormat_8length());
//                    }
//                }
//            }
//            String calss_no = getClassCodeFromTiceAccessDb(enrol.get(i).get("ClassCode").toString().trim(), enrol.get(i).get("Term").toString().trim()).trim();
//            if (jjNumber.isDigit(calss_no)) {
//                List<Map<String, Object>> cl = jjDatabase.separateRow(db2.Select(Tice_class.tableName, Tice_class._id + "=" + calss_no));
//                if (cl.size() > 0) {
//                    map.put(Tice_enrol._classId, cl.get(0).get(Tice_class._id));
//                    map.put(Tice_enrol._termPrice, jjNumber.isDigit(cl.get(0).get(Tice_class._fee).toString()) ? Integer.parseInt(cl.get(0).get(Tice_class._fee).toString()) : 0);
//                } else {
////                    if (true) {
////                        ServerLog.Print(">>> unknow class '" + enrol.get(i).get("ClassCode").toString() + "' in enrol " + enrol.get(i).get("No").toString());
////                        return;
////                    }
//                    Map<String, Object> map2 = new HashMap<String, Object>();
//                    map2.put(Tice_class._observatuib, "--Select--");
//                    map2.put(Tice_class._protestPermision, false);
//                    map2.put(Tice_class._registerLock, false);
//                    map2.put(Tice_class._book, "");
//                    map2.put(Tice_class._starting_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                    map2.put(Tice_class._midTerm_exam_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                    map2.put(Tice_class._final_exam_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                    map2.put(Tice_class._fee, 0);
//                    map2.put(Tice_class._no_of_sessions, 0);
//                    map2.put(Tice_class._cap, 0);
//                    map2.put(Tice_class._prerequisite, 0);
//                    map2.put(Tice_class._termId, 0);
//                    map2.put(Tice_class._levelId, 0);
//                    map2.put(Tice_class._teacherId, 0);
//                    map2.put(Tice_class._roomId, 0);
//                    map2.put(Tice_class._termName, enrol.get(i).get("Term").toString());
//                    map2.put(Tice_class._levelName, "unknown Level");
//                    map2.put(Tice_class._teacherName, "unknown Teacher");
//                    map2.put(Tice_class._roomName, "unknown Room");
//                    map2.put(Tice_class._saturday_1, 0);
//                    map2.put(Tice_class._saturday_2, 0);
//                    map2.put(Tice_class._saturday_3, 0);
//                    map2.put(Tice_class._saturday_4, 0);
//                    map2.put(Tice_class._sunday_1, 0);
//                    map2.put(Tice_class._sunday_2, 0);
//                    map2.put(Tice_class._sunday_3, 0);
//                    map2.put(Tice_class._sunday_4, 0);
//                    map2.put(Tice_class._monday_1, 0);
//                    map2.put(Tice_class._monday_2, 0);
//                    map2.put(Tice_class._monday_3, 0);
//                    map2.put(Tice_class._monday_4, 0);
//                    map2.put(Tice_class._tuesday_1, 0);
//                    map2.put(Tice_class._tuesday_2, 0);
//                    map2.put(Tice_class._tuesday_3, 0);
//                    map2.put(Tice_class._tuesday_4, 0);
//                    map2.put(Tice_class._wednesday_1, 0);
//                    map2.put(Tice_class._wednesday_2, 0);
//                    map2.put(Tice_class._wednesday_3, 0);
//                    map2.put(Tice_class._wednesday_4, 0);
//                    map2.put(Tice_class._thursday_1, 0);
//                    map2.put(Tice_class._thursday_2, 0);
//                    map2.put(Tice_class._thursday_3, 0);
//                    map2.put(Tice_class._thursday_4, 0);
//                    map2.put(Tice_class._friday_1, 0);
//                    map2.put(Tice_class._friday_2, 0);
//                    map2.put(Tice_class._friday_3, 0);
//                    map2.put(Tice_class._friday_4, 0);
//                    List<Map<String, Object>> insert = jjDatabase.separateRow(db2.insert(Tice_class.tableName, map2));
//
//                    if (insert.size() > 0) {
//                        map.put(Tice_enrol._classId, insert.get(0).get(Tice_class._id));
//                    } else {
//                        map.put(Tice_enrol._classId, 0);
//                    }
//                    map.put(Tice_enrol._termPrice, 0);
//                }
//            } else {
//                map.put(Tice_enrol._classId, 0);
//            }
//            //
//            map.put(Tice_enrol._otherDebit, 0);
//            map.put(Tice_enrol._otherDebitComment, "");
//            int paym1 = jjNumber.isDigit(enrol.get(i).get("Payment1").toString().trim()) ? Integer.parseInt(enrol.get(i).get("Payment1").toString().trim()) : 0;
//            int paym2 = jjNumber.isDigit(enrol.get(i).get("Payment2").toString().trim()) ? Integer.parseInt(enrol.get(i).get("Payment2").toString().trim()) : 0;
//            int paym3 = jjNumber.isDigit(enrol.get(i).get("Payment3").toString().trim()) ? Integer.parseInt(enrol.get(i).get("Payment3").toString().trim()) : 0;
//            map.put(Tice_enrol._payment1, paym1);
//            map.put(Tice_enrol._payment2, paym2);
//            map.put(Tice_enrol._payment3, paym3);
//            map.put(Tice_enrol._receiptType1, "");
//            map.put(Tice_enrol._receiptType2, "");
//            map.put(Tice_enrol._receiptType3, "");
//            map.put(Tice_enrol._paymentDate1, jjCalendar_IR.getDatabaseFormat_8length("", false));
//            map.put(Tice_enrol._paymentDate2, jjCalendar_IR.getDatabaseFormat_8length("", false));
//            map.put(Tice_enrol._paymentDate3, jjCalendar_IR.getDatabaseFormat_8length("", false));
//            map.put(Tice_enrol._receiptNo1, enrol.get(i).get("ReceiptNo1"));
//            map.put(Tice_enrol._receiptNo2, enrol.get(i).get("ReceiptNo2"));
//            map.put(Tice_enrol._receiptNo3, enrol.get(i).get("ReceiptNo3"));
//            map.put(Tice_enrol._oprator, enrol.get(i).get("Oprator"));
//            map.put(Tice_enrol._remaining, jjNumber.isDigit(enrol.get(i).get("Remaining").toString()) ? Integer.parseInt(enrol.get(i).get("Remaining").toString()) : 0);
//            map.put(Tice_enrol._discount, jjNumber.isDigit(enrol.get(i).get("Discount").toString().trim()) ? Integer.parseInt(enrol.get(i).get("Discount").toString().trim()) : 0);
//
//            db2.insert(Tice_enrol.tableName, map);
//        }
        // ----------------------------------------------------------------------------- workbook (karname)
//        db2.emptyTable(Tice_workbook.tableName);
//        List<Map<String, Object>> workbook = jjDatabase.separateRow(db1.SelectAll("ReportCard"));
//        clazz_in_access_tice = jjDatabase.separateRow(db1.SelectAll("ClassCode"));
//        for (int i = 0; i < workbook.size(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put(Tice_workbook._id, Integer.parseInt(workbook.get(i).get("No").toString()));
//
//            List<Map<String, Object>> st = jjDatabase.separateRow(db2.Select(Tice_student.tableName, Tice_student._id + "='" + workbook.get(i).get("StudentId").toString() + "'"));
//            if (st.size() > 0) {
//                map.put(Tice_workbook._studentId, st.get(0).get(Tice_student._id));
//            } else {
////                if (true) {
////                    ServerLog.Print(">>> unknow Student '" + workbook.get(i).get("StudentId").toString() + "' in workbook " + workbook.get(i).get("No").toString());
////                    return;
////                }
//                Map<String, Object> map2 = new HashMap<String, Object>();
//
//                map2.put(Tice_student._first_name, workbook.get(i).get("FirstName").toString());
//                map2.put(Tice_student._last_name, workbook.get(i).get("LastName").toString());
//                map2.put(Tice_student._home_phone, "");
//                map2.put(Tice_student._mobile_phone, "");
//                map2.put(Tice_student._address, "");
//                map2.put(Tice_student._education, "");
//                map2.put(Tice_student._email_address, "");
//                map2.put(Tice_student._father_name, "");
//                map2.put(Tice_student._first_name_en, workbook.get(i).get("LastName").toString());
//                map2.put(Tice_student._job, "");
//                map2.put(Tice_student._comments, "unknown Student");
//                map2.put(Tice_student._last_name_en, workbook.get(i).get("LastName").toString());
//                map2.put(Tice_student._sex, "");
//                map2.put(Tice_student._nationalId, "");
//                map2.put(Tice_student._picName, "");
//                map2.put(Tice_student._birthdate, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_student._registratin_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                map2.put(Tice_student._placement_test, "");
//                map2.put(Tice_student._starting_level, "");
//                map2.put(Tice_student._username, workbook.get(i).get("LastName").toString());
//                map2.put(Tice_student._password, workbook.get(i).get("LastName").toString());
//
//                List<Map<String, Object>> insert = jjDatabase.separateRow(db2.insert(Tice_student.tableName, map2));
//                jjFileTxt.write(new File("D:\\a.txt"), "Karname with Studebt:'" + workbook.get(i).get("StudentId").toString() + "' in whorkbook '" + workbook.get(i).get("No").toString() + "' is not being.", true, true);
//                if (insert.size() > 0) {
//                    map.put(Tice_workbook._studentId, insert.get(0).get(Tice_student._id));
//                } else {
//                    map.put(Tice_workbook._studentId, 0);
//                }
//            }
//
//            map.put(Tice_workbook._operator, workbook.get(i).get("Operator").toString());
//
//
//            map.put(Tice_workbook._book, workbook.get(i).get("TextBook").toString());
//            if (db2.Select(Tice_books.tableName, Tice_books._title + "='" + workbook.get(i).get("TextBook").toString().replace("'", "\\'") + "'").getRowCount() == 0) {
//                Map<String, Object> map2 = new HashMap<String, Object>();
//                map2.put(Tice_books._title, workbook.get(i).get("TextBook").toString().replace("'", "\'"));
//                map2.put(Tice_books._des, "");
//                db2.insert(Tice_books.tableName, map2);
//                jjFileTxt.write(new File("D:\\a.txt"), "Book '" + workbook.get(i).get("Textbook").toString().trim() + "' insert in table.", true, true);
//            }
//
//
//            //            map.put(Tice_workbook._level, workbook.get(i).get("Level").toString());
//            map.put(Tice_workbook._dec, workbook.get(i).get("Notes").toString());
//            map.put(Tice_workbook._stdProtestText, "");
//            map.put(Tice_workbook._protestAnswer, "");
//            map.put(Tice_workbook._disciplineNote, workbook.get(i).get("Rank").toString());
//
//            ServerLog.Print(workbook.get(i).get("Score1").toString());
//            map.put(Tice_workbook._score1, jjNumber.isDigitDouble(workbook.get(i).get("Score1").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score1").toString().trim()) : 0);
//            ServerLog.Print(workbook.get(i).get("Score2").toString());
//            map.put(Tice_workbook._score2, jjNumber.isDigitDouble(workbook.get(i).get("Score2").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score2").toString().trim()) : 0);
//            ServerLog.Print(workbook.get(i).get("Score3").toString());
//            map.put(Tice_workbook._score3, jjNumber.isDigitDouble(workbook.get(i).get("Score3").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score3").toString().trim()) : 0);
//            ServerLog.Print(workbook.get(i).get("Score4").toString());
//            map.put(Tice_workbook._score4, jjNumber.isDigitDouble(workbook.get(i).get("Score4").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score4").toString().trim()) : 0);
//            ServerLog.Print(workbook.get(i).get("Score5").toString());
//            map.put(Tice_workbook._score5, jjNumber.isDigitDouble(workbook.get(i).get("Score5").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score5").toString().trim()) : 0);
//            ServerLog.Print(workbook.get(i).get("Score6").toString());
//            map.put(Tice_workbook._score6, jjNumber.isDigitDouble(workbook.get(i).get("Score6").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score6").toString().trim()) : 0);
//            ServerLog.Print(workbook.get(i).get("Score7").toString());
//            map.put(Tice_workbook._score7, jjNumber.isDigitDouble(workbook.get(i).get("Score7").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Score7").toString().trim()) : 0);
//            map.put(Tice_workbook._score1_number, jjNumber.isDigitDouble(workbook.get(i).get("Out1").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out1").toString().trim()) : 0);
//            map.put(Tice_workbook._score2_number, jjNumber.isDigitDouble(workbook.get(i).get("Out2").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out2").toString().trim()) : 0);
//            map.put(Tice_workbook._score3_number, jjNumber.isDigitDouble(workbook.get(i).get("Out3").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out3").toString().trim()) : 0);
//            map.put(Tice_workbook._score4_number, jjNumber.isDigitDouble(workbook.get(i).get("Out4").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out4").toString().trim()) : 0);
//            map.put(Tice_workbook._score5_number, jjNumber.isDigitDouble(workbook.get(i).get("Out5").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out5").toString().trim()) : 0);
//            map.put(Tice_workbook._score6_number, jjNumber.isDigitDouble(workbook.get(i).get("Out6").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out6").toString().trim()) : 0);
//            map.put(Tice_workbook._score7_number, jjNumber.isDigitDouble(workbook.get(i).get("Out7").toString().trim()) ? Double.parseDouble(workbook.get(i).get("Out7").toString().trim()) : 0);
//            map.put(Tice_workbook._Des1, workbook.get(i).get("Des1").toString());
//            map.put(Tice_workbook._Des2, workbook.get(i).get("Des2").toString());
//            map.put(Tice_workbook._Des3, workbook.get(i).get("Des3").toString());
//            map.put(Tice_workbook._Des4, workbook.get(i).get("Des4").toString());
//            map.put(Tice_workbook._Des5, workbook.get(i).get("Des5").toString());
//            map.put(Tice_workbook._Des6, workbook.get(i).get("Des6").toString());
//            map.put(Tice_workbook._Des7, workbook.get(i).get("Des7").toString());
//
//
//            map.put(Tice_workbook._regDate, jjCalendar_IR.getDatabaseFormat_8length("", false));
//
//            // chech classsssssssssssss
//            String calss_no = getClassCodeFromTiceAccessDb(workbook.get(i).get("ClassCode").toString().trim(), workbook.get(i).get("Term").toString().trim()).trim();
//            if (jjNumber.isDigit(calss_no)) {
//                List<Map<String, Object>> cl = jjDatabase.separateRow(db2.Select(Tice_class.tableName, Tice_class._id + "=" + calss_no));
//                if (cl.size() > 0) {
//                    map.put(Tice_workbook._classId, cl.get(0).get(Tice_class._id));
//                } else {
////                    if (true) {
////                        ServerLog.Print(">>> unknow class '" + workbook.get(i).get("ClassCode").toString() + "' in workbook " + workbook.get(i).get("No").toString());
////                        return;
////                    }
//                    Map<String, Object> map2 = new HashMap<String, Object>();
//                    map2.put(Tice_class._classCode, "");
//                    map2.put(Tice_class._protestPermision, false);
//                    map2.put(Tice_class._registerLock, false);
//                    map2.put(Tice_class._book, "");
//                    map2.put(Tice_class._starting_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                    map2.put(Tice_class._midTerm_exam_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                    map2.put(Tice_class._final_exam_date, jjCalendar_IR.getDatabaseFormat_8length("", false));
//                    map2.put(Tice_class._fee, 0);
//                    map2.put(Tice_class._no_of_sessions, 0);
//                    map2.put(Tice_class._cap, 0);
//                    map2.put(Tice_class._prerequisite, 0);
//                    map2.put(Tice_class._termId, 0);
//                    map2.put(Tice_class._levelId, 0);
//                    map2.put(Tice_class._teacherId, 0);
//                    map2.put(Tice_class._roomId, 0);
//                    map2.put(Tice_class._termName, workbook.get(i).get("Term").toString());
//                    map2.put(Tice_class._levelName, "unknown Level");
//                    map2.put(Tice_class._teacherName, "unknown Teacher");
//                    map2.put(Tice_class._roomName, "unknown Room");
//                    map2.put(Tice_class._saturday_1, 0);
//                    map2.put(Tice_class._saturday_2, 0);
//                    map2.put(Tice_class._saturday_3, 0);
//                    map2.put(Tice_class._saturday_4, 0);
//                    map2.put(Tice_class._sunday_1, 0);
//                    map2.put(Tice_class._sunday_2, 0);
//                    map2.put(Tice_class._sunday_3, 0);
//                    map2.put(Tice_class._sunday_4, 0);
//                    map2.put(Tice_class._monday_1, 0);
//                    map2.put(Tice_class._monday_2, 0);
//                    map2.put(Tice_class._monday_3, 0);
//                    map2.put(Tice_class._monday_4, 0);
//                    map2.put(Tice_class._tuesday_1, 0);
//                    map2.put(Tice_class._tuesday_2, 0);
//                    map2.put(Tice_class._tuesday_3, 0);
//                    map2.put(Tice_class._tuesday_4, 0);
//                    map2.put(Tice_class._wednesday_1, 0);
//                    map2.put(Tice_class._wednesday_2, 0);
//                    map2.put(Tice_class._wednesday_3, 0);
//                    map2.put(Tice_class._wednesday_4, 0);
//                    map2.put(Tice_class._thursday_1, 0);
//                    map2.put(Tice_class._thursday_2, 0);
//                    map2.put(Tice_class._thursday_3, 0);
//                    map2.put(Tice_class._thursday_4, 0);
//                    map2.put(Tice_class._friday_1, 0);
//                    map2.put(Tice_class._friday_2, 0);
//                    map2.put(Tice_class._friday_3, 0);
//                    map2.put(Tice_class._friday_4, 0);
//                    List<Map<String, Object>> insert = jjDatabase.separateRow(db2.insert(Tice_class.tableName, map2));
//                    jjFileTxt.write(new File("D:\\a.txt"), "Class Code'" + workbook.get(i).get("ClassCode").toString().trim() + "' in Term:'"
//                            + workbook.get(i).get("Term").toString().trim() + "' is not Exist and insert in table Class with:'" + insert.get(0).get(Tice_class._id) + "' id.", true, true);
//
//                    if (insert.size() > 0) {
//                        map.put(Tice_workbook._classId, insert.get(0).get(Tice_class._id));
//                    } else {
//                        map.put(Tice_workbook._classId, 8888);
//                    }
//                }
//            } else {
//                jjFileTxt.write(new File("D:\\a.txt"), "Class Code'" + workbook.get(i).get("ClassCode").toString().trim() + "' in Term:'"
//                        + workbook.get(i).get("Term").toString().trim() + "' is not Exist and insert in table workbook with:'9999' classId.", true, true);
//                map.put(Tice_workbook._classId, 9999);
//            }
//            db2.insert(Tice_workbook.tableName, map);
//        }
    }
    public static List<Map<String, Object>> clazz_in_access_tice;

    public static String getClassCodeFromTiceAccessDb(String classCode, String term) {
        for (int i = 0; i < clazz_in_access_tice.size(); i++) {
            if (clazz_in_access_tice.get(i).get("ClassCode").toString().equals(classCode)
                    && clazz_in_access_tice.get(i).get("Term").toString().equals(term)) {
                return clazz_in_access_tice.get(i).get("No").toString();
            }
        }
        return "";
    }
}
