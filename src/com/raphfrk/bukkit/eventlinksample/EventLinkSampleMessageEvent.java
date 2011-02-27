package com.raphfrk.bukkit.eventlinksample;

import org.bukkit.event.Event;

public class EventLinkSampleMessageEvent extends Event {

	private static final long serialVersionUID = 1L;

	private final String message;
	
	EventLinkSampleMessageEvent(String message) {
		super("EventLinkSampleMessage");
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
