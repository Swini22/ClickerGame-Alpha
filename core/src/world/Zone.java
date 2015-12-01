package world;

public class Zone {

	public static enum ZoneType {

		NORMAL, BOSS, SPECIAL

	}

	public static enum Biome {

		FOREST, DESERT, WATER

	}

	private float zoneNumber;
	private ZoneType zoneType;
	private Biome zoneBiome;

	public Zone(float number, ZoneType type, Biome biome) {
		zoneNumber = number;
		this.zoneType = type;
		this.zoneBiome = biome;
	}

	public float getZoneNumber() {
		return zoneNumber;
	}

	public void setZoneNumber(float zoneNumber) {
		this.zoneNumber = zoneNumber;
	}

	public ZoneType getZoneType() {
		return zoneType;
	}

	public void setZoneType(ZoneType zoneType) {
		this.zoneType = zoneType;
	}

	public Biome getZoneBiome() {
		return zoneBiome;
	}

	public void setZoneBiome(Biome zoneBiome) {
		this.zoneBiome = zoneBiome;
	}

}
