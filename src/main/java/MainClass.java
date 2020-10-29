import com.anjanashankar.geektrust.FamilyTree;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class MainClass {

    public static void main(String[] args) {
        FamilyTree family = new FamilyTree();
        FileProcessor fp = new FileProcessor();

        if (fp.processBootstrapFile(family)) return;

        try {
            fp.processInputFile(family, args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter file location(s)!");
        }
    }
}
