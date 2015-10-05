package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.io.File;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Forum {

    public static String tableName = "forum";
    public static String _id = "id";
    public static String _date = "forum_date";
    public static String _content = "forum_content";
    public static String _creator = "forum_creator";
    public static String _category_id = "forum_category_id";
    public static String _url = "forum_url";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 47;
    public static int rul_ins = 48;
    public static int rul_edt = 49;
    public static int rul_dlt = 50;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            html.append("<table id='refreshForum' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>نویسنده</th>");
            html.append("<th width='15%'>تاریخ ویرایش</th>");
            html.append("<th width='15%'>کد سوال</th>");
            html.append("<th width='35%'>فایل ضمیمه</th>");
            html.append("<th width='15%'>سایز فایل ضمیمه</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr onclick='cmsForum.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='c'>#" + (row.get(i).get(_creator).toString()) + "</td>");
                html.append("<td class='c'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='c'>/" + (row.get(i).get(_category_id).toString()) + "/</td>");
                html.append("<td class='l'>" + (row.get(i).get(_url).toString().replace("%20", " ")) + "</td>");
                html.append("<td class='l'>");
                if (row.get(i).get(_url).toString().equals("")) {
//                    html.append("");
                } else {
                    File f = new File(folderAddressUpload.getAbsolutePath() + "/" + row.get(i).get(_url).toString().replace("%20", " "));
                    if (f.exists()) {
                        html.append((f.length() / 1024) + "kb");
                    } else {
//                        html.append("موجود نمی باشد");
                    }
                }
                html.append("</td>");
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
                panel = "swForumTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshForum", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "9" : "", "لیست بحث ها");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getParent(List<Map<String, Object>> rows, String id) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).get(_creator).toString().equals(id)) {
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
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Forum_button", "<input type=\"button\" id=\"insert_Forum_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Forum_new", Js.jjForum.insert()));
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

//            String creator = jjTools.getParameter(request, _creator);
            map.put(_creator, jjTools.getSeassionUserId(request));

            String category_id = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category_id) ? Integer.parseInt(category_id) : 1);

            map.put(_url, jjTools.getParameter(request, _url));
            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length("", true));


            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjForum.refresh();
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

            String creator = jjTools.getParameter(request, _creator);
            map.put(_creator, jjNumber.isDigit(creator) ? Integer.parseInt(creator) : 0);

            String category_id = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category_id) ? Integer.parseInt(category_id) : 1);

            map.put(_url, jjTools.getParameter(request, _url));

            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length("", true));


            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return "swGetForum(" + category_id + ");\n";
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

