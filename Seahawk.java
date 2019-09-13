/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

/**
 *
 * @author kps
 */
public class Seahawk extends JApplet implements KeyListener {

    CloudGenerator cloudGenerator;
    ShipGenerator shipGenerator;
    HelicopterGenerator helicopterGenerator;
    ScreenManager screenManager;
    boolean isTheFirstTimeForLeft = true;
    boolean isTheFirstTimeForRight = true;
    AirPlane airPlane;
    boolean isKeyPressed = false;
    File file;
    String name;
    int counter = 0;
    int frequency = 0;
    int scoreKeeper = 0;
    int HealthKeeper = 0;
    int frequencyKeeper = 0;
    boolean wantToSave = false;

    @Override
    public void init() {
        file = new File("C:\\Users\\kps\\Desktop\\FileTest.txt");

        setSize(800, 600);
        Score score = new Score(70, 0, 100, 40, Color.WHITE);
        Health health = new Health(70, getHeight() - 40, 100, 40, Color.red);
//        

//        
        screenManager = new ScreenManager(this, score, health);
//        
        BackGround backGround = new BackGround(0, 0, 600, 600, Color.BLACK, getImage(getDocumentBase(), "SeaHawk.jpg"));
        screenManager.addScreenObject(backGround);
//        
        screenManager.addScreenObject(health);
        screenManager.addScreenObject(score);
//        
        cloudGenerator = new CloudGenerator(screenManager, getImage(getDocumentBase(), "cloud.gif"));
//        
        airPlane = new AirPlane(10, 80, 40, 20, Color.orange, screenManager, getImage(getDocumentBase(), "AirPlane.gif"), getImage(getDocumentBase(), "chatr.gif"));
        screenManager.addScreenObject(airPlane);
//                     
        shipGenerator = new ShipGenerator(screenManager, airPlane, getImage(getDocumentBase(), "376.png"));
//        
        helicopterGenerator = new HelicopterGenerator(screenManager, airPlane, getImage(getDocumentBase(), "helicopter-icon.png"));
//        
        scoreKeeper = screenManager.score.sc;
        HealthKeeper = screenManager.health.health;
        String again = JOptionPane.showInputDialog(null, "0 --> New Game\n1 --> Load Game From File", "Register OR Load ?", 1);
        if (Integer.parseInt(again) == 0) {
            name = JOptionPane.showInputDialog(null, "Please Inter Your Name", "Register", 1);
            frequencyKeeper = -1;
            wantToSave = true;
            saveAndLoad(1);
            score.sc = 0;
            health.health = 8;
            this.repaint();
        } else if (Integer.parseInt(again) == 1) {
            name = JOptionPane.showInputDialog(null, "Please Inter Your Name", "Search", 1);
            saveAndLoad(0);
        }
//        
        this.addKeyListener(this);
    }

    @Override
    public void start() {
        screenManager.start();
        cloudGenerator.start();
        shipGenerator.start();
        helicopterGenerator.start();
    }

    @Override
    public void stop() {
        saveAndLoad(1);
        screenManager.finish();
        cloudGenerator.finish();
        shipGenerator.finish();
        helicopterGenerator.finish();
    }

