package app.fastFeatures;

import app.menus.*;

public class Initializer {

    public static void InitAllMethods(){
        ControlsInstructions.initControlsInstructions();
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
