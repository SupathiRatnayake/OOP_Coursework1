public class Bike extends Vehicle {

    private String bikeType;
    private boolean sidecar;

    public Bike(String plateNo, String make, int engine, String bikeType, boolean sidecar) {
        super(plateNo, make, engine);
        this.bikeType = bikeType;
        this.sidecar = sidecar;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " +
                super.toString()+
                " type: " + bikeType +
                " sidecar: " + sidecar;
    }

}
