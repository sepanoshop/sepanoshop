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

public class Pic {

    public static String tableName = "pic";
    public static String _id = "id";
    public static String _category_id = "gallery_id";
    public static String _title = "pic_title";
    public static String _url_name = "pic_pic_name";
    public static String _url_ex = "pic_pic_ex";
    public static String _parent = "pic_parent";
    public static String _lang = "pic_lang";
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
    public static int rul_rfs = 42;
    public static int rul_ins = 43;
    public static int rul_edt = 44;
    public static int rul_dlt = 45;
    public static int rul_lng = 46;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            DefaultTableModel dtm = db.Select(tableName, _lang + "=1");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table id='refreshPic' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='25%'>عنوان</th>");
            html.append("<th width='20%'>نام فایل</th>");
            html.append("<th width='10%'>حجم</th>");
            html.append("<th width='20%'>تصویر کوچک</th>");
            html.append("<th width='5%'>دسته</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            String address = "upload/";
//        String address = "file:///" + request.getServletContext().getRealPath("\\upload") + "\\";
//        address = address.replace("\\", "/");
            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsPic.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'  >");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_title).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_url_name).toString() + "." + row.get(i).get(_url_ex).toString()) + "</td>");
                html.append("<td class='l'>");
                if (row.get(i).get(_url_name).toString().equals("")) {
//                    html.append("");
                } else {
                    File f = new File(folderAddressUpload.getAbsolutePath() + "/" + (row.get(i).get(_url_name).toString().replace("%20", " ") + "." + row.get(i).get(_url_ex).toString()));
                    if (f.exists()) {
                        html.append((f.length() / 1024) + "kb");
                    } else {
//                        html.append("موجود نمی باشد");
                    }
                }
                html.append("</td>");
                html.append("<td class='c'>" + "<img style='max-height:40px' src='" + address + (row.get(i).get(_url_name).toString() + "_small." + row.get(i).get(_url_ex).toString()) + "' />" + "</td>");
                html.append("<td class='l'>/" + (row.get(i).get(_category_id).toString()) + "/</td>");
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
                panel = "swPicTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPic", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "12" : "", "لیست تصاویر");
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
                html.append(Js.setHtml("#Pic_button", "<input type=\"button\" id=\"insert_Pic_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Pic_new", Js.jjPic.insert()));
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

