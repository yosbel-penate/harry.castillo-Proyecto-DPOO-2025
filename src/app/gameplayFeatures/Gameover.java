package app.gameplayFeatures;

import app.fastFeatures.AudioPlayer;
import domain.generalClasses.PlayerCharacter;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static app.fastFeatures.PublicVariables.window;

public class Gameover {
    private static PlayerCharacter[] player;
    private static Group gameOverRoot;
    private static Scene gameOverScene;
    private static Canvas canvas;
    private static ImageView gameOver;
    private static Label restartLabel = new Label("Toque R para reinciar");


    public static void gameOver(Scene sceneToGameOver, AnimationTimer animationTimerToStop) {
        sceneToGameOver.setRoot(new Group());
        animationTimerToStop.stop();

        gameOverScreenConfig();
        labelConfigurations();
        setupRestartListener(gameOverScene);
        window.setScene(gameOverScene);

    }

    private static void gameOverScreenConfig(){
        gameOverRoot = new Group();
        gameOverScene = new Scene(gameOverRoot, 1000, 800);
        gameOverScene.getStylesheets().add(Gameover.class.getResource("/buttons.css").toExternalForm());
        canvas = new Canvas(1000, 800);
        gameOver=new ImageView(Gameover.class.getResource("/gameOverImage.png").toExternalForm());
        gameOver.setPreserveRatio(false);
        gameOver.fitWidthProperty().bind(gameOverScene.widthProperty());
        gameOver.fitHeightProperty().bind(gameOverScene.heightProperty());

        restartLabel.getStyleClass().add("game-over-label");
        restartLabel.setVisible(false);

        AudioPlayer.playGameOver();







    }

    private static void labelConfigurations(){


        Font gameOverFont = new Font(40);




        FadeTransition fade=new FadeTransition(Duration.seconds(2),gameOver);
        fade.setFromValue(0);
        fade.setToValue(1);


        FadeTransition labelFade=new FadeTransition(Duration.seconds(2),restartLabel);
        labelFade.setFromValue(0);
        labelFade.setToValue(1);
        labelFade.setDelay(Duration.seconds(2));

        Timeline pulse = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(restartLabel.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(restartLabel.opacityProperty(), 0.6)),
                new KeyFrame(Duration.seconds(2), new KeyValue(restartLabel.opacityProperty(), 1.0))
        );
        pulse.setCycleCount(Timeline.INDEFINITE);
        pulse.setAutoReverse(false);

        fade.setOnFinished(e -> restartLabel.setVisible(true));
        labelFade.setOnFinished(e ->pulse.play());
        fade.play();
        labelFade.play();





        restartLabel.setFont(gameOverFont);

        player = Gameplay.getPlayer();

        restartLabel.setTranslateX(300);
        restartLabel.setTranslateY(550);

        gameOverRoot.getChildren().addAll( canvas,gameOver, restartLabel);
    }

    private static void setupRestartListener(Scene gameOverScene) {
        gameOverScene.setOnKeyPressed(event -> {
            if ("R".equals(event.getCode().toString())) {
                Combat.setNoRandomPosition(false);
                window.setScene(Gameplay.getGameplayScene());
                Gameplay.startGameplayTimer();
                for (PlayerCharacter p : player) {
                    p.setHealth(5);
                }
            }
        });
    }
}
