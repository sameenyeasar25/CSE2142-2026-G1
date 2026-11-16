import java.io.*;
import java.util.*;

// Manages employee records stored in a comma-separated text file.

public class EmployeeManager {
    public static void main(String[] args) {
        // Validate that exactly one command-line argument is provided
        if(args.length != 1) {
            System.out.println(Constants.INVALID_ARG_COUNT_MESSAGE);
            return;
        }
        
        String userArgument = args[0];

        // List all employees
        if (userArgument.equals(Constants.LIST_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                for (String employeeName : loadEmployeeData().split(Constants.COMMA_DELIMITER)) {
                    System.out.println(employeeName);
                }
            } catch (IOException ioException) {
                System.out.println("Error reading employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Display a random employee    
        } else if (userArgument.equals(Constants.SHOW_RANDOM_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employeeNames = loadEmployeeData().split(Constants.COMMA_DELIMITER);
                System.out.println(employeeNames[new Random().nextInt(employeeNames.length)]);
            } catch (IOException ioException) {
                System.out.println("Error reading employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Add a new employee to the file
        } else if (userArgument.startsWith(Constants.ADD_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                saveEmployeeData(Constants.COMMA_DELIMITER + userArgument.substring(1), true);
            } catch (IOException ioException) {
                System.out.println("Error writing employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Search for an existing employee
        } else if (userArgument.startsWith(Constants.SEARCH_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                List<String> employeeList = Arrays.asList(loadEmployeeData().split(Constants.COMMA_DELIMITER));
                if (employeeList.contains(userArgument.substring(1))) {
                    System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                } else {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MESSAGE);
                }
            } catch (IOException ioException) {
                System.out.println("Error reading employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Count total employees and characters
        } else if (userArgument.equals(Constants.COUNT_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String rawEmployeeData = loadEmployeeData();
                String[] employeeNames = rawEmployeeData.split(Constants.COMMA_DELIMITER);
                System.out.println(employeeNames.length + " employee(s) found " + rawEmployeeData.length() + " character(s)");
            } catch (IOException ioException) {
                System.out.println("Error reading employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.LOADED_MESSAGE);

        // Update a matching employee record
        } else if (userArgument.startsWith(Constants.UPDATE_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                String[] employeeNames = loadEmployeeData().split(Constants.COMMA_DELIMITER);
                String targetEmployee = userArgument.substring(1);
                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        employeeNames[index] = Constants.UPDATED_LABEL;
                    }
                }
                saveEmployeeData(String.join(Constants.COMMA_DELIMITER, employeeNames), false);
            } catch (IOException ioException) {
                System.out.println("Error updating employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.UPDATED_MESSAGE);

        // Delete a matching employee record
        } else if (userArgument.startsWith(Constants.DELETE_CMD)) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(loadEmployeeData().split(Constants.COMMA_DELIMITER)));
                employeeList.remove(userArgument.substring(1));
                saveEmployeeData(String.join(Constants.COMMA_DELIMITER, employeeList), false);
            } catch (IOException ioException) {
                System.out.println("Error deleting employee data: " + ioException.getMessage());
            }
            System.out.println(Constants.DELETED_MESSAGE);

        // Handle unrecognized commands
        } else {
            System.out.println(Constants.INVALID_ARG_MESSAGE);
        }
    }

    // Reads the single-line employee record from the data file.

    public static String loadEmployeeData() throws IOException {
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));
        String fileContent = fileReader.readLine();
        fileReader.close();
        return fileContent;
    }

    // Writes or appends employee data to the data file.

    public static void saveEmployeeData(String dataContent, boolean shouldAppend) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, shouldAppend));
        fileWriter.write(dataContent);
        fileWriter.close();
    }
}