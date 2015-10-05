/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.io.File;
import jj.jjPicture;

/**
 *
 * @author Milad
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        String s = "aaa.jpg";
//        ServerLog.Print(s.substring(s.lastIndexOf("."), s.length()));
//        ServerLog.Print(jjPicture.getMargin(new File("D:\\b.jpg")));//By Md
        try {
            if (Server.sendEmail(Server.emailAccount, "mrsalesi@gmail.com", "Test", "hiiii سلام", true, null)) {
                System.out.println("Email Ok send");
            }else{
                System.out.println("EMAIL IS NOT SENT");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
