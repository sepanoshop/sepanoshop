package cms.cms;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

public class Portal {

    public static String tableName = "portal_post";
    public static String _id = "id";
    public static String _title = "portal_post_title";
    public static String _ownerId = "portal_post_ownerId";//forign key (id in portal table)
    public static String _ownerName = "portal_post_ownerName";
    public static String _detail = "portal_post_detail";
    public static String _date = "portal_post_date";
    public static String _priority = "portal_post_priority";
    public static String _val1 = "portal_post_val1";
    public static String _val2 = "portal_post_val2";
    public static String _val3 = "portal_post_val3";
    public static String _val4 = "portal_post_val4";
    public static String _val5 = "portal_post_val5";
    public static String _val6 = "portal_post_val6";
    public static String _val7 = "portal_post_val7";
    public static String _val8 = "portal_post_val8";
    public static String _val9 = "portal_post_val9";
    public static String _val10 = "portal_post_val10";
    public static String _prop1 = "portal_post_prop1";
    public static String _prop2 = "portal_post_prop2";
    public static String _prop3 = "portal_post_prop3";
    public static String _prop4 = "portal_post_prop4";
    public static String _prop5 = "portal_post_prop5";
    public static String _prop6 = "portal_post_prop6";
    public static String _prop7 = "portal_post_prop7";
    public static String _prop8 = "portal_post_prop8";
    public static String _prop9 = "portal_post_prop9";
    public static String _prop10 = "portal_post_prop10";
    public static String _pic1 = "portal_post_pic1";
    public static String _pic2 = "portal_post_pic2";
    public static String _pic3 = "portal_post_pic3";
    public static String _pic4 = "portal_post_pic4";
    public static String _pic5 = "portal_post_pic5";
    public static String _visit = "portal_post_visit";
    public static String _like = "portal_post_like";
    public static String _dislike = "portal_post_dislike";
    public static String _isActive = "portal_post_isActive";
    public static String _parent = "portal_post_parent";
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
            html.append("<table id='refreshPortalPost' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='15%'>عنوان</th>");
            html.append("<th width='10%'>پرتال</th>");
            html.append("<th width='60%'>تصاویر</th>");
            html.append("<th width='5%'>تایید</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            String picsStr;
            String pic1 = "";
            String pic2 = "";
            String pic3 = "";
            String pic4 = "";
            String pic5 = "";
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr onclick='cmsPortal.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'  >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_ownerName).toString()) + "</td>");

                // small pic in portalPost refresh table
                pic1 = row.get(i).get(_pic1).toString().replace(".", "_small.");
                pic2 = row.get(i).get(_pic2).toString().replace(".", "_small.");
                pic3 = row.get(i).get(_pic3).toString().replace(".", "_small.");
                pic4 = row.get(i).get(_pic4).toString().replace(".", "_small.");
                pic5 = row.get(i).get(_pic5).toString().replace(".", "_small.");
                picsStr = pic1.equalsIgnoreCase("") ? "" : "<img src='upload/" + pic1 + "'/>";
                picsStr += pic2.equalsIgnoreCase("") ? "" : "<img src='upload/" + pic2 + "'/>";
                picsStr += pic3.equalsIgnoreCase("") ? "" : "<img src='upload/" + pic3 + "'/>";
                picsStr += pic4.equalsIgnoreCase("") ? "" : "<img src='upload/" + pic4 + "'/>";
                picsStr += pic5.equalsIgnoreCase("") ? "" : "<img src='upload/" + pic5 + "'/>";

                html.append("<td class='imgRfsh c'>" + picsStr + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_isActive).toString()) + "</td>");
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
                panel = "swPicTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPortalPost", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "19" : "", "لیست تصاویر");
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
                html.append(Js.setHtml("#portalPost_button", "<input type=\"button\" id=\"insert_portalPost_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\" />"));
                html.append(Js.buttonMouseClick("#insert_portalPost_new", Js.jjPortal.insert()));
                int userId = jjTools.getSeassionUserId(request);
                html.append(Js.setVal("#portal_post_ownerId", userId));
                String userName = jjTools.getSessionAttribute(request, "#USER_NAME");
                String userFamily = jjTools.getSessionAttribute(request, "#USER_FAMILY");
                html.append(Js.setVal("#portal_post_ownerName", userName + " " + userFamily));
            } else {
                html.append("شما اجازه دسترسی به این بخش را ندارید");
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
            String title = jjTools.getParameter(request, _ownerId).toString();
            if (title.equals("")) {
                return Js.dialog("کد و عنوان پرتال را وارد کنید");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_detail, jjTools.getParameter(request, _detail));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);
            map.put(_ownerId, jjTools.getParameter(request, _ownerId));
            map.put(_ownerName, jjTools.getParameter(request, _ownerName));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_pic5, jjTools.getParameter(request, _pic5));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_like, jjTools.getParameter(request, _like));
            map.put(_dislike, jjTools.getParameter(request, _dislike));
            map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_isActive, jjTools.getParameter(request, _isActive));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPortal.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }

            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_detail, jjTools.getParameter(request, _detail));
            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);
            map.put(_ownerId, jjTools.getParameter(request, _ownerId));
            map.put(_ownerName, jjTools.getParameter(request, _ownerName));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_pic5, jjTools.getParameter(request, _pic5));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_like, jjTools.getParameter(request, _like));
            map.put(_dislike, jjTools.getParameter(request, _dislike));
            map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_isActive, jjTools.getParameter(request, _isActive));

