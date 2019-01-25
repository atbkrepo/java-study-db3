package d3.employeeGenerator;

public class EmployeeService {
    private Employee[] employees;

    public void setEmployees(Employee[] newEmployees) {
        employees = newEmployees;
    }

    public void print() {
        for (Employee employee : this.employees) {
            System.out.println(employee.toString());
        }
    }
}
