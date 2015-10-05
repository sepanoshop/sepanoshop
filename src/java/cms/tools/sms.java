/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import webServiceSms.SepahanGostar.SmsSepahanGostar;
import cms.access.Access_User;
//import com.kavenegar.api.ArrayOfInt;
//import com.kavenegar.api.ArrayOfLong;
//import com.kavenegar.api.ArrayOfString;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import static jj.jjNumber.isOdd;
import webServiceSms.KaveNegar.SmsKaveNegar;
//import org.tempuri.ArrayOfInt;
//import org.tempuri.ArrayOfLong;

/**
 *
 * @author Rashidi
 */
public class sms {

    public static String apiKey = "";
    public static String webService = "sepahangostar.com";
    public static String userName = "mrsalesi";
    public static String pass = "1234";
    public static String domain = "sepahansms";
    public static String sender = "30008672";

    public static String tableName = "sms";
    public static String _id = "id";
    public static String _text = "sms_text";
    public static String _sender = "sms_sender";
    public static String _receiver = "sms_receiver";
    public static String _characters = "sms_characters";
    public static String _status = "sms_status";
    public static String _date = "sms_date";
    public static String _sendTime = "sms_send_time";
    public static String _messageID = "sms_messageID";
    public static String _webService = "sms_webService";
    public static String _receiverId = "sms_receiver_id";
    public static String _receiverName = "sms_receiver_name";
    public static String _receiverFamily = "sms_receiver_family";
    public static String emptyField = "فیلدهای خالی را پر کنید.";
    public static int rul_rfs = 51;
    public static int rul_ins = 52;
    public static int rul_edt = 53;
    public static int rul_dlt = 54;
//    public static int reserved=55;

////// ---------------- sendSMSWithRequest() ------------------->
    public static String sendSMSWithRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
            String result="";
            if (webService.equalsIgnoreCase("kavenegar.com")) {//اگر وب سرویس پیامک کاوه نگار باشه کلاس مربوط به کاوه نگار فراخوانده میشه
                result=SmsKaveNegar.sendSMSByApiKeyWithRequest(request, db, isPost);
            } else if (webService.equalsIgnoreCase("sepahangostar.com")) {//اگر وب سرویس پیامک سپاهان گستر باشه کلاس مربوط به سپاهان گستر فراخوانده میشه
                result=SmsSepahanGostar.sendSMSWithRequest(request, db, isPost);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
        ////// <---------------- sendSMSWithRequest -------------------
    ////// ---------------- sendSMS() ------------------->
    public static String sendSMS(HttpServletRequest request,jjDatabaseWeb db,String text,String receptorStr) throws SQLException {
        try {
            String result="";
            if (webService.equalsIgnoreCase("kavenegar.com")) {//اگر وب سرویس پیامک کاوه نگار باشه کلاس مربوط به کاوه نگار فراخوانده میشه
                result=SmsKaveNegar.sendSMSByApiKey(request,db,text,receptorStr);
            } else if (webService.equalsIgnoreCase("sepahangostar.com")) {//اگر وب سرویس پیامک سپاهان گستر باشه کلاس مربوط به سپاهان گستر فراخوانده میشه
                result=SmsSepahanGostar.sendSMS(request,db,text,receptorStr);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    ////// <---------------- sendSMS -------------------

    ////// ---------------- refresh2() ------------------->
    public static String refresh2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }

            StringBuilder script = new StringBuilder();
//            StringBuilder html = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            //انتخاب پیامک هایی که وضعیت ان ها مشخص نیست هنوز در مخابرات هستند یا در صف ارسال هستند
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _status + " != 'رسیده به گیرنده' AND " + _status + " != 'نرسیده به گیرنده' AND " + _status + " != 'فیلتر شده' "));
//            com.kavenegar.api.ArrayOfInt smsStausKvNg = new com.kavenegar.api.ArrayOfInt();
            com.kavenegar.api.ArrayOfLong smsMessageIdKvNg = new com.kavenegar.api.ArrayOfLong();
            com.kavenegar.api.ArrayOfString smsStausSpKvNg = new com.kavenegar.api.ArrayOfString();
//            org.tempuri.ArrayOfInt smsStausSpGs = new org.tempuri.ArrayOfInt();
            org.tempuri.ArrayOfLong smsMessageIdSpGs = new org.tempuri.ArrayOfLong();
            org.tempuri.ArrayOfString smsStausSpGs = new org.tempuri.ArrayOfString();
            if (!row.isEmpty()) {
                if (webService.equalsIgnoreCase("kavenegar.com")) {//اگر وب سرویس پیامک کاوه نگار بود
                    for (int i = 0; i < row.size(); i++) {
                        smsMessageIdKvNg.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)));//لیست کردن شناسه های پیامک ها که قبلن از کاوه نگار دریافت و ذخیره شدند
                    }
                    smsStausSpKvNg = SmsKaveNegar.getStatusByApiKey(smsMessageIdKvNg);//دریافت وضعیت پیامک ها از وب سرویس
                } else if (webService.equalsIgnoreCase("sepahangostar.com")) {//اگر وب سرویس پیامک سپاهان گستر بود
                    for (int i = 0; i < row.size(); i++) {
                        smsMessageIdSpGs.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)));//لیست کردن شناسه های پیامک ها که قبلن از سپاهان گستر دریافت و ذخیره شدند
                    }
                    smsStausSpGs = SmsSepahanGostar.getStatus(smsMessageIdSpGs);//دریافت وضعیت پیامک ها از وب سرویس
                    
                }

                for (int i = 0; i < row.size(); i++) { //آپدیت کردن وضعیت های دریافتی برای هر کدام از پیامک های انتخاب شده
                    
                    if (webService.equalsIgnoreCase("kavenegar.com")) {//اگر وب سرویس پیامک کاوه نگار بود
                         System.out.println("STATUS : "+smsStausSpKvNg.getString().get(i));
                        map.put(_status, smsStausSpKvNg.getString().get(i));
                    } else if (webService.equalsIgnoreCase("sepahangostar.com")) {//اگر وب سرویس پیامک سپاهان گستر بود
                        System.out.println("STATUS : "+smsStausSpGs.getString().get(i));
                        map.put(_status, smsStausSpGs.getString().get(i));
                    }
                    if (!db.update(tableName, map, _id + " = " + row.get(i).get(_id))) {
                        String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                        if (jjTools.isLangEn(request)) {
                            errorMessage = "Edit Failed;";
                        }
                        return Js.dialog(errorMessage);
                    }
                }
            }
            row = jjDatabaseWeb.separateRow(db.JoinLeft(tableName, Access_User.tableName, "*", _receiverId, Access_User._id));//انتخاب تمام پیامک ها و یوزر مربوط به آن پیامک ها
            StringBuilder tableBody = new StringBuilder();
            String text = "";
            String classRowType = "";
