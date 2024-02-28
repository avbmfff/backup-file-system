package Strategy;

import FileSystem.AbstractFile;
import FileSystem.Archive;
import RepositorySystem.RestorePoint;

import java.util.ArrayList;

public interface StrategyImpl {
    public ArrayList<Archive> saveFiles(RestorePoint restorePoint);
}
