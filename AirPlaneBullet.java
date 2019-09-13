/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kps
 */
public class AirPlaneBullet extends ScreenObject implements Runnable {

    int dxBullet;
    int dyBullet = +8;
    int sleepTime;
    boolean isDirect;
    ScreenManager screenManager;

    public AirPlaneBullet(int xBullet, int YBullet, int wBullet, int hBullet, Color clBullet, int sleepTime, ScreenManager screenManager, int dxBullet) {
        super(xBullet, YBullet, wBullet, hBullet, clBullet);
        this.sleepTime = sleepTime;
        this.screenManager = screenManager;
        this.dxBullet = dxBullet;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
//        g.fillOval(x, y, w, h);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void stop() {
        try {
            screenManager.removeScreenObject(this);
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
        }
    }

    public void directBullet(boolean isDirect) {
        if (isDirect) {
            x = x + dxBullet;
            this.isDirect = true;
        } else {
            x = x + dxBullet;
            y = y + dyBullet;
            this.isDirect = false;
        }
    }

    @Override
    public void run() {
        //       \\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\\\\\\\\\\With Reporter\\\\\\\\\
        while (x + dxBullet < screenManager.SeahawkApplet.getWidth()
                && x + dxBullet > 0
                && y < 560) {
            directBullet(isDirect);
            try {
                Thread.currentThread().sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cloud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            stop();
        } catch (Exception ex) {
        }
    }
//    public void setBulletToleftByDx(boolean setToLeft) {
//        if (setToLeft) {
//            this.dxBullet = -8;
//        }
//    }
}
