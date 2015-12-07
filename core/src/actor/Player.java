package actor;

import java.util.ArrayList;

import actor.item.Item;

public class Player {

	public static final float ATTACK_COOLDOWN = 1f;

	private ArrayList<Item> inventory;

	private int id;
	private String name;

	public Player() {
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
