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
public class HelicopterBullet extends ScreenObject implements Runnable {

    int dxBullet;
//    double dyBullet;
    int sleepTime;
    ScreenManager screenManager;

    public HelicopterBullet(int xBullet, int YBullet, int wBullet, int hBullet, Color clBullet, int sleepTime, int dxBullet, ScreenManager screenManager) {
        super(xBullet, YBullet, wBullet, hBullet, clBullet);
        this.sleepTime = sleepTime;
        this.dxBullet = dxBullet;
//        this.dyBullet = dyBullet;
        this.screenManager = screenManager;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
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

    @Override
    public void run() {
        //       \\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\\\\\\\\\\With Reporter\\\\\\\\\
        while (x + dxBullet < screenManager.SeahawkApplet.getWidth() && x + dxBullet > 0) {
//            if (xBullet =0 ) {
//                dxBullet = -3;
//            }
            x = x + dxBullet;
//            yBullet = yBullet + dyBullet;
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
}
