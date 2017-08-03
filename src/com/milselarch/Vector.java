package com.milselarch;

public class Vector {
    public Double x;
    public Double y;

    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Vector getNormalised() {
        Vector copy = this.getCopy();
        copy.normalise();
        return copy;
    }

    public void normalise() {
        double magnitude = this.getMagnitude();
        this.x /= magnitude;
        this.y /= magnitude;
    }

    public Vector getCopy() {
        return new Vector(x, y);
    }

    public void scale(double mul) {
        this.x *= mul;
        this.y *= mul;
    }


}
