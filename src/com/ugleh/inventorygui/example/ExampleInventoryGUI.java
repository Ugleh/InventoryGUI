package com.ugleh.inventorygui.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
	
	
	public static ExampleInventoryGUI getInstance()
	{
		return instance;
	}
	
	public InventoryGUIListener getListener()
	{
		return listener;
	}
}
