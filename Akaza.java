import java.util.Random;

public class Akaza extends Boss {
    private int initialHealth;

    public Akaza() {
        super("Akaza", 250, 30);
        this.initialHealth = getHealth();
    }

    @Override
    public String getDialogue() {
        int currentHealth = getHealth();
        if (currentHealth > initialHealth * 0.75) {
            return "\nAkaza: \"I will not let you defeat me, mortal! Prepare to die!\"\n" +
                   "-".repeat(50);
        } else if (currentHealth > initialHealth * 0.5) {
            return "\nAkaza: \"You're tougher than I thought, but it's still over for you!\"\n" +
                   "-".repeat(50);
        } else if (currentHealth > initialHealth * 0.25) {
            return "\nAkaza: \"You're pushing me, but I will crush you!\"\n" +
                   "-".repeat(50);
        } else {
            return "\nAkaza: \"Impossible... I will not be defeated by you!\"\n" +
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
                    System.out.println("Akaza doesn't have enough mana for Skill 1!");
                }
                break;
            case 2:
                if (getMana() >= 15) {
                    useSkill2(enemy);
                } else {
                    System.out.println("Akaza doesn't have enough mana for Skill 2!");
                }
                break;
            case 3:
                if (getMana() >= 20) {
                    useSkill3(enemy);
                } else {
                    System.out.println("Akaza doesn't have enough mana for Skill 3!");
                }
                break;
            default:
                break;
        }
    }

    public void useSkill1(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(15) + 10;
        System.out.println("Akaza uses Skill 1 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() + 5);
        System.out.println("Akaza gains 5 mana! Current mana: " + getMana());
    }

    public void useSkill2(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(25) + 20;
        System.out.println("Akaza uses Skill 2 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() - 15);
        System.out.println("Akaza uses 15 mana. Current mana: " + getMana());
    }

    public void useSkill3(Character enemy) {
        Random rand = new Random();
        int damage = rand.nextInt(30) + 25;
        System.out.println("Akaza uses Skill 3 on " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() - 20);
        System.out.println("Akaza uses 20 mana. Current mana: " + getMana());
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
        return "\nBoss Encounter: Akaza\n" +
               "Name: " + getName() + "\n" +
               "Health: " + getHealth() + "/" + initialHealth + "\n" +
               "Base Damage: " + getBaseDamage() + "\n" +
               "Mana: " + getMana() + "/100\n" +
               "=".repeat(30);
    }
}
