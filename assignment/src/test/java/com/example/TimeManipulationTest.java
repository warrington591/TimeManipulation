package com.example;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Warrington on 6/4/17.
 */
public class TimeManipulationTest {

    @org.junit.Test
    public void addMinutesTest() throws Exception {
        String input1 = "9:13 AM";
        int input2 = 200;
        String output;
        String expected = "12:33 PM";
        TimeManipulation t = new TimeManipulation();
        output = t.addMinutes(input1, input2);
        assertEquals(expected, output);
    }


    @org.junit.Test
    public void validateTest() throws Exception {
        String input = "9:13 AM";
        Boolean output;
        Boolean expected =true ;
        TimeManipulation t = new TimeManipulation();
        output = t.validateTimeGiven(input);
        assertEquals(expected,output);
    }

    @org.junit.Test
    public void getMeridiemTest() throws Exception {
        String input1 = "9:13 AM";
        String output;
        String expected = "am";
        TimeManipulation t = new TimeManipulation();
        output = t.getMeridiem(input1);
        assertEquals(expected, output);
    }

    @org.junit.Test
    public void getHourTest() throws Exception {
        String input1 = "9:13 AM";
        String output;
        String expected = "9";
        TimeManipulation t = new TimeManipulation();
        output = t.getHour(input1);
        assertEquals(expected, output);
    }


    @org.junit.Test
    public void getMinsTest() throws Exception {
        String input1 = "9:13 AM";
        String output;
        String expected = "13";
        TimeManipulation t = new TimeManipulation();
        output = t.getMins(input1);
        assertEquals(expected, output);
    }

    @Test
    public void addValuesTest() throws Exception {
        int input1= 200;
        String input3 = "9";
        String input4 = "13";
        StringBuilder output;
        StringBuilder expected = new StringBuilder();
        expected.append("12:33 PM");
        TimeManipulation t = new TimeManipulation();
        t.meridiem = "am";
        output = t.addValues(input1, input3, input4);
        assertEquals(expected.toString(), output.toString());
    }

    @Test
    public void minusValuesTest() throws Exception {
        int input1 = -1270;
        String input3 ="9";
        String input4 ="00";
        StringBuilder output;
        StringBuilder expected = new StringBuilder();
        expected.append("11:50 AM");
        TimeManipulation t = new TimeManipulation();
        t.meridiem ="am";
        output = t.minusValues(input1, input3, input4);
        assertEquals(expected.toString(), output.toString());
    }

    @Test
    public void formatHoursTest() throws Exception {
        int expected = 1;
        int output;
        TimeManipulation t = new TimeManipulation();
        t.resultingHours = 13;
        output = t.formatHours();
        assertEquals(expected, output);
    }

    @Test
    public void adjustMeridiemTest() throws Exception {
        String expected = "pm";
        TimeManipulation t = new TimeManipulation();
        t.meridiem="am";
        t.resultingHours = 13;
        t.adjustMeridiem();
        String output = t.meridiem;
        assertEquals(expected, output);
    }

}