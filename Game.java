package com.arinerron.games.pingpong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {
    
    private static int x = 0;
    private static int y = 0;
    private int xa = 1;
    private static int ya = 1;
    private static int bx = 100;
    private static int ax = 0;
    private int by = getHeight() - 350;
    private static int mouseX;
    private int choice1;
    private static int dead = 0;
    private static int choice2;
    private static int speed;
    private static int choice3;
    
    private void moveBall() {
        if (x + xa < 0)
        xa = 1;
        if (x + xa > getWidth() - 30)
        xa = -1;
        if (y + ya < 0)
        ya = 1;
        if (y + ya > getHeight() - 30) {
            ya = -1;
            gameOver();
        }
        
        x = x + xa;
        y = y + ya;
    }
    
    private void gameOver() {
        dead = 1;
        Object[] options = {
            "Re-play!",
            "Close game!"
        };
        choice1 = JOptionPane.showOptionDialog(null, "You died! :( Re-play?", "Goodbye... :(",
        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        null, options, options[0]);
        
        if (choice1 == 0) {
            dead = 0;
            Object[] options2 = {
                "Easy",
                "Hard"
            };
            choice2 = JOptionPane.showOptionDialog(null, "Please select the difficulty! (Again...) :)", "Waiting for answer...",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options2, options2[0]);
            
            if (choice2 == 0) {
                speed = 5;
            }
            if (choice2 == 1) {
                speed = 1;
            }
        }
        if (choice1 == 1) {
            setVisible(false);
            System.exit(0);
        }
        x = 0;
        y = 0;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval(x, y, 30, 30);
        g.fillRect(mouseX - 100, 350, 80, 10);
        
    }
    
    public static void SPACE() {
        Object[] options5 = {
            "Easy",
            "Hard",
            "Exit Program",
            "Restart",
            "Continue"
        };
        choice3 = JOptionPane.showOptionDialog(null, "Please select the difficulty! :)", "Waiting for answer...",
        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        null, options5, options5[0]);
        
        if (choice3 == 0) {
            speed = 5;
        }
        if (choice3 == 1) {
            speed = 1;
        }
        if (choice3 == 2) {
            System.exit(0);
        }
        if (choice3 == 3) {
            x = 0;
            y = 0;
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        bx = -3;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        bx = 3;
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            SPACE();
        }
    }
    public void paint(Graphics2D g) {
        g.fillRect(0, 330, 600, 600);
    }
    
    public static void main(String[] args) throws InterruptedException {
        Object[] options = {
            "Easy",
            "Hard"
        };
        choice2 = JOptionPane.showOptionDialog(null, "Please select the difficulty! :)", "Waiting for answer...",
        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        null, options, options[0]);
        
        if (choice2 == 0) {
            speed = 5;
        }
        if (choice2 == 1) {
            speed = 1;
        }
        
        JFrame frame = new JFrame("Arinerron's Ping-Pong!");
        Game game = new Game();
        frame.add(game);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (true) {
            if (dead == 0) {
                game.moveBall();
                mouseX = MouseInfo.getPointerInfo().getLocation().x;
                ax = ax + bx;
                game.repaint();
                Thread.sleep(speed);
                if (y > 325) {
                    if (mouseX - 100 - 40 < x) {
                        if (mouseX - 100 + 40 > x) {
                            if (y < 350) {
                                ya = -1;
                            }
                        }
                    }
                }
            }
        }
    }
}
