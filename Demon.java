import java.util.Random;

public class Demon extends Boss {
    private final int level;
    private final Random random = new Random();

    public Demon(String name, int level) {
        super(name, 50 + (level * 25), 5 + (level * 5));  
        this.level = level;
    }

    @Override
    public void attack(Character player) {
        Random rand = new Random();
        int damageDealt = getBaseDamage() + rand.nextInt(6); 
        System.out.println(getName() + " attacks " + player.getName() + " for " + damageDealt + " damage!");
        player.setHealth(player.getHealth() - damageDealt); 
    }

    @Override
    public void useSkill1(Character enemy) {
        int damage = random.nextInt(10 * level) + 10;
        System.out.println(getName() + " uses Skill 1 (Claw Slash) on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage); 
        setMana(getMana() + 5);
        System.out.println(getName() + " gains 5 mana. Current mana: " + getMana());
    }

    @Override
    public void useSkill2(Character enemy) {
        if (getMana() >= 15) {
            int damage = random.nextInt(15 * level) + 15;
            System.out.println(getName() + " uses Skill 2 (Shadow Strike) on " + enemy.getName() + " for " + damage + " damage!");
            enemy.setHealth(enemy.getHealth() - damage); 
            setMana(getMana() - 15); 
            System.out.println(getName() + " uses 15 mana. Current mana: " + getMana());
        } else {
            System.out.println(getName() + " doesn't have enough mana for Skill 2!");
        }
    }

    @Override
    public void useSkill3(Character enemy) {
        if (getMana() >= 20) {
            int damage = random.nextInt(20 * level) + 20; 
            System.out.println(getName() + " uses Skill 3 (Dark Fury) on " + enemy.getName() + " for " + damage + " damage!");
            enemy.setHealth(enemy.getHealth() - damage); 
            setMana(getMana() - 20);
            System.out.println(getName() + " uses 20 mana. Current mana: " + getMana());
        } else {
            System.out.println(getName() + " doesn't have enough mana for Skill 3!");
        }
    }

    @Override
    public String getDialogue() {
        return "The demon snarls ferociously, its eyes glowing with malice!";
    }

    public void dropLoot(Inventory inventory) {
        int gold = random.nextInt(10 * level) + 10;  
        int healthPotions = random.nextInt(level) + 1;
        int manaPotions = random.nextInt(level) + 1;   

        inventory.addGold(gold);
        inventory.addHealthPotion(healthPotions);
        inventory.addManaPotion(manaPotions);

        System.out.println("The demon drops " + gold + " gold, " +
                healthPotions + " Health Potion(s), and " +
                manaPotions + " Mana Potion(s)!");
    }

    @Override
    public void displayStatus() {
        System.out.println("\n" + getName() + "'s Status:");
        System.out.println("Level : " + level);
        System.out.println("Health : " + getHealth() + "/" + getMaxHealth());
        System.out.println("Mana : " + getMana() + "/100");
        System.out.println("Base Damage : " + getBaseDamage());
        System.out.println("=".repeat(30));
    }
}
