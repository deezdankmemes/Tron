/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author NathanielWard
 */
public class Bike {
//no grid bounderies
    //moves to fast
    //no colissions

    public Bike(Direction direction, Point startLocation, Color color, Grid grid) {
        this.direction = direction;
        this.grid = grid;
        this.bodyColor = color;

        body = new ArrayList<>();
        body.add(startLocation);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(bodyColor);
        for (int i = 0; i < body.size(); i++) {
            graphics.fillRect(grid.getCellSystemCoordinate(body.get(i)).x,
                    grid.getCellSystemCoordinate(body.get(i)).y,
                    grid.getCellWidth(),
                    grid.getCellHeight());

        }
    }

    public void move() {
        Point newHead = new Point(getHead());

        if (getDirection() == Direction.LEFT) {
            newHead.x--;
        } else if (getDirection() == Direction.RIGHT) {
            newHead.x++;
        } else if (getDirection() == Direction.DOWN) {
            newHead.y++;
        } else if (getDirection() == Direction.UP) {
            newHead.y--;
        }

        body.add(HEAD_POSITION, newHead);
    }
    private static int HEAD_POSITION = 0;

    public Point getHead() {
        return body.get(HEAD_POSITION);

    }
   //<editor-fold defaultstate="collapsed" desc="properties">

    private Direction direction = Direction.LEFT;
    private ArrayList<Point> body;
    private Grid grid;
    private Color bodyColor = Color.red;
//    private Color bodyColor1 = Color.GREEN;

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @param bodyColor the bodyColor to set
     */
    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }//</editor-fold>

    /**
     * @return the whole structure of the bike this will be useful when we want
     * to see if one bike hit another bike and/or tail...
     */
    public ArrayList<Point> getBikeAndTrail() {
        return body;

    }

    /**
     * @return the trail only, but not the bike this will be useful when we want
     * to see if the bike hit its own tail
     */
    public ArrayList<Point> getTrail() {
        ArrayList<Point> trail = new ArrayList<>();
        for (int i = 1; i < body.size(); i++) {
            trail.add(body.get(i));

        }
        return trail;
    }

}
