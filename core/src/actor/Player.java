package actor;

import java.util.ArrayList;

import actor.item.Item;

public class Player {

	public static final float ATTACK_COOLDOWN = 1f;

	private ArrayList<Item> inventory;

	private String name;

    private double gold;

    private double exp;

	public Player(String name, double gold, double exp) {

		this.name = name;
		this.gold = gold;
		this.exp = exp;

	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public double getExp() {
        return exp;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

}
