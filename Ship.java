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
public class Ship extends ScreenObject implements Runnable {

    int dxShip;
    int sleepTime;
    ScreenManager screenManager;
    AirPlane airPlane;
    Point point;
    boolean isDead = false;
    Image shipImage;

    public Ship(int xShip, int YShip, int wShip, int hShip, Color clShip, int dxShip, int sleepTime, ScreenManager screenManager, AirPlane airPlane, Image shipImage) {
        super(xShip, YShip, wShip, hShip, clShip);
        this.sleepTime = sleepTime;
        this.dxShip = dxShip;
        this.screenManager = screenManager;
        this.airPlane = airPlane;
        this.point = airPlane.pointReporter();
        this.shipImage = shipImage;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        g.drawImage(shipImage, x, y - 25, w + h, h + w, screenManager.SeahawkApplet);
    }

    @Override
    public void stop() {
        try {
            screenManager.removeScreenObject(this);
//            Thread.currentThread().interrupt();
        } catch (Exception ex) {
        }
    }

    public void shipFire() {
        point = airPlane.pointReporter();
//        System.out.println(point.getX());
        ShipBullet bullet = new ShipBullet(x + (w / 2), y - (h / 2), 18, 2, Color.red, 70, xCalculator(point) / 70, yCalculator(point) / 70, screenManager);
        screenManager.addScreenObject(bullet);
        Thread bulletTread = new Thread(bullet);
        bulletTread.start();

    }

    public double xCalculator(Point point) {
        return -(x + (w / 2) - point.getX());
    }

    public double yCalculator(Point point) {
        return -(y + (h / 2) - point.getY());
    }

    public void randomFire() {
//        if (Math.round(Math.random() * 100) % 77 == 7) {
        if (Math.round(Math.random() * 100) % 137 == 0) {
            shipFire();
        }
//        }
    }

    @Override
    public void run() {
        //       \\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\\\\\\\\\\\\\\\\\\\
        while (!isDead && ((x + dxShip) < (screenManager.SeahawkApplet.getWidth()) * (3 / 2) + 200) && (x + dxShip) > 0 - (screenManager.SeahawkApplet.getWidth()) * (1 / 2) - 200) {

            x = x + dxShip;
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

    public void setX(int xShip) {
        this.x = x;
    }

    public void setdX(int dxShip) {
        this.dxShip = dxShip;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
