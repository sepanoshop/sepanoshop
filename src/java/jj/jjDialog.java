/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import java.util.List;

/**
 *
 * @author Milad
 */
public class jjDialog {

    private static String LastOpen = jjOsInfo.getTempFilePath() + "\\LastOpen.txt";
    public static Font myFontTahoma12 = new Font("Tahoma", Font.PLAIN, 13);
    public static Icon myIconOnDialog = null;

    /**
     *
     * Show a Message Dialog with blue circle OK buttomn
     */
    public static void alert_Blue(Component parentComponent, String title, String message) {
        if (Setting.isLanguagePersian()) {
            CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{"تائید"}, 0, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        javax.swing.JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * Show a Message Dialog with yellow triangle OK buttomn
     */
    public static void alert_Yellow(Component parentComponent, String title, String message) {
        if (Setting.isLanguagePersian()) {
            CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{"تائید"}, 0, JOptionPane.WARNING_MESSAGE);
            return;
        }
        javax.swing.JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     *
     * Show a Message Dialog withoutLogo
     */
    public static void alert_WhithoutLogo(Component parentComponent, String title, String message) {
        if (Setting.isLanguagePersian()) {
            CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{"تائید"}, 0, JOptionPane.PLAIN_MESSAGE);
            return;
        }
        javax.swing.JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     *
     * Show a Message Dialog with OK buttomn for Errors
     */
    public static void error_Message(Component parentComponent, String title, String message) {
        if (Setting.isLanguagePersian()) {
            CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{"تائید"}, 0, JOptionPane.ERROR_MESSAGE);
            return;
        }
        javax.swing.JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * Show a Question Message Dialog with Yes_No buttomns <br/><br/>
     *@return with click on yes return true else return false
     */
    public static boolean YES_NO(Component parentComponent, String title, String message) {
        int result = 0;
        if (Setting.isLanguagePersian()) {
            result = CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{"بلی", "خیر"}, 0, JOptionPane.QUESTION_MESSAGE);
            return (result == 0 ? true : false);
        }
        result = CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{"Yes", "No"}, 0, JOptionPane.QUESTION_MESSAGE);
        return (result == 0 ? true : false);
    }

