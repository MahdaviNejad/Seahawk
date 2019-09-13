package Seahawk;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author ui
 */
public class Pilot extends ScreenObject implements Runnable {

    int dy = 2;
    int sleep;
    Image image;
    ScreenManager sm;
    AirPlane airPlane;
    Image pilotImage;

    public Pilot(int x, int y, int w, int h, Color cl, int sleep, ScreenManager sm, AirPlane airPlane, Image pilotImage) {
        super(x, y, w, h, cl);
        this.sleep = sleep;
        this.sm = sm;
        this.airPlane = airPlane;
        this.pilotImage = pilotImage;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        g.drawImage(pilotImage, x, y - 25, w, h + w, sm.SeahawkApplet);
    }

    public void stop() {
        sm.removeScreenObject(this);
    }

    @Override
    public void run() {
        while (y + h <= sm.SeahawkApplet.getHeight()) {

            y += dy;
            try {
                Thread.currentThread().sleep(sleep);
            } catch (InterruptedException ex) {
            }
        }
        airPlane.x = 10;
        airPlane.y = 80;
        stop();
    }
}
