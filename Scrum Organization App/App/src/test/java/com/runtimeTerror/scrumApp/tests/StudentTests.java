/**
 * Tests for the Student Class
 *
 * @author: Kevin Welch
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
public class StudentTests {

    private Student s;

    @Before
    public void Setup(){s = new Student("name1",0);}

    @After
    public void tearDown(){s = null;}

    @Test
    public void getName(){
        assertEquals("name1", s.getName());
    }

    @Test
    public void getId(){
        assertEquals(0, s.getId());
    }
    
}
