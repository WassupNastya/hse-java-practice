package lessons.lesson8;

public class App {
    private int from;
    private int to;
    private Direction direction;

    public App(int from, int to) {
        this.from = from;
        this.to = to;
        if (from < to) this.direction = Direction.UP;
        else this.direction = Direction.DOWN;
    }

    public int getFrom() { return from; }
    public int getTo() { return to; }
    public Direction getDirection() { return direction; }

    @Override
    public String toString() {
        return ("Заявка с " + from + " на " + to + " этаж");
    }
}
