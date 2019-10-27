import java.util.ArrayList;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    @Override
    public void deleteVehicle(String plateNo) {
        for (Vehicle v: vehicles){
            if (v.getPlateNo().equals(plateNo)){
                vehicles.remove(v);
            }
        }
    }

    @Override
    public void printList() {
        for (Vehicle v: vehicles){
            System.out.println(v);
        }
    }

    @Override
    public void saveStockist() {
        System.out.println("save");
    }
}
