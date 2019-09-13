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
public class ShipGenerator extends Thread {

    boolean finished = false;
    int xShip = 0;
    int dxShip = +3;
    ScreenManager screenManager;
    AirPlane airPlane;
    int sleep = 10;
    Image shipImage;

    public ShipGenerator(ScreenManager screenManager, AirPlane airPlane, Image shipImage) {
        this.screenManager = screenManager;
        this.airPlane = airPlane;
        this.shipImage = shipImage;
    }

    @Override
    public void run() {
        while (!finished) {
            //\\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\SM\\\\\\\\\

            Ship ship = randomShip();
            screenManager.addScreenObject(ship);
            Thread t = new Thread(ship);
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

    public void setX(int xShip) {
        this.xShip = xShip;
    }

    public void setdX(int dxShip) {
        this.dxShip = dxShip;
    }

    private Ship randomShip() {
        int w = 50;
        int h = 10;
        //\\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\X\\\\\\\\\
        int y = 520;

//        Color cl = new Color(random(256), random(256), random(256));
        return new Ship(xShip, y, w, h, Color.BLACK, dxShip, sleep, screenManager, airPlane, shipImage);
    }

    protected void finish() {
        finished = true;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
