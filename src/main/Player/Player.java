package main.Player;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private  int health = 100;
    private Map<String, Integer> backpack = new HashMap<>();

    public  int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addItem(String itemName) {
        backpack.put(itemName, backpack.getOrDefault(itemName, 0) + 1);
    }

    public boolean removeItem(String itemName) {
        if (backpack.containsKey(itemName) && backpack.get(itemName) > 0) {
            backpack.put(itemName, backpack.get(itemName) - 1);
            if (backpack.get(itemName) == 0) {
                backpack.remove(itemName);

            }
            return true;
        }
        return false;
    }
    public String showBag() {
        if(backpack.isEmpty()){
            return "Сумка пуста";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : backpack.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
