package com.ugleh.inventorygui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.ugleh.inventorygui.InventoryMenu;

public class MenuOpenedEvent extends Event implements Cancellable{

	private static final HandlerList handlers  = new HandlerList();
	private boolean cancelled;
	private InventoryMenu inventoryMenu;
	private Player player;
	
	public MenuOpenedEvent(InventoryMenu inventoryMenu, Player player) {
		this.inventoryMenu = inventoryMenu;
		this.player = player;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {

		cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	public InventoryMenu getInventoryMenu() {
		return inventoryMenu;
	}
	public Player getPlayer()
	{
		return this.player;
	}
}
