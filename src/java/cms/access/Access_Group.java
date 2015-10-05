package cms.access;

import cms.tools.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Arvin1
 */
public class Access_Group {

    public static String tableName = "access_group";
    public static String _id = "id";
    public static String _title = "group_title";
    public static String _creator = "group_creator";
    public static String _des = "group_des";
    public static String _chk = "group_c";
    public static int chkNumber = 110;// end of access_group table in database access_group_groupec110
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 33;
    public static int rul_ins = 34;
    public static int rul_edt = 35;
    public static int rul_dlt = 36;

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
        StringBuffer html = new StringBuffer();
        String where = "";
        String creator = jjTools.getSessionAttribute(request, "#ID");
        if (jjNumber.isDigit(creator)) {
            where = _creator + "=" + creator;
        }
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, where));
        html.append("<table id='refreshAccessGroup' dir='rtl'><thead>");
        html.append("<th width='5%'>کد</th>");
        html.append("<th width='90%'>عنوان</th>");
        html.append("<th width='5%'>عملیات</th>");
        html.append("</thead><tbody>");
        for (int i = 0; i < row.size(); i++) {
            html.append("<tr  onclick='cmsGroup.m_select(" + row.get(i).get(_id) + ");' class='mousePointer' >");
            html.append("<td class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_id).toString()) + "</td>");
            html.append("<td class='tahoma10' style='text-align: right;'>" + (row.get(i).get(_title).toString()) + "</td>");
            html.append("<td style='text-align: center;'><img src='img/l.png' style='cursor: pointer;height:30px' onclick='cmsGroup.m_select(" + row.get(i).get(_id) + ");' /></td>");
            html.append("</tr>");
        }
        html.append("</tbody></table>");
        String height = jjTools.getParameter(request, "height");
        String panel = jjTools.getParameter(request, "panel");
        if (!jjNumber.isDigit(height)) {
            height = "400";
        }
        if (panel.equals("")) {
            panel = "swGroupTbl";
        }
        String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
        html2 += Js.table("#refreshAccessGroup", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "10" : "", "لیست گروه ها");
        return html2;
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
        if (accIns) {
            html.append(Js.setHtml("#Group_button", "<input type=\"button\" id=\"insert_Group_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
            html.append(Js.buttonMouseClick("#insert_Group_new", Js.jjGroup.insert()));
        }
        return html.toString();
    }

    /**
     *
     * @param content_parent
     * @param content_content
     * @param content_title
     * @param content_lang
     * @param content_parent
     */
    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
        if (!hasAccess.equals("")) {
            return hasAccess;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(_des, jjTools.getParameter(request, _des));
        String creator = jjTools.getSessionAttribute(request, "#ID");
        map.put(_creator, jjNumber.isDigit(creator) ? Integer.parseInt(creator) : 0);
        map.put(_title, jjTools.getParameter(request, _title));
        for (int i = 1; i < chkNumber; i++) {
            String thisRow = _chk + (i < 10 ? "0" + i : i);
            map.put(thisRow, jjTools.getSessionAttribute(request, "#NOACCESS").contains("$" + i + "$") ? 0
                    : Integer.parseInt(jjNumber.isDigit(jjTools.getParameter(request, thisRow)) ? jjTools.getParameter(request, thisRow) : "0"));
        }


        if (db.insert(tableName, map).getRowCount() == 0) {
            String errorMessage = "عملیات درج به درستی صورت نگرفت.";
            if (jjTools.getParameter(request, "myLang").equals("en")) {
                errorMessage = "Edit Fail;";
            }
            return Js.dialog(errorMessage);
        }
        return Js.jjGroup.refresh();
    }

    /**
     *
     * @param id
     * @param group_title
     * @param group_des
     */
    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
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

        String creator = jjTools.getSessionAttribute(request, "#ID");
        if (jjNumber.isDigit(creator)) {
            if (db.Select(tableName, _id + "=" + id + " AND " + _creator + "=" + creator).getRowCount() == 0) {
                return Js.dialog("شما اجازه ویرایش این گروه را ندارید.");
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(_des, jjTools.getParameter(request, _des));
        map.put(_title, jjTools.getParameter(request, _title));
        for (int i = 1; i < chkNumber; i++) {
            String thisRow = _chk + (i < 10 ? "0" + i : i);
            map.put(thisRow, jjTools.getSessionAttribute(request, "#NOACCESS").contains("$" + i + "$") ? 0
                    : Integer.parseInt(jjNumber.isDigit(jjTools.getParameter(request, thisRow))
                    ? jjTools.getParameter(request, thisRow) : "0"));
        }
        ServerLog.Print(map);
        if (creator.equals("1") && id.equals("1")) {
            map.put(_chk + 15, 1);
            map.put(_chk + 16, 1);
            map.put(_chk + 17, 1);
            map.put(_chk + 18, 1);
            map.put(_chk + 33, 1);
            map.put(_chk + 34, 1);
            map.put(_chk + 35, 1);
            map.put(_chk + 36, 1);
        }
        if (creator.equals("1") && jjTools.getParameter(request, _des).toString().equals("$")) {
            map.put(_chk + 15, 1);
            map.put(_chk + 16, 1);
            map.put(_chk + 17, 1);
            map.put(_chk + 18, 1);
            map.put(_chk + 33, 1);
            map.put(_chk + 34, 1);
            map.put(_chk + 35, 1);
            map.put(_chk + 36, 1);
        }
        if (!db.update(tableName, map, _id + "=" + id)) {
            String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "Edit Fail;";
            }
            return Js.dialog(errorMessage);
        }
        return Js.jjGroup.refresh();
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

        String creator = jjTools.getSessionAttribute(request, "#ID");
        if (jjNumber.isDigit(creator)) {
            if (db.Select(tableName, _id + "=" + id + " AND " + _creator + "=" + creator).getRowCount() == 0) {
                return Js.dialog("شما اجازه حذف این گروه را ندارید.");
            }
        }

        if (!db.delete(tableName, _id + "=" + id)) {
            String errorMessage = "عملیات حذف به درستی صورت نگرفت";
            if (jjTools.isLangEn(request)) {
                errorMessage = "Delete Fail;";
            }
            return Js.dialog(errorMessage);
        }
        db.delete(Access_Group_User.tableName, Access_Group_User._group_id + "=" + id);
        return Js.jjGroup.refresh();
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
        html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
        html.append(Js.setVal("#" + _des, row.get(0).get(_des)));
        for (int i = 1; i < chkNumber; i++) {
            String thisRow = _chk + (i < 10 ? "0" + i : i);
            html.append(Js.setVal("#C" + (i < 10 ? "0" + i : i), row.get(0).get(thisRow)));
        }

        boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
        boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

        if (accEdt) {
            html2.append("<input type=\"button\" id=\"edit_Group\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
            html.append(Js.buttonMouseClick("#edit_Group", Js.jjGroup.edit()));
        }
        if (accDel) {
            html2.append("<input type=\"button\" id=\"delete_Group\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
            html.append(Js.buttonMouseClick("#delete_Group", Js.jjGroup.delete(id)));
        }
        return (Js.setHtml("#Group_button", html2.toString())) + html.toString();
    }

    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(tableName));
        html.append("&nbsp;&nbsp;<ul>");
        for (int i = 0; i < row.size(); i++) {
            html.append("<li onclick='swNewsCategory(" + row.get(i).get(_id) + ");'>&nbsp;" + row.get(i).get(_title) + " </li>");
        }
        html.append("</ul>");
        return html.toString();
    }

    public static String getOptions(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(tableName));
        for (int i = 0; i < row.size(); i++) {
            html.append("<option value='" + row.get(i).get(_id) + "'>" + row.get(i).get(_title).toString() + "</option>");
        }
        return html.toString();
    }

    public static String getCheckboxList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        StringBuffer html = new StringBuffer();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));

        String accessGroup = "";
        String groups = "";
        String creator = jjTools.getSessionAttribute(request, "#ID");
        if (jjNumber.isDigit(creator)) {
            List<Map<String, Object>> row2 = jjDatabase.separateRow(db.Select(tableName, _creator + "=" + creator));
            for (int i = 0; i < row2.size(); i++) {
                accessGroup += "$" + row2.get(i).get(_id) + "$";
            }
            List<Map<String, Object>> gr = jjDatabase.separateRow(db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + creator));
            for (int i = 0; i < gr.size(); i++) {
                groups += "$" + gr.get(i).get(Access_Group_User._group_id) + "$";

            }
        }
        String panel = jjTools.getParameter(request, "panel");
        panel = panel.equals("") ? "group_checkbox_list" : panel;

        String userId = jjTools.getParameter(request, "user_id");
        if (row.size() > 0) {
            html.append("<div align='center'><table border='1' style='width: 100%;height: 100' cellpadding='1'><tr>");
            html.append("<td height='100' align='center' width='100%' bgcolor='white' valign='top'>");
            html.append("<div style='padding: 0px;background-color: white;color: black ;border:0px solid black;width:100%;height:100px;overflow:auto;text-align: right'>");
            String selected = "";
            for (int i = 0; i < row.size(); i++) {
                String disable = "disabled=\"disabled\"";
                if (accessGroup.contains("$" + row.get(i).get(_id) + "$")) {
                    disable = "";
                }
                if (jjNumber.isDigit(userId)) {
//                    int rowCount = db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + userId
//                            + " AND " + Access_Group_User._group_id + "=" + row.get(i).get(_id)).getRowCount();//+ " AND " + Access_Group_User._group_id + "<>2001"
//                    selected = groups.contains("$"+row.get(i).get(_id).toString()+"$") ? "checked" : "";
                    int rowCount = db.Select(Access_Group_User.tableName, Access_Group_User._user_id + "=" + userId
                            + " AND " + Access_Group_User._group_id + "=" + row.get(i).get(_id)).getRowCount();//+ " AND " + Access_Group_User._group_id + "<>2001"
                    selected = rowCount > 0 ? "checked" : "";
                }
                html.append("<li style='width: 250px;font-size: 10pt;cursor: pointer;text-align: right;padding: 0px;'>");
                html.append("<input  type='checkbox' style='width: 30px;' "
                        + "id='chk" + row.get(i).get(_id) + "' name='chk" + row.get(i).get(_id) + "' " + selected + " " + disable + "/>");
                html.append(row.get(i).get(_title));
                html.append("</li>");
            }
            html.append("</div></td></table></div>");
        }
        return Js.setHtml("#" + panel, html.toString());
    }
}
