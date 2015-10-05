/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Milad
 */
public class jjSwTextField {

    Class ClassOfMethod;
    String publicStaticWithowtParameterMethodName;
    private JTextField yourJTextField;

    public jjSwTextField(JTextField yourJTextField) {
        this.yourJTextField = yourJTextField;
    }

    public jjSwTextField(JFormattedTextField yourJTextField) {
        this.yourJTextField = yourJTextField;
    }

    public void removeDocumentListener() {
        this.yourJTextField.getDocument().removeDocumentListener(documentListener);
    }

    public void addDocumentListener(Class ClassOfMethod, String publicStaticWithowtParameterMethodName) {
        this.ClassOfMethod = ClassOfMethod;
        this.publicStaticWithowtParameterMethodName = publicStaticWithowtParameterMethodName;
        this.yourJTextField.getDocument().addDocumentListener(documentListener);
    }

    public void ListenerAct() {
        try {
            if (!Setting.IS_DOCUMENT_LISTENER_ACTIVE) {
                return;
            }
            removeDocumentListener();
            jjReflection.callMethodByName(ClassOfMethod, publicStaticWithowtParameterMethodName);
            yourJTextField.getDocument().addDocumentListener(documentListener);
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }
    public DocumentListener documentListener = new DocumentListener() {

        public void insertUpdate(DocumentEvent e) {
            ListenerAct();
        }

        public void removeUpdate(DocumentEvent e) {
            ListenerAct();
        }

        public void changedUpdate(DocumentEvent e) {
            ListenerAct();
        }
    };

    public void setOnlyWriteNumber() {
        yourJTextField.addKeyListener(ListenerKey_Only_No);
    }

    public void setOnlyWriteNumberAndDot() {
        yourJTextField.addKeyListener(ListenerKey_No_And_Dot);
    }

    public void setFormaterNumber() {
        yourJTextField.addKeyListener(textFieldNumberFormater);
    }
    private java.awt.event.KeyAdapter textFieldNumberFormater = new java.awt.event.KeyAdapter() {

        public void keyTyped(java.awt.event.KeyEvent evt) {
            JTextField txt = (JTextField) evt.getSource();
            char c = evt.getKeyChar();
            String s = txt.getText().replace(" ", "");
            if (c == ' ') {
                evt.consume();
                return;
            } else if (Character.isDigit(c)) {
                int cursorLocation = txt.getCaretPosition();
                s = s.substring(0, cursorLocation) + c + s.substring(cursorLocation);
                txt.setText(jjNumber.getFormattedNumber(s.replace(",", "")));
            } else if (Character.isLetter(c)) {
                evt.consume();
                return;
            } else {
                txt.setText(jjNumber.getFormattedNumber(s.replace(",", "")));
                return;
            }
            evt.consume();
        }
    };
    private KeyAdapter ListenerKey_Only_No = new java.awt.event.KeyAdapter() {

        public void keyTyped(java.awt.event.KeyEvent evt) {
            if (evt.getKeyChar() != 48
                    && evt.getKeyChar() != 49
                    && evt.getKeyChar() != 50
                    && evt.getKeyChar() != 51
                    && evt.getKeyChar() != 52
                    && evt.getKeyChar() != 53
                    && evt.getKeyChar() != 54
                    && evt.getKeyChar() != 55
                    && evt.getKeyChar() != 37
                    && evt.getKeyChar() != 38
                    && evt.getKeyChar() != 39
                    && evt.getKeyChar() != 40
                    && evt.getKeyChar() != 8
                    && evt.getKeyChar() != 127
                    && evt.getKeyChar() != 56
                    && evt.getKeyChar() != 57) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    };
    private KeyAdapter ListenerKey_No_And_Dot = new java.awt.event.KeyAdapter() {

        public void keyTyped(java.awt.event.KeyEvent evt) {

            char cc = evt.getKeyChar();
//            int ddd = evt.getKeyChar();
            if (cc != 48
                    && cc != 49
                    && cc != 50
                    && cc != 51
                    && cc != 52
                    && cc != 53
                    && cc != 54
                    && cc != 55
                    && cc != 46
                    && cc != 37
                    && cc != 38
                    && cc != 39
                    && cc != 40
                    && cc != 8
                    && cc != 127
                    && cc != 56
                    && cc != 57) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    };

    /**
     *
     *  Help for update documentListener on JTextField
     */
    public static DocumentListener Help_Update_DocListener = new DocumentListener() {

        public void insertUpdate(DocumentEvent e) {
            // method();
        }

        public void removeUpdate(DocumentEvent e) {
            // method();
        }

        public void changedUpdate(DocumentEvent e) {
            // method();
        }
    };
}
