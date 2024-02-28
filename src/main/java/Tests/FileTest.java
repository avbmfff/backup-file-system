package Tests;

import FileSystem.File;
import FileSystem.Folder;
import org.junit.Assert;
import org.junit.Test;

public class FileTest {
    Folder folder = new Folder("Moodle");
    File file1 = new File("Moodle", ".txt");
    File file2 = new File("Moodle", ".ru");
    File file3 = new File("Moodle", ".txt", folder);


    @Test
    public void checkFileName()
    {
        Assert.assertEquals("Moodle.txt", file1.getName());
    }

    @Test
    public void checkFileParent()
    {
        Assert.assertEquals("C:/", file1.getParent().getName());
    }

    @Test
    public void checkFileParentDe()
    {
        Assert.assertEquals("C:/", file1.getParent().getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkSetFileAsParent(){
        file1.setParent(file2);
    }

    @Test
    public void checkSetFolderAsParent(){
        Assert.assertEquals("Moodle", file3.getParent().getName());
    }

}
