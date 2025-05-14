package app.gameplayFeatures;

import app.main.Roaster.Roaster;
import domain.consumables.Inventory;
import domain.entities.EnemyCharacter;
import domain.entities.PlayerCharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

import static app.main.Game.window;

public class Gameplay {

    private static boolean grabConsumable;
    private static boolean drawConsumable;
    private static boolean addConsumable;
    private static boolean activateRange;
    private static AnimationTimer gameplayTimer;
    private static ArrayList<Consumables> inventory;
    private static int previusX;
    private static int previusY;
    private static Scene gameplayScene;
    private  static Group root;
    private  static Canvas canvas;
    private static GraphicsContext graphics;
    private static int actionPoints = 2;
    private static Random random = new Random();
    private static PlayerCharacter[] player;
    private static EnemyCharacter[] enemy;
    private static int size;
    private static long time;


    private static Label actionPointLabel;
    private static Label inventoryLabel;
    private static Label emptyInventoryLabel;
    private static Label itemNumber1;
    private static Label itemNumber2;
    private static Label itemNumber3;
    private static Label itemNumber4;
    // Labels y botones.

    public static void initializeGameplay() {
        reviewMission();
        windowDesign();
        anotherConfigs();
        initializeEnemy();
        playerMovement();
        labelConfigurations();
        gameLoop();
    }

    private static void windowDesign() {
        player = Roaster.getPlayer();
        player[0].setEnemy(enemy);
        root = new Group();
        gameplayScene = new Scene(root, 1000, 850);
        canvas = new Canvas(1000, 850);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(gameplayScene);
    }


    private static void initializeEnemy() {
        enemy = new EnemyCharacter[3];
        // El lobito tiene tre na ma.
        for (int i = 0; i < 3; i++){
            enemy[i] = new EnemyCharacter();
            enemy[i].setAlive(true);
        }
        if (enemy[0].isAlive()) {
            if (!Combat.isNoRandomPosition()) {
                randomPosition();
                enemy[0].setCharacter(player[0]);
            }
        }
    }
    private static void labelConfigurations() {
        Font font = new Font("Arial", 20);
        actionPointLabel = LabelCreator.createLabel(705, 48,"Action Points: " + actionPoints, Color.WHITE, font);
        inventoryLabel = LabelCreator.createLabel(770, 550, "Inventario", Color.WHITE, font);
        emptyInventoryLabel = LabelCreator.createLabel(720, 600, "El inventario esta vacio", Color.WHITE, font);


        root.getChildren().addAll(actionPointLabel, inventoryLabel, emptyInventoryLabel);
    }
    private static void anotherConfigs() {
        TileMap.setPlayer(player);

        if (!(Inventory.isAlreadyCreated())) {
            inventory = Inventory.createInventory();

        }
    }


    private static void gameLoop() {
        long initialTime = System.nanoTime();
        gameplayTimer = new AnimationTimer() {

            //Este metodo es el que maneja los frames por segundo, que son 60.
            @Override
            public void handle(long actualTime) {
                time = (actualTime - initialTime) / 1000000000;
                if (time == 60) {
                    time = 0;
                }
                actionPointLabel.setText("Action Points: " + actionPoints);
                /* Aqui se calcula el tiempo, que luego se usara
                para que parpadee el rango.
                */
                draw(time);
                actualizeState();


            }
        };
        gameplayTimer.start();
    }

    private static void randomPosition() {
        int xPos = random.nextInt(7, 11);
        int yPos = random.nextInt(1, 10);
        /* xPos es de 7 a 11 para que el enemigo solo aparezca
            esas columnas. Y la yPos es para que aparezca en
            los hexagonos del 1 al 10 (esto varia si es par
            o impar).
         */

        if (xPos % 2 == 0) {
            yPos = (yPos * 64) + 32;
        } else {
            yPos = yPos * 64;
        }
        xPos = switch (xPos) {
            case 7 -> 352;
            case 8 -> 400;
            case 9 -> 448;
            case 10 -> 496;
            case 11 -> 544;
            default -> xPos;
        };
        /* Se comprueba la paridad de xPos para ubicarlo
        entre las columnas pares e impares, luego
         */

        enemy[0].setX(xPos);
        enemy[0].setY(yPos);
        // Se le pone al enemigo la posicion aleatoria generada.
    }

