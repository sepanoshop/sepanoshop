package cms.cms;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

public class PortalUser {

    public static String tableName = "portal_user";
    public static String _id = "id";
    public static String _UserId = "portal_user_UserId";
    public static String _UserName = "portal_user_UserName";
    public static String _pass = "portal_user_pass";
    public static String _firstName = "portal_user_firstName";
    public static String _lastName = "portal_user_lastName";
    public static String _pageTitle1 = "portal_user_pageTitle1";
    public static String _pageTitle2 = "portal_user_pageTitle2";
    public static String _mobile = "portal_user_mobile";
    public static String _phone = "portal_user_phone";
    public static String _val1 = "portal_user_val1";
    public static String _val2 = "portal_user_val2";
    public static String _val3 = "portal_user_val3";
    public static String _val4 = "portal_user_val4";
    public static String _val5 = "portal_user_val5";
    public static String _val6 = "portal_user_val6";
    public static String _pic1 = "portal_user_pic";
    public static String _comment = "portal_user_comment";
    public static String _content = "portal_user_content";
    public static String _status = "portal_user_status";
    public static String _address = "portal_user_address";
    public static String _visit = "portal_user_visit";
    public static String _date = "portal_user_creationDate";
    public static String _blocked = "portal_user_blocked";
    public static String _isActive = "portal_user_isActive";
//    public static String _dislike = "cmsPortalUser";
//    public static String _parent = "portal_post_parent";
//    public static String _lang = "pic_lang";
//    public static String _price = "pic_price";
//    public static String _comment = "pic_comment";
//    public static String _margin = "pic_margin";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 100;
    public static int rul_ins = 101;
    public static int rul_edt = 102;
    public static int rul_dlt = 103;
//    public static int rul_lng = 46;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuilder html = new StringBuilder();
            DefaultTableModel dtm = db.Select(tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table id='refreshPortalUser' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='20%'>عنوان</th>");
            html.append("<th width='20%'>نگارنده</th>");
//            html.append("<th width='60%'>تصویر</th>");
            html.append("<th width='5%'>تایید</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
//            String picsStr;
//            String pic1 = "";
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr >");
                html.append("<td class='c'>" + row.get(i).get(_id).toString() + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_pageTitle1).toString() + "<br/>" + row.get(i).get(_pageTitle1).toString() + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_firstName).toString() + "  " + row.get(i).get(_lastName).toString() + "</td>");
                // small pic in portalPost refresh table
//                pic1 = row.get(i).get(_pic1).toString().replace(".", "_small.");
//                picsStr = pic1.equalsIgnoreCase("") ? "" : "<img src='upload/" + pic1 + "'/>";
//                html.append("<td class='c'>" + picsStr + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_isActive).toString()) + "</td>");
                html.append("<td class='c'><img src='img/l.png' style='max-height:30px' onclick='cmsPortalUser.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'  /></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swPicTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPortalUser", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "20" : "", "لیست پرتال نویس ها");//for tbl code look at cmsManager.tblMethod(tblcode)
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#portalUser_button", "<input type=\"button\" id=\"insert_portalUser_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\" />"));
                html.append(Js.buttonMouseClick("#insert_portalUser_new", Js.jjPortalUser.insert()));
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
            String UserId = jjTools.getParameter(request, _UserId).toString();
            if (UserId.equals("")) {
                return Js.dialog("به عنوان مدیر این صفحه،  کاربری را انتخاب کنید  را انتخاب کنید");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_UserId, UserId);
            map.put(_UserName, jjTools.getParameter(request, _UserName));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_blocked, jjTools.getParameter(request, _blocked));
            map.put(_comment, jjTools.getParameter(request, _comment));
            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), false));
            map.put(_firstName, jjTools.getParameter(request, _firstName));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_lastName, jjTools.getParameter(request, _lastName));
            map.put(_mobile, jjTools.getParameter(request, _mobile));
            map.put(_pageTitle1, jjTools.getParameter(request, _pageTitle1));
            map.put(_pageTitle2, jjTools.getParameter(request, _pageTitle2));
            map.put(_pass, jjTools.getParameter(request, _pass));
            map.put(_phone, jjTools.getParameter(request, _phone));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_visit, jjTools.getParameter(request, _visit));
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPortalUser.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String UserId = jjTools.getParameter(request, _UserId).toString();
            if (UserId.equals("")) {
                return Js.dialog("به عنوان مدیر این صفحه،  کاربری را انتخاب کنید  را انتخاب کنید");
            }
            String id = jjTools.getParameter(request, _id);
            if (id.equals("")) {
                return Js.dialog("کد پرتال وارد نشده");
            }

            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_UserId, UserId);
            map.put(_UserName, jjTools.getParameter(request, _UserName));
            map.put(_address, jjTools.getParameter(request, _address));
            map.put(_blocked, jjTools.getParameter(request, _blocked));
            map.put(_comment, jjTools.getParameter(request, _comment));
            map.put(_content, jjTools.getParameter(request, _content));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), false));
            map.put(_firstName, jjTools.getParameter(request, _firstName));
            map.put(_isActive, jjTools.getParameter(request, _isActive));
            map.put(_lastName, jjTools.getParameter(request, _lastName));
            map.put(_mobile, jjTools.getParameter(request, _mobile));
            map.put(_pageTitle1, jjTools.getParameter(request, _pageTitle1));
            map.put(_pageTitle2, jjTools.getParameter(request, _pageTitle2));
            map.put(_pass, jjTools.getParameter(request, _pass));
            map.put(_phone, jjTools.getParameter(request, _phone));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_visit, jjTools.getParameter(request, _visit));

