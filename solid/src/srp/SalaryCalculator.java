package srp;

// Responsibility 2: Salary calculation
public class SalaryCalculator {
    public double calculateBonus(Employee employee) {
        if (employee.getDepartment().equals("Sales")) {
            return employee.getSalary() * 0.2;
        }
        return employee.getSalary() * 0.1;
    }
}
