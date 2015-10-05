/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import java.util.TimerTask;
import cms.tools.ServerLog;
/**
 *
 * @author Milad
 */
public class jjWeather {

    public jjWeather(String cityName, String cityDegree, String cityStatus) {
        this.cityName = cityName;
        this.cityDegree = cityDegree;
        this.cityStatus = cityStatus;
    }
    String cityName;
    String cityDegree;
    String cityStatus;

    public static enum citys {

        tehran, esfahan, mashhad;
    }
    private static javax.swing.Timer WheatherTimer = null;

    public static void setWheatherTimer(long delayForUpdate) {
        java.util.Timer tt = new java.util.Timer();
        tt.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    ServerLog.Print("Start: " + "");
                    jjTime.MyDate date = jjTime.getDateNowPersian();
                    jjWeather teh = getWeather(citys.tehran);
                    jjWeather esf = getWeather(citys.esfahan);
                    jjWeather mash = getWeather(citys.mashhad);
                    String teh2 = " " + teh.cityName + ":" + teh.cityDegree + (teh.cityStatus.equals("") ? "" : "(") + teh.cityStatus + (teh.cityStatus.equals("") ? "" : ")");
                    String esf2 = " " + esf.cityName + ":" + esf.cityDegree + (esf.cityStatus.equals("") ? "" : "(") + esf.cityStatus + (esf.cityStatus.equals("") ? "" : ")");
                    String mash2 = " " + mash.cityName + ":" + mash.cityDegree + (mash.cityStatus.equals("") ? "" : "(") + mash.cityStatus + (mash.cityStatus.equals("") ? "" : ")");
                    String mess = "امروز " + date.Day_name + " " + date.Day + "/" + date.Month + "/" + date.Year + " ";
                    if (!teh2.trim().equals(":")) {
                        mess += "درجه حرارت " + "   " + teh2;
                        mess += "      " + esf2;
                        mess += "      " + mash2;
                    }
                    jjFileTxt.write(jjOsInfo.getProgramAddress() + "/Weather.txt", mess, false, false);
                    ServerLog.Print("End");
                } catch (Exception ex) {
                    ServerLog.Print("Error");
                    jjFileTxt.write(jjOsInfo.getProgramAddress() + "/Weather.txt", " ", false, false);
                    return;
                }
            }
        }, 1, delayForUpdate);

    }

    public static jjWeather getWeather(citys City) {
        try {
            String name = "";
            String degree = "";
            String status = "";
            String mainCity = "";
            if (City == citys.tehran) {
                name = "تهران";
                mainCity = jjWebURL.getWebSiteSource("http://weather.yahooapis.com/forecastrss?p=IRXX0018&u=f");
            } else if (City == citys.esfahan) {
                name = "اصفهان";
                mainCity = jjWebURL.getWebSiteSource("http://weather.yahooapis.com/forecastrss?p=IRXX0003&u=f");
            } else if (City == citys.mashhad) {
                name = "مشهد";
                mainCity = jjWebURL.getWebSiteSource("http://weather.yahooapis.com/forecastrss?p=IRXX0008&u=f");
            }
//          String s = Help.getWebSite("http://www.aftabir.com/portal/weather/view.php?code=40800");
//          String s = Help.getWebSite("http://rss.weather.com/weather/rss/local/IRXX0003?cm_ven=LWO&cm_cat=rss&par=LWO_rss");
//          String s = Help.getWebSite("http://www.irinn.ir/Default.aspx?TabID=25");
            mainCity = mainCity.replace("\r\n", "");
            if (mainCity.contains("Current Conditions:</b><br />")) {
                mainCity = mainCity.split("Current Conditions:</b><br />")[1];
            }
            if (mainCity.contains("<BR /><BR /><b>Forecast:")) {
                mainCity = mainCity.split("<BR /><BR /><b>Forecast:")[0];
            }
            String degre = "0";
            if (mainCity.contains(",")) {
                degree = mainCity.split(",")[1].replace("F", "").trim();
                status = convertWhetherStatement(mainCity.split(",")[0].trim());
            }
            if (jjNumber.isDigit(degree)) {
                degre = convertForenhightToCuntigrad(Integer.parseInt(degree));
            }
            return new jjWeather(name, degre + "", status);

        } catch (Exception ex) {
//            Help.dialog_ERROR_MESSAGE(ex, null, "getWeather", ex.getMessage());
            return new jjWeather("", "", "");
        }
    }

    /**
     *
     * get resolution from screen
     */
    public static String convertForenhightToCuntigrad(int faren) {

        return ((faren - 32) * 5 / 9) + "°C";
    }

    private static String convertWhetherStatement(String Statement) {
        String s = "";
        if (Statement.toLowerCase().contains("Haze".toLowerCase())) {
            s = "مه خفیف";
        } else if (Statement.toLowerCase().equals("Cloudy".toLowerCase())) {
            s = "ابری";
        } else if (Statement.toLowerCase().equals("tornado".toLowerCase())) {
            s = "طوفان";
        } else if (Statement.toLowerCase().contains("tropical storm".toLowerCase())) {
            s = "طوفان منطقه حاره";
        } else if (Statement.toLowerCase().contains("hurricane".toLowerCase())) {
            s = "تند باد";
        } else if (Statement.toLowerCase().contains("severe thunderstorms".toLowerCase())) {
            s = "طوفان تندری شدید";
        } else if (Statement.toLowerCase().contains("thunderstorms".toLowerCase())) {
            s = "طوفان تندری";
        } else if (Statement.toLowerCase().contains("mixed rain and snow".toLowerCase())) {
            s = "باران و برف";
        } else if (Statement.toLowerCase().contains("drizzle".toLowerCase())) {
            s = "باران ریز";
        } else if (Statement.toLowerCase().equals("freezing rain".toLowerCase())) {
            s = "تگرگ";
        } else if (Statement.toLowerCase().contains("showers".toLowerCase())) {
            s = "درشت باران";
        } else if (Statement.toLowerCase().contains("blowing snow".toLowerCase())) {
            s = "بوران";
        } else if (Statement.toLowerCase().equals("snow".toLowerCase())) {
            s = "برف";
        } else if (Statement.toLowerCase().contains("hail".toLowerCase())) {
            s = "تگرگ";
        } else if (Statement.toLowerCase().contains("sleet".toLowerCase())) {
            s = "برف و باران";
        } else if (Statement.toLowerCase().contains("dust".toLowerCase())) {
            s = "گردو قبار";
        } else if (Statement.toLowerCase().contains("foggy".toLowerCase())) {
            s = "مه";
        } else if (Statement.toLowerCase().contains("smoky".toLowerCase())) {
            s = "دود گرفته";
        } else if (Statement.toLowerCase().contains("blustery".toLowerCase())) {
            s = "پر باد";
        } else if (Statement.toLowerCase().equals("windy".toLowerCase())) {
            s = "پرباد";// بدخیز
        } else if (Statement.toLowerCase().equals("cold".toLowerCase())) {
            s = "سرد";
        } else if (Statement.toLowerCase().contains("mostly cloudy".toLowerCase())) {
            s = "تغریبا تمام ابری";
        } else if (Statement.toLowerCase().contains("partly cloudy".toLowerCase())) {
            s = "تا قسمتی ابری";
        } else if (Statement.toLowerCase().contains("sunny".toLowerCase())) {
            s = "آفتابی";
        } else if (Statement.toLowerCase().contains("fair".toLowerCase())) {
            s = "نسبتا بدون ابر";
        } else if (Statement.toLowerCase().contains("mixed rain and hail".toLowerCase())) {
            s = "باران و تگرگ";
        } else if (Statement.toLowerCase().contains("hot".toLowerCase())) {
            s = "گرم و سوزان";
        } else if (Statement.toLowerCase().contains("scattered thunderstorms".toLowerCase())) {
            s = "رعد و برق پراکنده";
        } else if (Statement.toLowerCase().contains("scattered showers".toLowerCase())) {
            s = "برف پراکنده";
        } else if (Statement.toLowerCase().contains("heavy snow".toLowerCase())) {
            s = "برف شدید";
        } else if (Statement.toLowerCase().contains("scattered snow showers".toLowerCase())) {
            s = "ریز برف پراکنده";
        } else if (Statement.toLowerCase().contains("heavy snow".toLowerCase())) {
            s = "برف سنگین";
        } else if (Statement.toLowerCase().contains("partly cloudy".toLowerCase())) {
            s = "تا قسمتی ابری";
        } else if (Statement.toLowerCase().contains("Light Rain Shower".toLowerCase())) {
            s = "رگبار و باران نسبتا سبک";
        } else {
            s = Statement;
        }
        return s;
    }
}
