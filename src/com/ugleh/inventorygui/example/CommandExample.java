package com.ugleh.inventorygui.example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ugleh.inventorygui.InventoryMenu;

public class CommandExample implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String args, String[] argsArray) {
		if(!(sender instanceof Player)) return true;
		InventoryMenu inventoryMenu = ExampleInventoryGUI.getInstance().getListener().ourPersonalMenus.get("example_menu");
		inventoryMenu.showMenu((Player)sender);
		return true;
	}

}
