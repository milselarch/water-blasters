package com.milselarch;

/**
 * Created by user on 24/7/2017.
 */

import javax.swing.ImageIcon;

public class Monster extends Sprite {
    private Board board;

    private Bomb bomb;
    private final String alienImg = "src/images/alien.png";

    public Monster(Board board, int x, int y) {
        initAlien(board, x, y);
    }

    private void initAlien(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(alienImg);
        setImage(ii.getImage());
    }

    public void act(int direction) {
        this.x += direction;
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
            ImageIcon ii = new ImageIcon(bombImg);
            setImage(ii.getImage());

        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}