/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author kps
 */
public class AirPlane extends ScreenObject {

    Thread t = new Thread();
    ScreenManager screenManager;
    int dxOfBullet = +8;
    Image airPlaneImage;
    Image pilotImage;
    int reverse;

    public AirPlane(int xAirPlane, int yAirPlane, int wAirPlane, int hAirPlane, Color clAirPlane, ScreenManager screenManager, Image airPlaneImage, Image pilotImage) {
        super(xAirPlane, yAirPlane, wAirPlane, hAirPlane, clAirPlane);
        this.screenManager = screenManager;
        this.airPlaneImage = airPlaneImage;
        this.pilotImage = pilotImage;
        this.reverse = wAirPlane;
    }

    public void up() {
        if (y >= screenManager.SeahawkApplet.getHeight() - 520) {
            y = y - 8;
        }
    }

    public void down() {
        if (y + h < screenManager.SeahawkApplet.getHeight() - 95) {
            y = y + 8;
        }
    }

    public void right() {
        if (x + w < screenManager.SeahawkApplet.getWidth()) {
            x = x + 8;
        }
    }

    public void left() {
        if (x > 0) {
            x = x - 8;
        }
    }
//    .........................................................................

    public void fastUp() {
        if (y >= screenManager.SeahawkApplet.getHeight() - 520) {
            y = y - 15;
        }
    }

    public void fastDown() {
        if (y + h < screenManager.SeahawkApplet.getHeight() - 95) {
            y = y + 15;
        }
    }

    public void fastRight() {
        if (x + w < screenManager.SeahawkApplet.getWidth()) {
            x = x + 15;
        }
    }

    public void fastLeft() {
        if (x > 0) {
            x = x - 15;
        }
    }

    public void directBullet() {
        AirPlaneBullet bullet = new AirPlaneBullet(x + reverse, y + 7, 18, 2, Color.BLACK, 20, screenManager, dxOfBullet);
        bullet.directBullet(true);
        screenManager.addScreenObject(bullet);
        Thread bulletTread = new Thread(bullet);
        bulletTread.start();
    }

    public void dropBullet() {
        AirPlaneBullet bullet = new AirPlaneBullet(x + (reverse / 2), (y + h / 2), 18, 2, Color.BLACK, 20, screenManager, dxOfBullet);
        bullet.directBullet(false);
        screenManager.addScreenObject(bullet);
        Thread bulletTread = new Thread(bullet);
        bulletTread.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        if (!t.isAlive()) {
            g.drawImage(airPlaneImage, x, y, reverse, h, screenManager.SeahawkApplet);
        } else {
            x = 1300;
            y = 1000;
        }
    }

    @Override
    public void stop() {
    }

    public Point pointReporter() {
        Point point = new Point(x + (reverse / 2), y + (h / 2));
        return point;
    }

    public void setBulletsToLeft() {
        this.dxOfBullet = -8;
    }

    public void setBulletsToRight() {
        this.dxOfBullet = +8;
    }

    public void dropPilot() {
//        int realW;
//        if (reverse < 0) {
//            realW = x + reverse;
//        } else {
//            realW = x;
//        }
        Pilot pilot = new Pilot(x, y, reverse, h, cl, 40, screenManager, this, pilotImage);
        screenManager.addScreenObject(pilot);
        t = new Thread(pilot);
        t.start();
    }

    public void reverse() {
        this.reverse = -reverse;
    }
}
