/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Seahawk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kps
 */
public class ScreenManager extends Thread {

    ArrayList<ScreenObject> objects;
    Seahawk SeahawkApplet;
    Graphics offGraphics;
    Image offImage;
    boolean finished = false;
    Score score;
    Health health;
    AirPlane airPlane = null;
    boolean airPlaneAutoRight = false;

    public ScreenManager(Seahawk SeahawkApplet, Score score, Health health) {
        this.SeahawkApplet = SeahawkApplet;
        objects = new ArrayList<ScreenObject>(20);
        offImage = SeahawkApplet.createImage(SeahawkApplet.getWidth(), SeahawkApplet.getHeight());
        offGraphics = offImage.getGraphics();
        this.score = score;
        this.health = health;
    }

    public void addScreenObject(ScreenObject object) {
        objects.add(object);
    }

    public void removeScreenObject(ScreenObject object) {
        try {
            objects.remove(object);
//            object.stop();
        } catch (Exception ex) {
        }
    }

    public void draw(Graphics g) {
        try {
            checkConflict();
            offGraphics.setColor(Color.BLUE);
            offGraphics.clearRect(0, 0, SeahawkApplet.getWidth(), SeahawkApplet.getHeight());

            for (ScreenObject screenObjects : objects) {
                screenObjects.draw(offGraphics);
            }

            g.drawImage(offImage, 0, 0, SeahawkApplet);
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        //            Find AirPlane
        for (ScreenObject screenObjects : objects) {
            if (screenObjects instanceof AirPlane) {
                airPlane = (AirPlane) screenObjects;
            }
        }
        int realW = airPlane.x;

        while (!finished) {
            if (airPlane.reverse < 0) {
                realW = airPlane.x;
            } else {
                realW = airPlane.x + airPlane.w;
            }
            if (airPlaneAutoRight && realW < SeahawkApplet.getWidth()) {
                airPlane.x++;
            } else if (!airPlaneAutoRight && airPlane.x > 0) {
                airPlane.x--;
            }
//            if (airPlane.dropedPilot) {
//            }
            SeahawkApplet.repaint();
            try {
                Thread.currentThread().sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void finish() {
        finished = true;
    }

    public void goLeft() {
        for (ScreenObject screenObjects : objects) {
            if (screenObjects instanceof Cloud) {
                Cloud cloud = (Cloud) screenObjects;
                cloud.setdX(-3);
//                System.out.println("*");
            } else if (screenObjects instanceof Ship) {
                Ship ship = (Ship) screenObjects;
                ship.setdX(-3);
            } else if (screenObjects instanceof Helicopter) {
                Helicopter helicopter = (Helicopter) screenObjects;
                helicopter.setdX(-3);
            }
        }
    }

    public void goRight() {
        for (ScreenObject screenObjects : objects) {
            if (screenObjects instanceof Cloud) {
                Cloud cloud = (Cloud) screenObjects;
                cloud.setdX(+3);
            } else if (screenObjects instanceof Ship) {
                Ship ship = (Ship) screenObjects;
                ship.setdX(+3);
            } else if (screenObjects instanceof Helicopter) {
                Helicopter helicopter = (Helicopter) screenObjects;
                helicopter.setdX(+3);
            }
        }
    }

    public void goLeftFast() {
        for (ScreenObject screenObjects : objects) {
            if (screenObjects instanceof Cloud) {
                Cloud cloud = (Cloud) screenObjects;
                cloud.setdX(-3);
                cloud.setSleepTime(5);
//                System.out.println("*");
            } else if (screenObjects instanceof Ship) {
                Ship ship = (Ship) screenObjects;
                ship.setdX(-3);
                ship.setSleepTime(10);
            } else if (screenObjects instanceof Helicopter) {
                Helicopter helicopter = (Helicopter) screenObjects;
                helicopter.setdX(-3);
            }
        }
    }

    public void goRightFast() {
        for (ScreenObject screenObjects : objects) {
            if (screenObjects instanceof Cloud) {
                Cloud cloud = (Cloud) screenObjects;
                cloud.setdX(+3);
                cloud.setSleepTime(5);
            } else if (screenObjects instanceof Ship) {
                Ship ship = (Ship) screenObjects;
                ship.setdX(+3);
                ship.setSleepTime(10);
            } else if (screenObjects instanceof Helicopter) {
                Helicopter helicopter = (Helicopter) screenObjects;
                helicopter.setdX(+3);
            }
        }
    }

    private void checkConflict() {


        ArrayList<Helicopter> helicopters = new ArrayList<Helicopter>(10);
        ArrayList<HelicopterBullet> helicopterBullets = new ArrayList<HelicopterBullet>(10);
//        ArrayList<AirPlane> airPlanes = new ArrayList<AirPlane>(1);
        ArrayList<AirPlaneBullet> airPlaneBullets = new ArrayList<AirPlaneBullet>(10);
        ArrayList<Ship> ships = new ArrayList<Ship>(10);
        ArrayList<ShipBullet> shipBullets = new ArrayList<ShipBullet>(10);
        for (ScreenObject screenObjects : objects) {
            if (screenObjects instanceof AirPlane) {
                airPlane = (AirPlane) screenObjects;
//                airPlanes.add(airPlane);
            } else if (screenObjects instanceof AirPlaneBullet) {
                AirPlaneBullet airPlaneBullet = (AirPlaneBullet) screenObjects;
                airPlaneBullets.add(airPlaneBullet);
            } else if (screenObjects instanceof Helicopter) {
                Helicopter helicopter = (Helicopter) screenObjects;
                helicopters.add(helicopter);
            } else if (screenObjects instanceof HelicopterBullet) {
                HelicopterBullet helicopterBullet = (HelicopterBullet) screenObjects;
                helicopterBullets.add(helicopterBullet);
            } else if (screenObjects instanceof Ship) {
                Ship ship = (Ship) screenObjects;
                ships.add(ship);
            } else if (screenObjects instanceof ShipBullet) {
                ShipBullet shipBullet = (ShipBullet) screenObjects;
                shipBullets.add(shipBullet);
            }
        }
//        AirPlane-Helicopter
        for (Helicopter helly : helicopters) {
            int realX = airPlane.x;
            if (airPlane.reverse < 0) {
                realX = airPlane.x + airPlane.reverse;
            } else {
                realX = airPlane.x;
            }
            int realXH = airPlane.x;
            if (helly.reverse < 0) {
                realXH = helly.x + helly.reverse;
            } else {
                realXH = helly.x;
            }
            double xhelly = realXH + helly.w / 2;
            double yhelly = helly.y + helly.h / 2;
            if (xhelly <= realX + airPlane.w && xhelly >= realX
                    && yhelly <= airPlane.y + airPlane.h && yhelly >= airPlane.y) {
                removeScreenObject(helly);
                restart();
            }
        }
//        Ship To AirPlane
        for (ShipBullet bullet : shipBullets) {
            int realX = airPlane.x;
            if (airPlane.reverse < 0) {
                realX = airPlane.x + airPlane.reverse;
            } else {
                realX = airPlane.x;
            }
            double xbullet = bullet.xBullet + bullet.w / 2;
            double ybullet = bullet.yBullet + bullet.h / 2;
            if (xbullet < realX + airPlane.w && xbullet > realX
                    && ybullet < airPlane.y + airPlane.h && ybullet > airPlane.y) {
                removeScreenObject(bullet);
//                removeScreenObject(airPlane);
//                score.reduseScore(500);
//                airPlane.stop();
                restart();
            }
        }
//        AirPlane to Ship        
        for (AirPlaneBullet bullet : airPlaneBullets) {
            for (Ship ship : ships) {
                double xbullet = bullet.x + bullet.w / 2;
                double ybullet = bullet.y + bullet.h / 2;
                if (xbullet < ship.x + ship.w && xbullet > ship.x
                        && ybullet < ship.y + ship.h && ybullet > ship.y) {
                    removeScreenObject(bullet);
                    removeScreenObject(ship);
                    ship.setIsDead(true);
                    score.addScore(500);
//                    ship.stop();
                }
            }
        }
//        AirPlane to Helicopter
        for (AirPlaneBullet bullet : airPlaneBullets) {
            for (Helicopter helicopter : helicopters) {
                double xbullet = bullet.x + bullet.w / 2;
                double ybullet = bullet.y + bullet.h / 2;
                if (xbullet < helicopter.x + helicopter.reverse && xbullet > helicopter.x
                        && ybullet < helicopter.y + helicopter.h && ybullet > helicopter.y) {
                    removeScreenObject(bullet);
                    removeScreenObject(helicopter);
                    helicopter.setIsDead(true);
                    score.addScore(100);
//                    helicopter.stop();
                } else if (xbullet > helicopter.x + helicopter.reverse && xbullet < helicopter.x
                        && ybullet < helicopter.y + helicopter.h && ybullet > helicopter.y) {
                    removeScreenObject(bullet);
                    removeScreenObject(helicopter);
                    helicopter.setIsDead(true);
                    score.addScore(100);
//                    helicopter.stop();
                }
            }
        }
//        Helicopter To AirPlane
        for (HelicopterBullet bullet : helicopterBullets) {
            double xbullet = bullet.x + bullet.w / 2;
            double ybullet = bullet.y + bullet.h / 2;
            if (xbullet < airPlane.x + airPlane.w && xbullet > airPlane.x
                    && ybullet < airPlane.y + airPlane.h && ybullet > airPlane.y) {
                removeScreenObject(bullet);
//                removeScreenObject(airPlane);
//                score.reduseScore(100);
                restart();
            }
        }
    }

    private void restart() {
//        SeahawkApplet.cloudGenerator.setSleep(1000000000);
//        SeahawkApplet.shipGenerator.setSleep(1000000000);
//        SeahawkApplet.helicopterGenerator.setSleep(1000000000);
        if (airPlane.reverse < 0) {
            airPlane.reverse();
        }
        airPlane.dropPilot();
        airPlane.setBulletsToRight();
        airPlaneAutoRight = false;
        SeahawkApplet.screenGoLeft();
        airPlane.x = 10;
        airPlane.y = 80;
        if (health.getHealth() > 1) {
            health.reduseHealth(1);
        } else {
            String again = JOptionPane.showInputDialog(null, "1 --> Yes\n0 --> No", "Again ?", 1);
            if (Integer.parseInt(again) == 0) {
                System.exit(0);
            } else if (Integer.parseInt(again) == 1) {
                score.sc = 0;
                health.health = 8;
                SeahawkApplet.repaint();
            }


        }

    }

    public void setAutoRight(boolean airPlaneAutoRight) {
        this.airPlaneAutoRight = airPlaneAutoRight;
    }
}
