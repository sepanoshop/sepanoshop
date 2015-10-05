package jj;

import java.io.File;
import java.util.List;
import cms.tools.ServerLog;
public class jjLock extends javax.swing.JDialog {

    public jjLock(javax.swing.JFrame frame, String savedPasswordPath) {
        super(frame, "Password", true);
        initComponents();
        try {
            txt_UnKey.addKeyListener(ManageNextFocusAdapter);
            this.setIconImage(jjPicture.getIconImageFromURL(getClass().getResource("/Images/1.png")));
            int uniqueNumber = jjOsInfo.getUniqueNumber();
            this.setSize(250, 340);
            jjSwForm.setCenterFrameLocation(this);
            txt_key.setText(uniqueNumber + "");
            address.setText(savedPasswordPath);
            address.setVisible(false);
            txt_UnKey.grabFocus();
            List<String> readLine = jjFileTxt.readLine(savedPasswordPath);
            boolean isTruePass = true;
            for (int i = 0; i < readLine.size(); i++) {
                if (readLine.get(i).equals(jjOsInfo.getUniqueNumberKey(uniqueNumber) + "")) {
                    isTruePass = false;
                    break;
                }
            }
            this.setVisible(isTruePass);

        } catch (Exception ex) {
            ServerLog.Print(ex);
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_key = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_UnKey = new javax.swing.JPasswordField();
        jButtonCreateReserve1 = new javax.swing.JButton();
        address = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closee(evt);
            }
        });
        getContentPane().setLayout(new java.awt.FlowLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("کد سیستم:");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 20));
        getContentPane().add(jLabel1);

        txt_key.setEditable(false);
        txt_key.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_key.setPreferredSize(new java.awt.Dimension(150, 20));
        getContentPane().add(txt_key);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Key.png"))); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(220, 128));
        getContentPane().add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("با توجه به کد دریافتی بالا ");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 20));
        getContentPane().add(jLabel6);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("رمز عبور را وارد نمایید.");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 20));
        getContentPane().add(jLabel4);

        txt_UnKey.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_UnKey.setPreferredSize(new java.awt.Dimension(150, 20));
        getContentPane().add(txt_UnKey);

        jButtonCreateReserve1.setText("ورود");
        jButtonCreateReserve1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonCreateReserve1.setPreferredSize(new java.awt.Dimension(150, 25));
        jButtonCreateReserve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateReserve1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCreateReserve1);

        address.setFont(new java.awt.Font("Tahoma", 0, 13));
        address.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address.setText("آدرس");
        address.setPreferredSize(new java.awt.Dimension(150, 20));
        getContentPane().add(address);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    java.awt.event.KeyAdapter ManageNextFocusAdapter = new java.awt.event.KeyAdapter() {

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getKeyCode() == 10) {
                jButtonCreateReserve1ActionPerformed(null);
            }
        }
    };

    public static void Expire(int dayNumber) {
        File ExpireManage = new File(jjOsInfo.getTempFilePath().getAbsoluteFile() + "\\Photoshop.dll");
        jjCalendar_IR now = new jjCalendar_IR();
        if (ExpireManage.exists()) {
            String read = jjFileTxt.read(ExpireManage);
            jjCalendar_IR writed = new jjCalendar_IR(read);
            writed.addDay(dayNumber);
            if (writed.getDBFormat_8length() <= now.getDBFormat_8length()) {
                // Expire
                if (Setting.isLanguagePersian()) {
                    jjDialog.alert_Blue(null, "Expire", "اعتبار برنامه شما به پایان رسیده، لطفا با شماره " + Setting.CONTACT_NUMBER + "تماس حاصل فرمایید" + ".");
                } else {
                    jjDialog.alert_Blue(null, "Expire", "Your Program is Expire, please call to" + Setting.CONTACT_NUMBER + ".");
                }
                System.exit(0);
            } else {
                if (Integer.parseInt(read) > now.getDBFormat_8length()) {
                    // Expire
                    if (Setting.isLanguagePersian()) {
                        jjDialog.alert_Blue(null, "Expire", "شما تنظیمات تاریخ سیستم خود را به عقب برگردانده اید" + ".");
                        jjDialog.alert_Blue(null, "Expire", "اعتبار برنامه شما به پایان رسیده، لطفا با شماره " + Setting.CONTACT_NUMBER + "تماس حاصل فرمایید" + ".");
                    } else {
                        jjDialog.alert_Blue(null, "Expire", "You Change your date Setting to back.");
                        jjDialog.alert_Blue(null, "Expire", "Your Program is Expire, please call to" + Setting.CONTACT_NUMBER + ".");
                    }
                    System.exit(0);
                }
            }
        } else {
            jjFileTxt.write(ExpireManage, now.getDBFormat_8length() + "", false, false);
        }
    }

    private void jButtonCreateReserve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateReserve1ActionPerformed
        String pass = "";
        char[] password = txt_UnKey.getPassword();
        for (int i = 0; i < password.length; i++) {
            pass += password[i];
        }
        int a = Integer.parseInt(txt_key.getText());
        if (pass.equals(jjOsInfo.getUniqueNumberKey(a) + "")) {
            jjDialog.alert_Blue(rootPane, "", "رمز شما صحیح می باشد.");
            jjFileTxt.write(address.getText(), pass, true, true);
            this.setVisible(false);
        } else {
            jjDialog.alert_Blue(rootPane, "", "رمز شما اشتباه می باشد.");
            this.setVisible(true);
        }



    }//GEN-LAST:event_jButtonCreateReserve1ActionPerformed

    private void closee(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closee
        System.exit(1);
    }//GEN-LAST:event_closee
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel address;
    public static javax.swing.JButton jButtonCreateReserve1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private static javax.swing.JPasswordField txt_UnKey;
    public javax.swing.JTextField txt_key;
    // End of variables declaration//GEN-END:variables
}
