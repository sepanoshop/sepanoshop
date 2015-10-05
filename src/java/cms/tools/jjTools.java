/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

//import cms.ticeAccess.Tice_User;
import cms.access.Access_User;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.*;
import jj.jjCalendar_IR;
import jj.jjFileTxt;
import jj.jjNumber;
import jj.jjWebURL;

/**
 *
 * @author Arvin2
 */
public class jjTools {

    public static PrintWriter getWriterUTF8(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            OutputStream os = response.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            return new PrintWriter(osw);
//            return response.getWriter();
        } catch (Exception e) {
            Server.ErrorHandler(e);
        }
        return null;
    }
    public static String today = "";
//    public static StringBuffer beingSeassionToday = new StringBuffer();
    public static int todayUserCount;

    public static String setLang(HttpServletRequest request) {
        try {
            jjCalendar_IR ir = new jjCalendar_IR();
            String nowDateAndTime = ir.toString();
            String nowDate = ir.getViewFormat_10length();
            if (today.equals("")) {
                today = nowDate;
            } else if (!today.equals(nowDate)) {
                today = nowDate;
//                beingSeassionToday = new StringBuffer();
            }

            if (!jjTools.getParameter(request, "myLang").equals("")) {
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                String sesID = request.getSession(true).getId();
//                if (beingSeassionToday.toString().indexOf("#" + sesID + "#") == -1) {
//                    beingSeassionToday.append("#" + sesID + "#");
//                }
                
//                >>>> By mohammad commented................................................
//                if (!ipAddress.equals("0:0:0:0:0:0:0:1")) {
//                    File folderAddress = new File(request.getServletContext().getRealPath("/upload"));
//                    File visitor = new File(folderAddress.getAbsolutePath() + "/visitor.txt");
//                    String ipData = jjWebURL.getWebSiteSource("http://api.ipinfodb.com/v3/ip-city/?key=20b96dca8b9a5d37b0355e9461c66e76eed30a2274422fa6213d9de6ffb2b34e&ip=" + ipAddress);
//                    if (ipData.indexOf(";") > -1) {
//                        String[] split = ipData.split(";");
//                        ipData = split.length > 5 ? ipAddress + " - " + split[4] + " - " + split[5] + " - " + split[6] : "Unknow IP";
//                    }
//                    jjFileTxt.write(visitor, nowDateAndTime + " - " + ipData, true, true);
//                }
//                .......................................
                
                jjTools.setSessionAttribute(request, "myLang", jjTools.getParameter(request, "myLang").toLowerCase());
            }
            if (!jjTools.getSessionAttribute(request, "myLang").equals("fa")) {
                if (!jjTools.getSessionAttribute(request, "myLang").equals("en")) {
                    if (!jjTools.getSessionAttribute(request, "myLang").equals("ar")) {
                        jjTools.setSessionAttribute(request, "myLang", Server.defaultLang);
                    }
                }
            }
//            return Js.setHtml("#jjTodayUserCount", (beingSeassionToday.toString().split("##").length + 250));
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static void setPoolStatus(HttpServletRequest request) {
        try {
            if (!jjTools.getParameter(request, "IS_POLL_NEED_LOGIN").equals("")) {
                Server.pollNeedLogin = jjTools.getParameter(request, "IS_POLL_NEED_LOGIN").toLowerCase().equals("true");
            }
            if (!jjTools.getParameter(request, "IS_POLL_SOW_AFTER_VOTE").equals("")) {
                Server.pollShowAfterVote = !jjTools.getParameter(request, "IS_POLL_SOW_AFTER_VOTE").toLowerCase().equals("false");
            }
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
        }
    }

    public static void setLangFa(HttpServletRequest request) {
        jjTools.setSessionAttribute(request, "myLang", "fa");
    }

    public static void setLangEn(HttpServletRequest request) {
        jjTools.setSessionAttribute(request, "myLang", "en");
    }

    public static boolean isLangFa(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("fa");
    }

    public static boolean isLangEn(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("en");
    }

    public static boolean isLangAr(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("ar");
    }

    public static String getLangDir(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("en") ? "ltr" : "rtl";
    }

    public static String getLangAlign(HttpServletRequest request) {
        return jjTools.getSessionAttribute(request, "myLang").equals("fa") ? "right" : "left";
    }

    public static boolean isUserLogin(HttpServletRequest request) {
        return (jjNumber.isDigit(jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase()).toString()));
    }


    public static int getSeassionUserId(HttpServletRequest request) {
        return jjNumber.isDigit(jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase()).toString()) ? Integer.parseInt(jjTools.getSessionAttribute(request, "#" + Access_User._id.toUpperCase()).toString()) : 0;
    }
    public static String getParameter(HttpServletRequest request, String name) {
        Object mode = request.getParameter(name);
        if (mode == null) {
            return "";
        } else {
            try {
//                return mode.toString().equals("null") ? "" :(new String(request.getParameter(name).getBytes(), "UTF-8"));
                return mode.toString().equals("null") ? "" : mode.toString();
            } catch (Exception ex) {
                Server.ErrorHandler(ex);
            }
        }
        return "";
    }

    public static void ShowAllParameter(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        ServerLog.Print("-----------ShowAllParameter-----------");
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
           ServerLog.Print(key + "=" + request.getParameter(key));
        }
        ServerLog.Print("--------------_________---------------");
    }
    public static void ShowAllAttribute(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        ServerLog.Print("-----------ShowAllAttribute-----------");
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
           ServerLog.Print(key + "=" + request.getAttribute(key));
        }
        ServerLog.Print("--------------_________---------------");
    }

    public static String getSessionAttribute(HttpServletRequest request, String name) {
        try {
            Object mode = request.getSession().getAttribute(name);
            if (mode == null) {
                return "";
            } else {
                return mode.toString().equals("null") ? "" : mode.toString();
            }
        } catch (Exception e) {
            return Server.ErrorHandler(e);
        }
    }

    public static void setSessionAttribute(HttpServletRequest request, String name, Object value) {
        try {
            ServerLog.Print(">> In session '" + name + "' = '" + value + "'");
            request.getSession(true).setAttribute(name, value);
        } catch (Exception e) {
            Server.ErrorHandler(e);
        }
    }

    public static String getAtLeastChar(String html) {
        return html.equals("") ? "&nbsp;" : html;
    }

    public static String getAtLeastChar(Object html) {
        return getAtLeastChar(html.toString());
    }
}
