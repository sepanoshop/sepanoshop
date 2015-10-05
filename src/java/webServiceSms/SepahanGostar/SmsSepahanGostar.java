/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServiceSms.SepahanGostar;

//import java.sql.SQLException;
//import javax.servlet.http.HttpServletRequest;
//import jj.jjDatabaseWeb;
//import org.tempuri.SendType;
import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.ServerLog;
import cms.tools.jjTools;
import cms.tools.sms;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import org.tempuri.ArrayOfInt;
import org.tempuri.ArrayOfLong;
import org.tempuri.ArrayOfString;
import org.tempuri.SendType;
import org.tempuri.SmsMode;
import org.tempuri.SmsSendWebServiceforPHP;
import org.tempuri.SmsSendWebServiceforPHPSoap;

/**
 *
 * @author Rashidi
 */
////*************** NOTE : WSDL : http://www.sepahansms.com/smsSendWebServiceforphp.asmx?wsdl
public class SmsSepahanGostar {

    public static int rul_rfs = 51;
    public static int rul_ins = 52;
    public static int rul_edt = 53;
    public static int rul_dlt = 54;
//    public static int reserved=55;

    ////// ------------- sendSMSWithRequest() ------------->
    public static String sendSMSWithRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
//    public static String sendSMSByApiKeyWithRequest() {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
            ArrayOfString text = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfLong result = new ArrayOfLong();
            sms.sender=jjTools.getParameter(request, sms._sender).toString();
            String sender = jjTools.getParameter(request, sms._sender).toString();
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {
                return Js.dialog(sms.emptyField);
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));
            System.out.println("receptor size : " + receptor.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }

            SmsSendWebServiceforPHPSoap ws;
            SmsSendWebServiceforPHP wsService = new SmsSendWebServiceforPHP();
            ws = wsService.getSmsSendWebServiceforPHPSoap();
            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
                result = ws.sendSms(sms.userName, sms.pass, sms.domain, text, receptor, sender, SendType.STATIC_TEXT, SmsMode.SAVE_IN_PHONE);
                if (!result.getLong().isEmpty()) {
                    ///////-------------DATE------->

                    jjCalendar_IR dateIR = new jjCalendar_IR();
                    int dot = dateIR.toString().indexOf("-");
                    String date = "";
                    String time = "";
                    if (dot > -1) {
                        date = dateIR.toString().substring(0, dot - 1);
                        time = dateIR.toString().substring(dot + 2, dateIR.toString().length());
                    }
                    System.out.println("DATE : " + date + " TIME : " + time);
                    int dateInt = jjCalendar_IR.getDatabaseFormat_8length(date, true);
                    ///////<-------------DATE-------
//                    String userNumber = request.getAttribute("userNumber").toString()!=null  ? request.getAttribute("userNumber").toString() : "";
                    //شماره موبایل فرد رو میگیره و از بین یوزرها انتخابش میکنه و اطلاعاتش رو برای اس ام اس ذخیره میکنه
//                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + " = " + userNumber));
                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + " = 3"));
                    String userId = (row.size() > 0) ? row.get(0).get(Access_User._id).toString() : "0";
                    String userName = (row.size() > 0) ? row.get(0).get(Access_User._name).toString() : "";
                    String userFamily = (row.size() > 0) ? row.get(0).get(Access_User._family).toString() : "";
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < result.getLong().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender);
                        if (receptor.getString().get(i).startsWith("00") || receptor.getString().get(i).startsWith("+")) {
                            smsMap.put(sms._receiver, receptor.getString().get(i));
                        } else if (receptor.getString().get(i).matches("[(+989)(+98)(00989)(0098)(09)(9)]+[0-9]{9},{0,1}")) {
                            smsMap.put(sms._receiver, "+98" + receptor.getString().get(i).substring(receptor.getString().get(i).length() - 10, receptor.getString().get(i).length()));
                        }
