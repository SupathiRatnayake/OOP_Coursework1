import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    @Override
    public String deleteVehicle(String plateNo) {
        for (Vehicle v: vehicles){
            if (v.getPlateNo().equals(plateNo)){
                vehicles.remove(v);
                return v.toString();
            }
        }
        return "The vehicle you are looking to delete does not exist.";
    }

    @Override
    public void printList() {
        for (Vehicle v: vehicles){
            System.out.println(v);
        }
    }

    @Override
    public void saveStockist() throws IOException {
        System.out.println("save");
        FileWriter fWriter = new FileWriter("vehicles.txt");
        String record = "";
        for (Vehicle v: vehicles){
            String vType = v.getClass().getName();
            record += vType + ";";
            record += v.getPlateNo() + ";";
            record += v.getMake() + ";";
            record += v.getEngine() + ";";
            if (vType.equals("Car")){
                Car c = (Car) v;
                record += c.getDoors() + ";";
                record += c.getSeats() + ";";
            }else {
                Bike b = (Bike) v;
                record += b.getBikeType() + ";";
                record += b.isSidecar() + ";";
            }
            record += "\n";
        }

        fWriter.write(record);
        fWriter.close();
    }

}
