package com.salesforce.tests.fs.CommandTests;

import Commands.CdCommandVisitor;
import Commands.MkdirCommandVisitor;
import Commands.PwdCommandVisitor;
import org.junit.Assert;
import org.junit.Test;

public class CdCommandVisitorTest extends BaseCommandVisitorTest {

    private CdCommandVisitor command;

    private PwdCommandVisitor pwdCommand = new PwdCommandVisitor();

    private MkdirCommandVisitor mkdirCommand;

    @Test
    public void cdShouldStayInRootWhenCalledToGoUpOneLevel() {
        command = new CdCommandVisitor("cd ..");
        pwdCommand.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/"+"\n", result);
    }

    @Test
    public void cdShouldBeAbleToGoToAnyChildrenDirectory() {
        command = new CdCommandVisitor("dir1");
        mkdirCommand = new MkdirCommandVisitor("dir1");
        mkdirCommand.visit(shell);

        command.visit(shell);
        pwdCommand.visit(shell);

        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/dir1" + "\n", result);
    }

    @Test
    public void cdShouldBeAbleToReachParentFromDir1() {
        command = new CdCommandVisitor("dir1");
        mkdirCommand = new MkdirCommandVisitor("dir1");
        mkdirCommand.visit(shell);

        command.visit(shell);
        command = new CdCommandVisitor("..");
        command.visit(shell);
        pwdCommand.visit(shell);

        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/" + "\n", result);
    }

    @Test
    public void cdShouldBeAbleToGoDownManyLevels() {
        command = new CdCommandVisitor("dir1");
        mkdirCommand = new MkdirCommandVisitor("dir1");
        mkdirCommand.visit(shell);
        command.visit(shell);

        command = new CdCommandVisitor("..");
        mkdirCommand = new MkdirCommandVisitor("dir2");
        mkdirCommand.visit(shell);
        command.visit(shell);

        command = new CdCommandVisitor("dir1/dir2");
        command.visit(shell);

        pwdCommand.visit(shell);

        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/dir1/dir2" + "\n", result);
    }

    @Test
    public void cdCanNotGoToANotExistingDirectory() {
        command = new CdCommandVisitor("dir2");
        mkdirCommand = new MkdirCommandVisitor("dir1");
        mkdirCommand.visit(shell);

        command.visit(shell);

        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("Directory does not exists" + "\n", result);
    }
}


