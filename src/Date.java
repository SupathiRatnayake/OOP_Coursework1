import java.util.Objects;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) { // constructor

        if (validate(day, month, year)){

            this.day = day;
            this.month = month;
            this.year = year;

        }else {
            IllegalArgumentException e = new IllegalArgumentException("Date is not valid!");
            throw e;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (validate(day, month, year)){ // do validation

            this.day = day;

        }else {
            IllegalArgumentException e = new IllegalArgumentException("Day is not valid!");
            throw e;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (validate(day, month, year)){ // do validation

            this.month = month;

        }else {
            IllegalArgumentException e = new IllegalArgumentException("Month is not valid!");
            throw e;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (validate(day, month, year)){ // do validation

            this.year = year;

        }else {
            IllegalArgumentException e = new IllegalArgumentException("Year is not valid!");
            throw e;
        }

    }

    @Override
    public String toString() {
        return  String.format("%02d/%02d/%04d", day, + month, year);
    } // returns date as a string in dd/mm/yy format

    public static boolean validate(int day, int month, int year){

        if (year > 0){ // year must be greater than zero
            if (month > 0 && month <= 12){ // month must be greater than 0 AND less than or equal to 12
                int[] maxDates = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // maximum date of each month
                if (day > 0 && day <= maxDates[month]){
                    // day must be greater than zero AND less than or equal to maximum date of month
                    return true;
                } else if (month == 2 && day == 29 && year % 4 == 0){
                    // month must be February AND day must be 29 AND year must be leap year
                    return true;
                } else {
                    return false;
                }

            }else {
                return false;
            }

        }else {
            return false;
        }


    } // end validate

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
