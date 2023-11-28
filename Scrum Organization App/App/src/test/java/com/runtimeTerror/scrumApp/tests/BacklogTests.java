
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
 * Tests for the Backlog Class
 *
 * @author Thomas Breimer
 * @version 5/3/23
 */


@RunWith(JUnit4.class)
public class BacklogTests
{
    private Backlog b;
    
    @Before
    public void setUp()
    {
        b = new Backlog(0);
    }

    @After
    public void tearDown()
    {
        b = null;
    }
    
    @Test
    public void addList()
    {
        b.addList(new List("Name", 0));
        assertEquals("Can add list", "Name", b.getList(0).getName());
    }

    @Test
    public void removeList()
    {
        b.addList(new List("Name", 0));
        b.addList(new List("Name", 1));
        b.removeList(1);
        b.removeList(0);
        assertEquals("Removing lists until empty backlog", 0, b.getLength());
    }

    @Test
    public void getListPosition()
    {
        b.addList(new List("Name1", 0));
        b.addList(new List("Name2", 1));
        b.addList(new List("Name3", 2));

        assertEquals("Get list position from name", 1, b.getListPosition("Name2"));

        assertEquals("Get list position for non-existent list returns -1", -1, b.getListPosition("Name4"));
    }

    @Test
    public void swap()
    {
        b.addList(new List("Name1", 0));
        b.addList(new List("Name2", 1));
        b.addList(new List("Name3", 2));
        b.addList(new List("Name4", 3));

        b.swap(1, 2);

        assertEquals("Swapping lists does indeed swap them", "Name3", b.getList(1).getName());
        assertEquals("Swapping lists does indeed swap them2", "Name2", b.getList(2).getName());
    }
}
