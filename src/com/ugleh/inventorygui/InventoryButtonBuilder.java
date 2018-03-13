package com.ugleh.inventorygui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class InventoryButtonBuilder {
	private String displayName; // Display Name given to the ItemStack used for the button.
	private ItemStack itemStack; // The ItemStack set for the button.
	private ItemStack toggledItemStack; // If set the buttons ItemStack will change to this one during its toggled state.
	private List<String> lore = new ArrayList<String>(); // The lore set to the ItemStack when being created/modified.
	private String permissionNode; // Permission Node used to check if a player should be able to use said item.
	private String permissionMessage; // Permission Message is sent to Player if they are not allowed to use said button.
	private boolean handlePermission = false; // Should we handle permissions with this button?
	private boolean toggleState = false; // The toggable state of the button.
	private String flag; // String used for anything. I use it for commands.
	
	public InventoryButton build() {
		return new InventoryButton(displayName, itemStack, toggledItemStack, flag, permissionNode, permissionMessage);

	}

	public String getDisplayName() {
		return displayName;
	}

	public InventoryButtonBuilder setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public InventoryButtonBuilder setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
		return this;
	}

	public ItemStack getToggledItemStack() {
		return toggledItemStack;
	}

	public InventoryButtonBuilder setToggledItemStack(ItemStack toggledItemStack) {
		this.toggledItemStack = toggledItemStack;
		return this;
	}

	public List<String> getLore() {
		return lore;
	}

	public InventoryButtonBuilder setLore(List<String> lore) {
		this.lore = lore;
		return this;
	}

	public String getPermissionNode() {
		return permissionNode;
	}
	
	public InventoryButtonBuilder setPermission(String permissionNode, String permissionMessage) {
		this.permissionNode = permissionNode;
		this.permissionMessage = permissionMessage;
		return this;
	}
	
	public String getPermissionMessage() {
		return permissionMessage;
	}

	public boolean isHandlePermission() {
		return handlePermission;
	}

	public InventoryButtonBuilder setHandlePermission(boolean handlePermission) {
		this.handlePermission = handlePermission;
		return this;
	}

	public boolean isToggleState() {
		return toggleState;
	}

	public InventoryButtonBuilder setToggleState(boolean toggleState) {
		this.toggleState = toggleState;
		return this;
	}

	public String getFlag() {
		return flag;
	}

	public InventoryButtonBuilder setFlag(String flag) {
		this.flag = flag;
		return this;
	}
	
	/**
	 * Method used to set the stored Lore of the button in a String form, having us word wrap it.
	 * 
	 * @param lore The lore set using a String.
	 */
	public InventoryButtonBuilder setLoreWordWrapped(String lore) {
		StringBuilder sb = new StringBuilder(lore);

		int i = 0;
		while (i + 35 < sb.length() && (i = sb.lastIndexOf(" ", i + 35)) != -1) {
			sb.replace(i, i + 1, "\n");
		}
		this.lore = Arrays.asList(sb.toString().split("\n"));
		return this;
	}
	
}
