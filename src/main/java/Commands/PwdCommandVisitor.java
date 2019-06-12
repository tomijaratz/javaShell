package Commands;

import Shell.Shell;

import java.util.List;

public class PwdCommandVisitor extends CommandVisitor {
    public void visit(Shell shell) {
        List<String> path = shell.getPath();
        for(String dir : path){
            System.out.print("/"+dir);
        }
        if(path.isEmpty()){
            System.out.print("/");
        }
        System.out.print ("\n");
    }
}
