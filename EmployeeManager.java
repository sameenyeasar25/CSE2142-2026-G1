//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println(Constants.INVALID_ARG_COUNT_MESSAGE);
            return;
        }
        if (args[0].equals(Constants.LIST_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].equals(Constants.SHOW_RANDOM_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readEmployeesFromFile();
                System.out.println(line);
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.ADD_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String newEmployee = args[0].substring(1);
                writeEmployeesToFile(Constants.COMMA_DELIMITER + newEmployee, true);
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.SEARCH_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                boolean found = false;
                String searchName = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                        found = true;
                    }
                }
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.COUNT_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readEmployeesFromFile();
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
        } catch (Exception exception) {}
        System.out.println(Constants.LOADED_MESSAGE);
    } else if (args[0].contains(Constants.UPDATE_CMD)) {
        System.out.println(Constants.LOADING_MESSAGE);
        try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                String targetEmployee = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(targetEmployee)) {
                        employees[i] = Constants.UPDATED_LABEL;
                    }
                }
                writeEmployeesToFile(String.join(Constants.COMMA_DELIMITER, employees), false);
        } catch (Exception exception) {}
        System.out.println(Constants.UPDATED_MESSAGE);
    } else if (args[0].contains(Constants.DELETE_CMD)) {
        System.out.println(Constants.LOADING_MESSAGE);
        try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                String targetEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(targetEmployee);
                writeEmployeesToFile(String.join(Constants.COMMA_DELIMITER, employeeList), false);
            } catch (Exception exception) {}
            System.out.println(Constants.DELETED_MESSAGE);
        }
    }
    public static String readEmployeesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    public static void writeEmployeesToFile(String content, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, append));
        writer.write(content);
        writer.close();
    }
}