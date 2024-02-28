package FileSystem;

import java.util.ArrayList;

public abstract class AbstractFile {
    private String name;
    private AbstractFile parent;
    private ArrayList<AbstractFile> files = new ArrayList<AbstractFile>();

    public String getName() { return name; }

    public AbstractFile getParent() {
        return parent;
    }

    public void changeParent(AbstractFile parent) {
        if (parent.name.isBlank()) throw new IllegalArgumentException("invalid file name");
        this.parent = parent;
    }

    public void addFile(AbstractFile file) {
        if (this instanceof File) throw new IllegalArgumentException("cannot create file in file type");
        if (isExist(file.getName())) throw new IllegalArgumentException("this file already exist");
        if (file.getParent() == null) file.addFile(file);
        file.getParent().deleteFile(file.getName());
        file.setParent(this);
        files.add(file);
    }

    public void addFiles(ArrayList<AbstractFile> files) {
        if (this instanceof File) throw new IllegalArgumentException("cannot create file in file type");
        for (AbstractFile file :
                files) {
            if (!isExist(file.name)) throw new IllegalArgumentException("this file already exist");
            files.add(file);
        }
    }

    public void deleteFile(String name) {
        if (this instanceof File) throw new IllegalArgumentException("cannot delete file in file type");
            if (!isExist(name)) throw new IllegalArgumentException("this file does not exist");
        files.removeIf(file -> file.getName().equals(name));
    }

    public void deleteFiles(ArrayList<String> names) {
        if (this instanceof File) throw new IllegalArgumentException("cannot create file in file type");
        for (AbstractFile file :
                files) {
            if (!isExist(file.name)) throw new IllegalArgumentException("this file does not exist");
            files.remove(file);
        }
    }

    public ArrayList<AbstractFile> getFiles() {
        return files;
    }

    public void setParent(AbstractFile parent) {
        if (parent instanceof File) throw new IllegalArgumentException("the files cannot be parent");
        this.parent = parent;
    }

    public AbstractFile clone() {
        AbstractFile clone = null;
        if (this instanceof File) {
            File file = (File) this;
            clone = new File(file);
        }
        if (this instanceof Folder) {
            Folder folder = (Folder) this;
            clone = new Folder(folder);
        }
        if (this instanceof Archive) {
            Archive archive = (Archive) this;
            clone = new Archive(archive);
        }
        if (this instanceof Root) throw new IllegalArgumentException("cannot copy root file");
        return clone;
    }

    protected boolean isExist(String name) {
        for (AbstractFile file :
                files) {
            if (file.getName().equals(name)) return true;
        }
        return false;
    }
}

