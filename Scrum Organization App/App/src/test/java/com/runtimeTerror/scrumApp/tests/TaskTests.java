

package com.runtimeTerror.scrumApp.tests;

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
 * Tests for the Task Class
 *
 * @author Thomas Breimer
 * @version 5/3/23
 */

@RunWith(JUnit4.class)
public class TaskTests
{
    private Task t;

    @Before
    public void setUp()
    {
        t = new Task("Name", 0);
    }

    @After
    public void tearDown()
    {
        t = null;
    }

    @Test
    public void constructorAndName() {
        assertEquals("Name of new task", "Name", t.getName());

        t.changeName("New name");

        assertEquals("change task name", "New name", t.getName());
    }

    @Test
    public void check() {
        assertFalse("tasks start off unchecked", t.isChecked());

        t.check();

        assertTrue("task is checked after we check it off", t.isChecked());

        t.toggle();

        assertFalse("task is unchecked again!!!!!", t.isChecked());
    }
}