//            map.put(_rating, jjTools.getParameter(request, _rating));
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPortal.refresh();
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
////                                + "!</heading><paragraph>" + slideRow.get(i).get(_title)
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
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic2).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic2).toString().replace(".", "_small."));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic3).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic3).toString().replace(".", "_small."));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic4).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic4).toString().replace(".", "_small."));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic5).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic5).toString().replace(".", "_small."));
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
            return Js.jjPortal.refresh();
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
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _ownerId, row.get(0).get(_ownerId)));
            html.append(Js.setVal("#" + _ownerName, row.get(0).get(_ownerName)));
            if (row.get(0).get(_pic1).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic1", "src", "upload/" + row.get(0).get(_pic1).toString()));
                html.append(Js.setVal("#portal_post_pic1", row.get(0).get(_pic1).toString()));
            }
            if (row.get(0).get(_pic2).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic2", "src", "upload/" + row.get(0).get(_pic2).toString()));
                html.append(Js.setVal("#portal_post_pic2", row.get(0).get(_pic2).toString()));
            }
            if (row.get(0).get(_pic3).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic3", "src", "upload/" + row.get(0).get(_pic3).toString()));
                html.append(Js.setVal("#portal_post_pic3", row.get(0).get(_pic3).toString()));
            }
            if (row.get(0).get(_pic4).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic4", "src", "upload/" + row.get(0).get(_pic4).toString()));
                html.append(Js.setVal("#portal_post_pic4", row.get(0).get(_pic4).toString()));
            }
            if (row.get(0).get(_pic5).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic5", "src", "upload/" + row.get(0).get(_pic5).toString()));
                html.append(Js.setVal("#portal_post_pic5", row.get(0).get(_pic5).toString()));
            }
            html.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            html.append(Js.setVal("#" + _val1, row.get(0).get(_val1)));
            html.append(Js.setVal("#" + _prop1, row.get(0).get(_prop1)));
            html.append(Js.setVal("#" + _val2, row.get(0).get(_val2)));
            html.append(Js.setVal("#" + _prop2, row.get(0).get(_prop2)));
            html.append(Js.setVal("#" + _val3, row.get(0).get(_val3)));
            html.append(Js.setVal("#" + _prop3, row.get(0).get(_prop3)));
            html.append(Js.setVal("#" + _val4, row.get(0).get(_val4)));
            html.append(Js.setVal("#" + _prop4, row.get(0).get(_prop4)));
            html.append(Js.setVal("#" + _val5, row.get(0).get(_val5)));
            html.append(Js.setVal("#" + _prop5, row.get(0).get(_prop5)));
            html.append(Js.setVal("#" + _detail, row.get(0).get(_detail)));
