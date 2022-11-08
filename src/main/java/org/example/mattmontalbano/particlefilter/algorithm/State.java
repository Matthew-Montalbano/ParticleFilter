package org.example.mattmontalbano.particlefilter.algorithm;

public class State {

    protected double _x;
    protected double _y;
    private double _xVel;
    private double _yVel;

    public static final double MAX_SPEED = 5; // meters per second

    public State(double x, double y, double xVelocity, double yVelocity) {
        _x = x;
        _y = y;
        _xVel = xVelocity;
        _yVel = yVelocity;
    }

    public double getX() {
        return _x;
    }

    public void setX(double x) {
        _x = x;
    }

    public double getY() {
        return _y;
    }

    public void setY(double y) {
        _y = y;
    }

    public double getXVelocity() {
        return _xVel;
    }

    public void setXVelocity(double xVel) {
        _xVel = enforceSpeedBounds(xVel);
    }

    public double getYVelocity() {
        return _yVel;
    }

    public void setYVelocity(double yVel) {
        _yVel = enforceSpeedBounds(yVel);
    }

    private double enforceSpeedBounds(double speed) {
        if (speed < -MAX_SPEED) {
            return -MAX_SPEED;
        } else if (speed > MAX_SPEED) {
            return MAX_SPEED;
        } else {
            return speed;
        }
    }
}
