package actor.item;

public class Weapon extends Equipment {

	public enum WeaponType {
		SWORD, AXE, BOW, STAFF
	}

	private WeaponType type;

	public Weapon() {
		super();
	}

}
