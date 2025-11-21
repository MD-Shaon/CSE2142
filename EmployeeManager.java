import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = Constant.EMPLOYEE_FILE;

    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME)))) {
            String line = reader.readLine();
            return (line == null || line.isEmpty()) ? new String[0] : line.split(",");
        }
    }

    private static void writeEmployees(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(data);
        }
    }

    private static void appendEmployee(String employeeName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(", " + employeeName);
        }
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments!");
            System.out.println("Usage: java EmployeeManager <command>");
            return;
        }

        String command = args[0];

        try {
            if (command.equals("l")) {
                System.out.println("Loading data ...");
                for (String emp : readEmployees()) System.out.println(emp.trim());
                System.out.println("Data Loaded.");

            } else if (command.equals("s")) { 
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                System.out.println("Random Employee: " + employees[new Random().nextInt(employees.length)].trim());
                System.out.println("Data Loaded.");

            } else if (command.contains("+")) {
                System.out.println("Loading data ...");
                appendEmployee(command.substring(1));
                System.out.println("Employee Added.");
                System.out.println("Data Loaded.");

            } else if (command.contains("?")) { 
                System.out.println("Loading data ...");
                boolean found = false;
                for (String emp : readEmployees()) {
                    if (emp.trim().equals(command.substring(1))) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Employee NOT found!");
                System.out.println("Data Loaded.");

            } else if (command.contains("c")) { 
                System.out.println("Loading data ...");
                char[] chars = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME))).readLine().toCharArray();
                int wordCount = 0;
                boolean inWord = false;
                for (char ch : chars) {
                    if (ch == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        }
                    } else inWord = false;
                }
                System.out.println(wordCount + " word(s), " + chars.length + " characters.");
                System.out.println("Data Loaded.");

            } else if (command.contains("u")) { 
                System.out.println("Loading data ...");
                String targetName = command.substring(1);
                String[] employees = readEmployees();
                for (int i = 0; i < employees.length; i++)
                    if (employees[i].trim().equals(targetName)) employees[i] = "Updated";
                writeEmployees(String.join(",", employees));
                System.out.println("Data Updated.");

            } else if (command.contains("d")) { 
                System.out.println("Loading data ...");
                String deleteName = command.substring(1);
                List<String> updatedList = new ArrayList<>();
                for (String emp : readEmployees())
                    if (!emp.trim().equals(deleteName)) updatedList.add(emp.trim());
                writeEmployees(String.join(",", updatedList));
                System.out.println("Data Deleted.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
