package app.fastFeatures;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class ButtonManager {

    public static Button createButton(String text, double x, double y, javafx.event.EventHandler<javafx.event.ActionEvent> action, Font font) {
    Button btn = new Button(text);
    btn.setTranslateX(x);
    btn.setTranslateY(y);
    btn.setFocusTraversable(false);
    btn.setOnAction(action);
    btn.setFont(font);
    return btn;
    }

    public static void buttonDisabler(Button btn){
        btn.setDisable(true);

    }
    public static void buttonDisabler(Button btn, Button btn1){
        btn.setDisable(true);
        btn1.setDisable(true);
    }
    public static void buttonDisabler(Button btn, Button btn1, Button btn2){
        btn.setDisable(true);
        btn1.setDisable(true);
        btn2.setDisable(true);
    }
    public static void buttonDisabler(Button btn, Button btn1, Button btn2, Button btn3){
        btn.setDisable(true);
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
    }
    public static void buttonDisabler(Button btn, Button btn1, Button btn2, Button btn3, Button btn4){
        btn.setDisable(true);
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
    }

    public static void buttonEnabler(Button btn){
        btn.setDisable(false);

    }
    public static void buttonEnabler(Button btn, Button btn1){
        btn.setDisable(false);
        btn1.setDisable(false);
    }
    public static void buttonEnabler(Button btn, Button btn1, Button btn2){
        btn.setDisable(false);
        btn1.setDisable(false);
        btn2.setDisable(false);
    }
    public static void buttonEnabler(Button btn, Button btn1, Button btn2, Button btn3){
        btn.setDisable(false);
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
    }
    public static void buttonEnabler(Button btn, Button btn1, Button btn2, Button btn3, Button btn4){
        btn.setDisable(false);
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
    }

    public static void buttonInvisibilizer(Button btn){
        btn.setDisable(false);
        btn.setVisible(false);

    }
    public static void buttonInvisibilizer(Button btn, Button btn1){
        btn.setDisable(false);
        btn.setVisible(false);
        btn1.setDisable(false);
        btn1.setVisible(false);
    }
    public static void buttonInvisibilizer(Button btn, Button btn1, Button btn2){
        btn.setDisable(false);
        btn.setVisible(false);
        btn1.setDisable(false);
        btn1.setVisible(false);
        btn2.setDisable(false);
        btn2.setVisible(false);
    }
    public static void buttonInvisibilizer(Button btn, Button btn1, Button btn2, Button btn3){
        btn.setDisable(false);
        btn.setVisible(false);
        btn1.setDisable(false);
        btn1.setVisible(false);
        btn2.setDisable(false);
        btn2.setVisible(false);
        btn3.setDisable(false);
        btn3.setVisible(false);
    }
    public static void buttonInvisibilizer(Button btn, Button btn1, Button btn2, Button btn3, Button btn4){
        btn.setDisable(false);
        btn.setVisible(false);
        btn1.setDisable(false);
        btn1.setVisible(false);
        btn2.setDisable(false);
        btn2.setVisible(false);
        btn3.setDisable(false);
        btn3.setVisible(false);
        btn4.setDisable(false);
        btn4.setVisible(false);
    }

    public static void buttonVisibilizer(Button btn) {
        btn.setDisable(false);
        btn.setVisible(true);

    }

    public static void buttonVisibilizer(Button btn, Button btn1) {
        btn.setDisable(false);
        btn.setVisible(true);
        btn1.setDisable(false);
        btn1.setVisible(true);
    }

    public static void buttonVisibilizer(Button btn, Button btn1, Button btn2) {
        btn.setDisable(false);
        btn.setVisible(true);
        btn1.setDisable(false);
        btn1.setVisible(true);
        btn2.setDisable(false);
        btn2.setVisible(true);
    }

    public static void buttonVisibilizer(Button btn, Button btn1, Button btn2, Button btn3) {
        btn.setDisable(false);
        btn.setVisible(true);
        btn1.setDisable(false);
        btn1.setVisible(true);
        btn2.setDisable(false);
        btn2.setVisible(true);
        btn3.setDisable(false);
        btn3.setVisible(true);
    }

    public static void buttonVisibilizer(Button btn, Button btn1, Button btn2, Button btn3, Button btn4) {
        btn.setDisable(false);
        btn.setVisible(true);
        btn1.setDisable(false);
        btn1.setVisible(true);
        btn2.setDisable(false);
        btn2.setVisible(true);
        btn3.setDisable(false);
        btn3.setVisible(true);
        btn4.setDisable(false);
        btn4.setVisible(true);
    }

}
