/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.util.List;
import jj.jjCalendar_IR;
import jj.jjNumber;

/**
 *
 * @author Arvin2
 */
public class Js {

    public static String replacor(String comment) {
        String replacor = "^*#~";;
        comment = comment.trim().replace("\'", replacor);
        comment = comment.replace("\\\"", replacor);
        comment = comment.replace("'", replacor);
        comment = comment.replace("\"", replacor);
        comment = comment.replace(replacor, "\"");
        comment = comment.replace("\n", "");
        comment = comment.replace("ي", "ی");
        return comment;
    }

    public static String dialog(String comment) {
        return "$('<div></div>').dialog({"
                + "title : \" پیام سامانه\","
                + "open: function() {$(this).html(\"" + comment + "\");"
                //============ BY RASHIDI ========>
                + " $(this).css('direction','rtl');},"
                //<============ BY RASHIDI ========
                + "close: function() {$(this).dialog('destroy');}});\n";
    }

    public static String dialog(String comment, String title) {
        return "jj('" + replacor(comment) + "','" + replacor(title) + "').jjDialog();\n";
    }

    public static String dialog(String comment, String btn1func, String btn1Title, String height, String widht) {
        return "$('<div></div>').dialog({"
                + "height:" + height + ","
                + "width:" + widht + ","
                + "modal: true,"
                + "title : \" پیام سامانه\","
                + "buttons: {\"" + btn1Title + "\": function() {" + btn1func + ";}},"
                + "open: function() {$(this).html(\"" + comment + "\");},"
                + "close: function() {$(this).dialog('destroy');}});\n";
    }

    public static String setHtml(String selector, Object value) {
        return setHtml(selector, value.toString());
    }

    public static String setHtml(String selector, String value) {
        return "$('" + (selector.startsWith("#") ? selector : "#" + selector) + "').html(\"" + replacor(value).replace("\"", "'") + "\");\n"; //EDITED BY RASHIDI
//        return "$('" + selector + "').html(\"" + replacor(value).replace("\"", "'") + "\");\n";
    }

    public static String hide(String selector) {
        return "$('" + selector + "').hide();\n";
    }

    public static String setCss(String selector, String key, String value) {
        return "$('" + selector + "').css('" + replacor(key) + "','" + replacor(value) + "');\n";
    }

    /**
     * this function uses to set html tag attributes like checked='checked' for
     * "input type='checkbox'"
     *
     * @param attrName = disabled , cheked ,style,src,href,...
     * @since V1.5.0
     *
     */
    public static String setAttr(String selector, String attrName, String value) {
        return "$('" + selector + "').attr('" + replacor(attrName) + "','" + replacor(value) + "');\n";
    }

    /**
     * this function uses to remove html tag attributes like checked='checked'
     * for "input type='checkbox'"
     *
     * @param attrName = disabled,cheked,style,src,href,...
     * @since V1.5.0
     *
     */
    public static String removeAttr(String selector, String attrName) {
        return "$('" + selector + "').removeAttr('" + replacor(attrName) + "');\n";
    }

    public static String setVal(String selector, Object value) {
        return setVal(selector, value.toString());
    }

    public static String setVal(String selector, String value) {
        return "new jj('" + (selector.startsWith("#") ? selector : "#" + selector) + "').jjVal('" + replacor(value) + "');\n";
    }

    public static String setSrc(String selector, Object picName) {
        return setSrc(selector, picName.toString());
    }

    public static String setSrc(String selector, String picName) {
        return "$('" + selector + "_preview').attr('src','upload/" + picName + "');\n";
    }

    public static String show(String selector) {
        return "$('" + selector + "').show();\n";
    }

    public static String append(String panel, String value) {
        return "$('" + panel + "').append('" + replacor(value) + "');\n";
    }

