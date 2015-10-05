/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.filechooser.FileSystemView;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjOsInfo {

    /**
     *
     * get whith and height from screen
     */
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     *
     * get on hand font on this system
     */
    public static String[] getFontsNameOnSystem() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    /**
     *
     * get address jar file
     */
    public static String getProgramAddress() {
        if (programAddress.equals("")) {
            programAddress = System.getProperty("user.dir");
        }
        return programAddress;
    }
    static String programAddress = "";

    /**
     *
     * get address jar file
     */
//    public static String getProgramAddress(javax.servlet.http.HttpSession session) {
//        return session.getServletContext().getRealPath("");
//    }
    /**
     *
     * get Memory Info
     */
    public static String getMemoryInfo() {
        return Runtime.getRuntime().freeMemory() + " byte is Free memory." + " \n"
                + Runtime.getRuntime().maxMemory() + " byte is Max memory that JVM allow use that.\n"
                + Runtime.getRuntime().totalMemory() + " byte is Used memory.\n";

    }

    /**
     *
     * get Line separator (like /n on UNIX)
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }
    public static String sep = "";

    /**
     *
     * get File separator ('/' on UNIX)
     */
    public static String getFileSeparator() {
        return File.pathSeparator;
    }

    /**
     *
     * get Desktop Location File
     */
    public static File getDesktopLocation() {
        if (desktopLocation == null) {
            desktopLocation = FileSystemView.getFileSystemView().getHomeDirectory();
        }
        return desktopLocation;
    }
    static File desktopLocation = null;

    /**
     *
     * get Operating system name
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    /**
     *
     * get Path separator (like ':' on UNIX)
     */
    public static String getPathSeparator() {
        return System.getProperty("path.separator");
    }

    /**
     *
     * get Screen Shot from your monitor
     */
    public static void getScreenshot(File outJPG_FilePath) {
        try {
            String outFileName = outJPG_FilePath.getAbsolutePath().toLowerCase().endsWith(".jpg")
                    ? outJPG_FilePath.getAbsolutePath()
                    : outJPG_FilePath.getAbsolutePath() + ".jpg";
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            // save captured image to PNG file
            ImageIO.write(image, "jpg", new File(outFileName));
            // give feedback
            ServerLog.Print("Saved screen shot (" + image.getWidth()
                    + " x " + image.getHeight() + " pixels) to file \""
                    + outFileName + "\".");
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getScreenshot()", ex.getMessage());
        }
    }

    /**
     *
     * set Location Of Mouse in monitor
     */
    public static void setMouseLocationChange(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "setMouseLocationChange()", ex.getMessage());
        }
    }

    /**
     *
     * do Mouse click on Monitor
     */
    public static void doMouseClick(boolean isDoubleClick) {
        try {
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            if (isDoubleClick) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doMouseClick()", ex.getMessage());
        }
    }

    /**
     *
     * get Color from one point of monitor
     */
    public static Color getColorFromOnePointOfMonitor(int x, int y) {
        try {
            Robot robot = new Robot();
//            ServerLog.Print(robot.getAutoDelay());
            return robot.getPixelColor(x, y);
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getColorFromOnePointOfMonitor()", ex.getMessage());
            return null;
        }
    }

    public static void setIconImageToFrame(JFrame frame, String iconPath) {
        try {

            if (iconPath.startsWith("/")) {
                frame.setIconImage(jjPicture.getIconImageFromURL(frame.getClass().getResource(iconPath)));
            } else {
                File f = new File(iconPath);
                if (f.exists()) {
                    frame.setIconImage(jjPicture.getIconImageFromURL(f.toURL()));
                }
            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "setIconImageToFrame()", ex.getMessage());
            return;
        }

    }

    /**
     *
     *  get MacAddress from windows
     */
    public static Vector<String> getMacAddress() {
        Vector<String> macs = new Vector<String>();
        Scanner input2;
        try {
            Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
            input2 = new Scanner(p.getInputStream(), "UTF-8");
            while (input2.hasNext()) {
                String s = input2.nextLine().trim();
                s = s.substring(1, 18);
                if (!s.trim().equals("")) {
                    macs.add(s);
                }
            }
            return macs;
        } catch (Exception ex) {
            jjError.Handler(ex);
            return macs;
        }
    }

    /**
     *
     *  get Unique number
     */
    public static int getUniqueNumber() {
        String all = "";
        int hashCode = 0;
        try {
            Vector<String> macAddress = jjOsInfo.getMacAddress();
            for (int i = 0; i < macAddress.size(); i++) {
                all += macAddress.get(i);
            }
            List<String> driveList = jjOsInfo.getMainDriveList();
            for (int i = 0; i < driveList.size(); i++) {
                all += driveList.get(i);
            }
            hashCode = all.hashCode();
        } catch (Exception ex) {
            jjError.Handler(ex);
            return 1;
        }
        return hashCode < 0 ? -hashCode : hashCode;
    }

    /**
     *
     *  get Unique number key
     */
    public static int getUniqueNumberKey(int uniqueNumber) {
        String aKey = "1342";
        try {
            int key = uniqueNumber / 20 / 23 / 3 * 5 / 2 / 100;
            String sKey = key + "";
            aKey = key > 9999 ? sKey.substring(0, 5) : sKey;
            aKey = aKey.length() < 2 ? aKey + 232 : aKey;
            aKey = aKey.length() < 3 ? aKey + 23 : aKey;
            aKey = aKey.length() < 4 ? aKey + 2 : aKey;
        } catch (Exception ex) {
            return 1342;
        }
        return Integer.parseInt(aKey);
    }

    /**
     *
     * beep from motherboard
     */
    public static void doBeep() {
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     *
     *  Help.doShowSystemTry("/im/1.png","png",new NewJFrame());
     *  Help.doShowSystemTry("/im/1.jpg","jpg",new NewJFrame());
     *  Help.doShowSystemTry("/im/1.gif","gif ",new NewJFrame());
     */
    public static void doShowSystemTray(String iconPath, String tooltip, JWindow frame) {
        try {
            if (SystemTray.isSupported()) {
                SystemTray st = SystemTray.getSystemTray();
                PopupMenu pm = new PopupMenu();
                MenuItem m1 = new MenuItem("Close");
//                MenuItem m2 = new MenuItem("M2");
                pm.add(m1);
//                pm.add(m2);
                Image img;
                if (iconPath.startsWith("/")) {
                    img = (jjPicture.getIconImageFromURL(frame.getClass().getResource(iconPath)));
                } else {
                    img = jjPicture.getIconImageFromURL(new File(iconPath).toURL());
                }
                TrayIcon tIcon = new TrayIcon(img, tooltip, pm);
                st.add(tIcon);
                m1.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                       System.exit(0);
                    }
                });
            }
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doShowSystemTray()", ex.getMessage());
        };
        return;
    }

    /**
     *
     * execute comment to command prompt and return response from that
     */
    public static Vector<String> doCmdExecute(String comment) {
        Vector<String> v = new Vector<String>();
        try {
            Process p = java.lang.Runtime.getRuntime().exec(comment);
            Scanner input2 = new Scanner(p.getInputStream(), "UTF-8");
            while (input2.hasNext()) {
                v.add(input2.nextLine().trim());
            }
            return v;
        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "doCmdExecute()", ex.getMessage());

            return v;
        }
    }

    /**
     *
     * Open a file in a program like(a.jpg in photoshop)<br/>
     * for open Folder Or File by default program => send programExeAddress = null
     */
    public static void doRenameFolder(String FoldrPath, String newFolderName) {
        try {
            ServerLog.Print("REN \"" + FoldrPath + "\" \"" + newFolderName + "\"");
//            Runtime.getRuntime().exec("REN \"" + FoldrPath + "\" \"" + newFolderName + "\"");
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
    }

    /**
     *
     * Open a file in a program like(a.jpg in photoshop)<br/>
     * for open Folder Or File by default program => send programExeAddress = null
     */
    public static void doCmdOpenFileInOneProgram(String openedFile) {
        doCmdOpenFileInOneProgram(null, openedFile);
    }
    private static int counter = 0;

    /**
     *
     * Open a file in a program like(a.jpg in photoshop)<br/>
     * for open Folder Or File by default program => send programExeAddress = null
     */
    public static void doCmdOpenFileInOneProgram(String programExeAddress, String openedFile) {
        try {
            String defaultName = "Map Picture";
            if (counter == 0) {
                File[] listFiles = jjOsInfo.getTempFilePath().listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].getName().startsWith(defaultName)) {
                        jjFile.delete(listFiles[i].getAbsolutePath());
                    }
                }
            }
            File file = new File(openedFile);
            if (file.exists()) {
                char[] toCharArray = openedFile.toCharArray();
                for (int i = 0; i < toCharArray.length; i++) {
                    if (jjString.persian.contains(String.valueOf(toCharArray[i]))) {
                        counter += 1;
                        int lastIndex = file.getName().lastIndexOf(".");
                        String newName = defaultName + counter + file.getName().substring(lastIndex);
                        String newAddress = jjOsInfo.getTempFilePath() + "\\" + newName;
                        for (int j = 0; j < 1000000000; j++) {
                            if (new File(newAddress).exists()) {
                                counter += 1;
                                newName = defaultName + counter + file.getName().substring(lastIndex);
                                newAddress = jjOsInfo.getTempFilePath() + "\\" + newName;
                            } else {
                                break;
                            }
                        }
                        ServerLog.Print(newAddress);
                        jjFile.doCopyFileByChannel(openedFile, newAddress, false);
                        openedFile = newAddress;
                        break;
                    };
                }
                if (programExeAddress == null) {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"" + openedFile + "\"");
//                    Runtime.getRuntime().exec("Explorer \"" + openedFile + "\"");
                } else {
                    Runtime.getRuntime().exec("\"" + programExeAddress + "\" " + openedFile);
                }
            } else {
                ServerLog.Print("File Not Exists :" + openedFile);
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return;
        }
    }

    /**
     *
     * change Cursor by int model like Cursor.WAIT_CURSOR
     */
    public static void doChangeCursorIcon(Component Parentcomponent, int CursorDotModel) {
//        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor hourglassCursor = new Cursor(CursorDotModel);
        Parentcomponent.setCursor(hourglassCursor);
    }

    /**
     *
     *  get Data About Drives And Her Space
     */
    public static void getDriveInfo() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++) {
            ServerLog.Print("-------------------------------------");
            ServerLog.Print("Drive: " + f[i]);
            ServerLog.Print("Display name: " + fsv.getSystemDisplayName(f[i]));
            ServerLog.Print("getSystemTypeDescription: " + fsv.getSystemTypeDescription(f[i]));
            ServerLog.Print("Is drive: " + fsv.isDrive(f[i]));
            ServerLog.Print("Is floppy: " + fsv.isFloppyDrive(f[i]));
            ServerLog.Print("Readable: " + f[i].canRead());
            ServerLog.Print("Writable: " + f[i].canWrite());
            ServerLog.Print("Total space: " + f[i].getTotalSpace());
            ServerLog.Print("Usable space: " + f[i].getUsableSpace());
        }
    }

    /**
     *
     *  get Drives List
     */
    public static List<String> getDriveList() {
//        getDriveInfo();
        List<String> drive = new ArrayList<String>();
        try {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File[] f = File.listRoots();
            for (int i = 0; i < f.length; i++) {
                drive.add(f[i].toString().replace(":\\", ""));
            }
        } catch (Exception ex) {
            ServerLog.Print("ERROR");
        }
        return drive;
    }

    /**
     *
     *  get Drives List
     */
    public static List<String> getMainDriveList() {
        List<String> drive = new ArrayList<String>();
        try {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File[] f = File.listRoots();
            for (int i = 0; i < f.length; i++) {
                if (fsv.getSystemTypeDescription(f[i]).equals("Local Disk")) {
                    drive.add(f[i].toString().replace(":\\", ""));
                }
            }
            ServerLog.Print(drive);
        } catch (Exception ex) {
            jjError.Handler(ex);
        }
        return drive;
    }

    /**
     *
     *  get Data About one Drive (Like "C")</br>
     *  type can be "LOCAL DISK" , "CD DRIVE" or "REMOVABLE DISK"
     */
    public static DriveInfo getDriveInfo(char driveName) {
        File[] f = File.listRoots();
        double totalSpace_In_GB = 0;
        double freeSpace_In_GB = 0;
        double totalSpace_In_MB = 0;
        double freeSpace_In_MB = 0;
        String type = "";
        String displayName = "";
        boolean isDrive = true;
        boolean isFloppy = true;
        boolean isReadable = true;
        boolean isWritable = true;
        try {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            for (int i = 0; i < f.length; i++) {
                if (f[i].getAbsolutePath().toUpperCase().startsWith(String.valueOf(driveName).toUpperCase())) {
                    double total = new Double(f[i].getTotalSpace());
                    double free = new Double(f[i].getUsableSpace());
                    freeSpace_In_GB = new Double(jjNumber.getRound(free / 1024 / 1024 / 1024, 1));
                    freeSpace_In_MB = new Double(jjNumber.getRound(free / 1024 / 1024, 3));
                    totalSpace_In_GB = new Double(jjNumber.getRound(total / 1024 / 1024 / 1024, 1));
                    totalSpace_In_MB = new Double(jjNumber.getRound(total / 1024 / 1024, 3));
                    type = fsv.getSystemTypeDescription(f[i]);
                    displayName = fsv.getSystemDisplayName(f[i]);
                    isDrive = fsv.isDrive(f[i]);
                    isFloppy = fsv.isFloppyDrive(f[i]);
                    isReadable = f[i].canRead();
                    isWritable = f[i].canWrite();
                }
            }
            return (new DriveInfo(totalSpace_In_GB, freeSpace_In_GB, totalSpace_In_MB, freeSpace_In_MB, type, displayName, isDrive, isFloppy, isReadable, isWritable));
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
    }

    public static class DriveInfo {

        public double totalSpace_In_GB;
        public double freeSpace_In_GB;
        public double totalSpace_In_MB;
        public double freeSpace_In_MB;
        public String type;
        public String displayName;
        public boolean isDrive;
        public boolean isFloppy;
        public boolean isReadable;
        public boolean isWritable;

        public DriveInfo(double totalSpace_In_GB, double freeSpace_In_GB, double totalSpace_In_MB, double freeSpace_In_MB, String type, String displayName, boolean isDrive, boolean isFloppy, boolean isReadable, boolean isWritable) {
            this.totalSpace_In_GB = totalSpace_In_GB;
            this.freeSpace_In_GB = freeSpace_In_GB;
            this.totalSpace_In_MB = totalSpace_In_MB;
            this.freeSpace_In_MB = freeSpace_In_MB;
            this.type = type;
            this.displayName = displayName;
            this.isDrive = isDrive;
            this.isFloppy = isFloppy;
            this.isReadable = isReadable;
            this.isWritable = isWritable;
        }
    }

    /**
     *
     *  User's account name
     */
    public static String getUserName() {
        return System.getProperty("user.name");
    }

    /**
     *
     *  User's home directory
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     *
     *  Operating system version
     */
    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    /**
     *
     *  Default temp file path for this user
     */
    public static File getTempFilePath() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    /**
     *
     *  List of paths to search when loading libraries
     */
    public static String getJavaLibraryPath() {
        return System.getProperty("java.library.path");
    }

    /**
     *
     *  Java installation directory like (C:\Program Files\Java\jdk1.6.0_17\jre)
     */
    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    /**
     *
     *  Java Runtime Environment version
     */
    public static String getJdkVersion() {
        return System.getProperty("java.version");
    }

    /**
     *
     *  get Fields Of Class
     */
    public static java.lang.reflect.Field[] getFieldsOfClass(Object Class) {
        return Class.class.getDeclaredFields();
    }

    /**
     *
     *  get All Information about System
     */
    public static void getAllInfo() {
        String DataAboutSystem = String.valueOf(System.getenv());
        String detailsss[] = DataAboutSystem.split(",");
        for (int i = 0; i < detailsss.length; i++) {
            ServerLog.Print("-->" + i + "  " + detailsss[i]);
        }
    }

    /**
     *
     *  get informtion abour given String Start with info_ ...
     */
    public static String getnfo(String System_Info) {
        return String.valueOf(System.getenv(System_Info));
    }
    public static String Info_USERPROFILE = "USERPROFILE";
    public static String Info_ProgramData = "ProgramData";
    public static String Info_PATHEXT = "PATHEXT";
    public static String Info_TEMP = "TEMP";
    public static String Info_SystemDrive = "SystemDrive";
    public static String Info_ProgramFiles = "ProgramFiles";
    public static String Info_HOMEDRIVE = "HOMEDRIVE";
    public static String Info_USERDOMAIN = "USERDOMAIN";
    public static String Info_ALLUSERSPROFILE = "ALLUSERSPROFILE";
    public static String Info_TMP = "TMP";
    public static String Info_LOGONSERVER = "LOGONSERVER";
    public static String Info_CommonProgramFiles = "CommonProgramFiles";
    public static String Info_OS = "OS";
    public static String Info_HOMEPATH = "HOMEPATH";
    public static String Info_PROCESSOR_LEVEL = "PROCESSOR_LEVEL";
    public static String Info_LOCALAPPDATA = "LOCALAPPDATA";
    public static String Info_COMPUTERNAME = "COMPUTERNAME";
    public static String Info_Windir = "windir";
    public static String Info_SystemRoot = "SystemRoot";
    public static String Info_USERNAME = "USERNAME";
    public static String Info_PSModulePath = "PSModulePath";
    public static String Info_PUBLIC = "PUBLIC";
    public static String Info_Info_ComSpec = "ComSpec";
    public static String Info_APPDATA = "APPDATA";
}
