package lessons.lesson8;


public class ApplicationProcessor implements Runnable  {
    ElevatorManagmentSystem ems;

    public ApplicationProcessor(ElevatorManagmentSystem ems) {
        this.ems = ems;
    }

    @Override
    public void run() {
        try {
            ems.consume();
        }
        catch (InterruptedException e) {
            System.out.println("Поток обработки заявок прерван");
        }
    }
}
