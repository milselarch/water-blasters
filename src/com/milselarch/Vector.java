package com.milselarch;

public class Vector {
    public Double x;
    public Double y;

    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector normalise() {
        double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return new Vector(x/magnitude, y/magnitude);
    }

    public void scale(double mul) {
        this.x *= mul;
        this.y *= mul;
    }
}
