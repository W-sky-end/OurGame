package main.items;

public class HealthPotion extends Item{
    private final int healAmount;

    public HealthPotion() {
        super(1, "Зелье здоровья", "Восстанавливает 20 здоровья.");
        this.healAmount = 20;
    }

    public int getHealAmount() {
        return healAmount;
    }
}