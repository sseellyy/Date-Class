import java.text.SimpleDateFormat;
import java.util.*;

abstract class Date implements Comparable<Date> {
    private int day, month, year;

    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    private boolean isValidDate(int day, int month, int year) {
        return false;
    }
}
