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

public boolean isValidDate(int d, int m, int y) {
    if (m < 1 || m > 12 || d < 1) return false;
    int[] daysInMonth = {31, isLeapYear(y) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    return d <= daysInMonth[m - 1];
}

public static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}

public void updateDate(int d, int m, int y) {
    if (isValidDate(d, m, y)) {
        this.day = d;
        this.month = m;
        this.year = y;
    } else {
        System.out.println("Invalid date!");
    }
}
