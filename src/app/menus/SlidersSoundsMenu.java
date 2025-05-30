package app.menus;

import app.fastFeatures.AudioPlayer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SlidersSoundsMenu {

    private static Label MasterSound;
    private static Slider MasterVolumen;
    private static Label MusicSound;
    private static Slider MusicVolumen;
    private static Label EffectSound;
    private static Slider EffectVolumen;
    private static Button back=new Button("Back");

    private static VBox allSlider;

    public static void initSlidersForVolumen(){
        allSlider=new VBox(10);

        back.getStyleClass().add("menu-button");


        MasterSound=new Label("Master-Sound");
        MasterSound.getStyleClass().add("slider-label");
        MasterVolumen=new Slider(0,1,0.5);
        MasterVolumen.getStyleClass().add("slider");
        MasterVolumen.setShowTickLabels(true);
        MasterVolumen.setPrefWidth(80);

        MusicSound=new Label("Music-Sound");
        MusicSound.getStyleClass().add("slider-label");
        MusicVolumen=new Slider(0,1,0.5);
        MusicVolumen.getStyleClass().add("slider");
        MusicVolumen.setShowTickLabels(true);
        MusicVolumen.setPrefWidth(80);

        EffectSound=new Label("Effect-Sound");
        EffectSound.getStyleClass().add("slider-label");
        EffectVolumen=new Slider(0,1,0.5);
        EffectVolumen.getStyleClass().add("slider");
        EffectVolumen.setShowTickLabels(true);
        EffectVolumen.setPrefWidth(80);

        allSlider.getChildren().addAll(MasterSound,MasterVolumen,MusicSound,MusicVolumen,EffectSound,EffectVolumen,back);
        allSlider.setAlignment(Pos.CENTER);
        allSlider.setPadding(new Insets(0,0,30,100));
        allSlider.setTranslateX(130);
        allSlider.setTranslateY(-20);


        MasterVolumen.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVolumen= newValue.doubleValue();
            AudioPlayer.setVolumeForAllSounds(newVolumen);
        });
        MusicVolumen.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVolumen=newValue.doubleValue();
            AudioPlayer.setVolumenForMusic(newVolumen);
        });
        EffectVolumen.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVolumen= newValue.doubleValue();
            AudioPlayer.setVolumenForEffect(newVolumen);
        });



        hideSlider();

        back.setOnAction(_ ->  {
            AudioPlayer.playButtonSound();
            hideSlider();
            OptionsMenu.showMenu();
        });

    }


    public static VBox getSliders(){
        return allSlider;
    }
    public static void hideSlider(){
        allSlider.setVisible(false);
        allSlider.setMouseTransparent(true);

    }
    public static void showSlider(){
       allSlider.setVisible(true);
       allSlider.setMouseTransparent(false);
    }




}
