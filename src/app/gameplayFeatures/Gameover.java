package app.gameplayFeatures;

import app.main.AudioPlayer;
import domain.entities.PlayerCharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static app.main.Game.window;

public class Gameover {
    private static PlayerCharacter[] player;
    private static Group gameOverRoot;
    private static Scene gameOverScene;
    private static Canvas canvas;

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
        gameOverScene = new Scene(gameOverRoot, 1000, 850);
        canvas = new Canvas(1000, 850);
    }

    private static void labelConfigurations(){
        Font gameOverFont = new Font(40);
        Label gameOverLabel = new Label("Game over.");
        Label restartLabel = new Label("Reinicie la partida con R.");

        gameOverLabel.setFont(gameOverFont);
        restartLabel.setFont(gameOverFont);

        player = Gameplay.getPlayer();
        gameOverLabel.setTranslateX(250);
        gameOverLabel.setTranslateY(150);
        restartLabel.setTranslateX(250);
        restartLabel.setTranslateY(200);

        gameOverRoot.getChildren().addAll(canvas, gameOverLabel, restartLabel);
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
