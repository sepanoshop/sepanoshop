package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Enrolment {

    public static String tableName = "enrolment";
    public static String _id = "id";
    public static String _name_Full = "enrolment_name";
    public static String _tell = "enrolment_tell";
    public static String _date = "enrolment_date";
    public static String _send_date = "enrolment_send_date";
    public static String _melli_code = "enrolment_melli_code";
    public static String _degree = "enrolment_degree";
    public static String _course = "enrolment_course";
    public static String _email = "enrolment_email";
    public static String _address = "enrolment_address";
    public static String _courses = "enrolment_courses";
    public static String _jobs = "enrolment_jobs";
    public static String _file = "enrolment_file";
    public static String _pic = "enrolment_pic";
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
            html.append("<table id='refreshEnrolment' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='30%'>نام</th>");
            html.append("<th width='20%'>مدرک تحصیلی</th>");
            html.append("<th width='20%'>رشته تحصیلی</th>");
            html.append("<th width='20%'>تاریخ ارسال</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr onclick='cmsEnrolment.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_name_Full).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_degree).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_course).toString()) + "</td>");
                html.append("<td class='r'>" + (jjCalendar_IR.getViewFormatRtlLeft(row.get(i).get(_course).toString())) + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swEnrolmentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshEnrolment", height, 0, "", "لیست فرم های استخدام");
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
            if (row.size() == 0) {
                return Js.dialog("رکوردی با این کد وجود ندارد");
            }
            File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
            File pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic).toString().replace("%20", " "));
            if (pic.exists()) {
                pic.delete();
            }
            int dot = row.get(0).get(_pic).toString().lastIndexOf(".");
            if (dot > -1) {
                String name = row.get(0).get(_pic).toString().substring(0, dot);
                String ex = row.get(0).get(_pic).toString().substring(dot, row.get(0).get(_pic).toString().length());
                File pic_small = new File(folderAddress.getAbsolutePath() + "/" + name.replace("%20", " ") + "_small" + ex);
                if (pic_small.exists()) {
                    pic_small.delete();
                }
            }
            File fileZamime = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_file).toString().replace("%20", " "));
            if (fileZamime.exists()) {
                fileZamime.delete();
            }
            return Js.jjEnrolment.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            map.put(_send_date, new jjCalendar_IR().getDBFormat_8length());
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_course, jjTools.getParameter(request, _course));
            map.put(_courses, jjTools.getParameter(request, _courses));
            map.put(_degree, jjTools.getParameter(request, _degree));
            map.put(_email, jjTools.getParameter(request, _email));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_jobs, jjTools.getParameter(request, _jobs));
            map.put(_melli_code, jjTools.getParameter(request, _melli_code));
            map.put(_name_Full, jjTools.getParameter(request, _name_Full));
            map.put(_pic, jjTools.getParameter(request, _pic));
            map.put(_tell, jjTools.getParameter(request, _tell));


            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "insert Fail;";
                }
                return Js.dialog(errorMessage);
            }

//            Map<String, Object> map2 = new HashMap<String, Object>();
//            map2.put(Comment._date, new jjCalendar_IR().getDBFormat_8length());
//            map2.put(Comment._email, jjTools.getParameter(request, _email));
//            map2.put(Comment._name_Full, jjTools.getParameter(request, _name_Full));
//            map2.put(Comment._tell, jjTools.getParameter(request, _tell));
//            map2.put(Comment._text, "توسط کاربران سایت یک فرم استخدام ارسال شده است");
//            map2.put(Comment._title, "فرم استخدام جدید");
//            map2.put(Comment._answer, "");
//            db.insert(Comment.tableName, map2).getRowCount();
            String email = jjTools.getParameter(request, _email);
            if (jjValidation.isEmail(email)) {
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
                if (!Server.sendEmail("", email, "تایید درخواست کار توسط شما", a, true, request)) {
                }
            }

            String a = "عملیات ارسال فرم استخدام به درستی صورت پذیرفت";
            a += ".";
            a += "<br/>";
            a += "جهت مصاحبه با شما تماس گرفته خواهد شد.";
            return Js.dialog(a);
        } catch (Exception ex) {
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
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setValDate("#" + _send_date, row.get(0).get(_send_date)));
            html.append(Js.setVal("#" + _email, row.get(0).get(_email)));
            html.append(Js.setVal("#" + _name_Full, row.get(0).get(_name_Full)));
            html.append(Js.setVal("#" + _tell, row.get(0).get(_tell)));
            html.append(Js.setVal("#" + _melli_code, row.get(0).get(_melli_code)));
            html.append(Js.setVal("#" + _degree, row.get(0).get(_degree)));
            html.append(Js.setVal("#" + _course, row.get(0).get(_course)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _courses, row.get(0).get(_courses)));
//            html.append(Js.setVal("#" + _file, row.get(0).get(_file)));
            html.append(Js.setVal("#" + _jobs, row.get(0).get(_jobs)));
            html.append(Js.setHtml("#enrolment_file_and_pic",
                    (row.get(0).get(_pic).toString().equals("") ? "" : "<img src='upload/" + row.get(0).get(_pic).toString() + "' style='width:128px'>")
                    + "<br/>" + (row.get(0).get(_file).toString().equals("") ? "" : "<a href='upload/" + row.get(0).get(_file).toString() + "'>" + row.get(0).get(_file).toString() + "</a>")));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);

            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_Enrolment\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Enrolment", Js.jjEnrolment.delete(id)));
            }
            return (Js.setHtml("#enrolment_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
