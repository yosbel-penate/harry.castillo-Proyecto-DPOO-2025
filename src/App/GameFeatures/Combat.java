package app.gameplayFeatures;

import app.main.AudioPlayer;
import app.main.Game;
import app.menus.PauseMenu;
import domain.consumables.VitalityPotion;
import domain.entities.EnemyCharacter;
import domain.entities.PlayerCharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

import static app.gameplayFeatures.Gameplay.*;
import static app.main.Game.*;

public class Combat {
    private static PlayerCharacter[] player;
    private static EnemyCharacter[] enemy;
    private static int selectedCharacter = 0;
    private static int selectedEnemy = 0;
    private static int playerTurn = 5;
    private static GraphicsContext graphics;


    private static AnimationTimer animationForCombat;
    private static Scene combatScene;
    private static Group root;
    private static ArrayList<Consumables> inventory;
    private static String keyPressed;
    private static boolean selectEnemyToAttack = false;
    private static boolean attackToSelectedEnemy = false;
    private static Label message;
    private static Label playerLife;
    private static Label playerAttack;
    private static Label playerTurnLabel;
    private static Label enemyLife;
    private static Label enemyAttackL;

    private static Button attack;
    private static Button runAway;
    private static Button passTurn;
    private static Button useConsumable;


    private static boolean noRandomPosition;
    private static boolean dropConsumable;