    /**
     * Show a Question Message Dialog with Yes_No_Cancel buttomns <br/><br/>
     *@return   Yes      buttomn return   2 <br/>
     *@return   No       buttomn return   1 <br/>
     *@return   Cancel   buttomn return   0 <br/>
     *@return   Close    buttomn return  -1 <br/>
     */
    public static int YES_NO_CANCEL(Component parentComponent, String title, String message) {
        if (Setting.isLanguagePersian()) {
            String yes = "بله";
            String no = "خیر";
            String cancel = "لغو";
            return CUSTOM_BUTTOMS(parentComponent, title, message, new String[]{cancel, no, yes}, 2, JOptionPane.QUESTION_MESSAGE);
        }
        return javax.swing.JOptionPane.showConfirmDialog(parentComponent, message, title, javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**
     *
     * Show a JColorChooser , you can send null for defualtColor
     */
    public static Color COLOR(Color defualtColor) {
        JColorChooser cc = new JColorChooser();
        Color c = cc.showDialog(null, "Select A Color", defualtColor);
        if (c != null) {
            return c;
        } else {
            return defualtColor;
        }
    }

    /**
     *
     * Show a InputDialog with a textfield for get a String from user
     */
    public static String QUESTION_And_GET_TEXT(String title, String question) {
        String s = JOptionPane.showInputDialog(null, question, title, JOptionPane.QUESTION_MESSAGE);
        if (s != null && !s.equals("")) {
            // Click Ok button with filled data
            return s;
        } else if (s == null) {
            // Click Cancel
            return null;
        } else if (s.equals("")) {
            // Click Ok button with empty string
            return "";
        } else {
            return "";
        }
    }
    /**
     *
     * Show a Question Message Dialog with Custom buttomns<br/><br/>
     * iconType can be javax.swing.JOptionPane.QUESTION_MESSAGE or other
     *@return   return   -1     for Close <br/>
     *@return   and else return number of Arrey Selected Click <br/>
     */
    public static int returnValueForCustomConfirmDialog = -1;

    public static int CUSTOM_BUTTOMS(Component parentComponent, String title, String message, String[] buttonValue, int defualtSelectedButtom, int iconType) {
        JLabel mess = new JLabel(message);
        if (Setting.isLanguagePersian()) {
            mess.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            mess.setFont(myFontTahoma12);
            mess.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        }
        Object[] options = new Object[buttonValue.length];
        int length = 0;
        int indexLarg = 0;
        Dimension buttomSize;
        for (int i = 0; i < buttonValue.length; i++) {
            if (buttonValue[i].length() > length) {
                length = buttonValue[i].length();
                indexLarg = i;
            }
        }
        buttomSize = jjFont.getStringPixelSize(buttonValue[indexLarg], myFontTahoma12);
        for (int i = 0; i < buttonValue.length; i++) {
            JButton btn = new JButton(buttonValue[i]);
            btn.setName(String.valueOf(i));
            btn.setPreferredSize(new Dimension(buttomSize.width + 50, 25));
            btn.setFont(myFontTahoma12);
            btn.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JButton b = (JButton) evt.getSource();
                    returnValueForCustomConfirmDialog = Integer.parseInt(b.getName());
                    JDialog dialog = (JDialog) b.getParent().getParent().getParent().getParent().getParent().getParent();
                    dialog.setVisible(false);
                    dialog.dispose();
                }
            });
            options[i] = btn;
        }
        JOptionPane.showOptionDialog(parentComponent, mess, title, JOptionPane.YES_NO_CANCEL_OPTION,
                iconType, myIconOnDialog, options, options[defualtSelectedButtom]);
        return returnValueForCustomConfirmDialog;
    }

    /**
     *
     *  You can use this method like this <br/>
     *  Help.dialog_OPEN(new File("E:\\"), "Picture", "title", true, true, "jpg", "gif", "Png", "bmp", "psd", "tif", "jpeg");
     */
    private static List<File> OPEN_OR_Save(
            Component Parentcomponent,
            File openedLocation,
            String buttonName,
            String comment,
            String title,
            boolean isGetFolder,
            boolean isMultiSelectionEnabled,
            boolean isSave,
            String... extension) {
        JFileChooser chooser = new JFileChooser();
        if (openedLocation != null && openedLocation.exists()) {
            chooser = new JFileChooser(openedLocation);
        }
        chooser.resetChoosableFileFilters();


//        FileDialog fileDialog = new FileDialog(f);
//        fileDialog.setMode(FileDialog.SAVE);
//        fileDialog.setDirectory("D:\\");
//        fileDialog.setVisible(true);
////        fileDialog.setFilenameFilter(new filter)
////        String []s = {"dd","ww","rr"};
//        FilenameFilter v = new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        };
//        v.accept(new File("D:\\1.jpg"),".jpg");
//        v.accept(new File("D:\\1.jpg"),".txt");
//        fileDialog.setFilenameFilter(v);
//        String file = fileDialog.getFile();
//        if (file == null) {
//            return;
//        }

        // select Filter
        // FileFilter Pictures = createFileFilter("Picture", true, "jpg", "gif", "Png", "bmp", "psd", "tif", "jpeg");
        // FileFilter Pictures = createFileFilter(comment, true, extension);
        // FileFilter Pictures =new FileNameExtensionFilter("", "");
        // chooser.setFileSelectionMode(2);
        if (extension.length > 0) {
            if (extension[0] != null && !extension[0].trim().equals("")) {
                chooser.setFileFilter(new FileNameExtensionFilter(
                        createFileNameFilterDescriptionFromExtensions(comment, extension), extension));
            }
        }

        // Title
        chooser.setDialogTitle(title);
        // setToolTipText On all of windows
        // chooser.setToolTipText("Open Windows");

        // Show Button Open And Close
        chooser.setControlButtonsAreShown(true);

        // Drag Enable
        chooser.setDragEnabled(true);

        // set other Name to Open Button for open dialog
        // chooser.setApproveButtonText("Open");

        // multi selection
        chooser.setMultiSelectionEnabled(isMultiSelectionEnabled);

        // select all of filter for show files
        // chooser.setAcceptAllFileFilterUsed(true);

        // if be false show hiden file
        chooser.setFileHidingEnabled(true);

        chooser.setApproveButtonText(buttonName);

        chooser.setAutoscrolls(true);
        chooser.setDoubleBuffered(true);
        chooser.setRequestFocusEnabled(true);
        chooser.setOpaque(true);
        chooser.setInheritsPopupMenu(true);
        chooser.setIgnoreRepaint(true);
        // select file or directory or file & directory

        if (isGetFolder) {
            chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        } else {
            chooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        }
        // chooser.setFileSelectionMode(DIRECTORIES_ONLY);
        // chooser.setFileSelectionMode(FILES_AND_DIRECTORIES);

        //chooser.getChoosableFileFilters();

        // remove 'All file' filter from filtters
        // chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());

        // set selected FileFilter
        // chooser.setFileFilter(gifFilter);

        // Open Or Save Dialog & Titel
        // int retval = chooser.showDialog(null, "Title");
        // retval = chooser.showSaveDialog(null);
        int retval = 0;
        if (isSave) {
            retval = chooser.showSaveDialog(Parentcomponent);
        } else {
            retval = chooser.showOpenDialog(Parentcomponent);

        }
        List<File> list = new ArrayList<File>();
        if (!isSave) {
//            if (isGetFolder) {
//                for (int i = 0; i < chooser.getSelectedFiles().length; i++) {
//                    if (chooser.getSelectedFiles()[i].isDirectory()) {
//                        list.add(chooser.getSelectedFiles()[i]);
//                    }
//                }
//                return list;
//            } else {
//                for (int i = 0; i < chooser.getSelectedFiles().length; i++) {
//                    if (chooser.getSelectedFiles()[i].isFile()) {
//                        ServerLog.Print("dddddddddd");
//                        list.add(chooser.getSelectedFiles()[i]);
//                    }
//                }
//                return list;
//            }
            list.add(chooser.getSelectedFile());
            return list;
        } else {
            if (retval == javax.swing.JFileChooser.APPROVE_OPTION) {
                list.add(chooser.getSelectedFile());
                return list;
            } else {
                return list;
            }
        }

        // FilenameFilter faafs = new onlyPic("jpg");
        // FileDialog aaqq = new FileDialog(myJframe, "FileDialog", FileDialog.LOAD);
        // aaqq.setFilenameFilter(faafs);
        // aaqq.setVisible(true);
    }
