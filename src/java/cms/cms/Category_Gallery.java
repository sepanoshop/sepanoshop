package cms.cms;

import cms.tools.*;
import cms.access.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Category_Gallery {

    public static String tableName = "category_gallery";
    public static String _id = "id";
    public static String _title = "category_gallery_title";
    public static String _parent = "category_gallery_parent";
    public static String _lang = "category_gallery_lang";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 24;
    public static int rul_ins = 25;
    public static int rul_edt = 26;
    public static int rul_dlt = 27;
    public static int rul_lng = 28;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=1"));
            html.append("<table id='refreshCategoryGallery' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsCategoryGallery.m_select(" + row.get(i).get(_id) + ");'  class='mousePointer' >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
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
                panel = "swCategoryGalleryTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryGallery", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "4" : "", "لیست دسته های گالری تصاویر");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryGallery_button", "<input type=\"button\" id=\"insert_CategoryGallery_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_CategoryGallery_new", Js.jjCategoryGallery.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchPicGalleryTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryGallery.refresh() + getOptions(request, db, isPost);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchPicGalleryTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }

            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));

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
            return Js.jjCategoryGallery.refresh() + getOptions(request, db, isPost);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            Content.catchPicGalleryTitle = null;
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
            db.delete(tableName, _parent + "=" + id);

            // change gallery category to default
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Pic._category_id, 1);
            db.update(Pic.tableName, map, Pic._category_id + "=" + id);
            return Js.jjCategoryGallery.refresh() + getOptions(request, db, isPost);
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
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type='button' id='edit_CategoryGallery' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_CategoryGallery", Js.jjCategoryGallery.edit()));
            }
            if (accDel) {
                if (!id.equals("1")) {
                    if (!row.get(0).get(_parent).toString().equals("1")) {
                        html2.append("<input type='button' id='delete_CategoryGallery' value='" + lbl_delete + "' class='tahoma10'  />");
                        html.append(Js.buttonMouseClick("#delete_CategoryGallery", Js.jjCategoryGallery.delete(id)));
                    }
                }
            }
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_CategoryGallery' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_CategoryGallery", Js.jjCategoryGallery.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_CategoryGallery' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_CategoryGallery", Js.jjCategoryGallery.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_CategoryGallery' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_CategoryGallery", Js.jjCategoryGallery.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_CategoryGallery' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_CategoryGallery", Js.jjCategoryGallery.addAr(id)));
                    }
                }
            }
            return (Js.setHtml("#CategoryGallery_button", html2.toString())) + html.toString();
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
            html.append(Js.setVal("#" + _lang, "2"));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryGallery_button", "<input type=\"button\" id=\"insert_CategoryGallery_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_CategoryGallery_new", Js.jjCategoryGallery.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static String add_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            html.append(Js.setVal("#" + _lang, "3"));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryGallery_button", "<input type='button' id='insert_CategoryGallery_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_CategoryGallery_new_ar", Js.jjCategoryGallery.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
/**
 *this metod use in site view on mouse over in 'gallerry' tab or menu or "li"
 */
    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {//has been changed by mohammad
            int langNo=1;
            if (jjTools.isLangEn(request)) {
                    langNo=2;
                }else if (jjTools.isLangEn(request)) {
                    langNo=3;
                }
            String panel = jjTools.getParameter(request, "panel");            
            if (panel.equals("")) {
                panel = "pic_gallery_id_select";
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDesc(tableName,_lang+"="+langNo,_id));
            for (int i = 0; i < row.size(); i++) {
                html.append("<li><a onclick='swGetGallery(" + row.get(i).get(_id) + ");'>" + row.get(i).get(_title) + "</a></li>");
            }
            return Js.setHtml("#" + panel,html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
/**
 *This methid use in cms when user select an option to set category
 */
    public static String getOptions(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            if (panel.equals("")) {
                panel = "pic_gallery_id_select";
            }
            String selectedCategory = "";
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Pic.tableName, Pic._id + "=" + id));
                if (row.size() > 0) {
                    selectedCategory = row.get(0).get(Pic._category_id).toString();
                }
            }
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=0"));
            for (int i = 0; i < row.size(); i++) {
                String toString = row.get(i).get(_id).toString();
                html.append("<option value='" + toString + "' "
                        + (selectedCategory.equals(toString) ? "selected='selected'" : "") + ">"
                        + row.get(i).get(_title).toString() + "</option>");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
