package testCases;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class testthu {
    public static void main(String[] args){
        LocalDate currentDateTime = LocalDate.now();
        int currentDay = currentDateTime.getDayOfMonth();
        String currentMonth = currentDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
        int currentYear = currentDateTime.getYear();

        String currentDate = currentDay + " " + currentMonth + " " + currentYear;
        System.out.println(currentDate);
    }
}
