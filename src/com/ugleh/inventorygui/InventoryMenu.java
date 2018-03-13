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

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryMenu {
	private int slotAmount; //Used to set the Inventories slot amount. This may not equal the same as the actual inventory amount.
	private String displayName; //Display name given to the inventory.
	private Inventory inventory; //The stored inventory for the Inventory menu.
	private String permissionNode; //Permission string node checked when the inventory is attempted to open.
	private String permissionMessage; //Permission message sent to the player if they do not have permission.
	private String flag; //A flag saved for any reason at all. It is just a custom string you can use.
	public UUID uniqueID; //Unique ID that is given to InventoryMenu when it is initiated.
	public Sound allowedSound = Sound.ENTITY_CHICKEN_EGG; //Default Allowed Button Press sound for InventoryMenu buttons.
	public Sound deniedSound = Sound.ENTITY_EVOCATION_ILLAGER_AMBIENT; //Default Denied Button Press sound for InventoryMenu buttons.
	private HashMap<Integer, InventoryButton> buttonList = new HashMap<Integer, InventoryButton>(); //InventoryMenu stores the buttons in a list before adding them to the inventory.
	/**
	 * InventoryMenu Constructor.
	 * @param displayName the name you want to give the Inventory.
	 * @param slotAmount the Slot Amount you want to give the Inventory.
	 */
	public InventoryMenu(String displayName, int slotAmount) {
		this.uniqueID = UUID.randomUUID();
		this.setDisplayName(displayName);
		this.setSlotAmount(slotAmount);
		createInventory();
	}
	
	/**
	 * Used by the InventoryGUI Library to create the Inventory after every crucial change.
	 */
	private void createInventory() {
		if ((this.getSlotAmount() != 0) && (!this.getDisplayName().equals(""))) {
			this.inventory = Bukkit.createInventory(null, this.getSlotAmount(), this.getDisplayName());
			updateInventoryButtons();
		}
	}

	/**
	 * Used by the InventoryGUI Library to update the Inventory Buttons.
	 */
	public void updateInventoryButtons() {
		for (Entry<Integer, InventoryButton> button : buttonList.entrySet()) {
			if (button.getValue().isToggleState()) {
				if (!(button.getValue().getToggledItemStack() == null)) {
					this.getInventory().setItem(button.getKey(), button.getValue().getToggledItemStack());
				}
			} else {
				this.getInventory().setItem(button.getKey(), button.getValue().getItemStack());
			}
		}
	}

	/**
	 * Used to get the slot amount of the InventoryMenu.
	 * 
	 * @return int Returns the Inventory Slot Amount for the Inventory.
	 */
	public int getSlotAmount() {
		return slotAmount;
	}

	/**
	 * Used to set the slot amount of the InventoryMenu.
	 * 
	 * @param slotAmount
	 *            the Inventory Slot Amount for the Inventory.
	 */
	public void setSlotAmount(int slotAmount) {
		this.slotAmount = slotAmount;
		createInventory();
	}

	/**
	 * Used to get the Display Name of the InventoryMenu.
	 * 
	 * @return Returns the Inventory Display Name that was set earlier.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Used to set the Display Name of the InventoryMenu at any given time.
	 * 
	 * @param displayName
	 *            The Inventory Display Name.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		createInventory();
	}

	/**
	 * Method to grab the Inventory used.
	 * 
	 * @return Inventory returns the Bukkit Inventory used in the InventoryMenu.
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Method to grab a list of buttons in the menu.
	 * 
	 * @return HashMap<Integer, InventoryButton> A HashMap used to store the
	 *         buttons in an InventoryMenu used by the Library.
	 */
	public HashMap<Integer, InventoryButton> getButtonList() {
		return buttonList;
	}

	/**
	 * Used to add a button to the InventoryMenu.
	 * 
	 * @param slotNumber
	 *            choose a slot number in which to add a button or override an
	 *            existing slot.
	 * @param button
	 *            The InventoryButton you are adding.
	 */
	public void addButton(int slotNumber, InventoryButton button) {
		this.getButtonList().put(slotNumber, button);
		updateInventoryButtons();
	}

	/**
	 * Used to remove a button if you don't know where it is in the menu.
	 * 
	 * @param button
	 *            The InventoryButton that will be removed from everywhere in
	 *            the menu.
	 */
	public void removeButton(InventoryButton button) {
		if (this.getButtonList().containsValue(button)) {
			for (Entry<Integer, InventoryButton> b : this.getButtonList().entrySet()) {
				if (b.getValue().equals(button)) {
					this.getButtonList().remove(b.getKey());
				}
			}
		}
		updateInventoryButtons();
	}

	/**
	 * Used to remove a button from inside a menu.
	 * 
	 * @param slotNumber
	 *            The Slot Number you want to remove the button at.
	 */

	public void removeButtonAtSlot(int slotNumber) {
		this.getButtonList().remove(slotNumber);
		updateInventoryButtons();
	}

	/**
	 * Gets the permission node.
	 * 
	 * @return String the permission checked to see if the player has permission
	 *         to use the buttons inside a menu.
	 */
	public String getPermissionNode() {
		return permissionNode;
	}

	/**
	 * Gets the permission message.
	 * 
	 * @return String the message shown to the Player when denied permission
	 *         from a button.
	 */
	public void setPermissionNode(String permissionNode) {
		this.permissionNode = permissionNode;
	}

	/**
	 * Gets the permission message.
	 * 
	 * @return String the message shown to the Player when denied permission
	 *         from a button.
	 */
	public String getPermissionMessage() {
		return permissionMessage;
	}

	/**
	 * Sets the permission message.
	 * 
	 * @param permissionMessage
	 *            the message shown to the Player when denied permission from a
	 *            button.
	 */

	public void setPermissionMessage(String permissionMessage) {
		this.permissionMessage = permissionMessage;
	}

	public void setPermission(String permissionNode, String permissionMessage)
	{
		this.permissionNode = permissionNode;
		this.permissionMessage = permissionMessage;
	}
	/**
	 * Returns the flag you set previously.
	 * 
	 * @return String the flag you set previously.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * Give this InventoryMenu a custom flag for any use.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Returns the Unique ID of the Menu.
	 * 
	 * @return UUID Returns the Unique ID of the Menu.
	 */

	public UUID getUniqueID() {
		return uniqueID;
	}

	/**
	 * Used to show the menu to the player.
	 * 
	 * @param Player
	 *            the player you want to show the menu to.
	 */

	public void showMenu(Player player) {
		// The InventoryMenu has Permission Nodes set, so check if the Player
		// has permission.
		if (!this.getPermissionNode().equals("")) {
			// The player has permission to use menu so open the menu up.
			if (player.hasPermission(this.getPermissionNode())) {
				player.openInventory(this.getInventory());
			}
			// The player does not have permission so send them the Permission
			// Message (if set)
			else if (!this.getPermissionMessage().equals("")) {
				player.sendMessage(this.getPermissionMessage());
			}
			// InventoryMenu does not have a permission node set so just open
			// the inventory.
		} else {
			player.openInventory(this.getInventory());
		}
	}

	/**
	 * Used to change the sound played when a user has permission and has
	 * sucessfully clicked a button.
	 */

	public void overrideAllowedSound(Sound s) {
		this.allowedSound = s;
	}

	/**
	 * Used to change the sound played when a user is denied permission of
	 * clicking a button.
	 */
	public void overrideDeniedSound(Sound s) {
		this.deniedSound = s;
	}
}
