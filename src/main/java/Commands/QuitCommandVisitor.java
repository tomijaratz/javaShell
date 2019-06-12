package Commands;

import Shell.Shell;

public class QuitCommandVisitor extends CommandVisitor {
    public void visit(Shell shell) {
        System.exit(0);
    }
}
