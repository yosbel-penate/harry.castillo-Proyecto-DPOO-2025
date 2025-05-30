package app.menus;

import app.fastFeatures.AudioPlayer;
import app.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.VBox;

public class SlidersBrightnessMenu {

    public static Label brightnessLabel=new Label("Brightness");
    public static Slider brightnessSlider=new Slider(-1.0,1.0,0);
    public static VBox brightnessVbox;
    public static Button back=new Button("Back");

    public static void initBrightnessSlider(){
        brightnessLabel.getStyleClass().add("slider-label");
        brightnessSlider.getStyleClass().add("slider");
        brightnessSlider.setShowTickLabels(true);
        brightnessSlider.setPrefWidth(80);

        back.getStyleClass().add("menu-button");

        brightnessVbox=new VBox(10);
        brightnessVbox.getChildren().addAll(brightnessLabel,brightnessSlider,back);
        brightnessVbox.setAlignment(Pos.CENTER);
        brightnessVbox.setPadding(new Insets(0,0,30,100));
        brightnessVbox.setTranslateX(130);
        brightnessVbox.setTranslateY(-20);

        ColorAdjust brightness=new ColorAdjust();
        Game.getMainScene().getRoot().setEffect(brightness);

        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newBrightness=newValue.doubleValue();
            brightness.setBrightness(newBrightness);
        });



        back.setOnAction(_ ->{
            AudioPlayer.playButtonSound();
            hideSlider();
            OptionsMenu.showMenu();
        });

        hideSlider();
    }

    public static VBox getBrightnessVbox(){
        return brightnessVbox;
    }
    public static void showSlider(){
        brightnessVbox.setVisible(true);
        brightnessVbox.setMouseTransparent(false);
    }
    public static void hideSlider(){
        brightnessVbox.setVisible(false);
        brightnessVbox.setMouseTransparent(true);
    }
}
