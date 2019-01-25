package d3.employeeGeneratorFactory;

public interface DeveloperBuilder extends EmployeeBuilder{
    DeveloperBuilder WithFixedBugs(boolean fixedBugs);
}
