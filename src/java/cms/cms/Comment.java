package cms.cms;

import cms.tools.*;
import cms.access.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Comment {

    public static String tableName = "comment";
    public static String _id = "id";
    public static String _text = "comment_text";
    public static String _name_Full = "comment_full_name";
    public static String _email = "comment_email";
    public static String _date = "comment_date";
    public static String _tell = "comment_tell";
    public static String _title = "comment_title";
    public static String _answer = "comment_answer";
    public static String _char1 = "comment_char1";
    public static String _char2 = "comment_char2";
    public static String _char3 = "comment_char3";
    public static String _int1 = "comment_int1";
    public static String _int2 = "comment_int2";
    public static String _int3 = "comment_int3";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 11;
    public static int rul_edt = 12;
    public static int rul_dlt = 13;
    public static int rul_senEmail = 14;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshComment' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>ارسال در</th>");
            html.append("<th width='25%'>نام فرستنده</th>");
            html.append("<th width='45%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("<th width='5%'>چاپ</th>");
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            if (accDel) {
                html.append("<th width='5%'>حذف</th>");
            }
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_name_Full).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='c mousePointer' onclick='cmsComment.m_select(" + row.get(i).get(_id) + ");'><img src='img/l.png' style='height:30px'/></td>");
                html.append("<td class='c mousePointer' onclick='printComment(" + row.get(i).get(_id) + ");'><img src='img/3.png' style='height:30px'/></td>");
                if (accDel) {
                    html.append("<td class='c mousePointer' onclick='cmsComment.m_delete(" + row.get(i).get(_id) + ");'><img src='img/3.png' style='height:30px'/></td>");
                }
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swCommentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshComment", height, 0, "", "لیست پیام مدیر به کاربران");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sendAnswer(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_senEmail);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String email = jjTools.getParameter(request, _email);
            if (!jjValidation.isEmail(email)) {
                String errorMessage = "ایمیل موجود معتبر نمی باشد.";
                return Js.dialog(errorMessage);
            }
            if (!Server.sendEmail("", email, "Your Answer from " +"", jjTools.getParameter(request, _answer), true, request)) {
                String errorMessage = "ارسال پیام به درستی صورت نپذیرفت.";
                return Js.dialog(errorMessage);
            }
            String message = "ارسال پیام به درستی صورت پذیرفت";
            return Js.dialog(message);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_date, new jjCalendar_IR().getDBFormat_8length());
            map.put(_email, jjTools.getParameter(request, _email));
            map.put(_name_Full, jjTools.getParameter(request, _name_Full));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_text, jjTools.getParameter(request, _text));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_answer, jjTools.getParameter(request, _answer));

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String mobile=jjTools.getParameter(request, _tell);
            String a="عملیات ارسال پیام شما به درستی صورت پذیرفت.";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_date, new jjCalendar_IR().getDBFormat_8length());
            map.put(_email, jjTools.getParameter(request, _email));
            map.put(_name_Full, jjTools.getParameter(request, _name_Full));
            map.put(_tell, mobile);
            map.put(_text, jjTools.getParameter(request, _text));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_answer, jjTools.getParameter(request, _answer));

            if (db.insert(tableName, map).getRowCount() < 1) {
                String errorMessage = "عملیات ارسال پیام شما به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Insert Fail;";
                }
                return Js.dialog(errorMessage);
            }
            String email = jjTools.getParameter(request, _email);
//            if (jjValidation.isEmail(email)) {
//                String answer = "";
//                if (jjTools.isLangEn(request)) {
//                    jjCalendar_EN en = new jjCalendar_EN();
//                    answer = "Thanks for your comment in " + Server.siteName + " on " + en.toString();
//                } else {
//                    jjCalendar_IR ir = new jjCalendar_IR();
//                    answer = "از ارسال پیام شما در سایت " + Server.siteName + " در " + ir.toString() + "  متشکریم. ";
//                }
//                if (!Server.send(Server.siteName, email, Server.siteName + "Thanks for comment", answer, true, request)) {
//                }
//            }

            String title = "تایید دریافت نظرات";
            String body = "";
            body += "هیئت مدیره موسسه ضمن تشکر از  ارائه نظرات سازنده شما،";
            body += "تلاش می کند تا جهت بهینه سازی شرایط،";
            body += "نقطه نظرات شمارا اعمال نماید.";
            body += "<br/>";
            body += "با سپاس فراوان";
            body += " هیئت مدیره موسسه";
            body += "";
            if (jjTools.getParameter(request, _title).equals("رزرو ثبت نام")) {
                title = "تایید رزرو ثبت نام";
                body = "";
                body += "ضمن تشکر از رزرو ثبت نام شما،";
                body += "دراولین فرصت برای تکمیل روند ثبت نام شما با شما تماس گرفته خواهد شد.";
            }else if (jjTools.getParameter(request, _title).contains("وقت آزاد")) {
                title = "تایید دریافت فرم زمان آزاد";
                body = "";
                body += "همکار گرامی";
                body += "،";
                body += "فرم تکمیل شده ثبت زمان آزاد شما جهت ترم آینده دریافت گردید.";
                body += "<br/>";
                body += "با سپاس فراوان";
                body += "<br/>";
                body += "هیئت مدیره موسسه";
            }
            if (jjValidation.isEmail(email)) {
                if (!Server.sendEmail("", email, title, body, true, request)) {
                }
            }
            if (jjTools.getParameter(request, _title).equals("رزرو ثبت نام")) {
                a = "با تشکر از رزرو ثبت نام شما";
                a += "،";
                a += "<br/>";
                a += "در اولین فرصت برای تکمیل روند ثبت نام با شما تماس گرفته خواهد شد";
                a += ".";                
            }else
            if (jjTools.getParameter(request, _title).contains("وقت آزاد")) {
                a = "پر نمودن فرم زمان آزاد شما با موفقیت انجام شد";
                a += ".";
                a += " با تشکر";
            }
            // ارسال پیامک-----------------------------------------------------------
            if (!mobile.isEmpty()) { 
                String result=sms.sendSMS(request, db,"",mobile);
                a += "<br/> یک پیامک تایید برای شما ارسال شد" + result + "<br/>";
            }
            
            return Js.dialog(a);
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
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjComment.refresh();
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
            html.append(Js.setVal("#" + _email, row.get(0).get(_email)));
            html.append(Js.setVal("#" + _name_Full, row.get(0).get(_name_Full)));
            html.append(Js.setVal("#" + _tell, row.get(0).get(_tell)));
            html.append(Js.setVal("#" + _text, row.get(0).get(_text)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _answer, row.get(0).get(_answer)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_Comment\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_Comment", Js.jjComment.edit()));
            }
            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_Comment\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Comment", Js.jjComment.delete(id)));
            }
            return (Js.setHtml("#Comment_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
