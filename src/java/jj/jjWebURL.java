/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.net.*;
import java.util.*;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjWebURL {

    /**
     * Reads a web page into a StringBuilder object
     * and prints it out to console along with the
     * size of the page.
     * sample: getWebSite("http://www.Yahoo.com")
     * sample: getWebSite("http://www.microsoft.com/windowsserver2003/docs/NTMigrate.pdf")
     */
    public static String getWebSiteSource(String WebSiteAddress) {

        String html = "";
        try {
            URL url = new URL(WebSiteAddress);
            URLConnection urlc = url.openConnection();
            Scanner inputs = new Scanner(urlc.getInputStream(), "UTF-8");
            while (inputs.hasNext()) {
                html += inputs.nextLine().trim();
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
        return html;
    }

    public static void download(String WebSiteFileAddress, String writeAddress) {
        try {
            URL url = new URL(WebSiteFileAddress);
            URLConnection urlc = url.openConnection();
            jjFile.doCopyFileByInputOutputStream(urlc.getInputStream(), writeAddress);
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    public static void getHostsIPAddress(String SiteName) {
        try {
            InetAddress inetHost = InetAddress.getByName(SiteName);
            ServerLog.Print("IP of " + inetHost.getHostName() + " is: " + inetHost.getHostAddress());
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }
    
    
}
