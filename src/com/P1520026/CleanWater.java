package com.P1520026;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.P1520026.Commons.*;

public class CleanWater extends Sprite {
    private Board board;
    private Cooldown shotCooldown;

    private Double speedup = 1.0;

    private final String waterImg = "src/images/water.png";
    private boolean isChasing = false;
    private double lastStun;

    public CleanWater(Board board, int x, int y) {
        initWater(board, x, y);
    }

    private void initWater(Board board, int x, int y) {
        this.board = board;

        this.x = x;
        this.y = y;

        this.setRandomMotion();

        try {
            BufferedImage image = ImageIO.read(new File(waterImg));
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

    public void act() {
        super.act();
        this.onWallCollide(0,0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    public void draw(Graphics g) {
        g.drawImage(
            this.getImage(),
            this.getX() - this.board.worldx,
            this.getY() - this.board.worldy,
            this.board
        );
    }
}
