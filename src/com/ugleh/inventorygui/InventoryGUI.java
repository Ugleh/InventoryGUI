/**
* The InventoryGUI Library enables other developers to not have to reinvent he wheel
* Using custom events you no longer need to worry about the inventory click event again.
* let us worry about which menu gets opened.
*
* @author  Ugleh
* @version 1.0
* @since   2018-02-23 
*/

package com.ugleh.inventorygui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import com.ugleh.inventorygui.events.ButtonClickedEvent;
import com.ugleh.inventorygui.events.MenuOpenedEvent;

public class InventoryGUI implements Listener{
	private ArrayList<InventoryMenu> inventoryMenus = new ArrayList<InventoryMenu>(); //ArrayList of registered Inventory Menus.
	
	public InventoryGUI(Plugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * InventoryOpenEvent event.
	 */
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e)
	{
		InventoryMenu inventoryMenu = getRegisteredMenuInventory(e.getInventory());
		if(inventoryMenu != null)
		{
			//This is an Inventory Menu
			MenuOpenedEvent menuOpenedEvent = new MenuOpenedEvent(inventoryMenu, (Player)e.getPlayer());
			Bukkit.getServer().getPluginManager().callEvent(menuOpenedEvent);
			
			if(menuOpenedEvent.isCancelled())
			{
				e.setCancelled(true);
			}
		}
	}
	
	/**
	 * InventoryClickEvent event.
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		InventoryMenu inventoryMenu = getRegisteredMenuInventory(e.getInventory());
		if(inventoryMenu != null)
		{
			//This is an Inventory Menu
			InventoryButton inventoryButton = inventoryMenu.getButtonList().get(e.getSlot());
			if(inventoryButton != null)
			{
				//This is a InventoryButton
				Player p = (Player) e.getWhoClicked();
				
				if(inventoryButton.isHandlePermission())
				{
					if(!p.hasPermission(inventoryButton.getPermissionNode()))
					{
						p.sendMessage(inventoryButton.getPermissionMessage());
						p.playSound(p.getLocation(), inventoryMenu.deniedSound, 1, 0);
						e.setCancelled(true);
					}
				}
				
				ButtonClickedEvent buttonClickedEvent = new ButtonClickedEvent(inventoryButton, inventoryMenu, p, e.getClick());
				Bukkit.getServer().getPluginManager().callEvent(buttonClickedEvent);
				if(!buttonClickedEvent.isCancelled())
				{
					inventoryButton.setToggleState(!inventoryButton.isToggleState());
					if(!(inventoryButton.getToggledItemStack() == null))
					{
						inventoryMenu.updateInventoryButtons();
					}
				}
				p.playSound(p.getLocation(), inventoryMenu.allowedSound, 1, 0);
				e.setCancelled(true);
			}

		}
	}
	
	/**
	 * Method used to get the InventoryMenu using the Inventory.
	 * 
	 * @param inventory Grabbing the InventoryMenu using the Inventory.
	 * @return InventoryMenu the InventoryMenu from the Inventory.
	 */
	private InventoryMenu getRegisteredMenuInventory(Inventory inventory) {
		for(InventoryMenu invMenu : inventoryMenus)
		{
			if(invMenu.getInventory().equals(inventory)) return invMenu;
		}
		return null;
	}

	/**
	 * Method used to register the Menu for Events in the InventoryGUI.
	 * 
	 * @param invMenu The Inventory Menu you are unregistering from the InventoyGUI Event Handler.
	 */
	public void registerMenu(InventoryMenu invMenu)
	{
		this.inventoryMenus.add(invMenu);
	}
	
	

	/**
	 * Method used to check if permissions will be handled by InventoryGUI.
	 * 
	 * @param invMenu The Inventory Menu you are unregistering from the InventoyGUI Event Handler.
	 */
	public void unregisterMenu(InventoryMenu invMenu)
	{
		this.inventoryMenus.remove(invMenu);
	}
	
	/**
	 * Method used to check if permissions will be handled by InventoryGUI.
	 * 
	 * @return ArrayList<InventoryMenu> Returns the Inventory Menus used for the InventoyGUI Event Handler.
	 */
	public ArrayList<InventoryMenu> getInventoryMenus() {
		return inventoryMenus;
	}
	
	
}