    public static String table(String tableSelector, String height, int sort, String tblCode, String tableTitle) {
        tblCode = tblCode == null ? "null" : tblCode;
        tblCode = tblCode.equals("") ? "null" : tblCode;
        if (!jjNumber.isDigit(tblCode)) {
            tblCode = "null";
        }

        tableTitle = tableTitle == null ? "null" : tableTitle.replace("\n", "").replace("'", "");
        tableTitle = tableTitle.equals("") ? "null" : tableTitle;
        tableSelector = tableSelector.replace("#", "");
//        sort = sort.equals("asc") ? "asc" : "desc";
//        return "$('" + tableSelector + "').dataTable({'oTableTools':{'aButtons':['pdf','print']},'tblCode':" + tblCode + ",'iDisplayLength':100,'sScrollY': " + height + ",'bJQueryUI': true,'sDom':'T<\"clear\">lfrtip','aoColumns':[null,null,null],'sPaginationType': 'full_numbers', 'aaSorting': [[ " + sort + ", 'desc' ]] });\n";//asc 0r desc
//        return "$('" + tableSelector + "').dataTable({'tblCode':" + tblCode + ",'iDisplayLength':100,'sScrollY': " + height + ",'bJQueryUI': true,'sPaginationType': 'full_numbers' ,'oColVis':{},'sDom': 'T<\"clear\">lfrtip', 'aaSorting': [[ " + sort + ", 'asc' ]] });\n";
//        return "$('" + tableSelector + "').dataTable({'tblCode':" + tblCode + ",'iDisplayLength':100,'sScrollY': " + height + ",'bJQueryUI': true,'sPaginationType': 'full_numbers','sDom': 'C<\"clear\">Rlfrtip','oColVis':{}, 'aaSorting': [[ " + sort + ", 'asc' ]] });\n";
        return "$('#" + tableSelector + "').dataTable({'tblCode':" + tblCode + ",'tblTitle':'" + tableTitle
                + "','iDisplayLength':300,'sScrollY': " + height + ",'bJQueryUI': true,'sPaginationType': 'full_numbers', 'aaSorting': [[ " + sort + ", 'desc' ]] });\n";//C<\"clear\">lfrtip<\"H\"Cfr>t<\"F\"ip><\"H\"fr>lfrtipt<\"F\"ip>
//        return "$('" + tableSelector + "').dataTable({'tblCode':" + tblCode + ",'iDisplayLength':100,'sScrollY': " + height + ",'bJQueryUI': true,'sPaginationType': 'full_numbers', 'aaSorting': [[ " + sort + ", 'asc' ]] });\n";
    }

    /**
     *
     * @param tableSelector
     * @param newRow sample: "<span class='tahoma10' style='text-align: right;'
     * onclick='alert(3);'>" + "aaa" + "</span>"
     */
    public static String table_add_row(String tableSelector, String... newRow) {
        tableSelector = tableSelector == null ? "null" : tableSelector.replace("#", "");
        StringBuffer html = new StringBuffer();

        html.append("$('#" + tableSelector + "').dataTable().fnAddData( [");
        for (int i = 0; i < newRow.length; i++) {
            html.append((i == 0 ? "" : ",") + "\"" + newRow[i].replace("\"", "'") + "\"");
        }
        html.append("]);");
        return html.toString();
    }

    public static String table_edit_row(String tableSelector, Tr tr) {
        tableSelector = tableSelector == null ? "null" : tableSelector.replace("#", "");
        StringBuffer html = new StringBuffer();
        for (int i = 0; i < tr.getTds().size(); i++) {
            html.append("$('#" + tableSelector + "').dataTable().fnUpdate(\"" + tr.getTds().get(i).getHtmlValue().replace("\"", "")
                    + "\",$('#" + tableSelector + "').dataTable().fnGetPosition("
                    + "document.getElementById('" + tableSelector + "" + tr.getId() + "')),"
                    + tr.getTds().get(i).getTdPositiong() + ");\n");
        }
        return html.toString();
    }

