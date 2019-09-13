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
public class ShipBullet extends ScreenObject implements Runnable {

    double xBullet;
    double yBullet;
    double dxBullet;
    double dyBullet;
    int sleepTime;
    ScreenManager screenManager;

    public ShipBullet(int xBullet, int YBullet, int wBullet, int hBullet, Color clBullet, int sleepTime, double dxBullet, double dyBullet, ScreenManager screenManager) {
        super(xBullet, YBullet, wBullet, hBullet, clBullet);
        this.xBullet = xBullet;
        this.yBullet = YBullet;
        this.sleepTime = sleepTime;
        this.dxBullet = dxBullet;
        this.dyBullet = dyBullet;
        this.screenManager = screenManager;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        g.fillRect((int) xBullet, (int) yBullet, w, h);
    }

    @Override
    public void stop() {
        try {
            screenManager.removeScreenObject(this);
//            Thread.currentThread().interrupt();
        } catch (Exception ex) {
        }
    }

    @Override
    public void run() {
        while (xBullet + dxBullet <= screenManager.SeahawkApplet.getWidth() && xBullet + dxBullet >= 0 && yBullet > screenManager.SeahawkApplet.getHeight() - 550) {
            xBullet = xBullet + dxBullet;
            yBullet = yBullet + dyBullet;
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
