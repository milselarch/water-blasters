package com.P1520026;

/**
 * Created by user on 24/7/2017.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Monster extends Sprite implements Commons {
    private Board board;
    private Cooldown shotCooldown;

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
        this.shotCooldown = new Cooldown(ENEMY_SHOT_COOLDOWN, true);

        this.x = x;
        this.y = y;

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
            Double scale = 1.0 + Math.sqrt(CHASE_RADIUS / distance) / 2.0;

            Vector normalised = displacement.getNormalised();
            //System.out.println(displacement.x + ", " + displacement.y);
            this.dx = ((Double) (normalised.x * scale)).intValue();
            this.dy = ((Double) (normalised.y * scale)).intValue();
            this.fireShot(normalised);

        } else if (distance < CHASE_RADIUS * 2 && isChasing) {
            isChasing = false;
            this.setRandomMotion();
        }
    }

    public void fireShot(Vector direction) {
        if (!stunned() && this.shotCooldown.startIfCooledDown()) {
            direction.scale(ENEMY_SHOT_SPEED);

            EShot eshot = new EShot(
                this.board, this.getX(), this.getY(),
                direction.x.intValue(),
                direction.y.intValue()
            );

            this.board.eshots.add(eshot);
        }
    }

    public boolean stunned() {
        return this.board.getCurrentTime() - this.lastStun < STUN_DURATION;
    }

    @Override
    public void act() {
        if (!this.stunned()) { super.act(); }
        this.onWallCollide(0,0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    public void draw(Graphics2D g2d) {
        float opacity;
        if (this.stunned()) { opacity = 0.5f; }
        else { opacity = 1; }

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

    public void stun() {
        this.lastStun = board.getCurrentTime();
    }
}