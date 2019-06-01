package lessons.lesson8;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.HashSet;
import static lessons.lesson8.Direction.UP;

public class Elevator {
    private Direction currentDirection;
    private int currentFloor;
    private Rectangle rectangle;
    private boolean hasApplication;
    private int number;
    private HashSet<Integer> stops;
    private int from, to;

    public int getFrom() {return from;}

    public int getTo() {return to;}

    public Elevator(int number, int position) {
        rectangle = new Rectangle(position, 610, 80, 80);
        rectangle.setFill(Color.DARKGRAY);
        currentDirection = UP;
        currentFloor = 1;
        hasApplication = false;
        this.number = number;
        stops = new HashSet<>();
        this.to = 0;
        this.from = 0;
    }

    public void addApplication(App app) {
        this.from = app.getFrom();
        this.to = app.getTo();
    }

    public void removeApplication(App app) {
        this.from = 0;
        this.to = 0;
    }

    public void addAdditionalApplication(App app) {
        stops.add(app.getFrom());
        stops.add(app.getTo());
    }

    public void removeAdditionalApplication(int floor) {
        stops.remove(floor);
    }

    public int getSize() { return stops.size(); }

    public HashSet<Integer> getSet() { return stops; }

    public void setCurrentDirection(Direction d) { currentDirection  = d; }

    public Direction getCurrentDirection() { return currentDirection; }

    public int getCurrentFloor() { return currentFloor; }

    public Rectangle getRectangle() {return rectangle;}

    public void setCurrentFloor(int floor) { currentFloor = floor;}

    public void acceptApplication() {
        hasApplication = true;
    }

    public void becomeFree() { hasApplication = false; }

    public boolean hasApplication() { return hasApplication; }

    public int getNumber() { return number; }

}
