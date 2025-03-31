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
@Override
public int compareTo(Date other) {
    if (this.year != other.year)
        return Integer.compare(this.year, other.year);
    if (this.month != other.month)
        return Integer.compare(this.month, other.month);
    return Integer.compare(this.day, other.day);
}
}

public class Main {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();

        try {
            dates.add(new Date(15, 3, 2023));
            dates.add(new Date(1, 1, 2022));
            dates.add(new Date(29, 2, 2024)); // Leap year case
            dates.add(new Date(10, 7, 2025));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Before Sorting:");
        for (Date d : dates) d.printDate();

        Collections.sort(dates);

        System.out.println("\nAfter Sorting:");
        for (Date d : dates) d.printDate();

        // Demonstrate date difference
        Date d1 = new Date(1, 1, 2022);
        Date d2 = new Date(15, 3, 2023);
        System.out.println("\nDifference between dates: " + d1.calculateDifference(d2) + " days");

        // Print the day of the week
        System.out.println("\nThe day of the week for ");
        d2.printDate();
        System.out.println(" is " + d2.getDayOfWeek());
    }
}
