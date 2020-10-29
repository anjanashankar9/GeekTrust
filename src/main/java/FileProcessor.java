import com.anjanashankar.geektrust.FamilyTree;
import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.CommandNotFoundException;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static com.anjanashankar.geektrust.Constants.*;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-29
 */
public class FileProcessor {
    public boolean processBootstrapFile(FamilyTree family) {
        String initializingFile = "initialFamilyTree.txt";

        InputStream input = FileProcessor.class.getResourceAsStream(initializingFile);
        if (input == null) {
            System.out.println("The initializing file is not found");
            return true;
        }

        Scanner sc = new Scanner(input);
        processFile(family, sc, true);
        return false;
    }

    public boolean processInputFile(FamilyTree family, String inputFile) {
        File file = new File(inputFile);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            processFile(family, sc, false);
        } catch (FileNotFoundException e) {
            System.out.println("Input File is not found." +
                    "Please enter valid file!");
        }
        return false;
    }

    private void processFile(FamilyTree family, Scanner sc, boolean isBootStrap) {
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                processCommand(family, command, isBootStrap);
            } catch (PersonNotFoundException | CommandNotFoundException | ChildAdditionException | SpouseAdditionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Assumption is that the input file will be properly formatted.
    So have no checks around it.
     */
    private void processCommand(FamilyTree family, String command, boolean isBootstrap)
            throws PersonNotFoundException, CommandNotFoundException, ChildAdditionException, SpouseAdditionException {
        String[] array = command.split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        switch (array[0]) {
            case ADD_FAMILY_HEAD:
                family.addFamilyHead(array[1].replace("\"", ""), array[2]);
                break;

            case ADD_CHILD:
                family.addChild(array[1].replace("\"", ""), array[2].replace("\"", ""), array[3], isBootstrap);

                break;

            case ADD_SPOUSE:
                family.addSpouse(array[1].replace("\"", ""), array[2].replace("\"", ""), array[3]);
                break;

            case GET_RELATIONSHIP:
                System.out.println(family.getRelationship(array[1].replace("\"", ""), array[2]));
                break;

            default:
                throw new CommandNotFoundException();
        }
    }
}

