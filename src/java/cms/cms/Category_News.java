package cms.cms;

import cms.tools.jjTools;
import cms.tools.jjValidation;
import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.*;
import org.xml.sax.ErrorHandler;
import cms.tools.ServerLog;

public class Category_News {

    public static String tableName = "category_news";
    public static String _id = "id";
    public static String _title = "category_news_title";
    public static String _parent = "category_news_parent";
    public static String _lang = "category_news_lang";
    public static String _upperNode = "category_news_upperNode";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 19;
    public static int rul_ins = 20;
    public static int rul_edt = 21;
    public static int rul_dlt = 22;
    public static int rul_lng = 23;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=1"));
            html.append("<table id='refreshCategoryNews' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsCategoryNews.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
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
                panel = "swCategoryNewsTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCategoryNews", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "5" : "", "لیست دسته های اخبار");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=1"));
            html.append("<br/>");
            html.append("<table id='refreshCategoryNews2' style='width:100%' class='tahoma10' dir='rtl'><thead>");
            html.append("<th width='5%'>ردیف</th>");
            html.append("<th width='60%'>عنوان</th>");
            html.append("<th width='5%'>مشاهده</th>");
            html.append("</thead><tbody>");
            int counter = 0;
            String firstCode = "";
            for (int i = 0; i < row.size(); i++) {
                if (db.Select(News.tableName, News._category_id + "=" + row.get(i).get(_id)).getRowCount() > 0) {
                    counter += 1;
                    if (counter == 1) {
                        firstCode = row.get(i).get(_id).toString();
                    }
                    html.append("<tr  onclick='swGetNews(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                    html.append("<td class='c'>" + counter + "</td>");
                    html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                    html.append("<td class='c'><img src='img/l.png' style='height:30px' /></td>");
                    html.append("</tr>");
                }
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String sort = jjTools.getParameter(request, "sort");
            String panel = jjTools.getParameter(request, "panel");
            height = !jjNumber.isDigit(height) ? "200" : height;
            sort = !jjNumber.isDigit(sort) ? "0" : sort;
            panel = panel == null ? "sw" : panel;
            String html2 = "";
            if (counter > 1) {
                html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
                html2 += Js.table("#refreshCategoryNews2", height, Integer.parseInt(sort), "", "لیست دسته های اخبار");
            } else if (counter == 1) {
                html2 = "swGetNews(" + firstCode + ");";
            }
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * This method return hierarchical div for mane page in '#sw' and have some
     * little different whit getHierarchyList() in onclick();
     *
     * @since v1.5.0
     * @return some JQuery statements like
     * :<br/>$('#div1').setHtml('xxxx');<br/>$('#div2').setHtml('xxxx');<br/>,...
     * @param panel whit out # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getHierarchyDiv(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            if (jjTools.isLangEn(request)) {
                return getHierarchyDiv_en(request, db, isPost);
            }
            if (jjTools.isLangAr(request)) {
//                return getList_Ar(request, db, isPost);
            }
            StringBuilder html = new StringBuilder();
//          String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDesc(tableName, _lang + " = 1", _upperNode));
            String id ;
            String upperNode;
            String title;
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "swRight" : panel;
            html.append(Js.setHtml("#" + panel, "<div id='hierarchyListDiv' class='hierarchyDivs'>"
                    + "<div style=\"padding-left: 0px; visibility: visible;\" id='uperNodeDiv0' >"
                    + "<a id='uperNodeA0' class='headerList' onclick='swGetNews(0);makeSelected(0)'></a>"
                    + "</div>"
                    + "</div>"));//end hierarchyListDiv
            for (int i = row.size() - 1; row.size() > 0 || i >= 0; i--) {
                if (i < 0) {//every when some node is note assigned in hier archy list 
                    i = row.size() - 1;
                }
                id = row.get(i).get(Category_News._id).toString();
                upperNode = row.get(i).get(Category_News._upperNode).toString();
                title = row.get(i).get(Category_News._title).toString();
                if (html.indexOf("uperNodeDiv" + upperNode) > 0) {//if upper node is avalable then add childe to it
                    if(!jjNumber.isDigit(upperNode)){
                        upperNode="0";
                    }
                    if (Integer.valueOf(upperNode) == 0) {// For first level item
                        html.append(Js.append("#uperNodeDiv" + upperNode,
                                "<div id=\"uperNodeDiv" + id + "\" class=\"closedList\">"
                                + "<div>"
                                + "<div class=\"heading\" onclick=\"toggleList(" + id + ");\">"
                                + "<div id=\"item_node" + id + "\"><a class=\"link\" id=\"uperNodeA" + id + "\" onclick='swGetNews(" + id + ");makeSelected("+id+");'>" + title + "</a>" + "</div>"
                                + "</div>"
                                + "</div>"
                                + "</div>"
                                + "<div class=\"separator\"></div>"
                                + "<div class=\"separator\"></div>"));
                    } else {//for sub items (second and lower level items) 
                        html.append(Js.append("#uperNodeDiv" + upperNode,
                                "<div class=\"sub closedList\"  id=\"uperNodeDiv" + id + "\">"
                                + "<div>"
                                + "<div class=\"sub\" onclick=\"toggleList(" + id + ");\">"
                                + "<ul class=\"sub\" id=\"item_node" + id + "\"><li>"
                                + "<div ><a class=\"link\" id=\"uperNodeA" + id + "\" onclick='swGetNews(" + id + ");;makeSelected("+id+");'>" + title + "</a>" + "</div>"
                                + "</li></ul>"
                                + "</div>"
                                + "</div>"
                                + "</div>"));
                    }
                    //for nodes that which have any child (too add arrow)
                    html.append(Js.append("#item_node" + upperNode, "<div class=\"arrowImage\" ></div>"));
                    row.remove(i);//remove assigned node from rowّ
                }
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
/**
     * This method return hierarchical div in Englinsh Language for mane page in '#sw' and have some
     * little different whit getHierarchyList() in onclick();
     *
     * @since v1.5.0
     * @return some JQuery statements like
     * :<br/>$('#div1').setHtml('xxxx');<br/>$('#div2').setHtml('xxxx');<br/>,...
     * @param panel whit out # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getHierarchyDiv_en(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            StringBuilder html = new StringBuilder();
//          String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDesc(tableName, _lang + " = 2", _upperNode));
            String id ;
            String upperNode;
            String title;
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "swRight" : panel;
            html.append(Js.setHtml("#" + panel, "<div id='hierarchyListDiv' class='hierarchyDivs'>"
                    + "<div style=\"padding-left: 0px; visibility: visible;\" id='uperNodeDiv0' >"
                    + "<a id='uperNodeA0' class='headerList' onclick='swGetNews(0);makeSelected(0)'></a>"
                    + "</div>"
                    + "</div>"));//end hierarchyListDiv
            for (int i = row.size() - 1; row.size() > 0 || i >= 0; i--) {
                if (i < 0) {//every when some node is note assigned in hier archy list 
                    i = row.size() - 1;
                }
                id = row.get(i).get(Category_News._id).toString();
                upperNode = row.get(i).get(Category_News._upperNode).toString();
                title = row.get(i).get(Category_News._title).toString();
                if (html.indexOf("uperNodeDiv" + upperNode) > 0) {//if upper node is avalable then add childe to it
                    if(!jjNumber.isDigit(upperNode)){
                        upperNode="0";
                    }
                    if (Integer.valueOf(upperNode) == 0) {// For first level item
                        html.append(Js.append("#uperNodeDiv" + upperNode,
                                "<div id=\"uperNodeDiv" + id + "\" class=\"closedList\">"
                                + "<div>"
                                + "<div class=\"heading\" onclick=\"toggleList(" + id + ");\">"
                                + "<div id=\"item_node" + id + "\"><a class=\"link\" id=\"uperNodeA" + id + "\" onclick='swGetNews(" + id + ");makeSelected("+id+");'>" + title + "</a>" + "</div>"
                                + "</div>"
                                + "</div>"
                                + "</div>"
                                + "<div class=\"separator\"></div>"
                                + "<div class=\"separator\"></div>"));
                    } else {//for sub items (second and lower level items) 
                        html.append(Js.append("#uperNodeDiv" + upperNode,
                                "<div class=\"sub closedList\"  id=\"uperNodeDiv" + id + "\">"
                                + "<div>"
                                + "<div class=\"sub\" onclick=\"toggleList(" + id + ");\">"
                                + "<ul class=\"sub\" id=\"item_node" + id + "\"><li>"
                                + "<div ><a class=\"link\" id=\"uperNodeA" + id + "\" onclick='swGetNews(" + id + ");;makeSelected("+id+");'>" + title + "</a>" + "</div>"
                                + "</li></ul>"
                                + "</div>"
                                + "</div>"
                                + "</div>"));
                    }
                    //for nodes that which have any child (too add arrow)
                    html.append(Js.append("#item_node" + upperNode, "<div class=\"arrowImage\" ></div>"));
                    row.remove(i);//remove assigned node from rowّ
                }
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    public static String getHierarchyList(String panel, jjDatabaseWeb db) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
//            String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDesc(tableName, _lang + " = 1", _upperNode));
            String id = new String();
            String upperNode = new String();
            String title = new String();
            //Node 0 in below is for root(+)
            html.append(Js.setHtml("#" + panel, "<div id='uperNodeDiv0'><a id='uperNodeA0' class='selectedNode' onclick='cmsCategoryNews.m_select_upper_node(0);'> + </a></div>"));
            for (int i = row.size() - 1; row.size() > 0 || i >= 0; i--) {
                if (i < 0) {//every when some node is note assigned in hier archy list 
                    i = row.size() - 1;
                }
                id = row.get(i).get(_id).toString();
                upperNode = row.get(i).get(_upperNode).toString();
                title = row.get(i).get(_title).toString();
                if (html.indexOf("uperNodeDiv" + upperNode) > 0) {//if upper node is avalable then add childe to it
                    html.append(Js.append("#uperNodeDiv" + upperNode,
                            "<div id=\"uperNodeDiv" + id + "\">"
                            + "<a id=\"uperNodeA" + id + "\" onclick='cmsCategoryNews.m_select_upper_node(" + id + ");'>" + title + "</a>"
                            + "</div>"));
                    row.remove(i);//remove assigned node from row
                }
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    /*
     * only get upper nodes and remove childer becuse it is not possible
     * to be one node childern of his child
     * 
     @only use when there is a div  with 'id=hierarchyListDiv',
     */

    public static String getHierarchyUpper(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
//            String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDesc(tableName, _lang + " = 1", _upperNode));
            String id = new String();
            String upperNode = new String();
            String title = new String();
            html.append(Js.setHtml("#hierarchyListDiv", "<div id='uperNodeDiv0'><a id='uperNodeA0' class='selectedNode' onclick='cmsCategoryNews.m_select_upper_node(0);'> + </a></div>\n"));
            for (int i = row.size() - 1; row.size() > 0 || i >= 0; i--) {
                if (i < 0) {//every when some node is note assigned in hier archy list 
                    i = row.size() - 1;
                }
                id = row.get(i).get(_id).toString();
                upperNode = row.get(i).get(_upperNode).toString();
                title = row.get(i).get(_title).toString();
                if (html.indexOf("uperNodeDiv" + upperNode) > 0) {//if upper node is avalable then add childe to it
                    html.append(Js.append("#uperNodeDiv" + upperNode,
                            "<div id=\"uperNodeDiv" + id + "\">\n"
                            + "<a id=\"uperNodeA" + id + "\" onclick='cmsCategoryNews.m_select_upper_node(" + id + ");'>\n" + title + "\n</a>\n"
                            + "</div>\n"));
                    row.remove(i);//remove assigned node from row
                }
            }
            id = request.getParameter(_id);//id means which node must be desabled!
            /* if lang is not 'fa'(is 2 for 'en' and 3 for 'ar') selecting upper node must be desable.*/
            row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
            if (!row.get(0).get(_lang).toString().equalsIgnoreCase("1")) {
// "برای انتخاب سرشاخه از قسمت ویرایش زبان خارج شوید";
                String parent = row.get(0).get(_parent).toString();
                title = row.get(0).get(_title).toString();
                html.append(Js.setHtml("#uperNodeDiv" + parent,
                        "<div id=\"uperNodeDiv" + id + "\">\n"
                        + "<a id=\"uperNodeA" + id + "\" >\n" + title + "\n</a>\n"
                        + "</div>\n"));
                id = "0";//0 for root (+)
            };
//            html.append("$(\"#uperNodeDiv"+id+"\").html($(\"#uperNodeDiv"+id+"\").html().replace(/<a.*id=\\\"uperNodeA.*\\\">/g,\"\"));");
            html.append("cmsCategoryNews.m_disablechilds(" + id + ");");//javascript function to disable click and hide childs


            String panel = jjTools.getParameter(request, "panel");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getListBazrafshan(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=1"));
            for (int i = 0; i < row.size(); i++) {
                html.append("<li><span onclick='swGetNews(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='tahoma10' style='text-align: center;'>" + (row.get(i).get(_title).toString().length() > 23 ? row.get(i).get(_title).toString().substring(0, 23) + "..." : row.get(i).get(_title)) + "</span></li>");
            }
            String panel = jjTools.getParameter(request, "panel");
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryNews_button", "<input type=\"button\" id=\"insert_CategoryNews_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_CategoryNews_new", Js.jjCategoryNews.insert()));
            }
            return html.toString() + getHierarchyList("hierarchyListDiv", db);
//            return html.toString();
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

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_upperNode, jjTools.getParameter(request, _upperNode));

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryNews.refresh() + getOptions(request, db, isPost);
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

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_upperNode, jjTools.getParameter(request, _upperNode));

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
            return Js.jjCategoryNews.refresh() + getOptions(request, db, isPost);
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
            db.delete(tableName, _parent + "=" + id);

            // change news category to default
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(News._category_id, 1);
            db.update(News.tableName, map, News._category_id + "=" + id);
                        //بعد از پاک کردن یک ریشه، فرزندانش به ریشه 0 انتقال می یابند
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put(Category_News._upperNode, 0);
            db.update(Category_News.tableName, map2, Category_News._upperNode + "=" + id);
            return Js.jjCategoryNews.refresh() + getOptions(request, db, isPost);
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
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _upperNode, row.get(0).get(_upperNode)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_CategoryNews\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_CategoryNews", Js.jjCategoryNews.edit()));
            }
            if (accDel) {
                if (!id.equals("1")) {
                    if (!row.get(0).get(_parent).toString().equals("1")) {
                        html2.append("<input type=\"button\" id=\"delete_CategoryNews\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                        html.append(Js.buttonMouseClick("#delete_CategoryNews", Js.jjCategoryNews.delete(id)));
                    }
                }
            }
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_CategoryNews' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_CategoryNews", Js.jjCategoryNews.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_CategoryNews' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_CategoryNews", Js.jjCategoryNews.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_CategoryNews' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_CategoryNews", Js.jjCategoryNews.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_CategoryNews' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_CategoryNews", Js.jjCategoryNews.addAr(id)));
                    }
                }
            }
            return (Js.setHtml("#CategoryNews_button", html2.toString())) + html.toString() + getHierarchyUpper(request, db, isPost);
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
            html.append(Js.setVal("#" + _parent, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _lang, 2));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryNews_button", "<input type=\"button\" id=\"insert_CategoryNews_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_CategoryNews_new", Js.jjCategoryNews.insert()));
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
            html.append(Js.setVal("#" + _parent, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _lang, 3));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#CategoryNews_button", "<input type='button' id='insert_CategoryNews_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_CategoryNews_new_ar", Js.jjCategoryNews.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

 /**
     * This method return onli " li " tags  menu of top level products use to set sub menu
     * for products
     *
     * @since v1.5.0
     * @return some JQuery statements like :<br/>$('#div1').setHtml('xxxx');
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            String langId="1";//by defult language is 'fa' for Farsi
            if (jjTools.isLangEn(request)) {
                langId="2";
            }
            if (jjTools.isLangAr(request)) {
                langId="3";
            }
            StringBuilder html = new StringBuilder();
//          String parent = new String( request.getParameter(_parent));
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName,  _lang + " = "+ langId+" AND " + _upperNode + "=0"));// =0 means only toplevel nod
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "swRight" : panel;
            for (int i = row.size()-1; i >= 0; i--) {
                html.append("<li><a onclick='swGetNews(" + row.get(i).get(_id) + ");swRightNewsMenu();'>" + row.get(i).get(_title) + "</a></li>");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
 /**
     * This method return a option/select input in cms news form
     * for products
     *
     * @since v1.5.0
     * @return some JQuery statements like :<br/>$('#div1').setHtml('xxxx');
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getOptions(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            if (panel.equals("")) {
                panel = "news_category_id_select";
            }
            String selectedCategory = "";
            String selectedCategoryLang = "1";
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(News.tableName, News._id + "=" + id));
                if (row.size() > 0) {
                    selectedCategoryLang = row.get(0).get(News._lang).toString();//for english option in menu
                    selectedCategory = row.get(0).get(News._category_id).toString();
                }
            }
            if(selectedCategoryLang.equals("2")){
                return  getOptions_en(request,db,false);
            }
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=0"));
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
     /**
     * This method return a option/select input in cms news form in Edit english lang ( retuen where lang=2 see select statement)
     * for products
     *
     * @since v1.5.0
     * @return some JQuery statements like :<br/>$('#div1').setHtml('xxxx');
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getOptions_en(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            if (panel.equals("")) {
                panel = "news_category_id_select";
            }
            String selectedCategory = "";
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(News.tableName, News._id + "=" + id));
                if (row.size() > 0) {
                    selectedCategory = row.get(0).get(News._category_id).toString();
                }
            }
            StringBuilder html = new StringBuilder();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _lang + "=2"));
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
