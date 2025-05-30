package app;

import app.fastFeatures.Initializer;
import app.fastFeatures.PublicVariables;
import app.menus.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
// Cosas de JavaFX.


public class Game extends Application {
    private static Scene mainScene;
    private static StackPane stackPane=new StackPane();
    private HBox mainContainer;
    private VBox leftContainer;
    private StackPane rightContainer;
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

        cssSettings();
        Initializer.InitAllMethods();
        settings();

        window.setScene(mainScene);
        window.setTitle("Cronicas de Valthar: El Torneo de las Eras");
        window.setResizable(false);
        window.show();
    }

    private void cssSettings() {
        mainScene=new Scene(stackPane,1000,750);
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



