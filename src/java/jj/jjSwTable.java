/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.*;

/**
 *
 * @author Milad
 */
public class jjSwTable {

    /**
     *
     * Filter table by search String
     */
    public static void setFilter(JTable table, String searchSrc) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        if (myModel.getRowCount() > 0) {
            TableRowSorter sorter = new TableRowSorter(myModel);
            RowFilter<Object, Object> regexFilter1 = RowFilter.regexFilter(".*" + searchSrc + ".*");
//        RowFilter<Object, Object> regexFilter2 = RowFilter.regexFilter(".*" + searchSrc.toUpperCase() + ".*");
//        RowFilter<Object, Object> regexFilter3 = RowFilter.regexFilter(".*" + searchSrc.toLowerCase() + ".*");
//        List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
//        filters.add(regexFilter1);
//        filters.add(regexFilter2);
//        filters.add(regexFilter3);
//        regexFilter1.orFilter(filters);
            sorter.setRowFilter(regexFilter1);
            table.setModel(myModel);
            table.setRowSorter(sorter);
        }
    }

    /**
     *
     * Filter table by search String
     */
    public static void setFilter(JTable table, String searchSrc, int wichColumnSearch) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        if (myModel.getRowCount() > 0) {
            TableRowSorter sorter = new TableRowSorter(myModel);
            if (wichColumnSearch > -1) {
                RowFilter<Object, Object> regexFilter1 = RowFilter.regexFilter(".*" + searchSrc + ".*", wichColumnSearch);
//            RowFilter<Object, Object> regexFilter2 = RowFilter.regexFilter(".*" + searchSrc.toUpperCase() + ".*", wichColumnSearch);
//            RowFilter<Object, Object> regexFilter3 = RowFilter.regexFilter(".*" + searchSrc.toLowerCase() + ".*", wichColumnSearch);
//            List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
//            filters.add(regexFilter1);
//            filters.add(regexFilter2);
//            filters.add(regexFilter3);
//            regexFilter1.orFilter(filters);
                sorter.setRowFilter(regexFilter1);
            }
            table.setModel(myModel);
            table.setRowSorter(sorter);
        }
    }

    public static void setFilterNumber(JTable table, int wichColumnSearch, int... searchInt) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        TableRowSorter sorter = new TableRowSorter(myModel);
        RowFilter<Object, Object> regexFilter1 = RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, wichColumnSearch, searchInt);
        sorter.setRowFilter(regexFilter1);
        table.setModel(myModel);
        table.setRowSorter(sorter);
    }

    /**
     *
     * Filter table by search String
     */
    public ActionListener setFilter(JTable table, JComboBox searchSrc, int wichColumnSearch) {
        wichColumnSearch1 = wichColumnSearch;
        tbl = table;
        searchCmb = searchSrc;
        searchSrc.addActionListener(CmbListener);
        return CmbListener;
    }
    private java.awt.event.ActionListener CmbListener = new java.awt.event.ActionListener() {

        public void actionPerformed(java.awt.event.ActionEvent evt) {
            setFilter(tbl, searchCmb.getSelectedItem().toString(), wichColumnSearch1);
        }
    };

    /**
     *
     * Filter table by search String
     */
    public DocumentListener setFilter(JTable table, JTextField searchSrc, int wichColumnSearch) {
        wichColumnSearch1 = wichColumnSearch;
        tbl = table;
        searchTxt = searchSrc;
        searchSrc.getDocument().addDocumentListener(listenerTextField);
        return listenerTextField;
    }
    JTable tbl;
    JTextField searchTxt;
    JComboBox searchCmb;
    int wichColumnSearch1;
    private DocumentListener listenerTextField = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            setFilter(tbl, searchTxt.getText(), wichColumnSearch1);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            setFilter(tbl, searchTxt.getText(), wichColumnSearch1);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            setFilter(tbl, searchTxt.getText(), wichColumnSearch1);
        }
    };

    /**
     *
     * Add row to table
     */
    public static void addRow(JTable table, String... fieldData) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        myModel.addRow(fieldData);
        table.setModel(myModel);
    }

    /**
     *
     * Add row to table
     */
    public static void addRow(JTable table, List<String> fieldData) {
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        String row[] = new String[fieldData.size()];
        for (int i = 0; i < row.length; i++) {
            row[i] = fieldData.get(i);
        }
        myModel.addRow(row);
        table.setModel(myModel);
    }

    /**
     *
     * Clean table and delete all rows
     */
    public static void clean(JTable table) {
        table.setVisible(false);
        table.setRowSorter(null);
        DefaultTableModel myModel = (DefaultTableModel) table.getModel();
        for (int i = myModel.getRowCount() - 1; i > -1; i--) {
            myModel.removeRow(i);
        }
        table.setModel(myModel);
        for (int i = 0; i < table.getRowCount(); i++) {
            table.remove(i);
        }
        table.setVisible(true);
    }

    /**
     *
     * sort column of table
     */
    public static void sort(JTable jTable1, int wichColumnSort) {
        DefaultTableModel myModel = (DefaultTableModel) jTable1.getModel();
        TableRowSorter sorter = new TableRowSorter(myModel);
        sorter.toggleSortOrder(wichColumnSort);
        jTable1.sorterChanged(new RowSorterEvent(sorter));
        jTable1.setModel(myModel);
        jTable1.setRowSorter(sorter);
    }

    /**
     *
     * sort column of table
     */
    public static void setSortable(JTable jTable1, boolean isSortable) {
        jTable1.setAutoCreateRowSorter(isSortable);
    }

    /**
     *
     *  Set alignment to cell of table (L_R_C is 'L' , 'R' or 'C')
     */
    public static void setCellAlignment(JTable tbl, char[] L_R_or_C, Integer[] columnWidth) {
        if (L_R_or_C.length == columnWidth.length && L_R_or_C.length == tbl.getColumnCount()
                && L_R_or_C.length == tbl.getModel().getColumnCount()) {
//            return;
            tbl.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
            tbl.setVisible(true);
            TableColumnModel tcm = tbl.getColumnModel();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
//            FormatRenderer r2 = new FormatRenderer(formatter);
            DefaultTableCellRenderer rR = new DefaultTableCellRenderer();
            DefaultTableCellRenderer rL = new DefaultTableCellRenderer();
            DefaultTableCellRenderer rC = new DefaultTableCellRenderer();
            rR.setHorizontalAlignment(SwingConstants.RIGHT);
            rL.setHorizontalAlignment(SwingConstants.LEFT);
            rC.setHorizontalAlignment(SwingConstants.CENTER);
            tbl.getTableHeader().setDefaultRenderer(rC);
            for (int i = 0; i < tbl.getColumnCount(); i++) {
                tbl.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
                if (Character.toUpperCase(L_R_or_C[i]) == 'R') {
                    tcm.getColumn(i).setCellRenderer(rR);
                } else if (Character.toUpperCase(L_R_or_C[i]) == 'L') {
                    tcm.getColumn(i).setCellRenderer(rL);
                } else {
                    tcm.getColumn(i).setCellRenderer(rC);
                }
                tbl.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
            }
            tbl.getTableHeader().setPreferredSize(new Dimension(20, 30));
            tbl.getTableHeader().setBorder(javax.swing.BorderFactory.createTitledBorder(
                    null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM));
        } else {
            System.err.println("is not same size, column Count is:" + tbl.getColumnCount() + " - L_R_or_C is:" + L_R_or_C.length + " - columnWidth is:" + columnWidth.length);
        }
    }

    /**
     *
     *  Set column title name to table
     *  Help.setColumnTitleNameToTable(jTable1,new String[]{"code","ID"});
     */
    public static void setColumnTitleName(JTable tbl, List<String> headerTitleName) {
        String titleName[] = new String[headerTitleName.size()];
        for (int i = 0; i < titleName.length; i++) {
            titleName[i] = headerTitleName.get(i);
        }
        setColumnTitleName(tbl, titleName);
    }

    public static void setColumnTitleName(JTable tbl, String... headerTitleName) {
        if (tbl.getColumnCount() == headerTitleName.length) {
//            jjDialog.alert_Blue(null, "Error", "sended array length is not equeal with table colomn count");
            TableColumnModel tcm = tbl.getColumnModel();
            for (int i = 0; i < tcm.getColumnCount(); i++) {
                tcm.getColumn(i).setHeaderValue(headerTitleName[i]);
            }
        }
    }
}
