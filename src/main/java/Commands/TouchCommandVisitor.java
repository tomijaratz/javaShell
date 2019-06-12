package Commands;

import Shell.Shell;

public class TouchCommandVisitor extends CommandVisitor {

    private String filename;

    public TouchCommandVisitor(String filename) {
        this.filename = filename;
    }

    public void visit(Shell shell) {
        shell.addFileToCurrentDirectory(filename);

    }
}
