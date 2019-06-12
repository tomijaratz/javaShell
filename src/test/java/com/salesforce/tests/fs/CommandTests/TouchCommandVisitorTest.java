package com.salesforce.tests.fs.CommandTests;

import Commands.LsCommandVisitor;
import Commands.TouchCommandVisitor;
import org.junit.Assert;
import org.junit.Test;

public class TouchCommandVisitorTest extends BaseCommandVisitorTest {

    private TouchCommandVisitor command;

    private LsCommandVisitor lsCommand = new LsCommandVisitor();

    @Test
    public void touchShouldCreateFileCorrectly(){
        command = new TouchCommandVisitor("file.txt");
        command.visit(shell);
        lsCommand.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("file.txt" + "\n", result);
    }

    @Test
    public void touchMustShowFileAlreadyExistsMessageWhenWeWantToAddAnAlreadyExistingFile(){
        command = new TouchCommandVisitor("file.txt");
        command.visit(shell);
        command.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("File already exists" + "\n", result);
    }
}
