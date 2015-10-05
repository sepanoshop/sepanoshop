/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServiceSms.KaveNegar;

import cms.tools.*;
import cms.access.Access_User;
import com.kavenegar.api.ArrayOfInt;
import com.kavenegar.api.ArrayOfLong;
import com.kavenegar.api.ArrayOfString;
import com.kavenegar.api.V1;
import com.kavenegar.api.V1Soap;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Holder;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;
import static jj.jjNumber.isOdd;
//import jj.wPersianCalendarClassHelper;

/**
 *
 * @author Rashidi
 */
////*************** NOTE : WSDL : http://api.kavenegar.com/soap/v1.asmx?WSDL
public class SmsKaveNegar {

//    public static String apiKey = "77326C565155764552664D796D544773554F6D4253773D3D"; // 936...
//    public static String apiKey = "7676666E4B744A303454624A376C4F4550687A2B6D513D3D"; // 9132015...
//    public static String webService = "526D2B4E316652564E754B686E354A746A67374C53513D3D"; // 913807...
//    public static String apiKey = "526D2B4E316652564E754B686E354A746A67374C53513D3D"; // 913807...
//    public static String webService = "sepahangostar";
//    public static String userName = "";
//    public static String pass = "";
    public static String tableName = "sms";
//    public static String _id = "id";
//    public static String _text = "sms_text";
//    public static String _sender = "sms_sender";
//    public static String _receiver = "sms_receiver";
//    public static String _characters = "sms_characters";
//    public static String _status = "sms_status";
//    public static String _date = "sms_date";
//    public static String _sendTime = "sms_send_time";
//    public static String _messageID = "sms_messageID";
//    public static String _webService = "sms_webService";
//    public static String _receiverId = "sms_receiver_id";
//    public static String _receiverName = "sms_receiver_name";
//    public static String _receiverFamily = "sms_receiver_family";
//    public static String emptyField = "فیلدهای خالی را پر کنید.";
    public static int rul_rfs = 51;
    public static int rul_ins = 52;
    public static int rul_edt = 53;
    public static int rul_dlt = 54;
//    public static int reserved=55;

    ////// ------------- sendSMS() ------------->
    public static String sendSMS(HttpServletRequest request, jjDatabaseWeb db,String text, String receptorStr, boolean byApiKey) throws SQLException {

        try {
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString textArray = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            System.out.println("receptorStr : " + receptorStr);
            System.out.println("text size : " + text);
            receptor = checkNum(receptorStr);
            System.out.println("receptor size : " + receptor.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add("300002525");
            }
            for (int i = 0; i < receptor.getString().size(); i++) {
                textArray.getString().add(text);
            }
            System.out.println("sender size : " + sender.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());
            long unixdate = 0;
            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
                if (byApiKey) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                    ws.sendArrayByApikey(sms.apiKey, sender, textArray, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                } else {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                    ws.sendArrayByLoginInfo(sms.userName, sms.pass, sender, textArray, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);                    
                }
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                ////<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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

                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text);
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
////// <------------- sendSMS() -------------
////// ------------- sendSMS() with request ------------->

    public static String sendSMSWithRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }

            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            ArrayOfString text = new ArrayOfString();

