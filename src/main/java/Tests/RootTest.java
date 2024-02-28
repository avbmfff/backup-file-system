package Tests;

import FileSystem.AbstractFile;
import FileSystem.Archive;
import FileSystem.Root;
import org.junit.Assert;
import org.junit.Test;

public class RootTest {
    @Test
    public void checkRootName()
    {
        Root root = Root.getInstance();
        AbstractFile abstractFile = root;
        Assert.assertEquals("C:/", abstractFile.getName());
    }
}
