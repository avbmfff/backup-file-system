package FileSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Root extends AbstractFile{
    private String name;
    private ArrayList<AbstractFile> files = new ArrayList<>();
    private static Root instance;

    private Root() {
        name = "C:/";
    }

    @Override
    public String getName() {return name;}

    public static Root getInstance()
    {
        if (instance == null)
            instance = new Root();
        return instance;
    }

    @Override
    public void addFiles(ArrayList<AbstractFile> files) {
        for (AbstractFile file :
                files) {
            if (!isExist(file.getName())) throw new IllegalArgumentException("this file already exist");
            files.add(file);
        }
    }

    @Override
    public AbstractFile getParent() {
        return null;
    }

    @Override
    public ArrayList<AbstractFile> getFiles() {
        return files;
    }

    @Override
    public void addFile(AbstractFile file) {
        if (isExist(file.getName())) throw new IllegalArgumentException("this file already exist");
        files.add(file);
    }

    public boolean searchFile(String fileName) {
        Root root = Root.getInstance();
        Queue<AbstractFile> queue = new LinkedList<>();
        queue.addAll(files);

        while (!queue.isEmpty()) {
            AbstractFile current = queue.poll();

            if (current.getName().equals(fileName)) {
                return true;
            }

            if (current instanceof Folder) {
                Folder folder = (Folder) current;
                queue.addAll(folder.getFiles());
            }

            if (current instanceof Root) {
                Root root1 = (Root) current;
                queue.addAll(root.files);
            }
        }

        return false;
    }

    @Override
    public void deleteFile(String name) {
        if (!isExist(name)) throw new IllegalArgumentException("this file does not exist");
        files.removeIf(file -> file.getName().equals(name));
    }

    @Override
    protected boolean isExist(String name) {
        for (AbstractFile file :
                files) {
            if (file.getName().equals(name)) return true;
        }
        return false;
    }

}
