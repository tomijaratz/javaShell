package Commands;

import Shell.Shell;

public class CdCommandVisitor extends CommandVisitor {

    private String path;

    public CdCommandVisitor(String path) {
        this.path = path;
    }

    public void visit(Shell shell) {
        shell.changeDirectory(path);
    }
}
