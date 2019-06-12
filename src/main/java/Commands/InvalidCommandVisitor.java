package Commands;

import Shell.Shell;

public class InvalidCommandVisitor extends CommandVisitor {

    public InvalidCommandVisitor(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public void visit(Shell shell) {

        System.out.print(errorMessage);
    }
}
