package com.amastalerczuk.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.*;
import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    @Test
    void dateShouldNotBeNull(){
        //given
        DateService dateService = new DateService();

        //when
        String date = dateService.setCurrentDate();

        //then
        assertNotNull(date);
    }

}