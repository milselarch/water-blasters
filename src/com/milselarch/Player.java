package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {
    private Board board;

    private final int START_Y = 580;
    private final int START_X = 470;

    private final String playerImg = "src/images/player.png";
    private int width;
    private int height;

    public Player(Board board) {
        initPlayer(board);
    }

    private void initPlayer(Board board) {
        this.board = board;

        try {
            BufferedImage image = ImageIO.read(new File(playerImg));
            this.setImage(image);
        } catch (Exception e) {
            System.out.println("IMAGE READ ERROR");
        }

        width = this.getWidth();
        height = this.getHeight();

        setX(START_X);
        setY(START_Y);
    }

    public void act() {
        /*
        x,y coordinates are actually the top left corner of the sprite
        */
        //System.out.println(Integer.toString(x) + ", " +Integer.toString(y) + ", " +Integer.toString(height + y));
        int displacex = this.board.getWidth()/2 - this.getWidth()/2;
        int displacey = this.board.getHeight()/2 - this.getHeight()/2;


        //System.out.println("DISPLACE X = " + displacex);

        this.board.worldx += dx;
        this.board.worldy += dy;

        if (this.board.worldx <= -displacex) {
            this.board.worldx = -displacex;
        } else if (this.board.worldx + this.getWidth() >= BOARD_WIDTH - displacex) {
            this.board.worldx = BOARD_WIDTH - this.getWidth() - displacex;
        }

        if (this.board.worldy <= -displacey) {
            this.board.worldy = -displacey;
        } else if (this.board.worldy + this.getHeight() >= BOARD_HEIGHT - displacey) {
            this.board.worldy = BOARD_HEIGHT - this.getHeight() - displacey;
        }

        /*
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
        */
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