/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjPicture {

    public static JLabel setImage_By_HTML_TO_Lable(String path, JLabel lbl, int Width, int Height) {
        lbl.setSize(Width, Height);
        setImage_By_HTML_TO_Lable(path, lbl);
        return lbl;
    }

    public static void savePicture(String path, Image img, String newPath, int Width, int Height) {
        int intW = Width;
        int intH = Height;
        File f = new File(path);
        String getAbsolutePath = f.getName();
        String extension = getAbsolutePath.substring(getAbsolutePath.lastIndexOf(".") + 1, getAbsolutePath.length());
        BufferedImage dimg; // 1 pic good // 2 pic bad background good
        if (extension.toLowerCase().contains("png")) {
            dimg = new BufferedImage(intW, intH, 2); // 1 pic good // 2 pic bad background good
        } else {
            dimg = new BufferedImage(intW, intH, 1); // 1 pic good // 2 pic bad background good //BufferedImage.TYPE_INT_RGB
        }
        Graphics2D gg = dimg.createGraphics();
        gg.drawImage(img, 0, 0, intW, intH, null);
        gg.dispose();
        try {
            ImageIO.write(dimg, extension, new File(newPath));
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    public static JLabel setImage_By_HTML_TO_Lable(String path, JLabel lbl) {
        Image image = new javax.swing.ImageIcon(jjPicture.getIconImageFromURL(jjPicture.getURL(path))).getImage();
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int newW = image.getWidth(null);
        int newH = image.getHeight(null);
        Dimension size = lbl.getSize();
        newW = size.width;
        newH = size.width * h / w;
        if (newH > size.height) {
            newH = size.height;
            newW = w * size.height / h;
        }
        String html =
                "<html><body><p><img src='file:///"
                + path + "' width='"
                + newW + "' height='"
                + newH + "' border='0' /></p></body></html>";
        lbl.setText(html);
        return lbl;
    }

    /**
     *
     * get resolution from picture
     */
    public static int getResolutionFromPicture(String filePath) {
        try {
//            com.lowagie.text.Image img2 = com.lowagie.text.Image.getInstance(filePath);
//            return img2.getDpiY();
            return -1;

        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getResolutionFromPicture()", ex.getMessage());
            return -1;

        }
    }

    /**
     *
     * get a URL and give you a Image
     */
    public static Image getIconImageFromURL(java.net.URL picSource) {
        java.net.URL imgURL = picSource;//Login.class.getResource(Source);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }

    /**
     *
     * get a URL and give you a Image
     */
    public static ImageIcon getImageIcon(String path) {
        java.net.URL imgURL = getURL(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }

    public static ImageIcon getIconImage(String picSource) {
        java.net.URL imgURL = getURL(picSource);//Login.class.getResource(Source);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }

    public static URL getURL(String path) {
        try {
            return new URL("file:///" + path);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * Create a image for your text (like "D:\\Milad.gif")
     */
    public static void getGifImageFromText(String myData, String pathOfFile) {
        try {
            int width = 400;
            int height = 200;
            // Create a buffered image in which to draw
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // Create a graphics contents on the buffered image
            Graphics2D g2d = bufferedImage.createGraphics();
            // Draw graphics
            g2d.setBackground(Color.RED);
            g2d.setColor(Color.white);
            g2d.fillRect(5, 5, width - 10, height - 10);
            g2d.setColor(Color.BLACK);
            g2d.setFont(myFontTahoma22);
            g2d.drawString(myData, 50, 47);
            g2d.dispose();
            ImageIO.write(bufferedImage, "gif", new File(pathOfFile));
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getGifImageFromText()", ex.getMessage());
            return;
        }
    }

    /**
     *
     * Change size of JPG, PNG, Jpeg and GIF File Pic (GIF file withoud
     * animation)
     */
    public static void doChangeSizeOfPic(File srcFrom, File srcTo, int pixelSize) {
        try {
            if (srcFrom.exists()) {
                ImageIcon imIcon = new ImageIcon(srcFrom.getAbsolutePath());
                Image img = null;
                img = imIcon.getImage();
                int newW = 100;
                int newH = 100;
                int LastW = img.getWidth(null);
                int LastH = img.getHeight(null);
                if (LastW > LastH) {
                    newW = pixelSize;
                    newH = (LastH * pixelSize / LastW);
                } else {
                    newH = pixelSize;
                    newW = (LastW * pixelSize / LastH);
                }
                ServerLog.Print("Size:" + LastW + " x " + LastH);
                String Extension = "JPG";
                int imageType = 1;
                if (srcFrom.getAbsolutePath().toLowerCase().endsWith("png")) {
                    imageType = 2;
                    Extension = "PNG";
                } else if (srcFrom.getAbsolutePath().toLowerCase().endsWith("jpeg")) {
                    Extension = "JPEG";
                    imageType = 1;
                } else if (srcFrom.getAbsolutePath().toLowerCase().endsWith("gif")) {
                    Extension = "GIF";
                    imageType = 1;
                }
                BufferedImage dimg = new BufferedImage(newW, newH, imageType);
                Graphics2D gg = dimg.createGraphics();
                gg.drawImage(img, 0, 0, newW, newH, null);

//                gg.setFont(new Font("b Mitra", Font.PLAIN, 52));
//                gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                FontMetrics fm = gg.getFontMetrics();
//                gg.setColor(Color.BLACK);
//                gg.setBackground(Color.BLACK);
//                Vector<String> v = new Vector<String>();
//                for (int i = 0; i < 50; i++) {
//                    v.add("میلاد جمال زاده 14 ساله از تهران");
//                }
//                int h = fm.getHeight() + 15;
//                for (int i = 0; i < v.size(); i++) {
//                    gg.drawString(v.get(i), newW - 40 - fm.stringWidth(v.get(0)), (h * (i + 1)));
//                }
                srcTo.getParentFile().mkdirs();
                ImageIO.write(dimg, Extension, srcTo);
                gg.dispose();
            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doChangeSizeOfPic()", ex.getMessage());
            return;
        }
    }

    public static Dimension getSize(File srcPicFile) {
        try {
            if (srcPicFile.exists()) {
                if (srcPicFile.getAbsolutePath().toLowerCase().endsWith(".jpg") || srcPicFile.getAbsolutePath().toLowerCase().endsWith(".png") || srcPicFile.getAbsolutePath().toLowerCase().endsWith(".gif")) {
                    ImageIcon imIcon = new ImageIcon(srcPicFile.getAbsolutePath());
                    Image img = imIcon.getImage();
                    return new Dimension(img.getWidth(null), img.getHeight(null));
                }
            }
        } catch (Exception ex) {
            return new Dimension();
        }
        return new Dimension();
    }

    public static Color getColor(File srcPicFile, int x, int y) {
        try {
            if (srcPicFile.exists()) {
                if (srcPicFile.getAbsolutePath().toLowerCase().endsWith(".jpg") || srcPicFile.getAbsolutePath().toLowerCase().endsWith(".png") || srcPicFile.getAbsolutePath().toLowerCase().endsWith(".gif")) {
                    BufferedImage img2 = ImageIO.read(srcPicFile); // Throws IOException
                    int rgB = img2.getRGB(x, y);
                    return new Color(rgB);
                }
            }
        } catch (Exception ex) {
            return Color.BLACK;
        }
        return Color.BLACK;
    }

    public static int getMargin(File srcPicFile) {
        int height = 0;
        try {
            Dimension size = jjPicture.getSize(srcPicFile);
            int w = size.width / 2;
            int h = size.height / 2;
            ServerLog.Print("w:" + w);
            ServerLog.Print("h:" + h);
            Color c = Color.BLACK;
            int counter = 0;
            for (int i = 0; i < h; i += 1) {
                if (counter > 5) {
                    i = h + 1;
                    height = i - 154;
                }
                Color color = jjPicture.getColor(srcPicFile, w, i);
                if (i == 0) {
                    c = color;
                }
                if (c.getRed() == color.getRed() || c.getGreen() == color.getGreen() || c.getBlue() == color.getBlue()) {
                    counter += 1;
                } else {
                    counter = 0;
                }
                c = color;
            }
        } catch (Exception ex) {
            return 0;
        }
        return height;
    }

    public static void doChangeSizeOfPic(File srcFrom, File srcTo, int pixelSize, Color font) {
        try {
            if (srcFrom.exists()) {
                ImageIcon imIcon = new ImageIcon(srcFrom.getAbsolutePath());
                Image img = null;
                img = imIcon.getImage();
                int newW = 100;
                int newH = 100;
                int LastW = img.getWidth(null);
                int LastH = img.getHeight(null);
                if (LastW > LastH) {
                    newW = pixelSize;
                    newH = (LastH * pixelSize / LastW);
                } else {
                    newH = pixelSize;
                    newW = (LastW * pixelSize / LastH);
                }
                String Extension = "JPG";
                int imageType = 1;
                if (srcFrom.getAbsolutePath().toLowerCase().endsWith("png")) {
                    imageType = 2;
                    Extension = "PNG";
                } else if (srcFrom.getAbsolutePath().toLowerCase().endsWith("jpeg")) {
                    Extension = "JPEG";
                    imageType = 1;
                } else if (srcFrom.getAbsolutePath().toLowerCase().endsWith("gif")) {
                    Extension = "GIF";
                    imageType = 1;
                }
                BufferedImage dimg = new BufferedImage(pixelSize, pixelSize, imageType);
                Graphics2D gg = dimg.createGraphics();
                gg.setBackground(font);
                gg.setColor(font);
                gg.drawImage(img, (pixelSize / 2) - (newW / 2) - 1, (pixelSize / 2) - 1 - (newH / 2),
                        newW, newH, font, null);
                gg.setFont(new Font("Tahoma", Font.PLAIN, 17));
//                gg.drawString("آتلیه همسران", 10, 20);
//                gg.drawString("آتلیه همسران", pixelSize - 105, pixelSize - 20);
//                gg.setColor(Color.BLACK);
//                gg.drawString("آتلیه همسران", pixelSize - 105, 20);
//                gg.drawString("آتلیه همسران", 10, pixelSize - 20);
                srcTo.getParentFile().mkdirs();
                ImageIO.write(dimg, Extension, srcTo);
                gg.dispose();
            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doChangeSizeOfPic()", ex.getMessage());
            return;
        }
    }
    public static Font myFontTahoma12 = new Font("Tahoma", Font.PLAIN, 12);
    public static Font myFontTahoma14 = new Font("Tahoma", Font.PLAIN, 14);
    public static Font myFontTahoma16 = new Font("Tahoma", Font.PLAIN, 16);
    public static Font myFontTahoma18 = new Font("Tahoma", Font.PLAIN, 18);
    public static Font myFontTahoma20 = new Font("Tahoma", Font.PLAIN, 20);
    public static Font myFontTahoma22 = new Font("Tahoma", Font.PLAIN, 22);
    public static Font myFontTahoma24 = new Font("Tahoma", Font.PLAIN, 24);
}
