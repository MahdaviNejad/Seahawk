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
public class CloudGenerator extends Thread {

    boolean finished = false;
    ScreenManager screenManager;
    int dxCloud = -3;
    int xCloud;
    int sleep = 10;
    Image cloudImage;

    public CloudGenerator(ScreenManager screenManager, Image cloudImage) {
        this.screenManager = screenManager;
        xCloud = screenManager.SeahawkApplet.getWidth();
        this.cloudImage = cloudImage;
    }

    @Override
    public void run() {
        while (!finished) {
            //\\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\SM\\\\\\\\\

            Cloud cloud = randomCloud();
            screenManager.addScreenObject(cloud);
            Thread t = new Thread(cloud);
            t.start();
            try {
                Thread.currentThread().sleep(100 + random(600 + random(1000)));
            } catch (InterruptedException ex) {
            }
        }

    }

    public int random(int m) {
        return (int) (m * Math.random());
    }

    public void setX(int xCloud) {
        this.xCloud = xCloud;
    }

    public void setdX(int dxCloud) {
        this.dxCloud = dxCloud;
    }

    private Cloud randomCloud() {
        int w = 40;
        int h = 20;
        int y = 20 + random(20);
//        Color cl = new Color(random(256), random(256), random(256));
        return new Cloud(xCloud, y, w, h, Color.WHITE, dxCloud, sleep, screenManager, cloudImage);
    }

    protected void finish() {
        finished = true;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
