package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;

public class Monster extends Sprite implements Commons {
    private Board board;

    private Bomb bomb;
    private final String alienImg = "src/images/alien.png";

    public Monster(Board board, int x, int y) {
        initAlien(board, x, y);
    }

    private void initAlien(Board board, int x, int y) {
        RandomRange random = new RandomRange();

        this.board = board;
        this.dx = random.rand(-5, 5);
        this.dy = random.rand(-5, 5);
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        try {
            BufferedImage image = ImageIO.read(new File(alienImg));
            this.setImage(image);
        } catch (Exception e) {
            System.out.println("IMAGE READ ERROR");
        }
    }

    public int getEndX() {
        return this.x + this.getWidth();
    }

    public int getEndY() {
        return this.y + this.getHeight();
    }

    public void act() {
        this.x += this.dx;
        this.y += this.dy;

        if (this.x <= 0) {
            this.x = 0;
            this.dx *= -1;
        } else if (this.getEndX() >= BOARD_WIDTH) {
            this.x = BOARD_WIDTH - this.getWidth();
            this.dx *= -1;
        }

        if (this.y <= 0) {
            this.y = 0;
            this.dy *= -1;
        } else if (this.getEndY() >= BOARD_HEIGHT) {
            this.y = BOARD_HEIGHT - this.getHeight();
            this.dy *= -1;
        }
    }

    public Bomb getBomb() {
        return bomb;
    }

    public class Bomb extends Sprite {
        private final String bombImg = "src/images/bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {
            initBomb(x, y);
        }

        private void initBomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;

            try {
                BufferedImage image = ImageIO.read(new File(bombImg));
                this.setImage(image);
            } catch (Exception e) {
                System.out.println("IMAGE READ ERROR");
            }
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}