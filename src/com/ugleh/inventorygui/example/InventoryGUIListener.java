package com.ugleh.inventorygui.example;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.ugleh.inventorygui.InventoryButton;
import com.ugleh.inventorygui.InventoryGUI;
import com.ugleh.inventorygui.InventoryMenu;
import com.ugleh.inventorygui.events.ButtonClickedEvent;
import com.ugleh.inventorygui.events.MenuOpenedEvent;

public class InventoryGUIListener implements Listener {
	public HashMap<String, InventoryMenu> ourPersonalMenus = new HashMap<String, InventoryMenu>();
	
	public InventoryGUIListener(Plugin plugin)
	{
		InventoryGUI invGUI = new InventoryGUI(plugin);
		
		InventoryMenu invMenu = new InventoryMenu(ChatColor.DARK_PURPLE + "Example Menu", 9);

		InventoryButton sendHome = new InventoryButton(ChatColor.GOLD + "Go to Spawn", new ItemStack(Material.BED), "cp_spawn");
		sendHome.setLoreWordWrapped("Press to send yourself back to spawn.");
		InventoryButton killSelf = new InventoryButton(ChatColor.GOLD + "Kill Self", new ItemStack(Material.ARROW), "cp_kill");
		killSelf.setLoreWordWrapped("Press to kill your self. No really, just press.");
		InventoryButton setFire = new InventoryButton(ChatColor.GOLD + "Set On Fire", new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.WATER_BUCKET), "fire");
		setFire.setLoreWordWrapped("Toggle yourself on/off fire.");
		InventoryButton reload = new InventoryButton(ChatColor.GOLD + "Reload", new ItemStack(Material.HOPPER), "c_reload");
		reload.setLoreWordWrapped("Reload the server whenever with this button.");
		
		invMenu.addButton(0, sendHome);
		invMenu.addButton(1, killSelf);
		invMenu.addButton(2, setFire);
		invMenu.addButton(8, reload);
		
		ourPersonalMenus.put("example_menu", invMenu);
		invGUI.registerMenu(invMenu);
		
	}
	@EventHandler
	public void onButtonClickedEvent(ButtonClickedEvent e)
	{
		InventoryButton b = e.getInventoryButton();
		String flag = b.getFlag();
		if(ourPersonalMenus.containsValue(e.getInventoryMenu()))
		{
			//This is a menu created by our plugin and not another using the same InventoryGUI.
			if(flag.contains("cp_"))
			{
				//cp_ is a personal prefix that tells me it is a button which requires command execution when pressed by Player.
				String command = flag.replace("cp_", "");
				e.getPlayer().performCommand(command);
			}else if(flag.contains("c_"))
			{
				//c_ is a personal prefix that tells me it is a button which requires command execution when pressed by Console.
				String command = flag.replace("c_", "");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
			}else if(flag.equals("fire"))
			{
				if(!b.isToggleState())
				{
					b.setDisplayName(ChatColor.GOLD + "Remove Fire");
					e.getPlayer().setFireTicks(1000);
				}else
				{
					b.setDisplayName(ChatColor.GOLD + "Set on Fire");
					e.getPlayer().setFireTicks(0);
				}
			}
		}
	}
	
	@EventHandler
	public void onMenuOpenedEvent(MenuOpenedEvent e)
	{
		
	}
}
