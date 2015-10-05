<%-- 
    Document   : sms
    Created on : Jun 19, 2013, 1:45:25 PM
    Author     : Milad
--%>

<%@page import="jj.jjCollections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sms.GetPrice"%>
<%@page import="sms.SendSmsResponse"%>
<%@page import="sms.SendSmsWebService"%>
<%@page import="sms.ArrayOfString"%>
<%@page import="sms.SMSService"%>
<%@page import="sms.SendSms"%>
<%@page import="sms.SendSmsWebServiceSoap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            ArrayOfString numbers = new ArrayOfString();
            ArrayOfString texts = new ArrayOfString();
            texts.getString().add("aaaa");
            numbers.getString().add("09132686053");
//            texts.getString().add("aaaa");
//
            SendSms s = new SendSms();

            s.setDomainName("sepahansms");
            s.setMobileNumber(numbers);
            s.setPassword("m136407");
            s.setSenderNumber("3000703003");
            s.setSmsCount(1);
            s.setSmsText(texts);
            s.setUserName("miladjj");
////            out.print();
////            new SMSService(texts, numbers).run();
//            GetPrice g = new GetPrice();
//            out.print(g.setDomainName());
//            out.print(g.getMobileNum());
//            out.print(g.getMobileNumberCount());
//            out.print(g.getPassword());
//            out.print(g.getSmsText());
//            out.print(g.getUserName());
//            SMSService.sendSMS(texts, numbers);
//            SendSmsResponse ss = new SendSmsResponse();
            out.print(SMSService.sendSMS(texts, numbers));
        %>
    </body>
</html>
