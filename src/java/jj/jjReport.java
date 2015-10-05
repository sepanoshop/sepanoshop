/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.*;
/**
 *
 * @author Milad
 */
public class jjReport {

    /**
     *
     * get Report from JasperReport
     * ReportPath sample is "/r/report.jasper"
     * sample Map<String, Object> params = new HashMap();
     */
    public static void getReport(String ReportPath, Map<String, Object> params, String A4_or_A5) {
        try {
            DefaultTableModel tm = new DefaultTableModel(new String[][]{{"1"}}, new String[]{"1"});
            InputStream is;
            if (ReportPath.startsWith("/")) {
                is = new jjReport().getClass().getResourceAsStream(ReportPath);
            } else {
                is = new FileInputStream(new File(ReportPath));
            }

//            net.sf.jasperreports.engine.data.JRTableModelDataSource tmds = new net.sf.jasperreports.engine.data.JRTableModelDataSource(tm);
//            net.sf.jasperreports.engine.JasperPrint jp;
//            jp = net.sf.jasperreports.engine.JasperFillManager.fillReport(is, params, tmds);
//            if (A4_or_A5.toUpperCase().equals("A4")) {
//                jp.setPageHeight(840);
//                jp.setPageWidth(615);
//            } else {
//                jp.setPageHeight(570);
//                jp.setPageWidth(420);
//            }
//            net.sf.jasperreports.view.JasperViewer.viewReport(jp, false);
            Runtime.getRuntime().gc();
//            JasperDesign jrDesign = JRXmlLoader.load(is);
//            JasperReport jrReport = JasperCompileManager.compileReport(jrDesign);
//            JasperPrint jrPrint = JasperFillManager.fillReport(jrReport, params);
//            net.sf.jasperreports.view.JasperViewer.viewReport(jrPrint);
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getReport()", ex.getMessage());
        }
    }
}
