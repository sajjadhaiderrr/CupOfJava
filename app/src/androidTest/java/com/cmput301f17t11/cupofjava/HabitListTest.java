package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by eshna on 2017-10-21.
 */

public class HabitListTest extends ActivityInstrumentationTestCase2 {
    public HabitListTest(){
        super(com.cmput301f17t11.cupofjava.MainActivity.class);
    }

    public void testAddHabit(){
        HabitList habits = new HabitList("User1");
        Habit habit = new Habit("adding habit", "for test",new Date());
        habits.addHabit(habit);
        assertTrue(habits.habitExists(habit));
    }

    public void testDelete(){
        HabitList list = new HabitList("User1");
        Habit habit = new Habit("test","for test",new Date());
        list.addHabit(habit);
        list.deleteHabit(habit);
        assertFalse(list.habitExists(habit));
    }

    public void testGetHabit(){
        HabitList habits = new HabitList("User1"); //
        Habit habit = new Habit("test", "for test", new Date());
        habits.addHabit(habit);
        Habit returnedHabit = habits.getHabit(0);
        assertEquals(returnedHabit.getHabitTitle(), habit.getHabitTitle());
        assertEquals(returnedHabit.getHabitReason(), habit.getHabitReason());
        assertEquals(returnedHabit.getHabitStartDate(), habit.getHabitStartDate());

    }

    public void testHasHabit(){
        HabitList list = new HabitList("User1");
        Habit habit = new Habit("test","for test", new Date());
        list.addHabit(habit);
        assertTrue(list.habitExists(habit));
    }
}