            map.put(_category_id, jjNumber.isDigit(jjTools.getParameter(request, _category_id)) ? Integer.parseInt(jjTools.getParameter(request, _category_id)) : 0);
            map.put(_title, jjTools.getParameter(request, _title));
            String picName = jjTools.getParameter(request, _url_name);
            String extension = "";
            int dot = picName.lastIndexOf(".");
            if (dot > -1) {
                map.put(_url_name, picName.substring(0, dot));
                extension = picName.substring(dot + 1, picName.length());
                map.put(_url_ex, extension);
            }
            if (extension.toLowerCase().equals("jpg") || extension.toLowerCase().equals("png") || extension.toLowerCase().equals("gif")) {
            } else {
                return Js.dialog("نوع فایل تصویر باید jpg، png و یا gif باشد.");
            }
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);


            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            WritXmlConfigSliderFlash(request, db, isPost);
            return Js.jjPic.refresh();
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
            List<Map<String, Object>> lastRow = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));

            Map<String, Object> map = new HashMap<String, Object>();
            String statement2 = "";
            map.put(_category_id, jjNumber.isDigit(jjTools.getParameter(request, _category_id)) ? Integer.parseInt(jjTools.getParameter(request, _category_id)) : 0);
            map.put(_title, jjTools.getParameter(request, _title));
            String picName = jjTools.getParameter(request, _url_name);
            int dot = picName.lastIndexOf(".");
            if (dot > -1) {
                map.put(_url_name, picName.substring(0, dot));
                map.put(_url_ex, picName.substring(dot + 1, picName.length()));
            }
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            if (lastRow.size() > 0) {
                String last = lastRow.get(0).get(_url_name) + "." + lastRow.get(0).get(_url_ex);
                String newPic = map.get(_url_name) + "." + map.get(_url_ex);
                if (!last.equals(newPic)) {
                    File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
                    File pic = new File(folderAddress.getAbsolutePath() + "/" + lastRow.get(0).get(_url_name).toString().replace("%20", " ") + "." + lastRow.get(0).get(_url_ex));
                    if (pic.exists()) {
                        pic.delete();
                    }
                    File picSmall = new File(folderAddress.getAbsolutePath() + "/" + lastRow.get(0).get(_url_name).toString().replace("%20", " ") + "_small." + lastRow.get(0).get(_url_ex));
                    if (picSmall.exists()) {
                        picSmall.delete();
                    }
                }
            }

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            WritXmlConfigSliderFlash(request, db, isPost);
            return Js.jjPic.refresh() + statement2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String changeAllPrice(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String int1 = jjTools.getParameter(request, "int1");
            String int2 = jjTools.getParameter(request, "int2");

            if (jjNumber.isDigit(int1)) {
                if (jjNumber.isDigit(int2)) {
                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put(_price, Integer.parseInt(int2));
//                    db.update(tableName, map, _price + "=" + int1);
                }

            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String WritXmlConfigSliderFlash(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        ServerLog.Print("WritXmlConfigSliderFlash");
        if (jjTools.getParameter(request, _category_id).equals("1")) {
            String address = request.getServletContext().getRealPath("/js/prettyPhoto");
            String address2 = request.getServletContext().getRealPath("/");
            File file = new File(address + "/sample.xml");
            File file_fa = new File(address2 + "/config.xml");
            File file_en = new File(address2 + "/config_en.xml");
            File file_ar = new File(address2 + "/config_ar.xml");
            String text = "";
            StringBuffer slide = new StringBuffer();
            StringBuffer slide_en = new StringBuffer();
            StringBuffer slide_ar = new StringBuffer();
            if (file.exists()) {
                text = jjFileTxt.read(file);
                int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
                List<Map<String, Object>> slideRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1"));
                File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));

//                BRIEF TRANSITION GUIDE
//		direction: left, right, up, down
//		slicing: horizontal, vertical
//		num - number of slices each transition consists of
//		shader - transition shading type - none, flat, phong
//		cube_color - define cube color during transition (hex value)
//		duration - time for each sliced cube transition
//		delay – time each sliced cube will wait before starting transition.
//		z_multiplier - z offset enables jo-jo effect of the cubes on z axis during transition. Higher Z numbers create a pull back effect.
                String direction[] = {"left", "right", "up", "down"};
                String shader[] = {"none", "flat", "phong"};
                int counter = 0;
                int directionCounter = -1;
                for (int i = 0; i < slideRow.size(); i++) {
                    File f = new File(folderAddressUpload.getAbsolutePath()
                            + "/" + (slideRow.get(i).get(_url_name).toString().replace("%20", " ") + "."
                            + slideRow.get(i).get(_url_ex).toString()));
                    if (f.exists()) {
                        counter += 1;
                        directionCounter += 1;
                        String s = ("<slide>\n<url>upload/" + f.getName()
                                + "</url>\n<link target='_self'>"
                                + "</link>\n</slide>\n"
                                + "<transition num='3' slicing='" + (counter % 2 == 0 ? "vertical" : "horizontal")
                                + "' direction='" + (direction[directionCounter])
                                + "' shader='" + (counter % 2 == 0 ? "flat" : "phong") + "' delay='0.08' z_multiplier='1' />\n");
                        if (slideRow.get(i).get(_lang).toString().equals("1")) {
                            slide.append(s);
                        } else if (slideRow.get(i).get(_lang).toString().equals("2")) {
                            slide_en.append(s);
                        } else {
                            slide_ar.append(s);
                        }
                        directionCounter = directionCounter > 2 ? -1 : directionCounter;
//                        slide += "\n"
//                                + "        <slide><url>upload/" + f.getName()
//                                + "</url><link target='_self'>your_page_link.htm</link><description>\n"
//                                + "                <heading>" + Server.siteName
//                                + "!</heading><paragraph>" + slideRow.get(i).get(_title)
//                                + "</paragraph></description></slide>\n"
//                                + "        <transition num='" + slideRow.size() + "' slicing='vertical' direction='up' shader='flat' delay='0.08' z_multiplier='1' />";
                    }
                }

            }
            jjFileTxt.write(file_fa, text.replace("jjSlide", slide.toString()));
            jjFileTxt.write(file_en, text.replace("jjSlide", slide_en.toString()));
            jjFileTxt.write(file_ar, text.replace("jjSlide", slide_ar.toString()));
        }
        return "";
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
                File pic = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_url_name).toString().replace("%20", " ") + "." + row.get(0).get(_url_ex));
                if (pic.exists()) {
                    pic.delete();
                }
                File picSmall = new File(folderAddress.getAbsolutePath() + "/" + row.get(0).get(_url_name).toString().replace("%20", " ") + "_small." + row.get(0).get(_url_ex));
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
            db.delete(tableName, _parent + "=" + id);
            WritXmlConfigSliderFlash(request, db, isPost);
            return Js.jjPic.refresh();
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
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
//            html.append(Js.setVal("#" + _price, row.get(0).get(_price)));
//            html.append(Js.setVal("#" + _margin, row.get(0).get(_margin)));
//            html.append(Js.setVal("#" + _comment, row.get(0).get(_comment)));
            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type='button' id='edit_Pic' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_Pic", Js.jjPic.edit()));
            }
            if (accDel) {
                html2.append("<input type='button' id='delete_Pic' value='" + lbl_delete + "' class='tahoma10'  />");
                html.append(Js.buttonMouseClick("#delete_Pic", Js.jjPic.delete(id)));
            }
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_Pic' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_Pic", Js.jjPic.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_Pic' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_Pic", Js.jjPic.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_Pic' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_Pic", Js.jjPic.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_Pic' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_Pic", Js.jjPic.addAr(id)));
                    }
                }
            }
            return (Js.setHtml("#Pic_button", html2.toString())) + html.toString();
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
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
            html.append(Js.setVal("#" + _lang, "2"));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Pic_button", "<input type=\"button\" id=\"insert_Pic_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html2.append(Js.buttonMouseClick("#insert_Pic_new", Js.jjPic.insert()));
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
            html.append(Js.setVal("#" + _parent, id));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _category_id, row.get(0).get(_category_id)));
            html.append(Js.setVal("#" + _url_name, (row.get(0).get(_url_name).toString() + "." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setSrc("#" + _url_name, (row.get(0).get(_url_name).toString() + "_small." + row.get(0).get(_url_ex).toString())));
            html.append(Js.setVal("#" + _url_ex, row.get(0).get(_url_ex)));
            html.append(Js.setVal("#" + _lang, "3"));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Pic_button", "<input type='button' id='insert_Pic_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html2.append(Js.buttonMouseClick("#insert_Pic_new_ar", Js.jjPic.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String sw_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = jjTools.getParameter(request, "text").trim();
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            panel = (panel.equals("") ? "sw" : panel);
            titlePanel = (titlePanel.equals("") ? "swTitle" : titlePanel);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
            if (jjNumber.isDigit(text)) {
                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
            }
            if (row.size() > 0) {
                List<Map<String, Object>> picRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(Category_Gallery._id)));
                if (picRow.size() < 1) {
                    String errorMessage = "تصویری در این گالری وجود ندارد";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "No Exist any pictuare in this gallery ;";
                    }
                    return Js.setHtml("#" + panel, errorMessage);
                }
                StringBuffer content = new StringBuffer();
                int counter = 0;
                int counter2 = 0;
                content.append("<table  width='100%'>");
                for (int i = 0; i < picRow.size(); i++) {
                    counter++;
                    counter2++;
                    if (counter == 1) {
                        content.append("<tr style='vertical-align: top' >");
                    } else if (counter == 2) {
                        counter = 0;
                    }


                    content.append("<td>");
                    content.append("<table ><tr><td><img src='upload/"
                            + picRow.get(i).get(_url_name) + "." + picRow.get(i).get(_url_ex)
                            + "' width='300'></td></tr><tr><td class='c'>"
                            + picRow.get(i).get(_title)
                            + "</td></tr></table>");
                    content.append("</td>");

                    if (counter2 == 2) {
                        content.append("</tr>");
                        counter2 = 0;
                    }
                }
                if (picRow.size() % 2 != 0) {
                    content.append("<td>&nbsp;</td></tr>");
                }
                content.append("</table>");
                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._title));
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

    public static String sw(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (jjTools.isLangEn(request)) {
                return sw_En(request, db, isPost);
            }
            String text = jjTools.getParameter(request, "text").trim();
            String panel = jjTools.getParameter(request, "panel");
            String titlePanel = jjTools.getParameter(request, "title");
            panel = (panel.equals("") ? "sw" : panel);
            titlePanel = (titlePanel.equals("") ? "swTitle" : titlePanel);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._title + "='" + text + "'"));
            if (jjNumber.isDigit(text)) {
                row = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + "='" + text + "'"));
            }
            if (row.size() > 0) {
                List<Map<String, Object>> picRow = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + row.get(0).get(Category_Gallery._id)));
                if (picRow.size() < 1) {
                    String errorMessage = "تصویری در این گالری وجود ندارد";
                    if (jjTools.isLangEn(request)) {
                        errorMessage = "No Exist any pictuare in this gallery ;";
                    }
                    return Js.setHtml("#" + panel, errorMessage);
                }
                StringBuffer content = new StringBuffer();
                int counter = 0;
                int counter2 = 0;
                content.append("<table  width='100%'>");
                for (int i = 0; i < picRow.size(); i++) {
                    counter++;
                    counter2++;
                    if (counter == 1) {
                        content.append("<tr style='vertical-align: top' >");
                    } else if (counter == 2) {
                        counter = 0;
                    }


                    content.append("<td>");
                    content.append("<table ><tr><td><img src='upload/"
                            + picRow.get(i).get(_url_name) + "." + picRow.get(i).get(_url_ex)
                            + "' width='300'></td></tr><tr><td class='c'>"
                            + picRow.get(i).get(_title)
                            + "</td></tr></table>");
                    content.append("</td>");

                    if (counter2 == 2) {
                        content.append("</tr>");
                        counter2 = 0;
                    }
                }
                if (picRow.size() % 2 != 0) {
                    content.append("<td>&nbsp;</td></tr>");
                }
                content.append("</table>");
                return Js.setHtml("#" + panel, content.toString()) + Js.setHtml("#" + titlePanel, row.get(0).get(Category_Gallery._title));
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

    public static String getSlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            String delay = jjTools.getParameter(request, "delay");
            width = jjNumber.isDigit(width) ? width : "800";
            height = jjNumber.isDigit(height) ? height : "300";
            List<Map<String, Object>> allNews = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + (jjTools.isLangFa(request) ? "1" : "2")));
            for (int i = 0; i < allNews.size(); i++) {
                html.append("<a href='#'><img src='upload/" + allNews.get(i).get(_url_name) + "." + allNews.get(i).get(_url_ex)
                        + "' width='" + width + "px' style='width:" + width + "px;' alt='" + allNews.get(i).get(_title) + "' /></a>");
            }
            if (panel.equals("")) {
                panel = "picSlider";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript(panel, width, height, delay);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSlipprySliderRunScript(String divId, String width, String height, String delay) {
        try {
//        speed = speed == "" ? "200" : speed;
            return "var demo=$('#" + divId + "').slippry({"
                    // transition: 'fade',
                    // useCSS: true,
                    // speed: 1000,
                    // pause: 3000,
                    // auto: true,
                    // preload: 'visible',
                    // autoHover: false
                    + "});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getSliderRunScript(String divId, String width, String height, String delay) {
        try {
//        speed = speed == "" ? "200" : speed;
            return "$('#" + divId + "').coinslider({"
                    + "width:" + width + ","
                    + "height:" + height + ","
                    + "delay:" + delay + ","
                    + "opacity:1,"
                    + "spw:7,"
                    + "sph:5,"
                    + "sDelay:30,"
                    + "titleSpeed:1500,"
                    + "effect:'',"
                    + "navigation:true,"
                    + "links:true,"
                    + "hoverPause:true"
                    + "});\n";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getPicSlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            String delay = jjTools.getParameter(request, "delay");
            width = jjNumber.isDigit(width) ? width : "650";
            height = jjNumber.isDigit(height) ? height : "228";
            int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));

            List<Map<String, Object>> allPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + lang));
            for (int i = 0; i < allPic.size(); i++) {
                File f = new File(folderAddressUpload.getAbsolutePath() + "/" + (allPic.get(i).get(_url_name).toString().replace("%20", " ") + "." + allPic.get(i).get(_url_ex).toString()));
                if (f.exists()) {
                    html.append("<a href='#'><img src='upload/" + allPic.get(i).get(_url_name) + "." + allPic.get(i).get(_url_ex) + "'/></a>");
                }
            }
            if (panel.equals("")) {
                panel = "picSlider";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSliderRunScript(panel, width, height, delay);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getPicSlipprySlider(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            String sliderId = "demo1";
            String panel = jjTools.getParameter(request, "panel");
            String width = jjTools.getParameter(request, "width").replace("px", "");
            String height = jjTools.getParameter(request, "height").replace("px", "");
            String delay = jjTools.getParameter(request, "delay");
            width = jjNumber.isDigit(width) ? width : "650";
            height = jjNumber.isDigit(height) ? height : "228";
            int lang = jjTools.isLangFa(request) ? 1 : (jjTools.isLangEn(request) ? 2 : 3);
//            File folderAddressUpload = new File(request.getServletContext().getRealPath("/upload"));

            List<Map<String, Object>> allPic = jjDatabase.separateRow(db.Select(tableName, _category_id + "=1 AND " + _lang + "=" + lang));
            html.append("<ul id='" + sliderId + "'>");
            for (int i = 0; i < allPic.size(); i++) {
//                File f = new File(folderAddressUpload.getAbsolutePath() + "/" + (allPic.get(i).get(_url_name).toString().replace("%20", " ") + "." + allPic.get(i).get(_url_ex).toString()));
                html.append("<li><a href='#'><img src='upload/" + allPic.get(i).get(_url_name) + "." + allPic.get(i).get(_url_ex) + "' " + "alt='"
                        + allPic.get(i).get(_title) + "'"
                        + " /></a></li>");
            }
            html.append("</ul>");
            if (panel.equals("")) {
                panel = "picSlider";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += getSlipprySliderRunScript(sliderId, width, height, delay);
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getGallery(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            if (jjTools.isLangEn(request)) {
                return getGallery_En(request, db, isPost);
            }
            if (jjTools.isLangAr(request)) {
                return getGallery_Ar(request, db, isPost);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=1"));
            //پیدا کردن دسته گالری انتخاب شده برای های لایت کردن
            int selected = 0;
            for (int i = 0; i < categoryRow.size(); i++) {
                if (!jjNumber.isDigit(id)) {
                    //اگر عنوان گروه وارد شده بوود و عددی به عنوان آی دی گروه تصاویر نبود
                    if (categoryRow.get(i).get(Category_Gallery._title).toString().equals(id)) {
                        selected = i;
                    }
                } else {
                    if (categoryRow.get(i).get(Category_Gallery._id).toString().equals(id)) {
                        selected = i;
                    }
                }
            }
            html3.append("<div class=\"galleriTitle\">");
            html3.append("گالری");
            html3.append("</div>");
            html3.append("<ul class='picLink'>");
            html3.append("<li><a class='active' onclick='swGetGallery("
                    + categoryRow.get(selected).get(Category_Gallery._id) + ");' >" + categoryRow.get(selected).get(Category_Gallery._title) + "</a></li>");

//            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
            //برای سایر دسته بندی های که انتخاب نشده اند
            for (int i = 0; i < categoryRow.size(); i++) {
//                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._id)).getRowCount() > 0) { //اگر دسته بندی خالی نبود نشانش بده در عیر اینصورت نشان ندهد
                    if (!categoryRow.get(i).get(Category_Gallery._id).toString().equals(categoryRow.get(selected).get(Category_Gallery._id).toString())) {
                        html3.append("<li>" + "<a onclick='swGetGallery("
                                + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a></li>");
                    }
//                }
            }
            html3.append("</ul>");
            html.append(html3.toString());
            List<Map<String, Object>> row;
            if (!jjNumber.isDigit(id)) {
                row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + categoryRow.get(selected).get(Category_Gallery._id).toString() + " AND " + _lang + "=1"));
            } else {
                row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id + " AND " + _lang + "=1"));
            }
            html.append("<div class=picsInCategorie>");
            for (int i = 0; i < row.size(); i++) {
//                html.append("<table style='float:right;' class='picList'><tr><td>");
//                html.append("<img dir='rtl' title='"
//                        + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
//                        + "' src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
//                        + "_small." + row.get(i).get(_url_ex)
//                        + "' class='picInGallery mousePointer' onclick='picDialog(this);' />");
//                html.append("</td></tr><tr><td style='text-align:center'>");
//                html.append(row.get(i).get(_title).toString());
//                html.append("</td></tr></table>");
                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
                        + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
                        + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                        + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");

            }
            html.append("</div>");
//            for (int i = 0; i < row.size(); i++) {
//                html.append("<table><tr class='picEveryRowInGallery'><td>&nbsp;</td></tr></table>");
//            }


            String html2 = "";
//            html2= "$('#" + panel + "').html(\"" + html.toString() + "\");\n"
//                    + "showGallery();\n";
            html2 += Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getGallery_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            String id = jjTools.getParameter(request, "id");
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
//            if(jjNumber.isDigit(id)){
//                List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=2" + " limit 1"));
//                if(categoryRow.isEmpty()){
//                    return Js.setHtml("#" + panel, "There is not any gallery to show");
//                }
//                id = categoryRow.get(0).get(Category_Gallery._id).toString();
//            }
            id = jjNumber.isDigit(id) ? id : "1";
            //select an category as "selected"
            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=2"));
            if (id.equals("1")) {
                if (categoryRow.isEmpty()) {
                    return Js.setHtml("#" + panel, "There is not any gallery In english language ...");
                } else {
                    id = categoryRow.get(0).get(_id).toString();
                }
            }
            //Display all categories in English languge as "ul" & "li" tags
            int selectedI = 0;
            for (int i = 0; i < categoryRow.size(); i++) {
                if (categoryRow.get(i).get(Category_Gallery._id).toString().equals(id)) {
                    selectedI = i;
                }
            }
            html3.append("Gallery");
            html3.append("<ul class='picLink'>");
            html3.append("<li><a class='picLink' onclick='swGetGallery("
                    + categoryRow.get(selectedI).get(Category_Gallery._id) + ");' >" + categoryRow.get(selectedI).get(Category_Gallery._title) + "</a></li>");
            for (int i = 0; i < categoryRow.size(); i++) {
                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._parent)).getRowCount() > 0) {
                    if (!categoryRow.get(selectedI).get(Category_Gallery._id).toString().equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
                        html3.append("<li class='picLinkFlash'>" + "<a  class='picLink' onclick='swGetGallery("
                                + categoryRow.get(i).get(Category_Gallery._id) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a></li>");
                    }
                }
            }
            html3.append("</ul>");
            html.append(html3.toString());

            List<Map<String, Object>> galleriRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._id + "=" + id));
            if (!galleriRow.isEmpty()) {
                String parentId = galleriRow.get(0).get(Category_Gallery._parent).toString();
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + parentId + " AND " + _lang + "=2"));
                for (int i = 0; i < row.size(); i++) {
                    html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                            + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
                            + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
                            + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                            + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
                }
            }
            String html2 = "";
            html2 += Js.setHtml("#" + panel, html.toString());
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getGallery_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            StringBuffer html3 = new StringBuffer();
            String id = jjTools.getParameter(request, "id");
            id = jjNumber.isDigit(id) ? id : "1";
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            List<Map<String, Object>> rowCategory = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._parent + "=" + id + " AND " + Category_Gallery._lang + "=3"));
            html3.append("<span class='picLink'>"
                    + "آلبوم"
                    + "</span><span class='picLinkFlash'>&nbsp;>&nbsp;</span>");
            String idd = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._id).toString();
            String title = rowCategory.size() == 0 ? "" : rowCategory.get(0).get(Category_Gallery._title).toString();
            html3.append("<a class='picLink'>" + title + "</a>");

            List<Map<String, Object>> categoryRow = jjDatabase.separateRow(db.Select(Category_Gallery.tableName, Category_Gallery._lang + "=3"));
            html3.append("<span class='picLinkFlash'>&nbsp;&nbsp;(</span>");
            int counter2 = 0;            
            for (int i = 0; i < categoryRow.size(); i++) {
                if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_Gallery._parent)).getRowCount() > 0) {
                    if (!idd.equals(categoryRow.get(i).get(Category_Gallery._id).toString())) {
                        counter2 += 1;
                        html3.append((counter2 == 1 ? "<span class='picLinkFlash'> أو </span>" : "<span class='picLinkFlash'>, </span>")
                                + "<a  class='picLink' onclick='swGetGallery("
                                + categoryRow.get(i).get(Category_Gallery._parent) + ");'>" + categoryRow.get(i).get(Category_Gallery._title) + "</a>");
                    }
                }
            }

            html3.append("<span class='picLinkFlash'>)</span>");
            if (counter2 > 0) {
                html.append(html3.toString());
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _category_id + "=" + id + " AND " + _lang + "=3"));

            for (int i = 0; i < row.size(); i++) {
                html.append("<a class='galleryShow' href='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                        + "." + row.get(i).get(_url_ex) + "' data-fancybox-group='gallery' "
                        + "title='" + Js.replacor(row.get(i).get(_title).toString()).replace(" ", "&nbsp;")
                        + "'><img src='upload/" + Js.replacor(row.get(i).get(_url_name).toString()).replace(" ", "%20")
                        + "_small." + row.get(i).get(_url_ex) + "' alt='' style='padding:2px'/></a>");
            }
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n"
                    + "showGallery();\n";
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
