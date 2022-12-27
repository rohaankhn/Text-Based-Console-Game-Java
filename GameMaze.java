import java.util.ArrayList;
import java.util.Scanner;

public class GameMaze {
    private Location[][] maze;
    private Player player;
    private Player enemy1, enemy2;
    private boolean gameFinished = false;

    public GameMaze() {
        maze = new Location[5][5];


        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(maze[i][j] == null) {
                    maze[i][j] = new Location("Corridor");
                }
            }
        }

        maze[0][0] = new Location("Entrance");


        maze[4][0].setLocationName("Locker Room");
        maze[4][0].addEquipment(new Equipment("Pistol"));
        maze[4][0].addEquipment(new Equipment("Silencer"));
        maze[4][0].addEquipment(new Equipment("Money"));
        maze[4][0].addRequiredEquipments(new Equipment("Key Card"));

        maze[2][2].setLocationName("Store Room");
        maze[2][2].addEquipment(new Equipment("Pick lock"));
        maze[2][2].addEquipment(new Equipment("Grapple Hook"));
        maze[2][2].addRequiredEquipments(new Equipment("Flashlight"));

        maze[3][0].setLocationName("Laser Corridor");
        maze[3][0].addRequiredEquipments(new Equipment("Hack Tool"));
        maze[4][1].setLocationName("Laser Corridor");
        maze[4][1].addRequiredEquipments(new Equipment("Hack Tool"));

        maze[1][4].setLocationName("CEO office");
        maze[1][4].addEquipment(new Equipment("Key Card"));
        maze[1][4].addRequiredEquipments(new Equipment("Pick lock"));

        maze[0][3].setLocationName("Guards' Quarter");
        maze[0][3].addEquipment(new Equipment("Flashlight"));
        maze[0][3].addEquipment(new Equipment("Ammo"));

        maze[1][1].setLocationName("IT Office");
        maze[1][1].addEquipment(new Equipment("Hack Tool"));
        maze[1][1].addRequiredEquipments(new Equipment("Pick lock"));

        enemy1 = new Player();
        enemy1.setPlayerName("Security Guard");
        enemy1.setPlayerRow(4);
        enemy1.setPlayerCol(3);

        enemy2 = new Player();
        enemy2.setPlayerName("Security Guard");
        enemy2.setPlayerRow(3);
        enemy2.setPlayerCol(4);

        maze[3][4].setLocationName("Corridor with Guard");
        maze[3][4].addRequiredEquipments(new Equipment("Pistol"));
        maze[3][4].addRequiredEquipments(new Equipment("Silencer"));
        maze[3][4].addRequiredEquipments(new Equipment("Ammo"));

        maze[4][3].setLocationName("Corridor with Guard");
        maze[4][3].addRequiredEquipments(new Equipment("Pistol"));
        maze[4][3].addRequiredEquipments(new Equipment("Silencer"));
        maze[4][3].addRequiredEquipments(new Equipment("Ammo"));

        maze[4][4] = new Location("Exit");
        maze[4][4].addRequiredEquipments(new Equipment("Money"));
        maze[4][4].addRequiredEquipments(new Equipment("Grapple Hook"));

        player = new Player();
        player.setPlayerRow(0);
        player.setPlayerCol(0);
    }

    public void showCurrentLocation() {
        System.out.println("You are in " + maze[player.getPlayerRow()][player.getPlayerCol()].getLocationName());
    }

    public void showCurrentLocationEquipment() {
        if(maze[player.getPlayerRow()][player.getPlayerCol()].getEquipments().size() > 0) {
            System.out.println("You scan the location...you see following equipments/things:");
            for(Equipment equipment : maze[player.getPlayerRow()][player.getPlayerCol()].getEquipments()) {
                System.out.println(equipment.getEquipmentName());
            }
        } else {
            System.out.println("There is no equipment in this location.");
        }
    }


    public int getcurrentCol() {
        return player.getPlayerCol();
    }

    public int getcurrentRow() {
        return player.getPlayerRow();
    }
    public boolean isValidMove(int row, int col) {
        if(row < 0 || row > 4 || col < 0 || col > 4) {
            return false;
        } else {
            if(maze[row][col].getLocationName().equals("Laser Corridor") || maze[row][col].getLocationName().equals("Corridor with Guard"))
                return true;
            for(Equipment equipment : maze[row][col].getRequiredEquipments()) {
                if(!player.searchEquipment(equipment.getEquipmentName())) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean movePlayer(int row, int col) {
        if (isValidMove(row, col)) {
            player.setPlayerRow(row);
            player.setPlayerCol(col);
        }
        if(maze[player.getPlayerRow()][player.getPlayerCol()].getLocationName().equals("Corridor with Guard")) {
            System.out.print("Want to fight the guard? You need a pistol, silencer and ammo (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("yes")) {
                if(player.searchEquipment("Pistol") && player.searchEquipment("Silencer") && player.searchEquipment("Ammo")) {
                    System.out.println("You have defeated the guard");
                    maze[player.getPlayerRow()][player.getPlayerCol()].setLocationName("Room with dead guard");
                } else {
                    System.out.println("You have been defeated by the guard! Game Over!");
                    gameFinished = true;
                }
                return true;
            } else if(input.equalsIgnoreCase("no")) {
                System.out.println("You have returned to the previous room");
                return false;
            }
        } else if(maze[player.getPlayerRow()][player.getPlayerCol()].getLocationName().equals("Laser Corridor")) {
            System.out.print("Want to enter the Laser Corridor? You need a hack tool to disable the laser (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("yes")) {
                if(player.searchEquipment("Hack Tool")) {
                    System.out.println("You have entered the Laser Corridor");
                    maze[player.getPlayerRow()][player.getPlayerCol()].setLocationName("Laser Corridor");
                } else {
                    System.out.println("You have been caught by the laser! Game Over!");
                    gameFinished = true;
                }
                return true;
            } else if(input.equalsIgnoreCase("no")) {
                System.out.println("You have returned to the previous room");
                return false;
            }

        }
        return true;
    }

    public boolean pickEquipment(int index) {
        if (index >= 0 && index < maze[player.getPlayerRow()][player.getPlayerCol()].getEquipments().size()) {
            player.addEquipment(maze[player.getPlayerRow()][player.getPlayerCol()].getEquipments().get(index));
            maze[player.getPlayerRow()][player.getPlayerCol()].removeEquipment(maze[player.getPlayerRow()][player.getPlayerCol()].getEquipments().get(index));
            return true;
        }
        return false;
    }

    public boolean dropEquipment(int index) {
        if(index >= 0 && index < player.getEquipments().size()) {
            maze[player.getPlayerRow()][player.getPlayerCol()].addEquipment(player.getEquipments().get(index));
            player.removeEquipment(player.getEquipments().get(index));
            return true;
        }
        return false;
    }

    public void showPlayerEquipment() {
        if(player.getEquipments().size() == 0) {
            System.out.println("You have no equipment");
        } else {
            System.out.println("You currently have following equipments:");
            for(int i = 0; i < player.getEquipments().size(); i++) {
                System.out.println(i + 1 + ". " + player.getEquipments().get(i).getEquipmentName());
            }
        }

    }

    public Equipment getLastEquipmentOfPlayer() {
        return player.getEquipments().get(player.getEquipments().size() - 1);
    }

    public void startGame() {
        System.out.println("Welcome to the game!");
        System.out.println("In this game, you are a thief who is trying to steal money from a company.");
        System.out.println("You have to find the money and escape the building.");
        System.out.println("You can move around the building by typing 'w' for up, 's' for down, 'a' for left, and 'd' for right.");
        System.out.println("You can pick up equipment and drop equipment.");
        System.out.println("You can check your equipment.");
        System.out.println("You can check the equipment in your current location.");

    }

    public void showMenu () {

        System.out.println("What do you want to do?");
        System.out.println("1. Move");
        System.out.println("2. Pick up equipment");
        System.out.println("3. Drop equipment");
        System.out.println("4. View your equipment");
        System.out.println("5. Scan the location for equipments and thing to be picked up");
        System.out.println("6. Reset");
        System.out.println("7. Exit");
    }

    public void showRequiredEquipment(int row, int col) {
        System.out.println("You need following equipments to enter this room:");
        for(int i = 0; i < maze[row][col].getRequiredEquipments().size(); i++) {
            System.out.println(i + 1 + ". " + maze[row][col].getRequiredEquipments().get(i).getEquipmentName());
        }
    }

    public boolean isGameOver() {
        if(gameFinished) {
            if(player.getPlayerRow() == 4 && player.getPlayerCol() == 4) {
                System.out.println("You have escaped the building! You win!");
            } else {
                System.out.println("You have lost the game!");
            }
            return true;
        }
        return false;
    }

    public void showAdjacentLocations() {
        System.out.println("You can move to the following locations:");
        if( player.getPlayerRow() - 1 >= 0 ) {
            System.out.println("Up -> " + maze[player.getPlayerRow() - 1][player.getPlayerCol()].getLocationName());
        }
        if(player.getPlayerRow() + 1 < 5) {
            System.out.println("Down -> " + maze[player.getPlayerRow() + 1][player.getPlayerCol()].getLocationName());
        }
        if( player.getPlayerCol() - 1 >= 0) {
            System.out.println("Left -> " + maze[player.getPlayerRow()][player.getPlayerCol() - 1].getLocationName());
        }
        if(player.getPlayerCol() + 1 < 5) {
            System.out.println("Right -> " + maze[player.getPlayerRow()][player.getPlayerCol() + 1].getLocationName());
        }
    }
}
