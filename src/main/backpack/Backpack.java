package main.backpack;

import main.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Backpack {
    private final Map<Integer,Integer> items = new HashMap<>();
    private final Map<Integer,Item> itemCatalog = new HashMap<>();

    public void addItem(Item item) {
        items.put(item.getId(),items.getOrDefault(item.getId(),0) + 1);
        itemCatalog.putIfAbsent(item.getId(), item);
    }
    public boolean useItem(int itemId) {
        if (items.containsKey(itemId) && items.get(itemId) > 0) {
            items.put(itemId, items.get(itemId) - 1);
            if (items.get(itemId) == 0) {
                items.remove(itemId);
            }
            return true;
        }
        return false;
    }
    public String showItems() {
        if (items.isEmpty()) {
            return "Инвентарь пуст =( ";
        }
        StringBuilder sb = new StringBuilder("Ваш инвентарь:\n");
        items.forEach((id, count) -> {
            Item item = itemCatalog.get(id);
            sb.append("- ").append(item.getName()).append(": ").append(count).append("\n");
        });
        return sb.toString();
    }
}