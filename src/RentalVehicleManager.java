import java.io.IOException;

public interface RentalVehicleManager {

    public String addVehicle(Vehicle v);          // add vehicle to list
    public String deleteVehicle(String plateNo);  // delete vehicle from list
    public void printList();                    // print list of vehicles
    public void saveStockist() throws IOException;                 // save list of vehicles to file

}
