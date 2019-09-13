/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kps
 */
public class Helicopter extends ScreenObject implements Runnable {

    int dxHelicopter;
    int dyHelicopter = 3;
    int sleepTime;
    ScreenManager screenManager;
    AirPlane airPlane;
    Point point;
    boolean isDead = false;
    boolean isReversed = true;
    Image helicopterImage;
    int reverse;

    public Helicopter(int xHelicopter, int YHelicopter, int wHelicopter, int hHelicopter, Color clHelicopter, int dxHelicopter, int sleepTime, ScreenManager screenManager, AirPlane airPlane, Image helicopterImage) {
        super(xHelicopter, YHelicopter, wHelicopter, hHelicopter, clHelicopter);
        this.sleepTime = sleepTime;
        this.dxHelicopter = dxHelicopter;
        this.screenManager = screenManager;
        this.airPlane = airPlane;
        this.point = airPlane.pointReporter();
        this.helicopterImage = helicopterImage;
        this.reverse = wHelicopter;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        g.drawImage(helicopterImage, x, y, reverse, h, screenManager.SeahawkApplet);

    }

    @Override
    public void stop() {
        try {
            screenManager.removeScreenObject(this);
//            Thread.currentThread().interrupt();
        } catch (Exception ex) {
        }
    }

    public void HelicopterFire() {
        point = airPlane.pointReporter();
//        System.out.println(point.getX());
        if (Math.round(Math.random() * 100) % 4 == 0) {
            dyHelicopter = -dyHelicopter;
        }

        HelicopterBullet bullet = new HelicopterBullet(x,
                y + 25, 18, 2, Color.red, 7, xCalculator(point), screenManager);
        screenManager.addScreenObject(bullet);
        Thread bulletTread = new Thread(bullet);
        bulletTread.start();

    }

    public int xCalculator(Point point) {
//        return -(x - point.getX());
        if (-(x - point.getX()) < 0) {
            return -3;
        } else {
            return +3;
        }
    }
//
//    public double yCalculator(Point point) {
//        return -(y - point.getY());
//    }

    public void randomFire() {
//        if (Math.round(Math.random() * 100) % 77 == 7) {
        if (Math.round(Math.random() * 100) % 15 == 0) {
            HelicopterFire();
        }
//        }
    }

    @Override
    public void run() {
        //       \\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\\\\\\\\\\\\\\\\\\\
        while (!isDead && ((x + dxHelicopter) < (screenManager.SeahawkApplet.getWidth()) * (3 / 2) + 200)
                && (x + dxHelicopter) > 0 - (screenManager.SeahawkApplet.getWidth()) * (1 / 2) - 200) {
            if (y + dyHelicopter < 80 || y + dyHelicopter > 500) {
                dyHelicopter = -dyHelicopter;
            }
            if ((isReversed && x <= airPlane.x)) {
                reverse();
                this.isReversed = !isReversed;
            }
            if ((!isReversed && x >= airPlane.x + airPlane.w)) {
                reverse();
                this.isReversed = !isReversed;
            }
            y = y + dyHelicopter;
            x = x + dxHelicopter;
            try {
                Thread.currentThread().sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cloud.class.getName()).log(Level.SEVERE, null, ex);
            }
            randomFire();
        }
        try {
            stop();
        } catch (Exception ex) {
        }
    }

    public void setX(int xHelicopter) {
        this.x = xHelicopter;
    }

    public void setdX(int dxHelicopter) {
        this.dxHelicopter = dxHelicopter;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void reverse() {
        this.reverse = -reverse;
    }
}
