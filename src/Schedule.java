public class Schedule {

    private Date pickUpDate; // pick-up date
    private Date dropOffDate; // drop-off date

    public Schedule(Date pickUpDate, Date dropOffDate) {
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
    }

    @Override
    public String toString() { // returns a string "pick-up date: dd/mm/yy   drop-off date: dd/mm/yy"
        return "pick-up date: " + pickUpDate +
                "   drop-off date: " + dropOffDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

}
