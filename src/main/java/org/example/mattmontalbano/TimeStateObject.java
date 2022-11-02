package org.example.mattmontalbano;

public class TimeStateObject {
    protected State _state;
    protected long _time;

    public TimeStateObject(double x, double y, double xVelocity, double yVelocity, long time) {
        _state = new State(x, y, xVelocity, yVelocity);
        _time = time;
    }

    public State getState() {
        return _state;
    }

    public void setState(State state) {
        this._state = state;
    }

    public long getTime() {
        return _time;
    }

    public void setTime(long time) {
        this._time = time;
    }
}
