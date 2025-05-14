package app.menus;

import app.main.AudioPlayer;
import app.main.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

    public static void initPauseMenu() {

        resume.getStyleClass().add("menu-button");
        options.getStyleClass().add("menu-button");
        exitGame.getStyleClass().add("menu-button");
        video.getStyleClass().add("menu-button");
        video.setTranslateY(-180);
        audio.getStyleClass().add("menu-button");
        audio.setTranslateY(-180);
        back.getStyleClass().add("menu-button");
        back.setTranslateY(-180);

        hideOptionsButtons();



        pauseMenu = new VBox(20);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);");
        pauseMenu.setPrefSize(1000, 850);
        pauseMenu.setVisible(false);
        pauseMenu.setMouseTransparent(true);
        pauseMenu.getChildren().addAll(resume, options, exitGame,video,audio,back);




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
            AudioPlayer.playButtonSound();
            hideOptionsButtons();
            showLabels();
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


}
