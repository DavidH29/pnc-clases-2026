package srp;

// Responsibility 4: Persistence
public class EmployeeRepository {
    public void saveToDatabase(Employee employee) {
        System.out.println("Saving " + employee.getName() + " to database");
    }
}
