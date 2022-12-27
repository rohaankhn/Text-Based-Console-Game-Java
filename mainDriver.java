import java.util.Scanner;

public class mainDriver {
    public static void main(String[] args) {
        GameMaze gameMaze = new GameMaze();

        gameMaze.startGame();

        while(true) {
            System.out.println();
            gameMaze.showCurrentLocation();
            gameMaze.showMenu();
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Enter 'w' to move up, 's' to move down, 'a' to move left, 'd' to move right");
                    gameMaze.showAdjacentLocations();
                    System.out.print("Enter your choice: ");
                    String move = scanner.nextLine();
                    switch (move) {
                        case "w":
                            if(gameMaze.isValidMove(gameMaze.getcurrentRow() - 1, gameMaze.getcurrentCol())) {
                                if(!gameMaze.movePlayer(gameMaze.getcurrentRow() - 1, gameMaze.getcurrentCol()))
                                    gameMaze.movePlayer(gameMaze.getcurrentRow() + 1, gameMaze.getcurrentCol());
                            } else {
                                System.out.println("\nYou cannot move there!");
                                gameMaze.showRequiredEquipment(gameMaze.getcurrentRow() - 1, gameMaze.getcurrentCol());
                            }
                            break;
                        case "s":
                            if(gameMaze.isValidMove(gameMaze.getcurrentRow() + 1, gameMaze.getcurrentCol())) {
                                if(!gameMaze.movePlayer(gameMaze.getcurrentRow() + 1, gameMaze.getcurrentCol()))
                                    gameMaze.movePlayer(gameMaze.getcurrentRow() - 1, gameMaze.getcurrentCol());
                            } else {
                                System.out.println("\nYou cannot move there!");
                                gameMaze.showRequiredEquipment(gameMaze.getcurrentRow() + 1, gameMaze.getcurrentCol());
                            }
                            break;
                        case "a":
                            if(gameMaze.isValidMove(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() - 1)) {
                                if(!gameMaze.movePlayer(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() - 1))
                                    gameMaze.movePlayer(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() + 1);
                            } else {
                                System.out.println("\nYou cannot move there!");
                                gameMaze.showRequiredEquipment(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() - 1);
                            }
                            break;
                        case "d":
                            if(gameMaze.isValidMove(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() + 1)) {
                                if(!gameMaze.movePlayer(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() + 1))
                                    gameMaze.movePlayer(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() - 1);
                            } else {
                                System.out.println("\nYou cannot move there!");
                                gameMaze.showRequiredEquipment(gameMaze.getcurrentRow(), gameMaze.getcurrentCol() + 1);
                            }
                            break;
                        default:
                            System.out.println("Invalid input!");
                            break;
                    }
                    if(gameMaze.isGameOver()) {
                        return;
                    }
                    break;
                case "2":   // Showing the equipments or items available to be picked up and asks user to pick any one of those up
                    gameMaze.showCurrentLocationEquipment();
                    System.out.println("Enter the index of the equipment or thing you want to pick up");
                    int index = scanner.nextInt();
                    if (gameMaze.pickEquipment(index - 1)) {
                        System.out.println("You have picked up the " + gameMaze.getLastEquipmentOfPlayer().getEquipmentName());
                    }
                    else
                        System.out.println("Invalid input!");
                    break;
                case "3":   // Shows the equipments or items that the player has picked up and asks user to drop any one of those
                    gameMaze.showPlayerEquipment();
                    System.out.println("Enter the index of the equipment you want to drop");
                    int index2 = scanner.nextInt();
                    if (gameMaze.dropEquipment(index2 - 1))
                        System.out.println("You have dropped the equipment!");
                    else
                        System.out.println("Invalid input!");
                    break;
                case "4":   // Shows the equipments or items that the player has picked up so far
                    gameMaze.showPlayerEquipment();
                    break;
                case "5":   // Shows the equipments or items that the player has picked up so far
                    gameMaze.showCurrentLocationEquipment();
                    break;
                case "6":
                    System.out.print("You are about to reset the maze! Are you sure? (y/n): ");
                    String reset = scanner.nextLine();
                    if (reset.equalsIgnoreCase("y")) {
                        gameMaze = new GameMaze();
                        System.out.println("The maze has been reset!");
                    } else if (reset.equalsIgnoreCase("n")) {
                        System.out.println("The maze has not been reset!");
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;
                case "7":
                    System.out.print("You are about to quit the game! Are you sure? (y/n): ");
                    String quit = scanner.nextLine();
                    if (quit.equalsIgnoreCase("y")) {
                        System.out.println("You have quit the game!");
                        System.exit(0);
                    } else if (quit.equalsIgnoreCase("n")) {
                        System.out.println("You have not quit the game!");
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
}