    @Override
    public void paint(Graphics g) {
        requestFocus();
        screenManager.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_RIGHT:
                if (airPlane.reverse < 0) {
                    airPlane.reverse();
                }
                airPlane.fastRight();
                airPlane.setBulletsToRight();
                screenGoLeftFast();
                isKeyPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                if (airPlane.reverse > 0) {
                    airPlane.reverse();
                }
                airPlane.fastLeft();
                airPlane.setBulletsToLeft();
                screenGoRightFast();
                isKeyPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                if (!e.isControlDown()) {
                    airPlane.dropBullet();
                } else {
                    airPlane.directBullet();
                }
                break;
            case KeyEvent.VK_DOWN:
                airPlane.fastDown();
                break;
            case KeyEvent.VK_UP:
                airPlane.fastUp();
                break;
            case KeyEvent.VK_F5:
                wantToSave = true;
                scoreKeeper = screenManager.score.sc;
                HealthKeeper = screenManager.health.health;
                break;
            case KeyEvent.VK_F6:
                Loader();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_RIGHT:
                screenGoLeft();
                screenManager.setAutoRight(false);
                airPlane.setBulletsToRight();
                isKeyPressed = false;
                airPlane.right();
                break;
            case KeyEvent.VK_LEFT:

                screenGoRight();
                screenManager.setAutoRight(true);
                airPlane.setBulletsToLeft();
                isKeyPressed = false;
                airPlane.left();
                break;
            case KeyEvent.VK_DOWN:
                airPlane.down();
                break;
            case KeyEvent.VK_UP:
                airPlane.up();
                break;
        }

    }

    public void screenGoLeft() {

        cloudGenerator.setSleep(10);
        shipGenerator.setSleep(15);
        screenManager.goLeft();
        cloudGenerator.setX(this.getWidth());
        cloudGenerator.setdX(-3);
        shipGenerator.setX(this.getWidth());
        shipGenerator.setdX(-3);
        helicopterGenerator.setX(this.getWidth());
        helicopterGenerator.setdX(-3);

    }

    public void screenGoRight() {

        cloudGenerator.setSleep(10);
        shipGenerator.setSleep(15);
        screenManager.goRight();
        cloudGenerator.setX(0);
        cloudGenerator.setdX(+3);
        shipGenerator.setX(0);
        shipGenerator.setdX(+3);
        helicopterGenerator.setX(0);
        helicopterGenerator.setdX(+3);
    }

    public void screenGoLeftFast() {

        cloudGenerator.setSleep(5);
        shipGenerator.setSleep(10);
        screenManager.goLeftFast();
        cloudGenerator.setX(this.getWidth());
        cloudGenerator.setdX(-3);
        shipGenerator.setX(this.getWidth());
        shipGenerator.setdX(-3);
        helicopterGenerator.setX(this.getWidth());
        helicopterGenerator.setdX(-3);

    }

    public void screenGoRightFast() {

        cloudGenerator.setSleep(5);
        shipGenerator.setSleep(10);
        screenManager.goRightFast();
        cloudGenerator.setX(0);
        cloudGenerator.setdX(+3);
        shipGenerator.setX(0);
        shipGenerator.setdX(+3);
        helicopterGenerator.setX(0);
        helicopterGenerator.setdX(+3);
    }

    public void saveAndLoad(int save) {
        if (save == 1) { //Save
            if (wantToSave) {
                RandomAccessFile writerToFile = null;
                try {
                    String finalString = name + "$$" + scoreKeeper + "&&" + HealthKeeper + "**" + ++frequencyKeeper + "!!";
                    writerToFile = new RandomAccessFile(file, "rw");
                    writerToFile.seek(writerToFile.length());
                    writerToFile.writeBytes(finalString + "\r\n");
                    writerToFile.close();
                } catch (Exception ex) {
                }
            }
        } else if (save == 0) { //Load
            try {
                counter = 0;
                String stringFromFile = "";
                String stringFromFileName = "";
                String stringFromFileScore = "";
                String stringFromFileHealth = "";
                String stringFromFileLastTime = "";
                BufferedReader readerFromFile = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                BufferedReader readerFromFileForLastTime = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                while (stringFromFile != null) {
                    stringFromFile = readerFromFileForLastTime.readLine();
                    if (stringFromFile != null) {
                        stringFromFileName = stringFromFile.substring(0, stringFromFile.indexOf("$$"));
                        stringFromFileLastTime = stringFromFile.substring(stringFromFile.indexOf("**") + 2, stringFromFile.indexOf("!!"));
                        if (stringFromFileName.equals(name)) {
                            frequencyKeeper = Integer.parseInt(stringFromFileLastTime);
                        }
                    }
                }
                readerFromFileForLastTime.close();
                stringFromFile = "";
                while (stringFromFile != null) {
                    stringFromFile = readerFromFile.readLine();
                    if (stringFromFile != null) {
                        stringFromFileName = stringFromFile.substring(0, stringFromFile.indexOf("$$"));
                        stringFromFileScore = stringFromFile.substring(stringFromFile.indexOf("$$") + 2, stringFromFile.indexOf("&&"));
                        stringFromFileHealth = stringFromFile.substring(stringFromFile.indexOf("&&") + 2, stringFromFile.indexOf("**"));

                        if (stringFromFileName.equals(name)) {
                            if (counter == frequencyKeeper) {
                                screenManager.score.sc = Integer.parseInt(stringFromFileScore);
                                screenManager.health.health = Integer.parseInt(stringFromFileHealth);
                                this.repaint();
                                readerFromFile.close();
                            }
                            counter++;
                        }

                    }
                }
                readerFromFile.close();
            } catch (Exception ex) {
            }
        }
    }

    public void Loader() {
        name = JOptionPane.showInputDialog(null, "Please Inter Your Name", "Search", 1);
        saveAndLoad(0);
    }
}