//            map.put(_rating, jjTools.getParameter(request, _rating));
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPortalUser.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//    public static String changeAllPrice(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            String int1 = jjTools.getParameter(request, "int1");
//            String int2 = jjTools.getParameter(request, "int2");
//
//            if (jjNumber.isDigit(int1)) {
//                if (jjNumber.isDigit(int2)) {
//                    Map<String, Object> map = new HashMap<String, Object>();
////                    map.put(_price, Integer.parseInt(int2));
////                    db.update(tableName, map, _price + "=" + int1);
//                }
//
//            }
//            return "";
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String WritXmlConfigSliderFlash(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        ServerLog.Print("WritXmlConfigSliderFlash");
//        if (jjTools.getParameter(request, _category_id).equals("1")) {
//            String address = request.getServletContext().getRealPath("/js/prettyPhoto");
//            String address2 = request.getServletContext().getRealPath("/");
//            File file = new File(address + "/sample.xml");
//            File file_fa = new File(address2 + "/config.xml");
//            File file_en = new File(address2 + "/config_en.xml");
//            File file_ar = new File(address2 + "/config_ar.xml");
//            String text = "";
//            StringBuffer slide = new StringBuffer();
//            StringBuffer slide_en = new StringBuffer();
//            StringBuffer slide_ar = new StringBuffer();
//            if (file.exists()) {
//                text = jjFileTxt.read(file);
//                int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
//                List<Map<String, Object>> slideRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1"));
//                File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
//
////                BRIEF TRANSITION GUIDE
////		direction: left, right, up, down
////		slicing: horizontal, vertical
////		num - number of slices each transition consists of
////		shader - transition shading type - none, flat, phong
////		cube_color - define cube color during transition (hex value)
////		duration - time for each sliced cube transition
////		delay – time each sliced cube will wait before starting transition.
////		z_multiplier - z offset enables jo-jo effect of the cubes on z axis during transition. Higher Z numbers create a pull back effect.
//                String direction[] = {"left", "right", "up", "down"};
//                String shader[] = {"none", "flat", "phong"};
//                int counter = 0;
//                int directionCounter = -1;
//                for (int i = 0; i < slideRow.size(); i++) {
//                    File f = new File(folderAddressUpload.getAbsolutePath()
//                            + "/" + (slideRow.get(i).get(_url_name).toString().replace("%20", " ") + "."
//                            + slideRow.get(i).get(_url_ex).toString()));
//                    if (f.exists()) {
//                        counter += 1;
//                        directionCounter += 1;
//                        String s = ("<slide>\n<url>upload/" + f.getName()
//                                + "</url>\n<link target='_self'>" + Server.siteName
//                                + "</link>\n</slide>\n"
//                                + "<transition num='3' slicing='" + (counter % 2 == 0 ? "vertical" : "horizontal")
//                                + "' direction='" + (direction[directionCounter])
//                                + "' shader='" + (counter % 2 == 0 ? "flat" : "phong") + "' delay='0.08' z_multiplier='1' />\n");
//                        if (slideRow.get(i).get(_lang).toString().equals("1")) {
//                            slide.append(s);
//                        } else if (slideRow.get(i).get(_lang).toString().equals("2")) {
//                            slide_en.append(s);
//                        } else {
//                            slide_ar.append(s);
//                        }
//                        directionCounter = directionCounter > 2 ? -1 : directionCounter;
////                        slide += "\n"
////                                + "        <slide><url>upload/" + f.getName()
////                                + "</url><link target='_self'>your_page_link.htm</link><description>\n"
////                                + "                <heading>" + Server.siteName
////                                + "!</heading><paragraph>" + slideRow.get(i).get(_pageTitle1)
////                                + "</paragraph></description></slide>\n"
////                                + "        <transition num='" + slideRow.size() + "' slicing='vertical' direction='up' shader='flat' delay='0.08' z_multiplier='1' />";
//                    }
//                }
//
//            }
//            jjFileTxt.write(file_fa, text.replace("jjSlide", slide.toString()));
//            jjFileTxt.write(file_en, text.replace("jjSlide", slide_en.toString()));
//            jjFileTxt.write(file_ar, text.replace("jjSlide", slide_ar.toString()));
//        }
//        return "";
//    }

    /*
     Delete post whit pictures which uploaded by user na dalso _small pictures
     */
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
            List<Map<String, Object>> postRow = jjDatabase.separateRow(db.Select(Portal.tableName, Portal._ownerId + "=" + id));
            if (!postRow.isEmpty()) {
                return Js.dialog("برای این پرتال چند پست وجود دارد - حذف پرتال ممکن نیست");
            } else {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
                //delete pic from upload folder
                File pic;
                File picSmall;
                File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                if (row.size() > 0) {
                    //There is not any problem for empty pic name(at least in wjndows and JDK)
                    pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic1).toString());
                    if (pic.exists()) {
                        pic.delete();
                    }
                    picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic1).toString().replace(".", "_small."));
                    if (picSmall.exists()) {
                        picSmall.delete();
                    }
                }
                if (!db.delete(tableName, _id + "=" + id)) {
                    String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "Delete Fail;";
                    }
                    return Js.dialog(errorMessage);
                }
            }
            return Js.jjPortalUser.refresh();
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
            html.append(Js.setVal("#" + _pageTitle1, row.get(0).get(_pageTitle1)));
            html.append(Js.setVal("#" + _pageTitle2, row.get(0).get(_pageTitle2)));
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _UserId, row.get(0).get(_UserId)));
            html.append(Js.setVal("#" + _UserName, row.get(0).get(_UserName)));
            html.append(Js.setVal("#" + _pass, row.get(0).get(_pass)));
            html.append(Js.setVal("#" + _mobile, row.get(0).get(_mobile)));
            html.append(Js.setVal("#" + _phone, row.get(0).get(_phone)));

            html.append(Js.setVal("#" + _comment, row.get(0).get(_comment)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.setVal("#" + _address, row.get(0).get(_address)));
            html.append(Js.setVal("#" + _firstName, row.get(0).get(_firstName)));
            html.append(Js.setVal("#" + _lastName, row.get(0).get(_lastName)));
            if (row.get(0).get(_pic1).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalUser_pic"
                        + "", "src", "upload/" + row.get(0).get(_pic1).toString()));
                html.append(Js.setVal("#portal_user_pic", row.get(0).get(_pic1).toString()));
            }
            html.append(Js.setVal("#" + _val1, row.get(0).get(_val1)));
            html.append(Js.setVal("#" + _val2, row.get(0).get(_val2)));
            html.append(Js.setVal("#" + _val3, row.get(0).get(_val3)));
            html.append(Js.setVal("#" + _val4, row.get(0).get(_val4)));
            html.append(Js.setVal("#" + _val5, row.get(0).get(_val5)));
            html.append(Js.setVal("#" + _val6, row.get(0).get(_val6)));
            html.append(Js.setValEditor("" + _content, row.get(0).get(_content)));//This method add "#"befor and "_editor" after selector.
