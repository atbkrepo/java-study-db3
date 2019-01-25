package d3.employeeGeneratorFactory;


import java.time.DayOfWeek;

public class CleanerBuilderImpl extends EmployeeBuilderImpl implements CleanerBuilder {

    @Override
    public CleanerBuilder withRate(Double rate) {
        ((Cleaner)employee).setRate(rate);
        return this;
    }

    @Override
    public CleanerBuilder withWorkedDays(DayOfWeek[] workedDays) {
        ((Cleaner)employee).setWorkedDays(workedDays);
        return this;
    }

    @Override
    protected Employee create() {
        return new Cleaner();
    }
}
