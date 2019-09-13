/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ui
 */
public abstract class ScreenObject {

    int x;
    int y;
    int w;
    int h;
    Color cl;

    public ScreenObject(int x, int y, int w, int h, Color cl) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cl = cl;
    }

    public abstract void draw(Graphics g);

    public abstract void stop();
}
