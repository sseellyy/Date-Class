import java.util.*;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    // Constructor
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Invalid date!");
        }
    }

    // Check if the date is valid
    public boolean isValidDate(int d, int m, int y) {
        return y >= 1 && m >= 1 && m <= 12 && d >= 1 && d <= daysInMonth(m, y);
    }

    // Update date
    public void updateDate(int d, int m, int y) {
        if (isValidDate(d, m, y)) {
            this.day = d;
            this.month = m;
            this.year = y;
        } else {
            System.out.println("Invalid date!");
        }
    }

    // Calculate days in month
    private int daysInMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> 0;
        };
    }

    // Check leap year
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    // Get the day of the week
    public String getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    // Calculate difference in days
    public int calculateDifference(Date otherDate) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.set(year, month - 1, day);
        cal2.set(otherDate.year, otherDate.month - 1, otherDate.day);

        long diffMillis = Math.abs(cal1.getTimeInMillis() - cal2.getTimeInMillis());
        return (int) (diffMillis / (1000 * 60 * 60 * 24));
    }

    // Print the date
    public void printDate() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month - 1] + " " + day + ", " + year);
    }

    // Compare method for sorting
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Date> dates = new ArrayList<>();

        dates.add(new Date(1, 1, 2023));
        dates.add(new Date(29, 2, 2020)); // Leap year date
        dates.add(new Date(15, 3, 2022));
        dates.add(new Date(30, 4, 2021));
        dates.add(new Date(25, 12, 2025));

        System.out.println("Original Dates:");
        for (Date date : dates) {
            date.printDate();
        }

        // Sort dates
        Collections.sort(dates);

        System.out.println("\nSorted Dates:");
        for (Date date : dates) {
            date.printDate();
        }

        // Update a date
        dates.getFirst().updateDate(5, 5, 2025);
        System.out.println("\nUpdated Date:");
        dates.get(0).printDate();

        // Get the day of the week
        System.out.println("\nDay of the Week for " + dates.get(0).getDayOfWeek());

        // Calculate difference between two dates
        int diff = dates.get(0).calculateDifference(dates.get(1));
        System.out.println("\nDifference in days: " + diff);
    }
}
