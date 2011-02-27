package com.raphfrk.bukkit.eventlinksample;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.raphfrk.bukkit.eventlink.EventLink;

public class EventLinkSample extends JavaPlugin {

	private Server server;
	private PluginManager pm;
	
	EventLink eventLink = null;
	
	EventLinkSampleServerListener eventLinkSampleServerListener = new EventLinkSampleServerListener(this);
	EventLinkSampleCustomListener eventLinkSampleCustomListener = new EventLinkSampleCustomListener(this);

	public void onEnable() {
		String name = "EventLinkSample";

		pm = getServer().getPluginManager();
		server = getServer();
		
		server.getLogger().info(name + " initialized");
		
		pm.registerEvent(Type.PLUGIN_ENABLE, eventLinkSampleServerListener, Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, eventLinkSampleCustomListener, Priority.Normal, this);
		
		eventLinkSampleServerListener.connectAttempt();
	}

	public void onDisable() {

	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {

		if(!commandSender.isOp()) {
			return false;
		}
		
		if(commandLabel.equals("send") && args.length > 1) {
			if(eventLink != null) {
				String message = implode(args, 1, " ");
				if(eventLink.sendEvent(args[0], new EventLinkSampleMessageEvent(message))) {
					commandSender.sendMessage("Sent message event: " + message);
				} else {
					commandSender.sendMessage("Message send failed");
				}
			}
			return true;
		}
		
		return false;
	}
	
	private String implode(String[] split, int start, String separator) {
		
		StringBuilder sb = new StringBuilder();
		
		boolean first = true;
		for(int cnt=start;cnt<split.length;cnt++) {
			if(!first) {
				sb.append(separator);
			} else {
				first = false;
			}
			sb.append(split[cnt]);
		}
		
		return sb.toString();
		
	}
	

}