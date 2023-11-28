/**
 * Tests for the Classroom Class
 *
 * @author: Guy Tallent
 * @version: 6/1/2023
 */


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


@RunWith(JUnit4.class)

public class ClassroomTests {

    private Classroom c;

    @Before
    public void Setup(){c = new Classroom(); }

    @After
    public void tearDown(){c = null; }

    @Test
    public void addGroup(){
        assertEquals(0,c.size());

        c.addGroup(new Group("group1",0));
        c.addGroup(new Group("group2",1));

        assertEquals(2, c.size());
    }

    @Test
    public void size(){
        c.addGroup(new Group("group1",0));

        assertEquals(1, c.size());
    }

    @Test
    public void getGroup(){
        c.addGroup(new Group("group1",0));

        assertEquals("group1",c.getGroup(0).getName());
    }

    @Test
    public void removeGroup(){
        c.addGroup(new Group("group1",0));
        c.addGroup(new Group("group2",1));

        assertEquals(2, c.size());

        c.removeGroup(1);

        assertEquals(1, c.size());
    }

    @Test
    public void reset(){
        c.addGroup(new Group("group1",0));

        assertEquals(1, c.size());

        c.reset();

        assertEquals(0, c.size());
    }

}
