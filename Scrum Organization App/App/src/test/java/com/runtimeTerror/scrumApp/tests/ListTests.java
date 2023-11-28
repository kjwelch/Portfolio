package com.runtimeTerror.scrumApp.tests;

/**
 * Tests for the List Class
 *
 * @author Thomas Breimer
 * @version 5/3/23
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import model.*;

/**
 * Tests for the List Class
 *
 * @author Thomas Breimer
 * @version 5/3/23
 */

@RunWith(JUnit4.class)
public class ListTests {
    private List l;

    @Before
    public void setUp() {
        l = new List("List1", 0);
    }

    @After
    public void tearDown() {
        l = null;
    }

    @Test
    public void constructorAndGetName(){
        assertEquals("Get name", "List1", l.getName());
    }
    @Test
    public void getLength(){
        assertEquals("Init length is 0", 0, l.getLength());

        l.addTask("task1", 0);
        l.addTask("task2", 1);

        assertEquals("Length increases after adding tasks", 2, l.getLength());

        l.removeTask(0);

        assertEquals("length decreases after removing tasks", 1, l.getLength());

        l.removeTask(0);

        assertEquals("length is 0 after removing all tasks", 0, l.getLength());
    }

    @Test
    public void deleteTask(){
        // removing out of bounds does not cause an error
        l.removeTask(4);
    }

    @Test
    public void taskChecking(){
        l.addTask("task1", 0);

        assertFalse("tasks starts off unchecked", l.taskIsChecked(0));

        l.toggleTaskChecked(0);

        assertTrue("Checking off a task checks it off", l.taskIsChecked(0));

        l.addTask("task2", 1);
        l.addTask("task3", 2);
        l.addTask("task4", 3);
        l.addTask("task5", 4);

        assertFalse("tasks buried in list start off unchecked", l.taskIsChecked(3));

        l.toggleTaskChecked(3);

        assertTrue("Checking off a task buried in list checks it off", l.taskIsChecked(3));

        assertFalse("task out of bounds should return false when taskIsChecked is called", l.taskIsChecked(7));
    }

    @Test
    public void getTaskName(){
        l.addTask("task1", 0);
        l.addTask("task2", 1);
        l.addTask("task3", 2);
        l.addTask("task4", 3);
        l.addTask("task5", 4);

        assertEquals("get task name", "task3", l.getTaskName(2));

        l.removeTask(2);

        assertEquals("task name at index is different after removing task before that task", "task4", l.getTaskName(2));

        l.removeTask(6);
    }

    @Test
    public void changeTaskName(){
        l.addTask("task1", 0);
        l.addTask("task2", 1);
        l.addTask("task3", 2);
        l.addTask("task4", 3);
        l.addTask("task5", 4);

        l.changeTaskName(3, "different task");

        assertEquals("get task name", "different task", l.getTaskName(3));

        l.changeTaskName(6, "should not cause error for out of bounds value");
    }
}