package com.P1520026;

/**
 * Created by user on 24/7/2017.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.P1520026.Commons.*;

public class Shot extends Sprite {
    String shotImg = "src/images/shot.png";
    Board board;

    public Shot(Board board, int x, int y, int dx, int dy) {
        this.board = board;
        initShot(board, x, y, dx, dy);
    }

    private void initShot(Board board, int x, int y, int dx, int dy) {
        this.board = board;

        try {
            BufferedImage image = ImageIO.read(new File(this.shotImg));
            this.setImage(image);
        } catch (Exception e) {
            System.out.println("IMAGE READ ERROR");
        }

        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void draw(Graphics g) {
        g.drawImage(
            this.getImage(),
            this.getX() - this.board.worldx,
            this.getY() - this.board.worldy,
            this.board
        );
    }

    public boolean isHitingWall() {
        if (this.x <= 0) {
            return true;
        } else if (this.getEndX() >= WORLD_WIDTH) {
            return true;
        }

        if (this.y <= 0) {
            return true;
        } else if (this.getEndY() >= WORLD_HEIGHT) {
            return true;
        }

        return false;
    }
}