package jj;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class jjSwFormManage {

    private List<String> vData = new ArrayList<String>();
    private List<Component> vComponentList = new ArrayList<Component>();
    private List<Character> vAlighnmentList = new ArrayList<Character>();
    private List<String> vTableColumnTitleList = new ArrayList<String>();
    private List<Integer> vWidthInTableList = new ArrayList<Integer>();
    private List<Integer> vTableCulomnNumberList = new ArrayList<Integer>();
    private File savePath = new File("");
    private JTable jTable1 = new JTable();
    private boolean isSearchStatus = true;
    private JFrame frame;
    private String lastSelectID = "";
    private int idFieldNumber = 0;
    private Component insert;
    private Component edit;
    private Component clean;
    private Component delete;

    //      Sample
//      ggg g = new ggg();
//      jjSwFormManage n = new jjSwFormManage(g, jjOsInfo.getProgramAddress() + "\\a.txt", g.jTable1, g.insert, g.update, g.delete, g.clean);
//      n.addComponent(g.txtID, "a", 'c', 10, true);
//      n.addComponent(g.txt1, "a", 'c', 30, false);
//      n.addComponent(g.txt2, "b", 'c', 30, false);
//      n.addComponent(g.cmb1, "c", 'c', 230, false);
//      g.setVisible(true);
//      n.render();
//                    
    public jjSwFormManage(JFrame fram, String savePath, JTable jtable, Component insert, Component edit, Component delete, Component clean) {
        this.savePath = new File(savePath);
        this.savePath.mkdirs();
        this.insert = insert;
        this.edit = edit;
        this.delete = delete;
        this.clean = clean;
        jTable1 = jtable;
        jjSwForm.setIcon(fram, "/Images/1.png");
        this.frame = fram;

        // insert ==============================================================
        if (insert.getClass().toString().equals("class javax.swing.JLabel")) {
            javax.swing.JLabel tf = (javax.swing.JLabel) insert;
            tf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            tf.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    insert();
                }
            });
            tf.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    insert();
                }
            });
        } else if (insert.getClass().toString().equals("class javax.swing.JButton")) {
            javax.swing.JButton tf = (javax.swing.JButton) insert;
            tf.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    insert();
                }
            });
        }

        // edit ==============================================================
        if (edit.getClass().toString().equals("class javax.swing.JLabel")) {
            javax.swing.JLabel tf = (javax.swing.JLabel) edit;
            tf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            tf.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Edit();
                }
            });
            tf.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    Edit();
                }
            });
        } else if (edit.getClass().toString().equals("class javax.swing.JButton")) {
            javax.swing.JButton tf = (javax.swing.JButton) edit;
            tf.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Edit();
                }
            });
        }
        // delete ==============================================================
        if (delete.getClass().toString().equals("class javax.swing.JLabel")) {
            javax.swing.JLabel tf = (javax.swing.JLabel) delete;
            tf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            tf.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    delete();
                }
            });
            tf.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    delete();
                }
            });
        } else if (delete.getClass().toString().equals("class javax.swing.JButton")) {
            javax.swing.JButton tf = (javax.swing.JButton) delete;
            tf.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    delete();
                }
            });
        }
        // clean ==============================================================
        if (clean.getClass().toString().equals("class javax.swing.JLabel")) {
            javax.swing.JLabel tf = (javax.swing.JLabel) clean;
            tf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            tf.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    clean();
                }
            });
            tf.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    clean();
                }
            });
        } else if (clean.getClass().toString().equals("class javax.swing.JButton")) {
            javax.swing.JButton tf = (javax.swing.JButton) clean;
            tf.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    clean();
                }
            });
        }
    }

    public void addComponent(Component component, String title, char alighnment, int widthInTable, boolean isID) {
        if (isID) {
            idFieldNumber = vTableCulomnNumberList.size();
            component.setEnabled(false);
        }
        vComponentList.add(component);
        vTableCulomnNumberList.add(vTableCulomnNumberList.size());
        vTableColumnTitleList.add(title);
        vAlighnmentList.add(alighnment);
        vWidthInTableList.add(widthInTable);
    }

    public void render() {
        try {
            addDocumentListenerTable();
//            jjSwForm.doManageNextFocus(vComponentList);
            fillData();
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void addDocumentListenerTable() {
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClick();
            }
        });
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableClick();
            }
        });
    }

    private void addDocumentListener() {
        for (int i = 0; i < vComponentList.size(); i++) {
            if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                tf.getDocument().addDocumentListener(listenerTextField);
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JComboBox")) {
                javax.swing.JComboBox tf = (javax.swing.JComboBox) vComponentList.get(i);
                tf.addActionListener(listenerComboBox);
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JFormattedTextField")) {
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextArea")) {
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JRadioButton")) {
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JCheckBox")) {
                javax.swing.JCheckBox tf = (javax.swing.JCheckBox) vComponentList.get(i);
                tf.addActionListener(listenerComboBox);
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JButton")) {
            }
        }

    }

    private void removeDocumentListener() {
        for (int i = 0; i < vComponentList.size(); i++) {
            if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                tf.getDocument().removeDocumentListener(listenerTextField);
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JComboBox")) {
                javax.swing.JComboBox tf = (javax.swing.JComboBox) vComponentList.get(i);
                tf.removeActionListener(listenerComboBox);
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JFormattedTextField")) {
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextArea")) {
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JRadioButton")) {
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JCheckBox")) {
                javax.swing.JCheckBox tf = (javax.swing.JCheckBox) vComponentList.get(i);
                tf.removeActionListener(listenerComboBox);
            } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JButton")) {
            }
        }
    }

    private void insert() {
        try {
            javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(idFieldNumber);
            tf.setText(jjDatabase.getCounterFromFile(savePath.getParent() + "\\" + savePath.getName() + "_Counter.dll") + "");
            vData.add(pack());
            try {
                save();
            } catch (Exception ex) {
                Logger.getLogger(jjSwFormManage.class.getName()).log(Level.SEVERE, null, ex);
            }
            fillData();
            isSearchStatus = true;
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void save() {
        try {
            if (!savePath.isDirectory()) {
                jjFileTxt.writeLine(savePath, vData, false, false);
                return;
            } else {
                File[] listFiles = savePath.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    jjFile.delete(listFiles[i]);

                }
                for (int i = 0; i < vData.size(); i++) {
                    jjFileTxt.write(savePath.getAbsolutePath() + "\\" + savePath.getName() + "_" + UnPack(vData.get(i))[idFieldNumber] + ".dll", vData.get(i), false, false);
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void read() {
        try {
            if (!savePath.isDirectory()) {
                vData = jjFileTxt.readLine(savePath);
            } else {
                File[] listFiles = savePath.listFiles();
                vData.clear();
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].getName().startsWith(savePath.getName() + "_")) {
                        vData.add(jjFileTxt.read(listFiles[i]));
                    }

                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void afterAct(DocumentEvent evt) {
        try {
            for (int i = 0; i < vComponentList.size(); i++) {
                if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                    javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                    if (tf.getDocument() == evt.getDocument()) {
                        jjSwTable.setFilter(jTable1, tf.getText(), vTableCulomnNumberList.get(i));
                    }
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private String pack() {
        String all = "";
        try {
            int last = vComponentList.size() - 1;
            for (int i = 0; i < vComponentList.size(); i++) {
                if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                    javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                    all += (tf.getText().equals("") ? "-" : tf.getText()) + (i == last ? "" : "#");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JComboBox")) {
                    javax.swing.JComboBox tf = (javax.swing.JComboBox) vComponentList.get(i);
                    all += (tf.getSelectedItem().toString().equals("") ? "-" : tf.getSelectedItem().toString()) + (i == last ? "" : "#");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JFormattedTextField")) {
                    javax.swing.JFormattedTextField tf = (javax.swing.JFormattedTextField) vComponentList.get(i);
                    all += (tf.getText().equals("") ? "-" : tf.getText()) + (i == last ? "" : "#");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextArea")) {
                    javax.swing.JTextArea tf = (javax.swing.JTextArea) vComponentList.get(i);
                    all += (tf.getText().equals("") ? "-" : tf.getText()) + (i == last ? "" : "#");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JRadioButton")) {
                    javax.swing.JRadioButton tf = (javax.swing.JRadioButton) vComponentList.get(i);
                    all += (tf.getText().equals("") ? "-" : tf.getText()) + (i == last ? "" : "#");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JCheckBox")) {
                    javax.swing.JCheckBox tf = (javax.swing.JCheckBox) vComponentList.get(i);
                    all += (tf.isSelected() ? "بله" : "خیر") + (i == last ? "" : "#");
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
        return all;
    }

    private static String[] UnPack(String all) {
        String[] split = all.split("#");
        try {
            for (int i = 0; i < split.length; i++) {
                if (split[i].equals("-")) {
                    split[i] = "";
                };

            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
        return split;
    }

    private void Edit() {
        try {
            if (lastSelectID.equals("")) {
                jjDialog.alert_Blue(frame, "", "ابتدا سطر مورد نظر را از جدول انتخاب نمایید.");
                return;
            }
            if (jjDialog.YES_NO(frame, "", "آیا از ویرایش این مورد مطمئن می باشید؟")) {
                for (int i = 0; i < vData.size(); i++) {
                    if (UnPack(vData.get(i))[idFieldNumber].equals(lastSelectID)) {
                        vData.set(i, pack());
                        save();
                    }
                }
                isSearchStatus = true;
                fillData();
//                jjSwTable.saveRow(idFieldNumber, lastSelectID);
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
        lastSelectID = "";
    }

    private void delete() {
        try {
            if (lastSelectID.equals("")) {
                jjDialog.alert_Blue(frame, "", "ابتدا سطر مورد نظر را از جدول انتخاب نمایید.");
                return;
            }
            if (jjDialog.YES_NO(frame, "", "آیا از حذف این مورد مطمئن می باشید؟")) {
                for (int i = 0; i < vData.size(); i++) {
                    if (UnPack(vData.get(i))[idFieldNumber].equals(lastSelectID)) {
                        vData.remove(i);
                        save();
                    }
                }
                lastSelectID = "";
                fillData();

            }
            isSearchStatus = true;
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void tableClick() {
        try {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow < 0) {
                return;
            }
            delete.setEnabled(true);
            edit.setEnabled(true);
            removeDocumentListener();
            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                String value = String.valueOf(jTable1.getValueAt(selectedRow, i));
                value = (value == null ? "" : value);
                if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                    javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                    tf.setText(value);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JComboBox")) {
                    javax.swing.JComboBox tf = (javax.swing.JComboBox) vComponentList.get(i);
                    tf.setSelectedItem(value);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JFormattedTextField")) {
                    javax.swing.JFormattedTextField tf = (javax.swing.JFormattedTextField) vComponentList.get(i);
                    tf.setText(value);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextArea")) {
                    javax.swing.JTextArea tf = (javax.swing.JTextArea) vComponentList.get(i);
                    tf.setText(value);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JRadioButton")) {
//                    javax.swing.JRadioButton tf = (javax.swing.JRadioButton) vComponentList.get(i);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JCheckBox")) {
                    javax.swing.JCheckBox tf = (javax.swing.JCheckBox) vComponentList.get(i);
                    tf.setSelected(value.equals("بله"));
                }
            }
            lastSelectID = jTable1.getValueAt(selectedRow, idFieldNumber) + "";
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void fillData() {
        try {
            jTable1.setVisible(false);
            vData.clear();
            read();
            jjSwTable.clean(jTable1);
            char[] align = new char[vAlighnmentList.size()];
            for (int i = 0; i < vAlighnmentList.size(); i++) {
                align[i] = vAlighnmentList.get(i);
            }
            Integer[] width = new Integer[vWidthInTableList.size()];
            for (int i = 0; i < vWidthInTableList.size(); i++) {
                width[i] = vWidthInTableList.get(i);
            }
            String[] columnName = new String[vTableColumnTitleList.size()];
            for (int i = 0; i < vTableColumnTitleList.size(); i++) {
                columnName[i] = vTableColumnTitleList.get(i);
            }
            DefaultTableModel tbl = (DefaultTableModel) jTable1.getModel();
            tbl.setColumnIdentifiers(columnName);
            jjSwTable.clean(jTable1);
            for (int i = 0; i < vData.size(); i++) {
                String[] UnPack = UnPack(vData.get(i));
                jjSwTable.addRow(jTable1, UnPack);
            }
            jjSwTable.setCellAlignment(jTable1, align, width);
            clean();
            jTable1.setVisible(true);
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    private void close(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    private void clean() {
        try {
            removeDocumentListener();
            for (int i = 0; i < vComponentList.size(); i++) {
                if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                    javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                    tf.setText("");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JComboBox")) {
                    javax.swing.JComboBox tf = (javax.swing.JComboBox) vComponentList.get(i);
                    tf.setSelectedIndex(0);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JFormattedTextField")) {
                    javax.swing.JFormattedTextField tf = (javax.swing.JFormattedTextField) vComponentList.get(i);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextArea")) {
                    javax.swing.JTextArea tf = (javax.swing.JTextArea) vComponentList.get(i);
                    tf.setText("");
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JRadioButton")) {
                    javax.swing.JRadioButton tf = (javax.swing.JRadioButton) vComponentList.get(i);
                } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JCheckBox")) {
                    javax.swing.JCheckBox tf = (javax.swing.JCheckBox) vComponentList.get(i);
                    tf.setSelected(false);
                }
            }
            addDocumentListener();
            jjSwTable.setFilter(jTable1, "", 0);
            delete.setEnabled(false);
            edit.setEnabled(false);
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }
    private DocumentListener listenerTextField = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            afterAct(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            afterAct(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            afterAct(e);
        }
    };
    private java.awt.event.ActionListener listenerComboBox = new java.awt.event.ActionListener() {

        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (isSearchStatus) {
                for (int i = 0; i < vComponentList.size(); i++) {
                    if (vComponentList.get(i) == evt.getSource()) {
                        if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextField")) {
                            javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                            jjSwTable.setFilter(jTable1, tf.getText(), vTableCulomnNumberList.get(i));
                        } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JComboBox")) {
                            javax.swing.JComboBox tf = (javax.swing.JComboBox) vComponentList.get(i);
                            jjSwTable.setFilter(jTable1, tf.getSelectedItem().toString(), vTableCulomnNumberList.get(i));
                        } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JFormattedTextField")) {
                        } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JTextArea")) {
                            javax.swing.JTextField tf = (javax.swing.JTextField) vComponentList.get(i);
                            jjSwTable.setFilter(jTable1, tf.getText(), vTableCulomnNumberList.get(i));
                        } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JRadioButton")) {
                        } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JCheckBox")) {
                            javax.swing.JCheckBox tf = (javax.swing.JCheckBox) vComponentList.get(i);
                            jjSwTable.setFilter(jTable1, tf.isSelected() ? "بله" : "خیر", vTableCulomnNumberList.get(i));
                        } else if (vComponentList.get(i).getClass().toString().equals("class javax.swing.JButton")) {
                        }
                    }
                }
            }
        }
    };
}
