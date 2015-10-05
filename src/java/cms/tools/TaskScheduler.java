/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author MOHAMMAD
 */
public class TaskScheduler implements ServletContextListener {

    //For Prevent More than one Call
    static Boolean IsSmsRunning = false;
    static ScheduledExecutorService SmsScheduler;
    static ScheduledExecutorService GCScheduler;
    ServletContext context;
    //Run in Start Up

    public void contextInitialized(ServletContextEvent contextEvent) {
        if (!Server.userNameSMS.equals("0") && !Server.passwordSMS.equals("0")) {
            Start();
        }
        GC();
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
        if (IsSmsRunning) {
            SmsScheduler.shutdownNow();
            GCScheduler.shutdownNow();
        }
    }

    static void Sms() {

        //Set For 10Am
        long Hour_OF_Send_Sms = 10;
        Calendar now = Calendar.getInstance();
        int CurrentMin = now.get(Calendar.MINUTE);
        int CurrentHour = now.get(Calendar.HOUR_OF_DAY);
        long MinDiffre = 0;
        //Find Difference Between Server Start And 10Am And Wait Correspond
        long HourDiff = 0;
        if ((Hour_OF_Send_Sms - CurrentHour) >= 0) {
            HourDiff = (Hour_OF_Send_Sms - CurrentHour) * 60 * 60 * 1000;
        } else {
            HourDiff = (24 - (Hour_OF_Send_Sms - CurrentHour)) * 60 * 60 * 1000;
        }


        if (HourDiff != 0) {
            MinDiffre = CurrentMin * 60 * 1000;
        }

        long Wait = HourDiff - MinDiffre;

        //long Period = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
        long Period = 86400000;//5*60 * 1000;//Hour_OF_Send_Sms*60*60*1000

//        Timer timer = new Timer();
        //timer.schedule(new SmsSchedule(), Diff * 1000, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
        //timer.schedule(new SmsTask(), Wait, Period); // 60*60*24*100 = 8640000ms
        SmsScheduler = Executors.newSingleThreadScheduledExecutor();
//        SmsScheduler.scheduleAtFixedRate(new BirthdaySmsTask(), 0, Period, TimeUnit.MILLISECONDS);

    }

    static void GC() {
        long Period = 180000;//3 min
        GCScheduler = Executors.newSingleThreadScheduledExecutor();
        GCScheduler.scheduleAtFixedRate(new ExplicitGC(), Period, Period, TimeUnit.MILLISECONDS);

    }

    public static void Start() {
        if (!IsSmsRunning) {
            IsSmsRunning = true;
            Sms();
        }
    }
}
