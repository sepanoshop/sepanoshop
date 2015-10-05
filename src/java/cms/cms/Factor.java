package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.util.HashMap;
import jj.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

public class Factor {

    public static String tableName = "account_factor";
    public static String _id = "id";
    public static String _code = "account_factor_code";
    public static String _user_id = "account_factor_user_id";
    public static String _user_name = "account_factor_user_name";
    public static String _cust_id = "account_factor_cust_id";
    public static String _cust_name = "account_factor_cust_name";
    public static String _type = "account_factor_type";
    public static String _date = "account_factor_date";
    public static String _sum = "account_factor_sum";
    public static String _discount = "account_factor_discount";
    public static String _pay = "account_factor_pay";
    public static String _remainder = "account_factor_remainder";
    public static String _comment = "account_factor_comment";
    public static String _pr_id_1 = "account_factor_pr_id_1";
    public static String _pr_id_2 = "account_factor_pr_id_2";
    public static String _pr_id_3 = "account_factor_pr_id_3";
    public static String _pr_id_4 = "account_factor_pr_id_4";
    public static String _pr_id_5 = "account_factor_pr_id_5";
    public static String _pr_id_6 = "account_factor_pr_id_6";
    public static String _pr_id_7 = "account_factor_pr_id_7";
    public static String _pr_id_8 = "account_factor_pr_id_8";
    public static String _pr_id_9 = "account_factor_pr_id_9";
    public static String _pr_id_10 = "account_factor_pr_id_10";
    public static String _pr_id_11 = "account_factor_pr_id_11";
    public static String _pr_id_12 = "account_factor_pr_id_12";
    public static String _pr_id_13 = "account_factor_pr_id_13";
    public static String _pr_id_14 = "account_factor_pr_id_14";
    public static String _pr_id_15 = "account_factor_pr_id_15";
    public static String _pr_id_16 = "account_factor_pr_id_16";
    public static String _pr_id_17 = "account_factor_pr_id_17";
    public static String _pr_id_18 = "account_factor_pr_id_18";
    public static String _pr_id_19 = "account_factor_pr_id_19";
    public static String _pr_id_20 = "account_factor_pr_id_20";
    public static String _pr_code_1 = "account_factor_pr_code_1";
    public static String _pr_code_2 = "account_factor_pr_code_2";
    public static String _pr_code_3 = "account_factor_pr_code_3";
    public static String _pr_code_4 = "account_factor_pr_code_4";
    public static String _pr_code_5 = "account_factor_pr_code_5";
    public static String _pr_code_6 = "account_factor_pr_code_6";
    public static String _pr_code_7 = "account_factor_pr_code_7";
    public static String _pr_code_8 = "account_factor_pr_code_8";
    public static String _pr_code_9 = "account_factor_pr_code_9";
    public static String _pr_code_10 = "account_factor_pr_code_10";
    public static String _pr_code_11 = "account_factor_pr_code_11";
    public static String _pr_code_12 = "account_factor_pr_code_12";
    public static String _pr_code_13 = "account_factor_pr_code_13";
    public static String _pr_code_14 = "account_factor_pr_code_14";
    public static String _pr_code_15 = "account_factor_pr_code_15";
    public static String _pr_code_16 = "account_factor_pr_code_16";
    public static String _pr_code_17 = "account_factor_pr_code_17";
    public static String _pr_code_18 = "account_factor_pr_code_18";
    public static String _pr_code_19 = "account_factor_pr_code_19";
    public static String _pr_code_20 = "account_factor_pr_code_20";
    public static String _pr_name_1 = "account_factor_pr_name_1";
    public static String _pr_name_2 = "account_factor_pr_name_2";
    public static String _pr_name_3 = "account_factor_pr_name_3";
    public static String _pr_name_4 = "account_factor_pr_name_4";
    public static String _pr_name_5 = "account_factor_pr_name_5";
    public static String _pr_name_6 = "account_factor_pr_name_6";
    public static String _pr_name_7 = "account_factor_pr_name_7";
    public static String _pr_name_8 = "account_factor_pr_name_8";
    public static String _pr_name_9 = "account_factor_pr_name_9";
    public static String _pr_name_10 = "account_factor_pr_name_10";
    public static String _pr_name_11 = "account_factor_pr_name_11";
    public static String _pr_name_12 = "account_factor_pr_name_12";
    public static String _pr_name_13 = "account_factor_pr_name_13";
    public static String _pr_name_14 = "account_factor_pr_name_14";
    public static String _pr_name_15 = "account_factor_pr_name_15";
    public static String _pr_name_16 = "account_factor_pr_name_16";
    public static String _pr_name_17 = "account_factor_pr_name_17";
    public static String _pr_name_18 = "account_factor_pr_name_18";
    public static String _pr_name_19 = "account_factor_pr_name_19";
    public static String _pr_name_20 = "account_factor_pr_name_20";
    public static String _pr_count_1 = "account_factor_pr_count_1";
    public static String _pr_count_2 = "account_factor_pr_count_2";
    public static String _pr_count_3 = "account_factor_pr_count_3";
    public static String _pr_count_4 = "account_factor_pr_count_4";
    public static String _pr_count_5 = "account_factor_pr_count_5";
    public static String _pr_count_6 = "account_factor_pr_count_6";
    public static String _pr_count_7 = "account_factor_pr_count_7";
    public static String _pr_count_8 = "account_factor_pr_count_8";
    public static String _pr_count_9 = "account_factor_pr_count_9";
    public static String _pr_count_10 = "account_factor_pr_count_10";
    public static String _pr_count_11 = "account_factor_pr_count_11";
    public static String _pr_count_12 = "account_factor_pr_count_12";
    public static String _pr_count_13 = "account_factor_pr_count_13";
    public static String _pr_count_14 = "account_factor_pr_count_14";
    public static String _pr_count_15 = "account_factor_pr_count_15";
    public static String _pr_count_16 = "account_factor_pr_count_16";
    public static String _pr_count_17 = "account_factor_pr_count_17";
    public static String _pr_count_18 = "account_factor_pr_count_18";
    public static String _pr_count_19 = "account_factor_pr_count_19";
    public static String _pr_count_20 = "account_factor_pr_count_20";
    public static String _pr_unit_1 = "account_factor_pr_unit_1";
    public static String _pr_unit_2 = "account_factor_pr_unit_2";
    public static String _pr_unit_3 = "account_factor_pr_unit_3";
    public static String _pr_unit_4 = "account_factor_pr_unit_4";
    public static String _pr_unit_5 = "account_factor_pr_unit_5";
    public static String _pr_unit_6 = "account_factor_pr_unit_6";
    public static String _pr_unit_7 = "account_factor_pr_unit_7";
    public static String _pr_unit_8 = "account_factor_pr_unit_8";
    public static String _pr_unit_9 = "account_factor_pr_unit_9";
    public static String _pr_unit_10 = "account_factor_pr_unit_10";
    public static String _pr_unit_11 = "account_factor_pr_unit_11";
    public static String _pr_unit_12 = "account_factor_pr_unit_12";
    public static String _pr_unit_13 = "account_factor_pr_unit_13";
    public static String _pr_unit_14 = "account_factor_pr_unit_14";
    public static String _pr_unit_15 = "account_factor_pr_unit_15";
    public static String _pr_unit_16 = "account_factor_pr_unit_16";
    public static String _pr_unit_17 = "account_factor_pr_unit_17";
    public static String _pr_unit_18 = "account_factor_pr_unit_18";
    public static String _pr_unit_19 = "account_factor_pr_unit_19";
    public static String _pr_unit_20 = "account_factor_pr_unit_20";
    public static String _pr_fee_1 = "account_factor_pr_fee_1";
    public static String _pr_fee_2 = "account_factor_pr_fee_2";
    public static String _pr_fee_3 = "account_factor_pr_fee_3";
    public static String _pr_fee_4 = "account_factor_pr_fee_4";
    public static String _pr_fee_5 = "account_factor_pr_fee_5";
    public static String _pr_fee_6 = "account_factor_pr_fee_6";
    public static String _pr_fee_7 = "account_factor_pr_fee_7";
    public static String _pr_fee_8 = "account_factor_pr_fee_8";
    public static String _pr_fee_9 = "account_factor_pr_fee_9";
    public static String _pr_fee_10 = "account_factor_pr_fee_10";
    public static String _pr_fee_11 = "account_factor_pr_fee_11";
    public static String _pr_fee_12 = "account_factor_pr_fee_12";
    public static String _pr_fee_13 = "account_factor_pr_fee_13";
    public static String _pr_fee_14 = "account_factor_pr_fee_14";
    public static String _pr_fee_15 = "account_factor_pr_fee_15";
    public static String _pr_fee_16 = "account_factor_pr_fee_16";
    public static String _pr_fee_17 = "account_factor_pr_fee_17";
    public static String _pr_fee_18 = "account_factor_pr_fee_18";
    public static String _pr_fee_19 = "account_factor_pr_fee_19";
    public static String _pr_fee_20 = "account_factor_pr_fee_20";
    public static String _is_deliver = "account_factor_is_deliver";
    public static String _pr_sum_1 = "account_factor_pr_sum_1";
    public static String _pr_sum_2 = "account_factor_pr_sum_2";
    public static String _pr_sum_3 = "account_factor_pr_sum_3";
    public static String _pr_sum_4 = "account_factor_pr_sum_4";
    public static String _pr_sum_5 = "account_factor_pr_sum_5";
    public static String _pr_sum_6 = "account_factor_pr_sum_6";
    public static String _pr_sum_7 = "account_factor_pr_sum_7";
    public static String _pr_sum_8 = "account_factor_pr_sum_8";
    public static String _pr_sum_9 = "account_factor_pr_sum_9";
    public static String _pr_sum_10 = "account_factor_pr_sum_10";
    public static String _pr_sum_11 = "account_factor_pr_sum_11";
    public static String _pr_sum_12 = "account_factor_pr_sum_12";
    public static String _pr_sum_13 = "account_factor_pr_sum_13";
    public static String _pr_sum_14 = "account_factor_pr_sum_14";
    public static String _pr_sum_15 = "account_factor_pr_sum_15";
    public static String _pr_sum_16 = "account_factor_pr_sum_16";
    public static String _pr_sum_17 = "account_factor_pr_sum_17";
    public static String _pr_sum_18 = "account_factor_pr_sum_18";
    public static String _pr_sum_19 = "account_factor_pr_sum_19";
    public static String _pr_sum_20 = "account_factor_pr_sum_20";
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
    static int pageCounter = 4;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _date + "=" + new jjCalendar_IR().getDBFormat_8length()));
            html.append("<table id='refreshFactor' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ</th>");
            html.append("<th width='10%'>کد دستی</th>");
            html.append("<th width='35%'>مشتری</th>");
            html.append("<th width='13%'>جمع (ریال)</th>");
            html.append("<th width='10%'>تخفیف</th>");
            html.append("<th width='12%'>باقیمانده</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsFactor.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_cust_id).toString() + ". " + row.get(i).get(_cust_name).toString()) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_sum).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_discount).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_remainder).toString())) + "</td>");
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
                panel = "swProduct1Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactor", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "16" : "", "لیست فاکتور های امروز");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String refresh2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            String query = (new jjCalendar_IR().getDBFormat_8length() + "").substring(0, 6) + "00";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _date + ">" + query));
            html.append("<table id='refreshFactor2' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ</th>");
            html.append("<th width='10%'>کد دستی</th>");
            html.append("<th width='35%'>مشتری</th>");
            html.append("<th width='13%'>جمع (ریال)</th>");
            html.append("<th width='10%'>تخفیف</th>");
            html.append("<th width='12%'>باقیمانده</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsFactor.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_cust_id).toString() + ". " + row.get(i).get(_cust_name).toString()) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_sum).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_discount).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_remainder).toString())) + "</td>");
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
                panel = "swProduct4Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactor2", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "" : "", "لیست فاکتور های این ماه");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String refresh3(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            String query = (new jjCalendar_IR().getDBFormat_8length() + "").substring(0, 4) + "0000";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _date + ">" + query));
            html.append("<table id='refreshFactor3' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ</th>");
            html.append("<th width='10%'>کد دستی</th>");
            html.append("<th width='35%'>مشتری</th>");
            html.append("<th width='13%'>جمع (ریال)</th>");
            html.append("<th width='10%'>تخفیف</th>");
            html.append("<th width='12%'>باقیمانده</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsFactor.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_cust_id).toString() + ". " + row.get(i).get(_cust_name).toString()) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_sum).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_discount).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_remainder).toString())) + "</td>");
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
                panel = "swProduct5Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactor3", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "" : "", "لیست فاکتور های امسال");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String refresh4(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            jjCalendar_IR ir = new jjCalendar_IR();
            String query = ir.getYear_4length() + "0000";
            String query2 = ((ir.getYear() - 1) + "").substring(0, 4) + "0000";
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, query + ">" + _date + " AND " + _date + ">" + query2));
            html.append("<table id='refreshFactor4' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ</th>");
            html.append("<th width='10%'>کد دستی</th>");
            html.append("<th width='35%'>مشتری</th>");
            html.append("<th width='13%'>جمع (ریال)</th>");
            html.append("<th width='10%'>تخفیف</th>");
            html.append("<th width='12%'>باقیمانده</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsFactor.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_cust_id).toString() + ". " + row.get(i).get(_cust_name).toString()) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_sum).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_discount).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_remainder).toString())) + "</td>");
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
                panel = "swProduct6Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactor4", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "" : "", "لیست فاکتور های پارسال");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String refresh5(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            DefaultTableModel dtm = db.Select(tableName, _remainder + ">0");
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
            html.append("<table id='refreshFactor5' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>تاریخ</th>");
            html.append("<th width='10%'>کد دستی</th>");
            html.append("<th width='35%'>مشتری</th>");
            html.append("<th width='13%'>جمع (ریال)</th>");
            html.append("<th width='10%'>تخفیف</th>");
            html.append("<th width='12%'>باقیمانده</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  onclick='cmsFactor.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='l'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + "</td>");
                html.append("<td class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_cust_id).toString() + ". " + row.get(i).get(_cust_name).toString()) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_sum).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_discount).toString())) + "</td>");
                html.append("<td class='l'>" + (jjNumber.getFormattedNumber(row.get(i).get(_remainder).toString())) + "</td>");
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
                panel = "swProduct7Tbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshFactor5", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "" : "", "لیست فاکتور های تسویه نشده");
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
                html.append(Js.setHtml("#Factor_button", "<input type=\"button\" id=\"insert_Factor_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Factor_new", Js.jjFactor.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static Map<String, Object> getPack(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put(_code, jjTools.getParameter(request, _code));
        map.put(_user_id, jjTools.getSeassionUserId(request));
        List<Map<String, Object>> rowUser = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + jjTools.getSeassionUserId(request)));
        map.put(_user_name, rowUser.size() == 0 ? "" : (rowUser.get(0).get(Access_User._name).toString() + " " + rowUser.get(0).get(Access_User._family).toString()));

        map.put(_comment, jjTools.getParameter(request, _comment));
        map.put(_discount, jjNumber.isDigit(jjTools.getParameter(request, _discount).replace(",", "")) ? Integer.parseInt(jjTools.getParameter(request, _discount).replace(",", "")) : 0);
        map.put(_sum, jjNumber.isDigit(jjTools.getParameter(request, _sum).replace(",", "")) ? Integer.parseInt(jjTools.getParameter(request, _sum).replace(",", "")) : 0);
        map.put(_remainder, jjNumber.isDigit(jjTools.getParameter(request, _remainder).replace(",", "")) ? Integer.parseInt(jjTools.getParameter(request, _remainder).replace(",", "")) : 0);
        map.put(_pay, jjNumber.isDigit(jjTools.getParameter(request, _pay).replace(",", "")) ? Integer.parseInt(jjTools.getParameter(request, _pay).replace(",", "")) : 0);
        map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));
        map.put(_is_deliver, jjNumber.isDigit(jjTools.getParameter(request, _is_deliver)) ? Integer.parseInt(jjTools.getParameter(request, _is_deliver)) : 0);
        map.put(_type, jjNumber.isDigit(jjTools.getParameter(request, _type)) ? Integer.parseInt(jjTools.getParameter(request, _type)) : 0);

        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_1))) {
            map.put(_pr_count_1, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_1)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_1)) : 1);
            map.put(_pr_fee_1, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_1)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_1)) : 0);
            map.put(_pr_sum_1, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_1)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_1)) : 0);
            map.put(_pr_id_1, Integer.parseInt(jjTools.getParameter(request, _pr_id_1)));
            map.put(_pr_name_1, jjTools.getParameter(request, _pr_name_1));
            map.put(_pr_unit_1, jjTools.getParameter(request, _pr_unit_1));
            map.put(_pr_code_1, jjTools.getParameter(request, _pr_code_1));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_2))) {
            map.put(_pr_count_2, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_2)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_2)) : 1);
            map.put(_pr_fee_2, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_2)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_2)) : 0);
            map.put(_pr_sum_2, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_2)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_2)) : 0);
            map.put(_pr_id_2, Integer.parseInt(jjTools.getParameter(request, _pr_id_2)));
            map.put(_pr_name_2, jjTools.getParameter(request, _pr_name_2));
            map.put(_pr_unit_2, jjTools.getParameter(request, _pr_unit_2));
            map.put(_pr_code_2, jjTools.getParameter(request, _pr_code_2));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_3))) {
            map.put(_pr_count_3, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_3)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_3)) : 1);
            map.put(_pr_fee_3, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_3)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_3)) : 0);
            map.put(_pr_sum_3, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_3)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_3)) : 0);
            map.put(_pr_id_3, Integer.parseInt(jjTools.getParameter(request, _pr_id_3)));
            map.put(_pr_name_3, jjTools.getParameter(request, _pr_name_3));
            map.put(_pr_unit_3, jjTools.getParameter(request, _pr_unit_3));
            map.put(_pr_code_3, jjTools.getParameter(request, _pr_code_3));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_4))) {
            map.put(_pr_count_4, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_4)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_4)) : 1);
            map.put(_pr_fee_4, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_4)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_4)) : 0);
            map.put(_pr_sum_4, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_4)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_4)) : 0);
            map.put(_pr_id_4, Integer.parseInt(jjTools.getParameter(request, _pr_id_4)));
            map.put(_pr_name_4, jjTools.getParameter(request, _pr_name_4));
            map.put(_pr_unit_4, jjTools.getParameter(request, _pr_unit_4));
            map.put(_pr_code_4, jjTools.getParameter(request, _pr_code_4));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_5))) {
            map.put(_pr_count_5, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_5)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_5)) : 1);
            map.put(_pr_fee_5, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_5)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_5)) : 0);
            map.put(_pr_sum_5, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_5)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_5)) : 0);
            map.put(_pr_id_5, Integer.parseInt(jjTools.getParameter(request, _pr_id_5)));
            map.put(_pr_name_5, jjTools.getParameter(request, _pr_name_5));
            map.put(_pr_unit_5, jjTools.getParameter(request, _pr_unit_5));
            map.put(_pr_code_5, jjTools.getParameter(request, _pr_code_5));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_6))) {
            map.put(_pr_count_6, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_6)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_6)) : 1);
            map.put(_pr_fee_6, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_6)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_6)) : 0);
            map.put(_pr_sum_6, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_6)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_6)) : 0);
            map.put(_pr_id_6, Integer.parseInt(jjTools.getParameter(request, _pr_id_6)));
            map.put(_pr_name_6, jjTools.getParameter(request, _pr_name_6));
            map.put(_pr_unit_6, jjTools.getParameter(request, _pr_unit_6));
            map.put(_pr_code_6, jjTools.getParameter(request, _pr_code_6));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_7))) {
            map.put(_pr_count_7, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_7)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_7)) : 1);
            map.put(_pr_fee_7, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_7)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_7)) : 0);
            map.put(_pr_sum_7, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_7)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_7)) : 0);
            map.put(_pr_id_7, Integer.parseInt(jjTools.getParameter(request, _pr_id_7)));
            map.put(_pr_name_7, jjTools.getParameter(request, _pr_name_7));
            map.put(_pr_unit_7, jjTools.getParameter(request, _pr_unit_7));
            map.put(_pr_code_7, jjTools.getParameter(request, _pr_code_7));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_8))) {
            map.put(_pr_count_8, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_8)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_8)) : 1);
            map.put(_pr_fee_8, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_8)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_8)) : 0);
            map.put(_pr_sum_8, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_8)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_8)) : 0);
            map.put(_pr_id_8, Integer.parseInt(jjTools.getParameter(request, _pr_id_8)));
            map.put(_pr_name_8, jjTools.getParameter(request, _pr_name_8));
            map.put(_pr_unit_8, jjTools.getParameter(request, _pr_unit_8));
            map.put(_pr_code_8, jjTools.getParameter(request, _pr_code_8));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_9))) {
            map.put(_pr_count_9, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_9)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_9)) : 1);
            map.put(_pr_fee_9, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_9)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_9)) : 0);
            map.put(_pr_sum_9, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_9)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_9)) : 0);
            map.put(_pr_id_9, Integer.parseInt(jjTools.getParameter(request, _pr_id_9)));
            map.put(_pr_name_9, jjTools.getParameter(request, _pr_name_9));
            map.put(_pr_unit_9, jjTools.getParameter(request, _pr_unit_9));
            map.put(_pr_code_9, jjTools.getParameter(request, _pr_code_9));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_10))) {
            map.put(_pr_count_10, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_10)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_10)) : 1);
            map.put(_pr_fee_10, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_10)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_10)) : 0);
            map.put(_pr_sum_10, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_10)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_10)) : 0);
            map.put(_pr_id_10, Integer.parseInt(jjTools.getParameter(request, _pr_id_10)));
            map.put(_pr_name_10, jjTools.getParameter(request, _pr_name_10));
            map.put(_pr_unit_10, jjTools.getParameter(request, _pr_unit_10));
            map.put(_pr_code_10, jjTools.getParameter(request, _pr_code_10));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_11))) {
            map.put(_pr_count_11, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_11)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_11)) : 1);
            map.put(_pr_fee_11, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_11)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_11)) : 0);
            map.put(_pr_sum_11, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_11)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_11)) : 0);
            map.put(_pr_id_11, Integer.parseInt(jjTools.getParameter(request, _pr_id_11)));
            map.put(_pr_name_11, jjTools.getParameter(request, _pr_name_11));
            map.put(_pr_unit_11, jjTools.getParameter(request, _pr_unit_11));
            map.put(_pr_code_11, jjTools.getParameter(request, _pr_code_11));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_12))) {
            map.put(_pr_count_12, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_12)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_12)) : 1);
            map.put(_pr_fee_12, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_12)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_12)) : 0);
            map.put(_pr_sum_12, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_12)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_12)) : 0);
            map.put(_pr_id_12, Integer.parseInt(jjTools.getParameter(request, _pr_id_12)));
            map.put(_pr_name_12, jjTools.getParameter(request, _pr_name_12));
            map.put(_pr_unit_12, jjTools.getParameter(request, _pr_unit_12));
            map.put(_pr_code_12, jjTools.getParameter(request, _pr_code_12));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_13))) {
            map.put(_pr_count_13, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_13)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_13)) : 1);
            map.put(_pr_fee_13, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_13)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_13)) : 0);
            map.put(_pr_sum_13, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_13)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_13)) : 0);
            map.put(_pr_id_13, Integer.parseInt(jjTools.getParameter(request, _pr_id_13)));
            map.put(_pr_name_13, jjTools.getParameter(request, _pr_name_13));
            map.put(_pr_unit_13, jjTools.getParameter(request, _pr_unit_13));
            map.put(_pr_code_13, jjTools.getParameter(request, _pr_code_13));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_14))) {
            map.put(_pr_count_14, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_14)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_14)) : 1);
            map.put(_pr_fee_14, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_14)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_14)) : 0);
            map.put(_pr_sum_14, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_14)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_14)) : 0);
            map.put(_pr_id_14, Integer.parseInt(jjTools.getParameter(request, _pr_id_14)));
            map.put(_pr_name_14, jjTools.getParameter(request, _pr_name_14));
            map.put(_pr_unit_14, jjTools.getParameter(request, _pr_unit_14));
            map.put(_pr_code_14, jjTools.getParameter(request, _pr_code_14));
        }
        if (jjNumber.isDigit(jjTools.getParameter(request, _pr_id_15))) {
            map.put(_pr_count_15, jjNumber.isDigit(jjTools.getParameter(request, _pr_count_15)) ? Integer.parseInt(jjTools.getParameter(request, _pr_count_15)) : 1);
            map.put(_pr_fee_15, jjNumber.isDigit(jjTools.getParameter(request, _pr_fee_15)) ? Integer.parseInt(jjTools.getParameter(request, _pr_fee_15)) : 0);
            map.put(_pr_sum_15, jjNumber.isDigit(jjTools.getParameter(request, _pr_sum_15)) ? Integer.parseInt(jjTools.getParameter(request, _pr_sum_15)) : 0);
            map.put(_pr_id_15, Integer.parseInt(jjTools.getParameter(request, _pr_id_15)));
            map.put(_pr_name_15, jjTools.getParameter(request, _pr_name_15));
            map.put(_pr_unit_15, jjTools.getParameter(request, _pr_unit_15));
            map.put(_pr_code_15, jjTools.getParameter(request, _pr_code_15));
        }
        if (jjTools.getParameter(request, _cust_name).equals("")) {
            List<Map<String, Object>> custRow = jjDatabase.separateRow(db.Select(Customer.tableName, Customer._id + "=1"));
            map.put(_cust_name, custRow.size() == 0 ? "" : custRow.get(0).get(Customer._ful_name));
            map.put(_cust_id, 1);
        } else {
            map.put(_cust_name, jjTools.getParameter(request, _cust_name));
            String custId = jjTools.getParameter(request, _cust_id);
            if (jjNumber.isDigit(custId)) {
                map.put(_cust_id, Integer.parseInt(custId));
            } else {
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put(Customer._ful_name, jjTools.getParameter(request, _cust_name));
                map2.put(Customer._birth, jjCalendar_IR.getDatabaseFormat_8length("", false));
                map2.put(Customer._code, "");
                map2.put(Customer._famil, "");
                map2.put(Customer._fax, "");
                map2.put(Customer._mob, "");
                map2.put(Customer._name, "");
                map2.put(Customer._tell, "");
                map2.put(Customer._val1, "");
                map2.put(Customer._val2, "");
                map2.put(Customer._val3, "");
                map2.put(Customer._val4, "");
                map2.put(Customer._val5, "");
                map2.put(Customer._val6, "");
                map2.put(Customer._val7, "");
                map2.put(Customer._val8, "");
                map2.put(Customer._val9, "");
                map2.put(Customer._val10, "");
                map2.put(Customer._val11, "");
                map2.put(Customer._val12, "");
                map2.put(Customer._val13, "");
                map2.put(Customer._val14, "");
                map2.put(Customer._val15, "");
                map2.put(Customer._val16, 0);
                map2.put(Customer._val17, 0);
                map2.put(Customer._val18, 0);
                map2.put(Customer._val19, 0);
                map2.put(Customer._val20, 0);
                List<Map<String, Object>> rowCust = jjDatabase.separateRow(db.insert(Customer.tableName, map2));
                map.put(_cust_id, rowCust.size() == 0 ? 0 : Integer.parseInt(rowCust.get(0).get(Customer._id).toString()));
            }
        }
        return map;
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            List<Map<String, Object>> row = jjDatabase.separateRow(db.insert(tableName, getPack(request, db, isPost)));
            if (row.size() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjFactor.refresh() + Js.jjFactor.print(row.get(0).get(_id).toString());
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
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, getPack(request, db, isPost), _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjFactor.refresh();
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
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjFactor.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String setCustToFactor(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Customer.tableName, Customer._id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "مشتری با این کد وجود ندارد";
                return Js.dialog(errorMessage);
            }

            StringBuffer html = new StringBuffer();

            html.append(Js.setVal("#" + _cust_name, row.get(0).get(Customer._ful_name)));
            html.append(Js.setVal("#" + _cust_id, row.get(0).get(Customer._id)));
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String searchCustomer(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = jjTools.getParameter(request, "text");
            String panel = jjTools.getParameter(request, "panel");
            if (!text.equals("")) {
                List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Customer.tableName, Customer._ful_name + " LIKE '%" + text + "%' OR " + Customer._code + " LIKE '%" + text + "%' " + (jjNumber.isDigit(text) ? "OR id=" + text : ";")));
                if (row.size() > 0) {
                    String find = "";
                    for (int i = 0; i < row.size(); i++) {
                        find += "<a style='color:blue' onclick='setCustToFactor(" + row.get(i).get(Customer._id) + ");'>" + row.get(i).get(Customer._id) + "." + row.get(i).get(Customer._ful_name)
                                + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    return Js.setHtml("#" + panel, find) + (row.size() > 0 ? Js.setVal("#" + _cust_id, row.get(0).get(Customer._id)) : "");
                } else {
                    return Js.setHtml("#" + panel, "موردی یافت نشد.") + Js.setVal("#" + _cust_id, "");
                }
            } else {
                return Js.setHtml("#" + panel, "");
            }
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
            html.append(Js.setValDate("#" + _date, row.get(0).get(_date)));

            html.append(Js.setVal("#" + _code, row.get(0).get(_code)));
            html.append(Js.setVal("#" + _cust_id, row.get(0).get(_cust_id)));
            html.append(Js.setVal("#" + _cust_name, row.get(0).get(_cust_name)));
            html.append(Js.setVal("#" + _discount, row.get(0).get(_discount)));
            html.append(Js.setVal("#" + _sum, jjNumber.getFormattedNumber(row.get(0).get(_sum).toString())));
            html.append(Js.setVal("#" + _remainder, jjNumber.getFormattedNumber(row.get(0).get(_remainder).toString())));
            html.append(Js.setVal("#" + _is_deliver, row.get(0).get(_is_deliver).toString().equals("1")));
            html.append(Js.setVal("#" + _pay, row.get(0).get(_pay)));
            html.append(Js.setVal("#" + _type, row.get(0).get(_type)));
            html.append(Js.setVal("#" + _user_id, row.get(0).get(_user_id)));
            html.append(Js.setVal("#" + _user_name, row.get(0).get(_user_name)));
            html.append(Js.setVal("#" + _comment, row.get(0).get(_comment)));

            html.append(Js.setVal("#" + _pr_code_1, row.get(0).get(_pr_code_1)));
            html.append(Js.setVal("#" + _pr_count_1, row.get(0).get(_pr_count_1)));
            html.append(Js.setVal("#" + _pr_fee_1, row.get(0).get(_pr_fee_1)));
            html.append(Js.setVal("#" + _pr_id_1, row.get(0).get(_pr_id_1)));
            html.append(Js.setVal("#" + _pr_name_1, row.get(0).get(_pr_name_1)));
            html.append(Js.setVal("#" + _pr_sum_1, row.get(0).get(_pr_sum_1)));
            html.append(Js.setVal("#" + _pr_unit_1, row.get(0).get(_pr_unit_1)));

            html.append(Js.setVal("#" + _pr_code_2, row.get(0).get(_pr_code_2)));
            html.append(Js.setVal("#" + _pr_count_2, row.get(0).get(_pr_count_2)));
            html.append(Js.setVal("#" + _pr_fee_2, row.get(0).get(_pr_fee_2)));
            html.append(Js.setVal("#" + _pr_id_2, row.get(0).get(_pr_id_2)));
            html.append(Js.setVal("#" + _pr_name_2, row.get(0).get(_pr_name_2)));
            html.append(Js.setVal("#" + _pr_sum_2, row.get(0).get(_pr_sum_2)));
            html.append(Js.setVal("#" + _pr_unit_2, row.get(0).get(_pr_unit_2)));

            html.append(Js.setVal("#" + _pr_code_3, row.get(0).get(_pr_code_3)));
            html.append(Js.setVal("#" + _pr_count_3, row.get(0).get(_pr_count_3)));
            html.append(Js.setVal("#" + _pr_fee_3, row.get(0).get(_pr_fee_3)));
            html.append(Js.setVal("#" + _pr_id_3, row.get(0).get(_pr_id_3)));
            html.append(Js.setVal("#" + _pr_name_3, row.get(0).get(_pr_name_3)));
            html.append(Js.setVal("#" + _pr_sum_3, row.get(0).get(_pr_sum_3)));
            html.append(Js.setVal("#" + _pr_unit_3, row.get(0).get(_pr_unit_3)));

            html.append(Js.setVal("#" + _pr_code_4, row.get(0).get(_pr_code_4)));
            html.append(Js.setVal("#" + _pr_count_4, row.get(0).get(_pr_count_4)));
            html.append(Js.setVal("#" + _pr_fee_4, row.get(0).get(_pr_fee_4)));
            html.append(Js.setVal("#" + _pr_id_4, row.get(0).get(_pr_id_4)));
            html.append(Js.setVal("#" + _pr_name_4, row.get(0).get(_pr_name_4)));
            html.append(Js.setVal("#" + _pr_sum_4, row.get(0).get(_pr_sum_4)));
            html.append(Js.setVal("#" + _pr_unit_4, row.get(0).get(_pr_unit_4)));

            html.append(Js.setVal("#" + _pr_code_5, row.get(0).get(_pr_code_5)));
            html.append(Js.setVal("#" + _pr_count_5, row.get(0).get(_pr_count_5)));
            html.append(Js.setVal("#" + _pr_fee_5, row.get(0).get(_pr_fee_5)));
            html.append(Js.setVal("#" + _pr_id_5, row.get(0).get(_pr_id_5)));
            html.append(Js.setVal("#" + _pr_name_5, row.get(0).get(_pr_name_5)));
            html.append(Js.setVal("#" + _pr_sum_5, row.get(0).get(_pr_sum_5)));
            html.append(Js.setVal("#" + _pr_unit_5, row.get(0).get(_pr_unit_5)));

            html.append(Js.setVal("#" + _pr_code_6, row.get(0).get(_pr_code_6)));
            html.append(Js.setVal("#" + _pr_count_6, row.get(0).get(_pr_count_6)));
            html.append(Js.setVal("#" + _pr_fee_6, row.get(0).get(_pr_fee_6)));
            html.append(Js.setVal("#" + _pr_id_6, row.get(0).get(_pr_id_6)));
            html.append(Js.setVal("#" + _pr_name_6, row.get(0).get(_pr_name_6)));
            html.append(Js.setVal("#" + _pr_sum_6, row.get(0).get(_pr_sum_6)));
            html.append(Js.setVal("#" + _pr_unit_6, row.get(0).get(_pr_unit_6)));

            html.append(Js.setVal("#" + _pr_code_7, row.get(0).get(_pr_code_7)));
            html.append(Js.setVal("#" + _pr_count_7, row.get(0).get(_pr_count_7)));
            html.append(Js.setVal("#" + _pr_fee_7, row.get(0).get(_pr_fee_7)));
            html.append(Js.setVal("#" + _pr_id_7, row.get(0).get(_pr_id_7)));
            html.append(Js.setVal("#" + _pr_name_7, row.get(0).get(_pr_name_7)));
            html.append(Js.setVal("#" + _pr_sum_7, row.get(0).get(_pr_sum_7)));
            html.append(Js.setVal("#" + _pr_unit_7, row.get(0).get(_pr_unit_7)));

            html.append(Js.setVal("#" + _pr_code_8, row.get(0).get(_pr_code_8)));
            html.append(Js.setVal("#" + _pr_count_8, row.get(0).get(_pr_count_8)));
            html.append(Js.setVal("#" + _pr_fee_8, row.get(0).get(_pr_fee_8)));
            html.append(Js.setVal("#" + _pr_id_8, row.get(0).get(_pr_id_8)));
            html.append(Js.setVal("#" + _pr_name_8, row.get(0).get(_pr_name_8)));
            html.append(Js.setVal("#" + _pr_sum_8, row.get(0).get(_pr_sum_8)));
            html.append(Js.setVal("#" + _pr_unit_8, row.get(0).get(_pr_unit_8)));

            html.append(Js.setVal("#" + _pr_code_9, row.get(0).get(_pr_code_9)));
            html.append(Js.setVal("#" + _pr_count_9, row.get(0).get(_pr_count_9)));
            html.append(Js.setVal("#" + _pr_fee_9, row.get(0).get(_pr_fee_9)));
            html.append(Js.setVal("#" + _pr_id_9, row.get(0).get(_pr_id_9)));
            html.append(Js.setVal("#" + _pr_name_9, row.get(0).get(_pr_name_9)));
            html.append(Js.setVal("#" + _pr_sum_9, row.get(0).get(_pr_sum_9)));
            html.append(Js.setVal("#" + _pr_unit_9, row.get(0).get(_pr_unit_9)));

            html.append(Js.setVal("#" + _pr_code_10, row.get(0).get(_pr_code_10)));
            html.append(Js.setVal("#" + _pr_count_10, row.get(0).get(_pr_count_10)));
            html.append(Js.setVal("#" + _pr_fee_10, row.get(0).get(_pr_fee_10)));
            html.append(Js.setVal("#" + _pr_id_10, row.get(0).get(_pr_id_10)));
            html.append(Js.setVal("#" + _pr_name_10, row.get(0).get(_pr_name_10)));
            html.append(Js.setVal("#" + _pr_sum_10, row.get(0).get(_pr_sum_10)));
            html.append(Js.setVal("#" + _pr_unit_10, row.get(0).get(_pr_unit_10)));

            html.append(Js.setVal("#" + _pr_code_11, row.get(0).get(_pr_code_11)));
            html.append(Js.setVal("#" + _pr_count_11, row.get(0).get(_pr_count_11)));
            html.append(Js.setVal("#" + _pr_fee_11, row.get(0).get(_pr_fee_11)));
            html.append(Js.setVal("#" + _pr_id_11, row.get(0).get(_pr_id_11)));
            html.append(Js.setVal("#" + _pr_name_11, row.get(0).get(_pr_name_11)));
            html.append(Js.setVal("#" + _pr_sum_11, row.get(0).get(_pr_sum_11)));
            html.append(Js.setVal("#" + _pr_unit_11, row.get(0).get(_pr_unit_11)));

            html.append(Js.setVal("#" + _pr_code_12, row.get(0).get(_pr_code_12)));
            html.append(Js.setVal("#" + _pr_count_12, row.get(0).get(_pr_count_12)));
            html.append(Js.setVal("#" + _pr_fee_12, row.get(0).get(_pr_fee_12)));
            html.append(Js.setVal("#" + _pr_id_12, row.get(0).get(_pr_id_12)));
            html.append(Js.setVal("#" + _pr_name_12, row.get(0).get(_pr_name_12)));
            html.append(Js.setVal("#" + _pr_sum_12, row.get(0).get(_pr_sum_12)));
            html.append(Js.setVal("#" + _pr_unit_12, row.get(0).get(_pr_unit_12)));

            html.append(Js.setVal("#" + _pr_code_13, row.get(0).get(_pr_code_13)));
            html.append(Js.setVal("#" + _pr_count_13, row.get(0).get(_pr_count_13)));
            html.append(Js.setVal("#" + _pr_fee_13, row.get(0).get(_pr_fee_13)));
            html.append(Js.setVal("#" + _pr_id_13, row.get(0).get(_pr_id_13)));
            html.append(Js.setVal("#" + _pr_name_13, row.get(0).get(_pr_name_13)));
            html.append(Js.setVal("#" + _pr_sum_13, row.get(0).get(_pr_sum_13)));
            html.append(Js.setVal("#" + _pr_unit_13, row.get(0).get(_pr_unit_13)));

            html.append(Js.setVal("#" + _pr_code_14, row.get(0).get(_pr_code_14)));
            html.append(Js.setVal("#" + _pr_count_14, row.get(0).get(_pr_count_14)));
            html.append(Js.setVal("#" + _pr_fee_14, row.get(0).get(_pr_fee_14)));
            html.append(Js.setVal("#" + _pr_id_14, row.get(0).get(_pr_id_14)));
            html.append(Js.setVal("#" + _pr_name_14, row.get(0).get(_pr_name_14)));
            html.append(Js.setVal("#" + _pr_sum_14, row.get(0).get(_pr_sum_14)));
            html.append(Js.setVal("#" + _pr_unit_14, row.get(0).get(_pr_unit_14)));

            html.append(Js.setVal("#" + _pr_code_15, row.get(0).get(_pr_code_15)));
            html.append(Js.setVal("#" + _pr_count_15, row.get(0).get(_pr_count_15)));
            html.append(Js.setVal("#" + _pr_fee_15, row.get(0).get(_pr_fee_15)));
            html.append(Js.setVal("#" + _pr_id_15, row.get(0).get(_pr_id_15)));
            html.append(Js.setVal("#" + _pr_name_15, row.get(0).get(_pr_name_15)));
            html.append(Js.setVal("#" + _pr_sum_15, row.get(0).get(_pr_sum_15)));
            html.append(Js.setVal("#" + _pr_unit_15, row.get(0).get(_pr_unit_15)));

            html.append(Js.show("#trProduct1"));
            html.append(Js.show("#trProduct2"));
            html.append(Js.show("#trProduct3"));
            html.append(Js.show("#trProduct4"));
            html.append(Js.show("#trProduct5"));
            html.append(Js.show("#trProduct6"));
            html.append(Js.show("#trProduct7"));
            html.append(Js.show("#trProduct8"));
            html.append(Js.show("#trProduct9"));
            html.append(Js.show("#trProduct10"));
            html.append(Js.show("#trProduct11"));
            html.append(Js.show("#trProduct12"));
            html.append(Js.show("#trProduct13"));
            html.append(Js.show("#trProduct14"));
            html.append(Js.show("#trProduct15"));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);

            if (accEdt) {
                html2.append("<input type='button' id='edit_Factor' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_Factor", Js.jjFactor.edit()));
            }
            if (accDel) {
                html2.append("<input type='button' id='delete_Factor' value='" + lbl_delete + "' class='tahoma10'/>");
                html.append(Js.buttonMouseClick("#delete_Factor", Js.jjFactor.delete(id)));
            }

            html2.append("<input type='button' id='print_Factor' value='چاپ' class='tahoma10'/>");
            html.append(Js.buttonMouseClick("#print_Factor", Js.jjFactor.print(id)));
            return (Js.setHtml("#Factor_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
