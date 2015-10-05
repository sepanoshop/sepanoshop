/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milad
 */
public class jjFileVCF {

    public static List<jjFileVCF_Entity> read(String vcfPath) {
        return read(new File(vcfPath));
    }

    public static List<jjFileVCF_Entity> sort(List<jjFileVCF_Entity> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getName().compareToIgnoreCase(list.get(i).getName()) < 0) {
                    jjFileVCF_Entity ff = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, ff);
                }
            }
        }
        return list;
    }

    public static List<jjFileVCF_Entity> read(File vcfPath) {
        List<jjFileVCF_Entity> l = new ArrayList<jjFileVCF_Entity>();
        if (vcfPath.exists()) {
            List<String> line = jjFileTxt.readLine(vcfPath);
            jjFileVCF_Entity e = new jjFileVCF_Entity();
            for (int j = 0; j < line.size(); j++) {
                if (line.get(j).startsWith("FN:")) {
                    e.setName(line.get(j).substring(3));
                } else if (line.get(j).startsWith("FN;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:")) {
                    e.setName(doEncodingMobileData(line.get(j).substring(43)));
                } else if (line.get(j).startsWith("TEL;CELL:")) {
                    e.setCellPhone(line.get(j).substring(9));
                } else if (line.get(j).startsWith("TEL;HOME:")) {
                    e.setHome(line.get(j).substring(9));
                } else if (line.get(j).startsWith("TEL;WORK:")) {
                    e.setWork(line.get(j).substring(9));
                } else if (line.get(j).startsWith("TEL;FAX:")) {
                    e.setFax(line.get(j).substring(8));
                } else if (line.get(j).startsWith("TEL:")) {
                    e.setOther(line.get(j).substring(4));
                } else if (line.get(j).startsWith("END:VCARD")) {
                    l.add(e);
                    e = new jjFileVCF_Entity();
                }
            }
        }
        return l;
    }

    public static void write(String vcfPath, List<jjFileVCF_Entity> VCF_EntityList) {
        write(new File(vcfPath), VCF_EntityList);
    }

    public static void write(File vcfPath, List<jjFileVCF_Entity> VCF_EntityList) {
        List<String> l = new ArrayList<String>();
        for (int i = 0; i < VCF_EntityList.size(); i++) {
            l.add("BEGIN:VCARD");
            l.add("VERSION:2.1");
            String name = doCodingMobileData(VCF_EntityList.get(i).getName());
            if (name.equals(VCF_EntityList.get(i).getName())) {
                l.add("N:" + name);//=D9=85
                l.add("FN:" + name);
            } else {
                l.add("N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:" + name);//=D9=85
                l.add("FN;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:" + name);
            }
            l.add("TEL;CELL:" + VCF_EntityList.get(i).getCellPhone());
            l.add("TEL;HOME:" + VCF_EntityList.get(i).getHome());
            l.add("TEL;WORK:" + VCF_EntityList.get(i).getWork());
            l.add("TEL;FAX:" + VCF_EntityList.get(i).getFax());
            l.add("TEL:" + VCF_EntityList.get(i).getOther());
            l.add("END:VCARD");
        }
        jjFileTxt.writeLine(vcfPath, l, false, false);
    }

