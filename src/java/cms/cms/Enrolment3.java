package cms.cms;

import cms.tools.*;
import cms.access.*;
import static cms.cms.Comment._name_Full;
import java.io.File;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Enrolment3 {

    public static String tableName = "enrolment3";
    public static String _id = "id";
    public static String _name_Full = "enrolment3_name";
    public static String _tell = "enrolment3_tell";
    public static String _mobile = "enrolment3_mobile";
    public static String _birthdate = "enrolment3_birthdate";
    public static String _marital_status = "enrolment3_marital_status";
    public static String _certificate = "enrolment3_certificate";
    public static String _major = "enrolment3_major";
    public static String _university = "enrolment3_university";
    public static String _starting_date = "enrolment3_starting_date";
    public static String _end_date = "enrolment3_end_date";
    public static String _favorite_group = "enrolment3_favorite_group";
    public static String _cv = "enrolment3_cv";
    public static String _publish = "enrolment3_publish";
    public static String _live = "enrolment3_live";
    public static String _address = "enrolment3_address";
    public static String _pic = "enrolment3_pic";
    public static String _file1 = "enrolment3_file1";
    public static String _file2 = "enrolment3_file2";
    public static String _elementary = "enrolment3_elementary";
    public static String _intermediate = "enrolment3_intermediate";
    public static String _advanced = "enrolment3_advanced";
    public static String _specific_course = "enrolment3_specific_course";
    public static String _speaking = "enrolment3_speaking";
    public static String _listening = "enrolment3_listening";
    public static String _writing = "enrolment3_writing";
    public static String _reading = "enrolment3_reading";
    public static String _date = "enrolment3_date";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 61;
    public static int rul_dlt = 62;
    public static int rul_senEmail = 0;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshEnrolment3' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='25%'>نام</th>");
            html.append("<th width='20%'>مدرک تحصیلی</th>");
            html.append("<th width='20%'>رشته تحصیلی</th>");
            html.append("<th width='20%'>تاریخ ارسال</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("<th width='5%'>چاپ</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_name_Full).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_certificate).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_major).toString()) + "</td>");
                html.append("<td class='l'>" + (jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString())) + "</td>");
                html.append("<td class='c mousePointer' onclick='cmsEnrolment3.m_select(" + row.get(i).get(_id) + ");' ><img src='img/l.png' style='height:30px'/></td>");
                html.append("<td class='c mousePointer' onclick='printEnrolment3(" + row.get(i).get(_id) + ");'><img src='img/3.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swEnrolmentTbl3";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshEnrolment3", height, 0, "", "لیست فرم های استخدام");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
            File pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic).toString().replace("%20", " "));
            if (pic.exists()) {
                pic.delete();
            }
            String name = row.get(0).get(_pic).toString().substring(0, row.get(0).get(_pic).toString().length() - 4);
            String ex = row.get(0).get(_pic).toString().substring(row.get(0).get(_pic).toString().length() - 4, row.get(0).get(_pic).toString().length());
            File pic_small = new File(folderAddress.getAbsolutePath() + "/" + name.replace("%20", " ") + "_small" + ex);
            if (pic_small.exists()) {
                pic_small.delete();
            }
            File fileZamime = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_file1).toString().replace("%20", " "));
            if (fileZamime.exists()) {
                fileZamime.delete();
            }
            File fileZamime2 = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_file2).toString().replace("%20", " "));
            if (fileZamime2.exists()) {
                fileZamime2.delete();
            }
            return Js.jjEnrolment.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            map.put(_birthdate, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birthdate), false));
            map.put(_starting_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _starting_date), false));
            map.put(_end_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _end_date), false));
            map.put(_date, new jjCalendar_IR().getDBFormat_8length());

            map.put(_file1, jjTools.getParameter(request, _file1));
            map.put(_file2, jjTools.getParameter(request, _file2));
            map.put(_pic, jjTools.getParameter(request, _pic));

            map.put(_cv, jjTools.getParameter(request, _cv));
            map.put(_publish, jjTools.getParameter(request, _publish));
            map.put(_live, jjTools.getParameter(request, _live));
            map.put(_address, jjTools.getParameter(request, _address) + " " + jjTools.getParameter(request, "enrolment3_email"));

            map.put(_elementary, jjNumber.isDigit(jjTools.getParameter(request, _elementary).toString()) ? Integer.parseInt(jjTools.getParameter(request, _elementary)) : 0);
            map.put(_intermediate, jjNumber.isDigit(jjTools.getParameter(request, _intermediate).toString()) ? Integer.parseInt(jjTools.getParameter(request, _intermediate)) : 0);
            map.put(_advanced, jjNumber.isDigit(jjTools.getParameter(request, _advanced).toString()) ? Integer.parseInt(jjTools.getParameter(request, _advanced)) : 0);
            map.put(_specific_course, jjNumber.isDigit(jjTools.getParameter(request, _specific_course).toString()) ? Integer.parseInt(jjTools.getParameter(request, _specific_course)) : 0);

            map.put(_certificate, jjTools.getParameter(request, _certificate));
            map.put(_favorite_group, jjTools.getParameter(request, _favorite_group));
            map.put(_listening, jjTools.getParameter(request, _listening));
            map.put(_major, jjTools.getParameter(request, _major));
            map.put(_marital_status, jjTools.getParameter(request, _marital_status));
            map.put(_writing, jjTools.getParameter(request, _writing));
            map.put(_university, jjTools.getParameter(request, _university));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_speaking, jjTools.getParameter(request, _speaking));
            map.put(_reading, jjTools.getParameter(request, _reading));
            map.put(_name_Full, jjTools.getParameter(request, _name_Full));
            String mobile = jjTools.getParameter(request, _mobile);
            map.put(_mobile, mobile);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "insert Fail;";
                }
                return Js.dialog(errorMessage);
            }

            if (jjValidation.isEmail(jjTools.getParameter(request, "enrolment3_email").toString())) {
                String a = "";
                a += "دوست گرامی،";
                a += "<br/>";
                a += "فرم درخواست کار شما در سایت"
                        + " "
                        + " "
                        + " دریافت شد";
                a += ".";
                a += "در صورت واجد شرایط بودن شما";
                a += "جهت مصاحبه حضوری با شما تماس گرفته خواهد شد.";
                a += ".";
                if (!Server.sendEmail("", jjTools.getParameter(request, "enrolment3_email").toString(), "تایید درخواست کار توسط شما", a, true, request)) {
                }
            }
             String a="";
            if (!mobile.isEmpty()) {
                String result = sms.sendSMS(request, db,"",mobile);
                a += "<br/> یک پیامک تایید برای شما ارسال شد" + result + "<br/>";
            }
