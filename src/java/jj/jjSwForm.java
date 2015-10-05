/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.print.DocFlavor.URL;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
//import sw.sw_Manager;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjSwForm {

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static Dimension setCenterFrameLocation(Component comp) {
        Dimension dim_scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim_lblSize = comp.getPreferredSize();
        dim_lblSize = comp.getSize();
        comp.setLocation(dim_scrSize.width / 2 - (dim_lblSize.width / 2),
                dim_scrSize.height / 2 - (dim_lblSize.height / 2) - 30);
        return dim_scrSize;
    }

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static Dimension setRightTopFrameLocation(Component comp) {
        Dimension dim_scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim_lblSize = comp.getPreferredSize();
        dim_lblSize = comp.getSize();
        comp.setLocation(dim_scrSize.width - dim_lblSize.width, 0);
        return dim_scrSize;
    }

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static Dimension setRightDownFrameLocation(Component comp) {
        Dimension dim_scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim_lblSize = comp.getPreferredSize();
        dim_lblSize = comp.getSize();
        comp.setLocation(dim_scrSize.width - dim_lblSize.width, dim_scrSize.height - dim_lblSize.height);
        return dim_scrSize;
    }

    public static void setBackGroundToForm(JCheckBox checkBox, Color c) {
        for (int i = 0; i < checkBox.getComponentCount(); i++) {
            if (checkBox.getComponent(i) instanceof JCheckBox) {
                JCheckBox p = (JCheckBox) checkBox.getComponent(i);
                p.setBackground(c);
                setBackGroundToForm(p, c);
            }
        }
    }

    public static void setBackGroundToForm(JScrollPane scrollPane, Color c) {
        for (int i = 0; i < scrollPane.getComponentCount(); i++) {
            if (scrollPane.getComponent(i) instanceof JScrollPane) {
                JScrollPane p = (JScrollPane) scrollPane.getComponent(i);
                p.setBackground(c);
                setBackGroundToForm(p, c);
            }
        }
    }

    public static void setBackGroundToForm(JPanel panel, Color c) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JRadioButton) {
                panel.getComponent(i).setBackground(c);
            }
            if (panel.getComponent(i) instanceof JCheckBox) {
                panel.getComponent(i).setBackground(c);
            }
            if (panel.getComponent(i) instanceof JPanel) {
                JPanel p = (JPanel) panel.getComponent(i);
                p.setBackground(c);
                setBackGroundToForm(p, c);
            }
        }
    }

    public static List<JTextField> getTextFields(JPanel panel) {
        List<JTextField> txt = new ArrayList<JTextField>();
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JTextField) {
                txt.add((JTextField) panel.getComponent(i));
            }
            if (panel.getComponent(i) instanceof JPanel) {
                JPanel p = (JPanel) panel.getComponent(i);
                txt.addAll(getTextFields(p));
            }
        }
        return txt;
    }

    /**
     *
     * set StandardSize to Frame
     */
    public static void setStandardSize1(Component comp) {
        Dimension dim = new Dimension(970, 630);
        comp.setSize(dim);
        comp.setPreferredSize(dim);
    }

    /**
     *
     * set StandardSize to Frame
     */
    public static void setStandardSize2(Component comp) {
        Dimension dim = new Dimension(410, 460);
        comp.setSize(dim);
        comp.setPreferredSize(dim);
    }

    /**
     *
     * set to maximize status
     */
    public static void setMaximize(JFrame f) {
        f.setExtendedState(Frame.MAXIMIZED_BOTH | Frame.NORMAL);
    }

    /**
     *
     * set to minimize status
     */
    public static void setMinimize(JFrame f) {
        f.setExtendedState(Frame.WIDTH);
    }

    /**
     *
     * set to close status
     */
    public static void setClose(JFrame f) {
        f.setVisible(false);
        System.exit(0);
    }

    /**
     *
     * Hidden frame title
     * You must call this method after super method
     */
    public static void setFrameWithoutTitle(JFrame f) {
        f.setVisible(false);
        f.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        f.setUndecorated(true);
        f.setVisible(true);
    }

    /**
     *
     * set size Of Frame depended of Screen <br/><br/>
     * (  frame.setSize( screanW-X , screanH-Y)  )
     */
    public static Dimension setFrameSizeDependentOfScreen(Component comp, int lessThanScreenWhith, int leeThanScreenHeight) {
        Dimension dim_scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        comp.setPreferredSize(new Dimension(dim_scrSize.width - lessThanScreenWhith, dim_scrSize.height - leeThanScreenHeight));
        comp.setSize(new Dimension(dim_scrSize.width - lessThanScreenWhith, dim_scrSize.height - leeThanScreenHeight));
        setCenterFrameLocation(comp);
        return dim_scrSize;
    }

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static void setFrameTransparentClosing(Window f) {
        myframe = f;
        try {
            for (int i = 100; i > 0; i--) {
                try {
                    jjSwForm.setFrameTransparent(myframe, i);
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    jjError.Handler(ex);
                }
            }

        } catch (Exception e) {
            jjError.Handler(e);
        }
    }

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static void setFrameTransparentClosingListener(Window f) {
        myframe = f;
        try {
            f.addWindowListener(new java.awt.event.WindowAdapter() {

                public void windowClosing(java.awt.event.WindowEvent evt) {
                    for (int i = 100; i > 0; i--) {
                        try {
                            jjSwForm.setFrameTransparent(myframe, i);
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            jjError.Handler(ex);
                        }
                    }
                }
            });

        } catch (Exception e) {
            jjError.Handler(e);
        }
    }
    private static Window myframe = null;

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static void setFrameTransparentOpening(Window f) {
        try {
            try {
                jjSwForm.setFrameTransparent(f, 0);
                f.setVisible(true);
                for (int i = 0; i < 101; i++) {
                    jjSwForm.setFrameTransparent(f, i);
                    Thread.sleep(5);
                }
            } catch (InterruptedException ex) {
                jjError.Handler(ex);
            }
        } catch (Exception e) {
            jjError.Handler(e);
        }
    }

    /**
     *
     * set Location Of Frame in Center Of Screen
     */
    public static void setFrameTransparent(Window f, int opacity_1_100) {
        if (opacity_1_100 > 100) {
            opacity_1_100 = 100;
        }
        if (opacity_1_100 < 0) {
            opacity_1_100 = 0;
        }

        try {
            Class<?> awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
            Method mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);
            mSetWindowOpacity.invoke(null, f, (opacity_1_100 / 100f));
//        com.sun.awt.AWTUtilities.setWindowOpacity(f, (opacity_1_100 / 100f));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getScreenResolution() {
        return Toolkit.getDefaultToolkit().getScreenResolution();
    }

    public static enum Theme {

        System, Windows, Windows_Classic, Metal, Nimbus, CDE_Motif, Napkin;
    }

    /**
     *
     * set selected lookAndFeel from Help.Theme. ...     to com <br/>
     */
    public static void setLookAndFeel(Theme theme) {
        try {
            // UIManager.LookAndFeelInfo[] installedLafs = UIManager.getInstalledLookAndFeels();
            String newLookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
            switch (theme) {
                case System:
                    newLookAndFeel = UIManager.getSystemLookAndFeelClassName();
                    break;
                case Windows:
                    newLookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                    break;
                case Windows_Classic:
                    newLookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
                    break;
                case CDE_Motif:
                    newLookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                    break;
                case Metal:
                    newLookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
                    break;
                case Nimbus:
                    newLookAndFeel = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
                    break;
                case Napkin:
                    // for use NapkinLookAndFeel add jar file napkinlaf_LookAndFeel.jar from Mylib Folder
                    newLookAndFeel = "napkin.NapkinLookAndFeel";
                    break;
            }
            UIManager.setLookAndFeel(newLookAndFeel);
//            if (frame != null) {
//                SwingUtilities.updateComponentTreeUI(frame);
//            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "setLookAndFeel()", ex.getMessage());
            return;
        }


    }
    /**
     *
     *  A "splash screen" to show while waiting program to load  <br/><br/>
     *  new SplashScreen("/Images/SplashScreen.jpg");
     */
    private static JWindow splashScreenWindow;

    public static void splashScreenShow(String pathOfPicture) {
        try {
            splashScreenWindow = new JWindow();
            setFrameTransparent(splashScreenWindow, 100);
            Image image = null;
            if (pathOfPicture.startsWith("/")) {
                image = jjPicture.getIconImageFromURL(splashScreenWindow.getClass().getResource(pathOfPicture));
            } else {
                File f = new File(pathOfPicture);
                if (f.exists()) {
                    new URL("");
                    image = jjPicture.getIconImageFromURL(f.toURL());
                } else {
                    ServerLog.Print(pathOfPicture + " isn't exist");
                }
            }

//            JLabel jlbl = new JLabel("Milad");
            JLabel jlbl = new JLabel(new ImageIcon(image));
            jlbl.setBorder(new LineBorder(Color.BLACK, 1));
            splashScreenWindow.getContentPane().add(jlbl, BorderLayout.CENTER);
            Dimension screenSize = jjOsInfo.getScreenSize();
//            splashScreenWindow.setPreferredSize(new Dimension((int)screenSize.getWidth(),(int) screenSize.getHeight()));
//            jlbl.setSize(new Dimension((int)screenSize.getWidth()/3,(int) screenSize.getHeight()/3));

            splashScreenWindow.pack();
            setCenterFrameLocation(splashScreenWindow);
            splashScreenWindow.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
//            Help.dialog_ERROR_MESSAGE(ex, null, "splashScreenShow()", ex.getMessage());
            return;
        }
    }

    /**
     *
     *  Close the SplashScreen
     */
    public static void splashScreenClose() {
        if (splashScreenWindow != null) {
            splashScreenWindow.setVisible(false);
//            splashScreenWindow.dispose();
        }
    }

    /**
     *
     *  set better font for ToolTipText
     */
    public static String getToolTipText_by_HTML(String ToolTipText, int size_1_9) {
        String html =
                "<html><p align='center' style='margin: 3px'><font color=\"#0066FF\" "
                + "size=\"" + size_1_9 + "\" face=\"Tahoma\">"
                + ToolTipText
                + "</font></p></html>";
        return html;
    }

    /**
     *
     *  set change when MouseEnter on component
     */
    public static void setMouseEnter(java.awt.event.MouseEvent evt, Color newForgroundColor) {
        evt.getComponent().setForeground(newForgroundColor);//new Color(150, 150, 150)
        evt.getComponent().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    /**
     *
     *  set change when MouseExite on component
     */
    public static void setMouseExite(java.awt.event.MouseEvent evt, Color lastForgroundColor) {
        evt.getComponent().setForeground(lastForgroundColor);//new Color(0, 0, 100)
        evt.getComponent().setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }

    public void doManageNextFocus(List<Component> component) {
        Component com[] = new Component[component.size()];
        for (int i = 0; i < component.size(); i++) {
            com[i] = component.get(i);
        }
        doManageNextFocus(com);
    }
    private Component c[];
    private Component Allcomponent[];

    public void doManageNextFocus(Component... component) {
        c = component;
        List<Component> comFocusList = new ArrayList<Component>();
        for (int i = 0; i < component.length; i++) {
            if (component[i] instanceof JPanel) {
//                comFocusList.addAll(sw_Manager.getFocusComponent(component[i]));
            } else {
                comFocusList.add(component[i]);
            }
        }
        Allcomponent = new Component[comFocusList.size()];
        for (int i = 0; i < Allcomponent.length; i++) {
            Allcomponent[i] = comFocusList.get(i);

        }
        for (int i = 0; i < Allcomponent.length; i++) {
            if (Allcomponent[i] instanceof JTextField) {
                javax.swing.JTextField tf = (javax.swing.JTextField) Allcomponent[i];
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                tf.addKeyListener(ManageNextFocusAdapter);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            } else if (Allcomponent[i] instanceof JComboBox) {
                javax.swing.JComboBox tf = (javax.swing.JComboBox) Allcomponent[i];
                javax.swing.JTextField tf2 = (JTextField)tf.getEditor().getEditorComponent();
                tf.addKeyListener(ManageNextFocusAdapter);
                tf2.addKeyListener(ManageNextFocusAdapter);
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            } else if (Allcomponent[i] instanceof JFormattedTextField) {
                javax.swing.JFormattedTextField tf = (javax.swing.JFormattedTextField) Allcomponent[i];
                tf.addKeyListener(ManageNextFocusAdapter);
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            } else if (Allcomponent[i] instanceof JTextArea) {
                javax.swing.JTextArea tf = (javax.swing.JTextArea) Allcomponent[i];
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                tf.addKeyListener(ManageNextFocusAdapter);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            } else if (Allcomponent[i] instanceof JRadioButton) {
                javax.swing.JRadioButton tf = (javax.swing.JRadioButton) Allcomponent[i];
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                tf.addKeyListener(ManageNextFocusAdapter);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            } else if (Allcomponent[i] instanceof JCheckBox) {
                javax.swing.JCheckBox tf = (javax.swing.JCheckBox) Allcomponent[i];
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                tf.addKeyListener(ManageNextFocusAdapter);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            } else if (Allcomponent[i] instanceof JButton) {
                javax.swing.JButton tf = (javax.swing.JButton) Allcomponent[i];
                tf.setNextFocusableComponent(i == Allcomponent.length - 1 ? Allcomponent[0] : Allcomponent[i + 1]);
                tf.addKeyListener(ManageNextFocusAdapter);
                if (i == 0 && tf.isEnabled()) {
                    tf.grabFocus();
                }
            }

        }
    }
    public void doRemoveManageNextFocus() {
        for (int i = 0; i < Allcomponent.length; i++) {
            if (Allcomponent[i] instanceof JTextField) {
                javax.swing.JTextField tf = (javax.swing.JTextField) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            } else if (Allcomponent[i] instanceof JComboBox) {
                javax.swing.JComboBox tf = (javax.swing.JComboBox) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            } else if (Allcomponent[i] instanceof JFormattedTextField) {
                javax.swing.JFormattedTextField tf = (javax.swing.JFormattedTextField) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            } else if (Allcomponent[i] instanceof JTextArea) {
                javax.swing.JTextArea tf = (javax.swing.JTextArea) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            } else if (Allcomponent[i] instanceof JRadioButton) {
                javax.swing.JRadioButton tf = (javax.swing.JRadioButton) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            } else if (Allcomponent[i] instanceof JCheckBox) {
                javax.swing.JCheckBox tf = (javax.swing.JCheckBox) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            } else if (Allcomponent[i] instanceof JButton) {
                javax.swing.JButton tf = (javax.swing.JButton) Allcomponent[i];
                tf.removeKeyListener(ManageNextFocusAdapter);
            }

        }
    }
    java.awt.event.KeyAdapter ManageNextFocusAdapter = new java.awt.event.KeyAdapter() {

        public void keyReleased(java.awt.event.KeyEvent evt) {
            Component tf = (Component) evt.getComponent();
            if (evt.getKeyCode() == 10) {
                for (int i = 0; i < c.length; i++) {
                    if (c[i] == tf) {
                        if (i == (c.length - 1)) {
                            if (c[0] instanceof JTextField) {
                                javax.swing.JTextField tf2 = (javax.swing.JTextField) c[0];
                                tf2.grabFocus();
                            } else if (c[0] instanceof JFormattedTextField) {
                                javax.swing.JFormattedTextField tf2 = (javax.swing.JFormattedTextField) c[0];
                                tf2.grabFocus();
                            } else if (c[0] instanceof JTextArea) {
                                javax.swing.JTextArea tf2 = (javax.swing.JTextArea) c[0];
                                tf2.grabFocus();
                            } else if (c[0] instanceof JComboBox) {
                                javax.swing.JComboBox tf2 = (javax.swing.JComboBox) c[0];
                                tf2.grabFocus();
                            } else if (c[0] instanceof JRadioButton) {
                                javax.swing.JRadioButton tf2 = (javax.swing.JRadioButton) c[0];
                                tf2.grabFocus();
                            } else if (c[0] instanceof JCheckBox) {
                                javax.swing.JCheckBox tf2 = (javax.swing.JCheckBox) c[0];
                                tf2.grabFocus();
                            } else if (c[0] instanceof JButton) {
                                javax.swing.JButton tf2 = (javax.swing.JButton) c[0];
                                tf2.grabFocus();
                            }
                        } else {
                            if (c[i + 1] instanceof JTextField) {
                                javax.swing.JTextField tf2 = (javax.swing.JTextField) c[i + 1];
                                tf2.grabFocus();
                            } else if (c[i + 1] instanceof JFormattedTextField) {
                                javax.swing.JFormattedTextField tf2 = (javax.swing.JFormattedTextField) c[i + 1];
                                tf2.grabFocus();
                            } else if (c[i + 1] instanceof JTextArea) {
                                javax.swing.JTextArea tf2 = (javax.swing.JTextArea) c[i + 1];
                                tf2.grabFocus();
                            } else if (c[i + 1] instanceof JComboBox) {
                                javax.swing.JComboBox tf2 = (javax.swing.JComboBox) c[i + 1];
                                tf2.grabFocus();
                            } else if (c[i + 1] instanceof JRadioButton) {
                                javax.swing.JRadioButton tf2 = (javax.swing.JRadioButton) c[i + 1];
                                tf2.grabFocus();
                            } else if (c[i + 1] instanceof JCheckBox) {
                                javax.swing.JCheckBox tf2 = (javax.swing.JCheckBox) c[i + 1];
                                tf2.grabFocus();
                            } else if (c[i + 1] instanceof JButton) {
                                javax.swing.JButton tf2 = (javax.swing.JButton) c[i + 1];
                                tf2.grabFocus();
                            }
                        }
                    }
                }
            }
        }
    };

    /**
     *
     *  set icon to label by HTML
     */
    public static void setIconToLabelByHTML(JLabel lbl, File picFile, int wiedth, int haight) {
        lbl.setText("<html>\n<body>\n<img border=\"0\" src=\"file:///"
                + picFile.getAbsolutePath() + "\" width=\"" + wiedth + "\" height=\"" + haight + "\">\n</body>\n</html>\n");
    }

    /**
     *
     *  set icon to Form
     */
    public static void setIcon(JFrame frame, String picPath) {
        Image imgIcon = jjPicture.getIconImageFromURL(frame.getClass().getResource(picPath));
        frame.setIconImage(imgIcon);
    }

    /**
     *
     *  set icon to Form
     */
    public static void setIcon(JDialog dialog, String picPath) {
        Image imgIcon = jjPicture.getIconImageFromURL(dialog.getClass().getResource(picPath));
        dialog.setIconImage(imgIcon);
    }

    public static void clean(JComponent parentPanel) {
        try {
            for (int i = 0; i < parentPanel.getComponentCount(); i++) {
                if (parentPanel.getComponent(i) instanceof JTextField) {
                    javax.swing.JTextField tf = (javax.swing.JTextField) parentPanel.getComponent(i);
                    tf.setText("");
                } else if (parentPanel.getComponent(i) instanceof JComboBox) {
                    javax.swing.JComboBox tf = (javax.swing.JComboBox) parentPanel.getComponent(i);
                    tf.setSelectedIndex(0);
                } else if (parentPanel.getComponent(i) instanceof JFormattedTextField) {
                    javax.swing.JFormattedTextField tf = (javax.swing.JFormattedTextField) parentPanel.getComponent(i);
                    tf.setText("");
                } else if (parentPanel.getComponent(i) instanceof JTextArea) {
                    javax.swing.JTextArea tf = (javax.swing.JTextArea) parentPanel.getComponent(i);
                    tf.setText("");
                } else if (parentPanel.getComponent(i) instanceof JRadioButton) {
//                    javax.swing.JRadioButton tf = (javax.swing.JRadioButton) vComponentList.get(i);
                } else if (parentPanel.getComponent(i) instanceof JCheckBox) {
                    javax.swing.JCheckBox tf = (javax.swing.JCheckBox) parentPanel.getComponent(i);
                    tf.setSelected(false);
                } else if (parentPanel.getComponent(i) instanceof JPanel) {
                    javax.swing.JPanel tf = (javax.swing.JPanel) parentPanel.getComponent(i);
                    clean(tf);
                } else if (parentPanel.getComponent(i) instanceof JTabbedPane) {
                    javax.swing.JTabbedPane tf = (javax.swing.JTabbedPane) parentPanel.getComponent(i);
                    clean(tf);
                } else if (parentPanel.getComponent(i) instanceof JScrollPane) {
                    javax.swing.JScrollPane tf = (javax.swing.JScrollPane) parentPanel.getComponent(i);
                    clean(tf);
                }
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }
}
