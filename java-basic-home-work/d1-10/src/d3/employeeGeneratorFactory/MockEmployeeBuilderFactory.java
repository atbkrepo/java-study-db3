package d3.employeeGeneratorFactory;


import java.time.DayOfWeek;
import java.util.Random;

public class MockEmployeeBuilderFactory {
    private static String[] firstNames = {"Иван", "Петр", "Семен", "Василий", "Кондратий", "Евгений", "Даниил", "Алексей"};
    private static String[] lastNames = {"Иванов", "Петров", "Потапов", "Слепаков", "Жуков", "Бандера", "Петлюра", "Хмельницкий"};
    static int employeeId;
    static Random random = new Random();

    private EmployeeBuilder createEmployee(EmployeeBuilder builder) {
        int firstNameIndex = random.nextInt(firstNames.length - 1);
        int lastNameIndex = random.nextInt(lastNames.length - 1);

        return builder
                .withId(++employeeId)
                .withAge(random.nextInt(50) + 20)
                .withGender(Gender.values()[random.nextInt(Gender.values().length)])
                .withName(firstNames[firstNameIndex] + " " + lastNames[lastNameIndex])
                .withSalary(Math.round(random.nextDouble() * 1000000) / 100.00);
    }

    public Employee createManager(EmployeeBuilder builder) {
        return
                ((ManagerBuilder) createEmployee(builder))
                        .build();
    }

    public Employee createDeveloper(EmployeeBuilder builder) {
        return
                ((DeveloperBuilder) createEmployee(builder))
                        .WithFixedBugs(random.nextBoolean())
                        .build();
    }

    public Employee createCleaner(EmployeeBuilder builder) {
        int countOfDays = random.nextInt(7);
        DayOfWeek[] dayOfWeeks = new DayOfWeek[countOfDays];

        for (int i = 0; i < countOfDays; i++) {
            dayOfWeeks[i] = DayOfWeek.values()[i];
        }
        return
                ((CleanerBuilder) createEmployee(builder))
                        .withRate(random.nextDouble())
                        .withWorkedDays(dayOfWeeks)
                        .build();

    }
}
