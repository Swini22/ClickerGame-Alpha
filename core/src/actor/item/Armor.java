package actor.item;

public class Armor extends Equipment {

	public enum ArmorType {
		BODY, HEAD, ARM, LEG, FOOT
	}

	private ArmorType type;

	public Armor(ArmorType type) {
		super();
		this.type = type;
	}

}
