import java.util.Objects;

public class Vehicle {

    private String plateNo; // plate number
    private String make;    // make of vehicle
    private int engine;     // engine capacity in cc

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

    public String getMake() {
        return make;
    }

    public int getEngine() {
        return engine;
    }

    @Override
    public boolean equals(Object o) { // equals
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return plateNo.equals(vehicle.plateNo);
    }

    @Override
    public int hashCode() { // hashcode
        return Objects.hash(plateNo);
    }
}
