package d3.employeeGeneratorFactory;
//Developer(id,name,age,salary,gender,fixedBugs)
//  Manager(id,name,age,salary,gender)
//  Cleaner(id,name,age,salary,gender,rate,workedDays)


import java.util.Random;

public class MockEmployeesGenerator {

    static Employee[] generate(int size) {
        Employee[] employees = new Employee[size];
        MockEmployeeBuilderFactory factory = new MockEmployeeBuilderFactory();;
        Random random = new Random(3);

        for (int i = 0; i < size; i++) {
            int j = random.nextInt(3);
            //System.out.println(j);
            if (j == 0) {
                employees[i] = factory.createManager(new ManagerBuilderImpl());
            } else if (j == 1) {
                employees[i] = factory.createDeveloper(new DeveloperBuilderImpl());
            } else {
                employees[i] = factory.createCleaner(new CleanerBuilderImpl());
            }

        }

        return employees;
    }


}
