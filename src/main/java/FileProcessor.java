import com.anjanashankar.geektrust.FamilyTree;
import com.anjanashankar.geektrust.exception.ChildAdditionException;
import com.anjanashankar.geektrust.exception.CommandNotFoundException;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;
import com.anjanashankar.geektrust.exception.SpouseAdditionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        processFile(family, sc);
        return false;
    }

    public boolean processInputFile(FamilyTree family, String inputFile) {
        File file = new File(inputFile);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            processFile(family, sc);
        } catch (FileNotFoundException e) {
            System.out.println("Input File is not found." +
                    "Please enter valid file!");
        }
        return false;
    }

    private void processFile(FamilyTree family, Scanner sc) {
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                processCommand(family, command);
            } catch (PersonNotFoundException | CommandNotFoundException | ChildAdditionException | SpouseAdditionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Assumption is that the input file will be properly formatted.
    So have no checks around it.
     */
    private void processCommand(FamilyTree family, String command)
            throws PersonNotFoundException, CommandNotFoundException, ChildAdditionException, SpouseAdditionException {
        String regex = "([A-Z_]+)\\ +(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            Pattern p;
            Matcher m;
            switch (matcher.group(1)) {
                case ADD_FAMILY_HEAD:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z ]+)");
                    m = p.matcher(matcher.group(2));
                    if (m.find()) {
                        family.addFamilyHead(m.group(1), m.group(2));
                    }
                    break;

                case ADD_CHILD:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z ]+)\"\\ +\"([A-Za-z]+)\"");
                    m = p.matcher(matcher.group(2));
                    if (m.find()) {
                        family.addChild(m.group(1), m.group(2), m.group(3));
                    }
                    break;

                case ADD_SPOUSE:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z ]+)\"\\ +\"([A-Za-z]+)");
                    m = p.matcher(matcher.group(2));
                    if (m.find()) {
                        family.addSpouse(m.group(1), m.group(2), m.group(3));
                    }

                    break;

                case GET_RELATIONSHIP:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z-]+)");
                    m = p.matcher(matcher.group(2));
                    if (m.find()) {
                        System.out.println(family.getRelationship(m.group(1), m.group(2)));
                    }
                    break;

                default:
                    throw new CommandNotFoundException();
            }
        }
    }

}
