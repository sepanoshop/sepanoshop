/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jj;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milad
 */
public class jjRss {
 public static List<String> getRssCurrency() {
        List<String> line = new ArrayList<String>();
        try {
            javax.xml.parsers.DocumentBuilderFactory dbFac = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            javax.xml.parsers.DocumentBuilder db = dbFac.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse("http://www.cbi.ir/ExRatesRss.aspx");
            doc.normalize();
            org.w3c.dom.NodeList nodes = doc.getElementsByTagName("item");
            for (int i = 0; i < nodes.getLength(); i++) {
                org.w3c.dom.Node node = nodes.item(i);
                org.w3c.dom.Node descNode = node.getChildNodes().item(3);
                String desc = descNode.getTextContent();
                String[] descParts = desc.split("=");
                for (int j = 0; j < descParts.length; j++) {
                    line.add(descParts[j]);
                }
            }
        } catch (Exception ex) {
        }
        return line;
    }
}
