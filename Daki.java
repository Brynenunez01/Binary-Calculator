import java.util.Random;

public class Daki extends Boss {
    private int initialHealth;

    public Daki() {
        super("Daki", 200, 25);
        this.initialHealth = getHealth();
    }

    @Override
    public String getDialogue() {
        int currentHealth = getHealth();
        if (currentHealth > initialHealth * 0.75) {
            return "\nDaki: \"You are too weak to defeat me! Prepare to be crushed!\"\n" +
                   "-".repeat(50);
        } else if (currentHealth > initialHealth * 0.5) {
            return "\nDaki: \"You are persistent, but it won't save you!\"\n" +
                   "-".repeat(50);
        } else if (currentHealth > initialHealth * 0.25) {
            return "\nDaki: \"You think you can win now? You're mistaken!\"\n" +
                   "-".repeat(50);
        } else {
            return "\nDaki: \"No... I cannot lose to you!\"\n" +
                   "-".repeat(50);
        }
    }

    @Override
    public void attack(Character enemy) {
        Random rand = new Random();
        int skillChoice = rand.nextInt(3) + 1;

        switch (skillChoice) {
            case 1:
                if (getMana() >= 5) {
                    useSkill1(enemy);
                } else {
                    System.out.println("Daki doesn't have enough mana for Skill 1!");
                }
                break;
            case 2:
                if (getMana() >= 15) {
                    useSkill2(enemy);
                } else {
                    System.out.println("Daki doesn't have enough mana for Skill 2!");
                }
                break;
            case 3:
                if (getMana() >= 20) {
                    useSkill3(enemy);
                } else {
                    System.out.println("Daki doesn't have enough mana for Skill 3!");
                }
                break;
            default:
                break;
        }
    }

    public void useSkill1(Character enemy) {
        Random rand = new Random();
        int damage = 15 + rand.nextInt(11);
        System.out.println("Daki uses her tentacles to lash " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() + 5);
        System.out.println("Daki gains 5 mana! Current mana: " + getMana());
    }

    public void useSkill2(Character enemy) {
        Random rand = new Random();
        int damage = 20 + rand.nextInt(11);
        System.out.println("Daki uses her mesmerizing dance to confuse " + enemy.getName() + " and attacks for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() - 15);
        System.out.println("Daki uses 15 mana. Current mana: " + getMana());
    }

    public void useSkill3(Character enemy) {
        Random rand = new Random();
        int damage = 40 + rand.nextInt(21);
        System.out.println("Daki uses her ultimate tentacle strike to deal " + damage + " damage to " + enemy.getName() + "!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() - 20);
        System.out.println("Daki uses 20 mana. Current mana: " + getMana());
    }

    @Override
    public void displayStatus() {
        System.out.println("\n" + getName() + "'s Status:");
        System.out.println("Health : " + getHealth() + "/" + initialHealth);
        System.out.println("Mana : " + getMana() + "/100");
        System.out.println("=".repeat(30));
    }

    @Override
    public String toString() {
        return "\nBoss Encounter: Daki\n" +
               "Name: " + getName() + "\n" +
               "Health: " + getHealth() + "/" + initialHealth + "\n" +
               "Base Damage: " + getBaseDamage() + "\n" +
               "Mana: " + getMana() + "/100\n" +
               "=".repeat(30);
    }
}
