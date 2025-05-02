
import java.util.Random;
import java.util.Scanner;

public class GameNarration {
    private final Narration narration;
    private final Scanner scanner;
    private final Inventory inventory;
    private final Shop shop;

    public GameNarration(Narration narration, Inventory inventory, Shop shop) {
        this.narration = narration;
        this.scanner = narration.getScanner();
        this.inventory = inventory;
        this.shop = shop;
    }

    public void runNarration(Character player, Battle battle) {
        showPrologue();

        showStageNarration(1, "\nStage 1: Natagumo Mountain\n" +
                "It is a night shrouded in mist. The air smells of pine and decay, and the forest is eerily silent. " +
                "You find yourself standing at the base of Natagumo Mountain, a place known for its eerie legends. " +
                "The sound of skittering legs fills the air as you make your way forward, cautiously stepping into the unknown.\n" +
                "A terrifying figure emerges from the shadows: Rui, one of the Twelve Kizuki. He stands tall, his face twisted in a cruel smile. " +
                "Around him, a horde of demon spiders creep from the darkness, ready to tear apart anyone who dares challenge them.\n" +
                "--- Dialogue: Tanjiro: 'I will save my sister, no matter the cost!' ---", player, battle);

        showStageNarration(2, "\nStage 2: Mugen Train\n" +
                "The Mugen Train is a symbol of strength and hope, racing through the night as it transports passengers to their destinations. " +
                "However, darkness has seeped aboard, with the demon Enmu lurking within the train. A strange, oppressive silence fills the air. " +
                "The passengers are unaware, their lives hanging by a thread.\n" +
                "Suddenly, you feel it — a cold presence, a strange pull. The world around you begins to twist, shifting into a nightmarish realm. " +
                "The train is no longer a vessel, but a prison.\n" +
                "--- Dialogue: Tanjiro: 'I must protect the passengers. Enmu, you won't get away with this!' ---", player, battle);

        showStageNarration(3, "\nStage 3: Entertainment District\n" +
                "The lights of the Entertainment District flicker in the distance. Beneath its bright, cheerful facade lies a world of sin and corruption. " +
                "The district is ruled by demons, and you are caught in the middle of a battle that threatens to engulf everyone.\n" +
                "You encounter Daki, a demon of immense power, and her brother Gyutaro. The two demons are vicious, their strength formidable. " +
                "As they tear through the district, innocent lives are lost. Tanjiro’s heart aches as he watches the destruction.\n" +
                "--- Dialogue: Tanjiro: 'These demons must be stopped. For the sake of the people here, for the sake of my family!' ---", player, battle);

        showStageNarration(4, "\nStage 4: Swordsmith Village\n" +
                "The Swordsmith Village is a place of peace and beauty, a sanctuary for the swordsmiths who create the blades that the Demon Slayer Corps uses to fight against the demons. " +
                "But peace is fleeting, and soon the village is attacked by Upper Moon demons.\n" +
                "Hantengu, the demon who thrives on despair, descends upon the village. The air is thick with fear as his power spreads, turning the once tranquil village into a battlefield.\n" +
                "Tanjiro, exhausted from the previous battles, stands tall. His thoughts turn to the swordsmiths who gave him his blade. " +
                "'I will protect them,' he vows.", player, battle);

        showStageNarration(5, "\nStage 5: Infinity Castle\n" +
                "You step into the darkness of Infinity Castle, a place where the air is thick with malevolent energy. This is the final stage of your journey, the place where Muzan Kibutsuji, the Demon King, awaits.\n" +
                "His presence is oppressive, suffocating. The castle is a labyrinth of twisted corridors and endless rooms, each filled with death and despair. " +
                "But you push forward, guided by the faces of those you’ve lost and the promise you made to them.\n" +
                "Finally, you face Muzan Kibutsuji, the demon who caused the destruction of your family, your village, your life. He is a terrifying being, his form ever-changing, his power beyond comprehension.\n" +
                "--- Dialogue: Tanjiro: 'This is the end, Muzan. I will defeat you, for my family, for my sister, for everyone you've destroyed.' ---", player, battle);

        System.out.println("\nEpilogue: A New Dawn\n" +
                "The sun rises over the land, casting a golden light on the world that has been saved. The demons are gone, but the scars of the past remain. " +
                "The Demon Slayer Corps has disbanded, and the survivors are left to rebuild.\n" +
                "You stand at the edge of a new beginning. The journey has ended, but your story has just begun. The memories of your fallen comrades, of the battles fought, will live on in your heart.\n" +
                "The world is free of demons, but the fight to protect humanity continues. The legacy of the Demon Slayer Corps will never be forgotten.\n" +
                "The End... Or is it?");
    }

