package com.penta.library;

/**
 * Created by linyueyang on 2018/9/5.
 */

public class UUEventUtil {

    public static void sendEvent(UUListener listener, String key, Object data, int position) {
        listener.onEvent(newEvent(key, data, position));
    }

    public static void sendEvent(UUListener listener, String key, Object data) {
        sendEvent(listener, key, data, 0);
    }

    public static UUEvent newEvent(final String key, Object data, int position) {
        UUEvent uuEvent = new UUEvent() {
            @Override
            public String getEventId() {
                return key;
            }
        };
        uuEvent.setPosition(position);
        uuEvent.setData(data);
        return uuEvent;
    }
}
