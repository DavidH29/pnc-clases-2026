package srp;

// Responsibility 3: Report generation
public class EmployeeReportGenerator {
    private SalaryCalculator salaryCalculator;

    public EmployeeReportGenerator(SalaryCalculator salaryCalculator) {
        this.salaryCalculator = salaryCalculator;
    }

    public String generateReport(Employee employee) {
        return String.format("Employee: %s, Salary: %.2f, Bonus: %.2f",
                employee.getName(),
                employee.getSalary(),
                salaryCalculator.calculateBonus(employee));
    }
}