            //*/*/*/*/*/*/*/
            String byApiKey = jjTools.getParameter(request, "byApiKey");
            //*/*/*/*/*/*/*/
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {
                return Js.dialog(sms.emptyField);
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));
            System.out.println("receptor size : " + receptor.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add(jjTools.getParameter(request, sms._sender));
            }
            System.out.println("sender size : " + sender.getString().size());
            sms.sender = jjTools.getParameter(request, sms._sender);
            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }
            System.out.println("sender size : " + text.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());

            long unixdate = 0;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
                if (byApiKey.equalsIgnoreCase("byApiKey")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                    ws.sendArrayByApikey(sms.apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                } else {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                    ws.sendArrayByLoginInfo(sms.userName, sms.pass, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);                    
                }
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                ////<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    ////// <------------- sendSMS() with request -------------
////----------------- sendSMSWithRequestWithUserId() ------------->
    public static String sendSMSWithRequestWithUserId(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }

            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            ArrayOfString text = new ArrayOfString();

            //*/*/*/*/*/*/*/
            String byApiKey = jjTools.getParameter(request, "byApiKey");
            //*/*/*/*/*/*/*/
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {
                return Js.dialog(sms.emptyField);
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));
            System.out.println("receptor size : " + receptor.getString().size());
            sms.sender = jjTools.getParameter(request, sms._sender);
            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add(jjTools.getParameter(request, sms._sender));
            }
            System.out.println("sender size : " + sender.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }
            System.out.println("sender size : " + text.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());

            long unixdate = 0;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
                if (byApiKey.equalsIgnoreCase("byApiKey")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                    ws.sendArrayByApikey(sms.apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                } else {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                    ws.sendArrayByLoginInfo(sms.userName, sms.pass, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);                    
                }
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                ////<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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
                    String userNumber = request.getAttribute("userNumber").toString();
                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + " = " + userNumber));
                    String userId = (row.size() > 0) ? row.get(0).get(Access_User._id).toString() : "";
                    String userName = (row.size() > 0) ? row.get(0).get(Access_User._name).toString() : "";
                    String userFamily = (row.size() > 0) ? row.get(0).get(Access_User._family).toString() : "";
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        smsMap.put(sms._receiverId, userId);
                        smsMap.put(sms._receiverName, userName);
                        smsMap.put(sms._receiverFamily, userFamily);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
////<----------------- sendSMSWithRequestWithUserId -------------

