import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    private ArrayList<Vehicle> vehicles = new ArrayList<>(); // initialize empty ArrayList of Vehicle objects
    private ArrayList<Schedule> bookings = new ArrayList<>(); // Initialize ArrayList of booking schedules

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    private final int MAX_PARKING = 50;
    @Override
    public String addVehicle(Vehicle v) {
        if (vehicles.size() < MAX_PARKING){
            vehicles.add(v);
            return "1 " + v.getClass().getName() + " added Successfully. "
                    + (MAX_PARKING - vehicles.size()) + " Parking lots are available";
        }else {
            return "No parking lots available. The vehicle was not entered";
        }
    }

    @Override
    public String deleteVehicle(String plateNo) {
        for (Vehicle v: vehicles){
            if (v.getPlateNo().equals(plateNo)){ // if plate number == given plateNo
                vehicles.remove(v);  // remove object
                return "You removed: " + v.toString() +"\n"+
                        "You have " + (MAX_PARKING - vehicles.size()) + "parking lots.";
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

    /*For each vehicle object in vehicles
    * get details and concatenate to a string
    * and write them in file "vehicles.txt"
    *  */
    @Override
    public void saveStockist() throws IOException {
        FileWriter fWriter = new FileWriter("vehicles.txt"); // open file
        String record = ""; // initialize empty string
        for (Vehicle v: vehicles){
            String vType = v.getClass().getName(); // class name is helpful to identify the class of the object
            record += vType + ";";
            record += v.getPlateNo() + ";"; // plate number
            record += v.getMake() + ";";    // make
            record += v.getEngine() + ";";  // engine capacity
            if (vType.equals("Car")){
                Car c = (Car) v;    // type casting to Car object
                record += c.getDoors() + ";";   // get number of doors
                record += c.getSeats() + ";";   // get number of seats
            }else {
                Bike b = (Bike) v;  // type casting to Bike object
                record += b.getBikeType() + ";";    // get type of the bike
                record += b.isSidecar() + ";";      // have a sidecar?
            }
            record += "\n"; // add new line character to separate data of each object
        }

        fWriter.write(record);  // writing the whole document to the file
        fWriter.close();
    }

    //  Read data from txt file, make objects and add them into ArrayList vehicles
    public void readList() throws IOException {

        File vFile = new File("vehicles.txt");  // initialize file
        Scanner in = new Scanner(vFile);  // use Scanner to read file line by line
        while (in.hasNextLine()){
            String line = in.nextLine(); // read line and assign to line

            String[] t = line.split(";"); // Split line by ";" and assign to String Array t

            Vehicle v;  // declaration of Vehicle v
            switch (t[0]){
                case "Car":
                    v = new Car(
                            t[1], t[2], Integer.parseInt(t[3]), Integer.parseInt(t[4]), Integer.parseInt(t[5])
                    ); // pass data from String Array t to Car constructor
                    vehicles.add(v);
                    break;
                case "Bike":
                    v = new Bike(
                            t[1], t[2], Integer.parseInt(t[3]), t[4], Boolean.parseBoolean(t[5])
                    );  // pass data from String Array t to Bike constructor
                    vehicles.add(v);
                    break;
            }
        }
    }

    public void addSchedule(Schedule s){
        bookings.add(s);
    }

}
