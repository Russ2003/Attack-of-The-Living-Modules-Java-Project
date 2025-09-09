package com.example.attackofthelivingmodules;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class GameplayController extends TopController{

    @FXML
    private Canvas canvas;

    @FXML
    private Label score;

    @FXML
    private Label lives1;

    @FXML
    private Label lives2;

    @FXML
    private Label stage;

    public void setScore(int playerScore) {score.setText(String.valueOf(playerScore));}

    public void setLives1(int playerLives) {lives1.setText(String.valueOf(playerLives));}

    public void setLives2(int playerLives) {lives2.setText(String.valueOf(playerLives));}

    public void setStage(int currentStage) {stage.setText(String.valueOf(currentStage));}

    public int getScore() {return Integer.valueOf(score.getText()).intValue();}

    public int getStage() {return Integer.valueOf(stage.getText()).intValue();}

    public Canvas getCanvas() {return canvas;}

    public void initialize(String file) {
        Font customFont;
        if (file.equals("TwoPlayer.fxml")) customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Fonts/pkmnrs.ttf"), 35);
        else customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Fonts/pkmnrs.ttf"), 50);
        applyCustomFontToLabels(root, customFont);
    }
}
