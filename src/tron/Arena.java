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
import java.awt.Font;
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
class Arena extends Environment implements MoveValidatorIntf {

    private Grid grid;
    private Bike bike1;
    private Bike bike2;
    private GameState state = GameState.STOPPED;
    private MySoundManager soundManager;

//    private Barriers barriers;
    int counter;

    public Arena() {
        this.state = GameState.CRASHED;
        this.setBackground(ResourceTools.loadImageFromResource("tron/SZdhPUH.jpg").getScaledInstance(1000, 750, Image.SCALE_SMOOTH));

        grid = new Grid(145, 140, 5, 5, new Point(30, 30), new Color(0, 0, 250));

        soundManager = MySoundManager.getSoundManager();

        resetGame();
    }

    private void resetGame() {
        // remove tail on the bikes
        // put the bikes in a new start position

        bike1 = new Bike(Direction.LEFT, new Point(128, 70), Color.GREEN, grid, this);
        bike2 = new Bike(Direction.RIGHT, new Point(18, 70), Color.RED, grid, this);

        // set the gamestate to STOPPED
        setState(GameState.STOPPED);

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
                setState(GameState.STOPPED);
//               state = GameState.PAUSED;

            } else if (state == GameState.STOPPED) {
                setState(GameState.RUNNING);
                System.out.println("running");

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
        if ((state == GameState.RUNNING) || ((state == GameState.STOPPED))) {
            if (grid != null) {
                grid.paintComponent(graphics);
            }
        }
        
        if (bike1 != null) {
            bike1.draw(graphics);
        }

        if (bike2 != null) {
            bike2.draw(graphics);
        }
        graphics.setColor(Color.green);
        graphics.drawString("PLAYER 1", 650, 20);
        graphics.setColor(Color.red);
        graphics.drawString("PLAYER 2", 70, 20);

        if (state == GameState.STOPPED) {
            graphics.setColor(new Color(0, 0, 0, 200));
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

            graphics.setFont(new Font("Arial", Font.BOLD, 60));
            graphics.setColor(Color.red);
            graphics.drawString("PAUSED", 288, 350);

        }

    }

    public void checkCollisions() {
        if ((bike1 != null) && (bike2 != null)) {
            if (bike1.selfHit()) {
                setState(GameState.STOPPED);

                //JOptionPane.showMessageDialog(null, "bike 1 crashed into itself. RIP");
                // System.out.println("bike 1 self hit....");
//                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }
            if (bike2.selfHit()) {
                JOptionPane.showMessageDialog(null, "bike 2 crashed into itself. RIP");
                //System.out.println("bike 2 self hit....");
//                AudioPlayer.play("/tron/.mp3");
                resetGame();
            }

            if (bike2.hasBeenHit(bike1.getHead())) {
                JOptionPane.showMessageDialog(null, "bike 1 crashed into bike 2. RIP");

//                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }

            if (bike1.hasBeenHit(bike2.getHead())) {
                JOptionPane.showMessageDialog(null, "bike 2 crashed into bike 1. RIP");
//                AudioPlayer.play("/tron/crashsound.mp3");
                resetGame();
            }

        }

    }

//    private static class Barriers {
//
//        public Barriers() {
//           if (barriers != null) {
//           for (Barrier barrier : barriers.getBarriers()) {
//               if (barrier.getLocation().equals(bike1.getHead())) {
//                   JB.addHealth(-1000);
//                   System.out.println("Game Over");
//               }
//
//               if (barrier.getLocation().equals(JB2.getHead())) {
//                   JB2.addHealth(-1000);
//                   System.out.println("Game Over");
//               } 
//        }
//    }
//    
//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf">
    @Override
    public Point validate(Point proposedLocation) {
        //assess and adjust the proposedLocation

        if (proposedLocation.x < 0) {
            setState(GameState.STOPPED);
        } else if (proposedLocation.x >= grid.getColumns()) {
            resetGame();
        }
        if (proposedLocation.y < 0) {
            resetGame();
        } else if (proposedLocation.y >= grid.getRows()) {
            resetGame();
        }
        return proposedLocation;
    }
//</editor-fold>

    /**
     * @param state the state to set
     */
    public void setState(GameState state) {
        this.state = state;
        
        //if ((state == GameState.RUNNING) || ((state == GameState.STOPPED))) {
        if (state == GameState.RUNNING) {
            soundManager.play(MySoundManager.DANKKAZOO);
            System.out.println("playing");

        }
        if (state == GameState.STOPPED) {
            soundManager.stop(MySoundManager.DANKKAZOO);
            System.out.println("not playing");
        }
    }
}
