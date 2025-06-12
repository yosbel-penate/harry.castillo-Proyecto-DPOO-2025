package app;

import app.fastFeatures.Initializer;
import app.fastFeatures.PublicVariables;
import app.menus.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
// Cosas de JavaFX.


public class Game extends Application {
    private static Scene mainScene;
    private static StackPane stackPane=new StackPane();
    private HBox mainContainer;
    private VBox leftContainer;
    private StackPane rightContainer;
    private MediaPlayer mediaPlayer;
    private boolean videoFinished = false;
    // Escena, graficos, raiz y lienzo activados para su uso en toda la aplicacion.



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) {
        PublicVariables.window = window;
        /* Game.window es la variable publica estatica que usaran
        todas las clases para instanciar la ventana que necesiten.
         */

        // Cargar la imagen del icono
        Image icono = new Image("icono.jpg"); // Reemplaza con la ruta correcta

        // Establecer el icono de la ventana
        window.getIcons().add(icono);


        showIntroVideo(window);
    }

    private void showIntroVideo(Stage window) {
        try {
            URL videoUrl = getClass().getResource("/inicio.mp4");

            Media media = new Media(videoUrl.toString());
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setPreserveRatio(true);
            mediaView.setFitWidth(1000);
            mediaView.setFitHeight(650);

            StackPane videoPane = new StackPane(mediaView);
            videoPane.setStyle("-fx-background-color: black;");

            Scene videoScene = new Scene(videoPane, 1000, 650);

            mediaPlayer.setOnEndOfMedia(() -> {
                videoFinished = true;
                startMainApplication(window);
            });

            videoScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (!videoFinished) {
                    mediaPlayer.stop();
                    videoFinished = true;
                    startMainApplication(window);
                }
            });

            window.setScene(videoScene);
            window.show();

            mediaPlayer.play();

        } catch (Exception e) {
            e.printStackTrace();
            startMainApplication(window);
        }
    }

    private void startMainApplication(Stage window) {
        // Inicializar la aplicación principal


        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), window.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {

            cssSettings();
            Initializer.InitAllMethods();
            settings();

            window.setScene(mainScene);
            window.setTitle("Cronicas de Valthar: El Torneo de las Eras");
            window.setResizable(true);
            window.show();

            // Hacer que la nueva escena aparezca con un fade-in
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), window.getScene().getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();


    }

    private void cssSettings() {
        mainScene=new Scene(stackPane,1000,650);
        mainScene.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

    }

    private void settings() {
        ImageView wallpaper=new ImageView(getClass().getResource("/mainWallpaper.png").toExternalForm());
        wallpaper.setPreserveRatio(false);
        wallpaper.fitWidthProperty().bind(stackPane.widthProperty());
        wallpaper.fitHeightProperty().bind(stackPane.heightProperty());

        leftContainer=MainMenu.getMainMenu();

        rightContainer=new StackPane();
        rightContainer.setPrefWidth(300);
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        rightContainer.getChildren().addAll(OptionsMenu.getOptionsMenu(),GameModeMenu.getGameModeMenu(),
                SlidersSoundsMenu.getSliders(), SlidersBrightnessMenu.getBrightnessVbox(),ControlsInstructions.getControlsVBox());

        mainContainer=new HBox();
        mainContainer.setSpacing(150);
        mainContainer.getChildren().addAll(leftContainer,rightContainer);

        stackPane.getChildren().addAll(wallpaper,mainContainer,PauseMenu.getPauseMenu());
    }

    public static Scene getMainScene(){
        return mainScene;
    }



}



