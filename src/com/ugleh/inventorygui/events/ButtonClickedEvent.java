/**
* The InventoryGUI Library enables other developers to not have to reinvent he wheel
* Using custom events you no longer need to worry about the inventory click event again.
* let us worry about which menu gets opened.
*
* @author  Ugleh
* @version 1.0
* @since   2018-02-23 
*/

package com.ugleh.inventorygui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;

import com.ugleh.inventorygui.InventoryButton;
import com.ugleh.inventorygui.InventoryMenu;

public class ButtonClickedEvent extends Event implements Cancellable{
	
	private static final HandlerList handlers  = new HandlerList();
	private boolean cancelled;
	private InventoryButton inventoryButton;
	private InventoryMenu inventoryMenu;
	private Player player;
	private ClickType clickType;
	
	public ButtonClickedEvent(InventoryButton inventoryButton, InventoryMenu inventoryMenu, Player player, ClickType clickType)
	{
		this.inventoryMenu = inventoryMenu;
		this.inventoryButton = inventoryButton;
		this.player = player;
		this.clickType = clickType;
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

	public InventoryButton getInventoryButton() {
		return inventoryButton;
	}
	public ClickType getClickType() {
		return clickType;
	}
}
