import java.util.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Employee extends InputOutputAndValidation{

    private int id;
    private String name;
    private String email;
    private String department;
    private double salary;
    private LocalDate joinDate;

    InputOutputAndValidation inst =  new InputOutputAndValidation();

    /* regard the setters, since we dont need modify the ID [ID is auto increment] and
       [I thought it best that taking the join date from the system not the user [Automated process] ]
       so we will crate setter methods just for these parameters [name , email, department, salary, joinDate ]
    */

    // setting name property
    public void setName(String name) {
        this.name = name;
    }

    //setting email property
    public void setEmail(String email) {
        this.email = email;
    }

    //setting department property
    public void setDepartment(String department) {
        this.department = department;
    }

    //setting salary property
    public void setSalary(double salary) {
        this.salary = salary;
    }

    //setting joinDate property
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

   /* A getters methods are necessary to retrieve the value of all parameters in the record:
      [id,name,email,department,salary,joinDate ], so we have to build them as follows :*/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void addEmployee(){

        inst.printScreens("Add an Employee Screen");
        setName(inst.readStringFromUser("Enter Name? "));
        setEmail(inst.readStringFromUser("Enter Email? "));
        setDepartment(inst.readStringFromUser("Enter Department? "));
        setSalary(inst.readSalaryFromUser());
        setJoinDate(getDateFromSystem());

        /*System.out.println(getName());
        System.out.println(getEmail());
        System.out.println(getDepartment());
        System.out.println(getSalary());
        System.out.println(getJoinDate());*/
    }

    public void viewAllEmployees(){
        inst.printScreens("View All Employees Screen");

    }

    public void searchEmployee(){
        inst.printScreens("Search Employee Screen");

    }

    public void updateEmployee(){
        inst.printScreens("Update Employee Screen");

    }

    public void deleteEmployee(){
        inst.printScreens("Delete Employee Screen");
    }

    public void exit(){
        inst.printScreens("Exit Screen");
        System.out.println("Thank you for using our program.");
    }

    public LocalDate getDateFromSystem(){
        // by this method, created a date object and return it :
        return LocalDate.now();
    }

}