//                        else {
//                            smsMap.put(_receiver, "+98" + receptor.getString().get(i));
//                        }
//                        smsMap.put(_receiver, receptor.getString().get(i));
                        smsMap.put(sms._characters, text.getString().get(i).length());
                        smsMap.put(sms._status, "ارسال به مخابرات");
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, result.getLong().get(i).toString());
                        smsMap.put(sms._webService, sms.webService);
                        smsMap.put(sms._receiverId, Integer.parseInt(userId));
                        smsMap.put(sms._receiverName, userName);
                        smsMap.put(sms._receiverFamily, userFamily);
                        System.out.println(">>SMS INFO : " + smsMap);
                        if (db.insert(sms.tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
                    ///<------------- INSERT --------
                    script.append("alert('ارسال شد.');");
                    script.append(Js.setVal("#sms_receiver", ""));
                    script.append(Js.setVal("#sms_text", ""));
                    script.append(Js.setHtml("#sms_characters", ""));
                    sms.refresh2(request, db, isPost);
                    script.append(Js.hide("#smsTableShowBtn"));
                    script.append(Js.show("#showSendFormBtn"));
                    script.append(Js.hide("#formDiv"));
                    script.append(Js.show("#tableDiv"));
                    return script.toString();
                } else {
                    script.append("alert('ارسال نشد.');");
                    return script.toString();
//                    return Js.dialog(statusmessage.value);
                }
            } else {
                System.out.println("شماره گیرنده معتبر نیست");
                script.append("alert('شماره گیرنده معتبر نیست');");
                return script.toString();
//                return Js.dialog("شماره گیرنده معتبر نیست.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    ////// <------------- sendSMSWithRequest() -------------
    ////// ------------- sendSMS() ------------->
    public static String sendSMS(HttpServletRequest request, jjDatabaseWeb db,String text, String receptorStr) throws SQLException {

        try {

            StringBuilder script = new StringBuilder();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfString textArray = new ArrayOfString();
            ArrayOfLong result = new ArrayOfLong();
            String sender = sms.sender;
            System.out.println("receptorStr from parameters : " + receptorStr);
            receptor = checkNum(receptorStr);
            System.out.println("receptor size : " + receptor.getString().size());

            SmsSendWebServiceforPHPSoap ws;
            SmsSendWebServiceforPHP wsService = new SmsSendWebServiceforPHP();
            ws = wsService.getSmsSendWebServiceforPHPSoap();

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
                result = ws.sendSms(sms.userName, sms.pass, sms.domain, textArray, receptor, sender, SendType.STATIC_TEXT, SmsMode.SAVE_IN_PHONE);
                if (!result.getLong().isEmpty()) {
                    ///////-------------DATE------->

                    jjCalendar_IR dateIR = new jjCalendar_IR();
                    int dot = dateIR.toString().indexOf("-");
                    String date = "";
                    String time = "";
                    if (dot > -1) {
                        date = dateIR.toString().substring(0, dot - 1);
                        time = dateIR.toString().substring(dot + 2, dateIR.toString().length());
                    }
                    System.out.println("DATE : " + date + " TIME : " + time);
                    int dateInt = jjCalendar_IR.getDatabaseFormat_8length(date, true);
                    ///////<-------------DATE-------
//                    String userNumber = request.getAttribute("userNumber").toString();
//                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + " = " + userNumber));
//                    String userId = (row.size() > 0) ? row.get(0).get(Access_User._id).toString() : "";
//                    String userName = (row.size() > 0) ? row.get(0).get(Access_User._name).toString() : "";
//                    String userFamily = (row.size() > 0) ? row.get(0).get(Access_User._family).toString() : "";
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < result.getLong().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text);
                        smsMap.put(sms._sender, sender);
                        if (receptor.getString().get(i).startsWith("00") || receptor.getString().get(i).startsWith("+")) {
                            smsMap.put(sms._receiver, receptor.getString().get(i));
                        } else if (receptor.getString().get(i).matches("[(+989)(+98)(00989)(0098)(09)(9)]+[0-9]{9},{0,1}")) {
                            smsMap.put(sms._receiver, "+98" + receptor.getString().get(i).substring(receptor.getString().get(i).length() - 10, receptor.getString().get(i).length()));
                        }
//                        else {
//                            smsMap.put(_receiver, "+98" + receptor.getString().get(i));
//                        }
//                        smsMap.put(_receiver, receptor.getString().get(i));
                        smsMap.put(sms._characters, text.length());
                        smsMap.put(sms._status, "ارسال به مخابرات");
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, result.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
//                        smsMap.put(sms._receiverId, userId);
//                        smsMap.put(sms._receiverName, userName);
//                        smsMap.put(sms._receiverFamily, userFamily);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(sms.tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
                    ///<------------- INSERT --------

                    return "alert('ارسال شد.');";
                } else {
                    script.append("alert('ارسال نشد.');");
                    return script.toString();
//                    return Js.dialog(statusmessage.value);
                }
            } else {
                System.out.println("شماره گیرنده معتبر نیست");
                script.append("alert('شماره گیرنده معتبر نیست');");
                return script.toString();
//                return Js.dialog("شماره گیرنده معتبر نیست.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    ////// <------------- sendSMS() -------------
    ////// ----------------- check mobileNum is true ------->
    public static ArrayOfString checkNum(String MobileNumber) {
        ArrayOfString numbersList = new ArrayOfString();
        try {
            if (MobileNumber == null) {
                MobileNumber = "0";
            }
            ServerLog.Print("\nsms.checkMobilenum(" + MobileNumber + ");");
            String str = MobileNumber;
            str = str.replaceAll("\\s+", ",");
            str = str.replaceAll(",+", ",");
            str = str.replaceAll("[^0-9+]+", ",");
            String numbers[] = str.split(",");
//            List<String> numbersList = new ArrayList<String>();
            for (int i = 0; i < numbers.length; i++) {
                ServerLog.Print(numbers[i] + " maches:" + numbers[i].matches("[(+989)(+98)(00989)(0098)(09)(9)]+[0-9]{9},{0,1}"));
                if (numbers[i].matches("[(+989)(+98)(00989)(0098)(09)(9)]+[0-9]{9},{0,1}") || numbers[i].startsWith("00") || numbers[i].startsWith("+")) {//regular expression
                    numbersList.getString().add(numbers[i]);
                }
//                else if(numbers[i].startsWith("000", 1)){
//                    numbersList.getString().add(numbers[i]);
//                }
            }
            if (numbersList.getString().isEmpty()) {
                numbersList.getString().add("INVALID NUMBER");
                return numbersList;
            } else {
                System.out.println(numbersList.getString().get(0));
                return numbersList;
            }

//            List<Long> result = new ArrayList<Long>();
//            if (numbersList.size() > 50) {// اگر لیست بزرگتر از مقدار مشخصی بود در جند نوبت اس ام اس ارسال شود
//                ServerLog.Print("send to :" + numbersList.size() + " Numbers ...");
//                for (int i = 0; i < numbersList.size() / 50; i++) {
//                    ServerLog.Print("send to :" + (i * 50) + "to" + ((i + 1) * 50 - 1));
//                    result.addAll(KvNgrSMS.send(content, numbersList.subList(i * 50, (i + 1) * 50 - 1)));
////                    result.add(Long.MAX_VALUE);// برای تست رمانی که می خواهیم پیامک ارسال نشود
//                }
//                if (numbersList.size() % 50 > 0) {// برای قسمت آخر اگر تقسیم لیست به بخش های پنجاه تایی کاملا مساوی ممکن نبود
//                    ServerLog.Print("send to :" + ((numbersList.size() / 50) * 50) + "to" + (numbersList.size()));
//                    result.addAll(KvNgrSMS.send(content, numbersList.subList((numbersList.size() / 50) * 50, numbersList.size())));
////                    result.add(Long.MAX_VALUE);// برای تست رمانی که می خواهیم پیامک ارسال نشود
//                }
//            } else {// اگر اندازه لیست کمتر از مقدار مشخص بود
//                result.addAll(KvNgrSMS.send(content, numbersList));
////                result.add(Long.MAX_VALUE);// برای تست رمانی که می خواهیم پیامک ارسال نشود
//            }
//            ServerLog.Print(result.toString());
//            String resultstr = "";
//            resultstr += result.size() > 2 ? result.size() * 50 + "تعداد" : result.toString();
//            return resultstr;
//        } catch (Exception ex) {
//            return "...  SmsKaveNegar.sendOneSms(" + content + "," + MobileNumber + ");" + ex;
//        }
        } catch (Exception ex) {
            System.out.println("EXEPTION...");
            ex.printStackTrace();
            numbersList.getString().add("INVALID NUMBER");
            return numbersList;
        }

    }

////// <----------------- check mobileNum is true -------
    ////// ---------------- getStatus() ------------------->
    public static ArrayOfString getStatus(ArrayOfLong messageIdArrayOfLong) {

        ArrayOfInt getStatusResult = new ArrayOfInt();

        SmsSendWebServiceforPHPSoap ws;
        SmsSendWebServiceforPHP wsService = new SmsSendWebServiceforPHP();
        ws = wsService.getSmsSendWebServiceforPHPSoap();

        getStatusResult = ws.getDelivery(sms.userName, sms.pass, sms.domain, messageIdArrayOfLong);

        ArrayOfString smsStatusStr = new ArrayOfString();
        for (int i = 0; i < getStatusResult.getInt().size(); i++) {
            switch (Integer.parseInt(getStatusResult.getInt().get(i).toString())) {
                case 0:
                    smsStatusStr.getString().add(i, "وضعیتی دریافت نشده است");
                    break;
                case 1:
                    smsStatusStr.getString().add(i, "رسیده به گیرنده");
                    break;
                case 2:
                    smsStatusStr.getString().add(i, "نرسیده به گیرنده");
                    break;
                case 5:
                    smsStatusStr.getString().add(i, "فیلتر شده");
                    break;
                case 8:
                    smsStatusStr.getString().add(i, "ارسال به مخابرات");
                    break;
                case 16:
                    smsStatusStr.getString().add(i, "نرسیده به مخابرات");
                    break;
            }

        }
        return smsStatusStr;
    }
////// <---------------- getStatus() -------------------
    ///---------------------------- remainCreditByRequest ------------------>

    public static String remainCreditByRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            System.out.println(">>> REMAINCREDIT SEPAHANGOSTAR");
//            StringBuilder html = new StringBuilder();

            SmsSendWebServiceforPHPSoap ws;
            SmsSendWebServiceforPHP wsService = new SmsSendWebServiceforPHP();
            ws = wsService.getSmsSendWebServiceforPHPSoap();

            String remainCredit = ws.getCredit(sms.userName, sms.pass, sms.domain);
            System.out.println(remainCredit);
            return " ریـــال" + remainCredit;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- remainCreditByRequest ------------------
    public static void main(String[] args) {
//        System.out.println(sendSMSByApiKeyWithRequest());
    }
}
