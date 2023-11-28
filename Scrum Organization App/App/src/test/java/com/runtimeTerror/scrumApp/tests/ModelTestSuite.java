package com.runtimeTerror.scrumApp.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses
({
    BacklogTests.class,
        ListTests.class,
        SprintTests.class,
        TaskTests.class,
        StudentTests.class,
        GroupTests.class,
        ClassroomTests.class,
        SprintArchiveTests.class
})
public class ModelTestSuite
{ // no implementation needed; above annotations do the work.
}