//            String myCode = jjTools.getParameter(request, _creator);
            map.put(_creator, jjTools.getSeassionUserId(request));

            String category_id = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category_id) ? Integer.parseInt(category_id) : 1);

            map.put(_url, jjTools.getParameter(request, _url));
            map.put(_content, jjTools.getParameter(request, _content));

            String id = jjTools.getParameter(request, _id);

            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length("", true));

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
            return Js.jjForum.refresh();
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
            if (row.size() > 0) {
                File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                File f = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_url).toString().replace("%20", " "));
                if (f.exists()) {
                    f.delete();
                }
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjForum.refresh();
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
            html.append(Js.setVal("#" + _creator, row.get(0).get(_creator)));
            html.append(Js.setVal("#" + _category_id + "_select", row.get(0).get(_category_id)));
            if (!row.get(0).get(_url).toString().equals("")) {
                html.append(Js.setVal("#forum_url", row.get(0).get(_url)));
                html.append("$('#upload_Forum').hide();\n");
                html.append("$('#forum_url_file').hide();\n");
                html.append("$('#forum_url').show();\n");
            }

            if (jjNumber.isDigit(row.get(0).get(_creator).toString())) {
                List<Map<String, Object>> row1 = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + row.get(0).get(_creator)));
                if (row1.size() > 0) {
                    html.append(Js.setHtml("#" + _creator + "_full", "(" + row1.get(0).get(Access_User._name) + "&nbsp;" + row1.get(0).get(Access_User._family) + ",&nbsp;" + row1.get(0).get(Access_User._email) + ")"));
                }
            }

            Object date = row.get(0).get(_date);
            if (date != null) {
                html.append(Js.setValDate("#" + _date, date));
            }
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_Forum\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_Forum", Js.jjForum.edit()));
            }
            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_Forum\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Forum", Js.jjForum.delete(id)));
            }
            return (Js.setHtml("#Forum_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            StringBuffer js = new StringBuffer();
            String id = jjTools.getParameter(request, "id");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> forum = jjDatabase.separateRow(db.Select(Category_Forum.tableName, Category_Forum._id + "=" + id));
            html.append("<a onclick='swGetForumCategory();' class='forumAddForumLink'>");
            html.append(jjTools.isLangFa(request) ? "انجمن ها" : "Forums");
            html.append("</a><a class='forumAddForumLinkFlash'>&nbsp;>&nbsp;</a>");
            if (forum.size() > 0) {
                html.append("<a class='forumAddForumLink'>");
                html.append(forum.get(0).get(Category_Forum._title));
                html.append("</a><br/><br/>");
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id));
            String creator = "";
            if (row.size() == 0) {
                String h2 = "";
                String h3 = "";

                h3 += jjTools.isLangFa(request) ? "کامنتی در این انجمن درج نشده است." : "Empty";
                h3 += ("<br/><br/><a onclick='swAddForumComment(" + id + ");' class='forumAddForumLink'>");
                h3 += (jjTools.isLangFa(request) ? "افزودن کامنت ..." : "Add Comment ...");
                h3 += ("</a><br/><br/>");
                h2 += Js.setHtml("#" + panel, html.toString() + h3);
                return h2;
            }
            html.append("<table class='forumTitleAll'>");
            boolean islangFa = jjTools.isLangFa(request);
            Map<String, String> map = new HashMap<String, String>();
            File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
            for (int i = 0; i < row.size(); i++) {
                creator = map.get("user" + (row.get(i).get(_creator).toString())) == null ? "" : map.get("user" + (row.get(i).get(_creator).toString()));
                if (creator.equals("")) {
                    List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + (row.get(i).get(_creator).toString())));
                    if (user.size() > 0) {
                        creator = user.get(0).get(Access_User._name) + "&nbsp;" + user.get(0).get(Access_User._family) + "&nbsp;"
                                + "(" + user.get(0).get(Access_User._email) + ")";
                        map.put("user" + (row.get(i).get(_creator).toString()), creator);
                    }
                }

                html.append("<tr><td><table class='forumTitleTable'><tr><td><table style='width: 100%;'><tr><td class='forumTitleTd'>"
                        + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString())
                        + "&nbsp;&nbsp;&nbsp;"
                        + creator
                        + "</td></tr></table></td></tr><tr><td class='forumContentTd'>"
                        + (row.get(i).get(_content))
                        + "<table style='width:100%'><tr><td style='width: 120px'>");
                String file = row.get(i).get(_url).toString();
                if (!file.equals("")) {
                    File attach = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_url).toString().replace("%20", " "));
                    if (attach.exists()) {
                        if (file.toLowerCase().endsWith(".jpg") || file.toLowerCase().endsWith(".png") || file.toLowerCase().endsWith(".gif")) {
                            html.append("<a href='upload/" + file + "'" + " target='_top'>");
                            html.append("<img style='width:99%;border:0px' src='upload/" + row.get(i).get(_url) + "' />");
                            html.append("</a>");
                        } else if (file.toLowerCase().endsWith(".mp4")
                                || file.toLowerCase().endsWith(".flv") //  || file.toLowerCase().endsWith(".mpg")
                                //  || file.toLowerCase().endsWith(".avi")
                                //  || file.toLowerCase().endsWith(".wmv")
                                //  || file.toLowerCase().endsWith(".3gp")
                                ) {
//                            html.append(attach.getName());
                            html.append("<div id='flvplayer" + row.get(i).get(_id) + "'></div>");
                            js.append("new SWFObject( '99%', '327', 'upload/" + file + "','flvplayer" + row.get(i).get(_id) + "',false);\n");
                        } else if (file.toLowerCase().endsWith(".mp3")) {
                            html.append("<div id='mp3player" + row.get(i).get(_id) + "'></div>");
                            js.append("new SWFObject( '99%', '27', 'upload/" + file + "','mp3player" + row.get(i).get(_id) + "',true);\n");
//                            html.append("<a href='upload/" + row.get(i).get(_url) + "' title='Play Sound'>");
//                            html.append((islangFa ? "بخش فایل صوتی (1) " : "Play Sound(1)"));
//                            html.append("</a>");
                        } else {
                            html.append("<a class='forumDownloadFile1'" + (row.get(i).get(_url).toString().equals("") ? "" : "href='upload/" + row.get(i).get(_url) + "'") + ">");
                            html.append((islangFa ? "دانلود فایل ضمیمه (1) " : "Attach(1)"));
                            html.append("</a>");
                        }
                    }
                }
                html.append("</td></tr></table>"
                        + "</td></tr></table></td></tr>");
            }
            html.append("</table>");

            html.append("<br/><a onclick='swAddForumComment(" + id + ");' class='forumAddForumLink'>");
            html.append(jjTools.isLangFa(request) ? "افزودن کامنت ..." : "Add Comment...");
            html.append("</a><br/><br/>");
            return Js.setHtml("#" + panel, html.toString()) + js.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
