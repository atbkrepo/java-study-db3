package d3.employeeGeneratorFactory;

import java.time.DayOfWeek;

public interface CleanerBuilder extends EmployeeBuilder {
    CleanerBuilder withRate(Double rate);
    CleanerBuilder withWorkedDays(DayOfWeek[] workedDays);
}