//    public static List<myphonebook.Entity> read(File vcfPath) {
//        Vector<mobilePhoneContacts> v = new Vector<mobilePhoneContacts>();
//        List<myphonebook.Entity> vv = new ArrayList<myphonebook.Entity>();
//        //BEGIN:VCARD
//        //VERSION:2.1
//        //N:LastName_Mob2;FirstName_Mob1;MidleName;PersonalTitle
//        //NICKNAME:NickName
//        //FN:FirstName_Mob1 LastName_Mob2
//        //TITLE:MyWorkJobTitle
//        //ORG:MyWorkCompany
//        //TEL;CELL:11111
//        //TEL;HOME:22222
//        //TEL;WORK:33333
//        //TEL;FAX:44444
//        //TEL;WORK;PAGER:555555
//        //TEL;HOME;FAX:666666
//        //TEL:77777
//        //EMAIL;INTERNET;PREF:Email1@yahoo.com
//        //EMAIL;INTERNET:Email2@yahoo.com
//        //EMAIL;INTERNET:Email3@yahoo.com
//        //URL:MyHomeWebsite.com
//        //URL;WORK:MyHomeWebsite.com
//        //ADR;HOME:;;MyHomeStreet;MyHomeCity;MyHomeState;MyHomePostalCode;MyHomeCountry
//        //ADR;WORK:;MyWorkOffice;MyWorkStreet;MyWorkCity;MyWorkState;MyWorkPostalCode;MyWorkCountry
//        //NOTE:MyInfo
//        //BDAY:20640708
//        //X-IRMC-LUID:000200000303
//        //END:VCARD
//        try {
//            String firstName = "";
//            String LastName = "";
//            String MidleName = "";
//            String PersonalTitle = "";
//            String NickName = "";
//            String WorkJobTitle = "";
//            String WorkCompany = "";
//            String Department = "";
//            String telPhone = "";
//            String telHOME = "";
//            String telWORK = "";
//            String telFAX = "";
//            String telWorkPAGER = "";
//            String telHomeFAX = "";
//            String telOther = "";
//            String emailInternetPref = "";
//            String emailInternet1 = "";
//            String emailInternet2 = "";
//            String homeWebsiteURL = "";
//            String WorkWebsiteURL = "";
//            String HomeStreet = "";
//            String HomeCity = "";
//            String HomeState = "";
//            String HomePostalCode = "";
//            String HomeCountry = "";
//            String WorkOffice = "";
//            String WorkStreet = "";
//            String WorkCity = "";
//            String WorkState = "";
//            String WorkPostalCode = "";
//            String WorkCountry = "";
//            String NOTE = "";
//            String Birtyear = "";
//            String BirtMonth = "";
//            String BirtDay = "";
//            Scanner input;
//            input = new Scanner(vcfPath);
//            while (input.hasNext()) {
//                String s1 = input.nextLine().split(":")[0] + ":";
//                int email1 = 0;
//                if (s1.contains("BEGIN:")) {
//                    firstName = "";
//                    LastName = "";
//                    MidleName = "";
//                    PersonalTitle = "";
//                    NickName = "";
//                    WorkJobTitle = "";
//                    WorkCompany = "";
//                    Department = "";
//                    telPhone = "";
//                    telHOME = "";
//                    telWORK = "";
//                    telFAX = "";
//                    telWorkPAGER = "";
//                    telHomeFAX = "";
//                    telOther = "";
//                    emailInternetPref = "";
//                    emailInternet1 = "";
//                    emailInternet2 = "";
//                    homeWebsiteURL = "";
//                    WorkWebsiteURL = "";
//                    HomeStreet = "";
//                    HomeCity = "";
//                    HomeState = "";
//                    HomePostalCode = "";
//                    HomeCountry = "";
//                    WorkOffice = "";
//                    WorkStreet = "";
//                    WorkCity = "";
//                    WorkState = "";
//                    WorkPostalCode = "";
//                    WorkCountry = "";
//                    NOTE = "";
//                    Birtyear = "";
//                    BirtMonth = "";
//                    BirtDay = "";
//                    for (int i = 0; i < 27; i++) {
//                        String[] s2 = input.nextLine().split(":");
//                        s2[0] = s2[0] + ":";
//                        if (s2[0].equalsIgnoreCase("N:")) {
//                            String[] s3 = s2[1].split(";");
//                            if (s3.length == 1) {
//                                LastName = s3[0];
//                            } else if (s3.length == 2) {
//                                LastName = s3[0];
//                                firstName = s3[1];
//                            } else if (s3.length == 3) {
//                                LastName = s3[0];
//                                firstName = s3[1];
//                                MidleName = s3[2];
//                            } else {
//                                LastName = s3[0];
//                                firstName = s3[1];
//                                MidleName = s3[2];
//                                PersonalTitle = s3[3];
//                            }
//                        }
//                        if (s2[0].equalsIgnoreCase("N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:")) {
//                            if (s2[1].endsWith("=")) {
//                                String ss = input.nextLine();
//                                s2[1] = s2[1].substring(0, s2[1].length() - 1);
//                                s2[1] = s2[1] + ss;
//                            }
//                            s2[1] = doEncodingMobileData(s2[1]);
//                            String[] s4 = s2[1].split(";");
//                            if (s4.length == 1) {
//                                LastName = (s4[0]);
//                            } else if (s4.length == 2) {
//                                LastName = s4[0];
//                                firstName = s4[1];
//                            } else if (s4.length == 3) {
//                                LastName = s4[0];
//                                firstName = s4[1];
//                                MidleName = s4[2];
//                            } else if (s4.length == 4) {
//                                LastName = s4[0];
//                                firstName = s4[1];
//                                MidleName = s4[2];
//                                PersonalTitle = s4[3];
//                            }
//
//                            s2 = input.nextLine().split(":");
//                            s2[0] = s2[0] + ":";
//                        } else if (s2[0].equalsIgnoreCase("NICKNAME:")) {
//                            NickName = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("TITLE:")) {
//                            WorkJobTitle = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("ORG:")) {
//                            String[] s4 = s2[1].split(";");
//                            if (s4.length == 1) {
//                                WorkCompany = s4[0];
//                            } else {
//                                WorkCompany = s4[0];
//                                Department = s4[1];
//                            }
//                        } else if (s2[0].equalsIgnoreCase("TEL;CELL:")) {
//                            telPhone = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("TEL;HOME:")) {
//                            telHOME = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("TEL;WORK:")) {
//                            telWORK = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("TEL;FAX:")) {
//                            telFAX = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("TEL;WORK;PAGER:")) {
//                            telWorkPAGER = s2[1];
//                        } else if (s2[0].contains("TEL;HOME;FAX:")) {
//                            telHomeFAX = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("TEL:")) {
//                            telOther = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("EMAIL;INTERNET;PREF:")) {
//                            emailInternetPref = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("EMAIL;INTERNET:")) {
//                            if (email1 == 0) {
//                                emailInternet1 = s2[1];
//                                email1 = 1;
//                            } else {
//                                emailInternet2 = s2[1];
//                                email1 = 0;
//                            }
//                        } else if (s2[0].equalsIgnoreCase("URL:")) {
//                            homeWebsiteURL = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("URL;WORK:")) {
//                            WorkWebsiteURL = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("ADR;HOME:")) {
//                            HomeStreet = s2[1].split(";")[2];
//                            HomeCity = s2[1].split(";")[3];
//                            HomeState = s2[1].split(";")[4];
//                            HomePostalCode = s2[1].split(";")[5];
//                            HomeCountry = s2[1].split(";")[6];
//                        } else if (s2[0].equalsIgnoreCase("ADR;WORK:")) {
//                            WorkOffice = s2[1].split(";")[1];
//                            WorkStreet = s2[1].split(";")[2];
//                            WorkCity = s2[1].split(";")[3];
//                            WorkState = s2[1].split(";")[4];
//                            WorkPostalCode = s2[1].split(";")[5];
//                            WorkCountry = s2[1].split(";")[6];
//                        } else if (s2[0].equalsIgnoreCase("NOTE:")) {
//                            NOTE = s2[1];
//                        } else if (s2[0].equalsIgnoreCase("BDAY:")) {
//                            Birtyear = s2[1].substring(0, 4);
//                            BirtMonth = s2[1].substring(4, 6);
//                            BirtDay = s2[1].substring(6, 8);
//                        } else if (s2[0].equalsIgnoreCase("END:")) {
//                            i = 27;
//                            v.add(new mobilePhoneContacts(
//                                    firstName, LastName, MidleName, PersonalTitle, NickName, WorkJobTitle, Department, WorkCompany, telPhone, telHOME, telWORK, telFAX, telWorkPAGER, telHomeFAX, telOther, emailInternetPref, emailInternet1, emailInternet2, homeWebsiteURL, WorkWebsiteURL, HomeStreet, HomeCity, HomeState, HomePostalCode, HomeCountry, WorkOffice, WorkStreet, WorkCity, WorkState, WorkPostalCode, WorkCountry, NOTE, Birtyear, BirtMonth, BirtDay));
//                            myphonebook.Entity e = new myphonebook.Entity();
//                            e.setCellPhone(telPhone);
//                            e.setFax(telFAX);
//                            e.setFname(firstName + " " + LastName);
//                            e.setHome(telHOME);
//                            e.setName(firstName + " " + LastName);
//                            e.setOther(telOther);
//                            e.setWork(telWORK);
//                            vv.add(e);
//                        }
//                    }
//                }
//            }
//        } catch (Exception ex) {
//        }
//        return vv;
//    }
//
    public static String doEncodingMobileData(String code) {
        code = code.replaceAll("=D8=A7", "ا");
        code = code.replaceAll("=D8=A8", "ب");
        code = code.replaceAll("=D9=BE", "پ");
        code = code.replaceAll("=D8=AA", "ت");
        code = code.replaceAll("=D8=AB", "ث");
        code = code.replaceAll("=D8=AC", "ج");
        code = code.replaceAll("=DA=86", "چ");
        code = code.replaceAll("=D8=AD", "ح");
        code = code.replaceAll("=D8=AE", "خ");
        code = code.replaceAll("=D8=AF", "د");
        code = code.replaceAll("=D8=B0", "ذ");
        code = code.replaceAll("=D8=B1", "ر");
        code = code.replaceAll("=D8=B2", "ز");
        code = code.replaceAll("=DA=98", "ژ");
        code = code.replaceAll("=D8=B3", "س");
        code = code.replaceAll("=D8=B4", "ش");
        code = code.replaceAll("=D8=B5", "ص");
        code = code.replaceAll("=D8=B6", "ض");
        code = code.replaceAll("=D8=B7", "ط");
        code = code.replaceAll("=D8=B8", "ظ");
        code = code.replaceAll("=D8=B9", "ع");
        code = code.replaceAll("=D8=BA", "غ");
        code = code.replaceAll("=D9=81", "ف");
        code = code.replaceAll("=D9=82", "ق");
        code = code.replaceAll("=D9=83", "ک");
        code = code.replaceAll("=DA=AF", "گ");
        code = code.replaceAll("=D9=84", "ل");
        code = code.replaceAll("=D9=85", "م");
        code = code.replaceAll("=D9=86", "ن");
        code = code.replaceAll("=D9=87", "ه");
        code = code.replaceAll("=D9=88", "و");
        code = code.replaceAll("=D9=8A", "ی");
        code = code.replaceAll("=DB=8C", "ی");
        code = code.replaceAll("=D8=A1", "ء");
        code = code.replaceAll("=D8=A9", "ة");
        code = code.replaceAll("=D8=A3", "أ");
        code = code.replaceAll("=D8=A5", "إ");
        code = code.replaceAll("=D8=A6", "ي");
        code = code.replaceAll("=D8=A4", "ؤ");
        code = code.replaceAll("=D9=89", "ی");
        code = code.replaceAll("=D8=A2", "آ");
        code = code.replaceAll("=D9=A0", "0");
        code = code.replaceAll("=D9=A1", "1");
        code = code.replaceAll("=D9=A2", "2");
        code = code.replaceAll("=D9=A3", "3");
        code = code.replaceAll("=D9=A4", "4");
        code = code.replaceAll("=D9=A5", "5");
        code = code.replaceAll("=D9=A6", "6");
        code = code.replaceAll("=D9=A7", "7");
        code = code.replaceAll("=D9=A8", "8");
        code = code.replaceAll("=D9=A9", "9");
        return code;
    }

    public static String doCodingMobileData(String code) {
        code = code.replaceAll("ا", "=D8=A7");
        code = code.replaceAll("ب", "=D8=A8");
        code = code.replaceAll("پ", "=D9=BE");
        code = code.replaceAll("ت", "=D8=AA");
        code = code.replaceAll("ث", "=D8=AB");
        code = code.replaceAll("ج", "=D8=AC");
        code = code.replaceAll("چ", "=DA=86");
        code = code.replaceAll("ح", "=D8=AD");
        code = code.replaceAll("خ", "=D8=AE");
        code = code.replaceAll("د", "=D8=AF");
        code = code.replaceAll("ذ", "=D8=B0");
        code = code.replaceAll("ر", "=D8=B1");
        code = code.replaceAll("ز", "=D8=B2");
        code = code.replaceAll("ژ", "=DA=98");
        code = code.replaceAll("س", "=D8=B3");
        code = code.replaceAll("ش", "=D8=B4");
        code = code.replaceAll("ص", "=D8=B5");
        code = code.replaceAll("ض", "=D8=B6");
        code = code.replaceAll("ط", "=D8=B7");
        code = code.replaceAll("ظ", "=D8=B8");
        code = code.replaceAll("ع", "=D8=B9");
        code = code.replaceAll("غ", "=D8=BA");
        code = code.replaceAll("ف", "=D9=81");
        code = code.replaceAll("ق", "=D9=82");
        code = code.replaceAll("ک", "=D9=83");
        code = code.replaceAll("گ", "=DA=AF");
        code = code.replaceAll("ل", "=D9=84");
        code = code.replaceAll("م", "=D9=85");
        code = code.replaceAll("ن", "=D9=86");
        code = code.replaceAll("ه", "=D9=87");
        code = code.replaceAll("و", "=D9=88");
        code = code.replaceAll("ی", "=D9=8A");
        code = code.replaceAll("ی", "=DB=8C");
        code = code.replaceAll("ء", "=D8=A1");
        code = code.replaceAll("ة", "=D8=A9");
        code = code.replaceAll("أ", "=D8=A3");
        code = code.replaceAll("إ", "=D8=A5");
        code = code.replaceAll("ي", "=D8=A6");
        code = code.replaceAll("ؤ", "=D8=A4");
        code = code.replaceAll("ی", "=D9=89");
        code = code.replaceAll("آ", "=D8=A2");
        code = code.replaceAll("0", "=D9=A0");
        code = code.replaceAll("1", "=D9=A1");
        code = code.replaceAll("2", "=D9=A2");
        code = code.replaceAll("3", "=D9=A3");
        code = code.replaceAll("4", "=D9=A4");
        code = code.replaceAll("5", "=D9=A5");
        code = code.replaceAll("6", "=D9=A6");
        code = code.replaceAll("7", "=D9=A7");
        code = code.replaceAll("8", "=D9=A8");
        code = code.replaceAll("9", "=D9=A9");
        return code;
    }

    public static class mobilePhoneContacts {

        String firstName;
        String LastName;
        String MidleName;
        String PersonalTitle;
        String NickName;
        String WorkJobTitle;
        String Department;
        String WorkCompany;
        String telPhone;
        String telHOME;
        String telWORK;
        String telFAX;
        String telWorkPAGER;
        String telHomeFAX;
        String telOther;
        String emailInternetPref;
        String emailInternet1;
        String emailInternet2;
        String homeWebsiteURL;
        String WorkWebsiteURL;
        String HomeStreet;
        String HomeCity;
        String HomeState;
        String HomePostalCode;
        String HomeCountry;
        String WorkOffice;
        String WorkStreet;
        String WorkCity;
        String WorkState;
        String WorkPostalCode;
        String WorkCountry;
        String NOTE;
        String Birtyear;
        String BirtMonth;
        String BirtDay;

        public mobilePhoneContacts(String firstName, String LastName, String MidleName, String PersonalTitle, String NickName, String WorkJobTitle, String Department, String WorkCompany, String telPhone, String telHOME, String telWORK, String telFAX, String telWorkPAGER, String telHomeFAX, String telOther, String emailInternetPref, String emailInternet1, String emailInternet2, String homeWebsiteURL, String WorkWebsiteURL, String HomeStreet, String HomeCity, String HomeState, String HomePostalCode, String HomeCountry, String WorkOffice, String WorkStreet, String WorkCity, String WorkState, String WorkPostalCode, String WorkCountry, String NOTE, String Birtyear, String BirtMonth, String BirtDay) {
            this.firstName = firstName;
            this.LastName = LastName;
            this.MidleName = MidleName;
            this.PersonalTitle = PersonalTitle;
            this.NickName = NickName;
            this.WorkJobTitle = WorkJobTitle;
            this.Department = Department;
            this.WorkCompany = WorkCompany;
            this.telPhone = telPhone;
            this.telHOME = telHOME;
            this.telWORK = telWORK;
            this.telFAX = telFAX;
            this.telWorkPAGER = telWorkPAGER;
            this.telHomeFAX = telHomeFAX;
            this.telOther = telOther;
            this.emailInternetPref = emailInternetPref;
            this.emailInternet1 = emailInternet1;
            this.emailInternet2 = emailInternet2;
            this.homeWebsiteURL = homeWebsiteURL;
            this.WorkWebsiteURL = WorkWebsiteURL;
            this.HomeStreet = HomeStreet;
            this.HomeCity = HomeCity;
            this.HomeState = HomeState;
            this.HomePostalCode = HomePostalCode;
            this.HomeCountry = HomeCountry;
            this.WorkOffice = WorkOffice;
            this.WorkStreet = WorkStreet;
            this.WorkCity = WorkCity;
            this.WorkState = WorkState;
            this.WorkPostalCode = WorkPostalCode;
            this.WorkCountry = WorkCountry;
            this.NOTE = NOTE;
            this.Birtyear = Birtyear;
            this.BirtMonth = BirtMonth;
            this.BirtDay = BirtDay;
        }
    }
}
