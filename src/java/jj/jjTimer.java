package jj;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author Milad Jamalzadeh 09132686053
 */
public class jjTimer {

    javax.swing.Timer tmr = null;
    static JLabel timerLable;

    /**
     *
     *  setText(time) to timerLable
     */
    public void setTimer(JLabel timerLable, int delayForUpdate) {
        jjTimer.timerLable = timerLable;
        jjTime.MyDate date = jjTime.getDateNowGregorian();
        jjTimer.timerLable.setText(date.Hour + ":"
                + jjNumber.getFormaterIntegerLength(date.Min, 2) + ":"
                + jjNumber.getFormaterIntegerLength(date.Second, 2));
        ActionListener al = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jjTime.MyDate date = jjTime.getDateNowGregorian();
                jjTimer.timerLable.setText(date.Hour + ":"
                        + jjNumber.getFormaterIntegerLength(date.Min, 2) + ":"
                        + jjNumber.getFormaterIntegerLength(date.Second, 2));
            }
        };
        tmr = new javax.swing.Timer(delayForUpdate, al);
        tmr.start();
    }
    /**
     *
     *  setText(time) to animation timerLable
     */
    private javax.swing.Timer tmr2 = null;
    private JLabel timerLable2;
    private int count = 0;
    private int frameSize = 0;
    private Point first;

    public void setAnimationTimer(JLabel timerLable, int delayForUpdate, int frameSize2) {
        timerLable2 = timerLable;

        frameSize = frameSize2;
        first = timerLable.getLocation();
        count = timerLable.getX();

        if (tmr2 == null) {
            tmr2 = new javax.swing.Timer(delayForUpdate, al);
        } else {
            tmr2.setDelay(delayForUpdate);
        }
        tmr2.start();
    }
    private ActionListener al = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            count++;
            if (count < frameSize) {
                timerLable2.setLocation(count, first.y);
            } else {
                int with = jjFont.getStringPixelSize(timerLable2.getText(), timerLable2.getFont()).width + 20;
                timerLable2.setSize(new Dimension(with, timerLable2.getHeight()));
                timerLable2.setPreferredSize(new Dimension(with, timerLable2.getHeight()));
                count = 0 - with;
            }
        }
    };
}
