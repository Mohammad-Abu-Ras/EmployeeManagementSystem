import java.util.Scanner;

public class EmployeeManagementSystem {

    private final EmployeeDAO dao = new EmployeeDAO();
    private final InputOutputAndValidation inputOutput = new InputOutputAndValidation();
    private final Scanner scanner = new Scanner(System.in);













    public void startProgram(){

        System.out.println();

        // we will update this:
       /* Employee em1 = new Employee();

        EnumOperations choice;

        do {
            em1.printMenu();
            em1.checkIfUserChoiceWithinTheRange((short) 1, (short) 6);
            choice = (EnumOperations.values())[em1.getUserChoice()-1] ;

            switch (choice){
                case ADD:
                    em1.addEmployee();
                    break;
                case VIEW:
                    em1.viewAllEmployees();
                    break;
                case SEARCH:
                    em1.searchEmployee();
                    break;
                case UPDATE:
                    em1.updateEmployee();
                    break;
                case DELETE:
                    em1.deleteEmployee();
                    break;
                case EXIT:
                    em1.exit();
            }
        } while((choice!=EnumOperations.EXIT));*/

    }




}
