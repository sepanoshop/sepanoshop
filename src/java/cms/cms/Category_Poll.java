package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.util.HashMap;
import jj.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Category_Poll {

    public static String tableName = "category_poll";
    public static String _id = "id";
    public static String _which = "category_poll_which";
    public static String _user_id = "category_poll_user_id";
    public static String _answer = "category_poll_answer";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 6;
    public static int rul_ins = 7;
    public static int rul_edt = 8;
    public static int rul_dlt = 9;
    public static int rul_lng = 10;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            List<Map<String, Object>> rowPolls = jjDatabase.separateRow(db.Select(Poll.tableName));
            html.append("<table id='refreshCategoryPoll' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='40%'>سوال</th>");
            html.append("<th width='15%'>جواب</th>");
            html.append("<th width='15%'>نام</th>");
            html.append("<th width='20%'>ایمیل</th>");
            html.append("<th width='5%'>حذف</th>");
            html.append("</thead><tbody>");
            List<Map<String, Object>> users = jjDatabase.separateRow(db.Select(Access_User.tableName));
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr>");
                html.append("<td class='c'>" + (row.get(i).get(_id)) + "</td>");
                html.append(getPoolQu(rowPolls, row.get(i).get(_which).toString()));
                html.append(getPoolAns(rowPolls, row.get(i).get(_which).toString(), row.get(i).get(_answer).toString()));
//                String a = getStudent(db, row.get(i).get(_user_id).toString());
//                html.append(a.equals("") ? getUser(users, row.get(i).get(_user_id).toString()) : a);
                html.append("<td class='c mousePointer' onclick='cmsPoll.m_delete(" + row.get(i).get(_id) + ");'><img src='img/3.png' style='height:30px'/></td>");

                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swCategoryPollTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryPoll", height, 0, "", "لیست نظر سنجی ها");
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
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryPoll.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_answer, Integer.parseInt(jjTools.getParameter(request, _answer).toString()));
            map.put(_which, Integer.parseInt(jjTools.getParameter(request, _which).toString()));
            map.put(_user_id, (jjTools.getParameter(request, _user_id).toString()));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return "sw('$poll')";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getUser(List<Map<String, Object>> row, String id) throws Exception {
        try {
            for (int i = 0; i < row.size(); i++) {
                ServerLog.Print(">>>>>>>>" + id);
                if (row.get(i).get(Access_User._id).toString().equals(id)) {
                    return ("<td class='tahoma10 r'>" + (row.get(i).get(Access_User._id) + ". " + row.get(i).get(Access_User._name) + "&nbsp;" + row.get(i).get(Access_User._family) + "</td>"
                            + "<td class='tahoma10 l'>" + row.get(i).get(Access_User._email)) + "</td>");
                }
            }
            return ("<td class='tahoma10 r'>" + "نا مشخص" + "</td><td class='tahoma10 l'>" + id + "</td>");
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static String getPoolQu(List<Map<String, Object>> row, String id) throws Exception {
        try {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(Poll._id).toString().equals(id)) {
                    return ("<td class='tahoma10 r'>" + id + ". " + (row.get(i).get(Poll._qu)) + "</td>");
                }
            }
            return ("<td class='tahoma10 r'>" + "نا مشخص" + "</td>");
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static String getPoolAns(List<Map<String, Object>> row, String id, String ansId) throws Exception {
        try {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).get(Poll._id).toString().equals(id)) {
                    if (ansId.equals("1")) {
                        return ("<td class='tahoma10 r'>1. " + (row.get(i).get(Poll._an1)) + "</td>");
                    } else if (ansId.equals("2")) {
                        return ("<td class='tahoma10 r'>2. " + (row.get(i).get(Poll._an2)) + "</td>");
                    } else if (ansId.equals("3")) {
                        return ("<td class='tahoma10 r'>3. " + (row.get(i).get(Poll._an3)) + "</td>");
                    } else if (ansId.equals("4")) {
                        return ("<td class='tahoma10 r'>4. " + (row.get(i).get(Poll._an4)) + "</td>");
                    } else if (ansId.equals("5")) {
                        return ("<td class='tahoma10 r'>5. " + (row.get(i).get(Poll._an5)) + "</td>");
                    } else if (ansId.equals("6")) {
                        return ("<td class='tahoma10 r'>6. " + (row.get(i).get(Poll._an6)) + "</td>");
                    }
                }
            }
            return ("<td class='tahoma10 r'>" + "نا مشخص" + "</td>");
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
