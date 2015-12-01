package actor.item;

public class Weapon extends Item {

	public enum WeaponType {
		SWORD, AXE, BOW, STAFF
	}

	private WeaponType type;

	public Weapon() {
		super();
	}

}
