/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jj;

import cms.tools.ServerLog;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author Milad
 */
public class jjFileXml {

    public static void sampleXML_Write(String XMLpath) {
        try {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbfac.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("root");
            Comment com = doc.createComment("Books Definition");
            Element book1 = doc.createElement("book");
            book1.setAttribute("Code", "1");
            Text txt = doc.createTextNode("Java");

            book1.appendChild(txt);
            root.appendChild(com);
            root.appendChild(book1);
            doc.appendChild(root);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            File f = new File("F:\\a.xml");
            FileOutputStream fos = new FileOutputStream(f);
            StreamResult res = new StreamResult(fos);
            DOMSource src = new DOMSource(doc);
            t.transform(src, res);
            fos.close();
//          <?xml version="1.0" encoding="UTF-8" ?>
//          - <root>
//          - <!-- Books Definition  -->
//          <book Code="1">Java</book>
//          </root>
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }

    public static void sampleXML_read(String XMLpath) {
        try {
            javax.xml.parsers.DocumentBuilderFactory dbFac = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            javax.xml.parsers.DocumentBuilder db = dbFac.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(XMLpath);
            doc.normalize();
            org.w3c.dom.NodeList nodes = doc.getElementsByTagName("root");
            //==============================================================
//          <?xml version="1.0" encoding="UTF-8" ?>
//          - <root>
//          - <!-- Books Definition  -->
//          <book Code="1">Java</book>
//          </root>
            //==============================================================
            ServerLog.Print(doc.getInputEncoding());//UTF-8
            ServerLog.Print(doc.getXmlVersion());//1.0
            for (int i = 0; i < nodes.getLength(); i++) {
                org.w3c.dom.Node node = nodes.item(i).getChildNodes().item(1);
                ServerLog.Print(node.getTextContent());//java
                ServerLog.Print(node.getNodeName());//book
                ServerLog.Print(node.getAttributes().item(0));//Code="1"
                ServerLog.Print(node.getAttributes().item(0).getNodeName());//Code
                ServerLog.Print(node.getAttributes().item(0).getTextContent());//1
            }

        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }

}
