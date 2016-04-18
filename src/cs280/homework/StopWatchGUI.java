package cs280.homework;

/*Import Statements*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StopWatchGUI extends Application {
    private String goalName = null;
    private int goalHour = 0;
    private int goalMin = 0;
    private int goalSec = 0;

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
        sideScreen.setAlignment(Pos.TOP_CENTER);
        Text sidelabel = new Text("Create a New Goal \n");
        sidelabel.setFill(Color.WHITE);
        sidelabel.setStyle("-fx-font-size: 20px;");
        Button sideButton = new Button("New Goal");
        sideButton.setStyle("-fx-min-height: 20px; -fx-font-size: 18px;");
        sideScreen.getChildren().addAll(sidelabel, sideButton);
        mainScreen.setLeft(sideScreen);

        //Setting up initial centerscene - GoalSetting
        StackPane frontScreen = new StackPane();
        frontScreen.setStyle("-fx-background-color: #5e5e63;");

        VBox goalSetting = new VBox();
        goalSetting.setAlignment(Pos.TOP_CENTER);
        goalSetting.setPadding(new Insets(30,30,50,30));

        //Instructions for Use
        Text directions = new Text("Welcome to ClockGoals. In the following fields, enter the name of your goal and \n how long you need to complete the goal in the format HH:MM:SS");
        directions.setStyle("-fx-font-size: 18px; -fx-text-alignment: center;");

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

        //Text that just exists to creat some space between items in the vBox
        Text spaceText = new Text(" ");
        spaceText.setStyle("-fx-font-size: 25px;");

        //Submit Button for Goals
        Button submitGoal = new Button("Submit");
        submitGoal.setStyle("-fx-min-height: 50px; -fx-font-size: 30px;");

        //Text that displays if there is an error
        Text errorText = new Text("");
        errorText.setStyle("-fx-font-size: 20px;");
        errorText.setFill(Color.DARKRED);

        //Adds the elements of the goalSetting VBOX
        goalSetting.getChildren().addAll(directions, goalLabel, goal, goalTimeLabel, goalTime, spaceText, submitGoal, errorText);

        //Creating the StopWatch Screen StackPane
        StackPane WatchScreen = new StackPane();

        //Creating the StopWatch object
        StopWatch goalwatch = new StopWatch();

        //Creating VBox for various text displays. The goal name, goal time and the time display itself.
        VBox TextDisplays = new VBox();
        TextDisplays.setPadding(new Insets(50, 0, 0, 0));
        TextDisplays.setAlignment(Pos.TOP_CENTER);
        Text GoalName = new Text(goalName);
        GoalName.setStyle("-fx-font-size: 25px;");

        Text GoalTime = new Text(goalHour + ":" + goalMin + ":" + goalSec);
        GoalTime.setStyle("-fx-font-size: 25px;");

        Text WatchTime = new Text(goalwatch.toString());
        WatchTime.setStyle("-fx-font-size: 125px;");

        //Adding the goal name, goal text and watch time to the TextDisplays layout
        TextDisplays.getChildren().addAll(GoalName, GoalTime, WatchTime);

        //Creating the HBOX for stop/start, lap and reset buttons
        HBox ButtonBox = new HBox();
        ButtonBox.setAlignment(Pos.TOP_CENTER);
        ButtonBox.setPadding(new Insets(260, 0, 0, 0));

        //Creating the HBOX for Lap Results
        HBox LapResults = new HBox();
        LapResults.setAlignment(Pos.TOP_CENTER);
        LapResults.setPadding(new Insets(500, 0, 0, 0));
        TextArea LapText = new TextArea();
        LapText.setEditable(false);
        LapText.setStyle("-fx-font-size: 30px;");
        LapText.setWrapText(false);
        LapResults.getChildren().addAll(LapText);

        Button Lap = new Button("Lap");
        Lap.setMinHeight(60);
        Lap.setStyle("-fx-font-size: 40px;");
        Lap.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                LapText.appendText("\n" + goalwatch.toString());
            }
        });


        Button Reset = new Button ("Reset");
        Reset.setMinHeight(60);
        Reset.setStyle("-fx-font-size: 40px;");
        Reset.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent f){
                goalwatch.reset();
                WatchTime.setText("00:00:00");
            }
        });

        Button StopStart = new Button("Start/Stop");
        StopStart.setMinHeight(60);
        StopStart.setStyle("-fx-font-size: 40px;");
        StopStart.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(!goalwatch.isRunning()) {
                    goalwatch.start();
                    Lap.setDisable(false);
                    Reset.setDisable(true);
                }
                else {
                    Lap.setDisable(true);
                    Reset.setDisable(false);
                    goalwatch.stop();
                    System.out.println("Works");
                }
            }
        });


        //Event when submitting text.
        submitGoal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    goalName = goal.getText();
                    goalHour = Integer.parseInt(goalTime.getText().substring(0, 2));
                    goalMin = Integer.parseInt(goalTime.getText().substring(3, 5));
                    goalSec = Integer.parseInt(goalTime.getText().substring(6, 8));
                    String timeString = String.format("%02d:%02d:%02d", goalHour, goalMin, goalSec);

                    GoalName.setText(goalName);
                    GoalTime.setText(timeString);
                    mainScreen.setCenter(WatchScreen);
                }
                catch(Exception y){
                    errorText.setText("Please Try Entering Your Goal Or Time Again.");
                }
            }
        });

        //SidePanel Going to a New Goal
        sideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                goalwatch.reset();
                mainScreen.setCenter(frontScreen);
                goal.setText("");
                goalTime.setText("");
            }
        });

        //Events taking place constantly
        goalwatch.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                WatchTime.setText(goalwatch.toString());

               //Changes color based on if goal reached
                if(goalwatch.getElapsed() <= goalHour*3600+goalMin*60+goalSec)
                    WatchTime.setFill(Color.FORESTGREEN);
                else
                    WatchTime.setFill(Color.RED);

            }
        });

        ButtonBox.getChildren().addAll(StopStart, Lap, Reset);

        WatchScreen.getChildren().addAll(LapResults, TextDisplays, ButtonBox);


        //Adds the goalSetting VBOX and submitBox VBOX to frontScreen. frontScreen is then added to the mainScreen's center.
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

