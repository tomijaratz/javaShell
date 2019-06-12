package com.salesforce.tests.fs.CommandTests;

import Commands.LsCommandVisitor;
import Commands.MkdirCommandVisitor;
import org.junit.Assert;
import org.junit.Test;

public class MkdirCommandVisitorTest extends BaseCommandVisitorTest {
    private MkdirCommandVisitor command;

    private LsCommandVisitor lsCommand = new LsCommandVisitor();

    @Test
    public void mkdirShouldCreateDirectoryCorrectly(){
        command = new MkdirCommandVisitor("dir1");
        command.visit(shell);
        lsCommand.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/dir1" + "\n", result);
    }

    @Test
    public void mkdirMustShowDirectoryAlreadyExistsMessageWhenWeWantToAddAnAlreadyExistingDirectory(){
        command = new MkdirCommandVisitor("dir1");
        command.visit(shell);
        command.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("Directory already exists" + "\n", result);
    }
}
