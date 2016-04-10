package cs280.homework;

/*Import Statements*/
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StopWatchGUI extends Application {
    @Override
    public void start(Stage mainStage) throws Exception{

        //Create a borderpane, and an hbox within the borderpane.
        BorderPane mainScreen = new BorderPane();
        HBox topbox = new HBox();

        //Text for Top
        Text headerlabel = new Text("ClockGoals");
        headerlabel.setStyle("-fx-font-size: 50px;");
        headerlabel.setFill(Color.WHITE);

        //hbox top formatting
        topbox.setStyle("-fx-background-color: #274689; -fx-text-alignment: center;");
        topbox.setSpacing(10);
        topbox.getChildren().addAll(headerlabel);
        topbox.setAlignment(Pos.CENTER);
        mainScreen.setTop(topbox);

        //Setting up the sidepane
        VBox sideScreen = new VBox();
        sideScreen.setStyle("-fx-background-color: #3d3d42; -fx-border-color: #000000; -fx-border-width: 4px;");
        Text sidelabel = new Text("Create a New Goal \n");
        sidelabel.setFill(Color.WHITE);
        sidelabel.setStyle("-fx-font-size: 20px;");
        Button sideButton = new Button("New Goal");
        sideButton.setStyle("-fx-min-height: 20px; -fx-font-size: 18px; -fx-background-color: #274689;");
        sideScreen.getChildren().addAll(sidelabel, sideButton);
        mainScreen.setLeft(sideScreen);

        //Setting up initial centerscene - GoalSetting
        StackPane frontScreen = new StackPane();
        frontScreen.setStyle("-fx-background-color: #5e5e63;");

        VBox goalSetting = new VBox();
        goalSetting.setAlignment(Pos.TOP_CENTER);
        goalSetting.setPadding(new Insets(30,30,50,30));

        //Label for Goal Name
        Label goalLabel = new Label("Goal Name: \n");
        goalLabel.setStyle("-fx-font-size: 35px;");

        //Field for goal text
        TextField goal = new TextField();
        goal.setMaxWidth(550);
        goal.setStyle("-fx-font-size: 25px;");

        //Label for Goal Time
        Label goalTimeLabel = new Label("Goal Time: \n");
        goalTimeLabel.setStyle("-fx-font-size: 35px;");

        //Field for goal Time
        TextField goalTime = new TextField();
        goalTime.setMaxWidth(150);
        goalTime.setStyle("-fx-font-size: 25px;");

        //Adds the elements of the goalSetting VBOX
        goalSetting.getChildren().addAll(goalLabel, goal, goalTimeLabel, goalTime);

        //Adds the goalSetting VBOX to frontScreen. frontScreen is then added to the mainScreen's center.
        frontScreen.getChildren().addAll(goalSetting);
        mainScreen.setCenter(frontScreen);

        //Setting the frontScene
        Scene front = new Scene(mainScreen, 1000, 750);

        //Set the title, the primary stage and shows the primary stage.
        mainStage.setTitle("ClockGoals Stopwatch");
        mainStage.setScene(front);
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

