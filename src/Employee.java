import java.time.LocalDate;
// creating Employee Record as follows:

public record Employee(int id, String name, String email, String department, double salary, LocalDate joiningDate) {

}