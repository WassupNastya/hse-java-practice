package lessons.lesson8;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    final static int[] coord = {610, 510, 410, 310, 210, 110, 10};
    static Elevator[] elevator = {new Elevator(0, 400), new Elevator(1, 600)};

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        for (int i = 0; i < 7; i++) {

            Rectangle rectangle = new Rectangle();
            rectangle.setX(10);
            rectangle.setY(i * 100 + 10);
            rectangle.setWidth(780);
            rectangle.setHeight(80);
            rectangle.setFill(Color.IVORY);
            root.getChildren().add(rectangle);
        }

        for (int i = 0; i < 7; i++) {
            Text text = new Text((7 - i) + " этаж");
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
            text.setFill(Color.CHOCOLATE);
            text.setX(20);
            text.setY(i * 100 + 80);
            root.getChildren().add(text);
        }

        root.getChildren().add(elevator[0].getRectangle());
        root.getChildren().add(elevator[1].getRectangle());

        Scene scene = new Scene(root, 800, 700, Color.BURLYWOOD);
        primaryStage.setTitle("Elevator Managment System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void runElevator(App app, int number){
        elevator[number].acceptApplication();

        SequentialTransition sq = new SequentialTransition();

        if (elevator[number].getCurrentFloor() < app.getFrom()) elevator[number].setCurrentDirection(Direction.UP);
        else  elevator[number].setCurrentDirection(Direction.DOWN);

        sq = runOneFloor(sq, elevator[number].getCurrentFloor(), app.getFrom(), number);
        sq.getChildren().add(stopOnFloor(number));

        elevator[number].setCurrentDirection(app.getDirection());
        sq = runOneFloor(sq, app.getFrom(), app.getTo(), number);
        sq.getChildren().add(stopOnFloor(number));
        sq.play();

        sq.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                elevator[number].becomeFree();
            }
        });
    }

    public static SequentialTransition runOneFloor(SequentialTransition sq, int from, int to, int number) {
        boolean flag;
        if (from < to) flag = true;
        else flag = false;

        int cycle = Math.abs(from - to);
        int value = 0;
        for (int i = 0; i < cycle; i++) {
            if (flag) {
                value = coord[(elevator[number].getCurrentFloor() + 1) - 1];
                elevator[number].setCurrentFloor(elevator[number].getCurrentFloor() + 1);
            }
            else {
                value = coord[(elevator[number].getCurrentFloor() - 1) - 1];
                elevator[number].setCurrentFloor(elevator[number].getCurrentFloor() - 1);
            }

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(elevator[number].getRectangle().yProperty(), value);
            KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
            timeline.getKeyFrames().add(kf);

            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    Double cf = 7 - ((elevator[number].getRectangle().getY() - 10) / 100);
                    elevator[number].setCurrentFloor(cf.intValue());
                    if (elevator[number].getSet().contains(cf.intValue())) {
                        (stopOnFloor(number)).play();
                        elevator[number].getSet().remove(cf.intValue());
                    }
                }
            });
            sq.getChildren().add(timeline);
        }

        return sq;
    }

    public static FillTransition stopOnFloor(int number) {
        FillTransition ft = new FillTransition(Duration.millis(500), elevator[number].getRectangle(),
                Color.DARKGRAY, Color.CHARTREUSE);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                elevator[number].getRectangle().setFill(Color.DARKGRAY);
            }
        });

        return ft;
    }

    public static void main(String[] args) {
        ElevatorManagmentSystem ems = new ElevatorManagmentSystem();
        ems.go(3);
    }
}
