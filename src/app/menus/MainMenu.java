package app.menus;

import app.main.AudioPlayer;
import app.main.Game;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import static app.main.Game.window;

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
      OptionsMenu.hideMenu();
      GameModeMenu.showMenu();
      SlidersSoundsMenu.hideSlider();
     });
     options.setOnAction(_ -> {
      AudioPlayer.playButtonSound();
      GameModeMenu.hideMenu();
      OptionsMenu.showMenu();
      SlidersSoundsMenu.hideSlider();
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