package com.example.attackofthelivingmodules;

import com.example.gameplay.Audio;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LeaderboardController extends TopController{

    private final Image mainMenuReleased = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_MainMenu_Release.png")));
    private final Image mainMenuPressed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_MainMenu_Pressed.png")));
    private final List<Scores> singleRanking = new ArrayList<>();
    private final List<Scores> twoRanking = new ArrayList<>();

    @FXML
    private Label singleName1;

    @FXML
    private Label singleName2;

    @FXML
    private Label singleName3;

    @FXML
    private Label singleName4;

    @FXML
    private Label singleName5;

    @FXML
    private Label singleScore1;

    @FXML
    private Label singleScore2;

    @FXML
    private Label singleScore3;

    @FXML
    private Label singleScore4;

    @FXML
    private Label singleScore5;

    @FXML
    private Label twoName1;

    @FXML
    private Label twoName2;

    @FXML
    private Label twoName3;

    @FXML
    private Label twoName4;

    @FXML
    private Label twoName5;

    @FXML
    private Label twoScore1;

    @FXML
    private Label twoScore2;

    @FXML
    private Label twoScore3;

    @FXML
    private Label twoScore4;

    @FXML
    private Label twoScore5;

    @FXML
    private ImageView mainMenuImage;

    @FXML
    private Button mainMenuButton;

    @FXML
    private void mainMenuButtonPressed() {mainMenuImage.setImage(mainMenuPressed);}

    @FXML
    private void mainMenuButtonReleased() {mainMenuImage.setImage(mainMenuReleased);}

    @FXML
    private void onMainMenuButtonPressed(ActionEvent event) {
        MainApplication app = new MainApplication();
        // Delay
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(e ->{
            try {
                app.changeScene("MainMenu.fxml", 0, 0, audio);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }

    private void readFile(String fileName, List<Scores> ranking) {
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

    @Override
    public void initialize(double fontSize, Audio audio) {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Fonts/pkmnrs.ttf"), fontSize);
        applyCustomFontToLabels(root, customFont);
        readFile("singlePLayerLeaderboard.txt", singleRanking);
        readFile("twoPlayerLeaderboard.txt", twoRanking);
        setLabels(singleName1, singleScore1, singleRanking, 0);
        setLabels(singleName2, singleScore2, singleRanking, 1);
        setLabels(singleName3, singleScore3, singleRanking, 2);
        setLabels(singleName4, singleScore4, singleRanking, 3);
        setLabels(singleName5, singleScore5, singleRanking, 4);
        setLabels(twoName1, twoScore1, twoRanking, 0);
        setLabels(twoName2, twoScore2, twoRanking, 1);
        setLabels(twoName3, twoScore3, twoRanking, 2);
        setLabels(twoName4, twoScore4, twoRanking, 3);
        setLabels(twoName5, twoScore5, twoRanking, 4);
    }

    private void setLabels(Label name, Label score, List<Scores> scores, int index) {
        name.setText(scores.get(index).getName());
        score.setText(Integer.toString(scores.get(index).getScore()));
    }
}
