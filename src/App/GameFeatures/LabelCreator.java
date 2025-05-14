package app.gameplayFeatures;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabelCreator {

    public static Label createLabel(double x, double y, String text, Color color, Font font) {
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setTextFill(color);
        label.setFont(font);
        return label;
    }
}
