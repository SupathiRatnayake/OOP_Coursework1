import java.io.IOException;
import java.util.Scanner;

public class App {

    // set rvm as new WestminsterRentalVehicleManager
    private static WestminsterRentalVehicleManager rvm = new WestminsterRentalVehicleManager();

    private static Scanner in = new Scanner(System.in); // Scanner object


    public static void main(String[] args) {

        // test file read
        init();

        // for testing purposes test()
        test();

        int option;
        do{
            System.out.printf("\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",
                    "Westminster Rental Vehicle Manager",
                    "1) Add new vehicle", "2) Delete a vehicle", "3) Print List", "4) Save", "5) Exit");

            System.out.print("Select Option: ");
            option = in.nextInt();
            in.nextLine();

            switch (option){
                case 1:
                    // prompt for data and add vehicle to list
                    addNewVehicle();
                    break;

                case 2:
                    deleteVehicle();
                    break;


                case 3:
                    printListOfVehicles();
                    break;


                case 4:
                    saveToFile();
                    break;

                case 5:
                    System.out.println("Program shutting down...");
                    break;

                default:
                    // error message for any other value for option
                    System.out.println("Please select a proper option!");
                    break;

            }

        }while (option != 5);

    }


    private static void addNewVehicle() {
        // method body here
        Vehicle v;
        String plateNo, make;
        int engine;
        plateNo = getStr("Plate number: ");
        make = getStr("Make of vehicle: ");
        engine = getInt("Engine capacity: ");

        // car or bike?
        int type = getInt("Select vehicle: 1) Car 2) Bike", 1, 2);
        if (type == 1){
            int doors = getInt("No. of doors: ", 2, 5);
            int seats = getInt("No. of seats: ", 2, 8);
            v = new Car(
                    plateNo,
                    make,
                    engine,
                    doors,
                    seats
                    );
        }else {
            String bType = getStr("Bike type: ");
            boolean sidecar = false;
            int n = getInt("Has sidecar? No(0), Yes(1): ",0, 1);
            if (n != 0){
                sidecar = true;
            }

            v = new Bike(
                    plateNo,
                    make,
                    engine,
                    bType,
                    sidecar
            );
        }

        rvm.addVehicle(v);
        System.out.println("Successfully added 1 " + v.getClass().getName());
        promptToMainMenu();
    }

    private static void deleteVehicle() {
        // method body here
        System.out.println("Delete a vehicle");
        System.out.print("Enter plate number: ");
        String plateNo = in.nextLine();
        String message = rvm.deleteVehicle(plateNo);
        System.out.println(message);
        promptToMainMenu();
    }

    private static void printListOfVehicles() {
        // method body here
        rvm.printList();
        System.out.println("Press enter to visit main menu");
        promptToMainMenu();
    }

    private static void saveToFile(){
        // method body here
        System.out.println("under construction but try");
        try {
            rvm.saveStockist();
            System.out.println("Records were successfully written to file.");
        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
        System.out.println("Press enter to visit main menu");
        promptToMainMenu();
    }

    private static int getInt(String hint){
        int num;
        System.out.print(hint);
        while (!in.hasNextInt()){
            System.out.println("Please enter integer only!");
            System.out.print(hint);
            in.next();
        }
        num = in.nextInt();
        in.nextLine();
        return num;
    }

    private static int getInt(String hint, int min, int max){
        int num;
        num = getInt(hint);
        while (num < min || num > max){
            System.out.println("Number must be between " + min + " and " + max);
            num = getInt(hint);
        }
        return num;
    }

    private static String getStr(String hint){
        System.out.print(hint);
        return in.nextLine();
    }

    static void test(){
        Vehicle v1 = new Car("aaa yyyy", "c1", 800, 5, 4);
        Vehicle v2 = new Car("bbb yyyy", "c2", 3500, 5, 8);
        Vehicle v3 = new Bike("ccc yyyy", "b1", 300, "cruiser", true);
        Vehicle v4 = new Bike("ddd yyyy", "b1", 100, "scooter", false);

        Vehicle[] testList = {v1, v2, v3, v4};
        for (Vehicle v: testList){
            rvm.addVehicle(v);
        }
    }

    static void init(){
        try {
            rvm.readList();
        } catch (IOException e) {
            System.out.println("Oops!");
            e.printStackTrace();
        }
    }

    private static void promptToMainMenu(){
        System.out.println("press any key to visit Main Menu.");
        in.nextLine();
    }

}
