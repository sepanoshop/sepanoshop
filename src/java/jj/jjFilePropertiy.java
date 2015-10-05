/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.util.*;

/**
 *
 * @author Milad
 */
public class jjFilePropertiy {

    private static Map<String, ResourceBundle> resourceBundleCash = new HashMap<String, ResourceBundle>();

    public static List<String> getKeyList(java.util.ResourceBundle resourceBundle) {
        List<String> keys = new ArrayList<String>();
        Enumeration<String> keysEnum = resourceBundle.getKeys();
        while (keysEnum.hasMoreElements()) {
            keys.add(keysEnum.nextElement());
        }
        return keys;
    }

    /**
     *
     * @param path is like "packName/fileName" = "language/form1_en"
     */
    public static java.util.ResourceBundle getResourceBundle(String path) {
        java.util.ResourceBundle resourceBundle = null;
        try {
            resourceBundle = resourceBundleCash.get(path);
            if (resourceBundle == null) {
                resourceBundle = ResourceBundle.getBundle(path);
                resourceBundleCash.put(path, resourceBundle);
                ServerLog.Print(path + " has read.");
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return resourceBundle;
    }
}
