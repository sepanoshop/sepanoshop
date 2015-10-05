/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Milad
 */
public class jjSwCombobox {

    String[] itemArrayString = new String[0];
    boolean checkOnlyStartsWith;
    JComboBox yourJComboBox;

    public jjSwCombobox(JComboBox yourJComboBox, boolean checkOnlyStartsWith) {
        this.yourJComboBox = yourJComboBox;
        this.checkOnlyStartsWith = checkOnlyStartsWith;
        yourJComboBox.setEditable(true);
        itemArrayString = new String[yourJComboBox.getItemCount()];
        for (int i = 0; i < yourJComboBox.getItemCount(); i++) {
            itemArrayString[i] = yourJComboBox.getItemAt(i).toString();
        }
        yourJComboBox.getEditor().getEditorComponent().addKeyListener(keyAdapter);
    }
    /**
     *
     * set AutoComplate and editable to JCombobox combo
     */
    KeyAdapter keyAdapter = new KeyAdapter() {

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT) {
                JTextField textField1 = (JTextField) yourJComboBox.getEditor().getEditorComponent();
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    yourJComboBox.hidePopup();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    yourJComboBox.showPopup();
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (yourJComboBox.getItemCount() == 1) {
                            textField1.setText(yourJComboBox.getItemAt(0).toString());
                            yourJComboBox.hidePopup();
                        }
                    }
                    return;
                }
                String a = yourJComboBox.getEditor().getItem().toString();
                yourJComboBox.removeAllItems();
                boolean showPopup = false;
                for (int k = 0; k < itemArrayString.length; k++) {
                    if (checkOnlyStartsWith) {
                        if (itemArrayString[k].toLowerCase().startsWith(a.toLowerCase())) {
                            yourJComboBox.addItem(itemArrayString[k]);
                            showPopup = true;
                        }
                    } else {
                        if (itemArrayString[k].toLowerCase().contains(a.toLowerCase())) {
                            yourJComboBox.addItem(itemArrayString[k]);
                            showPopup = true;
                        }
                    }
                }
                textField1.setCaretPosition(textField1.getDocument().getLength());
                textField1.setText(a);
                yourJComboBox.hidePopup();
                if (showPopup) {
                    yourJComboBox.showPopup();
                }
            }
        }
    };

    public void removeAutoComplateKeyAdapter() {
        this.yourJComboBox.getEditor().getEditorComponent().removeKeyListener(keyAdapter);
    }
}
