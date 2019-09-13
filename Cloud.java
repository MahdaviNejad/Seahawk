/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kps
 */
public class Cloud extends ScreenObject implements Runnable {

    int dxCloud;
    int sleepTime;
    ScreenManager screenManager;
    Image cloudImage;

    public Cloud(int xCloud, int yCloud, int wCloud, int hCloud, Color clCloud, int dxCloud, int sleepTime, ScreenManager screenManager, Image cloudImage) {
        super(xCloud, yCloud, wCloud, hCloud, clCloud);
        this.dxCloud = dxCloud;
        this.sleepTime = sleepTime;
        this.screenManager = screenManager;
        this.cloudImage = cloudImage;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
//        g.fillRect(x, y, w, h);
        g.drawImage(cloudImage, x, y, w + 20, h + 20, screenManager.SeahawkApplet);
    }

    @Override
    public void run() {
//       \\\\\\\\\\\\\\\\\\\\NOT FULL\\\\\\\\\\\\\\\\\\\\\\\\\\
        while (x + dxCloud < screenManager.SeahawkApplet.getWidth() && x + dxCloud >= 0) {
            x = x + dxCloud;
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

    @Override
    public void stop() {
        try {
            screenManager.removeScreenObject(this);
//            Thread.currentThread().interrupt();
        } catch (Exception ex) {
        }
    }

    public void setX(int xCloud) {
        this.x = xCloud;
    }

    public void setdX(int dxCloud) {
        this.dxCloud = dxCloud;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
