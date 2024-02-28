//package Strategy;
//
//import FileSystem.AbstractFile;
//import FileSystem.Archive;
//import RepositorySystem.RestorePoint;
//
//import java.util.ArrayList;
//
//public class SingleStrategy implements StrategyImpl {
//    @Override
//    public ArrayList<Archive> saveFiles(RestorePoint restorePoint) {
//        ArrayList<Archive> archives = new ArrayList<>();
//        Archive archive = new Archive(restorePoint.getName());
//        archive.addFiles(restorePoint.getFiles());
//        archives.add(archive);
//        return archives;
//    }
//}
