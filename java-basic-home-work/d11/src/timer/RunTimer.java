package timer;

public class RunTimer {
    public static void main(String[] args) {
        Timer timerFirst = new Timer("First Timer", 50);
        Timer timerSecond = new Timer("Second Timer", 30);
        Timer timerThird = new Timer("Third Timer", 10);

        Timer[] timers = {timerFirst, timerSecond, timerThird};
        for (Timer timer : timers) {
            Thread thread = new Thread(timer);
            thread.start();
        }
    }


}
