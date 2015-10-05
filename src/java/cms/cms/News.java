package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

public class News {

    public static String tableName = "news";
    public static String _id = "id";
    public static String _date = "news_date";
    public static String _priority = "news_priority";
    public static String _title = "news_title";
    public static String _content = "news_content";
    public static String _category_id = "news_category_id";
    public static String _parent = "news_parent";
    public static String _lang = "news_lang";
    public static String _pic = "news_pic";//v1.5.0
    public static String _abstract = "news_abstract";//v1.5.0
    public static String _rating = "news_rating";//v1.5.0
    public static String _visit = "news_visit";//v1.5.0
    public static String _likes = "news_likes";//v1.5.0
    public static String _disLikes = "news_disLikes";//v1.5.0
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 37;
    public static int rul_ins = 38;
    public static int rul_edt = 39;
    public static int rul_dlt = 40;
    public static int rul_lng = 41;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName, _parent + "=0");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table id='refreshNews' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='15%'>تاریخ خبر</th>");
            html.append("<th width='70%'>عنوان خبر</th>");
            html.append("<th width='5%'>دسته</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsNews.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>/" + (row.get(i).get(_category_id).toString()) + "/</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px' /></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swNewsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshNews", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "2" : "", "لیست اخبار");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String isBeing(List<Map<String, Object>> rows, String id) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).get(_parent).toString().equals(id)) {
                    return rows.get(i).get(_id).toString();
                }
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#News_button", "<input type=\"button\" id=\"insert_news_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_news_new", Js.jjNews.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchNewsTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));

            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);

            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 1);

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            
            //By Md
            map.put(_abstract, jjTools.getParameter(request, _abstract));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjNews.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchNewsTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }

            Map<String, Object> map = new HashMap<String, Object>();
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_pic, jjTools.getParameter(request, _pic));
            map.put(_abstract, jjTools.getParameter(request, _abstract));