//            String smsStatus = "";
            tableBody.append("<tr class=\"tableHeader\">\n"
                    + "<th>کد</th>\n"
                    + "<th>نام گیرنده</th>\n"
                    + "<th>شماره گیرنده</th>\n"
                    + "<th> فرستنده</th>\n"
                    + "<th>متن پیامک</th>\n"
                    + "<th>وضعیت</th>\n"
                    + "<th> زمان ارسال پیامک</th>\n"
                    + "<th>حذف </th>\n"
                    + "<th>ارسال مجدد </th>\n"
                    + "</tr>\n");
            for (int i = 0; i < row.size(); i++) {
                //اگر متن پیامک بیشتر از ده کاراکتر بود
                text = (row.get(i).get(_text).toString().length() > 10) ? row.get(i).get(_text).toString().substring(0, 10) + " ..." : row.get(i).get(_text).toString();
                classRowType = isOdd(i) ? "oddRow" : "evenRow";
//                switch (Integer.parseInt(row.get(i).get(_status).toString())) {
//                    case 1:
//                        smsStatus = "در صف ارسال";
//                        break;
//                    case 2:
//                        smsStatus = "زمان بندی شده";
//                        break;
//                    case 4:
//                        smsStatus = "ارسال به مخابرات";
//                        break;
//                    case 10:
//                        smsStatus = "رسیده به گیرنده";
//                        break;
//                    case 11:
//                        smsStatus = "نرسیده به گیرنده";
//                        break;
//                    case 14:
//                        smsStatus = "فیلتر شده";
//                        break;
//                    case 100:
//                        smsStatus = "شناسه ی پیامک نامعتبر است";
//                        break;
//                }
//                System.out.println(" " + i + " >>> sms_text : " + row.get(i).get(_text) + " sms_sender : " + row.get(i).get(_sender) + " sms_receiver : " + row.get(i).get(_receiver) + " sms_characters : " + row.get(i).get(_characters)
//                        + " sms_status : " + smsStaus.getInt().get(i) + " sms_date : " + row.get(i).get(_date) + " sms_send_time : " + row.get(i).get(_sendTime));
                tableBody.append("<tr id=\"smsRowTr" + row.get(i).get(_messageID).toString() + "\" class=\"" + classRowType + "\">\n"
                        + "<td>" + row.get(i).get(_messageID).toString() + "</td>\n"
                        + "<td>" + row.get(i).get(Access_User._name).toString() + " " + row.get(i).get(Access_User._family).toString() + "</td>\n"
                        + "<td>" + row.get(i).get(_receiver).toString() + "</td>\n"
                        + "<td>" + row.get(i).get(_sender).toString() + "</td>\n"
                        + "<td onmouseout=\"hideBox(0," + i + ");\" onmousemove=\"showBox(0," + i + ");\" >" + text.toString()
                        + "<div class=\"floatBox\" id=\"floatBox_0" + i + "\">\n"
                        + row.get(i).get(_text).toString()
                        + "</div>\n"
                        + "</td>\n"
                        + "<td>" +  row.get(i).get(_status).toString() + "</td>\n"
                        + "<td>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " - " + row.get(i).get(_sendTime).toString() + "</td>\n"
                        //                        + "<td id=\"smsWebServiceTd_" + row.get(i).get(_id).toString() + "\"> " + row.get(i).get(_webService).toString() + " </td>\n"
                        + "<td><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick=\"deleteRow(" + row.get(i).get(_messageID) + ");\" ></td>\n"
                        + "<td><img class=\"iconImages\" src=\"iconImages/forward.png\" onclick=\"reSendRow(" + row.get(i).get(_messageID) + ");\" ></td> "
                        + " </tr>");
            }
            script.append(Js.setHtml("#allSmsTbl", tableBody));
            script.append(Js.hide("#formDiv"));
            script.append(Js.show("#tableDiv"));
            script.append(Js.show("#showSendFormBtn"));
            script.append(Js.hide("#smsTableShowBtn"));
            return script.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
////// <---------------- refresh2() -------------------
    ////// ---------------- delete() ------------------->

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.delete(tableName, _messageID + " = " + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
//            script.append(Js.hide("#smsRowTr" + id));
            script.append("$('#smsRowTr" + id + "').remove();");
            return script.toString();
//            return refresh(request, db, isPost);

        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
////// <---------------- delete() -------------------
    ////// ---------------- select() ------------------->

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            System.out.println(">>> select");
            StringBuilder script = new StringBuilder();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _messageID + " = " + jjTools.getParameter(request, _id)));
            if (row.isEmpty()) {
                System.out.println("این رکورد وجود ندارد.");
                return Js.dialog("این رکورد وجود ندارد.");
            }

            script.append(Js.setVal(_receiver, row.get(0).get(_receiver)));
            script.append(Js.setVal(_text, row.get(0).get(_text)));
            script.append(Js.setHtml(_characters, row.get(0).get(_characters)));
//            System.out.println(script.toString());
//            request.setAttribute("panel_webServices",_webService);
//            request.setAttribute("panel_numbers",_sender);
//            script.append(smsSetting.selectWebService(request, db, isPost));
            System.out.println(script.toString());
            return script.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    ////// <---------------- select() -------------------

}
