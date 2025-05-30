package app.gameplayFeatures;

import app.fastFeatures.LabelManager;
import app.Roaster;
import app.menus.PauseMenu;
import domain.generalClasses.Inventory;
import domain.generalClasses.EnemyCharacter;
import domain.generalClasses.PlayerCharacter;
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

import static app.fastFeatures.PublicVariables.*;

public class Gameplay {

    // Variables diversas.

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
    private static long time;


    // Labels y botones.

    private static Label actionPointLabel;
    private static Label inventoryLabel;
    private static Label emptyInventoryLabel;
    private static Label itemNumber1;
    private static Label itemNumber2;
    private static Label itemNumber3;
    private static Label itemNumber4;

    /*  Los itemNumber son para la cantidad de
        items en el Inventario. En el ItemNumber1
        pueden poner, por ejemplo, la cantidad de
        pociones.
    */



    public static void initializeGameplay() {
        reviewMission();
        setupPlayer();
        setupWindow();
        setupAnotherConfigs();
        setupEnemy();
        playerMovement();
        labelConfigurations();
        gameLoop();
    }


    private static void reviewMission() {
    }

    private static void setupPlayer() {
        player = Roaster.getPlayer();
        player[0].setEnemy(enemy);
    }
    private static void setupWindow() {
        root = new Group();
        gameplayScene = new Scene(root, screenWidth, screenHeight);
        gameplayScene.getStylesheets().add(Gameplay.class.getResource("/buttons.css").toExternalForm());
        canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(gameplayScene);
        root.getChildren().add(PauseMenu.getPauseMenu());
        root.setEffect(PauseMenu.getBrightness());
    }

    private static void setupAnotherConfigs() {
        TileMap.setPlayer(player);
        if (!(Inventory.isAlreadyCreated())) {
            inventory = Inventory.createInventory();

        }
    }


