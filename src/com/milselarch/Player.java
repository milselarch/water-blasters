package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {
    private Board board;

    private final int SPEEDUP = 3;
    private final int START_Y = 580;
    private final int START_X = 470;
    int health = PLAYER_HEALTH;

    private Cooldown shotCooldown;
    public boolean isShooting = false;
    private final String playerImg = "src/images/player.png";
    private int width;
    private int height;

    public Player(Board board) {
        initPlayer(board);
    }

    private void initPlayer(Board board) {
        this.board = board;
        this.shotCooldown = new Cooldown(PLAYER_SHOT_COOLDOWN, true);

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

    public void resetHealth() {
        this.health = PLAYER_HEALTH;
    }

    public void loseHealth() {
        if (this.health > 0) {
            this.health--;
        }
    }

    public void draw(Graphics2D g2d) {
        float opacity = (health + 1) / ((float) PLAYER_HEALTH + 1);

        g2d.setComposite(
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)
        );

        g2d.drawImage(
            this.getImage(),
            this.getX() - this.board.worldx,
            this.getY() - this.board.worldy,
            this.board
        );

        g2d.setComposite(
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)
        );
    }

    @Override
    public int getX() {
        return this.board.worldx + this.getDisplacex();
    }

    @Override
    public int getY() {
        return this.board.worldy + this.getDisplacey();
    }

    @Override
    public int getEndX() {
        return this.board.worldx + this.getWidth() + this.getDisplacex();
    }

    @Override
    public int getEndY() {
        return this.board.worldy + this.getHeight() + this.getDisplacey();
    }

    public int getDisplacex() {
        return this.board.getWidth()/2 - this.getWidth()/2;
    }

    public int getDisplacey() {
        return this.board.getHeight()/2 - this.getHeight()/2;
    }

    public void act() {
        /*
        x,y coordinates are actually the top left corner of the sprite
        */
        int displacex = this.getDisplacex();
        int displacey = this.getDisplacey();
        //System.out.println("DISPLACE X = " + displacex);

        this.board.worldx += dx * this.SPEEDUP;
        this.board.worldy += dy * this.SPEEDUP;

        if (this.board.worldx <= -displacex) {
            this.board.worldx = -displacex;
        } else if (this.board.worldx + this.getWidth() >= WORLD_WIDTH - displacex) {
            this.board.worldx = WORLD_WIDTH - this.getWidth() - displacex;
        }

        if (this.board.worldy <= -displacey) {
            this.board.worldy = -displacey;
        } else if (this.board.worldy + this.getHeight() >= WORLD_HEIGHT - displacey) {
            this.board.worldy = WORLD_HEIGHT - this.getHeight() - displacey;
        }

        if (isShooting && shotCooldown.startIfCooledDown()) {
            Vector vec = new Vector(
                this.board.mousex - BOARD_WIDTH/2,
                this.board.mousey - BOARD_HEIGHT/2
            ).getNormalised();

            vec.scale(20.0);

            PShot pshot = new PShot(
                board,
                this.getX(),
                this.getY(),
                vec.x.intValue() + this.dx,
                vec.y.intValue() + this.dy
            );

            this.board.pshots.add(pshot);
            //System.out.println("XY " + shotx + ", " + shoty);
        }
    }

    @Override
    public Dimension getCenter() {
        return new Dimension(
            this.board.worldx - this.getWidth()/2,
            this.board.worldy - this.getHeight()/2
        );
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