package com.milselarch;

public class EShot extends Shot {
    private final String shotImg = "src/images/eshot.png";

    EShot(Board board, int x, int y, int dx, int dy) {
        super(board, x, y, dx, dy);
        this.loadImage(shotImg);
    }

    public boolean hitPlayer() {
        if (this.board.player.isColliding(this)) {
            return true;
        }

        return false;
    }
}
