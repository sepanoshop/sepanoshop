package jj;

/**
 * A "splash screen" to show while waiting program to load
 */
import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class jjSplashScreen extends JWindow {

    int BORDER = 1;

    public jjSplashScreen(String filename) {

        JLabel jlbl = new JLabel();
        if (new File(filename).exists()) {
            jlbl.setIcon(jjPicture.getIconImage(filename));
        }
        if (filename.startsWith("/")) {
            jlbl = new JLabel(new ImageIcon(getClass().getResource(filename)));
        }
        Color clrBlack = new Color(0, 0, 0);
        jlbl.setBorder(new LineBorder(clrBlack, BORDER));
        getContentPane().add(jlbl, BorderLayout.CENTER);
        pack();
        Dimension dim_scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim_lblSize = jlbl.getPreferredSize();
        setLocation(dim_scrSize.width / 2 - (dim_lblSize.width / 2),
                dim_scrSize.height / 2 - (dim_lblSize.height / 2));
    }

    // Close the screen
    public void Splash_Close() {
        setVisible(false);
        dispose();
    }
}
