package jj;

import java.lang.reflect.Method;
import java.awt.*;
import javax.swing.*;

public class jjTransparent extends JWindow {

    public jjTransparent(Component lbl, Point location) {
        // jjOsInfo.doShowSystemTray("/Images/PNG (52).png", "Shortcut", this);
        JPanel myPanel = new JPanel();
        myPanel.setSize(lbl.getSize());
        myPanel.setOpaque(false);
        myPanel.setDoubleBuffered(false);
        myPanel.setBackground(new Color(30, 190, 250));
        myPanel.setLayout(null);
        this.getContentPane().add(myPanel);
        this.setLocation(location);
        this.setSize(lbl.getSize());
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();
        GraphicsConfiguration translucencyCapableGC = null;
        // first see if we can find a translucency-capable graphics device
        for (int i = 0; i < devices.length && translucencyCapableGC == null; i++) {
            GraphicsConfiguration[] configs = devices[i].getConfigurations();
            for (int j = 0; j < configs.length && translucencyCapableGC == null; j++) {
                //translucency requires java version Java SE 6u10
//                if (com.sun.awt.AWTUtilities.isTranslucencyCapable(configs[j])) {
//                    translucencyCapableGC = configs[j];
//                }
            }
        }
        try {
            // set window transparent
            Class<?> awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
            Method mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);
            mSetWindowOpacity.invoke(null, this, Float.valueOf(1.0f));
//            com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl.setBounds(0, 0, lbl.getSize().width, lbl.getSize().height);
        myPanel.add(lbl);
        this.setVisible(true);
    }
}
