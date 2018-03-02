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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryButton {
	private String displayName; // Display Name given to the ItemStack used for
								// the button.
	private ItemStack itemStack; // The ItemStack set for the button.
	private ItemStack toggledItemStack; // If set the buttons ItemStack will
										// change to this one during its toggled
										// state.
	private List<String> lore = new ArrayList<String>(); // The lore set to the
															// ItemStack when
															// being
															// created/modified.
	private String permissionNode; // Permission Node used to check if a player
									// should be able to use said item.
	private String permissionMessage; // Permission Message is sent to Player if
										// they are not allowed to use said
										// button.
	private boolean handlePermission = false; // Should we handle permissions
												// with this button?
	private boolean toggleState = false; // The toggable state of the button.
	private String flag; // String used for anything. I use it for commands.
	public UUID uniqueID; // A Unique ID generated when the button is created.

	/**
	 * InventoryButton Constructor with the bare minimum
	 * 
	 * @param displayName
	 *            the name you want to give the Inventory.
	 * @param itemStack
	 *            the ItemStack you want the button to use.
	 * @param flag
	 *            A String that can be used for anything but mostly for
	 *            commands.
	 */
	public InventoryButton(String displayName, ItemStack itemStack, String flag) {
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.flag = flag;
		this.uniqueID = UUID.randomUUID();

		updateButton();
	}

	/**
	 * InventoryButton Constructor that uses a toggled itemstack.
	 * 
	 * @param displayName
	 *            the name you want to give the Inventory.
	 * @param itemStack
	 *            the ItemStack you want the button to use.
	 * @param toggledItemStack
	 *            the ItemStack you want the button to use when toggled.
	 * @param flag
	 *            A String that can be used for anything but mostly for
	 *            commands.
	 */
	public InventoryButton(String displayName, ItemStack itemStack, ItemStack toggledItemStack, String flag) {
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.toggledItemStack = toggledItemStack;
		this.flag = flag;
		this.uniqueID = UUID.randomUUID();

		updateButton();
	}

	/**
	 * InventoryButton Constructor that uses a toggled itemstack and requires a
	 * Player to have permission.
	 * 
	 * @param displayName
	 *            the name you want to give the Inventory.
	 * @param itemStack
	 *            the ItemStack you want the button to use.
	 * @param toggledItemStack
	 *            the ItemStack you want the button to use when toggled.
	 * @param flag
	 *            A String that can be used for anything but mostly for
	 *            commands.
	 * @param permissionNode
	 *            Permission Node used to check if a player should be able to
	 *            use said item.
	 * @param permissionMessage
	 *            Permission Message is sent to Player if they are not allowed
	 *            to use said button.
	 */
	public InventoryButton(String displayName, ItemStack itemStack, ItemStack toggledItemStack, String flag,
			String permissionNode, String permissionMessage) {
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.toggledItemStack = toggledItemStack;
		this.flag = flag;
		this.permissionNode = permissionNode;
		this.permissionMessage = permissionMessage;
		this.uniqueID = UUID.randomUUID();

		updateButton();

	}

	/**
	 * InventoryButton Constructor that requires a Player to have permission.
	 * 
	 * @param displayName
	 *            the name you want to give the Inventory.
	 * @param itemStack
	 *            the ItemStack you want the button to use.
	 * @param toggledItemStack
	 *            the ItemStack you want the button to use when toggled.
	 * @param flag
	 *            A String that can be used for anything but mostly for
	 *            commands.
	 * @param permissionNode
	 *            Permission Node used to check if a player should be able to
	 *            use said item.
	 * @param permissionMessage
	 *            Permission Message is sent to Player if they are not allowed
	 *            to use said button.
	 */
	public InventoryButton(String displayName, ItemStack itemStack, String flag, String permissionNode,
			String permissionMessage) {
		this.displayName = displayName;
		this.itemStack = itemStack;
		this.flag = flag;
		this.permissionNode = permissionNode;
		this.permissionMessage = permissionMessage;
		this.uniqueID = UUID.randomUUID();

		updateButton();

	}

	/**
	 * Method used to update the button after it has been created or edited.
	 */
	public void updateButton() {

		ItemStack item = this.getItemStack();
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(this.getDisplayName());
		itemMeta.setLore(this.lore);
		item.setItemMeta(itemMeta);
		this.itemStack = item;

		if (this.hasToggleItem()) {
			item = this.getToggledItemStack();
			itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(this.getDisplayName());
			itemMeta.setLore(this.lore);
			item.setItemMeta(itemMeta);
			this.toggledItemStack = (item);

		}
	}

	/**
	 * Method used to grab the display name of the button.
	 * 
	 * @return String the display name set to the button.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Method used to set the display name of the button.
	 * 
	 * @param displayName
	 *            The Display Name set to the button.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		updateButton();
	}

	/**
	 * Method used to grab the ItemStack of the button.
	 * 
	 * @return ItemStack the ItemStack of the button.
	 */
	public ItemStack getItemStack() {
		return itemStack;
	}

	/**
	 * Method used to set the ItemStack of the button.
	 * 
	 * @param itemStack
	 *            The ItemStack set to the button.
	 */
	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
		updateButton();
	}

	/**
	 * Method used to get the Toggled ItemStack.
	 * 
	 * @return ItemStack returns the toggled ItemStack.
	 */
	public ItemStack getToggledItemStack() {
		return toggledItemStack;
	}

	/**
	 * Method used to set the toggled ItemStack of the button.
	 * 
	 * @param toggledItemStack
	 *            The toggled ItemStack set to the button.
	 */
	public void setToggledItemStack(ItemStack toggledItemStack) {
		this.toggledItemStack = toggledItemStack;
		updateButton();
	}

	/**
	 * Method used to get the Permission Node string.
	 * 
	 * @return String The Permission Node that is checked against the player to
	 *         see if they can press the button.
	 */
	public String getPermissionNode() {
		return permissionNode;
	}

	/**
	 * Method used to set the Permission Node string.
	 * 
	 * @param permissionNode
	 *            The Permission Node is checked against the player who presses
	 *            the button.
	 */
	public void setPermissionNode(String permissionNode) {
		this.permissionNode = permissionNode;
	}

	/**
	 * Method used to get the Permission Message string.
	 * 
	 * @return String The Permission Message is displayed to the Player when
	 *         they do not have permission to use said button.
	 */
	public String getPermissionMessage() {
		return permissionMessage;
	}

	/**
	 * Method used to set the Permission Message string.
	 * 
	 * @param permissionNode
	 *            The Permission Message is displayed to the Player when they do
	 *            not have permission to use said button.
	 */
	public void setPermissionMessage(String permissionMessage) {
		this.permissionMessage = permissionMessage;
	}

	/**
	 * Method used to get the Flag String.
	 * 
	 * @return String The Flag is a custom variable for creators use, mostly
	 *         used for command execution.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * Method used to set the Flag String.
	 * 
	 * @param flag
	 *            The Flag is a custom variable for creators use, mostly used
	 *            for command execution.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Method used to get the the Unique ID.
	 * 
	 * @return UUID The Unique ID for this button.
	 */
	public UUID getUniqueID() {
		return uniqueID;
	}

	/**
	 * Method used to check if permissions will be handled by InventoryGUI.
	 * 
	 * @return boolean The boolean if permissions will be handled by InventoryGUI.
	 */
	public boolean isHandlePermission() {
		return handlePermission;
	}

	/**
	 * Method used to set if permissions will be handled by InventoryGUI.
	 * 
	 * @param handlePermission Sets if InventoryGUI should handle the permissions.
	 */
	public void setHandlePermission(boolean handlePermission) {
		this.handlePermission = handlePermission;
	}
	
	/**
	 * Method used to check if the button is in its toggled state.
	 * 
	 * @return boolean The toggle state of this button.
	 */
	public boolean isToggleState() {
		return toggleState;
	}

	/**
	 * Method used to set the toggle state of the button, used by InventoryGUI.
	 * 
	 * @param toggleState Sets the toggle state of the button.
	 */
	public void setToggleState(boolean toggleState) {
		this.toggleState = toggleState;
	}
	
	/**
	 * Method used to see if this InventoryButton has a toggled ItemStack.
	 * 
	 * @return boolean Returns TRUE if this InventoryButon has a toggle itemstack.
	 */
	public boolean hasToggleItem() {
		return !(this.getToggledItemStack() == null);
	}
	
	/**
	 * Method used to return the stored Lore of the button.
	 * 
	 * @return List<String> The lore of the button.
	 */
	public List<String> getLore() {
		return lore;
	}
	
	/**
	 * Method used to set the stored Lore of the button in a List<String> form.
	 * 
	 * @param lore The lore set using a List.
	 */
	public void setLore(List<String> lore) {
		this.lore = lore;
		updateButton();
	}
	
	/**
	 * Method used to set the stored Lore of the button in a String form, having us word wrap it.
	 * 
	 * @param lore The lore set using a String.
	 */
	public void setLoreWordWrapped(String lore) {
		StringBuilder sb = new StringBuilder(lore);

		int i = 0;
		while (i + 35 < sb.length() && (i = sb.lastIndexOf(" ", i + 35)) != -1) {
			sb.replace(i, i + 1, "\n");
		}
		this.lore = Arrays.asList(sb.toString().split("\n"));
		updateButton();
	}
}