//            map.put(_rating, jjTools.getParameter(request, _rating));
            map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_likes, jjTools.getParameter(request, _likes));
            map.put(_disLikes, jjTools.getParameter(request, _disLikes));

            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));

            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);

            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 1);

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

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
            return Js.jjNews.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchNewsTitle = null;
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
            if (row.size() != 0) {
                String toString = row.get(0).get(_content).toString();
                //
                File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                String[] list = new File(request.getServletContext().getRealPath("/upload")).list();
                for (int i = 0; i < list.length; i++) {
                    if (toString.indexOf("src=\"upload/" + list[i] + "\"") > -1) {
                        File pics = new File(folderAddress.getAbsolutePath() + "/" + list[i]);
                        if (pics.exists()) {
                            pics.delete();
                        }
                        File pics_small = new File(folderAddress.getAbsolutePath() + "/" + list[i].replace(".", "_small."));
                        if (pics_small.exists()) {
                            pics_small.delete();
                        }
                    }
                }
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            db.delete(tableName, _parent + "=" + id);
            return Js.jjNews.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getParent(List<Map<String, Object>> rows, String id) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).get(_parent).toString().equals(id)) {
                    return rows.get(i).get(_id).toString();
                }
            }
            return "";
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
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            String landId=row.get(0).get(_title).toString();

            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title,landId) );
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            html.append(Js.setVal("#" + _abstract, row.get(0).get(_abstract)));
            html.append(Js.setVal("#" + _pic, row.get(0).get(_pic)));
            html.append(Js.setAttr("#news_pic_name_preview", "src", row.get(0).get(_pic).toString()));
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setValEditor(_content, row.get(0).get(_content)));
//            html.append(Js.setValEditor(_content, row.get(0).get(_content)));
            //new in v1.5.0
            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal("#" + _visit, visit));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _visit + "_checkbox", 0));
                html.append(Js.setAttr("#" + _visit, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _visit + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _visit, "disabled"));
            }
            Integer likes = Integer.valueOf(row.get(0).get(_likes).toString());
            html.append(Js.setVal("#" + _likes, likes));
            if (likes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _likes + "_checkbox", 0));
                html.append(Js.setAttr("#" + _likes, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _likes + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _likes, "disabled"));
            }
            Integer dislikes = Integer.valueOf(row.get(0).get(_disLikes).toString());
            html.append(Js.setVal("#" + _disLikes, dislikes));
            if (dislikes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _disLikes + "_checkbox", 0));
                html.append(Js.setAttr("#" + _disLikes, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _disLikes + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _disLikes, "disabled"));
            }
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type='button' id='edit_news' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_news", Js.jjNews.edit()));
            }
            if (accDel && !row.get(0).get(_parent).toString().equals("1")) {
                html2.append("<input type='button' id='delete_news' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_news", Js.jjNews.delete(id)));
            }
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_news' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_news", Js.jjNews.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_news' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_news", Js.jjNews.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_news' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_news", Js.jjNews.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_news' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_news", Js.jjNews.addAr(id)));
                    }
                }
            }
            return (Js.setHtml("#News_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_lang(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal(tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal(_title, title));
            }
            Object priority = row.get(0).get(_priority);
            if (priority != null) {
                html.append(Js.setVal(_priority, priority));
            }
            Object parent = row.get(0).get(_parent);
            if (parent != null) {
                html.append(Js.setVal(_parent, parent));
            }
            Object date = row.get(0).get(_date);
            if (date != null) {
                html.append(Js.setValDate(_date, date));
            }
            Object category_id = row.get(0).get(_category_id);
            if (category_id != null) {
                html.append(Js.setVal(_category_id, category_id));
            }
            html.append(Js.setVal(_lang, "2"));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            return html.toString();
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
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            Object priority = row.get(0).get(_priority);
            if (priority != null) {
                html.append(Js.setVal("#" + _priority, priority));
            }
            Object date = row.get(0).get(_date);
            if (date != null) {
                html.append(Js.setValDate("#" + _date, date));
            }
            Object category_id = row.get(0).get(_category_id);
            if (category_id != null) {
                html.append(Js.setVal("#" + _category_id, category_id));
            }
            html.append(Js.setVal("#" + _lang, 2));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#News_button", "<input type=\"button\" id=\"insert_News_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_News_new", Js.jjNews.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            Object priority = row.get(0).get(_priority);
            if (priority != null) {
                html.append(Js.setVal("#" + _priority, priority));
            }
            Object date = row.get(0).get(_date);
            if (date != null) {
                html.append(Js.setValDate("#" + _date, date));
            }
            Object category_id = row.get(0).get(_category_id);
            if (category_id != null) {
                html.append(Js.setVal("#" + _category_id, category_id));
            }
            html.append(Js.setVal("#" + _lang, 3));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#News_button", "<input type='button' id='insert_News_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_News_new_ar", Js.jjNews.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOneNews(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            //to incriment visited time
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
            List<Map<String, Object>> row;
            row = jjDatabase.separateRow(db.Select(News.tableName, _id + " = " + id));
            if (row.isEmpty()) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            html.append("<div class='newsmainDiv'>");
            html.append("<span class='newsDatespan'>" + jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString()) + "</span>");
            int visit = new Integer(row.get(0).get(_visit).toString());
            if (visit >= 0) {
                html.append("<div class='newsvisitDiv' >" + visit + " بار مشاهده </div>");
            }
            int disLikes = new Integer(row.get(0).get(_disLikes).toString());
            if (disLikes >= 0) {
                html.append("<div class='newsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + " مخالف </div>");
            }
            int likes = new Integer(row.get(0).get(_likes).toString());
            if (likes >= 0) {
                html.append("<div class='newslikeDiv' onclick='newsLike(" + id + ");' >" + likes + " موافق </div>");
            }
            html.append("<img class='newsPicDiv' src='" + row.get(0).get(_pic).toString() + "'/>");
            html.append("<span class='newsTitlespan'><h3>" + row.get(0).get(_title).toString() + "<h3></span>");
            html.append("<div class='newsabstarctDiv'>" + row.get(0).get(_abstract).toString() + "</div>");
            html.append("<div id='newContentDiv' class='newContentDiv'></div>");

            html.append("<span class='moreDatale'>"
                    + "<a onclick='swGetNews(" + row.get(0).get(_category_id).toString() + ");'>" + " مطالب مرتبط" + "</a>"
                    + "</span>");
            html.append("</div>");

            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.setHtml("#newContentDiv", row.get(0).get(_content).toString());
//            row.get(0).get(_content).toString()
//        html2 += Js.table("#sss", height, sort, "");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String newsDisLike(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _disLikes + " = " + _disLikes + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String newsLike(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _likes + " = " + _likes + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            html.append("<ul>");
            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _priority + "=1 AND " + _lang + "=" + (jjTools.isLangFa(request) ? "1" : "2")));
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<li  style='width: " + width + "px;'><table class='tahoma10' style='width: " + width
                        + "px;height: " + height + "px;'>");
                html.append("<tr><td valign='top' class='tahoma12'  dir='" + jjTools.getLangDir(request) + "' style='vertical-align: middle;text-align: " + jjTools.getLangAlign(request) + ";height:20px;padding:10px'>");
                html.append(allNews.get(i).get(_title));
                html.append("</td></tr><tr>");
                html.append("<td valign='top'  dir='" + jjTools.getLangDir(request) + "' style='height:" + (Integer.parseInt(height) - 120) + "px;vertical-align: top;text-align: " + jjTools.getLangAlign(request) + ";padding:10px'>");
                html.append("<div style='overflow: hidden;height:" + (Integer.parseInt(height) - 120) + "px'>");
                html.append(allNews.get(i).get(_content));
                html.append("</div>");
                html.append("</td></tr><tr>");
                html.append("<td valign='top'  dir='rtl' style='text-align: left;height:20px;padding:10px'>");
                html.append("<a onclick=swNews(" + allNews.get(i).get(_id) + ");>");
                html.append(jjTools.isLangFa(request) ? "ادامه مطلب ..." : "... more");
                html.append("</a></td></tr></table></li>");
            }
            html.append("</ul>");
            String panel = jjTools.getParameter(request, "panel");
            String prevBtnSelector = jjTools.getParameter(request, "prevBtnSelector");
            String nextBtnSelector = jjTools.getParameter(request, "nextBtnSelector");
            String auto = jjTools.getParameter(request, "auto");
            String speed = jjTools.getParameter(request, "speed");
            if (panel.equals("")) {
                panel = "newsSlide";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript(panel, prevBtnSelector, nextBtnSelector, speed, auto);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSlider2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            html.append("<ul>");
            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _priority + "=1 AND " + _lang + "=" + (jjTools.isLangFa(request) ? "1" : "2")));
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<li><div dir='" + jjTools.getLangDir(request)
                        + "' style='overflow: hidden;height:" + height + "px;width:" + width + "px;text-align: "
                        + jjTools.getLangAlign(request)
                        + ";'>");
                html.append("<div class='NewsTitle'>"+allNews.get(i).get(_title).toString()+"</div>");
                html.append("<div class='NewsPIC'><img src='"+allNews.get(i).get(_pic).toString()+"'/></div>");
                html.append("<div class='NewsAbstract' >");
                html.append(allNews.get(i).get(_abstract).toString().replace("<li>", "").replace("<li dir='rtl'>", "").replace("</li>", ""));
                html.append("</div>");
                html.append("</div><div valign='top'  dir='" + jjTools.getLangDir(request)
                        + "' style='text-align: left;height:17px;padding:1px;vertical-align: top;'>");
                html.append("<a class='newsMoreInSlider' onclick=swNews(" + allNews.get(i).get(_id) + ");>");
                html.append(jjTools.isLangFa(request) ? "ادامه مطلب ..." : "... more");
                html.append("</a></div></li>");
            }
            html.append("</ul>");
            String panel = jjTools.getParameter(request, "panel");
            if (panel.equals("")) {
                panel = "jjSliderNews";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript2(panel);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSliderRunScript2(String divId) {
        try {
//        return ";\n";
            return "$('#" + divId + "').easySlider({controlsBefore:'<p id=\"controls\">',controlsAfter:'</p>',auto: true,continuous: true});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSliderRunScript(String divId, String prevBtnSelector, String nextBtnSelector, String speed, String auto){
        try {
            speed = speed == "" ? "200" : speed;
            auto = auto == "" ? "4000" : auto;
            return "$('#" + divId + "').jCarouselLite({"
                    + "vertical: false,"
                    + "hoverPause:true,"
                    + "btnPrev: '#" + prevBtnSelector + "',"
                    + "btnNext: '#" + nextBtnSelector + "',"
                    + "visible: 1,"
                    + "auto:" + auto + ","
                    + "speed:" + speed + ""
                    + "});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0
        try {
            if (jjTools.isLangEn(request)) {
                return getList_En(request, db, isPost);
            }
            if (jjTools.isLangAr(request)) {
                return getList_Ar(request, db, isPost);
            }
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuffer html3 = new StringBuffer();//for JQuery statements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
               /*طبقه بندی اخبار را تابع دیگری که از جاوا اسکریپت فراخوانی می شود برمیگرداند*/            
            Integer category_id = new Integer(jjTools.getParameter(request, "id").toString());
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topNewsRow;
            if (category_id == 0) {
                topNewsRow = jjDatabase.separateRow(
                        db.SelectDesc(News.tableName, News._lang + "=1 AND " + News._priority + "<=2" , _date));
            } else {
                topNewsRow = jjDatabase.separateRow(
                        db.SelectDesc(News.tableName, News._lang + "=1 AND " + News._category_id + "=" + category_id , _date));
            }

            //---------------one news post creation
                 /*لیست اخبار را بر میگرداند*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            html.append("<div id='swTopNewsDiv' class='topNewsDiv'></div>");
            if(topNewsRow.isEmpty()){
                temphtml.append("<div class='noAnyThing'>!!! در این بخش موردی برای نمایش وجود ندارد<div>");
            }else{
            for (int i = 0; i < topNewsRow.size(); i++) {
                String id = topNewsRow.get(i).get(_id).toString();
                temphtml.append("<div class='newsmainDiv'>");
                temphtml.append("<span class='newsDatespan'>" + jjCalendar_IR.getViewFormat(topNewsRow.get(i).get(_date).toString()) + "</span>");
                int visit = new Integer(topNewsRow.get(i).get(_visit).toString());
                if (visit >= 0) {
                    temphtml.append("<div class='newsvisitDiv' >" + visit + " بار مشاهده </div>");
                }
                int disLikes = new Integer(topNewsRow.get(i).get(_disLikes).toString());
                if (disLikes >= 0) {
                    temphtml.append("<div class='newsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + " مخالف </div>");
                }
                int likes = new Integer(topNewsRow.get(i).get(_likes).toString());
                if (likes >= 0) {
                    temphtml.append("<div class='newslikeDiv' onclick='newsLike(" + id + ");' >" + likes + " موافق </div>");
                }
                temphtml.append("<a href='Server?do=News.getOneNews&id="+ id +"' onclick='getOneNews(" + id + ");return false;' ><img class='newsPicDiv' src='" + topNewsRow.get(i).get(_pic).toString() + "'/></a>");
                temphtml.append("<span class='newsTitlespan'><h3>" + topNewsRow.get(i).get(_title).toString() + "</h3></span>");
                temphtml.append("<div class='newsabstarctDiv'><h4>" + topNewsRow.get(i).get(_abstract).toString() + "</h4></div>");
                temphtml.append("<span class='moreDatale'>"
                        + "<a href='Server?do=News.getOneNews&id="+ id +"' onclick='getOneNews(" + id + ");return false;'>" + "ادامه مطلب" + "</a>"
                        + "</span>");
                temphtml.append("<span class='coGruopNews'>"
                        + "<a onclick='swGetNews(" + topNewsRow.get(i).get(_category_id).toString() + ");'>" + "مطالب مرتبط" + "</a>"
                        + "</span>");
                temphtml.append("</div>");
            }
            }
            html3.append(Js.setHtml("#swTopNewsDiv", temphtml.toString()));
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
//        html2 += Js.table("#sss", height, sort, "");
            return html2 + html3;
        } catch (Exception ex){
            return Server.ErrorHandler(ex);
        }
    }
    public static String getList_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            ServerLog.Print("Run: News.getList_En");
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            StringBuffer html3 = new StringBuffer();//for JQuery statements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
               /*طبقه بندی اخبار را تابع دیگری که از جاوا اسکریپت فراخوانی می شود برمیگرداند*/            
            Integer category_id = new Integer(jjTools.getParameter(request, "id").toString());
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topNewsRow;
            if (category_id == 0) {
                topNewsRow = jjDatabase.separateRow(
                        db.SelectDesc(News.tableName, News._lang + "=2 AND " + News._priority + "<=2" , _date));
            } else {
                topNewsRow = jjDatabase.separateRow(
                        db.SelectDesc(News.tableName, News._lang + "=2 AND " + News._category_id + "=" + category_id , _date));
            }
            //---------------one news post creation
                 /*لیست اخبار را بر میگرداند*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            html.append("<div id='swTopNewsDiv' class='topNewsDiv'></div>");
            if(topNewsRow.isEmpty()){
                temphtml.append("<div class='noAnyThing'>!!! در این بخش موردی برای نمایش وجود ندارد<div>");
            }else{
            for (int i = 0; i < topNewsRow.size(); i++) {
                String id = topNewsRow.get(i).get(_id).toString();
                temphtml.append("<div class='newsmainDiv'>");
                temphtml.append("<span class='newsDatespan'>" + jjCalendar_IR.getViewFormat(topNewsRow.get(i).get(_date).toString()) + "</span>");
                int visit = new Integer(topNewsRow.get(i).get(_visit).toString());
                if (visit >= 0) {
                    temphtml.append("<div class='newsvisitDiv' >" + visit + " بار مشاهده </div>");
                }
                int disLikes = new Integer(topNewsRow.get(i).get(_disLikes).toString());
                if (disLikes >= 0) {
                    temphtml.append("<div class='newsDisLikeDiv' onclick='newsDisLike(" + id + ");' >" + disLikes + " مخالف </div>");
                }
                int likes = new Integer(topNewsRow.get(i).get(_likes).toString());
                if (likes >= 0) {
                    temphtml.append("<div class='newslikeDiv' onclick='newsLike(" + id + ");' >" + likes + " موافق </div>");
                }
                temphtml.append("<a href='Server?do=News.getOneNews&id="+ id +"' onclick='getOneNews(" + id + ");return false;' ><img class='newsPicDiv' src='" + topNewsRow.get(i).get(_pic).toString() + "'/></a>");
                temphtml.append("<span class='newsTitlespan'><h3>" + topNewsRow.get(i).get(_title).toString() + "</h3></span>");
                temphtml.append("<div class='newsabstarctDiv'><h4>" + topNewsRow.get(i).get(_abstract).toString() + "</h4></div>");
                temphtml.append("<span class='moreDatale'>"
                        + "<a href='Server?do=News.getOneNews&id="+ id +"' onclick='getOneNews(" + id + ");return false;'>" + "ادامه مطلب" + "</a>"
                        + "</span>");
                temphtml.append("<span class='coGruopNews'>"
                        + "<a onclick='swGetNews(" + topNewsRow.get(i).get(_category_id).toString() + ");'>" + "مطالب مرتبط" + "</a>"
                        + "</span>");
                temphtml.append("</div>");
            }
            }
            html3.append(Js.setHtml("#swTopNewsDiv", temphtml.toString()));
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
//        html2 += Js.table("#sss", height, sort, "");
            return html2 + html3;
        } catch (Exception ex){
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            ServerLog.Print("Run: News.getList_Ar");
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            id = jjNumber.isDigit(id) ? id : "1";
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_News.tableName,
                    Category_News._parent + "=" + id + " AND " + Category_News._lang + "=3"));

            html3.append("<span class='newsLink'>");
            html3.append("أخبار");
            html3.append("</span><span class='newsLinkFlash'>&nbsp;>&nbsp;</span>");
            if (rowCategory.size() > 0) {
                html3.append("<a class='newsLink' onclick='swGetNews("
                        + rowCategory.get(0).get(Category_News._parent) + ");'>" + rowCategory.get(0).get(Category_News._title) + "</a>");

                List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._lang + "=3"));
                html3.append("<span class='newsLinkFlash'>&nbsp;&nbsp;(</span>");
                int counter2 = 0;
                for (int i = 0; i < categoryRow.size(); i++) {
                    if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_News._parent)).getRowCount() > 0) {
                        if (!rowCategory.get(0).get(Category_News._id).toString().equals(categoryRow.get(i).get(Category_News._id).toString())) {
                            counter2 += 1;
                            html3.append((counter2 == 1 ? "<span class='newsLinkFlash'> أو </span>" : "<span class='newsLinkFlash'>, </span>")
                                    + "<a  class='newsLink' onclick='swGetNews("
                                    + categoryRow.get(i).get(Category_News._parent) + ");' >"
                                    + categoryRow.get(i).get(Category_News._title) + "</a>");
                        }
                    }
                }

                html3.append("<span class='newsLinkFlash'>)</span><br/><br/>");
                if (counter2 > 0) {
                    html.append(html3.toString());
                }
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + rowCategory.get(0).get(Category_News._parent) + " AND " + _lang + "=3"));
                int counter = 0;
                for (int i = 0; i < row.size(); i++) {
                    counter += 1;
                    html.append("<a dir='rtl' onclick='swNews(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                    html.append("<span class='newsListDate'>" + counter + ". ");
                    html.append(jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " </span><span class='newsList'>");
                    html.append(row.get(i).get(_title).toString() + "</span></a><br/>");
                }


            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
//        html2 += Js.table("#sss", height, sort, "");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + id));
            html.append("<a onclick='swGetNewsCategory();' class='newsLink'>");
            html.append(jjTools.isLangFa(request) ? "اخبار" : "News");
            html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span>" + rowCategory.get(0).get(Category_News._title) + "<br/><br/>");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id));
            html.append("<table id='refreshNews22' class='tahoma10' dir='rtl'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ خبر</th>");
            html.append("<th width='80%'>عنوان خبر</th>");

            html.append("<th width='5%'>مشاهده</th>");
            html.append("</thead><tbody>");
            int counter = 0;
            for (int i = 0; i < row.size(); i++) {
                counter += 1;
                html.append("<tr  onclick='swNews(").append(row.get(i).get(_id)).append(");' class='mousePointer'>");
                html.append("<td class='tahoma10 c'>").append(counter).append("</td>");
                html.append("<td class='tahoma10'>").append(jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString())).append("</td>");
                html.append("<td class='tahoma10 r'>").append(row.get(i).get(_title).toString()).append("</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");


            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.table("#refreshNews22", height, Integer.parseInt(sort), "", "لیست اخبار");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (jjTools.isLangEn(request)) {
                return sw_En(request, db, isPost);
            }
            if (jjTools.isLangAr(request)) {
                return sw_Ar(request, db, isPost);
            }
            String id = jjTools.getParameter(request, "id").trim();
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "sw" : panel);
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> rowContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (rowContent.size() > 0) {
                    StringBuilder html = new StringBuilder();

                    List<Map<String, Object>> rowParent = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + rowContent.get(0).get(_category_id)));
                    String persianTitle = "Unknown";
                    if (rowParent.size() > 0) {
                        persianTitle = rowParent.get(0).get(Category_News._title).toString();
                        if (!jjTools.isLangFa(request)) {
                            rowParent = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._parent + "=" + rowContent.get(0).get(_category_id)));
                        }
                        html.append("<a onclick='swGetNewsCategory();' class='newsLink'>");
                        html.append(jjTools.isLangFa(request) ? "اخبار" : "News");
                        if (rowParent.size() > 0) {
                            html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetNews("
                                    + rowParent.get(0).get(_parent) + ");' class='newsLink'>");
                            html.append(rowParent.get(0).get(Category_News._title));
                        } else {
                            html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetNews("
                                    + rowContent.get(0).get(_category_id)
                                    + ");' class='newsLink'>");
                            html.append(persianTitle);
                        }
                    }
                    html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><span class='newsLink'>");
                    html.append(rowContent.get(0).get(_title).toString() + "</span><br/><br/><span class='newsContent'>" + rowContent.get(0).get(_content).toString() + "</span>");
                    return Js.setHtml("#" + panel, html.toString());
                }
            }
            String errorMessage = "رکوردی با این محتوا وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "wiki Text News Fail;";
            }
            return Js.setHtml("#" + panel, errorMessage);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            ServerLog.Print("Run: News.sw_En");
            String id = jjTools.getParameter(request, "id").trim();
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "sw" : panel);
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> newsRow = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (newsRow.size() > 0) {
                    StringBuilder html = new StringBuilder();

                    List<Map<String, Object>> categoryParent_en = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + newsRow.get(0).get(_category_id)));
                    String persianTitle = "Unknown";
                    if (categoryParent_en.size() > 0) {
                        persianTitle = categoryParent_en.get(0).get(Category_News._title).toString();
                        categoryParent_en = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._parent + "=" + newsRow.get(0).get(_category_id) + " AND "
                                + Category_News._lang + "=2"));
                        html.append("<a onclick='swGetNewsCategory();' class='newsLink'>News</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span>");
                        List<Map<String, Object>> category = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + categoryParent_en.get(0).get(_id)));
                        ServerLog.Print(categoryParent_en.get(0));
                        html.append("<a onclick='swGetNews("
                                + categoryParent_en.get(0).get(Category_News._parent) + ");' class='newsLink'>");
                        html.append(categoryParent_en.get(0).get(Category_News._title));
                    } else {
                        html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetNews("
                                + newsRow.get(0).get(_category_id)
                                + ");' class='newsLink'>");
                        html.append(persianTitle);
                    }
                    html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><span class='newsLink'>");
                    html.append(newsRow.get(0).get(_title).toString() + "</span><br/><br/><span class='newsContent'>" + newsRow.get(0).get(_content).toString() + "</span>");
                    return Js.setHtml("#" + panel, html.toString());
                }
            }
            String errorMessage = "رکوردی با این محتوا وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "wiki Text News Fail;";
            }
            return Js.setHtml("#" + panel, errorMessage);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            ServerLog.Print("Run: News.sw_En");
            String id = jjTools.getParameter(request, "id").trim();
            String panel = jjTools.getParameter(request, "panel");
            panel = (panel.equals("") ? "sw" : panel);
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> newsRow = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                if (newsRow.size() > 0) {
                    StringBuilder html = new StringBuilder();

                    List<Map<String, Object>> categoryParent_en = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + newsRow.get(0).get(_category_id)));
                    String persianTitle = "غیر معروف";
                    if (categoryParent_en.size() > 0) {
                        persianTitle = categoryParent_en.get(0).get(Category_News._title).toString();
                        categoryParent_en = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._parent + "=" + newsRow.get(0).get(_category_id) + " AND "
                                + Category_News._lang + "=3"));
                        html.append("<a onclick='swGetNewsCategory();' class='newsLink'>"
                                + "أخبار"
                                + "</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span>");
                        List<Map<String, Object>> category = jjDatabase.separateRow(db.Select(Category_News.tableName, Category_News._id + "=" + categoryParent_en.get(0).get(_id)));
                        ServerLog.Print(categoryParent_en.get(0));
                        html.append("<a onclick='swGetNews("
                                + categoryParent_en.get(0).get(Category_News._parent) + ");' class='newsLink'>");
                        html.append(categoryParent_en.get(0).get(Category_News._title));
                    } else {
                        html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><a onclick='swGetNews("
                                + newsRow.get(0).get(_category_id)
                                + ");' class='newsLink'>");
                        html.append(persianTitle);
                    }
                    html.append("</a><span class='newsLinkFlash'>&nbsp;>&nbsp;</span><span class='newsLink'>");
                    html.append(newsRow.get(0).get(_title).toString() + "</span><br/><br/><span class='newsContent'>" + newsRow.get(0).get(_content).toString() + "</span>");
                    return Js.setHtml("#" + panel, html.toString());
                }
            }
            String errorMessage = "رکوردی با این محتوا وجود ندارد.";
            if (jjTools.isLangEn(request)) {
                errorMessage = "wiki Text News Fail;";
            }
            return Js.setHtml("#" + panel, errorMessage);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}