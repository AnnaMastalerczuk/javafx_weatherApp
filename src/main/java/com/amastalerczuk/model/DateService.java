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

    private String changeEnglishDayNameIntoPolish(String dayName) {
        String name = null;

        switch (dayName) {
            case "MONDAY": {
                name = "PON";
                break;
            }
            case "TUESDAY": {
                name = "WT";
                break;
            }
            case "WEDNESDAY": {
                name = "ŚR";
                break;
            }
            case "THURSDAY": {
                name = "CZW";
                break;
            }
            case "FRIDAY": {
                name = "PT";
                break;
            }
            case "SATURDAY": {
                name = "SOB";
                break;
            }
            case "SUNDAY": {
                name = "NDZ";
                break;
            }
            default: {
                break;
            }
        }
        return name;
    }

}
