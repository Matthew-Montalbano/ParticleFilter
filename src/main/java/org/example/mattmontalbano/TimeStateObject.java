package org.example.mattmontalbano;

public class TimeStateObject extends State {
    protected long _time;

    public TimeStateObject(double x, double y, double xVelocity, double yVelocity, long time) {
        super(x, y, xVelocity, yVelocity);
        _time = time;
    }

    public long getTime() {
        return _time;
    }

    public void setTime(long time) {
        this._time = time;
    }
}
