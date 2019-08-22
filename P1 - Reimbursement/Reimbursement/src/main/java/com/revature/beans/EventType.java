package com.revature.beans;

public class EventType {
	private int eventTypeID;
    private String eventName;
    private int eventPercent;
    
	public int getEventTypeID() {
		return eventTypeID;
	}
	public void setEventTypeID(int eventTypeID) {
		this.eventTypeID = eventTypeID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getEventPercent() {
		return eventPercent;
	}
	public void setEventPercent(int eventPercent) {
		this.eventPercent = eventPercent;
	}
}
