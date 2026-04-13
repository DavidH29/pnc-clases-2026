package srp;

// Violates SRP - multiple responsibilities
public class Employee {
    private String name;
    private double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Responsibility 1: Employee data management
    public String getName() { return name; }
    public double getSalary() { return salary; }

    // Responsibility 2: Salary calculation
    public double calculateBonus() {
        if (department.equals("Sales")) {
            return salary * 0.2;
        }
        return salary * 0.1;
    }

    // Responsibility 3: Report generation
    public String generateReport() {
        return String.format("Employee: %s, Salary: %.2f, Bonus: %.2f",
                name, salary, calculateBonus());
    }

    // Responsibility 4: Persistence
    public void saveToDatabase() {
        System.out.println("Saving " + name + " to database");
    }
}