package app.menus;

import app.fastFeatures.AudioPlayer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import static app.fastFeatures.PublicVariables.window;

public class MainMenu {
 private static Button play = new Button("Play");
 private static Button options = new Button("Options");
 private static Button exit = new Button("Exit");
 private static VBox mainMenu;


 public static void initMainMenu() {
       play.getStyleClass().add("menu-button");
       options.getStyleClass().add("menu-button");
       exit.getStyleClass().add("menu-button");

       mainMenu=new VBox(10);
       mainMenu.getChildren().addAll(play,options,exit);
       mainMenu.setPrefWidth(300);
       mainMenu.setAlignment(Pos.CENTER_RIGHT);
       mainMenu.setPadding(new Insets(0,40,0,0));

     PauseMenu.hide();


     play.setOnAction(_ -> {
      AudioPlayer.playButtonSound();
      SlidersBrightnessMenu.hideSlider();
      OptionsMenu.hideMenu();
      GameModeMenu.showMenu();
      SlidersSoundsMenu.hideSlider();
      ControlsInstructions.hideMenu();
     });
     options.setOnAction(_ -> {
      AudioPlayer.playButtonSound();
      GameModeMenu.hideMenu();
      OptionsMenu.showMenu();
      SlidersBrightnessMenu.hideSlider();
      SlidersSoundsMenu.hideSlider();
      ControlsInstructions.hideMenu();
     });
     exit.setOnAction(_ -> {
      AudioPlayer.playButtonSound();
      window.close();
     });


 }

 public static VBox getMainMenu(){
  return mainMenu;
 }
}