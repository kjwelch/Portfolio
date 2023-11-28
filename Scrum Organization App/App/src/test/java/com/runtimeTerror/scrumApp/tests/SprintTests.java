

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tests for the Sprint Class
 *
 * @author Thomas Breimer
 * @version 5/3/23
 */

@RunWith(JUnit4.class)
public class SprintTests
{
    private Sprint s;

    @Before
    public void setUp()
    {
        s = new Sprint("Cool Sprint", 0);
    }

    @After
    public void tearDown()
    {
        s = null;
    }

    @Test
    public void getAndChangeName()
    {

        assertEquals("Get original name from construction", "Cool Sprint", s.getName());

        s.changeName("cooler sprint");

        assertEquals("Get changed name", "cooler sprint", s.getName());
    }


    @Test
    public void addList()
    {
        s.addList(new List("Name", 0));
        assertEquals("Can add list", "Name", s.getList(0).getName());
    }

    @Test
    public void removeList()
    {
        s.addList(new List("Name", 0));
        s.addList(new List("Name", 0));
        s.removeList(1);
        s.removeList(0);
        assertEquals("Removing lists until empty backlog", 0, s.getLength());

        s.removeList(-4);
    }

    @Test
    public void getListPosition()
    {
        s.addList(new List("Name1", 0));
        s.addList(new List("Name2", 1));
        s.addList(new List("Name3", 2));

        assertEquals("Get list position from name", 1, s.getListPosition("Name2"));

        assertEquals("Get list position for non-existent list returns -1", -1, s.getListPosition("Name4"));

        // Should not raise error
        s.getList(6);
        s.getList(-2);
    }

    @Test
    public void finishSprint()
    {
        assertEquals("reflection is empty string before sprint is completed", "", s.getReflection());

        s.finishSprint("review");

        assertEquals("get reflection after sprint completion", "review", s.getReflection());

        DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDateTime now = LocalDateTime.now();
        String dateString = date.format(now);

        assertEquals("Get start date", dateString, s.getStartDate());
        assertEquals("Get finish date", dateString, s.getFinishDate());
    }

    @Test
    public void isEmpty()
    {
        assertTrue("sprint starts out empty", s.isEmpty());

        s.addList(new List("?", 0));

        assertFalse("sprint is not empty after adding a list", s.isEmpty());

        s.removeList(0);

        assertTrue("sprint is empty after removing all lists", s.isEmpty());
    }

}
