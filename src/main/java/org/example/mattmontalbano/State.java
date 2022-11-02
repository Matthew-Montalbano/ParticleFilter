package org.example.mattmontalbano;

public class State {

    private double _x;
    private double _y;
    private double _xVel;
    private double _yVel;

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
        _xVel = xVel;
    }

    public double getYVelocity() {
        return _yVel;
    }

    public void setYVelocity(double yVel) {
        _yVel = yVel;
    }
}
