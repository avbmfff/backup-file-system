import FileSystem.AbstractFile;
import FileSystem.Root;

public class main {
    public static void main(String[] args) {
        Root root = Root.getInstance();
        AbstractFile abstractFile = root;
        System.out.println(root.getName());
    }
}