//            html.append(Js.setVal("#" + _val6, row.get(0).get(_val6)));
//            html.append(Js.setVal("#" + _val7, row.get(0).get(_val7)));
//            html.append(Js.setVal("#" + _val8, row.get(0).get(_val8)));
//            html.append(Js.setVal("#" + _val9, row.get(0).get(_val9)));
//            html.append(Js.setVal("#" + _val10, row.get(0).get(_val10)));
            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal("#" + _visit, visit));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _visit + "_checkbox", 0));
                html.append(Js.setAttr("#" + _visit, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _visit + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _visit, "disabled"));
            }
            Integer likes = Integer.valueOf(row.get(0).get(_like).toString());
            html.append(Js.setVal("#" + _like, likes));
            if (likes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _like + "_checkbox", 0));
                html.append(Js.setAttr("#" + _like, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _like + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _like, "disabled"));
            }
            Integer dislikes = Integer.valueOf(row.get(0).get(_dislike).toString());
            html.append(Js.setVal("#" + _dislike, dislikes));
            if (dislikes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal("#" + _dislike + "_checkbox", 0));
                html.append(Js.setAttr("#" + _dislike, "disabled", "true"));
            } else {
                html.append(Js.setVal("#" + _dislike + "_checkbox", 1));
                html.append(Js.removeAttr("#" + _dislike, "disabled"));
            }
            html.append(Js.setVal("#" + _isActive, row.get(0).get(_isActive)));
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type='button' id='edit_PortalPost' value='" + lbl_edit + "' class='tahoma10' />");
                html.append(Js.buttonMouseClick("#edit_PortalPost", Js.jjPortal.edit()));

            }
            if (accDel) {
                html2.append("<input type='button' id='delete_PortalPost' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_PortalPost", Js.jjPortal.delete(id)));
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
            return (Js.setHtml("#portalPost_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String get_val1List(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _val1));
            StringBuilder html = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_val1).toString() + "'"
                        + (row.get(i).get(_val1).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_val1).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String get_val2List(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _val2));
            StringBuilder html = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_val2).toString() + "'"
                        + (row.get(i).get(_val2).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_val2).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String get_val3List(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _val3));
            StringBuilder html = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_val3).toString() + "'"
                        + (row.get(i).get(_val3).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_val3).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String get_val4List(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String selected = jjTools.getParameter(request, "selected");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectDistinct(tableName, _val4));
            StringBuilder html = new StringBuilder();
            for (int i = row.size() - 1; i >= 0; i--) {
                html.append("<option value='" + row.get(i).get(_val4).toString() + "'"
                        + (row.get(i).get(_val4).toString().equals(selected) ? " selected='selected'>" : ">")
                        + row.get(i).get(_val4).toString() + "</option>");//'option' and 'value' for this fild is same('value' is not necessary)
            }
            return Js.setHtml("#" + panel, html.toString());
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
//            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
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
//            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
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
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
//            if (jjNumber.isDigit(text)) {
//                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
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
//                            + picRow.get(i).get(_title)
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
//                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._title));
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
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
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
//                            + picRow.get(i).get(_title)
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
//                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._title));
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

    public static String getTopPortalPost(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String maxNo = (jjTools.getParameter(request, "maxNo").equals("")) ? "10" : jjTools.getParameter(request, "maxNo");
            List<Map<String, Object>> topPosts = jjDatabase.separateRow(db.SelectDescLimit(tableName, _isActive + " = 1 ", _date, maxNo));
            for (int i = 0; i < 50 && i < topPosts.size(); i++) {//maximum 50 top post return
                if (Boolean.valueOf(topPosts.get(i).get(Portal._isActive).toString())) {
                    html.append("<div class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">"
                            + "<div class=\"thumbnail\">"
                            + "<a title = \"" + topPosts.get(i).get(_title) + "\" class=\"fancy fancybox\" href=\"upload/" + topPosts.get(i).get(_pic1).toString() + "\" data-fancybox-group=\"gallery\">"
                            + "<img src=\"upload/" + topPosts.get(i).get(_pic1).toString().replace(".", "_small.") + "\" alt=\"" + topPosts.get(i).get(_title) + "\">"
                            + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                            + "</a>"
                            + "<div class=\"caption\">"
                            + "<a href=\"#\">" + topPosts.get(i).get(_title) + "</a>"
                            + "<p><a class=\"btn btn-default button\" href=\"#\" onclick=\"swGetPortalPost(" + topPosts.get(i).get(_id).toString() + ");return false;\">"
                            + "بیشتر بخوانید "
                            + "</a></p>"
                            + "</div>"
                            + "</div>"
                            + "</div>");
                }
            }
//            for (int i = 0; i < 12; i++) {//maximum 50 top post return
//                html.append("<div class=\"col-md-2 col-sm-4 col-xs-6 col-p5\">\n" +
//"                                                                    <div class=\"thumbnail\">\n" +
//"                                                                        <a rel=\"group1\" class=\"fancy fancybox\" href=\"images/slide-3.jpg\" data-fancybox-group=\"gallery\"><img src=\"images_takrod/images/1page_img6.jpg\" alt=\"...\">\n" +
//"                                                                            <div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>\n" +
//"                                                                        </a>\n" +
//"                                                                        <div class=\"caption\">\n" +
//"                                                                            <a href=\"#\">توضیحات درباره خودروهای نمایگاه ها</a>\n" +
//"                                                                            <p><a role=\"button\" class=\"btn btn-default button\" href=\"#\">بیشتر بخوانید <i class=\"fa fa-file-text\"></i></a></p>\n" +
//"                                                                        </div>\n" +
//"                                                                    </div>\n" +
//"                                                                </div>");
//            }
            if (panel.equals("")) {
                panel = "sw";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getPortalPost(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            List<Map<String, Object>> post = jjDatabase.separateRow(db.Select(tableName, _id + " = " + id));
            List<Map<String, Object>> postOwner = jjDatabase.separateRow(db.Select(PortalUser.tableName, PortalUser._id + " = " + post.get(0).get(_ownerId)));
            if (!post.isEmpty()) {
                if (Boolean.valueOf(postOwner.get(0).get(PortalUser._blocked).toString())) {
                    html.append("<div class=\"container\">"
                            + "<div class=\"panel-two pnl-contact\">"
                            + "<div class=\"panel panel-default mypnl\">"
                            + "<div class=\"panel-body no-p-btm panel-rental\" >"
                            + "<p class=\"text-p\">"
                            + "<br/>"
                            + "<br/>"
                            + "<br/>"
                            + "کلیه پست های این کاربر بلوکه شده است. امکان مشاده پستهای این کاربر تا اطلاع ثانوی وجود ندارد"
                            + "<br/>"
                            + "<br/>"
                            + "<br/>"
                            + "<br/>"
                            + "<p/>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "");
                    return Js.setHtml("#" + panel, html);
                }
                if (!Boolean.valueOf(post.get(0).get(Portal._isActive).toString())) {
                    html.append("<div class=\"container\">"
                            + "<div class=\"panel-two pnl-contact\">"
                            + "<div class=\"panel panel-default mypnl\">"
                            + "<div class=\"panel-body no-p-btm panel-rental\" >"
                            + "<p class=\"text-p\">"
                            + "<br/>"
                            + "<br/>"
                            + "<br/>"
                            + "این پست هنوز به تایید مدیر سایت نرسیده است"
                            + "<br/>"
                            + "<br/>"
                            + "<br/>"
                            + "<br/>"
                            + "<p/>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "");
                    return Js.setHtml("#" + panel, html);
                }
                boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
                html.append("<div class=\"container\">"
                        + "<div class=\"panel-two pnl-contact\">"
                        + "<div class=\"panel panel-default mypnl\">"
                        + "<br/>"
                        + "<div class=\"panel-heading myhead\">"
                        + "<h3 class=\"panel-title mytitle\"><i class=\"fa fa-truck\"></i> " + post.get(0).get(_title) + " </h3>"
                        + "</div>"
                        + "<div class=\"panel-body no-p-btm panel-rental\" >"
                        + "<div class=\"block\">"
                        + "<div >"
                        + "تاریخ انتشار :"
                        + jjCalendar_IR.getViewFormat(post.get(0).get(_date).toString())
                        + "<a class=\"block\" href=\"" + "upload/" + post.get(0).get(_pic1).toString() + "\" onclick=\"return false;\">"
                        + "<img alt=\"" + post.get(0).get(_title).toString() + "\" src=\"" + "upload/" + post.get(0).get(_pic1).toString() + "\">"
                        + "</a>");
                int visits = new Integer(post.get(0).get(_visit).toString());
                if (visits > 0) {
                    html.append(
                            " تعداد بازدید از این صفحه : "
                            + visits);
                }
                html.append(
                        "</div>"
                        + "</div>"
                        + "<p class=\"text-p\">"
                        + "<br/><h5>"
                        + post.get(0).get(_detail).toString()
                        + "</h5></p>"
                        + "<br>"
                        + "<h4 class=\"\"><i class=\"fa fa-truck\"></i> سایر تصاویر :</h4>"
                        + "</br>"
                        + "<div class=\"row row-m5\">");
                String postImgSrc;
                for (int i = 2; i <= 5; i++) { // pic2 tp pic5
                    if (!post.get(0).get(_pic1.replace("1", String.valueOf(i))).toString().equals("")) {
                        postImgSrc = post.get(0).get(_pic1.replace("1", String.valueOf(i))).toString();
                        html.append(
                                "<div class=\"col-md-3 col-sm-3 col-xs-6 col-p5\">"
                                + "<div class=\"thumbnail\">"
                                + "<a title=\"" + post.get(0).get(_title) + "\" data-fancybox-group=\"gallery\" href=\""
                                + "upload/" + postImgSrc
                                + "\" class=\"fancy fancybox\" rel=\"group2\" eq=\"0\" number=\"6\">"
                                + "<img alt=\"" + post.get(0).get(_title) + "\" src=\"upload/" + postImgSrc.replace(".", "_small.") + "\">"
                                + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                                + "</a>"
                                + "</div>"
                                + "</div>");
                    }
                }
                html.append("</div>"
                        + "<h4 class=\"m-btm20\"><i class=\"fa fa-truck\"></i> مشخصات  :</h4>"
                        + "<div class=\"row row-m5\">"
                        + "<div class=\"col-md-12 col-p5\">"
                        + "<form class=\"form-horizontal\">"
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop1)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\"  placeholder=\"عنوان متن\">"
                        + post.get(0).get(_val1)
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop2)+"</span> </label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\"  placeholder=\"عنوان متن\">"
                        + post.get(0).get(_val2)
                        + "</div>"
                        + "</div>"
                        + "</div> "
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop3)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\"  placeholder=\"عنوان متن\">"
                        + post.get(0).get(_val3)
                        + "</div>"
                        + "</div>"
                        + "</div> "
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop4)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\" placeholder=\"عنوان متن\">"
                        + post.get(0).get(_val4)
                        + "</div>"
                        + "</div>"
                        + "</div> "
                        + "<div class=\"form-group\">"
                        + "<label  class=\"col-sm-2\"><span>"+post.get(0).get(_prop5)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\" placeholder=\"عنوان متن\">"
                        + post.get(0).get(_val5)
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "</form>"
                        + "</div>"
                        + "</div>"
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._pageTitle1)
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._val1)
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._val2)
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._val3)
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._val4)
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._val5)
                        + " / "
                        + postOwner.get(0).get(PortalUser._val6)
                        + "<br/>"
                        + postOwner.get(0).get(PortalUser._address)
                        + "<br/>"
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }
            if (panel.equals("")) {
                panel = "sw";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

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
//                        + allPic.get(i).get(_title) + "'"
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
//                    + categoryRow.get(selectedI).get(Category_Gallery._id) + ");' >" + categoryRow.get(selectedI).get(Category_Gallery._title) + "</a></li>");
//
////            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
//
//            for (int i = 0; i < categoryRow.size(); i++) {
//                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._id)).getRowCount() > 0) {
//                    if (!categoryRow.get(selectedI).get(Category_Gallery._id).toString().equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
//
//                        html3.append("<li class='picLinkFlash'>" + "<a  class='picLink' onclick='swGetGallery("
//                                + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a></li>");
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
////                        + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
////                        + "' src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
////                        + "_small." + row.get(i).get(_url_ex)
////                        + "' class='picInGallery mousePointer' onclick='picDialog(this);' />");
////                html.append("</td></tr><tr><td style='text-align:center'>");
////                html.append(row.get(i).get(_title).toString());
////                html.append("</td></tr></table>");
//                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
//                        + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
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
//            String title = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._title).toString();
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
//                                + categoryRow.get(i).get(Category_Gallery._parent) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a>");
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
//                        + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
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
//            String title = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._title).toString();
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
//                                + categoryRow.get(i).get(Category_Gallery._parent) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a>");
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
//                        + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
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
    /**
     * اضافه کردن دکمه یک پست جدید توسط کاربر پرتال از طریق صفحه شخصی کاربر
     */
    public static String add_new_InPortal(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            int userId = jjTools.getSeassionUserId(request);
            List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(PortalUser.tableName, PortalUser._UserId + " = " + userId + " AND " + PortalUser._isActive + "=1" + " AND " + PortalUser._blocked + "=0"));
            if (!user.isEmpty()) {
                html.append(Js.setHtml("#portalPost_button", "<input type=\"button\" id=\"insert_portalPost_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\" />"));
                html.append(Js.buttonMouseClick("#insert_portalPost_new", Js.jjPortal.insertPostByUserPortal()));
                html.append(Js.setVal("#portal_post_ownerId", userId));
                String userName = jjTools.getSessionAttribute(request, "#USER_NAME");
                String userFamily = jjTools.getSessionAttribute(request, "#USER_FAMILY");
                html.append(Js.setVal("#portal_post_ownerName", userName + " " + userFamily));
            } else {
                html.append(Js.setHtml("#portalPost_button", ""));
                html.append(Js.dialog(" اجازه دسترسی شما محدود شده است "));

            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insertByPortalUser(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            int userId = jjTools.getSeassionUserId(request);
            List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(PortalUser.tableName, PortalUser._UserId + " = " + userId + " AND " + PortalUser._isActive + "=1" + " AND " + PortalUser._blocked + "=0"));
            if (!user.isEmpty()) {

                String title = jjTools.getParameter(request, _title).toString();
                if (title.equals("")) {
                    return "کد و عنوان مصلب جدید را وارد کنید";
                }
                if (jjTools.getParameter(request, _pic1).equals("")) {
                    return "تصویر اصلی را وارد کنید";
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(_title, title);
                map.put(_detail, jjTools.getParameter(request, _detail));
                map.put(_date, new jjCalendar_IR().getDBFormat_8length());
//            String priority = jjTools.getParameter(request, _priority);
//            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);
                map.put(_ownerId, user.get(0).get(PortalUser._id));// _ownerId is id in 'portal_user' in db.
                map.put(_ownerName, user.get(0).get(PortalUser._firstName) + " " + user.get(0).get(PortalUser._lastName));
                map.put(_pic1, jjTools.getParameter(request, _pic1));
                map.put(_pic2, jjTools.getParameter(request, _pic2));
                map.put(_pic3, jjTools.getParameter(request, _pic3));
                map.put(_pic4, jjTools.getParameter(request, _pic4));
                map.put(_pic5, jjTools.getParameter(request, _pic5));
                map.put(_val1, jjTools.getParameter(request, _val1));
                map.put(_val2, jjTools.getParameter(request, _val2));
                map.put(_val3, jjTools.getParameter(request, _val3));
                map.put(_val4, jjTools.getParameter(request, _val4));
                map.put(_val5, jjTools.getParameter(request, _val5));
//            map.put(_like, jjTools.getParameter(request, _like));
//            map.put(_dislike, jjTools.getParameter(request, _dislike));
//            map.put(_visit, jjTools.getParameter(request, _visit));
//            map.put(_isActive, jjTools.getParameter(request, _isActive));
                if (db.insert(tableName, map).getRowCount() == 0) {
                    String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                    if (jjTools.getParameter(request, "myLang").equals("en")) {
                        errorMessage = "Edit Fail;";
                    }
                    return Js.dialog(errorMessage);
                }
                return Js.jjPortal.portalFormClose() + Js.dialog("مطلب شما بعد از تایید مدیر سایت قابل مشاهده برای عموم خواهد بود", title);
            } else {
                return "شما اجازه ثبت ندارید";
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String selectPostInPublic(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            int userId = jjTools.getSeassionUserId(request);
            List<Map<String, Object>> user = jjDatabase.separateRow(db.Select(PortalUser.tableName, PortalUser._UserId + " = " + userId + " AND " + PortalUser._isActive + "=1" + " AND " + PortalUser._blocked + "=0"));
            if (!user.isEmpty()) {
                html.append(Js.jjPortal.portalPostLoadForm());

                return html.toString();
            }
            List<Map<String, Object>> post = jjDatabaseWeb.separateRow(db.Select(Portal.tableName, Portal._id + " = " + jjTools.getParameter(request, _id).toString()));
            for (int i = 0; i < post.size(); i++) {
                html.append(
                        "<div class=\"row show-grid\">"
                        + "<div class=\"col-md-6 col-sm-6 text-center\">"
                        + "<a data-fancybox-group=\"gallery\" href=\"upload/"
                        + post.get(i).get(Portal._pic1)
                        + "\" class=\"fancy fancybox\" rel=\"group2\" eq=\"0\" number=\"1\">"
                        + "<img alt=\"...\" src=\"upload/"
                        + post.get(i).get(Portal._pic1).toString().replace(".", "_small.")
                        + "\">"
                        + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                        + "</a>"
                        + "</div>"
                        + " "
                        + "");

                html.append(
                        "<div class=\"col-md-12 col-sm-12\">");
                String postImgSrc;
                for (int j = 2; j <= 5; j++) { // pic2 tp pic5
                    if (!post.get(i).get(Portal._pic1.replace("1", String.valueOf(j))).toString().equals("")) {
                        postImgSrc = post.get(i).get(Portal._pic1.replace("1", String.valueOf(j))).toString();
                        html.append(
                                "<div class=\"col-md-2 col-sm-2 text-center block\">"
                                + "<a data-fancybox-group=\"gallery\" href=\"upload/"
                                + postImgSrc
                                + "\" class=\"fancy fancybox\" rel=\"group2\" eq=\"0\" number=\"1\">"
                                + "<img alt=\"...\" src=\"upload/"
                                + postImgSrc.replace(".", "_small.")
                                + "\">"
                                + "<div><span class=\"span\"><span class=\"glyphicon glyphicon-search\"></span></span></div>"
                                + "</a>"
                                + "</div>");
                    }
                }
                html.append(
                        "<div class=\"thumbnail thumb-new \" >"
                        + "<div class=\"caption no-pad\">"
                        + "<h3 class=\"col-md-12 col-sm-12 block row\"><i class=\"fa fa-truck\"></i>"
                        + post.get(i).get(Portal._title)
                        + "</h3><p></p><br/> "
                        + "<div id=\"postDetails" + post.get(i).get(Portal._id) + "\" class=\"hideDisplay\">"
                        + "<p class=\"col-md-12 col-sm-12 block row text-p\">"
                        + post.get(i).get(Portal._detail)
                        + "</p>"
                        + "<form class=\"form-horizontal\">"
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop1)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\"  placeholder=\"عنوان متن\">"
                        + post.get(0).get(Portal._val1)
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop2)+"</span> </label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\"  placeholder=\"عنوان متن\">"
                        + post.get(0).get(Portal._val2)
                        + "</div>"
                        + "</div>"
                        + "</div> "
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop3)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\"  placeholder=\"عنوان متن\">"
                        + post.get(0).get(Portal._val3)
                        + "</div>"
                        + "</div>"
                        + "</div> "
                        + "<div class=\"form-group\">"
                        + "<label class=\"col-sm-2\"><span>"+post.get(0).get(_prop4)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\" placeholder=\"عنوان متن\">"
                        + post.get(0).get(Portal._val4)
                        + "</div>"
                        + "</div>"
                        + "</div> "
                        + "<div class=\"form-group\">"
                        + "<label  class=\"col-sm-2\"><span>"+post.get(0).get(_prop5)+"</span></label>"
                        + "<div class=\"col-sm-5\">"
                        + "<div class=\"form-control\" placeholder=\"عنوان متن\">"
                        + post.get(0).get(Portal._val5)
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "</form>"
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "<p>"
                        + "<a class=\"btn btn-default button float-shadow\"  onclick=\"$(\'#postDetails" + post.get(i).get(Portal._id) + "\').toggleClass(\'hideDisplay\');\" >"
                        + "بیشتر بخوانید "
                        + "<i class=\"fa fa-file-text\"></i></a></p>"
                        + "</div>"
                        + "</div>");
            }
            return Js.setHtml("#sw", html.toString());
        } catch (Exception ex) {
            System.out.println(ex);
            return Server.ErrorHandler(ex);
        }

    }

    public static String loadPortalPostforEdit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String portalId = request.getSession().getAttribute("#" + Portal._ownerId.toUpperCase()).toString();//portalId is set in Access_user.afterUserPortalLoginOrRegist();
            String panel = request.getParameter("panel");
            List<Map<String, Object>> AllPostOfThisUser = jjDatabaseWeb.separateRow(db.SelectDesc(
                    Portal.tableName,
                    Portal._ownerId + " = " + portalId,
                    Portal._date));
            for (int i = 0; i < AllPostOfThisUser.size(); i++) {
                html.append(
                        "<div class=\"col-md-3\">"
                        + "<a class=\"ads clearfix\" onclick=\"selectByPortalUser(" + AllPostOfThisUser.get(i).get(Portal._id) + "); return false;\">"
                        + "<h4>"
                        + AllPostOfThisUser.get(i).get(Portal._title)
                        + "</h4>"
                        + "<img alt=\""
                        + AllPostOfThisUser.get(i).get(Portal._title)
                        + "\" src=\"upload/"
                        + AllPostOfThisUser.get(i).get(Portal._pic1).toString().replace(".", "_small.")
                        + "\">"
                        + "</a>"
                        + " <div class='btn-default' onclick='selectPostforEdit(" + AllPostOfThisUser.get(i).get(Portal._id) + ");'>"
                        + "<i class='fa fa-star '></i>"
                        + "ویرایش این پست "
                        + "</div>"
                        + "<br/>"
                        + "</div>"
                        + "");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String selectByPortalUser(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
//            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
//            html.append(Js.setVal("#" + _ownerId, row.get(0).get(_ownerId)));
//            html.append(Js.setVal("#" + _ownerName, row.get(0).get(_ownerName)));
            if (row.get(0).get(_pic1).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic1", "src", "upload/" + row.get(0).get(_pic1).toString()));
                html.append(Js.setVal("#portal_post_pic1", row.get(0).get(_pic1).toString()));
            }
            if (row.get(0).get(_pic2).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic2", "src", "upload/" + row.get(0).get(_pic2).toString()));
                html.append(Js.setVal("#portal_post_pic2", row.get(0).get(_pic2).toString()));
            }
            if (row.get(0).get(_pic3).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic3", "src", "upload/" + row.get(0).get(_pic3).toString()));
                html.append(Js.setVal("#portal_post_pic3", row.get(0).get(_pic3).toString()));
            }
            if (row.get(0).get(_pic4).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic4", "src", "upload/" + row.get(0).get(_pic4).toString()));
                html.append(Js.setVal("#portal_post_pic4", row.get(0).get(_pic4).toString()));
            }
            if (row.get(0).get(_pic5).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic5", "src", "upload/" + row.get(0).get(_pic5).toString()));
                html.append(Js.setVal("#portal_post_pic5", row.get(0).get(_pic5).toString()));
            }
//            html.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            html.append(Js.setVal("#" + _val1, row.get(0).get(_val1)));
            html.append(Js.setVal("#" + _val2, row.get(0).get(_val2)));
            html.append(Js.setVal("#" + _val3, row.get(0).get(_val3)));
            html.append(Js.setVal("#" + _val4, row.get(0).get(_val4)));
            html.append(Js.setVal("#" + _val5, row.get(0).get(_val5)));
            html.append(Js.setVal("#" + _detail, row.get(0).get(_detail)));
//            html.append(Js.setVal("#" + _val6, row.get(0).get(_val6)));
//            html.append(Js.setVal("#" + _val7, row.get(0).get(_val7)));
//            html.append(Js.setVal("#" + _val8, row.get(0).get(_val8)));
//            html.append(Js.setVal("#" + _val9, row.get(0).get(_val9)));
//            html.append(Js.setVal("#" + _val10, row.get(0).get(_val10)));
//            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
//            html.append(Js.setVal("#" + _visit, visit));
//            if (visit < 0) {//That means it is disabled now and it has been enabled
//                html.append(Js.setVal("#" + _visit + "_checkbox", 0));
//                html.append(Js.setAttr("#" + _visit, "disabled", "true"));
//            } else {
//                html.append(Js.setVal("#" + _visit + "_checkbox", 1));
//                html.append(Js.removeAttr("#" + _visit, "disabled"));
//            }
//            Integer likes = Integer.valueOf(row.get(0).get(_like).toString());
//            html.append(Js.setVal("#" + _like, likes));
//            if (likes < 0) {//That means it is disabled now and it has been enabled
//                html.append(Js.setVal("#" + _like + "_checkbox", 0));
//                html.append(Js.setAttr("#" + _like, "disabled", "true"));
//            } else {
//                html.append(Js.setVal("#" + _like + "_checkbox", 1));
//                html.append(Js.removeAttr("#" + _like, "disabled"));
//            }
//            Integer dislikes = Integer.valueOf(row.get(0).get(_dislike).toString());
//            html.append(Js.setVal("#" + _dislike, dislikes));
//            if (dislikes < 0) {//That means it is disabled now and it has been enabled
//                html.append(Js.setVal("#" + _dislike + "_checkbox", 0));
//                html.append(Js.setAttr("#" + _dislike, "disabled", "true"));
//            } else {
//                html.append(Js.setVal("#" + _dislike + "_checkbox", 1));
//                html.append(Js.removeAttr("#" + _dislike, "disabled"));
//            }
//            html.append(Js.setVal("#" + _isActive, row.get(0).get(_isActive)));
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (!accEdt) {
                html2.append("<input type='button' id='edit_PortalPost' value='" + lbl_edit + "' class='tahoma10' />");
                html.append(Js.buttonMouseClick("#edit_PortalPost", Js.jjPortal.edit()));

            }
            if (!accDel) {
                html2.append("<input type='button' id='delete_PortalPost' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_PortalPost", Js.jjPortal.delete(id)));
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
            return (Js.setHtml("#portalPost_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * select post and set form for edit by portal owner(portalUser)
     *
     * @param request
     * @param db
     */
    public static String selectPostforEdit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id
                    + " AND " + _ownerId + " = " + request.getSession().getAttribute("#" + _ownerId.toUpperCase())));

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
//            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));
            html.append(Js.setVal("#" + _ownerId, row.get(0).get(_ownerId)));
            html.append(Js.setVal("#" + _ownerName, row.get(0).get(_ownerName)));
            if (row.get(0).get(_pic1).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic1", "src", "upload/" + row.get(0).get(_pic1).toString()));
                html.append(Js.setVal("#portal_post_pic1", row.get(0).get(_pic1).toString()));
            }
            if (row.get(0).get(_pic2).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic2", "src", "upload/" + row.get(0).get(_pic2).toString()));
                html.append(Js.setVal("#portal_post_pic2", row.get(0).get(_pic2).toString()));
            }
            if (row.get(0).get(_pic3).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic3", "src", "upload/" + row.get(0).get(_pic3).toString()));
                html.append(Js.setVal("#portal_post_pic3", row.get(0).get(_pic3).toString()));
            }
            if (row.get(0).get(_pic4).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic4", "src", "upload/" + row.get(0).get(_pic4).toString()));
                html.append(Js.setVal("#portal_post_pic4", row.get(0).get(_pic4).toString()));
            }
            if (row.get(0).get(_pic5).toString().matches("^p[0-9]{10}[.]((jpg)|(png)|(PNG)|(JPG))$")) {
                html.append(Js.setAttr("#preview_portalPost_pic5", "src", "upload/" + row.get(0).get(_pic5).toString()));
                html.append(Js.setVal("#portal_post_pic5", row.get(0).get(_pic5).toString()));
            }
//            html.append(Js.setVal("#" + _priority, row.get(0).get(_priority)));
            html.append(Js.setVal("#" + _val1, row.get(0).get(_val1)));
            html.append(Js.setVal("#" + _val2, row.get(0).get(_val2)));
            html.append(Js.setVal("#" + _val3, row.get(0).get(_val3)));
            html.append(Js.setVal("#" + _val4, row.get(0).get(_val4)));
            html.append(Js.setVal("#" + _val5, row.get(0).get(_val5)));
            html.append(Js.setVal("#" + _detail, row.get(0).get(_detail)));
//            html.append(Js.setVal("#" + _val6, row.get(0).get(_val6)));
//            html.append(Js.setVal("#" + _val7, row.get(0).get(_val7)));
//            html.append(Js.setVal("#" + _val8, row.get(0).get(_val8)));
//            html.append(Js.setVal("#" + _val9, row.get(0).get(_val9)));
//            html.append(Js.setVal("#" + _val10, row.get(0).get(_val10)));
            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal("#" + _visit, visit));

//            html.append(Js.setVal("#" + _isActive, row.get(0).get(_isActive)));
            boolean accDel = true;
            boolean accEdt = true;

            if (accDel) {
                html2.append("<input type='button' id='edit_PortalPost' value='" + lbl_edit + "' class='tahoma10' />");
                html.append(Js.buttonMouseClick("#edit_PortalPost", "editPostByUserPortal(" + id + ");"));

            }
            if (accEdt) {
                html2.append("<input type='button' id='delete_PortalPost' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_PortalPost", "deleteByPortalUser(" + id + ");"));
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
            return (Js.setHtml("#portalPost_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * select post and set form for edit by portal owner(portalUser)
     *
     * @param request
     * @param db
     */
    public static String editPostByUserPortal(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            String ownerId = request.getSession().getAttribute("#" + _ownerId.toUpperCase()).toString();
            List<Map<String, Object>> owner = jjDatabase.separateRow(db.Select(PortalUser.tableName, PortalUser._id + "=" + ownerId + " AND " + PortalUser._blocked + "=0 ;"));
            if (owner.isEmpty()) {
                return "شما اجازه ویرایش ندارید";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_detail, jjTools.getParameter(request, _detail));
//            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
//            String priority = jjTools.getParameter(request, _priority);
//            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);
//            map.put(_ownerId, jjTools.getParameter(request, _ownerId));
//            map.put(_ownerName, jjTools.getParameter(request, _ownerName));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_pic5, jjTools.getParameter(request, _pic5));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
//          map.put(_like, jjTools.getParameter(request, _like));
//          map.put(_dislike, jjTools.getParameter(request, _dislike));
//          map.put(_visit, jjTools.getParameter(request, _visit));
            map.put(_isActive, "0");// ممکن است بعد از ویرایش به تایید مدیر نیاز باشد
//          map.put(_rating, jjTools.getParameter(request, _rating));
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.dialog("edit complete", "cleanFormAfterEdit(" + request.getSession().getAttribute("#" + _ownerId.toUpperCase()).toString() + ");", _title, "400", "300");
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * select post and set form for edit by portal owner(portalUser)
     *
     * @param request
     * @param db
     */
    public static String deleteByPortalUser(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String ownerId = request.getSession().getAttribute("#" + _ownerId.toUpperCase()).toString();
            List<Map<String, Object>> owner = jjDatabase.separateRow(db.Select(PortalUser.tableName, PortalUser._id + "=" + ownerId + " AND " + PortalUser._blocked + "=0 ;"));
            if (owner.isEmpty()) {
                return "شما اجازه حذف ندارید";
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
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic2).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic2).toString().replace(".", "_small."));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic3).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic3).toString().replace(".", "_small."));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic4).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic4).toString().replace(".", "_small."));
                if (picSmall.exists()) {
                    picSmall.delete();
                }
                pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic5).toString());
                if (pic.exists()) {
                    pic.delete();
                }
                picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_pic5).toString().replace(".", "_small."));
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
            return "portalFormClose();";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
