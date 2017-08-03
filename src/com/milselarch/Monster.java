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
    private Double speedup = 1.0;
    private final String alienImg = "src/images/alien.png";
    private boolean isChasing = false;
    private double lastStun;

    public Monster(Board board, int x, int y) {
        initMonster(board, x, y);
    }

    private void initMonster(Board board, int x, int y) {
        this.board = board;
        this.lastStun = this.board.getCurrentTime() - STUN_DURATION;;

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        this.setRandomMotion();

        try {
            BufferedImage image = ImageIO.read(new File(alienImg));
            this.setImage(image);
        } catch (Exception e) {
            System.out.println("IMAGE READ ERROR");
        }
    }

    public void setRandomMotion() {
        RandomRange random = new RandomRange();

        this.dx = 0;
        this.dy = 0;

        while (this.dx == 0) {
            this.dx = random.rand(-4, 4);
        }
        while (this.dy == 0) {
            this.dy = random.rand(-4, 4);
        }
    }

    public void charge(Vector displacement) {
        Double distance = displacement.getMagnitude() + 20;

        if (distance < CHASE_RADIUS) {
            isChasing = true;
            Double scale = 1.0 + Math.sqrt(CHASE_RADIUS / distance);

            Vector normalised = displacement.getNormalised();
            //System.out.println(displacement.x + ", " + displacement.y);
            this.dx = ((Double) (normalised.x * scale)).intValue();
            this.dy = ((Double) (normalised.y * scale)).intValue();

        } else if (distance < CHASE_RADIUS * 2 && isChasing == true) {
            isChasing = false;
            this.setRandomMotion();
        }
    }

    public int getEndX() {
        return this.x + this.getWidth();
    }

    public int getEndY() {
        return this.y + this.getHeight();
    }

    public boolean stunned() {
        return this.board.getCurrentTime() - this.lastStun < STUN_DURATION;
    }

    public void act() {
        if (!this.stunned()) {
            this.x += this.dx;
            this.y += this.dy;
        }

        if (this.x <= 0) {
            this.x = 0;
            this.dx *= -1;
        } else if (this.getEndX() >= WORLD_WIDTH) {
            this.x = WORLD_WIDTH - this.getWidth();
            this.dx *= -1;
        }

        if (this.y <= 0) {
            this.y = 0;
            this.dy *= -1;
        } else if (this.getEndY() >= WORLD_HEIGHT) {
            this.y = WORLD_HEIGHT - this.getHeight();
            this.dy *= -1;
        }
    }

    public void stun() {
        this.lastStun = board.getCurrentTime();
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