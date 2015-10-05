package jj;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import cms.tools.ServerLog;
public class jjLogin extends javax.swing.JDialog {

    File f;
    static boolean answer = false;
    javax.swing.JFrame frame;

    private void trueValue() {
        answer = true;
        this.setVisible(false);
//        this.frame.setVisible(true);
    }

    public jjLogin(javax.swing.JFrame frame, String savedPasswordPath) {
        super(frame, "Password", true);
        initComponents();
        this.frame = frame;
        pss1.addKeyListener(ManageNextFocusAdapter);
        ServerLog.Print("eeee");
        try {
//            jjSwForm.setIcon(this, "/Images/1.png");
            f = new File(savedPasswordPath);
            pss2.setVisible(false);
            btn.setVisible(false);
            this.setSize(new Dimension(320, 150));
            jjSwForm.setCenterFrameLocation(this);
            setVisible(true);
            ServerLog.Print("000");
        } catch (Exception ex) {
            ServerLog.Print("ERROR");
            jjError.Handler(ex);
        }
    }
    java.awt.event.KeyAdapter ManageNextFocusAdapter = new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                ServerLog.Print("00000");
                jButton2ActionPerformed(null);
            }
        }
    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn = new javax.swing.JButton();
        pss2 = new javax.swing.JPasswordField();
        pss1 = new javax.swing.JPasswordField();

        jMenuItem1.setText("اطلاعات سفارش");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("اطلاعات فاکتور");
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("باز کردن پوشه");
        jPopupMenu1.add(jMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(10, 10));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("رمز عبور:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 40, 80, 15);

        jButton1.setText("تغییر");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(120, 70, 100, 23);

        jButton2.setText("ورود");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(10, 70, 100, 23);

        btn.setText("ثبت تغییر رمز عبور");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        getContentPane().add(btn);
        btn.setBounds(10, 140, 210, 23);
        getContentPane().add(pss2);
        pss2.setBounds(10, 110, 210, 20);
        getContentPane().add(pss1);
        pss1.setBounds(11, 40, 210, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        answer = false;
        System.exit(1);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pss2.setVisible(true);
        btn.setVisible(true);
        this.setSize(new Dimension(320, 230));

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        String pass = "";
        char[] password = pss1.getPassword();
        for (int i = 0; i < password.length; i++) {
            pass += password[i];
        }
        if (pass.equals("")) {
            jjDialog.alert_Blue(this, "", "رمز خالی قابل قبول نمی باشد");
            return;
        }
        String pass2 = "";
        char[] password2 = pss2.getPassword();
        for (int i = 0; i < password2.length; i++) {
            pass2 += password2[i];
        }
        if (pass2.equals("")) {
            jjDialog.alert_Blue(this, "", "رمز خالی قابل قبول نمی باشد");
            return;
        }
        if (pass.hashCode() == Integer.parseInt(jjFileTxt.read(f))) {
            jjFileTxt.write(f, pass2.hashCode() + "");
            pss2.setVisible(false);
            btn.setVisible(false);
            this.setSize(new Dimension(320, 150));
            jjDialog.alert_Blue(this, "", "رمز به درستی تغییر نمود.");
            pss1.setText("");
        } else {
            jjDialog.alert_Blue(this, "", "رمز قبلی به درستی وارد نشده است.");
        }
    }//GEN-LAST:event_btnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String pass = "";
        char[] password = pss1.getPassword();
        for (int i = 0; i < password.length; i++) {
            pass += password[i];
        }
        if (!f.exists()) {
            jjFileTxt.write(f, pass.hashCode() + "");
//            jjDialog.alert_Blue(this, "", "فایل رمز برنامه وجود ندارد.");
//            return;
        }
        if (pass.hashCode() == Integer.parseInt(jjFileTxt.read(f))) {
            trueValue();
        } else {
            jjDialog.alert_Blue(this, "", "رمز وارد شده اشتباه می باشد.");

        }


    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPasswordField pss1;
    private javax.swing.JPasswordField pss2;
    // End of variables declaration//GEN-END:variables
}
