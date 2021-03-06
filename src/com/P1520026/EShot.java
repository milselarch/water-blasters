package com.P1520026;

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

    public Monster getHitStunnedMonster() {
        for (Monster monster : this.board.monsters) {
            if (Sprite.isColliding(this, monster)) {
                if (monster.stunned()) {
                    return monster;
                }
            }
        }

        return null;
    }
}
