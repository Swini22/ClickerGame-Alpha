package actor.item;

public class Armor extends Item {

	public enum ArmorType {
		BODY, HEAD, ARM, LEG, FOOT
	}

	private ArmorType type;

	public Armor(ArmorType type) {
		super();
		this.type = type;
	}

}