    private static void setupEnemy() {
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

    private static void randomPosition() {
        int xPos = random.nextInt(7, 11);
        int yPos = random.nextInt(1, 10);
        int firstCol = 64;
        int evenPeak = 32;
        /* xPos es de 7 a 11 para que el enemigo solo aparezca
            esas columnas. Y la yPos es para que aparezca en
            los hexagonos del 1 al 10 (esto varia si es par
            o impar).
         */

        if (xPos % 2 == 0) {
            yPos = (yPos * down) + evenPeak;
        } else {
            yPos = yPos * down;
        }
        /* En caso de que sea par, se le añade 32 para que
           se adecue a las columnas pares, ya que estas
           estan desplazadas trentaidos pixeles mas arriba
           que las impares.
        */

        xPos = firstCol + ((xPos-1) * right);

        enemy[0].setX(xPos);
        enemy[0].setY(yPos);
        // Se le pone al enemigo la posicion aleatoria generada.
    }

    private static void labelConfigurations() {
        Font font = new Font("Arial", 20);
        actionPointLabel = LabelManager.createLabel(705, 48,"Action Points: " + actionPoints, Color.WHITE, font);
        inventoryLabel = LabelManager.createLabel(770, 550, "Inventario", Color.WHITE, font);
        emptyInventoryLabel = LabelManager.createLabel(720, 600, "El inventario esta vacio", Color.WHITE, font);
        root.getChildren().addAll(actionPointLabel, inventoryLabel, emptyInventoryLabel);
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
                draw();
                actualizeState();


            }
        };
        gameplayTimer.start();
    }

    private static void draw() {
        drawBackground();
        drawGameplaySquares();
        drawCampaignMap();
        drawRange();
        drawPlayer();
        drawConsumableAtMap();
        drawConsumableAtInventory();
    }

    private static void drawBackground() {

        graphics.drawImage(new Image("background.png"), 1, 0);
        // Fondo.

    }

    private static void drawGameplaySquares() {
        graphics.drawImage(new Image("statsSquare.png"), 680, 32);
        // Cuadro de las estadisticas.

        graphics.drawImage(new Image("dialogueSquare.png"), 32, 710);
        // Cuadro de los dialogos.

        graphics.drawImage(new Image("inventorySquare.png"), 680, 542);
        // Cuadro del inventario.

    }

    private static void drawCampaignMap() {
        TileMap.drawCampaignMap(graphics);
    }

    private static void drawRange() {
        player[0].range(graphics, time);
        if (enemy[0].isAlive()) {
            enemy[0].range(graphics, time);
            enemy[0].draw(graphics);
            rangeCollition();
        }
    }

    private static void drawPlayer() {
        graphics.drawImage(new Image(player[0].getImageName()), player[0].getX(), player[0].getY());
    }

    private static void drawConsumableAtMap() {
        if (Combat.isDropConsumable()) {
            for (int i = 0; i < inventory.size(); i++){
                if (!(inventory.get(i).getImage() == null)){
                    if (drawConsumable) {
                        graphics.drawImage(new Image(inventory.get(i).getImage()), inventory.get(i).getX(), inventory.get(i).getY());
                    }
                }}

        }
    }

    private static void drawConsumableAtInventory() {
        if(inventory.getFirst().getQuantity() > 0) {
            graphics.drawImage(new Image(inventory.getFirst().getImage()), 705, 602);
        }
    }


    private static void actualizeState() {
        checkIfEnemyIsAlive();
        checkIfPlayerCollideWithConsumable();
        checkIfDrawInventory();
        checkIfPlayerCollideWithEnemy();
    }
    private static void checkIfEnemyIsAlive() {
        if (enemy[0].isAlive()) {
            enemy[0].collideRange();
        }
    }
    private static void checkIfPlayerCollideWithConsumable() {
        player[0].collideWithConsumable(inventory);
    }
    private static void checkIfDrawInventory() {
        for (int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getQuantity() <= 0){
                emptyInventoryLabel.setVisible(false);
            }
        }
    }
    private static void checkIfPlayerCollideWithEnemy() {
        player[0].collideRange();

        /* Llama a los metodos que comprueban las
         colisiones en cada enemigo y personaje.
        */

        if (EnemyCharacter.isCollidePlayer() && PlayerCharacter.isCollideEnemy()) {
            Combat.setupCombat();
            EnemyCharacter.setCollidePlayer(false);
            PlayerCharacter.setCollideEnemy(false);
        }
        if (EnemyCharacter.isCollidePlayer()) {
            Combat.setupCombat();
            EnemyCharacter.setCollidePlayer(false);
            PlayerCharacter.setCollideEnemy(false);
        }
        if (PlayerCharacter.isCollideEnemy()) {
            Combat.setupCombat();
            EnemyCharacter.setCollidePlayer(false);
            PlayerCharacter.setCollideEnemy(false);
        }

    }



    private static void playerMovement() {
        /* Por cada tecla presionada, el codigo evaluara
        los puntos de accion y la posicion del jugador
        y determinara si se mueve o no se mueve.
        */

        gameplayScene.setOnKeyReleased(event -> {
            switch (event.getCode().toString()) {
                case "A":
                    // Tecla A mueve el jugador hacia la izquierda-abajo (X-48, Y+32)
                   previusX = player[0].getX();
                   previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == downLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == leftLimit ) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + left);
                            player[0].setY(player[0].getY() + diagonalDown);

                        }
                    break;
                case "D":
                    // Tecla D mueve el jugador hacia la derecha-abajo (X+48, Y+32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == downLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == rightLimit) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + right);
                            player[0].setY(player[0].getY() + diagonalDown);
                        }

                    break;
                case "W":
                    // Tecla W mueve el jugador hacia arriba (Y-64)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == upLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getY() == upLimitEven) {
                                actionPoints++;
                                break;
                            }
                            player[0].setY(player[0].getY() + up);
                        }

                    break;
                case "S":
                    // Tecla S mueve el jugador hacia abajo (Y+64)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == downLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getY() == downLimitEven) {
                                actionPoints++;
                                break;
                            }
                            player[0].setY(player[0].getY() + down);
                        }

                    break;
                case "Q":
                    // Tecla Q mueve el jugador hacia la izquierda-arriba (X-48, Y-32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == upLimitEven) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == leftLimit) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + left);
                            player[0].setY(player[0].getY() + diagonalUp);
                        }
                    break;
                case "E":
                    // Tecla E mueve el jugador hacia la derecha-arriba (X+48, Y-32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }

                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == upLimitEven) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == rightLimit) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + right);
                            player[0].setY(player[0].getY() + diagonalUp);
                        }

                    break;
                case "R":
                    actionPoints = 2;
                    enemy[0].move(player[0].getX(), player[0].getY(), enemy[0].getX(), enemy[0].getY());
                    break;
                case "T":
                    activateRange = !activateRange;
                    break;
                case "P":
                        PauseMenu.managePauseMenu();
                    break;

            }
        });


    }


    private static void rangeCollition() {
        int xDistanceEvP = enemy[0].getX() - player[0].getX();
        int yDistanceEvP = enemy[0].getY() - player[0].getY();
        if (activateRange) {
            if (time % 2 == 0) {
                Image rangoSobrepuesto = new Image("overRangeTerrain.png");
                if (xDistanceEvP == 96 && yDistanceEvP == 0) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalDown);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalUp);
                }
                if (xDistanceEvP == -96 && yDistanceEvP == 0) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalDown);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalUp);
                }
                if (xDistanceEvP == 96 && yDistanceEvP == 64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalDown);
                }
                if (xDistanceEvP == 96 && yDistanceEvP == -64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalUp);
                }
                if (xDistanceEvP == -96 && yDistanceEvP == 64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalDown);
                }
                if (xDistanceEvP == -96 && yDistanceEvP == -64) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalUp);
                }
                // Los tres primeros ifs son a la derecha y los tres ultimos a la izquierda.


                if (xDistanceEvP == 0 && yDistanceEvP == 128) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + down);

                }
                if (xDistanceEvP == 48 && yDistanceEvP == 96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + down);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + diagonalDown);
                }
                if (xDistanceEvP == -48 && yDistanceEvP == 96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + down);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + diagonalDown);
                }
                if (xDistanceEvP == 0 && yDistanceEvP == -128) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + up);

                }
                if (xDistanceEvP == 48 && yDistanceEvP == -96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + up);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + diagonalUp);
                }
                if (xDistanceEvP == -48 && yDistanceEvP == -96) {
                    graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + up);
                    graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + diagonalUp);
                }
            }
        }
    }

    public static boolean isGrabConsumable() {
        return grabConsumable;
    }

    public static void setGrabConsumable(boolean grabConsumable) {
        Gameplay.grabConsumable = grabConsumable;
    }

    public static void setDrawConsumable(boolean drawConsumable) {
        Gameplay.drawConsumable = drawConsumable;
    }

    public static void setAddConsumable(boolean addConsumable) {
        Gameplay.addConsumable = addConsumable;
    }

    public static boolean isActivateRange() {
        return activateRange;
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

    public static EnemyCharacter[] getEnemy() {
        return enemy;
    }

    public static int getPreviusX() {
        return previusX;
    }

    public static int getPreviusY() {
        return previusY;
    }

    public static Scene getGameplayScene() {
        return gameplayScene;
    }

    public static GraphicsContext getGraphics() {
        return graphics;
    }

    public static int getActionPoints() {
        return actionPoints;
    }

    public static void setActionPoints(int actionPoints) {
        Gameplay.actionPoints = actionPoints;
    }

    public static PlayerCharacter[] getPlayer() {
        return player;
    }

}