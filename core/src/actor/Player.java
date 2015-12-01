package actor;

import java.util.ArrayList;

import actor.item.Item;

public class Player extends Actor {

	public static final float ATTACK_COOLDOWN = 1f;

	private ArrayList<Item> inventory;

	public Player(float lifePoints) {
		super(lifePoints);
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

}
