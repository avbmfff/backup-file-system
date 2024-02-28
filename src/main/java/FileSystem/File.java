package FileSystem;

import java.util.ArrayList;


public class File extends AbstractFile {
    private String name;
    private AbstractFile parent;

    public File(File original) {
        this.name = original.name;
        this.parent = original.parent;
    }

    public File(String name, String extension, AbstractFile parent) {
        if (name.isBlank()) throw new IllegalArgumentException("incorrect file name");
        if (extension.isBlank()) throw new IllegalArgumentException("incorrect extension name");
        if (extension.charAt(0) != '.') throw new IllegalArgumentException("the first symbol at extension should be .");
        if (!Root.getInstance().searchFile(parent.getName()))
            throw new IllegalArgumentException("parent doesn't exist");
        if (parent instanceof File) throw new IllegalArgumentException("file cannot be parent");
        if (parent.isExist(name + extension)) throw new IllegalArgumentException("the same file already exist");

        this.name = name + extension;
        this.parent = parent;
    }

    public File(String name, String extension) {
        if (name.isBlank()) throw new IllegalArgumentException("incorrect file name");
        if (extension.isBlank()) throw new IllegalArgumentException("incorrect extension name");
        if (extension.charAt(0) != '.') throw new IllegalArgumentException("the first symbol at extension should be .");
        if (Root.getInstance().isExist(name + extension))
            throw new IllegalArgumentException("the same file already exist");

        this.name = name + extension;
        this.parent = Root.getInstance();
        Root.getInstance().addFile(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AbstractFile getParent() {
        return parent;
    }

    @Override
    public void setParent(AbstractFile parent) {
        if (parent instanceof File) throw new IllegalArgumentException("the files cannot be parent");
        this.parent = parent;
    }

    @Override
    public void addFile(AbstractFile file) {
        throw new IllegalArgumentException("file cannot be a parent");
    }

    @Override
    public void addFiles(ArrayList<AbstractFile> files) {
        throw new IllegalArgumentException("file cannot be a parent");
    }

    @Override
    public void deleteFile(String name) {
        throw new IllegalArgumentException("file cannot be a parent");
    }

    @Override
    public void deleteFiles(ArrayList<String> names) {
        throw new IllegalArgumentException("file cannot be a parent");
    }

}
