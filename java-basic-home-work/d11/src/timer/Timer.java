package timer;
/*   1. Написать класс Timer которій оперирует двумя параметрами : имя и количество секунд до конца.
        Каждую секунду віводит в консоль информацию о текущем состоянии таймера ->
        имя + количество секунд до финиша. По прошествии времени відает мессадж о конце работы.
        Таймер должен работать асинхронно. Протестировать  приложение с 2-3 таймерами запущенніми параллельно.

*/

public class Timer implements Runnable {
    private String name;
    private int secTillTheEnd;

    public Timer(String name, int secTillTheEnd) {
        this.name = name;
        this.secTillTheEnd = secTillTheEnd;
    }

    @Override
    public void run() {
        for (int i = secTillTheEnd; i > 0; i--) {
            System.out.println(name + ":" + i + " sec");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Stop "+name);
    }
}
