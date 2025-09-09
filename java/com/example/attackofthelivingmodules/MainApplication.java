package com.example.attackofthelivingmodules;

import com.example.gameplay.Audio;
import com.example.gameplay.SinglePlayerGameplay;
import com.example.gameplay.TwoPlayerGameplay;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    private static Stage globStage;

    @Override
    public void start(Stage stage) throws IOException {
        globStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Attack on the Living Modules");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Object controller = fxmlLoader.getController();
        ((MainMenuController) controller).audio.playMainMenuMusic();
    }

    public void changeScene(String file, int score, int numberOfPlayers, Audio audio) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load());
        globStage.setScene(scene);
        Object controller = fxmlLoader.getController();
        if (controller instanceof GameplayController) {
            audio.stopMainMenuMusic();
            ((GameplayController) controller).initialize(file);
            if (file.equals("SinglePlayer.fxml")) {SinglePlayerGameplay singlePlayerGameplay = new SinglePlayerGameplay(globStage.getScene(), ((GameplayController) controller), audio);}
            else {TwoPlayerGameplay twoPlayerGameplay = new TwoPlayerGameplay(globStage.getScene(), ((GameplayController) controller), audio);}
        }
        else if (controller instanceof EnterNameController) ((EnterNameController) controller).initialize(50, score, numberOfPlayers, audio);
        else if (controller instanceof LeaderboardController) ((LeaderboardController) controller).initialize(40, audio);
        else ((TopController) controller).initialize(50, audio);
    }

    public static void main(String[] args) {
        launch();
    }
}