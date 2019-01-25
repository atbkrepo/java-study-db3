package d3.employeeGenerator;

public class Developer extends Employee {
    private boolean fixedBugs;

    public boolean isFixedBugs() {
        return fixedBugs;
    }

    public void setFixedBugs(boolean fixedBugs) {
        this.fixedBugs = fixedBugs;
    }

    @Override
    public String toString() {
        return
                "Developer{" +super.toString()+
                " ,fixedBugs=" + fixedBugs +
                '}';
    }
}
