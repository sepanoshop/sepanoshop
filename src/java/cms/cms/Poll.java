package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.util.ArrayList;
import jj.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Poll {

    public static String tableName = "poll";
    public static String _id = "id";
    public static String _qu = "poll_qu";
    public static String _an1 = "poll_an1";
    public static String _an2 = "poll_an2";
    public static String _an3 = "poll_an3";
    public static String _an4 = "poll_an4";
    public static String _an5 = "poll_an5";
    public static String _an6 = "poll_an6";
    public static String _an7 = "poll_an7";
    public static String _an8 = "poll_an8";
    public static String _an9 = "poll_an9";
    public static String _an10 = "poll_an10";
    public static String _an11 = "poll_an11";
    public static String _lang = "poll_lang";
    public static String _parent = "poll_parent";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 6;
    public static int rul_ins = 7;
    public static int rul_edt = 8;
    public static int rul_dlt = 9;
    public static int rul_lng = 10;

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _parent + "=0"));
            List<Map<String, Object>> allRow = jjDatabase.separateRow(db.SelectAll(Category_Poll.tableName));
            html.append("<table id='refreshPoll' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='25%'>سوال</th>");
            html.append("<th width='10%'>جواب 1</th>");
            html.append("<th width='10%'>جواب 2</th>");
            html.append("<th width='10%'>جواب 3</th>");
            html.append("<th width='10%'>جواب 4</th>");
            html.append("<th width='10%'>جواب 5</th>");
            html.append("<th width='10%'>جواب 6</th>");
            html.append("<th width='5%'>کل</th>");
            html.append("<th width='5%'>عملیات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {

                int rowCount1 = getCount(allRow, row.get(i).get(_id).toString(), "1");
                int rowCount2 = getCount(allRow, row.get(i).get(_id).toString(), "2");
                int rowCount3 = getCount(allRow, row.get(i).get(_id).toString(), "3");
                int rowCount4 = getCount(allRow, row.get(i).get(_id).toString(), "4");
                int rowCount5 = getCount(allRow, row.get(i).get(_id).toString(), "5");
                int rowCount6 = getCount(allRow, row.get(i).get(_id).toString(), "6");
//                int rowCount2 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + row.get(i).get(_id) + " AND " + Category_Poll._answer + "=2").getRowCount();
//                int rowCount3 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + row.get(i).get(_id) + " AND " + Category_Poll._answer + "=3").getRowCount();
//                int rowCount4 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + row.get(i).get(_id) + " AND " + Category_Poll._answer + "=4").getRowCount();
//                int rowCount5 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + row.get(i).get(_id) + " AND " + Category_Poll._answer + "=5").getRowCount();
//                int rowCount6 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + row.get(i).get(_id) + " AND " + Category_Poll._answer + "=6").getRowCount();
                html.append("<tr  onclick='cmsPoll.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='r'>" + (row.get(i).get(_qu).toString()) + "</td>");
                html.append("<td class='r'>" + rowCount1 + ". " + (row.get(i).get(_an1).toString()) + "</td>");
                html.append("<td class='r'>" + rowCount2 + ". " + (row.get(i).get(_an2).toString()) + "</td>");
                html.append("<td class='r'>" + rowCount3 + ". " + (row.get(i).get(_an3).toString()) + "</td>");
                html.append("<td class='r'>" + rowCount4 + ". " + (row.get(i).get(_an4).toString()) + "</td>");
                html.append("<td class='r'>" + rowCount5 + ". " + (row.get(i).get(_an5).toString()) + "</td>");
                html.append("<td class='r'>" + rowCount6 + ". " + (row.get(i).get(_an6).toString()) + "</td>");
                html.append("<td class='c'>" + (rowCount1 + rowCount2 + rowCount3 + rowCount4 + rowCount5 + rowCount6) + "</td>");
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
                panel = "swPollTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshPoll", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "13" : "", "لیست نظرسنجی ها");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static int getCount(List<Map<String, Object>> allRow, String which, String answer) throws Exception {
        int count = 0;
        for (int i = 0; i < allRow.size(); i++) {
            if (allRow.get(i).get(Category_Poll._which).equals(which)) {
                if (allRow.get(i).get(Category_Poll._answer).equals(answer)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            panel = panel.equals("") ? "sw" : panel;
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName));
            for (int i = 0; i < row.size(); i++) {
                html.append(getOnePoll(request, db, Integer.parseInt(row.get(i).get(_id).toString())));
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOnePoll(HttpServletRequest request, jjDatabaseWeb db, int pollId) throws Exception {
        boolean userIsLogin = jjTools.isUserLogin(request);
        List<Map<String, Object>> row = new ArrayList<Map<String, Object>>();
        if (jjTools.isLangFa(request)) {
            row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + pollId));
        } else if (jjTools.isLangEn(request)) {
            row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + pollId + " AND " + _lang + "=2"));
        } else if (jjTools.isLangAr(request)) {
            row = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + pollId + " AND " + _lang + "=3"));
        }
        if (userIsLogin) {
            if (db.Select(Category_Poll.tableName, Category_Poll._user_id + "=" + jjTools.getSeassionUserId(request)
                    + " AND " + Category_Poll._which + "=" + pollId).getRowCount() > 0) {
                return "";
            };
        }
        StringBuffer html = new StringBuffer();
        if (row.size() > 0) {
            int rowCount1 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + pollId + " AND " + Category_Poll._answer + "=1").getRowCount();
            int rowCount2 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + pollId + " AND " + Category_Poll._answer + "=2").getRowCount();
            int rowCount3 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + pollId + " AND " + Category_Poll._answer + "=3").getRowCount();
            int rowCount4 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + pollId + " AND " + Category_Poll._answer + "=4").getRowCount();
            int rowCount5 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + pollId + " AND " + Category_Poll._answer + "=5").getRowCount();
            int rowCount6 = db.Select(Category_Poll.tableName, Category_Poll._which + "=" + pollId + " AND " + Category_Poll._answer + "=6").getRowCount();
            int all = rowCount1 + rowCount2 + rowCount3 + rowCount4 + rowCount5 + rowCount6;
            all = all == 0 ? 1 : all;
            boolean userVoteBeforNow = false;
            if (Server.pollNeedLogin && userIsLogin) {
                userVoteBeforNow = db.Select(Category_Poll.tableName, Category_Poll._user_id + "='" + jjTools.getSeassionUserId(request) + "' AND " + Category_Poll._which + "=" + pollId).getRowCount() > 0;
            }
            if (!Server.pollNeedLogin) {
                String sesID = request.getSession(true).getId();
                userVoteBeforNow = db.Select(Category_Poll.tableName, Category_Poll._user_id + "='" + sesID + "' AND " + Category_Poll._which + "=" + pollId).getRowCount() > 0;
            }

            String record1 = getOneRecordTemplate(request, (rowCount1 * 100 / all), rowCount1, row.get(0).get(_an1).toString(), 1, pollId, userVoteBeforNow, userIsLogin);
            String record2 = getOneRecordTemplate(request, (rowCount2 * 100 / all), rowCount2, row.get(0).get(_an2).toString(), 2, pollId, userVoteBeforNow, userIsLogin);
            String record3 = getOneRecordTemplate(request, (rowCount3 * 100 / all), rowCount3, row.get(0).get(_an3).toString(), 3, pollId, userVoteBeforNow, userIsLogin);
            String record4 = getOneRecordTemplate(request, (rowCount4 * 100 / all), rowCount4, row.get(0).get(_an4).toString(), 4, pollId, userVoteBeforNow, userIsLogin);
            String record5 = getOneRecordTemplate(request, (rowCount5 * 100 / all), rowCount5, row.get(0).get(_an5).toString(), 5, pollId, userVoteBeforNow, userIsLogin);
            String record6 = getOneRecordTemplate(request, (rowCount6 * 100 / all), rowCount6, row.get(0).get(_an6).toString(), 6, pollId, userVoteBeforNow, userIsLogin);


            html.append(getOnePoll(request, row.get(0).get(_qu).toString(), pollId, record1, record2, record3, record4, record5, record6));
            return html.toString();
        }
        return "";
    }

    public static String getOneRecordTemplate(HttpServletRequest request, int darsad, int allNumber, String lblRecord, int whichOneRecord, int pollId, boolean userVoteBeforNow, boolean userIsLogin) throws Exception {
        if (lblRecord.equals("")) {
            return "";
        }
        String temp = "<tr style='height:5px;padding: 0px'><td class='l'><table style=\"width:100%;\"><tr><td>persent%</td><td class='pollChartLineTd' style='width:darsad%;'></td></tr></table></td><td style='width:10px;checkLoginDisplay'><input type='radio' name='nameOfRadioGroup' id='idOfRadioGroup' onclick='swVoteToPoll(pollId,whichOneRecord);'/></td>"
                + "<td width='100px' style='height:5px;direction:" + jjTools.getLangDir(request) + "'>allNumber. lblRecord</td></tr>";
        temp = temp.replace("darsad", (darsad == 0 ? 1 : darsad) + "");
        temp = temp.replace("persent", darsad + "");
        temp = temp.replace("allNumber", jjNumber.getFormaterIntegerLength(allNumber, 3) + "");
        temp = temp.replace("lblRecord", lblRecord);
        temp = temp.replace("whichOneRecord", whichOneRecord + "");
        temp = temp.replace("pollId", pollId + "");
        temp = temp.replace("checkLoginDisplay", userVoteBeforNow ? "display:none;" : "");
        temp = temp.replace("nameOfRadioGroup", "nameOfRadioGroup" + pollId);
        temp = temp.replace("idOfRadioGroup", "group" + pollId + "_record" + whichOneRecord);
        return temp;
    }

    public static String getOnePoll(HttpServletRequest request, String question, int pollId, String record1, String record2, String record3, String record4, String record5, String record6) throws Exception {
        StringBuffer template = new StringBuffer();
        template.append("<table id='poll_id_is_" + pollId + "' border='1' class='pollsForm'>");
        template.append("    <tr>");
        template.append("        <td class='pollQuestionTd' style='padding:4px;direction:" + jjTools.getLangDir(request) + "'>");
        template.append(question);
        template.append("        </td>");
        template.append("    </tr>");
        template.append("    <tr>");
        template.append("        <td style='padding:0px'>");
        template.append("            <table width='100%'>");
        template.append(record1);
        template.append(record2);
        template.append(record3);
        template.append(record4);
        template.append(record5);
        template.append(record6);
        template.append("            </table>");
        template.append("        </td>");
        template.append("    </tr>");
        template.append("</table>");
        template.append("<br/>");
        return template.toString().replace("    ", "");
    }

    public static String voteToPoll(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String pollId = jjTools.getParameter(request, "pollId");
            String whichRecord = jjTools.getParameter(request, "whichRecord");
            if (jjTools.isUserLogin(request)) {
                if (jjNumber.isDigit(pollId)) {
                    if (jjNumber.isDigit(whichRecord)) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(Category_Poll._answer, Integer.parseInt(whichRecord));
                        map.put(Category_Poll._user_id, jjTools.getSeassionUserId(request) + "");
                        map.put(Category_Poll._which, Integer.parseInt(pollId));
                        db.insert(Category_Poll.tableName, map);
                        String poll = getOnePoll(request, db, Integer.parseInt(pollId));
                        if (Server.pollShowAfterVote) {
                            ServerLog.Print(">>>" + poll);
                            ServerLog.Print(">>>" + poll.length());
                            ServerLog.Print(">>>" + poll.lastIndexOf("</table>"));
                            if (poll.length() > poll.lastIndexOf("</table>")) {
                                if (poll.indexOf("</table>") > -1) {
                                    return Js.setHtml("#poll_id_is_" + pollId, poll.substring(poll.indexOf(">") + 1, poll.lastIndexOf("</table>")));
                                }
                            }
                            
                            return Js.dialog("ساختار جدول رای صحیح نمی باشد");
                        } else {
                            return Js.hide("#poll_id_is_" + pollId);
                        }
                    } else {
                        return Js.dialog("انتخاب رای صحیح نمی باشد.");
                    }
                } else {
//                    return Js.dialog("");
                    return Js.dialog("کد رای گیری صحیح نمی باشد");
                }
            } else if (Server.pollNeedLogin) {
                return "sw('$login');\n";
            } else {
                if (jjNumber.isDigit(whichRecord)) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(Category_Poll._answer, Integer.parseInt(whichRecord));
                    map.put(Category_Poll._user_id, request.getSession(true).getId());
                    map.put(Category_Poll._which, Integer.parseInt(pollId));
                    db.insert(Category_Poll.tableName, map);
                    String poll = getOnePoll(request, db, Integer.parseInt(pollId));
                    if (Server.pollShowAfterVote) {
                        if (poll.length() > poll.lastIndexOf("</table>")) {
                            if (poll.indexOf("</table>") > -1) {
                                return Js.setHtml("#poll_id_is_" + pollId, poll.substring(poll.indexOf(">") + 1, poll.lastIndexOf("</table>")));
                            }
                        }
                        return Js.dialog("ساختار جدول رای صحیح نمی باشد");
                    } else {
                        return Js.hide("#poll_id_is_" + pollId);
                    }
//                    return Js.setHtml("#poll_id_is_" + pollId, poll.substring(poll.indexOf(">") + 1, poll.lastIndexOf("</table>")));
                } else {
                    return Js.dialog("انتخاب رای صحیح نمی باشد.");
                }
            }
