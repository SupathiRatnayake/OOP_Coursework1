public interface RentalVehicleManager {

    public void addVehicle(Vehicle v);          // add vehicle to list
    public void deleteVehicle(String plateNo);  // delete vehicle from list
    public void printList();                    // print list of vehicles
    public void saveStockist();                 // save list of vehicles to file

}
