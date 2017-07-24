package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {

    private final int START_Y = 580;
    private final int START_X = 470;

    private final String playerImg = "src/images/player.png";
    private int width;
    private int height;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {

        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        height = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void act() {
        /*
        x,y coordinates are actually the top left corner of the sprite
        */
        //System.out.println(Integer.toString(x) + ", " +Integer.toString(y) + ", " +Integer.toString(height + y));
        x += dx;
        y += dy;

        if (x <= BORDER) {
            x = BORDER;
        } else if (x + width >= BOARD_WIDTH - 4 * BORDER) {
            x = BOARD_WIDTH - 4 * BORDER - width;
        }

        if (y <= BORDER) {
            y = BORDER;
        } else if (y >= BOARD_HEIGHT - 6 * BORDER - 2 * height) {
            y = BOARD_HEIGHT - 6 * BORDER - 2 * height;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
}