/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author kps
 */
public class BackGround extends ScreenObject {

    Image backGrroundImage;

    public BackGround(int x, int y, int w, int h, Color cl, Image backGrroundImage) {
        super(x, y, w, h, cl);
        this.backGrroundImage = backGrroundImage;

    }
    Seahawk seahawk = new Seahawk();

    public void stop() {
    }

    public void draw(Graphics g) {
        g.drawImage(backGrroundImage, 0, 0, 800, 600, cl, seahawk);
    }
}
