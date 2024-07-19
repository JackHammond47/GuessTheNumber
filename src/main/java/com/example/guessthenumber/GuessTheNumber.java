package com.example.guessthenumber;

import javafx.application.Application;
import javafx.stage.Stage;

public class GuessTheNumber extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.start(primaryStage);
    }
}
