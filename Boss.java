import java.util.Random;

public abstract class Boss {
    private String name;
    private int health;
    private int maxHealth;
    private int mana;
    private int baseDamage;
    private boolean paralyzed = false; 
    private int paralysisTurns = 0; 
    private int burnTurns = 0;           
    private int burnDamage = 0;

    public Boss(String name, int health, int baseDamage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.baseDamage = baseDamage;
        this.mana = 100;
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
        this.mana = Math.max(0, mana);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    
    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }

    public void setParalysisTurns(int turns) {
        this.paralysisTurns = turns;
    }

    public void decrementParalysisTurns() {
        if (paralysisTurns > 0) {
            paralysisTurns--;
            if (paralysisTurns == 0) {
                setParalyzed(false);  
                System.out.println(this.name + " is no longer paralyzed.");
            }
        }
    }

    public void applyBurn() {
        if (burnTurns > 0) {
            health -= burnDamage;
            burnTurns--;
            System.out.println(name + " is burned! It takes " + burnDamage + " damage from the burn!");
            System.out.println(name + " has " + burnTurns + " turns of burn left.");
        }
    }

    public void setBurnEffect(int turns, int damage) {
        this.burnTurns = turns;
        this.burnDamage = damage;
        System.out.println(name + " is now burning for " + burnTurns + " turns, taking " + burnDamage + " damage each turn.");
    }

    public void attack(Character enemy) {
        if (paralyzed) {
            System.out.println(this.name + " is paralyzed and cannot attack this turn.");
            decrementParalysisTurns(); 
        } else {
            Random rand = new Random();
            int damageDealt = baseDamage + rand.nextInt(11);
            System.out.println(this.name + " attacks " + enemy.getName() + " for " + damageDealt + " damage!");
            enemy.setHealth(enemy.getHealth() - damageDealt); 
        }
    }

    public void useSkill(Character enemy) {
        Random rand = new Random();
        int skillChoice = rand.nextInt(3) + 1;

        if (paralyzed) {
            System.out.println(this.name + " is paralyzed and cannot use any skills this turn.");
            decrementParalysisTurns(); 
            return;
        }

        switch (skillChoice) {
            case 1:
                if (this.mana >= 5) {
                    useSkill1(enemy);
                } else {
                    System.out.println(this.name + " doesn't have enough mana for Skill 1!");
                }
                break;
            case 2:
                if (this.mana >= 15) {
                    useSkill2(enemy);
                } else {
                    System.out.println(this.name + " doesn't have enough mana for Skill 2!");
                }
                break;
            case 3:
                if (this.mana >= 20) {
                    useSkill3(enemy);
                } else {
                    System.out.println(this.name + " doesn't have enough mana for Skill 3!");
                }
                break;
            default:
                break;
        }
    }

    public void useSkill1(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(15) + 10;
        System.out.println(this.name + " uses Skill 1 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        this.mana += 5;
        System.out.println(this.name + " gains 5 mana! Current mana: " + this.mana);
    }

    public void useSkill2(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(25) + 20;
        System.out.println(this.name + " uses Skill 2 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        this.mana -= 15;
        System.out.println(this.name + " uses 15 mana. Current mana: " + this.mana);
    }

    public void useSkill3(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(30) + 25;
        System.out.println(this.name + " uses Skill 3 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        this.mana -= 20;
        System.out.println(this.name + " uses 20 mana. Current mana: " + this.mana);
    }

    public abstract String getDialogue();

    public void displayStatus() {
        
        System.out.println("\n" + this.name + "'s Status:");
        System.out.println("Health : " + this.health + "/" + this.maxHealth);
        System.out.println("Mana : " + this.mana + "/100");
        System.out.println("=".repeat(30));
    }

    @Override
    public String toString() {
        return "\nBoss Encounter\n" +
               "Name: " + name + "\n" +
               "Health: " + health + "/" + maxHealth + "\n" +
               "Base Damage: " + baseDamage + "\n" +
               "Mana: " + mana + "\n" +
               "=".repeat(30);
    }
}