    public static void initializeCombat() {
        setupConfigurations();
        setupWindow();
        isPausable=true;
        combatScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case P -> {
                    if (Game.isPausable) {
                        PauseMenu.managePauseMenu();
                    }
                }
            }
        });
    }

    private static void setupConfigurations() {
        Gameplay.stopGameplayTimer();
        player = Gameplay.getPlayer();
        enemy = Gameplay.getEnemy();
        inventory = Gameplay.getInventory();
        AudioPlayer.playCombatMusic();
    }

    private static void setupWindow() {
        root = new Group();
        combatScene = new Scene(root, 1000, 850);
        //combatScene.getStylesheets().add(Combat.class.getResource("/buttons.css").toExternalForm());
        Canvas canvas = new Canvas(1000, 850);

        setupLabels();
        setupButtons();

        root.getChildren().addAll(canvas, playerLife, playerAttack, enemyLife, enemyAttackL, attack, runAway, passTurn, useConsumable, message, playerTurnLabel,PauseMenu.getPauseMenu());
        graphics = canvas.getGraphicsContext2D();

        setupKeyHandling();

        window.setScene(combatScene);

        animationForCombat = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkLifeStatus();
                updateState();
                drawScene();
                drawPortraits();

                if (playerTurn == 0) {
                    enemyTurn();
                }
            }
        };
        animationForCombat.start();
    }

    private static void setupLabels() {
        Font statsFont = new Font("Arial", 20);

        playerLife = createLabel(150, 450, "HP: " + player[0].getHealth(), Color.WHITE, statsFont);
        playerAttack = createLabel(150, 480, "Attack points: " + player[0].getAttack(), Color.WHITE, statsFont);
        playerTurnLabel = createLabel(20, 780, "Acciones por turno: " + playerTurn, Color.WHITE, statsFont);

        enemyLife = createLabel(735, 450, "HP: " + enemy[0].getHealth(), Color.WHITE, statsFont);
        enemyAttackL = createLabel(735, 480, "Attack points: " + enemy[0].getAttack(), Color.WHITE, statsFont);

        message = createLabel(20, 750, "¡Te has encontrado a un loboepinga!", Color.WHITE, statsFont);
    }

    private static Label createLabel(double x, double y, String text, Color color, Font font) {
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setTextFill(color);
        label.setFont(font);
        return label;
    }

    private static void setupButtons() {
        attack = createButton("Atacar.", 150, 550, e -> playerAttack());
        passTurn = createButton("Pasar turno.", 150, 600, e -> playerPassTurn());
        useConsumable = createButton("Usar item.", 150, 650, e -> playerUseConsumable());
        runAway = createButton("Huir.", 150, 700, e -> playerRunAway());
    }

    private static Button createButton(String text, double x, double y, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button btn = new Button(text);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setFocusTraversable(false);
        btn.setOnAction(action);
        return btn;
    }

    private static void setupKeyHandling() {
        combatScene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            switch (code) {
                case "W":
                    keyPressed = "W";
                    if (!selectEnemyToAttack) {
                        if (selectedCharacter < player.length - 1) {
                            selectedCharacter++;
                        }
                    } else {
                        if (selectedEnemy < enemy.length - 1) {
                            selectedEnemy++;
                        }
                    }
                    break;
                case "S":
                    keyPressed = "S";
                    if (!selectEnemyToAttack) {
                        if (selectedCharacter > 0) {
                            selectedCharacter--;
                        }
                    } else {
                        if (selectedEnemy > 0) {
                            selectedEnemy--;
                        }
                    }
                    break;
                case "SPACE":
                    if (selectEnemyToAttack) {
                        attackToSelectedEnemy = true;
                        keyPressed = "SPACE";
                    }
                    break;
            }
        });
    }

    private static void drawPortraits() {
        graphics.drawImage(new Image(player[selectedCharacter].getClosestImageName()), 0, 440);
        graphics.drawImage(new Image(enemy[selectedEnemy].getClosestImageName()), 880, 440);
    }

    private static void drawScene() {
        graphics.drawImage(new Image("combatBackground.png"), 0, 0);
        graphics.drawImage(new Image("combatSquare.png"), 0, 440);

        int positionY = 325;
        for (PlayerCharacter pc : player) {
            if (pc.getHealth() > 0) {
                graphics.drawImage(new Image(pc.getImageName()), 104, positionY);
            }
            positionY -= 50;
        }

        positionY = 325;
        for (EnemyCharacter en : enemy) {
            if (en.getHealth() > 0) {
                graphics.drawImage(new Image(en.getImageName()), 792, positionY);
            }
            positionY -= 50;
        }

        if (selectEnemyToAttack) {
            int selectorY = 325 - (selectedEnemy * 50);
            graphics.drawImage(new Image("selectEnemy.png"), 792, selectorY);
        }
    }

    private static void checkLifeStatus() {
        if (player[selectedCharacter].getHealth() <= 0) {
            switchToAlivePlayer();
        }
        if (enemy[selectedEnemy].getHealth() <= 0) {
            switchToAliveEnemy();
        }
        if (areAllPlayersDead()) {
            AudioPlayer.stopIfPlaying("combatMusic");
            Gameover.gameOver(combatScene, animationForCombat);
        }
        updateEnemiesAliveStatus();

        if (areAllEnemiesDead()) {
            resetEnemiesAfterBattle();
        }
    }

    private static void updateEnemiesAliveStatus() {
        for (EnemyCharacter en : enemy) {
            if (en.getHealth() <= 0) {
                en.setAlive(false);
            }
        }
    }


    private static void resetEnemiesAfterBattle() {
        AudioPlayer.stopIfPlaying("combatMusic");
        selectedEnemy = 0;
        for (EnemyCharacter en : enemy) {
            en.setHealth(10);
        }
        noRandomPosition = true;
        dropConsumable = new Random().nextBoolean();
        if (dropConsumable) {
            Consumables consumable = inventory.getFirst();
            consumable.setX(enemy[0].getX());
            consumable.setY(enemy[0].getY());
            consumable.setImage("vitality_potion.png");
            Gameplay.setAddConsumable(true);
            Gameplay.setDrawConsumable(true);
        }
        combatScene.setRoot(new Group());
        animationForCombat.stop();
        Gameplay.startGameplayTimer();
        window.setScene(Gameplay.getGameplayScene());
    }

    private static boolean areAllPlayersDead() {
        for (PlayerCharacter p : player) {
            if (p.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean areAllEnemiesDead() {
        for (EnemyCharacter en : enemy) {
            if (en.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private static void switchToAlivePlayer() {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getHealth() > 0) {
                selectedCharacter = i;
                return;
            }
        }
    }

    private static void switchToAliveEnemy() {
        for (int i = 0; i < enemy.length; i++) {
            if (enemy[i].getHealth() > 0) {
                selectedEnemy = i;
                return;
            }
        }
    }

    private static void updateState() {
        if (Gameplay.isGrabConsumable() && !inventory.isEmpty() && inventory.getFirst().getQuantity() > 0) {
            Gameplay.setGrabConsumable(false);
        }

        playerLife.setText("HP: " + player[selectedCharacter].getHealth());
        playerAttack.setText("Attack points: " + player[selectedCharacter].getAttack());
        playerTurnLabel.setText("Acciones por turno: " + playerTurn);
        enemyLife.setText("HP: " + enemy[selectedEnemy].getHealth());
        enemyAttackL.setText("Attack points: " + enemy[selectedEnemy].getAttack());

        if (attackToSelectedEnemy && "SPACE".equals(keyPressed)) {
            enemy[selectedEnemy].setHealth(enemy[selectedEnemy].getHealth() - player[selectedCharacter].getAttack());
            message.setText("¡Ay, Dios! ¡" + player[selectedCharacter].getCharacterName() +
                    " ha atacado al loboepinga número " + selectedEnemy + "!");
            selectEnemyToAttack = false;
            attackToSelectedEnemy = false;
            attack.setDisable(false);
            runAway.setDisable(false);
            useConsumable.setDisable(false);
            passTurn.setDisable(false);
            keyPressed = "";
        }
    }

    private static void playerAttack() {
        boolean attackSuccess = new Random().nextBoolean();
        if (attackSuccess) {
            selectEnemyToAttack = true;
            attack.setDisable(true);
            runAway.setDisable(true);
            useConsumable.setDisable(true);
            passTurn.setDisable(true);
        } else {
            message.setText("¡" + player[selectedCharacter].getCharacterName() + " ha fallado el ataque!");
        }
        playerTurn--;
    }

    private static void enemyAttack() {
        Random rand = new Random();
        for (int i = 0; i < enemy.length; i++) {
            boolean attackSuccess = rand.nextBoolean();
            int targetPlayerIndex = selectAlivePlayerForAttack(rand.nextInt(player.length));
            if (attackSuccess) {
                player[targetPlayerIndex].setHealth(player[targetPlayerIndex].getHealth() - enemy[i].getAttack());
                message.setText("¡El loboepinga número " + i + " ha atacado a " + player[targetPlayerIndex].getCharacterName() +
                        " y le ha hecho " + enemy[i].getAttack() + " de daño!");
            } else {
                message.setText("¡El loboepinga número " + i + " intentó atacar a " + player[targetPlayerIndex].getCharacterName() +
                        ", pero se resbaló y no pudo!");
            }
        }
    }

    private static int selectAlivePlayerForAttack(int startIndex) {
        if (player[startIndex].getHealth() > 0) {
            return startIndex;
        }
        for (int i = 0; i < player.length; i++) {
            if (player[i].getHealth() > 0) {
                return i;
            }
        }
        return startIndex;// retroceso, aunque todos podrían estar muertos
    }

    private static void enemyTurn() {
        enemyAttack();
        playerTurn = 5;
    }

    private static void playerPassTurn() {
        boolean enemyWillAttack = new Random().nextBoolean();
        playerTurn = 0;
        if (enemyWillAttack) {
            enemyAttack();
        } else {
            message.setText("¡Pasaste el turno, pero el loboepinga no te dañó porque se tropezó!");
        }
    }

    private static void playerUseConsumable() {
        if (inventory.isEmpty() || inventory.getFirst().getQuantity() == 0) {
            message.setText("¡No tienes consumibles! ¿¡Para qué tocas el botón?");
        } else {
            Button potionButton = new Button("Usar poción de vitalidad.");
            potionButton.setTranslateX(250);
            potionButton.setTranslateY(550);

            potionButton.setOnAction(e -> usePotion());

            message.setText("¡Guau, tienes " + inventory.getFirst().getQuantity() + " pociones de vitalidad!");
            root.getChildren().add(potionButton);
        }
    }

    private static void usePotion() {
        Consumables potionConsumable = inventory.getFirst();
        if (potionConsumable.getQuantity() > 0) {
            VitalityPotion potion = new VitalityPotion();
            player[selectedCharacter].setHealth(player[selectedCharacter].getHealth() + potion.getHealthAdded());
            message.setText("¡Has usado una poción de vitalidad! ¡Te curas " + potion.getHealthAdded() + " de vida!");
            potionConsumable.setQuantity(potionConsumable.getQuantity() - 1);
            playerLife.setText("HP: " + player[selectedCharacter].getHealth());
            playerTurn--;
        } else {
            message.setText("¿Crees que vas a usar una poción de vitalidad que no tienes?");
        }
    }

    private static void playerRunAway() {
        boolean runAwaySuccess = new Random().nextBoolean();
        if (runAwaySuccess) {
            noRandomPosition = true;
            System.out.println("Posicion X: " + player[0].getX());
            System.out.println("Posicion X: " + player[0].getY());
            AudioPlayer.stopIfPlaying("combatMusic");
            window.setScene(getGameplayScene());
            animationForCombat.stop();
            Gameplay.startGameplayTimer();
            player[0].setX(64);
            player[0].setY(64);
        } else {
            boolean enemyCounterAttack = new Random().nextBoolean();
            playerTurn--;
            if (enemyCounterAttack) {
                enemyAttack();
                message.setText("¡El loboepinga no te dejó huir y te hizo daño!");
            } else {
                message.setText("¡No escapaste pero el loboepinga falló el ataque!");
            }
        }
    }


    public static boolean isNoRandomPosition() {
        return noRandomPosition;
    }

    public static void setNoRandomPosition(boolean noRandomPosition) {
        Combat.noRandomPosition = noRandomPosition;
    }

    public static boolean isDropConsumable() {
        return dropConsumable;
    }


    public Combat() {
    }
}