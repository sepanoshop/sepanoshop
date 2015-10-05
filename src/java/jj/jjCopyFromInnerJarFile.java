/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

/**
 *
 * @author Milad
 */
public class jjCopyFromInnerJarFile {

    public jjCopyFromInnerJarFile(String filePathInClass,String fileNameInTempFolder) throws FileNotFoundException, IOException {
        try {
            File backgroundFile = new File(jjOsInfo.getTempFilePath() + fileNameInTempFolder);
            ServerLog.Print(backgroundFile);
            InputStream in = getClass().getResourceAsStream(filePathInClass);
            OutputStream out = new FileOutputStream(backgroundFile.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }
}
