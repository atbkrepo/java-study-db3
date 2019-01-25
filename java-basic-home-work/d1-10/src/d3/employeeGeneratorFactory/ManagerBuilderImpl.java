package d3.employeeGeneratorFactory;


public class ManagerBuilderImpl extends EmployeeBuilderImpl implements ManagerBuilder {

    @Override
    protected Employee create() {
        return new Manager();
    }
}
