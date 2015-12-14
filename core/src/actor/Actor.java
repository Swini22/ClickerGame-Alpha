package actor;

import actor.item.Armor;
import actor.item.Weapon;

import com.badlogic.gdx.graphics.Texture;

public class Actor {

    private String name;
    private float lifePoints;

    private Weapon weapon;
    private Armor headArmor;
    private Armor bodyArmor;
    private Armor armArmor;
    private Armor legArmor;
    private Armor footArmor;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Actor(String name, float lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints; }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getHeadArmor() {
        return headArmor;
    }

    public void setHeadArmor(Armor headArmor) {
        this.headArmor = headArmor;
    }

    public Armor getBodyArmor() {
        return bodyArmor;
    }

    public void setBodyArmor(Armor bodyArmor) {
        this.bodyArmor = bodyArmor;
    }

    public Armor getArmArmor() {
        return armArmor;
    }

    public void setArmArmor(Armor armArmor) {
        this.armArmor = armArmor;
    }

    public Armor getLegArmor() {
        return legArmor;
    }

    public void setLegArmor(Armor legArmor) {
        this.legArmor = legArmor;
    }

    public Armor getFootArmor() {
        return footArmor;
    }

    public void setFootArmor(Armor footArmor) {
        this.footArmor = footArmor;
    }

    public float getLifePoints() { return lifePoints; }

    public void setLifePoints(float lifePoints) {
        this.lifePoints = lifePoints;
    }

}
