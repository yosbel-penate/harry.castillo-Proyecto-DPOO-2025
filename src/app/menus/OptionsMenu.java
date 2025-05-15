package app.menus;

import app.main.AudioPlayer;
import app.main.Game;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OptionsMenu {
    private static Button controls=new Button("Controls");
    private static Button video=new Button("Video");
    private static Button audio=new Button("Audio");
    private static Button back=new Button("Back");
    private static VBox optionsMenu;


    public static void initOptionsMenu(){
        controls.getStyleClass().add("menu-button");
        video.getStyleClass().add("menu-button");
        audio.getStyleClass().add("menu-button");
        back.getStyleClass().add("menu-button");

        optionsMenu=new VBox(10);
        optionsMenu.getChildren().addAll(controls,video,audio,back);
        optionsMenu.setAlignment(Pos.CENTER_RIGHT);
        optionsMenu.setPadding(new Insets(0,0,0,10));



        hideMenu();

        video.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            hideMenu();
            SlidersBrightnessMenu.showSlider();
        });

        audio.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            hideMenu();
            SlidersSoundsMenu.showSlider();
        });

        back.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            hideMenu();
        });

    }

    public static VBox getOptionsMenu() {
        return optionsMenu;
    }

    public static void showMenu(){
        optionsMenu.setVisible(true);
        optionsMenu.setMouseTransparent(false);
    }
    public static void hideMenu(){
        optionsMenu.setVisible(false);
        optionsMenu.setMouseTransparent(true);
    }
}

