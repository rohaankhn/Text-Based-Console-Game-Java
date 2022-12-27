import java.util.ArrayList;

public class Location {
    private String locationName;
    private ArrayList<Equipment> equipments;
    private ArrayList<Equipment> requiredEquipments;

    public Location(String locationName) {
        this.locationName = locationName;
        this.requiredEquipments = new ArrayList<>();
        equipments = new ArrayList<>();
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public Equipment removeEquipment(Equipment equipment) {
        equipments.remove(equipment);
        return equipment;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public ArrayList<Equipment> getRequiredEquipments() {
        return requiredEquipments;
    }
    public void addRequiredEquipments(Equipment equipment) {
        requiredEquipments.add(equipment);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean searchEquipment(String equipmentName) {
        for(Equipment equipment : equipments) {
            if(equipment.getEquipmentName().equals(equipmentName)) {
                return true;
            }
        }
        return false;
    }


}


