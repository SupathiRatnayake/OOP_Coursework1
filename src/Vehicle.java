public class Vehicle {

    private String plateNo; // plate number
    private String make;    // make of vehicle
    private int engine;     // engine capacity in cc
    private Schedule booking; // booking schedule

    public Vehicle(String plateNo, String make, int engine) {
        this.plateNo = plateNo;
        this.make = make;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "plateNo: " + plateNo +
                " make: " + make +
                " engine: " + engine + " cc";
    }

    public String getPlateNo() {
        return plateNo;
    }
}
