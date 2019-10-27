public class Car extends Vehicle{

    private int doors; // number of door the car have
    private int seats; // number of seats the car have

    public Car(String plateNo, String make, int engine, int doors, int seats) {
        super(plateNo, make, engine);
        this.doors = doors;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " +
                super.toString()+
                " doors: " + doors +
                " seats: " + seats;
    }

    public int getDoors() {
        return doors;
    }

    public int getSeats() {
        return seats;
    }

}
