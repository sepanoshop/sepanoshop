<%-- 
    Document   : reportSms
    Created on : Jun 30, 2013, 2:36:40 PM
    Author     : Milad
--%>

<%@page import="java.util.List"%>
<%@page import="raygansms.Sms"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMS Report</title>
    </head>
    <body>
        <%

            List<String> reciveMessage = Sms.getReciveMessage();
            for (int i = 0; i < reciveMessage.size(); i++) {
                out.print(">>" + reciveMessage.get(i));
            }
        %>
    </body>
</html>
