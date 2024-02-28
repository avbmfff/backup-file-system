//package Strategy;
//
//import FileSystem.AbstractFile;
//import FileSystem.Archive;
//import RepositorySystem.RestorePoint;
//
//import java.util.ArrayList;
//
//public class SplitStrategy implements StrategyImpl {
//    @Override
//    public ArrayList<Archive> saveFiles(RestorePoint restorePoint) {
//        ArrayList<Archive> archives = new ArrayList<>();
//        for (AbstractFile file :
//                restorePoint.getFiles()) {
//            Archive archive = new Archive(restorePoint.getName() + '_' + file.getName());
//            archives.add(archive);
//        }
//
//        return archives;
//    }
//}
