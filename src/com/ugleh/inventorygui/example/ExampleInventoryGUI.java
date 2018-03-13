package com.ugleh.inventorygui.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import com.ugleh.inventorygui.InventoryMenu;

public class ExampleInventoryGUI extends JavaPlugin{

	public static ExampleInventoryGUI instance;
	public InventoryGUIListener listener;
	
	@Override
	public void onEnable()
	{
		instance = this;
		Bukkit.getPluginCommand("example").setExecutor(new CommandExample());
		Bukkit.getPluginManager().registerEvents(listener = new InventoryGUIListener(this), this);
	}
	
	
	@Override
	public void onDisable()
	{
		for(Entry<String, InventoryMenu> entry : this.getListener().ourPersonalMenus.entrySet())
		{
			List<HumanEntity> clonedViewers = new ArrayList<HumanEntity>(entry.getValue().getInventory().getViewers());
			
			for(HumanEntity player : clonedViewers)
			{
				player.closeInventory();
			}
		}
	}
	
	public static ExampleInventoryGUI getInstance()
	{
		return instance;
	}
	
	public InventoryGUIListener getListener()
	{
		return listener;
	}
}