//            Map<String, Object> map2 = new HashMap<String, Object>();
//            map2.put(Comment._date, new jjCalendar_IR().getDBFormat_8length());
//            map2.put(Comment._email, "");
//            map2.put(Comment._name_Full, jjTools.getParameter(request, _name_Full));
//            map2.put(Comment._tell, jjTools.getParameter(request, _tell));
//            map2.put(Comment._text, "توسط کاربران سایت یک فرم استخدام ارسال شده است");
//            map2.put(Comment._title, "فرم استخدام جدید");
//            map2.put(Comment._answer, "");
//            db.insert(Comment.tableName, map2).getRowCount();
            a += "عملیات ارسال فرم استخدام به درستی صورت پذیرفت";
            a += ".";
            a += "<br/>";
            a += "جهت برگزاری آزمون کتبی و مصاحبه با شما تماس گرفته خواهد شد.";

            return Js.dialog(a);
        } catch (Exception ex) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put(Comment._date, new jjCalendar_IR().getDBFormat_8length());
            map2.put(Comment._email, "");
            map2.put(Comment._name_Full, jjTools.getParameter(request, _name_Full));
            map2.put(Comment._tell, jjTools.getParameter(request, _tell));
            map2.put(Comment._text, map.toString());
            map2.put(Comment._title, "اشکال در درج فرم استخدام");
            map2.put(Comment._answer, "");
            db.insert(Comment.tableName, map2).getRowCount();
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();
            StringBuffer html2 = new StringBuffer();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));

            html.append(Js.setValDate("#" + _birthdate, row.get(0).get(_birthdate)));
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setValDate("#" + _starting_date, row.get(0).get(_starting_date)));
            html.append(Js.setValDate("#" + _end_date, row.get(0).get(_end_date)));

            html.append(Js.setVal("#" + _name_Full, row.get(0).get(_name_Full)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _advanced, row.get(0).get(_advanced)));
            html.append(Js.setVal("#" + _certificate, row.get(0).get(_certificate)));
            html.append(Js.setVal("#" + _cv, row.get(0).get(_cv)));
            html.append(Js.setVal("#" + _elementary, row.get(0).get(_elementary)));
            html.append(Js.setVal("#" + _favorite_group, row.get(0).get(_favorite_group)));
            html.append(Js.setVal("#" + _intermediate, row.get(0).get(_intermediate)));
            html.append(Js.setVal("#" + _listening, row.get(0).get(_listening)));
            html.append(Js.setVal("#" + _live, row.get(0).get(_live)));
            html.append(Js.setVal("#" + _major, row.get(0).get(_major)));
            html.append(Js.setVal("#" + _marital_status, row.get(0).get(_marital_status)));
            html.append(Js.setVal("#" + _mobile, row.get(0).get(_mobile)));
            html.append(Js.setVal("#" + _writing, row.get(0).get(_writing)));
            html.append(Js.setVal("#" + _university, row.get(0).get(_university)));
            html.append(Js.setVal("#" + _tell, row.get(0).get(_tell)));
            html.append(Js.setVal("#" + _specific_course, row.get(0).get(_specific_course)));
            html.append(Js.setVal("#" + _speaking, row.get(0).get(_speaking)));
            html.append(Js.setVal("#" + _reading, row.get(0).get(_reading)));
            html.append(Js.setVal("#" + _publish, row.get(0).get(_publish)));
            html.append(Js.setHtml("#enrolment3_pic", (row.get(0).get(_pic).toString().equals("") ? ""
                    : "<img src='upload/" + row.get(0).get(_pic).toString() + "' style='width:128px'>")));
            html.append(Js.setHtml("#enrolment3_file1", (row.get(0).get(_file1).toString().equals("") ? ""
                    : "<a href='upload/" + row.get(0).get(_file1).toString() + "'>"
                    + row.get(0).get(_file1).toString() + "</a>")));
            html.append(Js.setHtml("#enrolment3_file2", (row.get(0).get(_file2).toString().equals("") ? ""
                    : "<a href='upload/" + row.get(0).get(_file2).toString() + "'>"
                    + row.get(0).get(_file2).toString() + "</a>")));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);

            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_Enrolment3\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Enrolment3", Js.jjEnrolment3.delete(id)));
            }
            return (Js.setHtml("#enrolment3_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
