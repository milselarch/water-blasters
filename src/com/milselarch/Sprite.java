package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite {
    private boolean visible;

    private BufferedImage image;

    protected int x = 0;
    protected int y = 0;
    protected boolean dying = false;
    protected int dx = 0;
    protected int dy = 0;

    public Sprite() {
        visible = true;
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
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

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }
}