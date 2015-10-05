package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import java.util.ArrayList;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

public class Content {

    public static String tableName = "content";
    public static String _id = "id";
    public static String _title = "content_title";
    public static String _content = "content_content";
    public static String _parent = "content_parent";
    public static String _lang = "content_lang";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 1;
    public static int rul_ins = 2;
    public static int rul_edt = 3;
    public static int rul_dlt = 4;
    public static int rul_lng = 5;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            DefaultTableModel dtm = db.Select(tableName, _id + "," + _title, _parent + "=0 AND id>10");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table id='refreshContent' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='90%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
//                html.append("<tr onclick='$(this).html().print();' class='mousePointer'>");
//                html.append("<tr onclick='alert($(this).children(1).html());' class='mousePointer'>");
                html.append("<tr onclick='cmsContent.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='height:30px'/></td>");
                html.append("</tr>");
            }
            html.append("</tbody></tabl"
                    + "e>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swContentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshContent", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "1" : "", "لیست محتویات سایت");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

//    public static String getParent(List<Map<String, Object>> rows, String id) {
//        try {
//            for (int i = 0; i < rows.size(); i++) {
//                if (rows.get(i).get(_parent).toString().equals(id)) {
//                    return rows.get(i).get(_id).toString();
//                }
//            }
//            return "";
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Content_button", "<input type=\"button\" id=\"insert_content_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_content_new", Js.jjContent.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            catchContentTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title).trim());

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjContent.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            catchContentTitle = null;
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }

            Map<String, Object> map = new HashMap<String, Object>();

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_title, jjTools.getParameter(request, _title).trim());

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
            return Js.jjContent.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            catchContentTitle = null;
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
            return Js.jjContent.refresh();
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
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type='button' id='edit_content' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_content", Js.jjContent.edit()));
            }
            if (accDel && !row.get(0).get(_parent).toString().equals("1")) {
                html2.append("<input type='button' id='delete_content' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_content", Js.jjContent.delete(id)));
            }
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_content' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_content", Js.jjContent.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_content' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_content", Js.jjContent.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_content' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_content", Js.jjContent.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_content' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_content", Js.jjContent.addAr(id)));
                    }
                }
            }
            return (Js.setHtml("#Content_button", html2.toString())) + html.toString();
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
            StringBuffer html2 = new StringBuffer();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, 2));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Content_button", "<input type=\"button\" id=\"insert_content_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html2.append(Js.buttonMouseClick("#insert_content_new", Js.jjContent.insert()));
            }
            return html.toString() + html2.toString();
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
            StringBuffer html2 = new StringBuffer();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_title);
            if (title != null) {
                html.append(Js.setVal("#" + _title, title));
            }
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + _lang, 3));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Content_button", "<input type='button' id='insert_content_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html2.append(Js.buttonMouseClick("#insert_content_new_ar", Js.jjContent.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }    
    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = java.net.URLDecoder.decode(jjTools.getParameter(request, "text"), "utf-8");
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            boolean senderIsMyJs = jjTools.getParameter(request, "jj").equals("1");
            if(text.equals("")){//در این صورت بجای پارامتر اتریبیوت ها را باید استخراج کنیم
                jjTools.ShowAllAttribute(request);
                 text=request.getAttribute("text").toString() ;
                 panel=request.getAttribute("panel").toString();
            }
            panel = (panel.equals("") ? "sw" : panel);
            List<Map<String, Object>> swContent = new ArrayList<Map<String, Object>>();
            if (jjNumber.isDigit(text)) {
                swContent = jjDatabase.separateRow(db.Select(tableName, _id + "=" + text));
            } else {
                swContent = jjDatabase.separateRow(db.Select(tableName, _title + "='" + text + "'"));
            }
            if (swContent.size() > 0) {
                if (jjTools.isLangEn(request)) {
                    List<Map<String, Object>> rowContent_en = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + swContent.get(0).get(_id) + " AND " + _lang + "=2"));
                    if (rowContent_en.size() > 0) {
                        swContent = rowContent_en;
                    }
                }
                if (jjTools.isLangAr(request)) {
                    List<Map<String, Object>> rowContent_ar = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + swContent.get(0).get(_id) + " AND " + _lang + "=3"));
                    if (rowContent_ar.size() > 0) {
                        swContent = rowContent_ar;
                    }
                }
                String content = swContent.get(0).get(_content).toString();
                String title = titlePanel.equals("") ? "" : Js.setHtml("#" + titlePanel, swContent.get(0).get(_title));
                String tamasBaMa = "";
                if (text.equals("تماس با ما") || swContent.get(0).get(_title).toString().equals("تماس با ما")) {
                    tamasBaMa = "sw('$comment');\n";
                }
                if (text.toLowerCase().equals("contact us")) {
                    tamasBaMa = "sw('$comment');\n";
                }
                if (text.equals("استخدام") || swContent.get(0).get(_title).toString().equals("استخدام")) {
                    tamasBaMa = "sw('$enrolment');\n";
                }
                if (text.toLowerCase().equals("enrolment")) {
                    tamasBaMa = "sw('$enrolment');\n";
                }
                if (!senderIsMyJs) {
//                    return content;
                    String address = request.getServletContext().getRealPath("/");
                    File file = new File(address + "/");
                    StringBuilder html = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                    if (!file.exists()) {
                        return content;

                    } else {
                        //replace title.........................................
                        int index = html.indexOf("<title>");//finding title tag
                        if (index > -1) {
                            index = html.indexOf(">", index); //finding title tag ( <title> )
                            title=swContent.get(0).get(_title).toString()+", ";
                            html = html.insert(index + 1, title);
                        }
                        //replace content in <div id="sw"> ... </div>...........
                        index = html.indexOf("\"sw\"");//finding div in wich id="sw"
                        if (index > -1) {
                            index = html.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
                            html = html.insert(index + 1, content);
                        }
                        //......................................................
                    }
                    return html.toString();
                }
                return Js.setHtml("#" + panel, content) + title + tamasBaMa;
            } else {
                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "wiki Text Fail;";
                }
                return Js.setHtml("#" + panel, errorMessage);
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            List<Map<String, Object>> swContent = jjDatabase.separateRow(db.Select(tableName, _id + "= " + "57"));
            return swContent.get(0).get(_content).toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static List<Map<String, Object>> catchContentTitle = null;
    public static List<Map<String, Object>> catchPicGalleryTitle = null;
    public static List<Map<String, Object>> catchNewsTitle = null;
}
