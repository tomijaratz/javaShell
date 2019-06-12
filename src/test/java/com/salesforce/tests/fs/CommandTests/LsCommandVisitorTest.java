package com.salesforce.tests.fs.CommandTests;

import Commands.LsCommandVisitor;
import org.junit.Assert;
import org.junit.Test;

public class LsCommandVisitorTest extends BaseCommandVisitorTest {

    private LsCommandVisitor command = new LsCommandVisitor();

    @Test
    public void lsShouldReturnEmptyIfTheDirectoryIsEmpty(){
        command.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("", result);
    }

    @Test
    public void lsShouldShowFilesAndDirectoriesWhenTheyExist(){
        shell.makeDirectory("dir1");
        shell.addFileToCurrentDirectory("file.txt");

        command.visit(shell);
        String result = systemOutRule.getLogWithNormalizedLineSeparator();
        Assert.assertEquals("/dir1" + "\n" + "file.txt" + "\n", result);
    }
}
