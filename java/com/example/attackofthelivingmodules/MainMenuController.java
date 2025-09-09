package com.example.attackofthelivingmodules;

import com.example.gameplay.Audio;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

import static javafx.application.Platform.exit;

public class MainMenuController extends TopController{

    @FXML
    private ImageView singlePlayerImage;

    @FXML
    private ImageView twoPlayerImage;

    @FXML
    private ImageView leaderboardImage;

    @FXML
    private ImageView exitImage;

    @FXML
    private Button singlePlayerButton;

    @FXML
    private Button twoPlayerButton;

    @FXML
    private Button leaderboardButton;

    @FXML
    private Button exitButton;

    private final Image singlePlayerReleased = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_SinglePlayer_Release.png")));
    private final Image singlePlayerPressed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_SinglePlayer_Pressed.png")));
    private final Image twoPlayerReleased = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_TwoPlayer_Release.png")));
    private final Image twoPlayerPressed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_TwoPlayer_Pressed.png")));
    private final Image leaderboardReleased = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_Leaderboard_Release.png")));
    private final Image leaderboardPressed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_Leaderboard_Pressed.png")));
    private final Image exitReleased = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_Exit_Release.png")));
    private final Image exitPressed = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/EnvironmentAssets/Button_Exit_Pressed.png")));
    public static final Audio audio = new Audio();

    @FXML
    private void onSinglePlayerClick(ActionEvent event) {
        MainApplication app = new MainApplication();
        // Delay
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(e ->{
            try {
                app.changeScene("SinglePlayer.fxml", 0, 0, audio);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }

    @FXML
    private void onTwoPlayerClick(ActionEvent event) {
        MainApplication app = new MainApplication();
        // Delay
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(e ->{
            try {
                app.changeScene("TwoPlayer.fxml", 0, 0, audio);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }

    @FXML
    private void onLeaderboardClick(ActionEvent event) {
        MainApplication app = new MainApplication();
        // Delay
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(e ->{
            try {
                app.changeScene("Leaderboard.fxml", 0, 0, audio);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }

    @FXML
    private void onExitClick(ActionEvent event) {
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(e -> exit());
        pause.play();
    }

    @FXML
    private void singlePlayerPressed() {singlePlayerImage.setImage(singlePlayerPressed);}

    @FXML
    private void singlePlayerReleased() {singlePlayerImage.setImage(singlePlayerReleased);}

    @FXML
    private void twoPlayerPressed() {twoPlayerImage.setImage(twoPlayerPressed);}

    @FXML
    private void twoPlayerReleased() {twoPlayerImage.setImage(twoPlayerReleased);}

    @FXML
    private void leaderboardPressed() {leaderboardImage.setImage(leaderboardPressed);}

    @FXML
    private void leaderboardReleased() {leaderboardImage.setImage(leaderboardReleased);}

    @FXML
    private void exitPressed() {exitImage.setImage(exitPressed);}

    @FXML
    private void exitReleased() {exitImage.setImage(exitReleased);}
}
