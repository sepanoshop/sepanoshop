/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

/**
 *
 * @author Milad
 */
public class jjExportFileFromJar {
// create pic and library from iner file in jar file

    InputStream in = null;
    OutputStream out = null;

    public jjExportFileFromJar(String packName, String fileName, String newFolderPath) {
        try {
            if (!new File(newFolderPath + "\\" + fileName).exists()) {
                in = getClass().getResourceAsStream("/" + packName + "/" + fileName);
                out = new FileOutputStream(newFolderPath + "\\" + fileName);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.flush();
                out.close();
                ServerLog.Print("/" + packName + "/" + fileName + "  copy to:  " + newFolderPath + "\\" + fileName);
            }

        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }
}
