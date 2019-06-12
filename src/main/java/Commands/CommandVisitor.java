package Commands;

import Shell.Shell;

//This class represents the behavior expected that a command should have
public abstract class CommandVisitor {

    public abstract void visit(Shell shell);
}
