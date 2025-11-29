import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private final EmployeeDAO dao = new EmployeeDAO();
    private final InputOutputAndValidation inputOutput = new InputOutputAndValidation();
    private final Scanner scanner = new Scanner(System.in);

    public void startProgram(){


        EnumOperations choice;

        do {
            inputOutput.printMenu();
            inputOutput.checkIfUserChoiceWithinTheRange((short) 1, (short) 6);
            choice = (EnumOperations.values())[inputOutput.getUserChoice()-1] ;

            switch (choice){
                case ADD:
                    addEmployee();
                /*    break;
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
                    exit();*/
            }
        } while((choice!=EnumOperations.EXIT));

    }

// creating add Employee method to link between DB and user
    private void addEmployee(){

        inputOutput.printScreens("Add Employee");

        String name = inputOutput.readStringFromUser("Enter Name: ");
        String email = inputOutput.readStringFromUser("Enter Email: ");
        String department = inputOutput.readStringFromUser("Enter Department: ");
        double salary = inputOutput.readSalaryFromUser();

        Employee Emp = new Employee(0,name,email,department,salary, LocalDate.now());

        if(dao.addEmployee(Emp))
            System.out.println("The employee has been successfully added.");
        else
            inputOutput.printErrorMessage("Failed to add the employee.");
    }

    // view all method
    private void viewAllEmployees() {
        inputOutput.printScreens("Employees List");
        inputOutput.printTable(dao.getAllEmployees());
    }



}
