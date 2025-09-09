package com.example.attackofthelivingmodules;

import com.example.gameplay.Audio;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class EnterNameController extends TopController {

    private final Image submitReleased = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_Submit_Release.png")));
    private final Image submitPressed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_Submit_Pressed.png")));
    private int score;
    private int numberOfPlayers;
    private String fileName;
    private final List<Scores> ranking = new ArrayList<>();

    @FXML
    private VBox root;

    @FXML
    private TextField textField;

    @FXML
    private ImageView submitImage;

    @FXML
    private Button submitButton;

    @FXML
    private void onSubmitButtonClicked(ActionEvent event) {
        updateRankings();
        MainApplication app = new MainApplication();
        // Delay
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(e -> {
            try {
                app.changeScene("Leaderboard.fxml", score, numberOfPlayers, audio);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }

    @FXML
    private void submitButtonPressed() {
        submitImage.setImage(submitPressed);
    }

    @FXML
    private void submitButtonReleased() {
        submitImage.setImage(submitReleased);
    }

    @FXML
    private void onTextFieldComma(KeyEvent e) {
        textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (event.getCharacter().equals(",")) {
                event.consume();
            }
        });
    }

    public void initialize(int fontSize, int score, int numberOfPlayers, Audio audio) {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Fonts/pkmnrs.ttf"), fontSize);
        applyCustomFontToLabels(root, customFont);
        this.audio = audio;
        this.score = score;
        this.numberOfPlayers = numberOfPlayers;
        if (numberOfPlayers == 1) fileName = "singlePlayerLeaderboard.txt";
        else fileName = "twoPlayerLeaderboard.txt";
        readFile(fileName);
        if (ranking.get(4).getScore() >= score) {
            MainApplication app = new MainApplication();
            // Delay
            PauseTransition pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(e -> {
                try {
                    app.changeScene("Leaderboard.fxml", 0, 0, audio);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();
        }
    }


    private void updateRankings() {
        ranking.remove(4);
        ranking.add(new Scores(textField.getText(), score));
        for (int i = 4; i >= 0; i--) {
            for (int j = 4; j >= 1; j--) {
                if (ranking.get(j).getScore() > ranking.get(j - 1).getScore()) {
                    Collections.swap(ranking, j, j - 1);
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter("target/classes/com/example/attackofthelivingmodules/assets/" + fileName))) {
            for (Scores scores : ranking) {
                writer.println(scores.getName() + "," + scores.getScore());
            }
        } catch (IOException e) {
            System.out.println("Some Error Occurred LOL!");
        }
    }


    private void readFile(String fileName) {
        File file = new File("target/classes/com/example/attackofthelivingmodules/assets/" + fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] arr = line.split(",");
                ranking.add(new Scores(arr[0], Integer.parseInt(arr[1])));
            }
        } catch (Exception e) {
            System.out.println("Some Error Occurred LOL!");
        }
    }
}