//public static FileFilter createFileFilter(String description, boolean showExtensionInDescription, String... extensions) {
//        if (showExtensionInDescription) {
//            description = createFileNameFilterDescriptionFromExtensions(description, extensions);
//        }
//        return new FileNameExtensionFilter(description, extensions);
//    }

    private static String createFileNameFilterDescriptionFromExtensions(String description, String... extensions) {
        String fullDescription = (description == null) ? "(" : description.replaceAll(".", "") + " (";
        // build the description from the extension list
        fullDescription += "." + extensions[0];
        for (int i = 1; i < extensions.length; i++) {
            fullDescription += ", .";
            fullDescription += extensions[i];
        }
        fullDescription += ")";
        return fullDescription;
    }

    /**
     *
     *  You can use this method like this <br/>
     *  Help.dialog_OPEN(new File("E:\\"), "Picture", "title", true, true, "jpg", "gif", "Png", "bmp", "psd", "tif", "jpeg");
     */
    public static List<File> OPEN(
            Component Parentcomponent, File openedLocation,
            String buttonName, String comment, String title, boolean isGetFolder,
            boolean isMultiSelectionEnabled, String... extension) {
        if (openedLocation == null || !openedLocation.exists()) {
            File openPath = new File(LastOpen);
            if (openPath.exists()) {
                openedLocation = new File(jjFileTxt.read(LastOpen));
            } else {
                openedLocation = new File(jjOsInfo.getDesktopLocation().getAbsolutePath());
            }
        }
        List<File> dialog_OPEN = OPEN_OR_Save(Parentcomponent, openedLocation, buttonName,
                comment, title, isGetFolder, isMultiSelectionEnabled, false, extension);
        if (dialog_OPEN != null) {
            if (dialog_OPEN.size() > 0) {
                if (dialog_OPEN.get(0) != null) {
                    if (dialog_OPEN.get(0).exists()) {
                        if (dialog_OPEN.get(0).isDirectory()) {
                            jjFileTxt.write(LastOpen, dialog_OPEN.get(0).getAbsolutePath(), false, false);
                        } else {
                            jjFileTxt.write(LastOpen, dialog_OPEN.get(0).getParent(), false, false);
                        }
                    }
                } else {
                    dialog_OPEN.remove(0);
                }
            }
        }
        return dialog_OPEN;
    }

    /**
     *
     *  You can use this method like this <br/>
     *  Help.dialog_SAVE(null, new File("E:\\"), "Save", "Note", "title", false, "txt");
     */
    public static File SAVE(
            Component Parentcomponent, File openedLocation,
            String buttonName, String comment, String title, boolean isGetFolder,
            String extension) {
        if (openedLocation == null || !openedLocation.exists()) {
            File openPath = new File(LastOpen);
            if (openPath.exists()) {
                openedLocation = new File(jjFileTxt.read(LastOpen));
            } else {
                openedLocation = new File(jjOsInfo.getDesktopLocation().getAbsolutePath());
            }
        }
        List<File> f = OPEN_OR_Save(Parentcomponent, openedLocation, buttonName,
                comment, title, isGetFolder, false, true, extension);
        if (f.size() > 0) {
            if (f.get(0).isDirectory()) {
                jjFileTxt.write(LastOpen, f.get(0).getAbsolutePath(), false, false);
            } else {
                jjFileTxt.write(LastOpen, f.get(0).getParent(), false, false);
            }
            if (isGetFolder) {
                return f.get(0);
            }
            if (!extension.equals("")) {
                if (f.get(0).getAbsolutePath().toLowerCase().endsWith("." + extension.toLowerCase())) {
                    return f.get(0);
                } else {
                    return new File(f.get(0).getAbsolutePath() + "." + extension.toLowerCase());
                }
            } else {
                return f.get(0);
            }
        } else {
            return null;
        }
    }
}
