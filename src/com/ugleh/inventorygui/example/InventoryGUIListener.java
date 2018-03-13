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
import com.ugleh.inventorygui.InventoryButtonBuilder;
import com.ugleh.inventorygui.InventoryGUI;
import com.ugleh.inventorygui.InventoryMenu;
import com.ugleh.inventorygui.events.ButtonClickEvent;
import com.ugleh.inventorygui.events.MenuOpenedEvent;

public class InventoryGUIListener implements Listener {
	public HashMap<String, InventoryMenu> ourPersonalMenus = new HashMap<String, InventoryMenu>();

	public InventoryGUIListener(Plugin plugin) {
		InventoryGUI inventoryGUI = new InventoryGUI(plugin);
		InventoryMenu invMenu = new InventoryMenu(ChatColor.DARK_PURPLE + "Example Menu", 9);
		inventoryGUI.registerMenu(invMenu);

		InventoryButton sendToSpawn = new InventoryButtonBuilder()
				// You can create an item using the InventoryButtonBuilder,
				// which lets you build the button with seperate methods.
				.setDisplayName(ChatColor.GOLD + "Go to Spawn")
				// Setting the Buttons Display Name
				.setItemStack(new ItemStack(Material.BED))
				// Setting the Button to the Material Type Bed, using ItemStack
				// allows support for different data types.
				.setFlag("cp_spawn")
				// Setting a custom flag that will be accessed in the
				// ButtonClickEvent
				.setLoreWordWrapped("Press to send yourself back to spawn.")
				// Setting the lore using the custom method LoreWordWrapped does
				// the word wrapping for us :)
				.build();
		// Don't forget to build it after or else you wont get an
		// InventoryButton.

		InventoryButton killSelf = new InventoryButton(ChatColor.GOLD + "Kill Self", new ItemStack(Material.ARROW),
				"cp_kill");
		killSelf.setLoreWordWrapped("Press to kill your self. No really, just press.");
		killSelf.setPermission("bukkit.command.kill", ChatColor.RED + "You do not have permission to use that.", true);

		InventoryButton setFire = new InventoryButtonBuilder()
				// Set Display Name
				.setDisplayName(ChatColor.GOLD + "Set On Fire")
				// Set the button material.
				.setItemStack(new ItemStack(Material.LAVA_BUCKET))
				// This button will be toggable, so I have set its seperate
				// button to a water bucket.
				.setToggledItemStack(new ItemStack(Material.WATER_BUCKET))
				// The coding for this will be too complex for just a single
				// command to toggle fire/no fire, so I am just going give it a
				// normal flag
				.setFlag("fire")
				// Setting its lore.
				.setLoreWordWrapped("Toggle yourself on/off fire.")
				// Build it
				.build();

		InventoryButton reload = new InventoryButton(ChatColor.GOLD + "Reload", new ItemStack(Material.HOPPER),
				"c_reload");
		reload.setLoreWordWrapped("Reload the server whenever with this button.");

		invMenu.addButton(0, sendToSpawn);
		invMenu.addButton(1, killSelf);
		invMenu.addButton(2, setFire);
		invMenu.addButton(8, reload);
	}

	@EventHandler
	public void onButtonClickEvent(ButtonClickEvent e) {
		InventoryButton b = e.getInventoryButton();
		String flag = b.getFlag();
		// This is a menu created by our plugin and not another using the same InventoryGUI.
		if (flag.contains("cp_")) {
			// cp_ is a personal prefix that tells me it is a button which requires command execution when pressed by Player.
			String command = flag.replace("cp_", "");
			e.getPlayer().performCommand(command);
		} else if (flag.contains("c_")) {
			// c_ is a personal prefix that tells me it is a button which requires command execution when pressed by Console.
			String command = flag.replace("c_", "");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
		} else if (flag.equals("fire")) {
			if (!b.isToggleState()) {
				b.setDisplayName(ChatColor.GOLD + "Remove Fire");
				e.getPlayer().setFireTicks(1000);
			} else {
				b.setDisplayName(ChatColor.GOLD + "Set on Fire");
				e.getPlayer().setFireTicks(0);
			}
		}
	}

	@EventHandler
	public void onMenuOpenedEvent(MenuOpenedEvent e) {

	}
}
