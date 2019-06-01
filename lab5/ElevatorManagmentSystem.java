package lessons.lesson8;

import javafx.application.Application;
import java.util.LinkedList;
import java.util.Random;

import static lessons.lesson8.Main.*;

public class ElevatorManagmentSystem {
    LinkedList<App> applicationsList;
    int capacity;
    Random random;

    public void go(int capacity) {
        this.applicationsList = new LinkedList<>();
        this.random = new Random();
        this.capacity = capacity;

        try {
            Thread generator = new Thread(new ApplicationGenerator(this));
            Thread processor = new Thread(new ApplicationProcessor(this));

            generator.start();
            processor.start();

            Application.launch(Main.class);

            generator.join();
            processor.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while ((applicationsList.size() == capacity)) { wait(); }

                App app;

                while (true) {
                    app = new App(1 + random.nextInt(6), 1 + random.nextInt(6));
                    if (app.getFrom() != app.getTo()) break;
                }

                applicationsList.add(app);
                System.out.println("\n--- " + app.toString() + " принята ---\n");

                notify();
                Thread.sleep(2000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (applicationsList.size() == 0) { wait(); }
                System.out.println("\n--- " + applicationsList.getFirst().toString() + " отдана на обработку ---\n");
                int choice = chooseElevator(applicationsList.getFirst());
                if (choice != -1) {
                    elevator[choice].addApplication(applicationsList.getFirst());
                    runElevator(applicationsList.getFirst(), choice);
                }
                applicationsList.removeFirst();
                notify();
                Thread.sleep(2000);
            }
        }
    }

    public int chooseElevator(App app) throws InterruptedException {
        while (true) {
            if (Math.abs(elevator[0].getCurrentFloor() - app.getFrom())
                    <= Math.abs(elevator[1].getCurrentFloor() - app.getFrom())) {
                if (!elevator[0].hasApplication()) return 0;
                else if (checkForAdd(app, 0, Direction.UP)) {
                    elevator[0].addAdditionalApplication(app);
                    return -1;
                }
                else  if (checkForAdd(app, 0, Direction.DOWN)) {
                    elevator[0].addAdditionalApplication(app);
                    return -1;
                }
                else if (!elevator[1].hasApplication()) return 1;
                else Thread.sleep(1000);
            }
            else {
                if (!elevator[1].hasApplication()) return 1;
                else if (checkForAdd(app, 1, Direction.UP)) {
                    elevator[1].addAdditionalApplication(app);
                    return -1;
                }
                else if (checkForAdd(app, 1, Direction.DOWN)) {
                    elevator[1].addAdditionalApplication(app);
                    return -1;
                }
                else if (!elevator[0].hasApplication()) return 0;
                else Thread.sleep(1000);
            }
            }
    }

    public boolean checkForAdd(App app, int number, Direction direction) {
        if (direction == Direction.UP) {
            if ((elevator[number].hasApplication()) &&
                    (elevator[number].getCurrentDirection() == Direction.UP) &&
                    (elevator[number].getTo() != 0) &&
                    (app.getFrom() > elevator[number].getCurrentFloor()) &&
                    (app.getTo() < elevator[number].getTo()) &&
                    (app.getDirection() == elevator[number].getCurrentDirection()))
                return true;
            else return false;
        }
        else {
            if ((elevator[0].hasApplication()) &&
                    (elevator[number].getCurrentDirection() == Direction.DOWN) &&
                    (elevator[number].getTo() != 0) &&
                    (app.getFrom() < elevator[number].getCurrentFloor()) &&
                    (app.getTo() > elevator[number].getTo()) &&
                    (app.getDirection() == elevator[number].getCurrentDirection()))
                return true;
            else return false;
        }
    }

}
