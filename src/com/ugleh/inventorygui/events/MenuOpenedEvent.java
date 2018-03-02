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

import com.ugleh.inventorygui.InventoryMenu;

public class MenuOpenedEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private InventoryMenu inventoryMenu;
	private Player player;

	/**
	 * This Event is used to notify when a player has opened said menu. Mostly
	 * used for sending a message or something.
	 * 
	 * @param inventoryMenu
	 *            This is the InventoryMenu being opened.
	 * @param player
	 *            This is the player that is doing the opening.
	 */
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

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * @return InventoryMenu This returns the inventory menu being used.
	 */
	public InventoryMenu getInventoryMenu() {
		return inventoryMenu;
	}

	/**
	 * @return Player This returns the Player that is doing the opening.
	 */
	public Player getPlayer() {
		return this.player;
	}
}
