package com.example.guessthenumber;

import javafx.application.Application;
import javafx.stage.Stage;

/* ChatGPT was used interactively to assist in editing the text. I kept getting small errors and used ChatGPT to find the errors in my syntax and fix my code.
 I, Jackson Hammond, have carefully reviewed said te xt and take ultimate responsibility for the content of this code.*/

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
