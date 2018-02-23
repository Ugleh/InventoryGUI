package com.ugleh.inventorygui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryButton {
	private String displayName;
	private ItemStack itemStack;
	private ItemStack toggledItemStack;
	private List<String> lore = new ArrayList<String>();
	private String permissionNode;
	private String permissionMessage;
	private boolean handlePermission = false;
	private boolean toggleState = false;
	private String flag;
	public UUID uniqueID;
	
	public InventoryButton(String displayName, ItemStack itemStack, String flag)
	{
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.flag = flag;
		this.uniqueID = UUID.randomUUID();
		
		updateButton();
	}
	
	public InventoryButton(String displayName, ItemStack itemStack, ItemStack toggledItemStack, String flag)
	{
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.toggledItemStack = toggledItemStack;
		this.flag = flag;
		this.uniqueID = UUID.randomUUID();
		
		updateButton();
	}

	public InventoryButton(String displayName, ItemStack itemStack, ItemStack toggledItemStack, String flag, String permissionNode, String permissionMessage)
	{
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.toggledItemStack = toggledItemStack;
		this.flag = flag;
		this.permissionNode = permissionNode;
		this.permissionMessage = permissionMessage;
		this.uniqueID = UUID.randomUUID();
		
		updateButton();
		
	}
	
	public InventoryButton(String displayName, ItemStack itemStack, String flag, String permissionNode, String permissionMessage)
	{
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.flag = flag;
		this.permissionNode = permissionNode;
		this.permissionMessage = permissionMessage;
		this.uniqueID = UUID.randomUUID();
		
		updateButton();
		
	}
	
	
	
	public void updateButton() {
		
		ItemStack item = this.getItemStack();
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(this.getDisplayName());
		itemMeta.setLore(this.lore);
		item.setItemMeta(itemMeta);
		this.itemStack = item;
		
		if(this.hasToggleItem())
		{
			item = this.getToggledItemStack();
			itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(this.getDisplayName());
			itemMeta.setLore(this.lore);
			item.setItemMeta(itemMeta);
			this.toggledItemStack = (item);

		}
	}

	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		updateButton();
	}
	public ItemStack getItemStack() {
		return itemStack;
	}
	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
		updateButton();
	}
	public ItemStack getToggledItemStack() {
		return toggledItemStack;
	}
	public void setToggledItemStack(ItemStack toggledItemStack) {
		this.toggledItemStack = toggledItemStack;
		updateButton();
	}
	public String getPermissionNode() {
		return permissionNode;
	}
	public void setPermissionNode(String permissionNode) {
		this.permissionNode = permissionNode;
	}
	public String getPermissionMessage() {
		return permissionMessage;
	}
	public void setPermissionMessage(String permissionMessage) {
		this.permissionMessage = permissionMessage;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public UUID getUniqueID() {
		return uniqueID;
	}
	public boolean isHandlePermission() {
		return handlePermission;
	}

	public void setHandlePermission(boolean handlePermission) {
		this.handlePermission = handlePermission;
	}

	public boolean isToggleState() {
		return toggleState;
	}

	public void setToggleState(boolean toggleState) {
		this.toggleState = toggleState;
	}
	
	public boolean hasToggleItem()
	{
		return !(this.getToggledItemStack() == null);
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
		updateButton();
	}
	
	public void setLoreWordWrapped(String string){
		StringBuilder sb = new StringBuilder(string);

		int i = 0;
		while (i + 35 < sb.length() && (i = sb.lastIndexOf(" ", i + 35)) != -1) {
		    sb.replace(i, i + 1, "\n");
		}
		this.lore = Arrays.asList(sb.toString().split("\n"));
		updateButton();
	}
}
