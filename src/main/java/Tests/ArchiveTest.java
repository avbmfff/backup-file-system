package Tests;

import FileSystem.Archive;
import org.junit.Assert;
import org.junit.Test;

public class ArchiveTest {
    @Test
    public void checkArchiveName()
    {
        Archive archive = new Archive("Moodle");
        Assert.assertEquals("Moodle", archive.getName());
    }
}
