/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.awt.*;
import java.awt.datatransfer.*;

/**
 *
 * @author Milad
 */
public class jjClipBoard implements ClipboardOwner {

    /**
     *
     *  add ClipboardSrc to Clipboard for Paste that
     */
    public jjClipBoard(String ClipboardSrc) {
        SetToClipboard(ClipboardSrc);
    }

    public void SetToClipboard(String ClipboardSrc) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkSystemClipboardAccess();
            } catch (Exception ex) {
                jjError.Handler(ex);
            }
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        StringSelection st = new StringSelection(ClipboardSrc);
        Clipboard cp = tk.getSystemClipboard();
        cp.setContents(st, this);
    }

    public void lostOwnership(Clipboard clip, Transferable tr) {
        ServerLog.Print("Lost Clipboard Ownership?!?");
    }

    public static String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText =
                (contents != null)
                && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception ex) {
                jjError.Handler(ex);
            }
        }
        return result;
    }
}
