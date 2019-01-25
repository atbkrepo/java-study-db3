package d3.employeeGeneratorFactory;


import java.time.DayOfWeek;
import java.util.Arrays;

public class Cleaner extends Employee {
    private double rate;
    private DayOfWeek[] workedDays;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public DayOfWeek[] getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(DayOfWeek[] workedDays) {
        this.workedDays = workedDays;
    }

    @Override
    public String toString() {
        return  "Cleaner{  " +
                super.toString()+
                ", rate=" + rate +
                ", workedDays=" + Arrays.toString(workedDays) +
                '}';
    }
}
