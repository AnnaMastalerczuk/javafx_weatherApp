package com.amastalerczuk.model;

import java.time.LocalDate;

public class DateService {
    private LocalDate date;
    private String dateString;
    private String dayName;

    public String setCurrentDate(){
        date = LocalDate.now();
        dayName = changeEnglishDayNameIntoPolish(String.valueOf(date.getDayOfWeek()));
        dateString = String.valueOf(date) + " " + dayName;
        return dateString;
    }

    public String setNextDate(int number){
        date = LocalDate.now().plusDays(number);
        dayName = changeEnglishDayNameIntoPolish(String.valueOf(date.getDayOfWeek()));
        dateString = String.valueOf(date) + " " + dayName;
        return dateString;
    }

    private String changeEnglishDayNameIntoPolish(String dayName) {
        String name = null;

        switch (dayName) {
            case "MONDAY": {
                name = "Poniedziałek";
                break;
            }
            case "TUESDAY": {
                name = "Wtorek";
                break;
            }
            case "WEDNESDAY": {
                name = "Środa";
                break;
            }
            case "THURSDAY": {
                name = "Czwartek";
                break;
            }
            case "FRIDAY": {
                name = "Piątek";
                break;
            }
            case "SATURDAY": {
                name = "Sobota";
                break;
            }
            case "SUNDAY": {
                name = "Niedziela";
                break;
            }
            default: {
                break;
            }
        }

        return name;
    }

}
