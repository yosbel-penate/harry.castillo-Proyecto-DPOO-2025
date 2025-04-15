package app;

import domain.PlayerCharacter;
// Clases locales que usa Campaign.

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
// Java FX.

import static app.game.window;
// Variables estaticas importadas.

public class Campaign {
    public static Scene campaignGameScene;
    private static GraphicsContext graphics;
    private Group root;
    private Canvas canvas;
    // Variables privadas.


    public Campaign(GraphicsContext graphics, Scene gameScene) {
        Campaign.graphics = graphics;
        this.campaignGameScene = gameScene;
    }
public void initialize() {

        root = new Group();
        campaignGameScene = new Scene(root, 1000, 850);
        canvas = new Canvas(1000, 850);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(campaignGameScene);
        PlayerCharacter player = new PlayerCharacter(64, 64, Paths.get("src/DAO/images/inGameplayCharacters/monigote.png").toUri().toString());
        playerMovement(campaignGameScene, player);
        gameLoop(graphics, player);

        }




    private void playerMovement(Scene scene, PlayerCharacter player) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int move = 0;
                switch (event.getCode().toString()) {
                    case "A":
                        if (player.getY() == 640) {
                                break;
                            }
                            if (player.getX() == 64) {
                                break;
                            }

                            player.setX(player.getX()-48);
                            player.setY(player.getY()+32);


                            break;
                    case "D":
                            if (player.getY() == 640) {
                                break;
                            }
                            if (player.getX() == 544) {
                                break;
                            }
                            player.setX(player.getX()+48);
                            player.setY(player.getY()+32);

                        break;

                    case "W":
                            if (player.getY() == 32) {
                                break;
                            } else if (player.getY() == 64) {
                                break;
                            }

                            player.setY(player.getY()-64);
                        break;

                    case "S":

                            if (player.getY() == 640) {
                                break;
                            }
                            if (player.getY() == 608) {
                                break;
                            }
                            player.setY(player.getY()+64);

                        break;
                    case "Q":
                            if (player.getY() == 32) {

                                break;
                            }
                            if (player.getX() == 64) {

                                break;
                            }

                            player.setX(player.getX()-48);
                            player.setY(player.getY()-32);

                        break;
                    case "E":

                            if (player.getY() == 32) {
                                break;
                            }
                            if (player.getX() == 544) {

                                break;
                            }

                            player.setX(player.getX()+48);
                            player.setY(player.getY()-32);


                    break;
                    }
            }
        });
    }


    private void gameLoop(GraphicsContext graphics, PlayerCharacter player) {
        long initialTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {

            //Este metodo es el que maneja los frames por segundo, que son 60.
            @Override
            public void handle(long actualTime) {
                long time = (actualTime - initialTime) / 1000000000;
                if (time == 60){
                    time = 0;
                }
                /* Aqui se calcula el tiempo, que luego se usara
                para que parpadee el rango.
                */
                draw(graphics, player);
            }
        };
        animationTimer.start();
    }

    private void draw(GraphicsContext graphics, PlayerCharacter player) {
            //Salvenos Dios por toda esta cantidad de codigo.

            for (int i = 1; i <= 11; i++) {
                Image hex = new Image(Paths.get("src/DAO/images/terrain/normalTerrain.png").toUri().toString());
                if (i % 2 == 0) {
                    for (int pos = 112; pos <= 496; pos+= 96) {
                        for (int j = 32; j < 640; j += 64) {
                            graphics.drawImage(hex, pos, j);
                        }
                    }
                } else {
                    for (int pos = 64; pos <= 544; pos+=96) {
                        for (int j = 64; j < 704; j += 64) {
                            graphics.drawImage(hex, pos, j);
                        }
                    }
                }
            }
            //Dibujo de las columnas de hexagonos.
         player.draw(graphics);
    }
}
