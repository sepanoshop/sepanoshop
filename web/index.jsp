<%@page import="cms.tools.ServerLog"%>
<%@page import="jj.jjFileTxt"%>
<%@page import="cms.tools.Server"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script type="text/javascript">
    window.location.replace("<%= "/001/"+Server.mainPage %>");
</script>

<%
ServerLog.Print("######################################");
ServerLog.Print("call for index.jsp");
ServerLog.Print("######################################");
    String address = request.getServletContext().getRealPath("/");
    File file = new File(address + "/" +  Server.mainPage);
//    StringBuilder result = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
    StringBuilder result = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
    
    if (!file.exists()) {%>
<%= "is not bieng such as file"%>;
<%  } else {
        int index = result.indexOf("\"sw\"");//finding div in wich id="sw"
        if (index > -1) {
            index = result.indexOf(">", index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >
            jjDatabaseWeb db;
            Server.Connect();
            db = Server.db;
            //            String content = cms.tools.Server.run(Server.getClazzes(), "Content", "sw", request, db, false);
            String content = cms.tools.Server.noAjaxRun("do=Content.sw&text="+"صفحه اصلی"+"&panel=contactDetails&", request);
            //            String content = cms.tools.Server.run(request, re, false);
            result = result.insert(index+1,cms.cms.Content.ConvertToWiki(request, content, db, false) );
        }
    }
%>
<%= result.toString()%>
