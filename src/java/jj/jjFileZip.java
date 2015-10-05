/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjFileZip {

    /**
     *
     * doZipFileOpen_Only_1_File("a.zip", "b.jpg");
     */
    public static void doUnZipFileOpen_Only_1_File(String zipFileAddress, String extractedFileAddress) {

        try {

            //Create input and output streams
            ZipInputStream inStream = new ZipInputStream(new FileInputStream(zipFileAddress));
            OutputStream outStream = new FileOutputStream(extractedFileAddress);

            ZipEntry entry;
            byte[] buffer = new byte[1024];
            int nrBytesRead;

            //Get next zip entry and start reading data
            if ((entry = inStream.getNextEntry()) != null) {
                while ((nrBytesRead = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, nrBytesRead);
                }
            }

            //Finish off by closing the streams
            outStream.close();
            inStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void doZip(File fileAddress, File zipFileAddress) {
        doZip(fileAddress.getAbsolutePath(), zipFileAddress.getAbsolutePath());
    }

    /**
     * Creates a zip file
     */
    public static void doZip(String fileAddress, String zipFileAddress) {
        try {
            File f = new File(fileAddress);
            if (f.isDirectory()) {
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileAddress));
                int len = f.getAbsolutePath().lastIndexOf(File.separator);
                String baseName = f.getAbsolutePath().substring(0, len + 1);
                addFolderToZip(f, out, baseName);
                out.close();
            } else {
                String name = f.getAbsolutePath().substring(zipFileAddress.length());
                ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFileAddress));
                ZipEntry zipEntry = new ZipEntry(name);
                zip.putNextEntry(zipEntry);
//                IOUtils.copy(new FileInputStream(f), zip);
                zip.closeEntry();
//                FileInputStream inStream = new FileInputStream(f);
//                ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFileAddress));
//                outStream.putNextEntry(new ZipEntry(extractedFileAddress));
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = inStream.read(buffer)) > 0) {
//                    outStream.write(buffer, 0, bytesRead);
//                }
//                outStream.closeEntry();
//                outStream.close();
//                inStream.close();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void addFolderToZip(File folder, ZipOutputStream zip, String baseName) throws IOException {
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                addFolderToZip(file, zip, baseName);
            } else {
                String name = file.getAbsolutePath().substring(baseName.length());
                ZipEntry zipEntry = new ZipEntry(name);
                zip.putNextEntry(zipEntry);
                IOUtils.copy(new FileInputStream(file), zip);
                zip.closeEntry();
            }
        }
    }

    public static void listContentsOfZipFile(String zipFileAddress) {

        try {
            ZipFile zipFile = new ZipFile(zipFileAddress);

            Enumeration zipEntries = zipFile.entries();

            while (zipEntries.hasMoreElements()) {

                //Process the name, here we just print it out
                 ServerLog.Print(((ZipEntry) zipEntries.nextElement()).getName());

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void doUnZipFile(String zipFilePath, String unZipFolder) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(zipFilePath));
            ZipInputStream zip = new ZipInputStream(in);
            ZipEntry ze;
            while ((ze = zip.getNextEntry()) != null) {
                UnZip(zip, unZipFolder);
            }
            zip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UnZip(ZipInputStream in, String s) throws Exception {
        FileOutputStream out = new FileOutputStream(s);
        byte[] data = new byte[65536];
        int count = 0;
        while ((count = in.read(data)) != -1) {
            out.write(data, 0, count);
        }
        out.flush();
        out.close();
    }
//    public static void doUnZipFile(String zipFilePath) {
//        try {
//            InputStream in = new BufferedInputStream(new FileInputStream(zipFilePath));
//            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("D:\\a.zip")));
//            BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\01.txt"));
//            byte[] data = new byte[1000];
//            int count;
//            out.putNextEntry(new ZipEntry("outFile.zip"));
//            while ((count = in.read(data, 0, 900)) != -1) {
//                out.write(data, 0, count);
//            }
//            in.close();
//            out.flush();
//            out.close();
//            ServerLog.Print("Your file is zipped");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
