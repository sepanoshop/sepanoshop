package cms.cms;

import cms.tools.jjTools;
import cms.tools.jjValidation;
import cms.access.*;
import cms.tools.Js;
import cms.tools.Server;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.*;

public class Category_Forum {

    public static String tableName = "category_forum";
    public static String _id = "id";
    public static String _title = "category_forum_title";
    public static String _creator = "category_forum_creator";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 29;
    public static int rul_ins = 30;
    public static int rul_edt = 31;
    public static int rul_dlt = 32;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshCategoryForum' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='75%'>عنوان</th>");
            html.append("<th width='15%'>بحث ها</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            Map<String, Integer> forumCount = forumCount(db);
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr onclick='cmsCategoryForum.m_select(" + row.get(i).get(_id) + ");' class='mousePointer' >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='c'>" + (forumCount.get(row.get(i).get(_id)) == null ? 0 : forumCount.get(row.get(i).get(_id))) + "</td>");
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
                panel = "swCategoryForumTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryForum", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "3" : "", "لیست انجمن ها");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static Map<String, Integer> forumCount(jjDatabaseWeb db) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(Forum.tableName));
        for (int i = 0; i < row.size(); i++) {
            map.put(row.get(i).get(Forum._category_id).toString(), map.get(row.get(i).get(Forum._category_id).toString()) == null ? 1 : map.get(row.get(i).get(Forum._category_id).toString()) + 1);
        }
        return map;
    }

    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String id = jjTools.getParameter(request, "id");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "220" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;
            html.append("<a onclick='swAddForumCategory();' class='forumAddForumLink'>");
            html.append(jjTools.isLangFa(request) ? "افزودن بحث ..." : "Add Discussion ...");
            html.append("</a><br/><br/>");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshNews22' dir='rtl' style='color:black;width:100%' class='tahoma10'><thead>");
            html.append("<th width='5%'>ردیف</th>");
            html.append("<th width='75%'>عنوان</th>");
            html.append("<th width='15%'>بحث</th>");
            html.append("<th width='5%'>مشاهده</th>");
            html.append("</thead><tbody>");
            Map<String, Integer> forumCount = forumCount(db);
            int counter = 0;
            for (int i = 0; i < row.size(); i++) {
                counter += 1;
                html.append("<tr  onclick='swForum(" + row.get(i).get(_id) + ");' class='mousePointer' >");
                html.append("<td  class='c'>" + counter + "</td>");
                html.append("<td  class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td  class='c'>" + (forumCount.get(row.get(i).get(_id)) == null ? 0 : forumCount.get(row.get(i).get(_id))) + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");


            String html2 = "$('#" + panel + "').html('" + Js.replacor(html.toString()) + "');\n";
            html2 += Js.table("#refreshNews22", height, Integer.parseInt(sort), "", "لیست انجمن ها");
            return html2;
//            return Content.refresh(request, db, isPost);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryForum_button", "<input type=\"button\" id=\"insert_CategoryForum_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_CategoryForum_new", Js.jjCategoryForum.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();


            map.put(_title, jjTools.getParameter(request, _title));


            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryForum.refresh() + getOptions(request, db, isPost);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insertByUser(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (!jjTools.isUserLogin(request)) {
                return "";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return "sw('$forum');";
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


            map.put(_title, jjTools.getParameter(request, _title));


            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryForum.refresh() + getOptions(request, db, isPost);
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
            if (id.equals("1")) {
                String errorMessageId2 = "شما اجازه حذف این رکورد را ندارید.";
                if (jjTools.isLangEn(request)) {
                    errorMessageId2 = "You can't delete this record.";
                }
                return Js.dialog(errorMessageId2);
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Forum.tableName, Forum._category_id + "=" + id));
            for (int i = 0; i < row.size(); i++) {
                File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                File f = new File(folderAddress.getAbsolutePath() + "/" + row.get(i).get(Forum._url).toString().replace("%20", " "));
                if (f.exists()) {
                    f.delete();
                }
            }
            db.delete(Forum.tableName, Forum._category_id + "=" + id);

            return Js.jjCategoryForum.refresh() + getOptions(request, db, isPost);
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
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_CategoryForum\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_CategoryForum", Js.jjCategoryForum.edit()));
            }
            if (accDel) {
                if (!id.equals("1")) {
                    html2.append("<input type=\"button\" id=\"delete_CategoryForum\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                    html.append(Js.buttonMouseClick("#delete_CategoryForum", Js.jjCategoryForum.delete(id)));
                }
            }
            return (Js.setHtml("#CategoryForum_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryForum_button", "<input type=\"button\" id=\"insert_CategoryForum_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_CategoryForum_new", Js.jjCategoryForum.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(tableName));
            html.append("&nbsp;&nbsp;<ul>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<li onclick='swForumCategory(" + row.get(i).get(_id) + ");'>&nbsp;" + row.get(i).get(_title) + " </li>");
            }
            html.append("</ul>");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOptions(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            if (panel.equals("")) {
                panel = "forum_category_id_select";
            }
            String selectedCategory = "";
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Forum.tableName, Forum._id + "=" + id));
                if (row.size() > 0) {
                    selectedCategory = row.get(0).get(Forum._category_id).toString();
                }
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            for (int i = 0; i < row.size(); i++) {
                String toString = row.get(i).get(_id).toString();
                html.append("<option value='" + toString + "' " + (selectedCategory.equals(toString) ? "selected='selected'" : "")
                        + ">" + row.get(i).get(_title).toString() + "</option>");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
