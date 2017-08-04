package com.milselarch;

public class Cooldown {
    private Double lastTime;
    private Double duration;

    Cooldown(Double duration, boolean start) {
        this.duration = duration;

        if (start == true) {
            lastTime = currentTime();
        } else {
            lastTime = currentTime() - duration;
        }
    }

    public static Double currentTime() {
        return System.currentTimeMillis()/1000.0;
    }

    public boolean isCoolingDown() {
        if (this.currentTime() - lastTime < duration) {
            return true;
        }

        return false;
    }

    public void start() {
        lastTime = currentTime();
    }

    public boolean startIfCooledDown() {
        if (!this.isCoolingDown()) {
            this.start();
            return true;
        }

        return false;
    }
}
