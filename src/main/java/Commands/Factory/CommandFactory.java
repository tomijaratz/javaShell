package Commands.Factory;

import Commands.*;

public class CommandFactory {

    private static final int MAX_FILENAME_SIZE = 100;

    private static final int MAX_DIR_NAME_SIZE = 100;

    private static final String PWD_ERROR = "0 arguments must be passed to pwd";

    private static final String LS_ERROR = "0 arguments must be passed to ls";

    private static final String QUIT_ERROR = "0 arguments must be passed to quit";

    private static final String FILENAME_ERROR = "A filename must be provided with max length of 100 characters";

    private static final String DIRECTORY_ERROR = "A directory must be provided with max length of 100 characters";

    public static CommandVisitor getCommandForInput(String[] input) {

        /*
        As we did in Directory class, the messages i'm printing i should put them in constants in the future.
        There are to many if's, a lot of this should be parametrized too.
         */

        String command = input[0];
        if (command.equals("pwd")) {
            if (input.length > 1) {
                return new InvalidCommandVisitor(PWD_ERROR);
            }
            return new PwdCommandVisitor();
        }
        if (command.equals("ls")) {
            if (input.length > 1) {
                return new InvalidCommandVisitor(LS_ERROR);
            }
            return new LsCommandVisitor();
        }
        if (command.equals("touch")) {
            if (input.length < 2 || input[1].length() > MAX_FILENAME_SIZE || input.length > 2) {
                return new InvalidCommandVisitor(FILENAME_ERROR);
            }
            return new TouchCommandVisitor(input[1]);
        }
        if (command.equals("mkdir")) {
            if (input.length < 2 || input[1].length() > MAX_DIR_NAME_SIZE || input.length > 2) {
                return new InvalidCommandVisitor(DIRECTORY_ERROR);
            }
            return new MkdirCommandVisitor(input[1]);
        }
        if (command.equals("quit")) {
            if (input.length > 1) {
                return new InvalidCommandVisitor(QUIT_ERROR);
            }
            return new QuitCommandVisitor();
        }
        if (command.equals("cd")) {
            if (input.length < 2 || input[1].length() > MAX_DIR_NAME_SIZE) {
                return new InvalidCommandVisitor(DIRECTORY_ERROR);
            }
            return new CdCommandVisitor(input[1]);
        }

        return new InvalidCommandVisitor("");
    }
}
