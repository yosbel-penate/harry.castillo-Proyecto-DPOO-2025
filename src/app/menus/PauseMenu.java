package app.menus;

import app.main.AudioPlayer;
import app.main.Game;
import app.main.Roaster.Roaster;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PauseMenu {


    private static VBox pauseMenu;
    private static Button resume=new Button("Resume");
    private static Button options=new Button("Options");
    private static Button exitGame = new Button("Exit Game");


    private static Button video=new Button("Video");
    private static Button audio=new Button("Audio");

    private static Button back =new Button("Back");

    private static Label MusicSound=new Label("Music-Sound");
    private static Slider MusicVolumen=new Slider(0,1,0.5);

    private static Label EffectSound=new Label("Effect-Sound");
    private static Slider EffectVolumen=new Slider(0,1,0.5);

    private static Label brightnessLabel=new Label("Brightness");
    private static Slider brightnessSlider=new Slider(-1.0,1.0,0);
    private static ColorAdjust brightness=new ColorAdjust();


    public static void initPauseMenu() {

        resume.getStyleClass().add("menu-button");
        options.getStyleClass().add("menu-button");
        exitGame.getStyleClass().add("menu-button");
        resume.setTranslateY(170);
        options.setTranslateY(170);
        exitGame.setTranslateY(170);
        video.getStyleClass().add("menu-button");
        video.setTranslateY(-30);
        audio.getStyleClass().add("menu-button");
        audio.setTranslateY(-30);
        back.getStyleClass().add("menu-button");
        back.setTranslateY(-30);

        MusicSound.getStyleClass().add("slider-label");
        MusicVolumen.getStyleClass().add("slider");
        MusicVolumen.setShowTickLabels(true);
        MusicVolumen.setMinWidth(130);
        MusicVolumen.setMaxWidth(130);
        MusicVolumen.setPrefWidth(130);
        MusicSound.setTranslateY(-300);
        MusicVolumen.setTranslateY(-300);

        EffectSound.getStyleClass().add("slider-label");
        EffectVolumen.getStyleClass().add("slider");
        EffectVolumen.setShowTickLabels(true);
        EffectVolumen.setMinWidth(130);
        EffectVolumen.setMaxWidth(130);
        EffectVolumen.setPrefWidth(130);
        EffectSound.setTranslateY(-300);
        EffectVolumen.setTranslateY(-300);

        brightnessLabel.getStyleClass().add("slider-label");
        brightnessSlider.getStyleClass().add("slider");
        brightnessSlider.setShowTickLabels(true);
        brightnessSlider.setMinWidth(130);
        brightnessSlider.setMaxWidth(130);
        brightnessSlider.setPrefWidth(130);
        brightnessLabel.setTranslateY(-400);
        brightnessSlider.setTranslateY(-420);

        hideOptionsButtons();
        hideVolumeSliders();
        hideBrightnessSlider();



        pauseMenu = new VBox(20);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);");
        pauseMenu.setPrefSize(1000, 850);
        pauseMenu.setVisible(false);
        pauseMenu.setMouseTransparent(true);
        pauseMenu.getChildren().addAll(resume, options, exitGame,video,audio,back,MusicSound,MusicVolumen,
                EffectSound,EffectVolumen,brightnessLabel,brightnessSlider);

        MusicVolumen.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVolumen=newValue.doubleValue();
            AudioPlayer.setVolumenForMusic(newVolumen);
        });
        EffectVolumen.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVolumen= newValue.doubleValue();
            AudioPlayer.setVolumenForEffect(newVolumen);
        });




        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newBrightness=newValue.doubleValue();
            brightness.setBrightness(newBrightness);
        });




        resume.setOnAction(_ ->{
            AudioPlayer.playButtonSound();
            hide();
        });

        options.setOnAction(_ ->{
            AudioPlayer.playButtonSound();
            hideLabels();
            showOptionsButtons();

        });

        exitGame.setOnAction(_ ->{
            AudioPlayer.playButtonSound();
            Game.window.close();
        });

        back.setOnAction(_ ->{
            if (video.isVisible()){
                AudioPlayer.playButtonSound();
                hideOptionsButtons();
                showLabels();
            } else if (MusicVolumen.isVisible()) {
                AudioPlayer.playButtonSound();
                hideVolumeSliders();
                showOptionsButtons();
            } else if (brightnessSlider.isVisible()) {
                AudioPlayer.playButtonSound();
                hideBrightnessSlider();
                showOptionsButtons();

            }


        });

        audio.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            hideOptionsButtons();
            showVolumeSliders();
            back.setVisible(true);
        });

        video.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            hideOptionsButtons();
            showBrightnessSlider();
            back.setVisible(true);
        });




    }

    public static VBox getPauseMenu() {
        return pauseMenu;
    }

    public static void managePauseMenu() {
        boolean visible = pauseMenu.isVisible();
        pauseMenu.setVisible(!visible);
        pauseMenu.setMouseTransparent(visible);
    }

    public static void hide() {
        pauseMenu.setVisible(false);
        pauseMenu.setMouseTransparent(true);
    }
    public static void hideLabels(){
        resume.setVisible(false);
        options.setVisible(false);
        exitGame.setVisible(false);
    }
    public static void showLabels(){
        resume.setVisible(true);
        options.setVisible(true);
        exitGame.setVisible(true);
    }
    public static void showOptionsButtons(){
        back.setVisible(true);
        audio.setVisible(true);
        video.setVisible(true);
    }
    public static void hideOptionsButtons(){
        back.setVisible(false);
        audio.setVisible(false);
        video.setVisible(false);
    }
    public static void showVolumeSliders(){
        MusicSound.setVisible(true);
        MusicVolumen.setVisible(true);
        EffectSound.setVisible(true);
        EffectVolumen.setVisible(true);
    }
    public static void hideVolumeSliders(){
        MusicSound.setVisible(false);
        MusicVolumen.setVisible(false);
        EffectSound.setVisible(false);
        EffectVolumen.setVisible(false);
    }
    public static void showBrightnessSlider(){
        brightnessLabel.setVisible(true);
        brightnessSlider.setVisible(true);
    }
    public static void hideBrightnessSlider(){
        brightnessLabel.setVisible(false);
        brightnessSlider.setVisible(false);
    }



}
