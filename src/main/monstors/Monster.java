package main.monstors;

public class Monster {

    private final int maxHealth;
    private String name;
        private int health;
        private int damage;
        private String image;


        public Monster(String name, int health, int damage, String image) {
            this.name = name;
            this.health = health;
            this.damage = damage;
            this.image = image;
            this.maxHealth = health;
        }
    public void resetHealth() {
        this.health = this.maxHealth;
    }

        public String getName() {
            return name;
        }



        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }



        public String getImage() {
            return image;
        }



        @Override
        public String toString() {
            return "Monster{" +
                    "name='" + name + '\'' +
                    ", health=" + health +
                    ", damage=" + damage +
                    ", image='" + image + '\'' +
                    '}';
        }

    }
