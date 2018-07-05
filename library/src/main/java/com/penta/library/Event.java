package com.penta.library;

/**
 * Created by linyueyang on 2018/7/4.
 */

public abstract class Event {

    public abstract String getEventId();

    int position;

    Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
