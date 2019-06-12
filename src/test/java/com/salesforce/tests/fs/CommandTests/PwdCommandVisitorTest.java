package com.salesforce.tests.fs.CommandTests;

import Commands.PwdCommandVisitor;
import org.junit.Assert;
import org.junit.Test;

public class PwdCommandVisitorTest extends BaseCommandVisitorTest {

    private PwdCommandVisitor command = new PwdCommandVisitor();

    @Test
    public void pwdShouldReturnRootDirectoryWhenWeAreThere(){
        command.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/"+"\n", result);
    }

    @Test
    public void pwdShouldReturnChildrenDirectoryWhenWeAreThere(){
        shell.makeDirectory("dir1");
        shell.changeDirectory("dir1");

        command.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/dir1"+"\n", result);
    }
}
