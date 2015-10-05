///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cms.tools;
//
//import java.util.ArrayList;
//import java.util.List;
//import jj.jjCalendar_IR;
//
///**
// *
// * @author MOHAMMAD
// */
//public class GoodStudentSms {
//
//    public static void Send(String fullName, String sex, String mob, String TotalScore, String ClassName) {
//        try {
//            StringBuilder Message = new StringBuilder();
//            Message.append("دانشجوی گرامی");
//            Message.append(" ");
//            String MisOrSir = "";
//            if (sex.equals("آقای")) {
//                MisOrSir = "جناب";
//            }
//            if (sex.equals("خانم")) {
//                MisOrSir = "سرکار";
//            }
//            Message.append(MisOrSir);
//            Message.append(" ");
//            Message.append(sex);
//            Message.append(" ");
//            Message.append(fullName);
//            Message.append(" ");
//            Message.append("کسب نمره عالی در کلاس");
//            Message.append(System.getProperty("line.separator"));
//            Message.append(ClassName);
//            Message.append(System.getProperty("line.separator"));
//            Message.append("را به شما تبریک می گوییم");
//            Message.append(System.getProperty("line.separator"));
//            Message.append("موسسه زبان هفت اقلیم");
//            List<String> numbers = new ArrayList<String>();
////            numbers.add(("09134053479"));//TODO: del
//            numbers.add(mob); //TODO:uncomment
//            jjCalendar_IR ir = new jjCalendar_IR();
//            String Today = ir.getYear_4length()+ir.getMonth_2length()+ir.getDay_2length() ;
////            String sendbatchMassages =  cms.cms.SMS.insert( Message.toString(), numbers.toString(),"دانشجوی نمونه","","",fullName,"", Integer.valueOf(Today));            
//        } catch (Exception e) {
//            ServerLog.Print(e);
//        }
//    }
//}
