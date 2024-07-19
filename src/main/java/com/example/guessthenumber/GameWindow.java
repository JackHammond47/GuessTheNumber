package com.example.guessthenumber;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends Application {
    private int secretNumber;
    private int maxAttempts;
    private int attempts;
    private Random random = new Random();
    private Label messageLabel;
    private TextField guessInput;
    private Button guessButton;
    private TextArea historyArea;
    private List<String> guessHistory = new ArrayList<>();
    private int guessCount = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guess the Number Game");

        // Difficulty level selection
        VBox difficultyLayout = new VBox();
        Label difficultyLabel = new Label("Choose difficulty level:");
        Button easyButton = new Button("Easy (0-50, 10 attempts)");
        Button mediumButton = new Button("Medium (0-100, 7 attempts)");
        Button hardButton = new Button("Hard (0-500, 5 attempts)");

        difficultyLayout.getChildren().addAll(difficultyLabel, easyButton, mediumButton, hardButton);

        easyButton.setOnAction(e -> startGame(50, 10, primaryStage));
        mediumButton.setOnAction(e -> startGame(100, 7, primaryStage));
        hardButton.setOnAction(e -> startGame(500, 5, primaryStage));

        Scene difficultyScene = new Scene(difficultyLayout, 300, 200);
        primaryStage.setScene(difficultyScene);
        primaryStage.show();
    }

    private void startGame(int maxNumber, int maxAttempts, Stage primaryStage) {
        this.secretNumber = random.nextInt(maxNumber + 1);
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.guessHistory.clear();
        this.guessCount = 1;

        VBox gameLayout = new VBox();
        messageLabel = new Label("Try to guess the number between 0 and " + maxNumber + ".");
        guessInput = new TextField();
        guessButton = new Button("Guess");
        historyArea = new TextArea();
        historyArea.setEditable(false);

        guessButton.setOnAction(e -> processGuess());
        guessInput.setOnAction(e -> processGuess()); // Handle Enter key

        gameLayout.getChildren().addAll(messageLabel, guessInput, guessButton, historyArea);

        Scene gameScene = new Scene(gameLayout, 300, 300);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    private void processGuess() {
        try {
            int guess = Integer.parseInt(guessInput.getText());
            String feedback;
            attempts++;

            if (guess == secretNumber) {
                feedback = "Congratulations! You guessed the number in " + attempts + " attempts.";
                guessButton.setDisable(true);
                guessInput.setDisable(true);
            } else if (attempts >= maxAttempts) {
                feedback = "Sorry, you ran out of attempts. The number was " + secretNumber + ".";
                guessButton.setDisable(true);
                guessInput.setDisable(true);
            } else if (guess < secretNumber) {
                feedback = "Higher...";
            } else {
                feedback = "Lower...";
            }

            // Update guess history
            guessHistory.add("Guess " + guessCount + ": " + guess + " (" + feedback + ")");
            historyArea.setText(String.join("\n", guessHistory));

            // Clear input field
            guessInput.clear();
            guessCount++;

            // Update message label
            messageLabel.setText(feedback);

        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number.");
        }
    }
}
