package com.salesforce.tests.fs.CommandTests;

import Shell.Shell;
import Shell.Directory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;

/*
I'm adding only the command tests(and more should be done), the directory and shell tests and the tests to parse the
input are necessary too, in a real life project i would add them.
 */
public class BaseCommandVisitorTest {

    protected Shell shell;

    @Before
    public void setup(){
        Directory root = new Directory(null, "root");
        shell = new Shell(root);

    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().muteForSuccessfulTests().enableLog();
}