//            html.append(Js.setVal("#" + _val6, row.get(0).get(_val6)));
//            html.append(Js.setVal("#" + _mobile, row.get(0).get(_mobile)));
//            html.append(Js.setVal("#" + _phone, row.get(0).get(_phone)));
//            html.append(Js.setVal("#" + _pageTitle1, row.get(0).get(_pageTitle1)));
//            html.append(Js.setVal("#" + _pageTitle2, row.get(0).get(_pageTitle2)));
            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal("#" + _visit, visit));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _visit + "_checkbox", 0));
                html.append(Js.setAttr("#" + _visit, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _visit + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _visit, "disabled"));
            }
            html.append(Js.setVal("#" + _isActive, row.get(0).get(_isActive)));
            html.append(Js.setVal("#" + _blocked, row.get(0).get(_blocked)));
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type='button' id='edit_PortalUser' value='" + lbl_edit + "' class='tahoma10' />");
                html.append(Js.buttonMouseClick("#edit_PortalUser", Js.jjPortalUser.edit()));

            }
            if (accDel) {
                html2.append("<input type='button' id='delete_PortalUser' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_PortalUser", Js.jjPortalUser.delete(id)));
            }
//            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);
//            if (acclng) {
//                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
//                if (rowEn.size() > 0) {
//                    html2.append("<input type='button' id='edit_en_Pic' value='" + lbl_edit_en + "' class='tahoma10'  />");
//                    html.append(Js.buttonMouseClick("#edit_en_Pic", Js.jjPic.select(rowEn.get(0).get(_id).toString())));
//                } else {
//                    if (row.get(0).get(_parent).equals("0")) {
//                        html2.append("<input type='button' id='add_en_Pic' value='" + lbl_add_en + "' class='tahoma10' />");
//                        html.append(Js.buttonMouseClick("#add_en_Pic", Js.jjPic.addEN(id)));
//                    }
//                }
//                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
//                if (rowAr.size() > 0) {
//                    html2.append("<input type='button' id='edit_ar_Pic' value='" + lbl_edit_ar + "' class='tahoma10'  />");
//                    html.append(Js.buttonMouseClick("#edit_ar_Pic", Js.jjPic.select(rowAr.get(0).get(_id).toString())));
//                } else {
//                    if (row.get(0).get(_parent).equals("0")) {
//                        html2.append("<input type='button' id='add_ar_Pic' value='" + lbl_add_ar + "' class='tahoma10' />");
//                        html.append(Js.buttonMouseClick("#add_ar_Pic", Js.jjPic.addAr(id)));
//                    }
//                }
//            }
            return (Js.setHtml("#portalUser_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String get_val5List(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _val5));
            StringBuilder html = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_val5).toString() + "'"
                        + (row.get(i).get(_val5).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_val5).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String get_val6List(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _val6));
            StringBuilder html = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_val6).toString() + "'"
                        + (row.get(i).get(_val6).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_val6).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * return a title for id in requesr parametre
     */
    public static String getPortalTitle(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _pageTitle1, _id + " = " + id));//id>i for ignore site master in result
            StringBuilder html = new StringBuilder();
            if (!row.isEmpty()) {
                html.append(row.get(0).get(_pageTitle1).toString());
                return Js.setVal("#" + panel, html.toString());
            } else {
                return Js.dialog("چنین پرتالی وجود ندارد");
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * return a list of avalable portals id
     *
     *
     */
    public static String getPortalUsersList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id));//id>i for ignore site master in result
            StringBuilder html = new StringBuilder();
            html.append("<option value=''></option>");//'option' and 'value' for this fild is same('value' is not necessary)
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_id).toString() + "'"
                        + (row.get(i).get(_id).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_id).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getUsersCode(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(cms.access.Access_User.tableName, cms.access.Access_User._id, " id>1 "));//id>i for ignore site master in result
            StringBuilder html = new StringBuilder();
            html.append("<option value=''></option>");//'option' and 'value' for this fild is same('value' is not necessary)
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(cms.access.Access_User._id).toString() + "'"
                        + (row.get(i).get(cms.access.Access_User._id).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(cms.access.Access_User._id).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /*
     Fill some field in portal_user.html in add_ne or edit form.
     */
    public static String getUsersInfo(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String userId = jjTools.getParameter(request, "userId");
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(cms.access.Access_User.tableName, cms.access.Access_User._id + " = " + userId + " AND id>1"));//id>i for ignore site master in result
            StringBuilder html = new StringBuilder();
            if (!row.isEmpty()) {
                html.append(Js.setVal("#portal_user_UserName", row.get(0).get(cms.access.Access_User._email)));
                html.append(Js.setVal("#portal_user_pass", row.get(0).get(cms.access.Access_User._pass)));
                html.append(Js.setVal("#portal_user_firstName", row.get(0).get(cms.access.Access_User._name)));
                html.append(Js.setVal("#portal_user_lastName", row.get(0).get(cms.access.Access_User._family)));
            } else {
                html.append(Js.dialog("کاربری با این کد در سیستم وجود ندارد"));
            }
            return html.toString();
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
//    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            String id = jjTools.getParameter(request, _id);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            if (row.size() == 0) {
//                String errorMessage = "رکوردی با این کد وجود ندارد.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Select Fail;";
//                }
//                return Js.dialog(errorMessage);
//            }
//            StringBuffer html = new StringBuffer();
//            StringBuffer html2 = new StringBuffer();
//
//            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
//            html.append(Js.setVal("#" + _parent, id));
//            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
//            html.append(Js.setVal("#" + _pageTitle1, row.get(0).get(_pageTitle1)));
//            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
//            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
//            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
//            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
//            html.append(Js.setVal("#" + _lang, "2"));
//
//            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
//            if (accIns) {
//                html2.append(Js.setHtml("#Pic_button", "<input type=\"button\" id=\"insert_Pic_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
//                html2.append(Js.buttonMouseClick("#insert_Pic_new", Js.jjPic.insert()));
//            }
//            return html.toString() + html2.toString();
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String add_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            String id = jjTools.getParameter(request, _id);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            if (row.size() == 0) {
//                String errorMessage = "رکوردی با این کد وجود ندارد.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Select Fail;";
//                }
//                return Js.dialog(errorMessage);
//            }
//            StringBuffer html = new StringBuffer();
//            StringBuffer html2 = new StringBuffer();
//
//            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
//            html.append(Js.setVal("#" + _parent, id));
//            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
//            html.append(Js.setVal("#" + _pageTitle1, row.get(0).get(_pageTitle1)));
//            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
//            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
//            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
//            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
//            html.append(Js.setVal("#" + _lang, "3"));
//
//            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
//            if (accIns) {
//                html2.append(Js.setHtml("#Pic_button", "<input type='button' id='insert_Pic_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
//                html2.append(Js.buttonMouseClick("#insert_Pic_new_ar", Js.jjPic.insert()));
//            }
//            return html.toString() + html2.toString();
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String sw_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            String text = jjTools.getParameter(request, "text").trim();
//            String panel = jjTools.getParameter(request, "panel");
//            String titlePanel = jjTools.getParameter(request, "title");
//            panel = (panel.equals("") ? "sw" : panel);
//            titlePanel = (titlePanel.equals("") ? "swTitle" : titlePanel);
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._pageTitle1 + "='" + text + "'"));
//            if (jjNumber.isDigit(text)) {
//                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._pageTitle1 + "='" + text + "'"));
//            }
//            if (row.size() > 0) {
//                List<Map<String, Object>> picRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(Category_Gallery._id)));
//                if (picRow.size() < 1) {
//                    String errorMessage = "تصویری در این گالری وجود ندارد";
//                    if (jjTools.isLangEn(request)) {
//                        errorMessage = "No Exist any pictuare in this gallery ;";
//                    }
//                    return Js.setHtml("#" + panel, errorMessage);
//                }
//                StringBuffer content = new StringBuffer();
//                int counter = 0;
//                int counter2 = 0;
//                content.append("<table  width='100%'>");
//                for (int i = 0; i < picRow.size(); i++) {
//                    counter++;
//                    counter2++;
//                    if (counter == 1) {
//                        content.append("<tr style='vertical-align: top' >");
//                    } else if (counter == 2) {
//                        counter = 0;
//                    }
//
//
//                    content.append("<td>");
//                    content.append("<table ><tr><td><img src='upload/"
//                            + picRow.get(i).get(_url_name) + "." + picRow.get(i).get(_url_ex)
//                            + "' width='300'></td></tr><tr><td class='c'>"
//                            + picRow.get(i).get(_pageTitle1)
//                            + "</td></tr></table>");
//                    content.append("</td>");
//
//                    if (counter2 == 2) {
//                        content.append("</tr>");
//                        counter2 = 0;
//                    }
//                }
//                if (picRow.size() % 2 != 0) {
//                    content.append("<td>&nbsp;</td></tr>");
//                }
//                content.append("</table>");
//                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._pageTitle1));
//            } else {
//                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "wiki Text Fail;";
//                }
//                return Js.setHtml("#" + panel, errorMessage);
//            }
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            if (jjTools.isLangEn(request)) {
//                return sw_En(request, db, isPost);
//            }
//            String text = jjTools.getParameter(request, "text").trim();
//            String panel = jjTools.getParameter(request, "panel");
//            String titlePanel = jjTools.getParameter(request, "title");
//            panel = (panel.equals("") ? "sw" : panel);
//            titlePanel = (titlePanel.equals("") ? "swTitle" : titlePanel);
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._pageTitle1 + "='" + text + "'"));
//            if (jjNumber.isDigit(text)) {
//                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + "='" + text + "'"));
//            }
//            if (row.size() > 0) {
//                List<Map<String, Object>> picRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(Category_Gallery._id)));
//                if (picRow.size() < 1) {
//                    String errorMessage = "تصویری در این گالری وجود ندارد";
//                    if (jjTools.isLangEn(request)) {
//                        errorMessage = "No Exist any pictuare in this gallery ;";
//                    }
//                    return Js.setHtml("#" + panel, errorMessage);
//                }
//                StringBuffer content = new StringBuffer();
//                int counter = 0;
//                int counter2 = 0;
//                content.append("<table  width='100%'>");
//                for (int i = 0; i < picRow.size(); i++) {
//                    counter++;
//                    counter2++;
//                    if (counter == 1) {
//                        content.append("<tr style='vertical-align: top' >");
//                    } else if (counter == 2) {
//                        counter = 0;
//                    }
//
//
//                    content.append("<td>");
//                    content.append("<table ><tr><td><img src='upload/"
//                            + picRow.get(i).get(_url_name) + "." + picRow.get(i).get(_url_ex)
//                            + "' width='300'></td></tr><tr><td class='c'>"
//                            + picRow.get(i).get(_pageTitle1)
//                            + "</td></tr></table>");
//                    content.append("</td>");
//
//                    if (counter2 == 2) {
//                        content.append("</tr>");
//                        counter2 = 0;
//                    }
//                }
//                if (picRow.size() % 2 != 0) {
//                    content.append("<td>&nbsp;</td></tr>");
//                }
//                content.append("</table>");
//                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._pageTitle1));
//            } else {
//                String errorMessage = "رکوردی با این محتوا وجود ندارد.";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "wiki Text Fail;";
//                }
//                return Js.setHtml("#" + panel, errorMessage);
//            }
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }

    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
