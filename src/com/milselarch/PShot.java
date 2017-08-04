package com.milselarch;

public class PShot extends Shot {
    private final String shotImg = "src/images/pshot.png";

    PShot(Board board, int x, int y, int dx, int dy) {
        super(board, x, y, dx, dy);
        this.loadImage(shotImg);
    }

    public Monster getHitMonster() {
        for (Monster monster : this.board.monsters) {
            if (Sprite.isColliding(this, monster)) {
                return monster;
            }
        }

        return null;
    }
}
