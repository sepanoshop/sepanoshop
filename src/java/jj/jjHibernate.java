/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Milad
 */
public class jjHibernate {
//
//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml)
//            // config file.
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log the exception.
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//    static int sessionCounter = 0;
//
//    public static Session getSession() {
//        sessionCounter++;
//        ServerLog.Print("Start Session ( " + sessionCounter + " )");
//        return getSessionFactory().openSession();
//    }
//
//    /**
//     *
//     * @param session is current session
//     * @param objects is class object for insert to database
//     * @return true if hibernate act true and insert
//     */
//    public static boolean save(Session session, Object... objects) {
//        Transaction t = session.beginTransaction();
//        try {
//            for (int i = 0; i < objects.length; i++) {
//                session.save(objects[i]);
//            }
//            t.commit();
//            return true;
//        } catch (Exception ex) {
//            t.rollback();
//            ex.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     *
//     * @param objects is class object for insert to database
//     * @return true if hibernate act true and insert
//     */
//    public static boolean save(Object... objects) {
//        Session session = getSession();
//        try {
//            return save(session, objects);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            session.flush();
//        }
//        return false;
//    }
//
//    /**
//     *
//     * @param session is current session
//     * @param persistentClass is Class Name for "Select * from persistentClass"
//     * @param keyObj is like "12" or "new TableId('1','a')" or other...
//     * @return object from persistentClass Entity
//     */
//    public static Object getOne(Session session, Class persistentClass, Serializable keyObj) {
//        try {
//            return session.get(persistentClass, keyObj);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     *
//     * @param persistentClass is Class Name for "Select * from persistentClass"
//     * @param keyObj is like "12" or "new TableId('1','a')" or other...
//     * @return object from persistentClass Entity
//     */
//    public static Object getOne(Class persistentClass, Serializable keyObj) {
//        Session session = getSession();
//        try {
//            return getOne(session, persistentClass, keyObj);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            session.flush();
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param session is current session
//     * @param persistentClass
//     * @return  is Class Name for "Select * from persistentClass"
//     */
//    public static List getAll(Session session, Class persistentClass) {
//        try {
//            return session.createCriteria(persistentClass).list();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * you can use this method for every time when you need only some field from table and not all (NOT * from table)
//     * @param session is current session ;
//     * @param entity is name of your selected class (IS NOT table name);
//     * @param resteriction is like (resteriction.ed("id", 12))
//     * @param neededField is only fields of class that we need those field
//     * @return a list of array Object that length of this array Object depended of your number of neededField parameter
//     */
//    public List<Object[]> getOnlyNeededFields(Session session, Class entity, SimpleExpression resteriction, String... neededField) {
//        Criteria defaultCriteria = session.createCriteria(entity);
//        ProjectionList properties = Projections.projectionList();
//        for (int i = 0; i < neededField.length; i++) {
//            properties.add(Projections.property(neededField[i]));
//        }
//        if (neededField.length > 0) {
//            defaultCriteria.setProjection(properties);
//        }
//        if (resteriction != null) {
//            defaultCriteria.add(resteriction);
//        }
//        List<Object[]> rows = new ArrayList<Object[]>();
//        Iterator it = defaultCriteria.list().iterator();
//        while (it.hasNext()) {
//            Object[] row = (Object[]) it.next();
//            rows.add(row);
//        }
//        return defaultCriteria.list();
//    }
//
//    /**
//     * you can use this method for every time when you need only some field from table and not all (NOT * from table)
//     * @param session is current session ;
//     * @param entity is name of your selected class (IS NOT table name);
//     * @param resteriction is like (resteriction.ed("id", 12))
//     * @param neededField is only fields of class that we need those field
//     * @return a list of array Object that length of this array Object depended of your number of neededField parameter
//     */
//    public List<Object[]> getOnlyNeededFields(Class baseSelectedClass, SimpleExpression resteriction, String... neededField) {
//        return getOnlyNeededFields(getSession(), baseSelectedClass, resteriction, neededField);
//    }
}