//            String delay = jjTools.getParameter(request, "delay");
            List<Map<String, Object>> allPostalUsers = jjDatabase.separateRow(db.Select(tableName, _blocked + "=0 "));
            for (int i = 0; i < allPostalUsers.size(); i++) {
                html.append("<div class=\"col-md-3 no-pad\"><ul class=\"dropdown-menu\">");
                html.append(
                        "<li><a href=\""+Server.portalPage+"?PortalId=" + allPostalUsers.get(i).get(_id) + "\"><span><b><i class=\"fa fa-caret-left\"></i><i class=\"fa fa-caret-left\"></i></b></span> "
                        + "<font> " + allPostalUsers.get(i).get(_pageTitle1).toString() + " </font></a>"
                        + "</li>");
                html.append("</ul></div>");
            }
            if (panel.equals("")) {
                panel = "picSlider";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//    public static String getSlipprySliderRunScript(String divId, String width, String height, String delay) {
//        try {
////        speed = speed == "" ? "200" : speed;
//            return "var demo=$('#" + divId + "').slippry({"
//                    // transition: 'fade',
//                    // useCSS: true,
//                    // speed: 1000,
//                    // pause: 3000,
//                    // auto: true,
//                    // preload: 'visible',
//                    // autoHover: false
//                    + "});\n";
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String getSliderRunScript(String divId, String width, String height, String delay) {
//        try {
////        speed = speed == "" ? "200" : speed;
//            return "$('#" + divId + "').coinslider({"
//                    + "width:" + width + ","
//                    + "height:" + height + ","
//                    + "delay:" + delay + ","
//                    + "opacity:1,"
//                    + "spw:7,"
//                    + "sph:5,"
//                    + "sDelay:30,"
//                    + "titleSpeed:1500,"
//                    + "effect:'',"
//                    + "navigation:true,"
//                    + "links:true,"
//                    + "hoverPause:true"
//                    + "});\n";
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String getPicSlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            StringBuffer html = new StringBuffer();
//            String panel = jjTools.getParameter(request, "panel");
//            String width = jjTools.getParameter(request, "width").replace("px", "");
//            String height = jjTools.getParameter(request, "height").replace("px", "");
//            String delay = jjTools.getParameter(request, "delay");
//            width = jjNumber.isDigit(width) ? width : "650";
//            height = jjNumber.isDigit(height) ? height : "228";
//            int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
//            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
//
//            List<Map<String, Object>> allPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + lang));
//            for (int i = 0; i < allPic.size(); i++) {
//                File f = new File(folderAddressUpload.getAbsolutePath() + "/" + (allPic.get(i).get(_url_name).toString().replace("%20", " ") + "." + allPic.get(i).get(_url_ex).toString()));
//                if (f.exists()) {
//                    html.append("<a href='#'><img src='upload/" + allPic.get(i).get(_url_name) + "." + allPic.get(i).get(_url_ex) + "'/></a>");
//                }
//            }
//            if (panel.equals("")) {
//                panel = "picSlider";
//            }
//            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += getSliderRunScript(panel, width, height, delay);
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String getPicSlipprySlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            StringBuilder html = new StringBuilder();
//            String sliderId = "demo1";
//            String panel = jjTools.getParameter(request, "panel");
//            String width = jjTools.getParameter(request, "width").replace("px", "");
//            String height = jjTools.getParameter(request, "height").replace("px", "");
//            String delay = jjTools.getParameter(request, "delay");
//            width = jjNumber.isDigit(width) ? width : "650";
//            height = jjNumber.isDigit(height) ? height : "228";
//            int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
////            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
//
//            List<Map<String, Object>> allPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + lang));
//            html.append("<ul id='"+sliderId+"'>");
//            for (int i = 0; i < allPic.size(); i++) {
////                File f = new File(folderAddressUpload.getAbsolutePath() + "/" + (allPic.get(i).get(_url_name).toString().replace("%20", " ") + "." + allPic.get(i).get(_url_ex).toString()));
//                html.append("<li><a href='#'><img src='upload/" + allPic.get(i).get(_url_name) + "." + allPic.get(i).get(_url_ex) + "' " + "alt='"
//                        + allPic.get(i).get(_pageTitle1) + "'"
//                        + " /></a></li>");
//            }
//            html.append("</ul>");
//            if (panel.equals("")) {
//                panel = "picSlider";
//            }
//            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += getSlipprySliderRunScript(sliderId, width, height, delay);
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String getGallery(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            if (jjTools.isLangEn(request)) {
//                return getGallery_En(request, db, isPost);
//            }
//            if (jjTools.isLangAr(request)) {
//                return getGallery_Ar(request, db, isPost);
//            }
//            StringBuffer html = new StringBuffer();
//            StringBuffer html3 = new StringBuffer();
//            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
//            String panel = jjTools.getParameter(request, "panel");
//            panel = panel == null ? "sw" : panel;
//            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=1"));
//            int selectedI = 0;
//            for (int i = 0; i < categoryRow.size(); i++) {
//                if (categoryRow.get(i).get(Category_Gallery._id).toString().equals(id)) {
//                    selectedI = i;
//                }
//            }
//            html3.append("گالری");
//            html3.append("<ul class='picLink'>");
//            html3.append("<li><a class='picLink' onclick='swGetGallery("
//                    + categoryRow.get(selectedI).get(Category_Gallery._id) + ");' >" + categoryRow.get(selectedI).get(Category_Gallery._pageTitle1) + "</a></li>");
//
////            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
//
//            for (int i = 0; i < categoryRow.size(); i++) {
//                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._id)).getRowCount() > 0) {
//                    if (!categoryRow.get(selectedI).get(Category_Gallery._id).toString().equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
//
//                        html3.append("<li class='picLinkFlash'>" + "<a  class='picLink' onclick='swGetGallery("
//                                + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._pageTitle1) + "</a></li>");
//                    }
//                }
//            }
//
//            html3.append("</ul><br/><br/>");
//            html.append(html3.toString());
//
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id + " AND " + _lang + "=1"));
//            for (int i = 0; i < row.size(); i++) {
////                html.append("<table style='float:right;' class='picList'><tr><td>");
////                html.append("<img dir='rtl' title='"
////                        + Js.replacor(row.get(i).get(_pageTitle1).toString()).replace(" ", "&nbsp;")
////                        + "' src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
////                        + "_small." + row.get(i).get(_url_ex)
////                        + "' class='picInGallery mousePointer' onclick='picDialog(this);' />");
////                html.append("</td></tr><tr><td style='text-align:center'>");
////                html.append(row.get(i).get(_pageTitle1).toString());
////                html.append("</td></tr></table>");
//                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
//                        + "title='" + Js.replacor(row.get(i).get(_pageTitle1).toString()).replace(" ", "&nbsp;")
//                        + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
//
//            }
////            for (int i = 0; i < row.size(); i++) {
////                html.append("<table><tr class='picEveryRowInGallery'><td>&nbsp;</td></tr></table>");
////            }
//
//
//            String html2 = "";
////            html2= "$('#" + panel + "').html(\"" + html.toString() + "\");\n"
////                    + "showGallery();\n";
//            html2 += Js.setHtml("#" + panel, html.toString());
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String getGallery_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            StringBuffer html = new StringBuffer();
//            StringBuffer html3 = new StringBuffer();
//            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
//            String panel = jjTools.getParameter(request, "panel");
//            panel = panel == null ? "sw" : panel;
//            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._parent + "=" + id + " AND " + Category_Gallery._lang + "=2"));
//            html3.append("<span class='picLink'>Gallery</span><span class='picLinkFlash'>&nbsp;>&nbsp;</span>");
//            String idd = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._id).toString();
//            String title = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._pageTitle1).toString();
//            html3.append("<a class='picLink'>" + title + "</a>");
//
//            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=2"));
//            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
//            int counter2 = 0;
//            for (int i = 0; i < categoryRow.size(); i++) {
//                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._parent)).getRowCount() > 0) {
//                    if (!rowCategory.get(0).get(Category_Gallery._id).toString().equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
//                        counter2 += 1;
//                        html3.append((counter2 == 1 ? "<span class='picLinkFlash'> or </span>" : "<span class='picLinkFlash'>, </span>")
//                                + "<a  class='picLink' onclick='swGetGallery("
//                                + categoryRow.get(i).get(Category_Gallery._parent) + ");'>" + categoryRow.get(i).get(Category_Gallery._pageTitle1) + "</a>");
//                    }
//                }
//            }
//
//            html3.append("<span class='picLinkFlash'>)</span><br/><br/>");
//            if (counter2 > 0) {
//                html.append(html3.toString());
//            }
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id + " AND " + _lang + "=2"));
//
//            for (int i = 0; i < row.size(); i++) {
//                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
//                        + "title='" + Js.replacor(row.get(i).get(_pageTitle1).toString()).replace(" ", "&nbsp;")
//                        + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
//            }
//            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n"
//                    + "showGallery();\n";
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    public static String getGallery_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            StringBuffer html = new StringBuffer();
//            StringBuffer html3 = new StringBuffer();
//            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
//            String panel = jjTools.getParameter(request, "panel");
//            panel = panel == null ? "sw" : panel;
//            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._parent + "=" + id + " AND " + Category_Gallery._lang + "=3"));
//            html3.append("<span class='picLink'>"
//                    + "آلبوم"
//                    + "</span><span class='picLinkFlash'>&nbsp;>&nbsp;</span>");
//            String idd = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._id).toString();
//            String title = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._pageTitle1).toString();
//            html3.append("<a class='picLink'>" + title + "</a>");
//
//            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=3"));
//            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
//            int counter2 = 0;
//            for (int i = 0; i < categoryRow.size(); i++) {
//                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._parent)).getRowCount() > 0) {
//                    if (!idd.equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
//                        counter2 += 1;
//                        html3.append((counter2 == 1 ? "<span class='picLinkFlash'> أو </span>" : "<span class='picLinkFlash'>, </span>")
//                                + "<a  class='picLink' onclick='swGetGallery("
//                                + categoryRow.get(i).get(Category_Gallery._parent) + ");'>" + categoryRow.get(i).get(Category_Gallery._pageTitle1) + "</a>");
//                    }
//                }
//            }
//
//            html3.append("<span class='picLinkFlash'>)</span><br/><br/>");
//            if (counter2 > 0) {
//                html.append(html3.toString());
//            }
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id + " AND " + _lang + "=3"));
//
//            for (int i = 0; i < row.size(); i++) {
//                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
//                        + "title='" + Js.replacor(row.get(i).get(_pageTitle1).toString()).replace(" ", "&nbsp;")
//                        + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
//            }
//            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n"
//                    + "showGallery();\n";
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
}
