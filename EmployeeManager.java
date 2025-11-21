import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

      
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments!");
            System.out.println("Usage: java EmployeeManager <command>");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            } catch (Exception ex) {
                System.out.println("Error reading file!");
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.length);

                System.out.println("All Employees: " + line);
                System.out.println("Random Employee: " + employees[randomIndex].trim());

            } catch (Exception ex) {
                System.out.println("Error reading file!");
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("+")) {

            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true)
                );

                String name = command.substring(1);
                writer.write(", " + name);
                writer.close();

            } catch (Exception ex) {
                System.out.println("Error writing to file!");
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("?")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                String searchName = command.substring(1);
                boolean found = false;

                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee NOT found!");
                }

            } catch (Exception ex) {
                System.out.println("Error reading file!");
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("c")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                char[] chars = line.toCharArray();

                int wordCount = 0;
                boolean inWord = false;

                for (char ch : chars) {
                    if (ch == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        }
                    } else {
                        inWord = false;
                    }
                }

                System.out.println(wordCount + " word(s) found, " + chars.length + " characters in file.");

            } catch (Exception ex) {
                System.out.println("Error reading file!");
            }

            System.out.println("Data Loaded.");

        } else if (command.contains("u")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                String name = command.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt")
                );

                writer.write(String.join(",", employees));
                writer.close();

            } catch (Exception ex) {
                System.out.println("Error updating file!");
            }
            System.out.println("Data Updated.");

        } else if (command.contains("d")) {

            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );

                String line = reader.readLine();
                String[] employees = line.split(",");

                String name = command.substring(1);

                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(emp -> emp.trim().equals(name));

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt")
                );

                writer.write(String.join(",", employeeList));
                writer.close();

            } catch (Exception ex) {
                System.out.println("Error deleting employee!");
            }
            System.out.println("Data Deleted.");
        }
    }
}
