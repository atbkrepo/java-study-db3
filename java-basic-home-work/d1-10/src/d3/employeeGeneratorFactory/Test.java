package d3.employeeGeneratorFactory;

public class Test {
    static void print(Object[] objects) {
        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }

    public static void main(String[] args) {
        Employee[] employees = MockEmployeesGenerator.generate(10);

        print(employees);
    }
}
