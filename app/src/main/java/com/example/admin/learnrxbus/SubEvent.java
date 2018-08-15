package com.example.admin.learnrxbus;

/**
 * @author zhou.jn
 * @creator_at 2018/8/15 11:24
 */
public class SubEvent {
    private String eventName;
    private int eventId;

    public SubEvent(String mEventName, int mEventId) {
        this.eventName = mEventName;
        this.eventId = mEventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
