package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite {
    private BufferedImage image;

    protected int x = 0;
    protected int y = 0;
    protected boolean dying = false;
    protected int dx = 0;
    protected int dy = 0;

    public boolean loadImage(String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            this.setImage(image);
            return true;

        } catch (Exception e) {
            System.out.println("IMAGE READ ERROR");
            return false;
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    public Dimension getCenter() {
        return new Dimension(
            this.x - this.getWidth()/2,
            this.y - this.getHeight()/2
        );
    }

    public void act() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getEndX() {
        return this.x + this.getWidth();
    }

    public int getEndY() {
        return this.y + this.getHeight();
    }

    public void onWallCollide(int x1, int y1, int x2, int y2) {
        if (this.x <= x1) {
            this.x = x1;
            this.dx *= -1;
        } else if (this.getEndX() >= x2) {
            this.x = x2 - this.getWidth();
            this.dx *= -1;
        }

        if (this.y <= y1) {
            this.y = y1;
            this.dy *= -1;
        } else if (this.getEndY() >= y2) {
            this.y = y2 - this.getHeight();
            this.dy *= -1;
        }
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }

    public static boolean isColliding(Sprite sprite1, Sprite sprite2) {
        //https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
        if (sprite1.getX() < sprite2.getX() + sprite2.getWidth() &&
            sprite1.getX() + sprite1.getWidth() > sprite2.getX() &&
            sprite1.getY() < sprite2.getY() + sprite2.getHeight() &&
            sprite1.getHeight() + sprite1.getY() > sprite2.getY()) {
            return true;
            // collision detected!
        }

        return false;
    }

    public boolean isColliding(Sprite sprite) {
        return this.isColliding(this, sprite);
    }

    public static Vector getVectorBetween(Sprite sprite1, Sprite sprite2) {
        return new Vector(
            sprite1.getX() - sprite2.getX()
            + (sprite1.getWidth() - sprite2.getWidth())/2,
            sprite1.getY() - sprite2.getY()
            + (sprite1.getHeight() - sprite2.getHeight())/2
        );
    }

    public Vector getVectorTo(Sprite sprite) {
        return this.getVectorBetween(this, sprite);
    }

    public static Double getDistanceBetween(Sprite sprite1, Sprite sprite2) {
        return getVectorBetween(sprite1, sprite2).getMagnitude();
    }

    public Double getDistanceFrom(Sprite sprite) {
        return this.getVectorBetween(this, sprite).getMagnitude();
    }
}