    private static void playerMovement() {
        /* Por cada tecla presionada, el codigo evaluara
        los puntos de accion y la posicion del jugador
        y determinara si se mueve o no se mueve.
        */

        gameplayScene.setOnKeyPressed(event -> {
            switch (event.getCode().toString()) {
                case "A":
                    // Tecla A mueve el jugador hacia la izquierda-abajo (X-48, Y+32)
                   previusX = player[0].getX();
                   previusY = player[0].getY();
                        if (actionPoints != 0) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= 0) {
                            if (player[0].getY() == 640) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == 64) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() - 48);
                            player[0].setY(player[0].getY() + 32);

                        }
                    break;
                case "D":
                    // Tecla D mueve el jugador hacia la derecha-abajo (X+48, Y+32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != 0) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= 0) {
                            if (player[0].getY() == 640) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == 544) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + 48);
                            player[0].setY(player[0].getY() + 32);
                        }

                    break;
                case "W":
                    // Tecla W mueve el jugador hacia arriba (Y-64)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != 0) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= 0) {
                            if (player[0].getY() == 32) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getY() == 64) {
                                actionPoints++;
                                break;
                            }
                            player[0].setY(player[0].getY() - 64);
                        }

                    break;
                case "S":
                    // Tecla S mueve el jugador hacia abajo (Y+64)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != 0) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= 0) {
                            if (player[0].getY() == 640) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getY() == 608) {
                                actionPoints++;
                                break;
                            }
                            player[0].setY(player[0].getY() + 64);
                        }

                    break;
                case "Q":
                    // Tecla Q mueve el jugador hacia la izquierda-arriba (X-48, Y-32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != 0) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= 0) {
                            if (player[0].getY() == 32) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == 64) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() - 48);
                            player[0].setY(player[0].getY() - 32);
                        }
                    break;
                case "E":
                    // Tecla E mueve el jugador hacia la derecha-arriba (X+48, Y-32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != 0) {
                            actionPoints--;
                        } else {
                            break;
                        }

                        if (actionPoints >= 0) {
                            if (player[0].getY() == 32) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == 544) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + 48);
                            player[0].setY(player[0].getY() - 32);
                        }

                    break;
                case "R":
                    actionPoints = 2;
                    enemy[0].move(player[0].getX(), player[0].getY(), enemy[0].getX(), enemy[0].getY());
                    break;
                case "T":
                    activateRange = !activateRange;
                    break;

            }
        });


    }

    private static void draw(long time) {


        graphics.drawImage(new Image("background1.png"), 0, 0);
        // Fondo.

        graphics.drawImage(new Image("gameplaySquare1.png"), 680, 32);
        // Cuadro de las estadisticas.

        graphics.drawImage(new Image("gameplaySquare2.png"), 32, 710);
        // Cuadro de los dialogos.

        graphics.drawImage(new Image("gameplaySquare3.png"), 680, 542);
        // Cuadro del inventario.

        TileMap.drawCampaignMap(graphics);
        //Dibujo de las columnas de hexagonos.

        player[0].range(graphics, time);
        if (enemy[0].isAlive()) {
            enemy[0].range(graphics, time);
            enemy[0].draw(graphics);
            rangeCollition(time);
        }
        // Rangos.

        graphics.drawImage(new Image(player[0].getImageName()), player[0].getX(), player[0].getY());
        // Imagen del jugador.

        if (Combat.isDropConsumable()) {
            if (drawConsumable) {
                graphics.drawImage(new Image(inventory.getFirst().getImage()), inventory.getFirst().getX(), inventory.getFirst().getY());
            }
        }
        // Consumibles en el mapa.

        if(!drawConsumable && inventory.getFirst().getQuantity() > 0) {
            graphics.drawImage(new Image(inventory.getFirst().getImage()), 705, 602);
        }
        // Consumibles en el inventario.

    }

    private static void rangeCollition(long time) {
        int xDistanceEvP = enemy[0].getX() - player[0].getX();
        int yDistanceEvP = enemy[0].getY() - player[0].getY();
        if (activateRange) {
            if (time % 2 == 0) {
                Image rangoSobrepuesto = new Image("overRangeTerrain.png");
                if (xDistanceEvP == 96 && yDistanceEvP == 0) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + 32);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() - 32);
                }
                if (xDistanceEvP == -96 && yDistanceEvP == 0) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + 32);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() - 32);
                }
                if (xDistanceEvP == 96 && yDistanceEvP == 64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + 32);
                }
                if (xDistanceEvP == 96 && yDistanceEvP == -64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() - 32);
                }
                if (xDistanceEvP == -96 && yDistanceEvP == 64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + 32);
                }
                if (xDistanceEvP == -96 && yDistanceEvP == -64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() - 32);
                }
                // Los tres primeros ifs son a la izquierda y los tres ultimos a la derecha.


                if (xDistanceEvP == 0 && yDistanceEvP == 128) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + 64);

                }
                if (xDistanceEvP == 48 && yDistanceEvP == 96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + 64);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + 32);
                }
                if (xDistanceEvP == -48 && yDistanceEvP == 96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + 64);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + 32);
                }
                if (xDistanceEvP == 0 && yDistanceEvP == -128) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() - 64);

                }
                if (xDistanceEvP == 48 && yDistanceEvP == -96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() - 64);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() - 32);
                }
                if (xDistanceEvP == -48 && yDistanceEvP == -96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() - 64);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() - 32);
                }
            }
        }
    }

    private static void actualizeState() {
        if (enemy[0].isAlive()) {
            enemy[0].collideRange();
        }

            player[0].collideWithConsumable(inventory);

        for (int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getQuantity() <= 0){
                emptyInventoryLabel.setVisible(false);
            }
        }


        player[0].collideRange();

        /* Llama a los metodos que comprueban las
         colisiones en cada enemigo y personaje.
        */

        if (EnemyCharacter.isCollidePlayer() && PlayerCharacter.isCollideEnemy()) {
            Combat.initializeCombat();
            EnemyCharacter.setCollidePlayer(false);
            PlayerCharacter.setCollideEnemy(false);
        }
        if (EnemyCharacter.isCollidePlayer()) {
            Combat.initializeCombat();
            EnemyCharacter.setCollidePlayer(false);
            PlayerCharacter.setCollideEnemy(false);
        }
        if (PlayerCharacter.isCollideEnemy()) {
            Combat.initializeCombat();
            EnemyCharacter.setCollidePlayer(false);
            PlayerCharacter.setCollideEnemy(false);
        }




    }

    private static void reviewMission() {
    }

    public static boolean isGrabConsumable() {
        return grabConsumable;
    }

    public static void setGrabConsumable(boolean grabConsumable) {
        Gameplay.grabConsumable = grabConsumable;
    }

    public static boolean isDrawConsumable() {
        return drawConsumable;
    }

    public static void setDrawConsumable(boolean drawConsumable) {
        Gameplay.drawConsumable = drawConsumable;
    }

    public static boolean isAddConsumable() {
        return addConsumable;
    }

    public static void setAddConsumable(boolean addConsumable) {
        Gameplay.addConsumable = addConsumable;
    }

    public static boolean isActivateRange() {
        return activateRange;
    }

    public static void setActivateRange(boolean activateRange) {
        Gameplay.activateRange = activateRange;
    }

    public static void startGameplayTimer() {
        gameplayTimer.start();
    }

    public static void stopGameplayTimer() {
        gameplayTimer.stop();
    }

    public static ArrayList<Consumables> getInventory() {
        return inventory;
    }

    public static void setInventory(ArrayList<Consumables> inventory) {
        Gameplay.inventory = inventory;
    }

    public static EnemyCharacter[] getEnemy() {
        return enemy;
    }

    public static void setEnemy(EnemyCharacter[] enemy) {
        Gameplay.enemy = getEnemy(enemy);
    }

    private static EnemyCharacter[] getEnemy(EnemyCharacter[] enemy) {
        return enemy;
    }

    public static long getTime() {
        return time;
    }

    public static void setTime(long time) {
        Gameplay.time = time;
    }

    public static int getPreviusX() {
        return previusX;
    }

    public static void setPreviusX(int previusX) {
        Gameplay.previusX = previusX;
    }

    public static int getPreviusY() {
        return previusY;
    }

    public static void setPreviusY(int previusY) {
        Gameplay.previusY = previusY;
    }

    public static Scene getGameplayScene() {
        return gameplayScene;
    }

    public static void setGameplayScene(Scene gameplayScene) {
        Gameplay.gameplayScene = gameplayScene;
    }

    public static Group getRoot() {
        return root;
    }

    public static void setRoot(Group root) {
        Gameplay.root = root;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Gameplay.canvas = canvas;
    }

    public static GraphicsContext getGraphics() {
        return graphics;
    }

    public static void setGraphics(GraphicsContext graphics) {
        Gameplay.graphics = graphics;
    }

    public static int getActionPoints() {
        return actionPoints;
    }

    public static void setActionPoints(int actionPoints) {
        Gameplay.actionPoints = actionPoints;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        Gameplay.random = random;
    }

    public static Label getActionPointLabel() {
        return actionPointLabel;
    }

    public static void setActionPointLabel(Label actionPointLabel) {
        Gameplay.actionPointLabel = actionPointLabel;
    }


    public static PlayerCharacter[] getPlayer() {
        return player;
    }

    public static void setPlayer(PlayerCharacter[] player) {
        Gameplay.player = player;
    }


}