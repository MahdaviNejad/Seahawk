/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author ui
 */
public class Health extends ScreenObject {

    int health = 8;

    public Health(int x, int y, int w, int h, Color cl) {
        super(x, y, w, h, cl);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        g.setFont(new Font("Arial", Font.BOLD, h / 2));

        g.drawString(health + "", x, y + h / 2);
    }

    public void stop() {
    }

//    public void addScore(int diff) {
//        sc += diff;
//    }
    public void reduseHealth(int diff) {
        health -= diff;
    }

    public int getHealth() {
        return health;
    }
}
