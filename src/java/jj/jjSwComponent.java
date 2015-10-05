/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.ComponentOrientation;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Milad
 */
public class jjSwComponent {

    public static void setRightToLeft(JComponent yourJComponent) {
        yourJComponent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        yourJComponent.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        if (yourJComponent instanceof JTextField) {
            JTextField txt = (JTextField) yourJComponent;
            txt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        } else if (yourJComponent instanceof JLabel) {
            JLabel lbl = (JLabel) yourJComponent;
            lbl.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        } else if (yourJComponent instanceof JComboBox) {
            JComboBox combo = (JComboBox) yourJComponent;
            ((JComponent) combo.getRenderer()).applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
    }

    public static void setRightToLeftAllPanel(JPanel parentPanel) {
        jjSwComponent.setRightToLeft(parentPanel);
        for (int i = 0; i < parentPanel.getComponentCount(); i++) {
            if (parentPanel.getComponent(i) instanceof JPanel) {
                setRightToLeftAllPanel((JPanel) parentPanel.getComponent(i));
            }
        }
    }

    public static void setLeftToRightAllPanel(JPanel parentPanel) {
        jjSwComponent.setLeftToRight(parentPanel);
        for (int i = 0; i < parentPanel.getComponentCount(); i++) {
            if (parentPanel.getComponent(i) instanceof JPanel) {
                setLeftToRightAllPanel((JPanel) parentPanel.getComponent(i));
            }
        }
    }

    public static void setLeftToRight(JComponent yourJComponent) {
        yourJComponent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        yourJComponent.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        if (yourJComponent instanceof JTextField) {
            JTextField txt = (JTextField) yourJComponent;
            txt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        } else if (yourJComponent instanceof JLabel) {
            JLabel lbl = (JLabel) yourJComponent;
            lbl.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        } else if (yourJComponent instanceof JComboBox) {
            JComboBox combo = (JComboBox) yourJComponent;
            ((JComponent) combo.getRenderer()).applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
    }

    public static void setRightToLeft(JComponent... yourJComponent) {
        for (int i = 0; i < yourJComponent.length; i++) {
            jjSwComponent.setRightToLeft(yourJComponent[i]);
        }
    }

    public static void removeKeyListeners(JComponent yourJComponent) {
        KeyListener[] keyListeners = yourJComponent.getKeyListeners();
        for (int i = 0; i < keyListeners.length; i++) {
            yourJComponent.removeKeyListener(keyListeners[i]);
        }
    }

    public static void removeComponentListeners(JComponent yourJComponent) {
        ComponentListener[] componentListeners = yourJComponent.getComponentListeners();
        for (int i = 0; i < componentListeners.length; i++) {
            yourJComponent.removeComponentListener(componentListeners[i]);
        }
    }
}
