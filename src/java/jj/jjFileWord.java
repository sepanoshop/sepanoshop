/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.io.File;
import java.util.List;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

/**
 *
 * @author Milad
 */
public class jjFileWord {

    public static void writeDocx(String Title, String Subtitle, List<String> paragraph, File docxPath) {
        try {
//            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
//            MainDocumentPart mainPart = wordMLPackage.getMainDocumentPart();
//            mainPart.addStyledParagraphOfText("Title", Title);
//            mainPart.addStyledParagraphOfText("Subtitle", Subtitle);
//            for (int i = 0; i < paragraph.size(); i++) {
//                mainPart.addParagraphOfText(paragraph.get(i));
//            }
//            wordMLPackage.save(docxPath);
        } catch (Exception ex) {
        }
    }

    public static List<Object> readDocx(File docxPath) {
        try {
//            WordprocessingMLPackage wordMLPackage2 = WordprocessingMLPackage.load(docxPath);
//            return wordMLPackage2.getMainDocumentPart().getContent();
        } catch (Exception ex) {
            return null;
        }
        return null;
    }
}
