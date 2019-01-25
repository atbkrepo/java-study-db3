package d3.employeeGeneratorFactory;


public interface EmployeeBuilder {
    Employee build();
    EmployeeBuilder withId(int id);
    EmployeeBuilder withName(String name);
    EmployeeBuilder withAge(int age);
    EmployeeBuilder withSalary(double salary);
    EmployeeBuilder withGender(Gender gender);
}