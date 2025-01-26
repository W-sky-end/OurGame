package main.items;

public class Weapon extends Item {
    private final int attackBonus;

    public Weapon(int id, String name, String description, int attackBonus) {
        super(id, name, description);
        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }
}