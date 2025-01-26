package main.items;

import java.util.ArrayList;
import java.util.List;

public class WeaponRegistry {
    public static final List<Weapon> weapons = new ArrayList<>();

    static {
//        weapons.add(new Weapon(1,"Ложка Евы","Обычная ложка Евы,кажется она ей ест.",1));
//        weapons.add(new Weapon(2,"Палка","Обычная палка",2));
//        weapons.add(new Weapon(3,"Камень","Обычная камень",3));
//        weapons.add(new Weapon(4,"Кинжал Богдана","В далеком 2006, он был смертоносным.",4));
//        weapons.add(new Weapon(5,"Ножик","Обычная камень",5));
//        weapons.add(new Weapon(6,"Кусок НЛО","Дед Максим его поднял в далеком детстве," +
//                "оно или его облучило , или Леонардо с черепашек Ниндзя существует",6));
//        weapons.add(new Weapon(5,"Навали-наваля","Тут без коментариев",100));
    }
    public static List<Weapon> getAllWeapons() {
        return new ArrayList<>(weapons);
    }
    public static Weapon getWeaponById(int id) {
        return weapons.stream().filter(w -> w.getId() == id).findFirst().orElse(null);
    }

}
