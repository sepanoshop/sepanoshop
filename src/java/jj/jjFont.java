/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Milad
 */
public class jjFont {

    public static Font myFontTahoma11 = new Font("Tahoma", Font.PLAIN, 11);
    public static Font myFontTahoma12 = new Font("Tahoma", Font.PLAIN, 12);
    public static Font myFontTahoma14 = new Font("Tahoma", Font.PLAIN, 14);
    public static Font myFontTahoma16 = new Font("Tahoma", Font.PLAIN, 16);
    public static Font myFontTahoma18 = new Font("Tahoma", Font.PLAIN, 18);
    public static Font myFontTahoma20 = new Font("Tahoma", Font.PLAIN, 20);
    public static Font myFontTahoma22 = new Font("Tahoma", Font.PLAIN, 22);
    public static Font myFontTahoma24 = new Font("Tahoma", Font.PLAIN, 24);

    public static Dimension getStringPixelSize(String str, Font font) {
        BufferedImage dimg = new BufferedImage(100, 100, 1);
        Graphics2D gg = dimg.createGraphics();
        gg.drawString(str, 0, 0);
        gg.setFont(font);
        FontMetrics fm = gg.getFontMetrics();
        return new Dimension(fm.stringWidth(str), fm.getHeight());
    }

    public static int getMaxWidth(java.util.List<String> stringList, Font font) {
        int maxWidth = 1;
        for (int i = 0; i < stringList.size(); i++) {
            int ismax = jjFont.getStringPixelSize(stringList.get(i), font).width;
            if (maxWidth < ismax) {
                maxWidth = ismax;
            }
        }
        return maxWidth;
    }
}
