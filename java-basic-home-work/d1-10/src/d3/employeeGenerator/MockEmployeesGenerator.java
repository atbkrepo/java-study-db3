package d3.employeeGenerator;
//Developer(id,name,age,salary,gender,fixedBugs)
//  Manager(id,name,age,salary,gender)
//  Cleaner(id,name,age,salary,gender,rate,workedDays)

import java.time.DayOfWeek;
import java.util.Random;

public class MockEmployeesGenerator {

    private static String[] firstNames = {"Иван", "Петр", "Семен", "Василий", "Кондратий", "Евгений", "Даниил", "Алексей"};
    private static String[] lastNames = {"Иванов", "Петров", "Потапов", "Слепаков", "Жуков", "Бандера", "Петлюра", "Хмельницкий"};
    static int employeeId;
    static Random random = new Random();

    static private void fillEmployee(Employee employee) {

        int firstNameIndex = random.nextInt(firstNames.length - 1);
        int lastNameIndex = random.nextInt(lastNames.length - 1);
        employee.setName(firstNames[firstNameIndex] + " " + lastNames[lastNameIndex]);
        employee.setId(employeeId++);
        employee.setSalary(Math.round(random.nextDouble() * 1000000) / 100.00);
        employee.setGender(Gender.values()[random.nextInt(Gender.values().length)]);
    }

    static private void fillManager(Manager manager) {
    }

    static private void fillDeveloper(d3.employeeGenerator.Developer developer) {
        developer.setFixedBugs(random.nextBoolean());
    }

    static private void fillCleaner(Cleaner cleaner) {
        cleaner.setRate(random.nextDouble());
        int countOfDays = random.nextInt(7);
        DayOfWeek[] dayOfWeeks = new DayOfWeek[countOfDays];

        for (int i = 0; i < countOfDays; i++) {
            dayOfWeeks[i] = DayOfWeek.values()[i];
        }

        cleaner.setWorkedDays(dayOfWeeks);
    }

    static Employee[] generate(int size) {
        Employee[] employees = new Employee[size];

        for (int i = 0; i < size; i++) {
            Employee employee = null;
            switch (random.nextInt(3)) {
                case 0:
                    employee = new Manager();
                    fillEmployee(employee);
                    fillManager((Manager)employee);
                    break;
                case 1:
                    employee = new d3.employeeGenerator.Developer();
                    fillEmployee(employee);
                    fillDeveloper((Developer)employee);
                    break;
                case 2:
                    employee = new Cleaner();
                    fillEmployee(employee);
                    fillCleaner((Cleaner)employee);
                    break;
                default:
                    break;
            }

            employees[i] = employee;
        }

        return employees;
    }
}
