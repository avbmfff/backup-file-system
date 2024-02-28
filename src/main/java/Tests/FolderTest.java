package Tests;

import FileSystem.Archive;
import FileSystem.File;
import FileSystem.Folder;
import FileSystem.Root;
import org.junit.Assert;
import org.junit.Test;

public class FolderTest {
    @Test
    public void checkFolderName()
    {
        Folder folder = new Folder("Moodle");
        Assert.assertEquals("Moodle", folder.getName());
    }

    @Test
    public void checkAddFile()
    {
        Folder folder = new Folder("Moodle");
        File file = new File("String", ".txt");
        folder.addFile(file);
        Assert.assertEquals("Moodle", file.getParent().getName());
    }

    @Test
    public void checkAddFolder()
    {
        Folder folder1 = new Folder("Moodle");
        Folder folder2 = new Folder("Moodle-2");
        folder1.addFile(folder2);
        Assert.assertEquals("Moodle", folder2.getParent().getName());
    }

    @Test
    public void checkAddArchive()
    {
        Folder folder = new Folder("Moodle");
        Archive file = new Archive("String");
        folder.addFile(file);
        Assert.assertEquals("Moodle", file.getParent().getName());
    }

    @Test
    public void checkAddFolders(){
        Folder folder = new Folder("Moodle");
        Folder folder1 = new Folder("Moodle", folder);
        Folder folder2 = new Folder("Moodle", folder1);
        Assert.assertEquals("Moodle", folder2.getParent().getName());
    }

    @Test
    public void checkDeleteFiles(){
        Root root = Root.getInstance();
        Folder folder = new Folder("Moodle");
        root.deleteFile(folder.getName());
        Assert.assertEquals(root.getFiles().size(),0);
    }


}
