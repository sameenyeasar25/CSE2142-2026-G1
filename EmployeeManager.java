//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Invalid number of arguments. Usage: java EmployeeManager <l|s|+name|?name|c|uname|dname>");
            return;
        }
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String employees[] = line.split(",");
                for (String emp : employees) {
                    System.out.println(emp);
                }
                reader.close();
            } catch (Exception employees) {}
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                System.out.println(line);
                String employees[] = line.split(",");
                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true));
                String newEmployee = args[0].substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String employees[] = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
        System.out.println("Loading data ...");
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("employees.txt")));
            String line = reader.readLine();
            char[] characters = line.toCharArray();
            boolean inWord = false;
            int count = 0;
            for (char character : characters) {
                if (character == ' ') {
                    if (!inWord) {
                        count++;
                        inWord = true;
                    } else {
                        inWord = false;
                    }
                }
            }
            System.out.println(count + " word(s) found " + characters.length);
            reader.close();
        } catch (Exception exception) {}
        System.out.println("Data Loaded.");
    } else if (args[0].contains("u")) {
        System.out.println("Loading data ...");
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("employees.txt")));
            String line = reader.readLine();
            String employees[] = line.split(",");
            String targetEmployee = args[0].substring(1);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(targetEmployee)) {
                    employees[i] = "Updated";
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt"));
            writer.write(String.join(",", employees));
            writer.close();
        } catch (Exception exception) {}
        System.out.println("Data Updated.");
    } else if (args[0].contains("d")) {
        System.out.println("Loading data ...");
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("employees.txt")));
            String line = reader.readLine();
            String employees[] = line.split(",");
            String targetEmployee = args[0].substring(1);
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            employeeList.remove(targetEmployee);
            reader.close();
            BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
            } catch (Exception exception) {}
            System.out.println("Data Deleted.");
        }
    }
}