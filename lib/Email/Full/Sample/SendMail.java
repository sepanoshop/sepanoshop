/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author arjun Libraries Needed: mail.jar, commons-net-1.4.1.jar,
 * commons-lang-2.1.jar, commons-email-1.1.jar, commons-collections-3.1.jar,
 * activation.jar
 */
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import cms.tools.ServerLog;
public class SendMail {

    private HtmlEmail email;
    private StringBuffer htmlMsg;

    public HtmlEmail createEntity(String to_email, String msg_header,
            String msg_data, String smtp_server,
            String smtp_from, String smtp_user,
            String smtp_pwd, boolean sendMail) {
        try {
            email = new HtmlEmail();
            email.setHostName(smtp_server);
            email.addTo(to_email);
            email.setFrom(smtp_from);

            //If you are using Non Authenticated emails then you can ignore this part.
            email.setAuthentication(smtp_user, smtp_pwd);

            email = this.setRecipients(to_email, email);
            htmlMsg = new StringBuffer("");
        } catch (EmailException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public void attachFiles(String filePath, String name, String desc) {
        try {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(filePath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            //attachment.setDisposition(EmailAttachment.INLINE);
            attachment.setDescription(desc);
            attachment.setName(name);
            email.attach(attachment);

        } catch (EmailException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void embedImage(String filePath) {
        try {
            String cid = email.embed(new File(filePath));
            htmlMsg.append("<img src=\"cid:" + cid + "\">");

        } catch (EmailException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMsg(String msg) {
        htmlMsg.append(msg);
    }

    public void addSubj(String subj) {
        email.setSubject(subj);
    }

    public void SendMail_HTML() {
        try {
            email.setHtmlMsg(htmlMsg.toString());
            email.setTextMsg("Your email client does not support HTML messages");
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public HtmlEmail setRecipients(String recipients, HtmlEmail email)
            throws EmailException {
        String recipientsArray[] = null;
        if (recipients != null) {
            recipientsArray = recipients.split(",");
        }
        String as[] = recipientsArray;
        for (int i = 0; i < as.length; i++) {
            String recipient = as[i];
            email.addTo(recipient);
        }
        return email;

    }

    public static void main(String[] args) {

        //Use the below code within the class to invoke the send mail function.
        SendMail mailObj = new SendMail();

        mailObj.createEntity("to_email", "msg_header", "msg_data", "smtp_server", "smtp_from", "smtp_user", "smtp_pwd", false);
        mailObj.addSubj("This is a test");
        mailObj.addMsg("Hello World<BR>");
        mailObj.embedImage("./lib/logo.bmp");
        mailObj.attachFiles("./lib/logo.bmp", "logo.bmp", "LOGO");
        mailObj.addMsg("Can you see the embedded image<BR>");
        mailObj.addMsg("Thank you<BR>");
        mailObj.SendMail_HTML();
        ServerLog.Print("Mail Sent");

    }
}