////// ------------- sendSMSByApiKey() ------------->
    public static String sendSMSByApiKey(HttpServletRequest request, jjDatabaseWeb db,String text, String receptorStr) throws SQLException {

        try {

            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfString  textArray= new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            System.out.println("receptorStr : " + receptorStr);
            System.out.println("text size : " + text);
            receptor = checkNum(receptorStr);
            System.out.println("receptor size : " + receptor.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add("300002525");
            }
            for (int i = 0; i < receptor.getString().size(); i++) {
                textArray.getString().add(text);
            }
            System.out.println("sender size : " + sender.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());
            long unixdate = 0;
            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ws.sendArrayByApikey(sms.apiKey, sender, textArray, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                ////<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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

                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text);
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    ////// <------------- sendSMSByApiKey() -------------
    ////// ------------- sendSMSByApiKeyWithRequest() ------------->

    public static String sendSMSByApiKeyWithRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            System.out.println("ارساااااال اس ام اس");
            StringBuilder script = new StringBuilder();
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
//            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            ArrayOfString text = new ArrayOfString();
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {//اگر فیلدهای متن و شماره موبایل خالی بود
                return Js.dialog(sms.emptyField);//پیغام میدهد فیلدها رو پر کنید
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));//چک کردن معتبر بودن شماره های وارد شده
            System.out.println("receptor size : " + receptor.getString().size());
            sms.sender = jjTools.getParameter(request, sms._sender);
            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add(jjTools.getParameter(request, sms._sender));
            }
            System.out.println("sender size : " + sender.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }
            System.out.println("sender size : " + text.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());

            long unixdate = 0;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {//اگر شماره های وراد شده معتبر بودن
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ws.sendArrayByApikey(sms.apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);//ارسال پیامک
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                //<------------------ get Stutus ----------
                if (status.value.equals(200)) {// اگر برای رسال پیامک خطایی رخ نداده باشد
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
                    int dateInt = jjCalendar_IR.getDatabaseFormat_8length(date, true);//تبدیل تاریخ به فرمت عددی برای ذخیره در دیتابیس
                    ///////<-------------DATE-------

                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
                        if (receptor.getString().get(i).startsWith("00") || receptor.getString().get(i).startsWith("+")) {//شماره هایی که با  00 یا + شروع میشوند
                            smsMap.put(sms._receiver, receptor.getString().get(i));
                        } else if (receptor.getString().get(i).matches("[(+989)(+98)(00989)(0098)(09)(9)]+[0-9]{9},{0,1}")) {// شماره موبایل های ایران
                            smsMap.put(sms._receiver, "+98" + receptor.getString().get(i).substring(receptor.getString().get(i).length() - 10, receptor.getString().get(i).length()));//به فرمت روبرو ذخیره می شوند : +989123456789
                        }
//                        else {
//                            smsMap.put(_receiver, "+98" + receptor.getString().get(i));
//                        }
//                        smsMap.put(_receiver, receptor.getString().get(i));
                        smsMap.put(sms._characters, text.getString().get(i).length());
                        smsMap.put(sms._status, "ارسال به مخابرات");
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
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
                    System.out.println(statusmessage.value);
                    script.append("alert('" + statusmessage.value + "');");
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

    ////// <------------- sendSMSByApiKeyWithRequest() -------------
    ////// ------------- sendSMSByApiKeyWithRequestWithUserId() ------------->
    public static String sendSMSByApiKeyWithRequestWithUserId(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
            StringBuilder script = new StringBuilder();
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
//            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            ArrayOfString text = new ArrayOfString();
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {
                return Js.dialog(sms.emptyField);
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));
            System.out.println("receptor size : " + receptor.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add(jjTools.getParameter(request, sms._sender));
            }
            System.out.println("sender size : " + sender.getString().size());
            sms.sender = jjTools.getParameter(request, sms._sender);
            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }
            System.out.println("text size : " + text.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());

            long unixdate = 0;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ws.sendArrayByApikey(sms.apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                //<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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
                    String userNumber = request.getAttribute("userNumber").toString();
                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + " = " + userNumber));
                    String userId = (row.size() > 0) ? row.get(0).get(Access_User._id).toString() : "";
                    String userName = (row.size() > 0) ? row.get(0).get(Access_User._name).toString() : "";
                    String userFamily = (row.size() > 0) ? row.get(0).get(Access_User._family).toString() : "";
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, 4);
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        smsMap.put(sms._receiverId, userId);
                        smsMap.put(sms._receiverName, userName);
                        smsMap.put(sms._receiverFamily, userFamily);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
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
                    System.out.println(statusmessage.value);
                    script.append("alert('" + statusmessage.value + "');");
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

    ////// <------------- sendSMSByApiKeyWithRequestWithUserId() -------------
    ////// ------------- sendSMSByUserInfo() ------------->
    public static String sendSMSByUserInfo(HttpServletRequest request, jjDatabaseWeb db,String text, String receptorStr) throws SQLException {

        try {

            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfString textArray = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
//            if (receptorStr.equalsIgnoreCase("")) {
//                return Js.dialog(emptyField);
//            }
            System.out.println("receptorStr : " + receptorStr);
            System.out.println("text size : " + text);
            receptor = checkNum(receptorStr);
            System.out.println("receptor size : " + receptor.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add("300002525");
            }
            System.out.println("sender size : " + sender.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            for (int i = 0; i < receptor.getString().size(); i++) {
                textArray.getString().add(text);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());
            long unixdate = 0;
            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ws.sendArrayByLoginInfo(sms.userName, sms.pass, sender, textArray, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                //<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text);
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
////// <------------- sendSMSByUserInfo() -------------
////// ------------- sendSMSByUserInfoWithRequest() ------------->

    public static String sendSMSByUserInfoWithRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }

            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            ArrayOfString text = new ArrayOfString();
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {
                return Js.dialog(sms.emptyField);
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));
            System.out.println("receptor size : " + receptor.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add(jjTools.getParameter(request, sms._sender));
            }
            System.out.println("sender size : " + sender.getString().size());
            sms.sender = jjTools.getParameter(request, sms._sender);
            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }
            System.out.println("sender size : " + text.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());

            long unixdate = 0;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ws.sendArrayByLoginInfo(sms.userName, sms.pass, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                //<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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

                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    ////// <------------- sendSMSByUserInfoWithRequest() -------------

////// ------------- sendSMSByUserInfoWithRequestWithUserId() ------------->
    public static String sendSMSByUserInfoWithRequestWithUserId(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }

            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleResult = new Holder<>();
//            Holder<ArrayOfInt> getStatusResult = new Holder<>();
            ArrayOfLong sendSimpleResultArray = new ArrayOfLong();
            ArrayOfInt getStatusResultArray = new ArrayOfInt();
            ArrayOfString sender = new ArrayOfString();
            ArrayOfString receptor = new ArrayOfString();
            ArrayOfInt msgmode = new ArrayOfInt();
            ArrayOfString text = new ArrayOfString();
            if (jjTools.getParameter(request, sms._receiver).equalsIgnoreCase("") || jjTools.getParameter(request, sms._text).equalsIgnoreCase("")) {
                return Js.dialog(sms.emptyField);
            }
            System.out.println("receptorStr from parameters : " + jjTools.getParameter(request, sms._receiver));
            receptor = checkNum(jjTools.getParameter(request, sms._receiver));
            System.out.println("receptor size : " + receptor.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add(jjTools.getParameter(request, sms._sender));
            }
            System.out.println("sender size : " + sender.getString().size());

            for (int i = 0; i < receptor.getString().size(); i++) {
                text.getString().add(jjTools.getParameter(request, sms._text));
            }
            System.out.println("sender size : " + text.getString().size());
            sms.sender = jjTools.getParameter(request, sms._sender).toString();
            for (int i = 0; i < receptor.getString().size(); i++) {
                msgmode.getInt().add(1);
            }
            System.out.println("msgmode size : " + msgmode.getInt().size());

            long unixdate = 0;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here

            if (!receptor.getString().get(0).equalsIgnoreCase("INVALID NUMBER")) {
//            ws.sendAdvance(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ws.sendArrayByLoginInfo(sms.userName, sms.pass, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
//            ws.sendSimpleByApikey(apiKey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleResult);
                ServerLog.Print(statusmessage.value);
                ServerLog.Print(status.value);
                ////------------------ get Stutus ---------->
                sendSimpleResultArray = sendSimpleResult.value;
//                getStatusResultArray = getStatusByApiKey(sendSimpleResultArray);
                //<------------------ get Stutus ----------
                if (status.value.equals(200)) {
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
                    String userNumber = request.getAttribute("userNumber").toString();
                    List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + " = " + userNumber));
                    String userId = (row.size() > 0) ? row.get(0).get(Access_User._id).toString() : "";
                    String userName = (row.size() > 0) ? row.get(0).get(Access_User._name).toString() : "";
                    String userFamily = (row.size() > 0) ? row.get(0).get(Access_User._family).toString() : "";
                    ///--------------- INSERT----- >
                    System.out.println(">>  INSERT SMS INFO TO DB : ");
                    for (int i = 0; i < receptor.getString().size(); i++) {
                        Map<String, Object> smsMap = new HashMap<String, Object>();
                        smsMap.put(sms._text, text.getString().get(i));
                        smsMap.put(sms._sender, sender.getString().get(i));
                        System.out.println("RECEPTOR : " + receptor.getString().get(i));
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
                        smsMap.put(sms._status, getStatusResultArray.getInt().get(i));
                        smsMap.put(sms._date, dateInt);
                        smsMap.put(sms._sendTime, time);
                        smsMap.put(sms._messageID, sendSimpleResultArray.getLong().get(i));
                        smsMap.put(sms._webService, sms.webService);
                        smsMap.put(sms._receiverId, userId);
                        smsMap.put(sms._receiverName, userName);
                        smsMap.put(sms._receiverFamily, userFamily);
                        System.out.println("SMS INFO : " + smsMap);
                        if (db.insert(tableName, smsMap).getRowCount() != 0) {
                            System.out.println("اطلاعات اس ام اس درج شد.");
                        } else {
                            System.out.println("اطلاعات اس ام اس درج نشد.");
                        }
                    }
///<------------- INSERT --------
//                    script.append(Js.setVal("#sms_receiver", ""));
//                    script.append(Js.setVal("#sms_text", ""));
//                    script.append(Js.setHtml("#sms_characters", ""));
//                    refresh(request, db, isPost);
//                    script.append(Js.hide("#smsTableShowBtn"));
//                    script.append(Js.show("#showSendFormBtn"));
//                    script.append(Js.hide("#formDiv"));
//                    script.append(Js.show("#tableDiv"));
//                    return script.toString();
                    return "alert('ارسال شد.');";
                } else {
                    System.out.println(statusmessage.value);
                    return "alert('" + statusmessage.value + "');";
                }
            } else {
                System.out.println("شماره معتبر نیست");
                return "alert('شماره معتبر نیست');";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    ////// <------------- sendSMSByUserInfoWithRequestWithUserId() -------------
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
////// ---------------- getStatusByApiKey() ------------------->
    public static ArrayOfString getStatusByApiKey(ArrayOfLong messageIdArrayOfLong) {
        Holder<Integer> status = new Holder<>();
        Holder<String> statusmessage = new Holder<>();
        Holder<ArrayOfInt> getStatusByApikeyResult = new Holder<>();
        ArrayOfInt getStatusByApikeyResultArray = new ArrayOfInt();//وضعیت عددی پیامک های ارسال شده
        V1Soap ws;
        V1 wsService = new V1();
        ws = wsService.getV1Soap();
        ws.getStatusByApikey(sms.apiKey, messageIdArrayOfLong, status, statusmessage, getStatusByApikeyResult);
        getStatusByApikeyResultArray = getStatusByApikeyResult.value;
        ArrayOfString smsStatusStr = new ArrayOfString();// وضعیت رشته ای  پیامک های ارسال شده!!
        for (int i = 0; i < getStatusByApikeyResultArray.getInt().size(); i++) {
            switch (Integer.parseInt(getStatusByApikeyResultArray.getInt().get(i).toString())) {
                case 1:
                    smsStatusStr.getString().add(i, "در صف ارسال");
                    break;
                case 2:
                    smsStatusStr.getString().add(i, "زمان بندی شده");
                    break;
                case 4:
                    smsStatusStr.getString().add(i, "ارسال به مخابرات");
                    break;
                case 10:
                    smsStatusStr.getString().add(i, "رسیده به گیرنده");
                    break;
                case 11:
                    smsStatusStr.getString().add(i, "نرسیده به گیرنده");
                    break;
                case 14:
                    smsStatusStr.getString().add(i, "فیلتر شده");
                    break;
                case 100:
                    smsStatusStr.getString().add(i, "شناسه ی پیامک نامعتبر است");
                    break;
            }
        }
        return smsStatusStr;
    }
////// <---------------- getStatusByApiKey() -------------------
////// ---------------- getStatusByUserInfo() ------------------->

    public static ArrayOfInt getStatusByUserInfo(ArrayOfLong messageIdArrayOfLong) {
        Holder<Integer> status = new Holder<>();
        Holder<String> statusmessage = new Holder<>();
        Holder<ArrayOfInt> getStatusByApikeyResult = new Holder<>();
//        ArrayOfInt getStatusResultArray = new ArrayOfInt();
        V1Soap ws;
        V1 wsService = new V1();
        ws = wsService.getV1Soap();
        ws.getStatusByLoginInfo(sms.userName, sms.pass, messageIdArrayOfLong, status, statusmessage, getStatusByApikeyResult);
        return getStatusByApikeyResult.value;
    }
////// <---------------- getStatusByUserInfo() -------------------
//////// ---------------- refresh() ------------------->
//
//    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
////            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
////            if (!hasAccess.equals("")) {
////                return hasAccess;
////            }
//            StringBuilder script = new StringBuilder();
////            StringBuilder html = new StringBuilder();
//            ArrayOfInt smsStaus = new ArrayOfInt();
//            ArrayOfLong smsMessageId = new ArrayOfLong();
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName));
//            for (int i = 0; i < row.size(); i++) {
//                smsMessageId.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)).longValue());
//            }
//            smsStaus = getStatusByApiKey(smsMessageId);
//            StringBuilder tableBody = new StringBuilder();
//            String text = "";
//            String classRowType = "";
//            String smsStatus = "";
//            tableBody.append("<tr class=\"tableHeader\">\n"
//                    + "<th>کد</th>\n"
//                    + "<th>نام گیرنده</th>\n"
//                    + "<th>شماره گیرنده</th>\n"
//                    + "<th> فرستنده</th>\n"
//                    + "<th>متن پیامک</th>\n"
//                    + "<th>وضعیت</th>\n"
//                    + "<th>زمان ارسال پیامک</th>\n"
//                    + "<th>  سرویس دهنده</th>\n"
//                    + "<th>حذف </th>\n"
//                    + "<th>ارسال مجدد </th>\n"
//                    + "</tr>\n");
//            for (int i = 0; i < row.size(); i++) {
//                text = (row.get(i).get(_text).toString().length() > 10) ? row.get(i).get(_text).toString().substring(0, 10) + " ..." : row.get(i).get(_text).toString();
//                classRowType = isOdd(i) ? "oddRow" : "evenRow";
//                switch (smsStaus.getInt().get(i)) {
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
////                System.out.println(" " + i + " >>> sms_text : " + row.get(i).get(_text) + " sms_sender : " + row.get(i).get(_sender) + " sms_receiver : " + row.get(i).get(_receiver) + " sms_characters : " + row.get(i).get(_characters)
////                        + " sms_status : " + smsStaus.getInt().get(i) + " sms_date : " + row.get(i).get(_date) + " sms_send_time : " + row.get(i).get(_sendTime));
//                tableBody.append("<tr id=\"smsRowTr" + row.get(i).get(_id).toString() + "\" class=\"" + classRowType + "\">\n"
//                        + "<td id=\"smsIdTd_" + row.get(i).get(_id).toString() + "\">" + row.get(i).get(_messageID).toString() + "</td>\n"
//                        + "<td id=\"smsReceiverTd_" + row.get(i).get(_id).toString() + "\">" + row.get(i).get(_receiverName).toString() + " " + row.get(i).get(_receiverFamily).toString() + "</td>\n"
//                        + "<td id=\"smsReceiverTd_" + row.get(i).get(_id).toString() + "\">" + row.get(i).get(_receiver).toString() + "</td>\n"
//                        + "<td id=\"smsSenderTd_" + row.get(i).get(_id).toString() + "\">" + row.get(i).get(_sender).toString() + "</td>\n"
//                        + "<td id=\"smsTextTd_" + row.get(i).get(_id).toString() + "\" onmouseout=\"hideBox(0," + row.get(i).get(_id).toString() + ");\" onmousemove=\"showBox(0," + row.get(i).get(_id).toString() + ");\" >" + text.toString()
//                        + "<div class=\"floatBox\" id=\"floatBox_0" + row.get(i).get(_id).toString() + "\">\n"
//                        + row.get(i).get(_text).toString()
//                        + "</div>\n"
//                        + "</td>\n"
//                        + "<td>" + smsStatus + "</td>\n"
//                        + "<td>" + row.get(i).get(_date).toString() + " - " + row.get(i).get(_sendTime).toString() + "</td>\n"
//                        + "<td id=\"smsWebServiceTd_" + row.get(i).get(_id).toString() + "\"> " + row.get(i).get(_webService).toString() + " </td>\n"
//                        + "<td><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick=\"deleteRow(" + row.get(i).get(_id) + ");\" ></td>\n"
//                        + "<td><img class=\"iconImages\" src=\"iconImages/forward.png\" onclick=\"reSendRow(" + row.get(i).get(_id) + ");\" ></td> "
//                        + " </tr>");
//            }
//            script.append(Js.setHtml("#allSmsTbl", tableBody));
//            script.append(Js.hide("#formDiv"));
//            script.append(Js.show("#tableDiv"));
//            script.append(Js.show("#showSendFormBtn"));
//            script.append(Js.hide("#smsTableShowBtn"));
//            return script.toString();
//
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//////// <---------------- refresh() -------------------
//////// ---------------- refresh2() ------------------->
//
//    public static String refresh2(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
//            if (!hasAccess.equals("")) {
//                return hasAccess;
//            }
//            StringBuilder script = new StringBuilder();
////            StringBuilder html = new StringBuilder();
//            ArrayOfInt smsStaus = new ArrayOfInt();
//            ArrayOfLong smsMessageId = new ArrayOfLong();
//            Map<String, Object> map = new HashMap<String, Object>();
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _status + " != 10 AND " + _status + " != 14"));
//            if (!row.isEmpty()) {
//                for (int i = 0; i < row.size(); i++) {
//                    smsMessageId.getLong().add(i, Long.valueOf((String) row.get(i).get(_messageID)));
//                }
//                smsStaus = getStatusByApiKey(smsMessageId);
//                for (int i = 0; i < row.size(); i++) {
//                    map.put(_status, smsStaus.getInt().get(i));
//                    if (!db.update(tableName, map, _id + " = " + row.get(i).get(_id))) {
//                        String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
//                        if (jjTools.isLangEn(request)) {
//                            errorMessage = "Edit Failed;";
//                        }
//                        return Js.dialog(errorMessage);
//                    }
//                }
//            }
//            row = jjDatabaseWeb.separateRow(db.JoinLeft(tableName, Access_User.tableName, "*", _receiverId, Access_User._id));
//            StringBuilder tableBody = new StringBuilder();
//            String text = "";
//            String classRowType = "";
//            String smsStatus = "";
//            tableBody.append("<tr class=\"tableHeader\">\n"
//                    + "<th>کد</th>\n"
//                    + "<th>نام گیرنده</th>\n"
//                    + "<th>شماره گیرنده</th>\n"
//                    + "<th> فرستنده</th>\n"
//                    + "<th>متن پیامک</th>\n"
//                    + "<th>وضعیت</th>\n"
//                    + "<th> زمان ارسال پیامک</th>\n"
//                    //                    + "<th>  سرویس دهنده</th>\n"
//                    + "<th>حذف </th>\n"
//                    + "<th>ارسال مجدد </th>\n"
//                    + "</tr>\n");
//            for (int i = 0; i < row.size(); i++) {
//                text = (row.get(i).get(_text).toString().length() > 10) ? row.get(i).get(_text).toString().substring(0, 10) + " ..." : row.get(i).get(_text).toString();
//                classRowType = isOdd(i) ? "oddRow" : "evenRow";
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
////                System.out.println(" " + i + " >>> sms_text : " + row.get(i).get(_text) + " sms_sender : " + row.get(i).get(_sender) + " sms_receiver : " + row.get(i).get(_receiver) + " sms_characters : " + row.get(i).get(_characters)
////                        + " sms_status : " + smsStaus.getInt().get(i) + " sms_date : " + row.get(i).get(_date) + " sms_send_time : " + row.get(i).get(_sendTime));
//                tableBody.append("<tr id=\"smsRowTr" + row.get(i).get(_messageID).toString() + "\" class=\"" + classRowType + "\">\n"
//                        + "<td>" + row.get(i).get(_messageID).toString() + "</td>\n"
//                        + "<td>" + row.get(i).get(Access_User._name).toString() + " " + row.get(i).get(Access_User._family).toString() + "</td>\n"
//                        + "<td>" + row.get(i).get(_receiver).toString() + "</td>\n"
//                        + "<td>" + row.get(i).get(_sender).toString() + "</td>\n"
//                        + "<td onmouseout=\"hideBox(0," + i + ");\" onmousemove=\"showBox(0," + i + ");\" >" + text.toString()
//                        + "<div class=\"floatBox\" id=\"floatBox_0" + i + "\">\n"
//                        + row.get(i).get(_text).toString()
//                        + "</div>\n"
//                        + "</td>\n"
//                        + "<td>" + smsStatus + "</td>\n"
//                        + "<td>" + jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " - " + row.get(i).get(_sendTime).toString() + "</td>\n"
//                        //                        + "<td id=\"smsWebServiceTd_" + row.get(i).get(_id).toString() + "\"> " + row.get(i).get(_webService).toString() + " </td>\n"
//                        + "<td><img class=\"iconImages\" src=\"iconImages/Bin-512.png\" onclick=\"deleteRow(" + row.get(i).get(_messageID) + ");\" ></td>\n"
//                        + "<td><img class=\"iconImages\" src=\"iconImages/forward.png\" onclick=\"reSendRow(" + row.get(i).get(_messageID) + ");\" ></td> "
//                        + " </tr>");
//            }
//            script.append(Js.setHtml("#allSmsTbl", tableBody));
//            script.append(Js.hide("#formDiv"));
//            script.append(Js.show("#tableDiv"));
//            script.append(Js.show("#showSendFormBtn"));
//            script.append(Js.hide("#smsTableShowBtn"));
//            return script.toString();
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//////// <---------------- refresh2() -------------------
//    ////// ---------------- select() ------------------->
//
//    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
////            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
////            if (!hasAccess.equals("")) {
////                return hasAccess;
////            }
//            System.out.println(">>> select");
//            StringBuilder script = new StringBuilder();
//            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _messageID + " = " + jjTools.getParameter(request, _id)));
//            if (row.isEmpty()) {
//                System.out.println("این رکورد وجود ندارد.");
//                return Js.dialog("این رکورد وجود ندارد.");
//            }
//
//            script.append(Js.setVal(_receiver, row.get(0).get(_receiver)));
//            script.append(Js.setVal(_text, row.get(0).get(_text)));
//            script.append(Js.setHtml(_characters, row.get(0).get(_characters)));
////            System.out.println(script.toString());
////            request.setAttribute("panel_webServices",_webService);
////            request.setAttribute("panel_numbers",_sender);
////            script.append(smsSetting.selectWebService(request, db, isPost));
//            System.out.println(script.toString());
//            return script.toString();
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//    ////// <---------------- select() -------------------
//////// ---------------- delete() ------------------->
//
//    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
////            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
////            if (!hasAccess.equals("")) {
////                return hasAccess;
////            }
//            StringBuilder script = new StringBuilder();
//            String id = jjTools.getParameter(request, _id);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
//            if (!db.delete(tableName, _messageID + " = " + id)) {
//                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
//                if (jjTools.isLangEn(request)) {
//                    errorMessage = "Delete Fail;";
//                }
//                return Js.dialog(errorMessage);
//            }
////            script.append(Js.hide("#smsRowTr" + id));
//            script.append("$('#smsRowTr" + id + "').remove();");
//            return script.toString();
////            return refresh(request, db, isPost);
//
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//////// <---------------- delete() -------------------
    ///---------------------------- remainCreditByRequest ------------------>

    public static String remainCreditByRequest(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            System.out.println(">>> REMAINCREDIT KAVENEGAR");
//            StringBuilder html = new StringBuilder();
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<Long> remainCredit = new Holder<>();
            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            ws.remainCreditByApiKey(sms.apiKey, status, statusmessage, remainCredit);
//            html.append(Js.setHtml("#remain_lbl", " ریـــال" + Long.toString(remainCreditByRequest.value)));
            System.out.println(Long.toString(remainCredit.value));
//            System.out.println(html.toString());
            return " ریـــال" + Long.toString(remainCredit.value);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- remainCreditByRequest ------------------
    ///---------------------------- remainCreditByParameters ------------------>
    public static String remainCredit(String apiKey) throws Exception {
        try {
            System.out.println(">>> REMAINCREDIT KAVENEGAR");
//            StringBuilder html = new StringBuilder();
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<Long> remainCredit = new Holder<>();
            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            ws.remainCreditByApiKey(apiKey, status, statusmessage, remainCredit);
//            html.append(Js.setHtml("#remain_lbl", " ریـــال" + Long.toString(remainCreditByRequest.value)));
            System.out.println(Long.toString(remainCredit.value));
//            System.out.println(html.toString());
            return " ریـــال" + Long.toString(remainCredit.value);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    ///<---------------------------- remainCreditByParameters ------------------
////// ---------------- main ------------------->
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        jjDatabaseWeb db = jjDatabaseWeb.getInstance();
//        ArrayOfString text = new ArrayOfString();
//        text.getString().add("سلام");
////        text.getString().add("سلام");
//        String receptor = "+989132015239,9138139196,09367821444";
////        sendSMSByApiKeyWithRequest(text, receptor);
////        System.out.println(jjDatabaseQuery.joinLeft(tableName, Access_User.tableName, "id,sms_text,sms_sender,sms_receiver,sms_characters,sms_status,sms_date,sms_send_time,sms_messageID,sms_webService,user_name,user_family", _receiverId, Access_User._id));
////        List<Map<String, Object>> row = db.separateRow(db.JoinLeft(tableName, Access_User.tableName, "sms_text,sms_sender,sms_receiver,sms_characters,sms_status,sms_date,sms_send_time,sms_messageID,sms_webService,user_name,user_family", _receiverId, Access_User._id));
////        for (int i = 0; i < row.size(); i++) {
////            System.out.println(row.get(i).get(_text) + "  " + row.get(i).get(_sender) + "     " + row.get(i).get(_receiver) + "   " + row.get(i).get(_characters) + "     " + row.get(i).get(_status) + "     " + row.get(i).get(_date) + "   " + row.get(i).get(_sendTime) + "   " + row.get(i).get(_messageID) + "  " + row.get(i).get("user_name") + "     " + row.get(i).get("user_family"));
////        }
//        ArrayOfString num = checkNum(receptor);
//        for (int i = 0; i < num.getString().size(); i++) {
//            System.out.println(num.getString().get(i));
//        }
    }
}
