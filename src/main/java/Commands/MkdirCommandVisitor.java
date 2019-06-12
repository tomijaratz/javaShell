package Commands;

import Shell.Shell;

public class MkdirCommandVisitor extends CommandVisitor {

    private String directoryName;

    public MkdirCommandVisitor(String directoryName) {
        this.directoryName = directoryName;
    }

    public void visit(Shell shell) {
        shell.makeDirectory(directoryName);
    }
}
