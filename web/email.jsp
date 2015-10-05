<%-- 
    Document   : email
    Created on : Apr 16, 2013, 4:01:24 PM
    Author     : Milad
--%>

<%@page import="cms.tools.ServerLog"%>
<%@page import="cms.tools.Server"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.Transport"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try {
                if(Server.sendEmail("info@hafteghlim-ins.com", "mohammad_ms_ms@yahoo.com", "تست", "سلام تست", true, request)){
                    out.print("EmailSent");
                }else{
                    out.print("Email Not SENT");
                }
            } catch (Exception ex) {
                ServerLog.Print(ex);
            }

        %>
    </body>
</html>
