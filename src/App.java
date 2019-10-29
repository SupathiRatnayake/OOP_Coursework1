import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends Application implements EventHandler<ActionEvent> {

    // set rvm as new WestminsterRentalVehicleManager
    private static WestminsterRentalVehicleManager rvm = new WestminsterRentalVehicleManager();

    private static Scanner in = new Scanner(System.in); // Scanner object

    // GUI Objects
    // text field
    TextField txtName;
    // search button
    Button btnSearch;
    // search button
    Button btnReset;
    //Tile pane
    TilePane rt;
    //Radio btn
    RadioButton r1,r2;
    // Toggle grp
    ToggleGroup tg;

    // GUI Table
    TableView<Vehicle> table;
    // END GUI Object list


    public static void main(String[] args) {

        // test file read
        initData();

        // for testing purposes test()
        test();

        launch(args);

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

        String msg = rvm.addVehicle(v);
        System.out.println(msg);
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
        rvm.printList(); // call printList method of rvm
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

    static void initData(){
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

    // JAVA FX
    @Override
    public void start(Stage primaryStage) {
        Stage stage;
        Scene scene;


        ObservableList<Vehicle> observableVehicles = FXCollections.observableArrayList(rvm.getVehicles());

        stage = primaryStage;
        stage.setTitle("Vehicles");

        // id col
//        TableColumn<Vehicle, Integer> idColumn = new TableColumn<>("ID");
//        idColumn.setMinWidth(20);
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));

        // title col
        TableColumn<Vehicle, String> pNo = new TableColumn<>("Plate Number");
        pNo.setMinWidth(100);
        pNo.setCellValueFactory(new PropertyValueFactory<>("plateNo"));

        // genre col
        TableColumn<Vehicle, String> make = new TableColumn<>("Make");
        make.setMinWidth(100);
        make.setCellValueFactory(new PropertyValueFactory<>("make"));

        // date col
        TableColumn<Vehicle, Integer> engine = new TableColumn<>("Engine (cc)");
        engine.setMinWidth(100);
        engine.setCellValueFactory(new PropertyValueFactory<>("engine"));

        // artist col
        TableColumn<Vehicle, String> vehicle = new TableColumn<>("Vehicle");
        vehicle.setMinWidth(100);
        vehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));

        // doors
        TableColumn<Vehicle, Integer> doors = new TableColumn<>("Doors");
        doors.setMinWidth(50);
        doors.setCellValueFactory(new PropertyValueFactory<>("doors"));

        // seats
        TableColumn<Vehicle, Integer> seats = new TableColumn<>("Seats");
        seats.setMinWidth(50);
        seats.setCellValueFactory(new PropertyValueFactory<>("seats"));

        // type col
        TableColumn<Vehicle, Integer> btype = new TableColumn<>("Bike Style");
        btype.setMinWidth(20);
        btype.setCellValueFactory(new PropertyValueFactory<>("bikeType"));

        // type col
        TableColumn<Vehicle, String> sidecar = new TableColumn<>("Sidecar");
        sidecar.setMinWidth(20);
        sidecar.setCellValueFactory(new PropertyValueFactory<>("sidecar"));

        table = new TableView<>();
        table.setItems(observableVehicles);
        table.getColumns().addAll(pNo, make, engine, vehicle, seats, btype, sidecar);

        // label
        Label lbl1 = new Label();
        lbl1.setText("Album: ");

        // input
        txtName = new TextField();
        txtName.setPromptText("Enter Album Title");

        // Search Button
        btnSearch = new Button();
        btnSearch.setText("Search");
        btnSearch.setOnAction(this);

        // reset Button
        btnReset = new Button();
        btnReset.setText("Reset");
        btnReset.setOnAction(this);

        // tile pane
        rt = new TilePane();

        // radio btn
        r1 = new RadioButton("Car");
        r2 = new RadioButton("Bike");

        // new toggle group
        tg = new ToggleGroup();
        // set toggle group to radio
        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);

        rt.getChildren().addAll(r1,r2); // add radio to tile pane

        // HBox
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(lbl1, txtName, btnSearch, btnReset, rt);

        // scene1
        VBox layout1 = new VBox();
        layout1.setSpacing(10);
        layout1.getChildren().addAll(hBox, table);

        scene = new Scene(layout1);

        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void handle(ActionEvent event) {
        ArrayList<Vehicle> filteredList;
        ObservableList<Vehicle> obsFilteredList;

        if (event.getSource() == btnSearch){
            String val = txtName.getText().toLowerCase();
            filteredList = new ArrayList<>();
            boolean found = false;
            for (Vehicle v : rvm.getVehicles()){
                if (v.getPlateNo().toLowerCase().equals(val)){
                    filteredList.add(v);
                    found = true;
                }

            }

            obsFilteredList = FXCollections.observableArrayList(filteredList);

            table.setItems(obsFilteredList);

            if (!found){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("No vehicles found");
                alert.setContentText("There is no vehicle with plate number " + val);
                alert.show();
                clearFilter();
            }

        }

        if (event.getSource() == btnReset){
            clearFilter();
        }

        if(event.getSource() == tg){
            RadioButton rbtn = (RadioButton) tg.getSelectedToggle();
            String vType = rbtn.getText();
            filteredList = new ArrayList<>();
            for (Vehicle v: rvm.getVehicles()){
                if (v.getClass().getName().equals(vType)){
                    filteredList.add(v);
                }
            }
            obsFilteredList = FXCollections.observableArrayList(filteredList);

            table.setItems(obsFilteredList);
        }

    }

    // GUI Clear filter
    private void clearFilter(){
        txtName.clear();
        ObservableList<Vehicle> observableVehicles = FXCollections.observableArrayList(rvm.getVehicles());
        table.setItems(observableVehicles);
    }
}
