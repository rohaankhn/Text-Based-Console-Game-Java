import java.util.ArrayList;
import java.util.Map;

public class Player {
    private String playerName;
    private int playerRow;
    private int playerCol;
    private int playerHealth;
    private ArrayList<Equipment> equipments;

    public Player() {
        playerName = "Player";
        playerRow = 2;
        playerCol = 2;
        equipments = new ArrayList<>();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        equipments.remove(equipment);
    }

    public boolean searchEquipment(String equipmentName) {
        for(Equipment equipment : equipments) {
            if(equipment.getEquipmentName().equalsIgnoreCase(equipmentName)) {
                return true;
            }
        }
        return false;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }
}
