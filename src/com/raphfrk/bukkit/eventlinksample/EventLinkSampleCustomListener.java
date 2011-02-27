package com.raphfrk.bukkit.eventlinksample;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

import com.raphfrk.bukkit.eventlink.EventLinkCustomEvent;

public class EventLinkSampleCustomListener extends CustomEventListener {

	final EventLinkSample p;

	EventLinkSampleCustomListener(EventLinkSample p) {
		this.p = p;
	}

	public void onCustomEvent(Event event) {

		if(event.getEventName().equals("EventLinkSampleMessage")) {
			EventLinkSampleMessageEvent messageEvent = (EventLinkSampleMessageEvent)event;

			String message = messageEvent.getMessage();

			p.getServer().broadcastMessage("[EventLinkSample] Message: " + message);
			p.getServer().getLogger().info("[EventLinkSample] Message: " + message);
		}

	}
}

