import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    // -----------------------------
    // Task 4: Reusable I/O Methods
    // -----------------------------

    // Read all employees from file
    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_NAME))
        );

        String line = reader.readLine();
        reader.close();

        if (line == null || line.isEmpty()) {
            return new String[0];
        }

        return line.split(",");
    }

    // Write full data to file (overwrite)
    private static void writeEmployees(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME)
        );
        writer.write(data);
        writer.close();
    }

    // Append a new employee
    private static void appendEmployee(String employeeName) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, true)
        );
        writer.write(", " + employeeName);
        writer.close();
    }

    // -----------------------------
    // Main Program Logic
    // -----------------------------
    public static void main(String[] args) {

        // Task 2: Validate number of arguments
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments!");
            System.out.println("Usage: java EmployeeManager <command>");
            return;
        }

        String command = args[0];

        try {
            if (command.equals("l")) {   // Load all employees
                System.out.println("Loading data ...");

                String[] employees = readEmployees();
                for (String emp : employees) {
                    System.out.println(emp.trim());
                }

                System.out.println("Data Loaded.");

            } else if (command.equals("s")) {   // Show random employee
                System.out.println("Loading data ...");

                String[] employees = readEmployees();
                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.length);

                System.out.println("Random Employee: " + employees[randomIndex].trim());
                System.out.println("Data Loaded.");

            } else if (command.contains("+")) {   // Add new employee
                System.out.println("Loading data ...");

                String newEmployee = command.substring(1);
                appendEmployee(newEmployee);

                System.out.println("Employee Added.");
                System.out.println("Data Loaded.");

            } else if (command.contains("?")) {   // Search for employee
                System.out.println("Loading data ...");

                String[] employees = readEmployees();
                String searchName = command.substring(1);
                boolean found = false;

                for (String emp : employees) {
                    if (emp.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee NOT found!");
                }

                System.out.println("Data Loaded.");

            } else if (command.contains("c")) {   // Count words and chars
                System.out.println("Loading data ...");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(FILE_NAME))
                );

                String line = reader.readLine();
                reader.close();

                int wordCount = 0;
                boolean inWord = false;

                for (char ch : line.toCharArray()) {
                    if (ch == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        }
                    } else {
                        inWord = false;
                    }
                }

                System.out.println(wordCount + " word(s) found, " + line.length() + " characters.");
                System.out.println("Data Loaded.");

            } else if (command.contains("u")) {   // Update an employee
                System.out.println("Loading data ...");

                String[] employees = readEmployees();
                String targetName = command.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(targetName)) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployees(String.join(",", employees));
                System.out.println("Data Updated.");

            } else if (command.contains("d")) {   // Delete an employee
                System.out.println("Loading data ...");

                String[] employees = readEmployees();
                String deleteName = command.substring(1);

                List<String> updatedList = new ArrayList<>();
                for (String emp : employees) {
                    if (!emp.trim().equals(deleteName)) {
                        updatedList.add(emp.trim());
                    }
                }

                writeEmployees(String.join(",", updatedList));
                System.out.println("Data Deleted.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
