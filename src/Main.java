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

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private boolean isValidDate(int day, int month, int year) {
        return false;
    }

    public boolean isValidDate(int d, int m, int y) {
        if (m < 1 || m > 12 || d < 1) return false;
        int[] daysInMonth = {31, isLeapYear(y) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return d <= daysInMonth[m - 1];
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

    public String getDayOfWeek() {
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public int calculateDifference(Date otherDate) {
        Calendar cal1 = new GregorianCalendar(this.year, this.month - 1, this.day);
        Calendar cal2 = new GregorianCalendar(otherDate.year, otherDate.month - 1, otherDate.day);
        long diffMillis = Math.abs(cal1.getTimeInMillis() - cal2.getTimeInMillis());
        return (int) (diffMillis / (1000 * 60 * 60 * 24));
    }

    public void printDate() {
        System.out.println(MONTHS[month - 1] + " " + day + ", " + year);
    }
}

