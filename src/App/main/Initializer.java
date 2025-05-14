package app.main;

import app.menus.*;
import javafx.scene.layout.StackPane;

public class Initializer {

    public static void InitAllMethods(){
        OptionsMenu.initOptionsMenu();
        PauseMenu.initPauseMenu();
        AudioPlayer.initMediaPlayer();
        AudioPlayer.initAudioClips();
        MainMenu.initMainMenu();
        GameModeMenu.initGameModeMenu();
        SlidersSoundsMenu.initSlidersForVolumen();
        AudioPlayer.playMainMenu();
        SlidersBrightnessMenu.initBrightnessSlider();

    }
}
