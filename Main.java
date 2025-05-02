import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printTitle();

        try (Scanner scanner = new Scanner(System.in)) {

            Character player = chooseCharacter(scanner);
            if (player == null) {
                throw new IllegalStateException("No character selected.");
            }

            Inventory inventory = new Inventory();
            Battle battle = new Battle(scanner, inventory);
            Narration narration = new Narration(scanner, 20);
            Shop shop = new Shop();
            GameNarration gameNarration = new GameNarration(narration, inventory, shop);

            boolean gameRunning = true;
            while (gameRunning) {
                displayMainMenu();
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            shop.showShop(inventory, scanner);
                            break;
                        case 2:
                            System.out.println("Starting the story...");
                            gameNarration.runNarration(player, battle);
                            break;
                        case 3:
                            inventory.showInventory(scanner, player);
                            break;
                        case 4:
                            gameRunning = false; 
                            System.out.println("Goodbye, brave warrior!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); 
                }
            }

            System.out.println("\nYour final inventory:");
            inventory.showInventory(scanner, player); 

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

     static void printTitle() {
        // ANSI color codes
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";
    
        System.out.println(CYAN + "=".repeat(80) + RESET);
        System.out.println(GREEN +  "███████╗██╗   ██╗██████╗ ██╗   ██╗     ██████╗ ███████╗       " + RESET);
        System.out.println(RED +    "██╔════╝██║   ██║██╔══██╗╚██╗ ██╔╝    ██╔═══██╗██╔════╝        " + RESET);
        System.out.println(YELLOW + "█████╗  ██║   ██║██████╔╝ ╚████╔╝     ██║   ██║█████╗          " + RESET);
        System.out.println(BLUE +   "██╔══╝  ██║   ██║██╔══██╗  ╚██╔╝      ██║   ██║██╔══╝           " + RESET);
        System.out.println(PURPLE + "██║     ╚██████╔╝██║  ██║   ██║       ╚██████╔╝██║              " + RESET);
        System.out.println(CYAN +   "╚═╝      ╚═════╝ ╚═╝  ╚═╝   ╚═╝        ╚═════╝ ╚═╝               " + RESET);
    
        System.out.println(YELLOW + "████████╗██╗  ██╗███████╗    ██████╗ ███████╗███╗   ███╗ ██████╗ ███╗   ██╗" + RESET);
        System.out.println(GREEN +  "╚══██╔══╝██║  ██║██╔════╝    ██╔══██╗██╔════╝████╗ ████║██╔═══██╗████╗  ██║" + RESET);
        System.out.println(RED +    "   ██║   ███████║█████╗      ██║  ██║█████╗  ██╔████╔██║██║   ██║██╔██╗ ██║" + RESET);
        System.out.println(BLUE +   "   ██║   ██╔══██║██╔══╝      ██║  ██║██╔══╝  ██║╚██╔╝██║██║   ██║██║╚██╗██║" + RESET);
        System.out.println(PURPLE + "   ██║   ██║  ██║███████╗    ██████╔╝███████╗██║ ╚═╝ ██║╚██████╔╝██║ ╚████║" + RESET);
        System.out.println(CYAN +   "   ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═════╝ ╚══════╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝" + RESET);
        
        System.out.println(GREEN +  "███████╗██╗      █████╗ ██╗   ██╗███████╗██████╗ ███████╗                 ");
        System.out.println(RED +    "██╔════╝██║     ██╔══██╗╚██╗ ██╔╝██╔════╝██╔══██╗██╔════╝                  ");
        System.out.println(YELLOW + "███████╗██║     ███████║ ╚████╔╝ █████╗  ██████╔╝███████╗                  ");
        System.out.println(BLUE +   "╚════██║██║     ██╔══██║  ╚██╔╝  ██╔══╝  ██╔══██╗╚════██║                  "); 
        System.out.println(PURPLE + "███████║███████╗██║  ██║   ██║   ███████╗██║  ██║███████║                  "); 
        System.out.println(CYAN +   "╚══════╝╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝                  ");  
       
        System.out.println(CYAN + "=".repeat(80) + RESET);
        System.out.println(YELLOW + "                           Prepare for an Epic Adventure!" + RESET);
        System.out.println(CYAN + "=".repeat(80) + RESET);
        System.out.println(PURPLE + "Created By:" + RESET);
        System.out.println(PURPLE + "\t\tSerge Ylan M. Soldano\n\tRenz B. Chavez\t\tJosan Sumarago\n\tJerson Sendo\t\tEdzel Señor" + RESET);
        System.out.println(CYAN + "=".repeat(80) + RESET);
    }
    
    

     static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(78));
        System.out.println("                         WELCOME TO THE MAIN MENU                          ");
        System.out.println("=" .repeat(78));
        System.out.println("1. Visit Shop");
        System.out.println("2. Start Story");
        System.out.println("3. View Inventory");
        System.out.println("4. Exit Game");
        System.out.print("Choose an option: ");
    }

    private static Character chooseCharacter(Scanner scanner) {
        System.out.println("\n" + "=".repeat(78));
        System.out.println("                        Choose Your Character: ");
        System.out.println("=" .repeat(78));
        while (true) {
            System.out.println("1. Tanjiro Kamado - A balanced warrior with Water Breathing skills.");
            System.out.println("2. Zenitsu Agatsuma - A swift fighter wielding Thunder Breathing.");
            System.out.println("3. Inosuke Hashibira - A fierce brawler using Beast Breathing.");
            System.out.print("Enter your choice (1/2/3): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: 
                        System.out.println("You chose Tanjiro Kamado - A balanced warrior with Water Breathing skills.");
                        return new Tanjiro(); 
                    case 2: 
                        System.out.println("You chose Zenitsu Agatsuma - A swift fighter wielding Thunder Breathing.");
                        return new Zenitsu();  
                    case 3: 
                        System.out.println("You chose Inosuke Hashibira - A fierce brawler using Beast Breathing.");
                        return new Inosuke();  
                    default: 
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1/2/3).");
                scanner.nextLine(); 
            }
        }
    }
}
