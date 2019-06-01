package lessons.lesson8;

public class ApplicationGenerator implements Runnable {
    ElevatorManagmentSystem ems;

    public ApplicationGenerator (ElevatorManagmentSystem ems) {
        this.ems = ems;
    }

    @Override
    public void run() {
        try {
            ems.produce();
        }
        catch (InterruptedException e) {
            System.out.println("Поток приема заявок прерван");
        }
    }
}