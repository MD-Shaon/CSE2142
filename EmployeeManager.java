// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Error: incorrect number of arguments.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands:");
            System.out.println("  l         -> list all employees");
            System.out.println("  s         -> show a random employee");
            System.out.println("  +<name>   -> add employee with given name (example: +Alice)");
            System.out.println("  ?<name>   -> search for employee by name (example: ?Alice)");
            System.out.println("  c         -> count employees and characters");
            System.out.println("  u<name>   -> update matching employee(s) to the literal \"Updated\" (example: uAlice)");
            System.out.println("  d<name>   -> delete employee with given name (example: dAlice)");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );

                String line = reader.readLine();
                reader.close();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    for (String emp : employees) {
                        System.out.println(emp.trim());
                    }
                } else {
                    System.out.println("(No employees found)");
                }

            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        else if (command.equals("s")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );

                String line = reader.readLine();
                reader.close();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    Random rand = new Random();
                    int index = rand.nextInt(employees.length);

                    System.out.println("Random Employee: " + employees[index].trim());
                } else {
                    System.out.println("(No employees to choose from)");
                }

            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        else if (command.startsWith("+")) {

            System.out.println("Loading data ...");

            try {
                String name = command.substring(1).trim();

                if (name.isEmpty()) {
                    System.out.println("Error: name missing for add operation.");
                } else {
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("employees.txt", true)
                    );

                    File f = new File("employees.txt");
                    if (f.exists() && f.length() > 0) {
                        writer.write("," + name);
                    } else {
                        writer.write(name);
                    }

                    writer.close();
                    System.out.println("Added: " + name);
                }

            } catch (Exception e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        else if (command.startsWith("?")) {

            System.out.println("Loading data ...");

            try {
                String searchName = command.substring(1).trim();

                if (searchName.isEmpty()) {
                    System.out.println("Error: name missing for search operation.");
                } else {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("employees.txt")
                            )
                    );

                    String line = reader.readLine();
                    reader.close();

                    if (line != null && !line.isEmpty()) {
                        String[] employees = line.split(",");
                        boolean found = false;

                        for (String emp : employees) {
                            if (emp.trim().equals(searchName)) {
                                System.out.println("Employee found!");
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Employee not found.");
                        }
                    } else {
                        System.out.println("Employee not found.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        else if (command.equals("c")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );

                String line = reader.readLine();
                reader.close();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    int wordCount = employees.length;
                    int charCount = line.length();

                    System.out.println(wordCount + " word(s) found, " + charCount + " characters.");
                } else {
                    System.out.println("0 word(s) found, 0 characters.");
                }

            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            System.out.println("Data Loaded.");
        }

        else if (command.startsWith("u")) {

            System.out.println("Loading data ...");

            try {
                String target = command.substring(1).trim();

                if (target.isEmpty()) {
                    System.out.println("Error: name missing for update operation.");
                } else {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("employees.txt")
                            )
                    );

                    String line = reader.readLine();
                    reader.close();

                    if (line != null && !line.isEmpty()) {
                        String[] employees = line.split(",");
                        boolean updated = false;

                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i].trim().equals(target)) {
                                employees[i] = "Updated";
                                updated = true;
                            }
                        }

                        if (updated) {
                            BufferedWriter writer = new BufferedWriter(
                                    new FileWriter("employees.txt")
                            );

                            writer.write(String.join(",", employees));
                            writer.close();
                            System.out.println("Updated matching employee(s).");
                        } else {
                            System.out.println("No matching employee to update.");
                        }
                    } else {
                        System.out.println("No employees to update.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error updating file: " + e.getMessage());
            }

            System.out.println("Data Updated.");
        }

        else if (command.startsWith("d")) {

            System.out.println("Loading data ...");

            try {
                String target = command.substring(1).trim();

                if (target.isEmpty()) {
                    System.out.println("Error: name missing for delete operation.");
                } else {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("employees.txt")
                            )
                    );

                    String line = reader.readLine();
                    reader.close();

                    if (line != null && !line.isEmpty()) {
                        List<String> list = new ArrayList<>();

                        for (String emp : line.split(",")) {
                            if (!emp.trim().equals(target)) {
                                list.add(emp.trim());
                            }
                        }

                        BufferedWriter writer = new BufferedWriter(
                                new FileWriter("employees.txt")
                        );

                        writer.write(String.join(",", list));
                        writer.close();

                        System.out.println("Deleted (if existed): " + target);
                    } else {
                        System.out.println("No employees to delete.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }

            System.out.println("Data Deleted.");
        }
         
        else {
            System.out.println("Invalid command!");
        }
    }
}
