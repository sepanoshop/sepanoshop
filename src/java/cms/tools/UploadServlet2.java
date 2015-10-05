package cms.tools;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import jj.jjNumber;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet2 extends HttpServlet {

    private static long maxSize = 90000000;
    private static String Save_Folder_Name = "upload";
    Map<String, String> data = new HashMap<String, String>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        name = name == null ? "" : name;
        response.setContentType("text/plain");
        super.init(getServletConfig());

        if (jjNumber.isDigit(jjTools.getParameter(request, "maxSize"))) {
            maxSize = Long.parseLong(jjTools.getParameter(request, "maxSize"));
        }

//        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
//        out.println();

        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
//        fileItemFactory.setSizeThreshold(1024 * 1024); //1 MB

            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
            List items = uploadHandler.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    /*
                     * Field
                     */
//                    out.println("Field Name=" + item.getFieldName() + ", Value=" + item.getString());
                    data.put(item.getFieldName(), item.getString());
                } else {
                    /*
                     * File
                     */
                    File folderAddress = new File(request.getServletContext().getRealPath(Save_Folder_Name));//"/" +
                    String extension = "";
                    String nameWithoutExtension = item.getName();
                    if (item.getName().lastIndexOf(".") > -1) {
                        extension = item.getName().substring(item.getName().lastIndexOf("."));
                        nameWithoutExtension = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().lastIndexOf("."));
                    }
                    folderAddress.mkdirs();
                    nameWithoutExtension = "P";
                    File file = new File(folderAddress + "/" + nameWithoutExtension.toLowerCase() + jjNumber.getRandom(10) + extension.toLowerCase());
                    String i = "0000000000";
                    while (file.exists()) {
                        i = jjNumber.getRandom(10);
                        file = new File(folderAddress + "/" + nameWithoutExtension.toLowerCase() + i + extension.toLowerCase());
                    }
                    if (!name.equals("")) {
                        file = new File(folderAddress + "/" + name);
                    }
//                    out.println("File Name=" + item.getName()
//                            + ", Field Name=" + item.getFieldName()
//                            + ", Content type=" + item.getContentType()
//                            + ", File Size=" + item.getSize()
//                            + ", Save Address=" + file);
//                    out.println(file);
//                    String urlPath = request.getRequestURL().toString().replace("Upload2", "Upload") + "/" + file.getName().replace("\\", "/");
//                    out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body><input type='text' name='T1' size='58' value='" + urlPath + "'></body></html>");
                    data.put(item.getFieldName(), file.getAbsolutePath());
                    if (!file.getName().toLowerCase().endsWith(".exe")) {
                        item.write(file);
                    }

                    long size = file.length();
                    if (size > maxSize) {
                        file.delete();
                        out.print("big");
                    } else {
                        out.print(file.getName().replace(" ", "%20"));
                        ServerLog.Print("Write pic in: " + file + " size:" + file.length());
                    }
                }
            }
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public Map<String, String> getData() {
        return data;
    }
}