    public static String table_delete_row(String tableSelector, int id) {
        tableSelector = tableSelector == null ? "null" : tableSelector.replace("#", "");
        return "$('#" + tableSelector + "').dataTable().fnDeleteRow($('#" + tableSelector + "').dataTable().fnGetPosition("
                + "document.getElementById('" + tableSelector + "" + id + "')));";
    }
    /*
     *Dont put "#" befor selector
     *This method add "#"befor and "_editor" after selector.
     *There Must be in cms.html and ***.js a variable whit this name: ****_editor
     * 
     @example 
     * html.append(Js.setValEditor("" + _content, row.get(0).get("portal_user_content")));
     */

    public static String setValEditor(String editorName, Object value) {
        return setValEditor(editorName, value.toString());
    }

    /*
     *Dont put "#" befor selector
     *This method add "#"befor and "_editor" after selector.
     *There Must be in cms.html and ***.js a variable whit this name: ****_editor
     * 
     @example 
     * html.append(Js.setValEditor("" + _content, row.get(0).get("portal_user_content")));
     */
    public static String setValEditor(String editorName, String value) {
        return "new jj(" + editorName + "_editor).jjEditorVal('" + replacor(value) + "');\n";
    }

    public static String setValDate(String keySelector, Object value) {
        return setValDate(keySelector, value.toString());
    }

    public static String setValDate(String selector, String value) {
        return "new jj('" + selector + "').jjVal('" + jjCalendar_IR.getViewFormat(value) + "');\n";
    }

    public static String buttonMouseClick(String buttonSelector, String methods) {
        return "$('" + buttonSelector + "').button().click(function(e) {\n" + methods + "});\n";
    }

    public static String importJs(String jsLocation) {
        return "<script type=\"text/javascript\" src=\"" + jsLocation.replace("\\\\", "/") + "\"></script>";
    }

    public static String importCss(String cssLocation) {
        return "<link type=\"text/css\" href=\"" + cssLocation.replace("\\\\", "/") + "\" rel=\"stylesheet\" />";
    }

    public static String htmlMarquee(List<String> line) {
        String desc = "<marquee direction=\"up\" scrollamount=\"1px\" scrolldelay=\"270\" truespeed=\"120\" width=\"150\" height=\"100\" onmouseover=\"this.stop();\" onmouseout=\"this.start();\" >";
        for (int i = 0; i < line.size(); i++) {
            desc += line.get(i) + "<br/>";
        }
        desc += "</marquee>" + "<br/>";
        return desc;
    }

    public static String htmlMarquee(String message) {
        String desc = "<marquee direction=\"up\" scrollamount=\"1px\" scrolldelay=\"270\" truespeed=\"120\" width=\"150\" height=\"100\" onmouseover=\"this.stop();\" onmouseout=\"this.start();\" >";
        desc += message;
        desc += "</marquee>" + "<br/>";
        return desc;
    }

    public static String htmlLabel(String id, String value, String style) {
        return String.format("<span id='%s' %s>%s</span>", id, style, value);
    }

    public static String htmlTextBox(String id, String value, int maxLength, String style) {
        String ml = maxLength < 0 ? "" : "maxlength='" + maxLength + "'";
        return String.format("<input type='text' id='%s' name='%s' value='%s' %s  %s/>", id, id, value, ml, style);
    }

    public static String htmlPassword(String id) {
        return String.format("<input type='password' id='%s' name='%s' value=''/>", id, id);
    }

    public static String htmlHiddenField(String id, String value) {
        return String.format("<input type='hidden' id='%s' name='%s' value='%s'/>", id, id, value);
    }

    public static String htmlSubmit(String id, String text, int mode, String code) {
        return String.format("<input type='button' id='%s' name='%s' onclick=\"submitForm(%d, '%s')\" value='%s' />", id, id, mode, code, text);
    }

    public static String htmlSubmit(String id, String text, int mode, String code, String onclick) {
        return String.format("<input type='button' id='%s' name='%s' onclick=\"%s submitForm(%d, '%s')\" value='%s' />", id, id, onclick, mode, code, text);
    }

    public String htmlSelect(String id, List<String> options, String style) {
        String html = "";

        html += String.format("<select id='%s' name='%s' size='1' %s>", id, id, style);
        for (int i = 0; i < options.size(); i++) {
            html += options.get(i);
        }
        html += "</select>";
        return html;
    }

