package d3.employeeGenerator;

public class Test {
    public static void main(String[] args) {
        Employee[] employees = MockEmployeesGenerator.generate(10);
        d3.employeeGenerator.EmployeeService employeeService = new EmployeeService();
        employeeService.setEmployees(employees);

        employeeService.print();
    }
}
