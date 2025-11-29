import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputOutputAndValidation {

    private short userChoice;
    private short min;
    private short max;

    // I created one scanner and reused it
    private final Scanner input = new Scanner(System.in);

    public short getUserChoice() {
        return userChoice;
    }


//    --- note ---

    // I create this Function To notify the User that His choice are wrong! // to validate user choice
    public void checkIfUserChoiceWithinTheRange(short min, short max) {
        this.min = min;
        this.max = max;

        takeUserChoice("Choose option: ");
        while (userChoice < min || userChoice > max) {
            takeUserChoice("invalid choice!! Please enter a valid choice! ");
        }
    }

    // this method created to take the value(digit) from the user and validate it using handling exception
    public void takeUserChoice(String message) {
        System.out.print(message);

        while (true) {
            try {
                this.userChoice = input.nextShort();
                break;

            } catch (InputMismatchException e) {
                // to know the type of the exception, at first I used (Exception) class inside catch
                // then I printed the Exception message to know the exact class I must handle it => System.out.println(e);
                // to clear incorrect input, I used .nextLine method
                input.nextLine();
                System.out.printf("Error! Please enter a digit between %d and %d ? ", this.min, this.max);
            }
        }
    }

    // This method for printing the menu is reusable once we need it:
    public void printMenu() {
        System.out.println("ــــــــــــــــــــــــــــــــــــــ");
        System.out.println("\t\t\t\tMenu");
        System.out.println("ــــــــــــــــــــــــــــــــــــــ");
        System.out.println("\t1. Add Employee");
        System.out.println("\t2. View All Employees");
        System.out.println("\t3. Search Employee");
        System.out.println("\t4. Update Employee");
        System.out.println("\t5. Delete Employee");
        System.out.println("\t6. Exit");
        System.out.println("ــــــــــــــــــــــــــــــــــــــ");
    }

    // this is a dynamic method for printing the suitable message and taking a string [name , email, department]
    public String readStringFromUser(String message) {

        System.out.print(message);
        input.nextLine();
        return input.nextLine();
    }

    // this method to read an amount of money (double) and validate it using handling exception
    public double readSalaryFromUser() {
        System.out.print("Please enter Salary? ");
        while (true) {
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Error! Please enter the amount of salary ?");
            }
        }
    }

    // this is method is dynamic, used to print the screen based on the choice of user
    public void printScreens(String Screen) {
        System.out.println("--------------------------------------");
        System.out.println("\t\t" + Screen);
        System.out.println("--------------------------------------");

    }

    public void printSuccessMessage(String message) {
        System.out.println(message);
    }
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printEmployee(Employee employee) {
        if (employee == null) {
            printErrorMessage("Employee is not found!");
            return;
        }
        System.out.println("ID: " + employee.id());
        System.out.println("Name: " + employee.name());
        System.out.println("Email: " + employee.email());
        System.out.println("Department: "+employee.department());
        System.out.println("Salary: "+ employee.salary());
        System.out.println("Joining date: " + employee.joiningDate());

    }

    public void printTable(List<Employee> list) {
        if(list.isEmpty()) {
            printErrorMessage("No employees found!");
            return;
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-25s %-15s %-10s %-12s\n","ID", "Name", "Email", "Department", "Salary", "Join Date");
        System.out.println("------------------------------------------------------------------------");

        for (Employee e : list) {
            System.out.printf("%-5d %-20s %-25s %-15s %-10.2f %-12s\n",e.id(), e.name(), e.email(), e.department(), e.salary(), e.joiningDate());
        }

        System.out.println("------------------------------------------------------------------------");
    }





    //end
}


