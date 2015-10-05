/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.util.List;
import java.util.Map;
import jj.jjNumber;

/**
 *
 * @author Milad
 */
public class Chart {

    public static String getOneRecordTemplate(int darsad, Long allNumber, String lblRecord, int lengthOfNumber) {
        if (lblRecord.equals("")) {
            return "";
        }
        String temp = "<tr style='height:5px;padding: 0px'><td class='l'><table style=\"width:100%;\"><tr><td style='float:left;width:80px;font-size:11pt;font-family:Tahoma'>persent%</td><td class='pollChartLineTd' style='width:darsad%;height: 7px'></td></tr></table></td><td style='width:10px;checkLoginDisplay'>&nbsp;</td><td width='140px' style='height:5px;text-align:left;direction:ltr;width:210px;text-align:right'>allNumber. lblRecord</td></tr>";
        temp = temp.replace("darsad", (darsad == 0 ? 1 : (darsad<90)?darsad:90) + "");
//        temp = temp.replace("persent", "50");
        temp = temp.replace("persent", darsad + "");
        temp = temp.replace("allNumber", jjNumber.getFormaterLongLength(allNumber, lengthOfNumber) + "");
        temp = temp.replace("lblRecord", lblRecord);
        temp = temp.replace("whichOneRecord", "");
        temp = temp.replace("pollId", "");
        temp = temp.replace("checkLoginDisplay", "");
        temp = temp.replace("nameOfRadioGroup", "nameOfRadioGroup" + 0);
        temp = temp.replace("idOfRadioGroup", "group" + 0 + "_record" + 0);
        return temp;
    }

    public static String getOnePoll(List<Map<String, Long>> records, int lengthOfNumber) {
        StringBuffer html = new StringBuffer();
        if (records.size() > 0) {
            int all = 0;
            for (int i = 0; i < records.size(); i++) {
                Object[] toArray = records.get(i).keySet().toArray();
                if (toArray.length > 0) {
                    all += records.get(i).get(toArray[0]);
                }
            }
            all = all == 0 ? 1 : all;

            Long max = 0L;
            for (int i = 0; i < records.size(); i++) {
                Object[] toArray = records.get(i).keySet().toArray();
                if (toArray.length > 0) {
                    if (records.get(i).get(toArray[0]) > max) {
                        max = records.get(i).get(toArray[0]);
                    }
                }
            }
            for (int i = 0; i < records.size(); i++) {
                Object[] toArray = records.get(i).keySet().toArray();
                if (toArray.length > 0) {
                    all += records.get(i).get(toArray[0]);
                    Long l1 = Long.parseLong((records.get(i).get(toArray[0]) * 100) + "");
                    int l = Integer.parseInt((l1 / max) + "");
                    html.append(getOneRecordTemplate(l, records.get(i).get(toArray[0]), toArray[0].toString(), lengthOfNumber));
                }
            }
            return html.toString();
        }
        return "";
    }

    public static String getChartPack(String question, List<Map<String, Long>> records, int lengthOfNumber) {
        StringBuffer template = new StringBuffer();
        template.append("<table border='1' class='pollsForm' style='width:80%'>");
        template.append("    <tr>");
        template.append("        <td class='pollQuestionTd' style='padding:4px'>");
        template.append(question);
        template.append("        </td>");
        template.append("    </tr>");
        template.append("    <tr>");
        template.append("        <td style='padding:0px'>");
        template.append("            <table width='100%'>");
        template.append(getOnePoll(records, lengthOfNumber));
        template.append("            </table>");
        template.append("        </td>");
        template.append("    </tr>");
        template.append("</table>");
        template.append("<br/>");
        return template.toString().replace("    ", "");
    }
}
