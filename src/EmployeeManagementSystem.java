import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private final EmployeeDAO dao = new EmployeeDAO();
    private final InputOutputAndValidation inputOutput = new InputOutputAndValidation();
    private final Scanner scanner = new Scanner(System.in);

    public void startProgram() throws SQLException {


        EnumOperations choice;

        do {
            inputOutput.printMenu();
            inputOutput.checkIfUserChoiceWithinTheRange((short) 1, (short) 6);
            choice = (EnumOperations.values())[inputOutput.getUserChoice() - 1];

            switch (choice) {
                case ADD:
                    addEmployee();
                    break;
                case VIEW:
                    viewAllEmployees();
                    break;
                case SEARCH:
                    searchEmployee();
                    break;
                case UPDATE:
                    updateEmployee();
                    break;
                case DELETE:
                    deleteEmployee();
                    break;
                case EXIT:
                    exitProgram();
            }
        } while ((choice != EnumOperations.EXIT));

    }

    // creating add Employee method to link between DB and user
    private void addEmployee() throws SQLException {

        inputOutput.printScreens("Add Employee");

        String name = inputOutput.readStringFromUser("Enter Name: ");
        String email = inputOutput.readStringFromUser("Enter Email: ");
        String department = inputOutput.readStringFromUser("Enter Department: ");
        double salary = inputOutput.readSalaryFromUser();

        // create object from Employee record, and sending id 0 as a value, since id is auto-increment by DB
        Employee Emp = new Employee(0, name, email, department, salary, LocalDate.now());

        // in dao.addEmployee if the rows affected, it will return true, else it will return false
        if (dao.addEmployee(Emp)) {
            inputOutput.printSuccessMessage("The employee has been successfully added.");
        } else
            inputOutput.printErrorMessage("Failed to add employee. ");
    }

    // view all employees method
    private void viewAllEmployees() {
        inputOutput.printScreens("Employees List");
        inputOutput.printTable(dao.getAllEmployees());
    }

    //search about employee by id
    private void searchEmployee() {
        inputOutput.printScreens("Search Employee ");
        System.out.println("Please Enter Employee ID to Search: ");
        int id = scanner.nextInt();
        inputOutput.printEmployee(dao.getEmployeeById(id));
    }

    // update employee using this method
    //  updates an existing employee's information based on user input
    private void updateEmployee() {

        inputOutput.printScreens("Update Employee");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); //consume the leftover newline from nextInt() using nextLine()
        //retrieve employee from database
        Employee old = dao.getEmployeeById(id);
        //if employee does not exist show error and stop process
        if (old == null) {
            inputOutput.printErrorMessage("Employee not found.");
            return;
        }
        // asking user for new info: [name, email,dep,salary]
        String newName = inputOutput.readStringFromUser("Enter new Name (" + old.name() + "): ");
        if (newName.isBlank())
            //keeping original name if user enter white spaces
            // isBlank() Method to check if a string is empty or contains only whitespace characters.
            newName = old.name();

        String newEmail = inputOutput.readStringFromUser("Enter new Email (" + old.email() + "): ");
        if (newEmail.isBlank())
            newEmail = old.email();

        String newDepartment = inputOutput.readStringFromUser("Enter new Department (" + old.department() + "): ");
        if (newDepartment.isBlank())
            newDepartment = old.department();

        System.out.print("Enter new Salary (" + old.salary() + "): ");
        String salaryInput = scanner.nextLine();
        // if blank? keep old salary. otherwise parse new value, this by shorthand if
        // it is more readable
        double newSalary = salaryInput.isBlank() ?
                old.salary() : Double.parseDouble(salaryInput);

        // creating new employee record with updated values
        Employee update = new Employee(id, newName, newEmail, newDepartment, newSalary, old.joiningDate());

        if (dao.updateEmployee(update)) {
            inputOutput.printSuccessMessage("Employee has been successfully updated.");
        }else
            inputOutput.printErrorMessage("Failed to update the employee.");
    }


    private void deleteEmployee() {
        inputOutput.printScreens("Delete Employee");
        System.out.println("Enter ID");
        int id = scanner.nextInt();

        if (dao.deleteEmployee(id)) {
            inputOutput.printSuccessMessage("Employee has been successfully deleted.");
        } else
            inputOutput.printErrorMessage("Failed to delete the employee.");
    }

    private void exitProgram() {
        inputOutput.printScreens("Exit Program");
        //DBConnection.closeConnection();

        System.out.println("Thank you for using Employee Management System! ");
    }


}
