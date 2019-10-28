public class Schedule {

    private Vehicle vehicle;
    private Date pickUpDate; // pick-up date
    private Date dropOffDate; // drop-off date

    public Schedule(Vehicle vehicle, Date pickUpDate, Date dropOffDate) {
        this.vehicle = vehicle;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
    }

    @Override
    public String toString() { // returns a string "pick-up date: dd/mm/yy   drop-off date: dd/mm/yy"
        return "Vehicle: "+ vehicle.getPlateNo() +
                "pick-up date: " + pickUpDate +
                "   drop-off date: " + dropOffDate;
    }

    // getter for pick-up date
    public Date getPickUpDate() {
        return pickUpDate;
    }

    // setter for pick-up date
    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    // getter for drop-off date
    public Date getDropOffDate() {
        return dropOffDate;
    }

    // setter for drop-off date
    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

//    public boolean isOverlapping(Schedule s){
//        if (this.vehicle.equals(s.vehicle)){
//
//            if (this.pickUpDate.compareTo(s.dropOffDate) < 0 && ){
//                return true;
//            }
//        }
//    }

}