    private void showPrologue() {
        narration.showNarration("\nPrologue: A Call to Arms\n" +
                "The world has been engulfed by darkness. Beneath the beauty of nature, demons lurk, preying on the innocent. Their cursed blood flows through the land, and the Demon Slayer Corps is the last hope for humanity. " +
                "The protagonist, a young swordsman, lost everything to these monsters. His family slaughtered, his sister turned into a demon. " +
                "But despite the loss, he holds onto hope — hope that he can defeat the demons and restore peace.\n" +
                "This is your journey. A journey of bloodshed, sacrifice, and strength.\n");
        waitForPlayerInput();
    }

    private void showStageNarration(int stage, String text, Character player, Battle battle) {
        System.out.println("\n=== Proceed to Stage " + stage + " ===");
        waitForPlayerInput();
        narration.showNarration(text);
        waitForPlayerInput();
        engageInBattleBasedOnStage(stage, player, battle);
    }

    private void engageInBattleBasedOnStage(int stage, Character player, Battle battle) {
        try {
            engageRandomDemons(player, battle); 
            switch (stage) {
                case 1 -> engageBattle(player, new Rui(), battle);
                case 2 -> {
                    engageBattle(player, new Enmu(), battle);
                    engageBattle(player, new Akaza(), battle);
                }
                case 3 -> {
                    engageBattle(player, new Daki(), battle);
                    engageBattle(player, new Gyutaro(), battle);
                }
                case 4 -> engageBattle(player, new Hantengu(), battle);
                case 5 -> engageBattle(player, new MuzanKibutsuji(), battle);
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the battle: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

        private void engageBattle(Character player, Boss boss, Battle battle) {
            if (battle.engage(player, boss)) {  
                waitForBossDefeatMessage(boss);  
                mainMenuDisplay(player);
            } else {  
                System.out.println(player.getName() + " has been defeated. Returning to the main menu...\n");
                mainMenuDisplay(player); 
                return;
            }
        }

    private void mainMenuDisplay(Character player) {
        while (true) {
            System.out.println("\n====What would you like to do ?");
            System.out.println("1. View Inventory");
            System.out.println("2. Check Status");
            System.out.println("3. Use Item");
            System.out.println("4. Visit Shop");
            System.out.println("5. Visit Resting Place to rest");
            System.out.println("6. Continue to next stage");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> inventory.showInventory(scanner, player);  
                case 2 -> player.displayStatus();
                case 3 -> useItemMenu(player);
                case 4 -> shop.showShop(inventory, scanner);  
                case 5 -> visitRestingPlace(player);
                case 6 -> {
                    System.out.println("Proceeding to next stage...\n");
                    return;  
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void useItemMenu(Character player) {
        while (true) {
            System.out.println("\n=== Use Item Menu ===");
            System.out.println("1. Health Potion (x" + inventory.getHealthPotions() + ")");
            System.out.println("2. Mana Potion (x" + inventory.getManaPotions() + ")");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    if (inventory.getHealthPotions() > 0) {
                        inventory.useHealthPotion(player);
                    } else {
                        System.out.println("You don't have any health potions.");
                    }
                }
                case 2 -> {
                    if (inventory.getManaPotions() > 0) {
                        inventory.useManaPotion(player);
                    } else {
                        System.out.println("You don't have any mana potions.");
                    }
                }
                case 3 -> {
                    return;  
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void waitForPlayerInput() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    private void waitForBossDefeatMessage(Boss boss) {
        System.out.println("\n" + boss.getName() + " has been defeated!\n");
        waitForPlayerInput();
    }
    private void visitRestingPlace(Character player) {
        System.out.println("\nVisiting resting place...");
        player.restoreHealthAndMana();  
        System.out.println("\nReturning to the main menu...");
    }


    private void engageRandomDemons(Character player, Battle battle) {
    Random random = new Random();
    int demonCount = random.nextInt(3) + 1;  

    for (int i = 1; i <= demonCount; i++) {
        System.out.println("\nA random demon appears!");
        Demon demon = new Demon("Demon Level " + (i % 3 + 1), i % 3 + 1);
        if (battle.engage(player, demon)) {
            demon.dropLoot(inventory);
        } else {
            System.out.println("You were defeated by the demon...");
            break;
        }
    }
}


}