//            return Js.dialog("جهت شرکت در رای گیری ها باید ابتدا لاگین نمایید.");
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            return Js.dialog("در سیستم رای گیری مشکلی پیش آمده است.");
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Poll_button", "<input type=\"button\" id=\"insert_Poll_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Poll_new", Js.jjPoll.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_qu, jjTools.getParameter(request, _qu));
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            map.put(_an1, jjTools.getParameter(request, _an1));
            map.put(_an1, jjTools.getParameter(request, _an1));
            map.put(_an2, jjTools.getParameter(request, _an2));
            map.put(_an3, jjTools.getParameter(request, _an3));
            map.put(_an4, jjTools.getParameter(request, _an4));
            map.put(_an5, jjTools.getParameter(request, _an5));
            map.put(_an6, jjTools.getParameter(request, _an6));

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPoll.refresh();
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_qu, jjTools.getParameter(request, _qu));
            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);
            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            map.put(_an1, jjTools.getParameter(request, _an1));
            map.put(_an1, jjTools.getParameter(request, _an1));
            map.put(_an2, jjTools.getParameter(request, _an2));
            map.put(_an3, jjTools.getParameter(request, _an3));
            map.put(_an4, jjTools.getParameter(request, _an4));
            map.put(_an5, jjTools.getParameter(request, _an5));
            map.put(_an6, jjTools.getParameter(request, _an6));

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjPoll.refresh();
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
            if (id.equals("1")) {
                String errorMessageId2 = "شما اجازه حذف این رکورد را ندارید.";
                if (jjTools.isLangEn(request)) {
                    errorMessageId2 = "You can't delete this record.";
                }
                return Js.dialog(errorMessageId2);
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            db.delete(tableName, _parent + "=" + id);
            db.delete(Category_Poll.tableName, Category_Poll._which + "=" + id);
            return Js.jjPoll.refresh();
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
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _qu, row.get(0).get(_qu)));
            html.append(Js.setVal("#" + _an1, row.get(0).get(_an1)));
            html.append(Js.setVal("#" + _an2, row.get(0).get(_an2)));
            html.append(Js.setVal("#" + _an3, row.get(0).get(_an3)));
            html.append(Js.setVal("#" + _an4, row.get(0).get(_an4)));
            html.append(Js.setVal("#" + _an5, row.get(0).get(_an5)));
            html.append(Js.setVal("#" + _an6, row.get(0).get(_an6)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type='button' id='edit_Poll' value='" + lbl_edit + "' class='tahoma10'>");
                html.append(Js.buttonMouseClick("#edit_Poll", Js.jjPoll.edit()));
            }
            if (accDel && !row.get(0).get(_parent).toString().equals("1")) {
                if (!id.equals("1")) {
                    if (!row.get(0).get(_parent).toString().equals("1")) {
                        html2.append("<input type='button' id='delete_Poll' value='" + lbl_delete + "' class='tahoma10'  />");
                        html.append(Js.buttonMouseClick("#delete_Poll", Js.jjPoll.delete(id)));
                    }
                }
            }
            if (acclng) {
                List<Map<String, Object>> rows = jjDatabase.separateRow(db.Select(tableName));
                String parent = getParent(rows, id);
                if (!parent.equals("")) {
                    html2.append("<input type='button' id='edit_en_Poll' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_Poll", Js.jjPoll.select(parent)));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_Poll' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_Poll", Js.jjPoll.addEN(id)));
                    }
                }

                List<Map<String, Object>> rowAr = jjDatabase.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_Poll1' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_Poll1", Js.jjPoll.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_Poll1' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_Poll1", Js.jjPoll.addAr(id)));
                    }
                }
            }

            return (Js.setHtml("#Poll_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getParent(List<Map<String, Object>> rows, String id) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).get(_parent).toString().equals(id)) {
                    return rows.get(i).get(_id).toString();
                }
            }
            return "";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            html.append(Js.setVal("#" + _qu, row.get(0).get(_qu)));
            html.append(Js.setVal("#" + _an1, row.get(0).get(_an1)));
            html.append(Js.setVal("#" + _an2, row.get(0).get(_an2)));
            html.append(Js.setVal("#" + _an3, row.get(0).get(_an3)));
            html.append(Js.setVal("#" + _an4, row.get(0).get(_an4)));
            html.append(Js.setVal("#" + _an5, row.get(0).get(_an5)));
            html.append(Js.setVal("#" + _an6, row.get(0).get(_an6)));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Poll_button", "<input type=\"button\" id=\"insert_Poll_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html2.append(Js.buttonMouseClick("#insert_Poll_new", Js.jjPoll.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
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
            html.append(Js.setVal("#" + _qu, row.get(0).get(_qu)));
            html.append(Js.setVal("#" + _an1, row.get(0).get(_an1)));
            html.append(Js.setVal("#" + _an2, row.get(0).get(_an2)));
            html.append(Js.setVal("#" + _an3, row.get(0).get(_an3)));
            html.append(Js.setVal("#" + _an4, row.get(0).get(_an4)));
            html.append(Js.setVal("#" + _an5, row.get(0).get(_an5)));
            html.append(Js.setVal("#" + _an6, row.get(0).get(_an6)));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html2.append(Js.setHtml("#Poll_button", "<input type='button' id='insert_Poll_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html2.append(Js.buttonMouseClick("#insert_Poll_new_ar", Js.jjPoll.insert()));
            }
            return html.toString() + html2.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
