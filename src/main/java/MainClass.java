import com.anjanashankar.geektrust.FamilyTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.anjanashankar.geektrust.Constants.*;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public class MainClass {

    public static void processInitFile(FamilyTree family, String initFile) {
        try {
            File file = new File(initFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String command = sc.nextLine();

                processInitCommand(family, command);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!! " +
                "Please check the path provided!");
        }
    }

    /*
    Assumption is that the input file will be properly formatted.
    So have no checks around it.
     */
    private static void processInitCommand(FamilyTree family, String command) {
        String[] commandParams = command.split(";");
        switch (commandParams[0]) {
            case ADD_FAMILY_HEAD:
                family.addFamilyHead(commandParams[1], commandParams[2]);
                break;

            case ADD_CHILD:
                family.addChild(commandParams[1], commandParams[2], commandParams[3]);
                break;

            case ADD_SPOUSE:
                family.addSpouse(commandParams[1], commandParams[2], commandParams[3]);
                break;

            default:
                System.out.println("UNABLE TO UNDERSTAND THIS COMMAND!");
                break;
        }
    }

    public static void main(String[] args) {
        FamilyTree family = new FamilyTree();

        try {
            processInitFile(family, args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter file location(s)!");
        }
    }
}
