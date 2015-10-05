/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author Milad
 */
public class jjReflection {

    public static Object callMethodByName(Class ClassOfMethod, String methodName, Object... methodParameters) {
        try {
//            Method m[] = ClassOfMethod.getMethods();
             ClassOfMethod.getMethod(methodName).invoke(ClassOfMethod, methodParameters);
//            for (int i = 0; i < m.length; i++) {
//                if (m[i].getName().equals(methodName)) {
//                    return m[i].invoke(ClassOfMethod, methodParameters);
//                }
//            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }
}
