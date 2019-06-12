package Commands;

import Shell.Shell;

public class LsCommandVisitor extends CommandVisitor {
    public void visit(Shell shell) {
        shell.showCurrentDirectory();
    }
}
