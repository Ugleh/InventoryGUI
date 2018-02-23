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
	private ArrayList<InventoryMenu> inventoryMenus = new ArrayList<InventoryMenu>();
	
	public InventoryGUI(Plugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
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
	
	private InventoryMenu getRegisteredMenuInventory(Inventory inventory) {
		for(InventoryMenu invMenu : inventoryMenus)
		{
			if(invMenu.getInventory().equals(inventory)) return invMenu;
		}
		return null;
	}


	public void registerMenu(InventoryMenu invMenu)
	{
		this.inventoryMenus.add(invMenu);
	}
	
	public void unregisterMenu(InventoryMenu invMenu)
	{
		this.inventoryMenus.remove(invMenu);
	}
	
	public ArrayList<InventoryMenu> getInventoryMenus() {
		return inventoryMenus;
	}
	
	
}
