package cms.access;

import cms.tools.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class Access_Group_User {

    public static String tableName = "access_user_group";
    public static String _id = "id";
    public static String _user_id = "user_id";
    public static String _group_id = "group_id";
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt = 0;
    public static int rul_dlt = 0;

    /**
     *
     * @param height is int height of table
     * @param sort is number of default sort column number
     * @param panel is container id
     */
    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
        if (!hasAccess.equals("")) {
            return hasAccess;
        }
        boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
        boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
        StringBuffer html = new StringBuffer();
        DefaultTableModel dtm = db.Select(tableName);
        List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
        html.append("<table id='refreshContent'><thead>");
        html.append("<th width='5%'>کد</th>");
        html.append("<th width='70%'>کد گروه</th>");
        if (accDel) {
            html.append("<th width='5%'>حذف</th>");
        }
        if (accEdt) {
            html.append("<th width='5%'>ویرایش</th>");
        }
        html.append("</thead><tbody>");
        for (int i = 0; i < row.size(); i++) {
            html.append("<tr>");
            html.append("<td class='tahoma10'>" + (row.get(i).get(_id).toString()) + "</td>");
            html.append("<td class='tahoma10' style='text-align: right;padding:5px'>" + (row.get(i).get(_group_id).toString()) + "</td>");
            if (accDel) {
                html.append("<td><img src='img/d.png' style='cursor: pointer;height:35px' onclick='DeleteContent(" + row.get(i).get(_id) + ")' /></td>");
            }
            if (accEdt) {
                html.append("<td><img src='img/e.png' style='cursor: pointer;height:35px' onclick='SelectContent(" + row.get(i).get(_id) + ")' /></td>");
            }
            html.append("</tr>");
        }
        html.append("</tbody></table>");
        String height = jjTools.getParameter(request, "height");
        String sort = jjTools.getParameter(request, "sort");
        String panel = jjTools.getParameter(request, "panel");
        if (!jjNumber.isDigit(height)) {
            height = "400";
        }
        if (!jjNumber.isDigit(sort)) {
            sort = "0";
        }
        if (panel.equals("")) {
            panel = "sw";
        }
        String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
        html2 += ("$('#refreshContent').dataTable({'sScrollY': " + height + ",'bJQueryUI': true,'sPaginationType': 'full_numbers', 'aaSorting': [[ " + sort + ", 'asc' ]] });\n");
        return html2;
    }

    /**
     *
     * @param access_permission_title
     * @param access_permission_des
     */
    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
        if (!hasAccess.equals("")) {
            return hasAccess;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        String user_id = jjTools.getParameter(request, _user_id);
        String group_id = jjTools.getParameter(request, _group_id);
        map.put(_id, jjTools.getParameter(request, _id));
        map.put(user_id, jjNumber.isDigit(user_id) ? jjTools.getParameter(request, user_id) : Integer.parseInt(jjTools.getParameter(request, user_id)));
        map.put(group_id, jjTools.getParameter(request, group_id));

        if (db.insert(tableName, map).getRowCount() == 0) {
            String errorMessage = "عملیات درج به درستی صورت نگرفت.";
            if (jjTools.getParameter(request, "myLang").equals("en")) {
                errorMessage = "Edit Fail;";
            }
            return Js.dialog(errorMessage);
        }
        return "";
    }

    /**
     *
     * @param id
     * @param access_permission_title
     * @param access_permission_des
     */
    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
        if (!hasAccess.equals("")) {
            return hasAccess;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        String user_id = jjTools.getParameter(request, _user_id);
        String group_id = jjTools.getParameter(request, _group_id);
        map.put(_id, jjTools.getParameter(request, _id));
        map.put(user_id, jjNumber.isDigit(user_id) ? jjTools.getParameter(request, user_id) : Integer.parseInt(jjTools.getParameter(request, user_id)));
        map.put(group_id, jjTools.getParameter(request, group_id));

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
    }

    /**
     *
     * @param id
     */
    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
        return "";
    }

    /**
     *
     * @param id
     */
    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String id = jjTools.getParameter(request, _id);
        String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
        if (!errorMessageId.equals("")) {
            if (jjTools.isLangEn(request)) {
                errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
            }
            return Js.dialog(errorMessageId);
        }
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
        if (row.size() == 0) {
            String errorMessage = "رکوردی با این کد وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "Select Fail;";
            }
            return Js.dialog(errorMessage);
        }
        StringBuffer html = new StringBuffer();
        html.append(Js.setVal(tableName + "_" + _id, row.get(0).get(_id)));
        html.append(Js.setVal(_group_id, row.get(0).get(_group_id)));
        html.append(Js.setVal(_user_id, row.get(0).get(_user_id)));
        return html.toString();
    }
}
