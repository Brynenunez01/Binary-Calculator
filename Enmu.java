import java.util.Random;

public class Enmu extends Boss {
    private int initialHealth;

    public Enmu() {
        super("Enmu", 120, 15);
        this.initialHealth = getHealth();
    }

    @Override
    public String getDialogue() {
        int currentHealth = getHealth();
        if (currentHealth > initialHealth * 0.75) {
            return "\nEnmu: \"You’re a persistent one, but this train will not stop for you!\"\n" +
                   "-".repeat(50);
        } else if (currentHealth > initialHealth * 0.5) {
            return "\nEnmu: \"Do you really think you can defeat me? I am the master of dreams!\"\n" +
                   "-".repeat(50);
        } else if (currentHealth > initialHealth * 0.25) {
            return "\nEnmu: \"How can this be? You will pay for this insolence!\"\n" +
                   "-".repeat(50);
        } else {
            return "\nEnmu: \"No... it can't end like this... I won't let you escape my nightmare!\"\n" +
                   "-".repeat(50);
        }
    }

    @Override
    public void attack(Character enemy) {
        Random rand = new Random();
        int skillChoice = rand.nextInt(3) + 1;

        switch (skillChoice) {
            case 1:
                if (getMana() >= 0) {
                    useSkill1(enemy);
                }
                break;
            case 2:
                if (getMana() >= 15) {
                    useSkill2(enemy);
                } else {
                    System.out.println("Enmu doesn't have enough mana for Skill 2!");
                }
                break;
            case 3:
                if (getMana() >= 20) {
                    useSkill3(enemy);
                } else {
                    System.out.println("Enmu doesn't have enough mana for Skill 3!");
                }
                break;
            default:
                break;
        }
    }

    public void useSkill1(Character enemy) {
        Random rand = new Random();
        int damage = 10 + rand.nextInt(11);
        System.out.println("Enmu uses Skill 1 and attacks " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() + 5);
        System.out.println("Enmu gains 5 mana! Current mana: " + getMana());
    }

    public void useSkill2(Character enemy) {
        Random rand = new Random();
        int damage = 20 + rand.nextInt(11);
        System.out.println("Enmu uses Skill 2 and attacks " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() - 15);
        System.out.println("Enmu uses 15 mana. Current mana: " + getMana());
    }

    public void useSkill3(Character enemy) {
        Random rand = new Random();
        int damage = 25 + rand.nextInt(21);
        System.out.println("Enmu uses his ultimate skill and attacks " + enemy.getName() + " for " + damage + " damage!");
        enemy.setHealth(enemy.getHealth() - damage);
        setMana(getMana() - 20);
        System.out.println("Enmu uses 20 mana. Current mana: " + getMana());
    }

    @Override
    public void displayStatus() {
        System.out.println(getName() + "'s Status:");
        System.out.println("Health : " + getHealth() + "/" + initialHealth);
        System.out.println("Mana : " + getMana() + "/100");
        System.out.println("=".repeat(30));
    }

    @Override
    public String toString() {
        return "\nBoss Encounter: Enmu\n" +
               "Name: " + getName() + "\n" +
               "Health: " + getHealth() + "/" + initialHealth + "\n" +
               "Base Damage: " + getBaseDamage() + "\n" +
               "Mana: " + getMana() + "/100\n" +
               "=".repeat(30);
    }
}
