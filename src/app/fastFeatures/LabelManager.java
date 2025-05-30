package app.fastFeatures;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabelManager {

    public static Label createLabel(double x, double y, String text, Color color, Font font) {
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setTextFill(color);
        label.setFont(font);
        return label;
    }

    public static void labelDisabler(Label lbl) {
        lbl.setDisable(true);

    }

    public static void labelDisabler(Label lbl, Label lbl1) {
        lbl.setDisable(true);
        lbl1.setDisable(true);
    }

    public static void labelDisabler(Label lbl, Label lbl1, Label lbl2) {
        lbl.setDisable(true);
        lbl1.setDisable(true);
        lbl2.setDisable(true);
    }

    public static void labelDisabler(Label lbl, Label lbl1, Label lbl2, Label lbl3) {
        lbl.setDisable(true);
        lbl1.setDisable(true);
        lbl2.setDisable(true);
        lbl3.setDisable(true);
    }

    public static void labelDisabler(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4) {
        lbl.setDisable(true);
        lbl1.setDisable(true);
        lbl2.setDisable(true);
        lbl3.setDisable(true);
        lbl4.setDisable(true);
    }

    public static void labelDisabler(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5) {
        lbl.setDisable(true);
        lbl1.setDisable(true);
        lbl2.setDisable(true);
        lbl3.setDisable(true);
        lbl4.setDisable(true);
        lbl5.setDisable(true);
    }

    public static void labelDisabler(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5, Label lbl6) {
        lbl.setDisable(true);
        lbl1.setDisable(true);
        lbl2.setDisable(true);
        lbl3.setDisable(true);
        lbl4.setDisable(true);
        lbl5.setDisable(true);
        lbl6.setDisable(true);
    }

    public static void labelEnabler(Label lbl) {
        lbl.setDisable(false);

    }

    public static void labelEnabler(Label lbl, Label lbl1) {
        lbl.setDisable(false);
        lbl1.setDisable(false);
    }

    public static void labelEnabler(Label lbl, Label lbl1, Label lbl2) {
        lbl.setDisable(false);
        lbl1.setDisable(false);
        lbl2.setDisable(false);
    }

    public static void labelEnabler(Label lbl, Label lbl1, Label lbl2, Label lbl3) {
        lbl.setDisable(false);
        lbl1.setDisable(false);
        lbl2.setDisable(false);
        lbl3.setDisable(false);
    }

    public static void labelEnabler(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4) {
        lbl.setDisable(false);
        lbl1.setDisable(false);
        lbl2.setDisable(false);
        lbl3.setDisable(false);
        lbl4.setDisable(false);
    }

    public static void labelEnabler(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5) {
        lbl.setDisable(false);
        lbl1.setDisable(false);
        lbl2.setDisable(false);
        lbl3.setDisable(false);
        lbl4.setDisable(false);
        lbl5.setDisable(false);
    }

    public static void labelEnabler(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5, Label lbl6) {
        lbl.setDisable(false);
        lbl1.setDisable(false);
        lbl2.setDisable(false);
        lbl3.setDisable(false);
        lbl4.setDisable(false);
        lbl5.setDisable(false);
        lbl6.setDisable(false);
    }

    public static void labelInvisibilizer(Label lbl) {
        lbl.setDisable(false);
        lbl.setVisible(false);

    }

    public static void labelInvisibilizer(Label lbl, Label lbl1) {
        lbl.setDisable(false);
        lbl.setVisible(false);
        lbl1.setDisable(false);
        lbl1.setVisible(false);
    }

    public static void labelInvisibilizer(Label lbl, Label lbl1, Label lbl2) {
        lbl.setDisable(false);
        lbl.setVisible(false);
        lbl1.setDisable(false);
        lbl1.setVisible(false);
        lbl2.setDisable(false);
        lbl2.setVisible(false);
    }

    public static void labelInvisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3) {
        lbl.setDisable(false);
        lbl.setVisible(false);
        lbl1.setDisable(false);
        lbl1.setVisible(false);
        lbl2.setDisable(false);
        lbl2.setVisible(false);
        lbl3.setDisable(false);
        lbl3.setVisible(false);
    }

    public static void labelInvisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4) {
        lbl.setDisable(false);
        lbl.setVisible(false);
        lbl1.setDisable(false);
        lbl1.setVisible(false);
        lbl2.setDisable(false);
        lbl2.setVisible(false);
        lbl3.setDisable(false);
        lbl3.setVisible(false);
        lbl4.setDisable(false);
        lbl4.setVisible(false);
    }

    public static void labelInvisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5) {
        lbl.setDisable(false);
        lbl.setVisible(false);
        lbl1.setDisable(false);
        lbl1.setVisible(false);
        lbl2.setDisable(false);
        lbl2.setVisible(false);
        lbl3.setDisable(false);
        lbl3.setVisible(false);
        lbl4.setDisable(false);
        lbl4.setVisible(false);
        lbl5.setDisable(false);
        lbl5.setVisible(false);
    }

    public static void labelInvisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5, Label lbl6) {
        lbl.setDisable(false);
        lbl.setVisible(false);
        lbl1.setDisable(false);
        lbl1.setVisible(false);
        lbl2.setDisable(false);
        lbl2.setVisible(false);
        lbl3.setDisable(false);
        lbl3.setVisible(false);
        lbl4.setDisable(false);
        lbl4.setVisible(false);
        lbl5.setDisable(false);
        lbl5.setVisible(false);
        lbl6.setDisable(false);
        lbl6.setVisible(false);
    }

    public static void labelVisibilizer(Label lbl) {
        lbl.setDisable(false);
        lbl.setVisible(true);

    }

    public static void labelVisibilizer(Label lbl, Label lbl1) {
        lbl.setDisable(false);
        lbl.setVisible(true);
        lbl1.setDisable(false);
        lbl1.setVisible(true);
    }

    public static void labelVisibilizer(Label lbl, Label lbl1, Label lbl2) {
        lbl.setDisable(false);
        lbl.setVisible(true);
        lbl1.setDisable(false);
        lbl1.setVisible(true);
        lbl2.setDisable(false);
        lbl2.setVisible(true);
    }

    public static void labelVisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3) {
        lbl.setDisable(false);
        lbl.setVisible(true);
        lbl1.setDisable(false);
        lbl1.setVisible(true);
        lbl2.setDisable(false);
        lbl2.setVisible(true);
        lbl3.setDisable(false);
        lbl3.setVisible(true);
    }

    public static void labelVisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4) {
        lbl.setDisable(false);
        lbl.setVisible(true);
        lbl1.setDisable(false);
        lbl1.setVisible(true);
        lbl2.setDisable(false);
        lbl2.setVisible(true);
        lbl3.setDisable(false);
        lbl3.setVisible(true);
        lbl4.setDisable(false);
        lbl4.setVisible(true);
    }

    public static void labelVisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5) {
        lbl.setDisable(false);
        lbl.setVisible(true);
        lbl1.setDisable(false);
        lbl1.setVisible(true);
        lbl2.setDisable(false);
        lbl2.setVisible(true);
        lbl3.setDisable(false);
        lbl3.setVisible(true);
        lbl4.setDisable(false);
        lbl4.setVisible(true);
        lbl5.setDisable(false);
        lbl5.setVisible(true);
    }

    public static void labelVisibilizer(Label lbl, Label lbl1, Label lbl2, Label lbl3, Label lbl4, Label lbl5, Label lbl6) {
        lbl.setDisable(false);
        lbl.setVisible(true);
        lbl1.setDisable(false);
        lbl1.setVisible(true);
        lbl2.setDisable(false);
        lbl2.setVisible(true);
        lbl3.setDisable(false);
        lbl3.setVisible(true);
        lbl4.setDisable(false);
        lbl4.setVisible(true);
        lbl5.setDisable(false);
        lbl5.setVisible(true);
        lbl6.setDisable(false);
        lbl6.setVisible(true);
    }

    public static void labelSetFont(Label l1, Font f) {
        l1.setFont(f);
    }

    public static void labelSetFont(Label l1, Label l2, Font f) {
        l1.setFont(f);
        l2.setFont(f);
    }

    public static void labelSetFont(Label l1, Label l2, Label l3, Font f) {
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
    }

    public static void labelSetFont(Label l1, Label l2, Label l3, Label l4, Font f) {
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
    }

    public static void labelSetFont(Label l1, Label l2, Label l3, Label l4, Label l5, Font f) {
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
    }

    public static void labelSetFont(Label l1, Label l2, Label l3, Label l4, Label l5, Label l6, Font f) {
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);
    }

    public static void labelSetFont(Label l1, Label l2, Label l3, Label l4, Label l5, Label l6, Label l7, Font f) {
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);
        l7.setFont(f);
    }

    public static void labelSetColor(Label l1, Color c) {
        l1.setTextFill(c);
    }

    public static void labelSetColor(Label l1, Label l2, Color c) {
        l1.setTextFill(c);
        l2.setTextFill(c);
    }

    public static void labelSetColor(Label l1, Label l2, Label l3, Color c) {
        l1.setTextFill(c);
        l2.setTextFill(c);
        l3.setTextFill(c);
    }

    public static void labelSetColor(Label l1, Label l2, Label l3, Label l4, Color c) {
        l1.setTextFill(c);
        l2.setTextFill(c);
        l3.setTextFill(c);
        l4.setTextFill(c);
    }

    public static void labelSetColor(Label l1, Label l2, Label l3, Label l4, Label l5, Color c) {
        l1.setTextFill(c);
        l2.setTextFill(c);
        l3.setTextFill(c);
        l4.setTextFill(c);
        l5.setTextFill(c);
    }

    public static void labelSetColor(Label l1, Label l2, Label l3, Label l4, Label l5, Label l6, Color c) {
        l1.setTextFill(c);
        l2.setTextFill(c);
        l3.setTextFill(c);
        l4.setTextFill(c);
        l5.setTextFill(c);
        l6.setTextFill(c);
    }

    public static void labelSetColor(Label l1, Label l2, Label l3, Label l4, Label l5, Label l6, Label l7, Color c) {
        l1.setTextFill(c);
        l2.setTextFill(c);
        l3.setTextFill(c);
        l4.setTextFill(c);
        l5.setTextFill(c);
        l6.setTextFill(c);
        l7.setTextFill(c);
    }

}
