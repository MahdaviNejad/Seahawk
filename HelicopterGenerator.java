/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author kps
 */
public class HelicopterGenerator extends Thread {

    boolean finished = false;
    int dxHelicopter = -3;
    ScreenManager screenManager;
    AirPlane airPlane;
    int xHelicopter;
    int sleep;
    Image helicopterImage;

    public HelicopterGenerator(ScreenManager screenManager, AirPlane airPlane, Image helicopterImage) {
        this.screenManager = screenManager;
        this.airPlane = airPlane;
        xHelicopter = screenManager.SeahawkApplet.getWidth();
        this.helicopterImage = helicopterImage;
    }

    @Override
    public void run() {
        while (!finished) {
            //\\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\SM\\\\\\\\\

            Helicopter Helicopter = randomHelicopter();
            screenManager.addScreenObject(Helicopter);
            Thread t = new Thread(Helicopter);
            t.start();
            try {
                Thread.currentThread().sleep(8000 + random(6000 + random(1000)));
            } catch (InterruptedException ex) {
            }
        }
    }

    public int random(int m) {
        return (int) (m * Math.random());
    }

    public void setX(int xHelicopter) {
        this.xHelicopter = xHelicopter;
    }

    public void setdX(int dxHelicopter) {
        this.dxHelicopter = dxHelicopter;
    }

    private Helicopter randomHelicopter() {
        int w = 50;
        int h =50;
        //\\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\Y///X\\\\\\\\\
        int y = airPlane.y;
        sleep = 50;
//        Color cl = new Color(random(256), random(256), random(256));
        return new Helicopter(xHelicopter, y, w, h, Color.PINK, dxHelicopter, sleep, screenManager, airPlane, helicopterImage);
    }

    protected void finish() {
        finished = true;
    }

//    public void setHelicopterToLeft() {
//        this.dxHelicopter = -3;
//    }
//
//    public void setHelicopterToRight() {
//        this.dxHelicopter = +3;
//    }
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
