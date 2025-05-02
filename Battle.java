import java.util.Scanner;
import java.util.Random;

public class Battle {
    private final Scanner scanner;
    private final Inventory inventory;

    public Battle(Scanner scanner, Inventory inventory) {
        this.scanner = scanner;
        this.inventory = inventory;
    }

    public boolean engage(Character player, Boss opponent) {
        int turnCounter = 1;
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                       " + opponent.getName() + " Appears!                     ");
            System.out.println(opponent.getDialogue());
            System.out.println("=".repeat(60) + "\n");

            while (player.isAlive() && opponent.isAlive()) {
                System.out.println("\n" + "-".repeat(60));
                System.out.println("                         Round " + turnCounter + "                          ");
                System.out.println("-".repeat(60));

                player.displayStatus();
                displayBossStatus(opponent);

                System.out.println("\n" + player.getName() + "'s turn. Choose your action:");
                player.displaySkills();
                System.out.println("4. Use Health Potion (" + inventory.getHealthPotions() + ")");
                System.out.println("5. Use Mana Potion (" + inventory.getManaPotions() + ")");
                System.out.print("Enter your choice (1/2/3/4/5): ");

                int skillChoice = getPlayerChoice();

                if (skillChoice == -1) {
                    continue;
                }

                boolean validChoice = false;

                try {
                    switch (skillChoice) {
                        case 1 -> {
                            player.useSkill1(opponent);
                            validChoice = true;
                        }
                        case 2 -> {
                            player.useSkill2(opponent);
                            validChoice = true;
                        }
                        case 3 -> {
                            player.useSkill3(opponent);
                            validChoice = true;
                        }
                        case 4 -> {
                            useHealthPotion(player);
                            validChoice = true;
                        }
                        case 5 -> {
                            useManaPotion(player);
                            validChoice = true;
                        }
                        default -> System.out.println("Invalid choice, turn skipped.");
                    }

                    if (validChoice) {
                        player.displayStatus();
                        displayBossStatus(opponent);

                        if (opponent.isAlive()) {
                            if (opponent.isParalyzed()) {
                                System.out.println(opponent.getName() + " is paralyzed and cannot act this turn.");
                                opponent.decrementParalysisTurns();  
                            } else {
                                System.out.println("\n" + opponent.getName() + "'s turn");
                                opponent.attack(player);
                            }

                            if (!player.isAlive()) {
                                System.out.println(player.getName() + " has been defeated!");
                              
                                returnToMainMenu();
                                break; 
                            }
                        } else {
                            System.out.println(opponent.getName() + " has been defeated!");
                            if (opponent instanceof Demon) {
                                ((Demon) opponent).dropLoot(inventory);
                            } else {
                                rewardPlayerWithPotionsAndGold();
                            }
                            return true; 
                        }

                        turnCounter++;
                    }

                    if (opponent.isAlive()) {
                        System.out.println("\n" + opponent.getDialogue());
                    }
                } catch (Exception e) {
                    System.out.println("Error during player action: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the battle: " + e.getMessage());
            e.printStackTrace();
        }

        return false;  
    }

    private int getPlayerChoice() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input, please enter a number between 1 and 5.");
            scanner.nextLine();
            return -1;
        } finally {
            scanner.nextLine();
        }
    }

    private void useHealthPotion(Character player) {
        try {
            if (inventory.useHealthPotion(player)) {
                System.out.println(player.getName() + " used a Health Potion!");
            } else {
                System.out.println("No Health Potions available!");
            }
        } catch (Exception e) {
            System.out.println("Error using health potion: " + e.getMessage());
        }
    }

    private void useManaPotion(Character player) {
        try {
            if (inventory.useManaPotion(player)) {
                System.out.println(player.getName() + " used a Mana Potion!");
            } else {
                System.out.println("No Mana Potions available!");
            }
        } catch (Exception e) {
            System.out.println("Error using mana potion: " + e.getMessage());
        }
    }

    private void displayBossStatus(Boss boss) {
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                  --- " + boss.getName() + " Status ---                   ");
            System.out.println("=".repeat(60));
            System.out.println("HP: " + boss.getHealth() + "/" + boss.getMaxHealth());
            System.out.println("Mana: " + boss.getMana() + "/100");
            System.out.println("-".repeat(60));
        } catch (Exception e) {
            System.out.println("Error displaying boss status: " + e.getMessage());
        }
    }

    private void rewardPlayerWithPotionsAndGold() {
        try {
            Random rand = new Random();
            int healthPotionAmount = rand.nextInt(2) + 1;
            int manaPotionAmount = rand.nextInt(2) + 1;    
            int goldAmount = rand.nextInt(50) + 10;         
            inventory.addHealthPotion(healthPotionAmount);  
            inventory.addManaPotion(manaPotionAmount);      
            inventory.addGold(goldAmount);                   
            System.out.println("You received " + healthPotionAmount + " Health Potions, " + manaPotionAmount + " Mana Potions, and " + goldAmount + " gold!");
        } catch (Exception e) {
            System.out.println("Error rewarding player: " + e.getMessage());
        }
    }

    private void returnToMainMenu() {
        System.out.println("Returning to the main menu...");
    }
}
