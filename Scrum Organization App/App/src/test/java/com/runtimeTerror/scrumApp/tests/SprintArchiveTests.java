/**
 * Tests for the SprintArchive Class
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

public class SprintArchiveTests {

    private SprintArchive sA;

    @Before
    public void Setup(){sA = new SprintArchive(0); }

    @After
    public void tearDown(){sA = null; }

    @Test
    public void addSprint(){
        sA.addSprint(new Sprint("sprint1",0));
        sA.addSprint(new Sprint("sprint2",1));

        assertEquals("sprint2",sA.getSprint(1).getName());
    }

    @Test
    public void getSprint(){
        sA.addSprint(new Sprint("sprint1",0));

        assertEquals("sprint1",sA.getSprint(0).getName());
    }

    @Test
    public void getArchive(){
        sA.addSprint(new Sprint("sprint1",0));
        sA.addSprint(new Sprint("sprint2",1));

        assertEquals(2, sA.getArchive().size());
    }

    @Test
    public void clear(){
        sA.addSprint(new Sprint("sprint1",0));
        sA.addSprint(new Sprint("sprint2",1));

        sA.clear(0);

        assertEquals(0,sA.getArchive().size());
    }

}
