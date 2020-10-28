import com.anjanashankar.geektrust.FamilyTree;
import com.anjanashankar.geektrust.exception.PersonNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.anjanashankar.geektrust.Constants.*;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-27
 */
public class MainClass {

    public static void processFile(FamilyTree family, Scanner sc) {
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                processCommand(family, command);
            } catch (PersonNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Assumption is that the input file will be properly formatted.
    So have no checks around it.
     */
    private static void processCommand(FamilyTree family, String command) throws PersonNotFoundException {
        System.out.println(command);
        String regex = "([A-Z_]+)\\ +(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);

        if(matcher.find()) {
            Pattern p;
            Matcher m;
            switch (matcher.group(1)) {
                case ADD_FAMILY_HEAD:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z ]+)");
                    m = p.matcher(matcher.group(2));
                    if(m.find()) {
                        family.addFamilyHead(m.group(1), m.group(2));
                    }
                    break;

                case ADD_CHILD:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z ]+)\"\\ +\"([A-Za-z]+)\"");
                    m = p.matcher(matcher.group(2));
                    if(m.find()) {
                        family.addChild(m.group(1), m.group(2), m.group(3));
                    }
                    break;

                case ADD_SPOUSE:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z ]+)\"\\ +\"([A-Za-z]+)");
                    m = p.matcher(matcher.group(2));
                    if(m.find()) {
                        family.addSpouse(m.group(1), m.group(2), m.group(3));
                    }

                    break;

                case GET_RELATIONSHIP:
                    p = Pattern.compile("\"([A-Za-z ]+)\"\\ +\"([A-Za-z-]+)");
                    m = p.matcher(matcher.group(2));
                    if(m.find()) {
                        System.out.println(family.getRelationship(m.group(1), m.group(2)));
                    }
                    break;

                default:
                    System.out.println("UNABLE TO UNDERSTAND THIS COMMAND!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("MAIN CLASS - JUST TO SEE IF IT IS RUNNING");
        FamilyTree family = new FamilyTree();
        if (initializeFamilyTree(family)) return;

        try {
            System.out.println(args[0]);
            File inputFile = new File(args[0]);
            Scanner sc = new Scanner(inputFile);
            processFile(family, sc);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter file location(s)!");
        } catch (FileNotFoundException e) {
            System.out.println("Input File is not found." +
                    "Please enter valid file!");
        }
    }

    private static boolean initializeFamilyTree(FamilyTree family) {
        String initializingFile = "initialFamilyTree.txt";

        InputStream input = MainClass.class.getResourceAsStream(initializingFile);
        if (input == null) {
            System.out.println("The initializing file is not found");
            return true;
        }

        Scanner sc = new Scanner(input);
        processFile(family, sc);
        return false;
    }
}
