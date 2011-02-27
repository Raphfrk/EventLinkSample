package com.raphfrk.bukkit.eventlinksample;

import java.io.File;
import java.util.Arrays;

import org.bukkit.event.server.PluginEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.raphfrk.bukkit.eventlink.EventLink;

public class EventLinkSampleServerListener extends ServerListener {

	private final EventLinkSample p;

	EventLinkSampleServerListener(EventLinkSample p) {
		this.p = p;
	}

	public void onPluginEnabled(PluginEvent event) {
		connect(event.getPlugin());
	}

	public void onPluginDisabled(PluginEvent event) {
		disconnect(event.getPlugin());
	}

	public void connectAttempt() {
		synchronized(p) {
			Plugin plugin = p.getServer().getPluginManager().getPlugin("EventLink");
			if (plugin == null) {
				return;
			} else {
				if(p.eventLink == null && plugin instanceof EventLink) {
					linkTo((EventLink)plugin);
				}
			}
		}
	}

	public void connect(Plugin plugin) {
		synchronized(p) {
			if(plugin instanceof EventLink) {
				linkTo((EventLink)plugin);
			}
		}
	}

	public void disconnect(Plugin plugin) {
		synchronized(p) {
			if(plugin instanceof EventLink) {
				if(p.eventLink == (EventLink)plugin) {
					linkTo(null);
				}
			}
		}
	}

	private void linkTo(EventLink plugin) {
		synchronized(p) {
			if(plugin != null) {
				p.getServer().getLogger().info("EventLinkSample connected to EventLink");
			} else {
				p.getServer().getLogger().info("EventLinkSample disconnecting from EventLink");
			}
			p.eventLink = plugin;
		}
	}

}
