/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import audio.AudioPlayer;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NathanielWard
 */
class Arena extends Environment {

    private Grid grid;
    private Bike bike1;
    private Bike bike2;
    private GameState state = GameState.STOPPED;
    int counter;

    public Arena() {
        this.setBackground(ResourceTools.loadImageFromResource("tron/SZdhPUH.jpg").getScaledInstance(1000, 750, Image.SCALE_SMOOTH));

        grid = new Grid(145, 145, 5, 5, new Point(30, 30), new Color(0, 0, 250));

//        bike1 = new Bike(Direction.RIGHT, new Point(22, 22), Color.GREEN, grid);
//        bike2 = new Bike(Direction.DOWN, new Point(44, 44), Color.RED, grid);
        resetGame();
    }

    private void resetGame() {
        // remove tail on the bikes
        // put the bikes in a new start position
        
        
        bike1 = new Bike(Direction.LEFT, new Point(128, 50), Color.GREEN, grid);
        bike2 = new Bike(Direction.RIGHT, new Point(18, 50), Color.RED, grid);
         
        // set the gamestate to STOPPED
        state = GameState.STOPPED;
         
// play a cool
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        if (state == GameState.RUNNING) {
            if (bike1 != null) {
                bike1.move();
            }
            if (bike2 != null) {
                bike2.move();
            }
            checkCollisions();
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

        //System.out.println("key press" + e.getKeyChar());
        // System.out.println("key press" + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            bike1.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bike1.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bike1.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            bike1.setDirection(Direction.DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            bike2.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            bike2.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            bike2.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            bike2.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (state == GameState.RUNNING) {
                state = GameState.STOPPED;
            } else {
                state = GameState.RUNNING;
            }
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);

        }
        if (bike1 != null) {
            bike1.draw(graphics);
        }

        if (bike2 != null) {
            bike2.draw(graphics);
        }
    }

    public void checkCollisions() {
        if ((bike1 != null) && (bike2 != null)) {
            if (bike1.selfHit()) {
                state = GameState.STOPPED;
                JOptionPane.showMessageDialog(null, "bike 1 crashed into itself. RIP");
                // System.out.println("bike 1 self hit....");
                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }
            if (bike2.selfHit()) {
                JOptionPane.showMessageDialog(null, "bike 2 crashed into itself. RIP");
                //System.out.println("bike 2 self hit....");
                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }

            if (bike2.hasBeenHit(bike1.getHead())) {
                JOptionPane.showMessageDialog(null, "bike 1 crashed into bike 2. RIP");
                //System.out.println("bike 2 has been hit...");
                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }

            if (bike1.hasBeenHit(bike2.getHead())) {
                JOptionPane.showMessageDialog(null, "bike 2 crashed into bike 1. RIP");
                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }

        }
   
    
    }
    

}
