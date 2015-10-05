package cms.cms;

import cms.tools.*;
import cms.access.*;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Customer {

    public static String tableName = "account_cust";
    public static String _id = "id";
    public static String _name = "account_cust_name";
    public static String _famil = "account_cust_famil";
    public static String _ful_name = "account_cust_ful_name";
    public static String _code = "account_cust_code";
    public static String _birth = "account_cust_birth";
    public static String _tell = "account_cust_tell";
    public static String _mob = "account_cust_mob";
    public static String _fax = "account_cust_fax";
    public static String _val1 = "account_cust_val1";
    public static String _val2 = "account_cust_val2";
    public static String _val3 = "account_cust_val3";
    public static String _val4 = "account_cust_val4";
    public static String _val5 = "account_cust_val5";
    public static String _val6 = "account_cust_val6";
    public static String _val7 = "account_cust_val7";
    public static String _val8 = "account_cust_val8";
    public static String _val9 = "account_cust_val9";
    public static String _val10 = "account_cust_val10";
    public static String _val11 = "account_cust_val11";
    public static String _val12 = "account_cust_val12";
    public static String _val13 = "account_cust_val13";
    public static String _val14 = "account_cust_val14";
    public static String _val15 = "account_cust_val15";
    public static String _val16 = "account_cust_val16";
    public static String _val17 = "account_cust_val17";
    public static String _val18 = "account_cust_val18";
    public static String _val19 = "account_cust_val19";
    public static String _val20 = "account_cust_val20";
    public static String _email = "account_cust_email";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static int rul_rfs = 56;
    public static int rul_ins = 57;
    public static int rul_edt = 58;
    public static int rul_dlt = 59;
    public static int rul_lng = 60;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(tableName));
            html.append("<table id='refreshCustomer' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='30%'>نام</th>");
            html.append("<th width='10%'>تلفن</th>");
            html.append("<th width='10%'>موبایل</th>");
            html.append("<th width='30%'>ایمیل</th>");
            html.append("<th width='10%'>تولد</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
//                html.append("<tr onclick='$(this).html().print();' class='mousePointer'>");
//                html.append("<tr onclick='alert($(this).children(1).html());' class='mousePointer'>");
                html.append("<tr onclick='cmsCustomer.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_ful_name).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_tell).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_mob).toString()) + "</td>");
                html.append("<td class='l'>" + (row.get(i).get(_email).toString()) + "</td>");
                html.append("<td class='l'>" + (jjCalendar_IR.getViewFormat(row.get(i).get(_birth).toString())) + "</td>");
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
                panel = "swContentTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshCustomer", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "17" : "", "لیست مشتریان");
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
                html.append(Js.setHtml("#Customer_button", "<input type=\"button\" id=\"insert_customer_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_customer_new", Js.jjCustomer.insert()));
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

            map.put(_birth, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birth), true));
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_famil, jjTools.getParameter(request, _famil));
            map.put(_fax, jjTools.getParameter(request, _fax));
            map.put(_ful_name, jjTools.getParameter(request, _ful_name));
            map.put(_mob, jjTools.getParameter(request, _mob));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_val7, jjTools.getParameter(request, _val7));
            map.put(_val8, jjTools.getParameter(request, _val8));
            map.put(_val9, jjTools.getParameter(request, _val9));
            map.put(_val10, jjTools.getParameter(request, _val10));
            map.put(_val11, jjTools.getParameter(request, _val11));
            map.put(_val12, jjTools.getParameter(request, _val12));
            map.put(_val13, jjTools.getParameter(request, _val13));
            map.put(_val14, jjTools.getParameter(request, _val14));
            map.put(_val15, jjTools.getParameter(request, _val15));

            map.put(_val16, jjNumber.isDigit(jjTools.getParameter(request, _val16)) ? Integer.parseInt(jjTools.getParameter(request, _val16)) : 0);
            map.put(_val17, jjNumber.isDigit(jjTools.getParameter(request, _val17)) ? Integer.parseInt(jjTools.getParameter(request, _val17)) : 0);
            map.put(_val18, jjNumber.isDigit(jjTools.getParameter(request, _val18)) ? Integer.parseInt(jjTools.getParameter(request, _val18)) : 0);
            map.put(_val19, jjNumber.isDigit(jjTools.getParameter(request, _val19)) ? Integer.parseInt(jjTools.getParameter(request, _val19)) : 0);
            map.put(_val20, jjNumber.isDigit(jjTools.getParameter(request, _val20)) ? Integer.parseInt(jjTools.getParameter(request, _val20)) : 0);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCustomer.refresh();
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

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }


            map.put(_birth, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birth), true));
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_famil, jjTools.getParameter(request, _famil));
            map.put(_fax, jjTools.getParameter(request, _fax));
            map.put(_ful_name, jjTools.getParameter(request, _ful_name));
            map.put(_mob, jjTools.getParameter(request, _mob));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_tell, jjTools.getParameter(request, _tell));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_val7, jjTools.getParameter(request, _val7));
            map.put(_val8, jjTools.getParameter(request, _val8));
            map.put(_val9, jjTools.getParameter(request, _val9));
            map.put(_val10, jjTools.getParameter(request, _val10));
            map.put(_val11, jjTools.getParameter(request, _val11));
            map.put(_val12, jjTools.getParameter(request, _val12));
            map.put(_val13, jjTools.getParameter(request, _val13));
            map.put(_val14, jjTools.getParameter(request, _val14));
            map.put(_val15, jjTools.getParameter(request, _val15));

            map.put(_val16, jjNumber.isDigit(jjTools.getParameter(request, _val16)) ? Integer.parseInt(jjTools.getParameter(request, _val16)) : 0);
            map.put(_val17, jjNumber.isDigit(jjTools.getParameter(request, _val17)) ? Integer.parseInt(jjTools.getParameter(request, _val17)) : 0);
            map.put(_val18, jjNumber.isDigit(jjTools.getParameter(request, _val18)) ? Integer.parseInt(jjTools.getParameter(request, _val18)) : 0);
            map.put(_val19, jjNumber.isDigit(jjTools.getParameter(request, _val19)) ? Integer.parseInt(jjTools.getParameter(request, _val19)) : 0);
            map.put(_val20, jjNumber.isDigit(jjTools.getParameter(request, _val20)) ? Integer.parseInt(jjTools.getParameter(request, _val20)) : 0);


            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put(Factor._cust_name, jjTools.getParameter(request, _ful_name));
            db.update(Factor.tableName, map2, Factor._cust_id + "=" + id);
            return Js.jjCustomer.refresh();
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

            if (id.equals("1") || db.Select(Factor.tableName, Factor._cust_id + "=" + id).getRowCount() > 0) {
                return Js.dialog("شما اجازه حذف این رکورد را ندارید.");
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCustomer.refresh();
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
            html.append(Js.setValDate("#" + _birth, row.get(0).get(_birth)));
            html.append(Js.setVal(_code, row.get(0).get(_code)));
            html.append(Js.setVal(_famil, row.get(0).get(_famil)));
            html.append(Js.setVal(_fax, row.get(0).get(_fax)));
            html.append(Js.setVal(_ful_name, row.get(0).get(_ful_name)));
            html.append(Js.setVal(_mob, row.get(0).get(_mob)));
            html.append(Js.setVal(_name, row.get(0).get(_name)));
            html.append(Js.setVal(_tell, row.get(0).get(_tell)));
            html.append(Js.setVal(_val1, row.get(0).get(_val1)));
            html.append(Js.setVal(_val2, row.get(0).get(_val2)));
            html.append(Js.setVal(_val3, row.get(0).get(_val3)));
            html.append(Js.setVal(_val4, row.get(0).get(_val4)));
            html.append(Js.setVal(_val5, row.get(0).get(_val5)));
            html.append(Js.setVal(_val6, row.get(0).get(_val6)));
            html.append(Js.setVal(_val7, row.get(0).get(_val7)));
            html.append(Js.setVal(_val8, row.get(0).get(_val8)));
            html.append(Js.setVal(_val9, row.get(0).get(_val9)));
            html.append(Js.setVal(_val10, row.get(0).get(_val10)));
            html.append(Js.setVal(_val11, row.get(0).get(_val11)));
            html.append(Js.setVal(_val12, row.get(0).get(_val12)));
            html.append(Js.setVal(_val13, row.get(0).get(_val13)));
            html.append(Js.setVal(_val14, row.get(0).get(_val14)));
            html.append(Js.setVal(_val15, row.get(0).get(_val15)));
            html.append(Js.setVal(_val16, row.get(0).get(_val16)));
            html.append(Js.setVal(_val17, row.get(0).get(_val17)));
            html.append(Js.setVal(_val18, row.get(0).get(_val18)));
            html.append(Js.setVal(_val19, row.get(0).get(_val19)));
            html.append(Js.setVal(_val20, row.get(0).get(_val20)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_customer\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_customer", Js.jjCustomer.edit()));
            }
            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_customer\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_customer", Js.jjCustomer.delete(id)));
            }
            return (Js.setHtml("#Customer_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
