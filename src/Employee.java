import java.time.LocalDate;

public record Employee (int id,String name, String email, String department, double salary,LocalDate joiningDate) {

}