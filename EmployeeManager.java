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
                for (String employee : readEmployeesFromFile().split(Constants.COMMA_DELIMITER)) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].equals(Constants.SHOW_RANDOM_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employees = readEmployeesFromFile().split(Constants.COMMA_DELIMITER);
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.ADD_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                writeEmployeesToFile(Constants.COMMA_DELIMITER + args[0].substring(1), true);
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.SEARCH_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                List<String> employees = Arrays.asList(readEmployeesFromFile().split(Constants.COMMA_DELIMITER));
                if (employees.contains(args[0].substring(1))) {
                    System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                } else {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MESSAGE);
                }
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.COUNT_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String line = readEmployeesFromFile();
                String[] employees = line.split(Constants.COMMA_DELIMITER);
                System.out.println(employees.length + " employee(s) found " + line.length() + " character(s)");
            } catch (Exception exception) {}
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].contains(Constants.UPDATE_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employees = readEmployeesFromFile().split(Constants.COMMA_DELIMITER);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(args[0].substring(1))) {
                        employees[i] = Constants.UPDATED_LABEL;
                    }
                }
                writeEmployeesToFile(String.join(Constants.COMMA_DELIMITER, employees), false);
            } catch (Exception exception) {}
            System.out.println(Constants.UPDATED_MESSAGE);
        } else if (args[0].contains(Constants.DELETE_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile().split(Constants.COMMA_DELIMITER)));
                employeeList.remove(args[0].substring(1));
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