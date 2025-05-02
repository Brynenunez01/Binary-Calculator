import java.util.Random;

public abstract class Character {
    private String name;
    private int health;
    private int maxHealth;
    private int mana;

    public Character(String name, int health, int mana) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.min(mana, 100);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void useSkill1(Boss enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(15) + 10;
        System.out.println(this.name + " uses Skill 1 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        this.mana = Math.min(this.mana + 5, 100);
        System.out.println(this.name + " gains 5 mana! Current mana: " + this.mana);
    }

    public void useSkill2(Boss enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(25) + 20;
        if (this.mana >= 15) {
            System.out.println(this.name + " uses Skill 2 on " + enemy.getName() + " for " + damage + " damage!");
            enemy.setHealth(enemy.getHealth() - damage);
            this.mana -= 15;
            System.out.println(this.name + " uses 15 mana. Current mana: " + this.mana);
        } else {
            System.out.println("Not enough mana to use Skill 2!");
        }
    }

    public void useSkill3(Boss enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(30) + 25;
        if (this.mana >= 20) {
            System.out.println(this.name + " uses Skill 3 on " + enemy.getName() + " for " + damage + " damage!");
            enemy.setHealth(enemy.getHealth() - damage);
            this.mana -= 20;
            System.out.println(this.name + " uses 20 mana. Current mana: " + this.mana);
        } else {
            System.out.println("Not enough mana to use Skill 3!");
        }
    }

    public abstract String getSkill1Description();
    public abstract String getSkill2Description();
    public abstract String getSkill3Description();

    public void restoreHealthAndMana() {
        this.health = maxHealth;
        this.mana = 100;
        System.out.println("Your health and mana have been fully restored!");
    }

    public void displayStatus() {
        System.out.println("\n" + "-".repeat(30));
        System.out.println(this.name + "'s Status");
        System.out.println("-".repeat(30));
        System.out.printf("Health: %-5d/%-5d\n", this.health, this.maxHealth);
        System.out.printf("Mana: %-5d/100\n", this.mana);
        System.out.println("-".repeat(30));
    }
    
    public void displaySkills() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("Available Skills");
        System.out.println("=".repeat(30));
        System.out.println("1. " + getSkill1Description());
        System.out.println("2. " + getSkill2Description());
        System.out.println("3. " + getSkill3Description());
        System.out.println("=".repeat(30));
    }

    @Override
    public String toString() {
        return "\nCharacter Status\n" +
               "Name: " + name + "\n" +
               "Health: " + health + "/" + maxHealth + "\n" +
               "Mana: " + mana + "/100\n" +
               "-".repeat(30);
    }
}