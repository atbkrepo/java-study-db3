package d3.employeeGeneratorFactory;


public abstract class EmployeeBuilderImpl implements EmployeeBuilder {

    protected abstract Employee create();
    protected Employee employee = create();

    @Override
    public Employee build() {
        return employee;
    }

    @Override
    public EmployeeBuilder withId(int id) {
         employee.setId(id);
         return this;
    }

    @Override
    public EmployeeBuilder withName(String name) {
        employee.setName(name);
        return this;
    }

    @Override
    public EmployeeBuilder withAge(int age) {
        employee.setAge(age);
        return this;
    }

    @Override
    public EmployeeBuilder withSalary(double salary) {
        employee.setSalary(salary);
        return this;
    }

    @Override
    public EmployeeBuilder withGender(Gender gender) {
        employee.setGender(gender);
        return this;
    }
}
