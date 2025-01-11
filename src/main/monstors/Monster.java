package main.monstors;

public class Monster {

        private String name;
        private int health;
        private int damage;
        private String image;

        public Monster(String name, int health, int damage, String image) {
            this.name = name;
            this.health = health;
            this.damage = damage;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void takeDamage(int damage) {
            this.health = Math.max(0, this.health - damage);
        }

        public boolean isAlive() {
            return this.health > 0;
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
