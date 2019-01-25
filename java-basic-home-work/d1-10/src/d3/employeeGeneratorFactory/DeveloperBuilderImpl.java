package d3.employeeGeneratorFactory;


public class DeveloperBuilderImpl extends EmployeeBuilderImpl implements DeveloperBuilder {

    @Override
    public DeveloperBuilder WithFixedBugs(boolean fixedBugs) {
        ((Developer)employee).setFixedBugs(fixedBugs);
        return this;
    }

    @Override
    protected Employee create() {
        return new Developer();
    }
}
