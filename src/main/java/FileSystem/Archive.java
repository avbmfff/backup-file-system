package FileSystem;

import java.util.ArrayList;

public class Archive extends AbstractFile {
    private String name;
    private AbstractFile parent;
    private ArrayList<AbstractFile> files;

    public Archive(Archive original) {
        this.name = original.name;
        this.parent = original.parent;
        this.files = original.files;
    }

    public Archive(String name, AbstractFile parent) {
        if (name.isBlank()) throw new IllegalArgumentException("incorrect folder name");
        if (!Root.getInstance().searchFile(parent.getName()))
            throw new IllegalArgumentException("parent doesn't exist");
        if (parent instanceof File) throw new IllegalArgumentException("file cannot be parent");

        this.name = name;
        this.parent = parent;
        parent.addFile(this);

    }

    public Archive(String name) {
        if (name.isBlank()) throw new IllegalArgumentException("incorrect folder name");

        this.name = name;
        this.parent = Root.getInstance();
        Root.getInstance().addFile(this);
    }

    @Override
    public void addFile(AbstractFile file) {
        if (isExist(file.getName())) throw new IllegalArgumentException("this file already exist");
        if (file.getParent() == null) file.addFile(file);
        file.getParent().deleteFile(file.getName());
        file.setParent(this);
        files.add(file);
    }

    @Override
    public void setParent(AbstractFile parent) {
        if (parent instanceof File) throw new IllegalArgumentException("the files cannot be parent");
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<AbstractFile> getFiles() {
        return files;
    }

    @Override
    public AbstractFile getParent() {
        return parent;
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
