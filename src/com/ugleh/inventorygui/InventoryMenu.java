package com.ugleh.inventorygui;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryMenu {
	private int slotAmount;
	private String displayName = "";
	private Inventory inventory;
	private String permissionNode;
	private String permissionMessage;
	private String flag;
	public UUID uniqueID;
	public Sound allowedSound = Sound.ENTITY_CHICKEN_EGG;
	public Sound deniedSound = Sound.ENTITY_EVOCATION_ILLAGER_AMBIENT;
	
	private HashMap<Integer, InventoryButton> buttonList = new HashMap<Integer, InventoryButton>();
	
	public InventoryMenu(String displayName, int slotAmount) {
		this.uniqueID = UUID.randomUUID();
		this.setDisplayName(displayName);
		this.setSlotAmount(slotAmount);
		createInventory();
	}
	
	private void createInventory() {
		if((this.getSlotAmount() != 0) && (!this.getDisplayName().equals("")))
		{
			this.inventory = Bukkit.createInventory(null, this.getSlotAmount(), this.getDisplayName());
			updateInventoryButtons();
		}
	}

	public void updateInventoryButtons() {
		for(Entry<Integer, InventoryButton> button : buttonList.entrySet())
		{
			if(button.getValue().isToggleState())
			{
				if(!(button.getValue().getToggledItemStack() == null))
				{
					this.getInventory().setItem(button.getKey(), button.getValue().getToggledItemStack());
				}
			}else
			{
				this.getInventory().setItem(button.getKey(), button.getValue().getItemStack());
			}
		}
	}

	public int getSlotAmount() {
		return slotAmount;
	}

	public void setSlotAmount(int slotAmount) {
		this.slotAmount = slotAmount;
		createInventory();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		createInventory();
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public HashMap<Integer, InventoryButton> getButtonList() {
		return buttonList;
	}
	
	public void addButton(int slotNumber, InventoryButton button)
	{
		this.getButtonList().put(slotNumber, button);
		updateInventoryButtons();
	}
	
	public void removeButton(InventoryButton button)
	{
		if(this.getButtonList().containsValue(button))
		{
			for(Entry<Integer, InventoryButton> b : this.getButtonList().entrySet())
			{
				if(b.getValue().equals(button))
				{
					this.getButtonList().remove(b.getKey());
				}
			}
		}
		updateInventoryButtons();
	}
	public void removeButtonAtSlot(int slotNumber)
	{
		this.getButtonList().remove(slotNumber);
		updateInventoryButtons();
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

	public void showMenu(Player player) {
		player.openInventory(this.getInventory());
	}
	
	public void overrideAllowedSound(Sound s)
	{
		this.allowedSound = s;
	}
	public void overrideDeniedSound(Sound s)
	{
		this.deniedSound = s;
	}
}
