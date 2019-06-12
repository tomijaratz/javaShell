package Shell;

import Commands.CommandVisitor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Shell {

    private List<String> path;

    private Directory currentDirectory;

    public Shell(Directory root) {
        currentDirectory = root;
        this.path = new LinkedList<String>();
    }

    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }

    public List<String> getPath() {
        return this.path;
    }

    public void addFileToCurrentDirectory(String filename) {
        currentDirectory.addFile(filename);
    }

    public void showCurrentDirectory() {
        currentDirectory.showFilesAndFolders();
    }

    public void makeDirectory(String directoryName) {
        currentDirectory.addDirectory(directoryName);
    }

    void addToPath(String dirname) {
        this.path.add(dirname);

    }

    public void changeDirectory(String path) {
        List<String> pathToDir = new LinkedList<String>();
        pathToDir.addAll(Arrays.asList(path.split("/")));

        //the starting point to search the directory
        Directory directoryStartingPoint = currentDirectory;

        String firstPathDirectory = pathToDir.get(0);

        if (firstPathDirectory.equals("..")) {
            if (pathToDir.size() == 1) {
                if (this.path.size() > 0) {
                    this.path.remove(this.path.size() - 1);
                    this.currentDirectory = this.currentDirectory.getParent();

                }
                return;
            } else {
                String secondPathDirectory = pathToDir.get(1);
                Directory parent = currentDirectory.getParent();
                if(!parent.containsDirectory(secondPathDirectory)){
                    System.out.println("Invalid path");
                    return;
                }
                directoryStartingPoint = parent != null ? parent : currentDirectory;
                this.path.remove(this.path.size() - 1);
                pathToDir.remove(0);
            }
        }

        Directory newCurrentDirectory = directoryStartingPoint.getDirectory(pathToDir, this);
        if (newCurrentDirectory != null) {
            currentDirectory = newCurrentDirectory;

        }
    }


}
