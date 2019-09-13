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
public class Score extends ScreenObject {

    int sc = 0;

    public Score(int x, int y, int w, int h, Color cl) {
        super(x, y, w, h, cl);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cl);
        g.setFont(new Font("Arial", Font.BOLD, h / 2));

        g.drawString(sc + "", x, y + h / 2);
    }

    public void stop() {
    }

    public void addScore(int diff) {
        sc += diff;
    }
     public void reduseScore(int diff) {
        sc -= diff;
    }
}
