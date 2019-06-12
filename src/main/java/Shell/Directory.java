package Shell;

import java.util.*;

public class Directory {

    private static final String FILE_EXISTS_MESSAGE = "File already exists";

    private static final String DIRECTORY_EXISTS_MESSAGE = "Directory already exists";

    private static final String DIRECTORY_NOT_EXISTS_MESSAGE = "Directory does not exists";

    private String name;

    private List<String> files;

    //This collaborator includes the directories in the subtree of this node.
    private Map<String, Directory> childrens;

    private Directory parent;

    public Directory(Directory parent, String dirName){
        //We should also check for dirName != null
        this.name = dirName;
        this.parent = parent;
        this.files = new LinkedList<String>();
        this.childrens = new HashMap<String, Directory>();
    }

    public void addFile(String name) {
        if (files.contains(name)) {
            System.out.println(FILE_EXISTS_MESSAGE);
        } else {
            files.add(name);
        }
    }

    public void showFilesAndFolders() {
        for (String dirName : childrens.keySet()) {
            System.out.println("/"+dirName);
        }

        for (String filename : files) {
            System.out.println(filename);
        }
    }

    public void addDirectory(String dirName) {
        if (childrens.get(dirName) != null) {
            System.out.println(DIRECTORY_EXISTS_MESSAGE);
        } else {
            childrens.put(dirName, new Directory(this, dirName));
        }

    }


    /*
    This function is recursive, if we are looking for the same directory we
    are in then we return this, else we get the children/parent directory
    to continue the path and call getDirectory again.
     */
    public Directory getDirectory(List<String> pathToDir, Shell shell) {
        if (pathToDir.size() == 0) {
            return this;
        } else {
            String nextDirectoryName = pathToDir.get(0);
            pathToDir.remove(0);
            Directory nextDirectory = childrens.get(nextDirectoryName);
            if (nextDirectory != null) {
                shell.addToPath(nextDirectory.getName());
                return nextDirectory.getDirectory(pathToDir, shell);
            } else {
                System.out.println(DIRECTORY_NOT_EXISTS_MESSAGE);
                return null;
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public Directory getParent() {
        return this.parent;
    }

    public boolean containsDirectory(String secondPathDirectory) {
        return this.childrens.get(secondPathDirectory) != null;
    }
}
