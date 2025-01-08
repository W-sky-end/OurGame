package main.Player;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private int health = 100;
    private Map<String, Integer> bagpack = new HashMap<>();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addItem(String itemName) {
        bagpack.put(itemName, bagpack.getOrDefault(itemName, 0) + 1);
    }

    public boolean removeItem(String itemName) {
        if (bagpack.containsKey(itemName) && bagpack.get(itemName) > 0) {
            bagpack.put(itemName, bagpack.get(itemName) - 1);
            if (bagpack.get(itemName) == 0) {
                bagpack.remove(itemName);

            }
            return true;
        }
        return false;
    }
    public String showBag() {
        if(bagpack.isEmpty()){
            return "Сумка пуста";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : bagpack.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
