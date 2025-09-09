package com.example.attackofthelivingmodules;

import com.example.gameplay.Audio;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TopController {
    @FXML
    protected VBox root;

    protected Audio audio;

    public void initialize(double fontSize, Audio audio) {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/com/example/attackofthelivingmodules/assets/Fonts/pkmnrs.ttf"), fontSize);
        applyCustomFontToLabels(root, customFont);
        this.audio = audio;
    }

    protected void applyCustomFontToLabels(Parent parent, Font font) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof Label) {
                ((Label) node).setFont(font);
            } else if (node instanceof Parent) {
                applyCustomFontToLabels((Parent) node, font);
            }
        }
    }
}
