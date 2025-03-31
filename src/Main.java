import java.util.*;

class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    // Array for days in each month (normal year)
    private static final int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // Constructor
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    // Check if a date is valid
    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1) return false;
        int maxDays = daysInMonth[month];
        if (month == 2 && isLeapYear(year)) maxDays = 29;
        return day <= maxDays;
    }

    // Leap year checker
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Calculate day of the week using Zeller's Congruence
    public String getDayOfWeek() {
        int y = (month < 3) ? year - 1 : year;
        int m = (month < 3) ? month + 12 : month;
        int K = y % 100;
        int J = y / 100;
        int dayOfWeek = (day + (13 * (m + 1)) / 5 + K + (K / 4) + (J / 4) + (5 * J)) % 7;
        String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return days[dayOfWeek];
    }

    // Calculate difference between two dates (simplified approach)
    public int calculateDifference(Date other) {
        Calendar cal1 = new GregorianCalendar(year, month - 1, day);
        Calendar cal2 = new GregorianCalendar(other.year, other.month - 1, other.day);
        long diffMillis = Math.abs(cal1.getTimeInMillis() - cal2.getTimeInMillis());
        return (int) (diffMillis / (1000 * 60 * 60 * 24));
    }

    // Print the date
    public void printDate() {
        String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month] + " " + day + ", " + year);
    }

    // compareTo method for sorting
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }

    // toString method
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();

        // Creating dates
        dates.add(new Date(15, 3, 2023));
        dates.add(new Date(29, 2, 2024)); // Leap year
        dates.add(new Date(1, 1, 2020));
        dates.add(new Date(31, 12, 2019));
        dates.add(new Date(5, 7, 2022));
        dates.add(new Date(18, 11, 2025));
        dates.add(new Date(9, 9, 2021));
        dates.add(new Date(23, 4, 2018));
        dates.add(new Date(14, 6, 2017));
        dates.add(new Date(30, 8, 2016));

        // Printing dates
        System.out.println("Dates before sorting:");
        for (Date d : dates) d.printDate();

        // Sorting dates
        Collections.sort(dates);

        System.out.println("\nDates after sorting:");
        for (Date d : dates) d.printDate();

        // Comparing two dates
        Date d1 = new Date(1, 1, 2020);
        Date d2 = new Date(31, 12, 2019);
        System.out.println("\nDifference between " + d1 + " and " + d2 + " is " + d1.calculateDifference(d2) + " days.");

        // Getting day of the week
        System.out.println("\nThe day of the week for " + d1 + " is " + d1.getDayOfWeek());
    }
}