    public String htmlOption(String value, String style, boolean isSelected) {
        String html = "";
        String selected = (isSelected) ? "selected='selected'" : "";
        html += String.format("<option value='%s' %s %s>%s</option>", value, selected, style, value);
        return html;
    }

    public static class jjContent {

        static String cms = "cmsContent";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }

        public static String searchTextInTitle(String text) {
            return cms + ".m_searchTextInTitle('" + text + "');\n";
        }

        public static String searchTextInAll(String text) {
            return cms + ".m_searchTextInAll('" + text + "');\n";
        }

        public static String wikiId(String id) {
            return cms + ".m_wikiId('" + id + "');\n";
        }

        public static String wikiText(String text) {
            return cms + ".m_wikiText('" + text + "');\n";
        }

        public static String clean() {
            return cms + ".m_clean();\n";
        }

        public static String addNew() {
            return cms + ".m_add_new();\n";
        }

        public static String showForm() {
            return cms + ".m_show_form();\n";
        }

        public static String showTbl() {
            return cms + ".m_show_tbl();\n";
        }
    }

    public static class jjCustomer {

        static String cms = "cmsCustomer";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String clean() {
            return cms + ".m_clean();\n";
        }

        public static String addNew() {
            return cms + ".m_add_new();\n";
        }

        public static String showForm() {
            return cms + ".m_show_form();\n";
        }

        public static String showTbl() {
            return cms + ".m_show_tbl();\n";
        }
    }

    public static class jjProduct {

        static String cms = "cmsProduct";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String clean() {
            return cms + ".m_clean();\n";
        }

        public static String addNew() {
            return cms + ".m_add_new();\n";
        }

        public static String showForm() {
            return cms + ".m_show_form();\n";
        }

        public static String showTbl() {
            return cms + ".m_show_tbl();\n";
        }
        //By Md

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjFactor {

        static String cms = "cmsFactor";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String print(String id) {
            return "printFactor(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String clean() {
            return cms + ".m_clean();\n";
        }

        public static String addNew() {
            return cms + ".m_add_new();\n";
        }

        public static String showForm() {
            return cms + ".m_show_form();\n";
        }

        public static String showTbl() {
            return cms + ".m_show_tbl();\n";
        }
    }

    public static class jjNews {

        static String cms = "cmsNews";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }

        public static String clean() {
            return cms + ".m_clean();\n";
        }

        public static String addNew() {
            return cms + ".m_add_new();\n";
        }

        public static String showForm() {
            return cms + ".m_show_form();\n";
        }

        public static String showTbl() {
            return cms + ".m_show_tbl();\n";
        }
    }

    public static class jjUser {

        static String cms = "cmsUser";

        public static String insert() {
            return "cmsUser.m_insert();\n";
        }

        public static String refresh() {
            return "cmsUser.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String showTbl() {
            return cms + ".m_show_tbl();\n";
        }
    }

    public static class jjGroup {

        static String cms = "cmsGroup";

        public static String insert() {
            return "cmsGroup.m_insert();\n";
        }

        public static String refresh() {
            return "cmsGroup.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }
    }

    public static class jjPermission {

        static String cms = "cmsPermission";

        public static String insert() {
            return "cmsPermission.m_insert();\n";
        }

        public static String refresh() {
            return "cmsPermission.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }
    }

    public static class jjCategoryForum {

        static String cms = "cmsCategoryForum";

        public static String insert() {
            return "cmsCategoryForum.m_insert();\n";
        }

        public static String refresh() {
            return "cmsCategoryForum.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }
    }

    public static class jjCategoryGallery {

        static String cms = "cmsCategoryGallery";

        public static String insert() {
            return "cmsCategoryGallery.m_insert();\n";
        }

        public static String refresh() {
            return "cmsCategoryGallery.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjCategoryNews {

        static String cms = "cmsCategoryNews";

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjCategoryPoll {

        static String cms = "cmsCategoryPoll";

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String refresh() {
            return "cmsCategoryPoll.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }
    }

    public static class jjCategoryProduct {

        static String cms = "cmsCategoryProducts";

        public static String insert() {
            return "cmsCategoryProducts.m_insert();\n";
        }

        public static String refresh() {
            return "cmsCategoryProducts.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjManageDropDown {

        public static String getListBookForLevel() {
            return "manageDropDown.getListBookForLevel();\n";
        }

        public static String getListBookForClass() {
            return "manageDropDown.getListBookForClass();\n";
        }

        public static String getListRoomForClass() {
            return "manageDropDown.getListRoomForClass();\n";
        }

        public static String getListTeacherForClass() {
            return "manageDropDown.getListTeacherForClass();\n";
        }

        public static String getListTeacherForSalari() {
            return "manageDropDown.getListTeacherForSalari();\n";
        }

        public static String getListClassForRegist() {
            return "manageDropDown.getListClassForRegist();\n";
        }

        public static String getListTermForRegist() {
            return "manageDropDown.getListTermForRegist();\n";
        }

        public static String getListTermForClass() {
            return "manageDropDown.getListTermForClass();\n";
        }

        public static String getListTermForTeacherSalari() {
            return "manageDropDown.getListTermForTeacherSalari();\n";
        }

        public static String getListLevelForClass() {
            return "manageDropDown.getListLevelForClass();\n";
        }

        public static String getListLevelForClass2() {
            return "manageDropDown.getListLevelForClass2();\n";
        }

        public static String getListLevelForStudent() {
            return "manageDropDown.getListLevelForStudent();\n";
        }
    }

    public static class jjComment {

        static String cms = "cmsComment";

        public static String insert() {
            return "cmsComment.m_insert();\n";
        }

        public static String refresh() {
            return "cmsComment.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }
    }

    public static class jjEnrolment {

        static String cms = "cmsEnrolment";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }
    }

    public static class jjEnrolment3 {

        static String cms = "cmsEnrolment3";

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }
    }

    public static class jjForum {

        static String cms = "cmsForum";

        public static String insert() {
            return "cmsForum.m_insert();\n";
        }

        public static String refresh() {
            return "cmsForum.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }
    }

    public static class jjPic {

        static String cms = "cmsPic";

        public static String insert() {
            return "cmsPic.m_insert();\n";
        }

        public static String refresh() {
            return "cmsPic.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjPortal {

        static String cms = "cmsPortal";
//---------------------------------------------------------

        public static String insertPostByUserPortal() {
            return "insertPostByUserPortal();\n";
        }

        public static String portalFormClose() {//for portal_takrod_portal.jsp
            return "portalFormClose();\n";
        }

        public static String portalPostLoadForm() {//for portal_takrod_portal.jsp
            return "portalPostLoadForm();\n";
        }
//---------------------------------------------------------

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjPortalUser {

        static String cms = "cmsPortalUser";

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjPoll {

        static String cms = "cmsPoll";

        public static String insert() {
            return "cmsPoll.m_insert();\n";
        }

        public static String refresh() {
            return "cmsPoll.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }

        public static String addEN(String id) {
            return cms + ".m_add_EN(" + id + ");\n";
        }

        public static String addAr(String id) {
            return cms + ".m_add_Ar(" + id + ");\n";
        }
    }

    public static class jjSMS {

        static String cms = "cmsSMS";

        public static String insert() {//send sms
            return "cmsSMS.m_insert();\n";
        }

        public static String refresh() {
            return "cmsSMS.m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }
    }

    public static class jjTiceBook {

        static String cms = "cmsTiceBook";

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }
    }

    public static class jjTiceRoom {

        static String cms = "cmsTiceRoom";

        public static String insert() {
            return cms + ".m_insert();\n";
        }

        public static String refresh() {
            return cms + ".m_refresh();\n";
        }

        public static String edit() {
            return cms + ".m_edit();\n";
        }

        public static String delete(String id) {
            return cms + ".m_delete(" + id + ");\n";
        }

        public static String select(String id) {
            return cms + ".m_select(" + id + ");\n";
        }
    }
}
