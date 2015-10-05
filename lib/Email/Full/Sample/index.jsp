<%-- 
    Document   : index
    Created on : Oct 8, 2012, 11:12:32 PM
    Author     : Milad
--%>

<%@page import="newpackage.SendMail"%>
<%@page import="newpackage.Email"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% // Email.send("Arvin@Arvin.com", "Milad.jamalzadeh@yahoo.com", "سلام", "<a onclick=\" window.open('www.Google.com', '_blank', 'width='+300 +' ,left='+(screen.availWidth/2-(300/2)) +' ,top=' +(screen.availHeight/2-(300/2)) +' ,height='      +300+',status,resizable');\"> dddd </a>");%>
        <%

            //Use the below code within the class to invoke the send mail function.
            SendMail mailObj = new SendMail();
            
            mailObj.createEntity("milad.jamalzadeh@yahoo.com", "msg_header", "msg_data", "localhost", "Arvin@Arvin.com", "", "", true);
//            mailObj.createEntity("milad.jamalzadeh@yahoo.com", "msg_header", "msg_data", "smtp_server", "Arvin@Arvin.com", "smtp_user", "smtp_pwd", true);
            mailObj.addSubj("This is a test");
            mailObj.addMsg("Hello World<BR> <a href='www.google.com'>aaaa</a>");
            mailObj.addMsg("Hello World<BR> <a href='www.google.com'>aaaa2</a>");
//        mailObj.embedImage("./lib/logo.bmp");
//        mailObj.attachFiles("./lib/logo.bmp", "logo.bmp", "LOGO");
//        mailObj.addMsg("Can you see the embedded image<BR>");
            mailObj.addMsg("Thank you<BR>");
            mailObj.SendMail_HTML();
            ServerLog.Print("Mail Sent");
            out.print("oooooooooooooooooooooo");
        %>
    </body>
</html>
