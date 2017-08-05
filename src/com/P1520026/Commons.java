package com.P1520026;

/**
 * Created by user on 24/7/2017.
 */

public interface Commons {
    public static final int BOARD_WIDTH = 1200;
    public static final int BOARD_HEIGHT = 700;
    public static final int WORLD_WIDTH = 1200 * 4;
    public static final int WORLD_HEIGHT = 700 * 4;

    public static final int PLAYER_HEALTH = 10;
    public static final int CLEAN_WATERS = 6;
    public static final int DIRTY_WATERS = 200;

    public static final int CHASE_RADIUS = 500;

    public static final double ENEMY_SHOT_COOLDOWN = 3;
    public static final double PLAYER_SHOT_COOLDOWN = 0.2;
    public static final double ENEMY_SHOT_SPEED = 6;
    public static final double PLAYER_SHOT_SPEED = 20;

    public static final int STUN_DURATION = 10;
    public static final int DELAY = 17;

    public static final int GAME_LOST = 0;
    public static final int GAME_INGAME = 1;
    public static final int GAME_WON = 2;
    public static final int GAME_START = 3;
}
