package RepositorySystem;

import FileSystem.AbstractFile;
import FileSystem.File;
import FileSystem.Folder;

import java.util.ArrayList;

public class Repository {
    private String name;
    private ArrayList<RestorePoint> restorePoints;
    private ArrayList<AbstractFile> files;
    private Folder repositoryFolder;

    public Repository(String name){}

    public void cloneRepository(Folder cloneFolder){
        if(cloneFolder.isRepository()) throw new IllegalArgumentException("this folder already is repository");
        cloneFolder.changeRepositoryState(true);
        repositoryFolder = cloneFolder;
    }

    public void addFile(AbstractFile file){
        try {
            repositoryFolder.addFile(file);
        }
        catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("File already exist");
        }
        files.add(file.clone());
    }

    public void addFiles(ArrayList<AbstractFile> newFiles){
        try {
            repositoryFolder.addFiles(newFiles);
        }
        catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("File already exist");
        }
        for (AbstractFile file:
                newFiles) {
            files.add(file.clone());
        }
    }

}
