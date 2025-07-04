package app.gameplayFeatures;

import app.Game;
import app.fastFeatures.AudioPlayer;

import app.menus.PauseMenu;
import domain.consumables.ManaPotion;
import domain.consumables.ShardOfAether;
import domain.consumables.VitalityPotion;
import domain.generalClasses.EnemyCharacter;
import domain.generalClasses.PlayerCharacter;
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

import static app.fastFeatures.ButtonManager.*;
import static app.gameplayFeatures.Gameplay.*;
import static app.fastFeatures.LabelManager.createLabel;
import static app.fastFeatures.PublicVariables.*;

public class Combat {
    private static PlayerCharacter[] player;
    private static EnemyCharacter[] enemy;
    private static int selectedCharacter = 0;
    private static int selectedEnemy = 0;
    private static int playerTurn = 3;
    private static int enemiesXPosition = 792;
    private static GraphicsContext graphics;


    private static AnimationTimer animationForCombat;
    private static Scene combatScene;
    private static Group root;
    private static Canvas canvas;
    private static ArrayList<Consumables> inventory;
    private static boolean selectEnemyToAttack = false;
    private static boolean attackToSelectedEnemy = false;
    private static Label message;
    private static Label playerLife;
    private static Label playerAttack;
    private static Label playerMana = new Label();
    private static Label playerTurnLabel;
    private static Label enemyLife;
    private static Label enemyAttackL;
    private static Font statsFont;

    private static Button attack;
    private static Button runAway;
    private static Button passTurn;
    private static Button useConsumable;
    private static Button vitalityPotionButton;
    private static Button manaPotionButton;
    private static Button shardAetherButton;
    private static Button hideConsumablesButton;

    private static boolean noRandomPosition;
    private static boolean dropConsumable;

    public static void setupCombat(EnemyCharacter[] enemi) {
        setupConfigurations(enemi);
        setupWindow();
        setupCombatAnimation();
        setupKeyHandling();
        setupLabels();
        setupButtons();
        setupRoot();
        }


    private static void setupConfigurations(EnemyCharacter[] enemi) {
        Gameplay.stopGameplayTimer();
        player = Gameplay.getPlayer();
        enemy = enemi;
        inventory = Gameplay.getInventory();
        AudioPlayer.stopIfPlaying("tileMap");
        AudioPlayer.playCombatMusic();
    }

    private static void setupWindow() {
        root = new Group();
        combatScene = new Scene(root, screenWidth, screenHeight);
        combatScene.getStylesheets().add(Combat.class.getResource("/buttons.css").toExternalForm());
        canvas = new Canvas(screenWidth, screenHeight);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(combatScene);

    }

    private static void setupCombatAnimation() {
        animationForCombat = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkLifeStatus();
                updateState();
                draw();
                drawPortraits();


                if (playerTurn == 0) {
                    enemyTurn();
                }
            }
        };
        animationForCombat.start();
    }

    private static void checkLifeStatus() {
        if (player[selectedCharacter].getHealth() <= 0) {
            switchToAlivePlayer();
        }
        if (enemy[selectedEnemy].getHealth() <= 0) {
            switchToAliveEnemy();
        }
        if (areAllCharactersDead()) {
            AudioPlayer.stopIfPlaying("combatMusic");
            Gameover.gameOver(combatScene, animationForCombat);
        }
        updateEnemyState();
        if (areAllEnemiesDead()) {
            resetEnemiesAfterBattle();
            dropConsumable();
            changeToGameplay();

        }
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

    private static boolean areAllCharactersDead() {
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


    private static void updateEnemyState() {
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
    }



    private static void dropConsumable() {
        dropConsumable = true;
            int probabilities = new Random().nextInt(1, 4);
            if (probabilities == 1) {
                inventory.getFirst().setX(enemy[0].getX());
                inventory.getFirst().setY(enemy[0].getY());
                inventory.getFirst().setImage("vitalityPotion.png");
                inventory.getFirst().setDrawAtMap(true);
            } else if (probabilities == 2){
                inventory.get(1).setX(enemy[0].getX());
                inventory.get(1).setY(enemy[0].getY());
                inventory.get(1).setImage("manaPotion.png");
                inventory.get(1).setDrawAtMap(true);
            }else{
                inventory.get(2).setX(enemy[0].getX());
                inventory.get(2).setY(enemy[0].getY());
                inventory.get(2).setImage("shardAether.png");
                inventory.get(2).setDrawAtMap(true);

        }
    }

    private static void changeToGameplay() {
        noRandomPosition = true;
        combatScene.setRoot(new Group());
        animationForCombat.stop();
        Gameplay.startGameplayTimer();
        Gameplay.actualizeConsumablesAtInventary();
        window.setScene(Gameplay.getGameplayScene());
    }

    private static void updateState() {
        if (Gameplay.isGrabConsumable() && !inventory.isEmpty() && inventory.getFirst().getQuantity() > 0) {
            Gameplay.setGrabConsumable(false);
        }
        playerLife.setText("HP: " + player[selectedCharacter].getHealth());
        playerAttack.setText("Attack points: " + player[selectedCharacter].getAttack());
        if(player[selectedCharacter].havesMana()) {
            playerMana.setText("MP: " + player[selectedCharacter].getMana());
        }
        playerTurnLabel.setText("Acciones por turno: " + playerTurn);
        enemyLife.setText("HP: " + enemy[selectedEnemy].getHealth());
        enemyAttackL.setText("Attack points: " + enemy[selectedEnemy].getAttack());
        if (player[selectedCharacter].havesMana()){
            playerAttack.setTranslateY(500);
            playerMana.setVisible(true);
        }else{
            playerAttack.setTranslateY(480);
            playerMana.setVisible(false);
        }

        if (attackToSelectedEnemy) {
            if (player[selectedCharacter].havesMana()){
                if (player[selectedCharacter].getMana() < 1){
                    enemy[selectedEnemy].setHealth(enemy[selectedEnemy].getHealth() - (player[selectedCharacter].getAttack() - 3));
                    message.setText("¡Como " + player[selectedCharacter].getCharacterName() +
                            " no tiene mana, solamente ha hecho " + (player[selectedCharacter].getAttack() - 3) + "de daño!");
                    selectEnemyToAttack = false;
                    attackToSelectedEnemy = false;
                    buttonEnabler(attack, runAway, useConsumable, passTurn);

                }else {
                    player[selectedCharacter].setMana(player[selectedCharacter].getMana() - 5);
                    enemy[selectedEnemy].setHealth(enemy[selectedEnemy].getHealth() - (player[selectedCharacter].getAttack() + 4));
                    message.setText("¡Ay, Dios! ¡" + player[selectedCharacter].getCharacterName() +
                            " ha atacado al enemigo con su magia, haciendo mas daño de lo normal!");
                    selectEnemyToAttack = false;
                    attackToSelectedEnemy = false;
                    buttonEnabler(attack, runAway, useConsumable, passTurn);
                    if (player[selectedCharacter].getMana()<1){
                        player[selectedCharacter].setMana(0);
                    }
                }
            }else {
                enemy[selectedEnemy].setHealth(enemy[selectedEnemy].getHealth() - player[selectedCharacter].getAttack());
                message.setText("¡Ay, Dios! ¡" + player[selectedCharacter].getCharacterName() +
                        " ha atacado al enemigo número " + selectedEnemy + "!");
                selectEnemyToAttack = false;
                attackToSelectedEnemy = false;
                buttonEnabler(attack, runAway, useConsumable, passTurn);
            }
        }
    }

    private static void draw() {
        drawAssets();
        drawPlayer();
        drawEnemy();
        drawSelector();
    }

    private static void drawAssets() {
        graphics.drawImage(new Image("combatBackground.png"), 0, 0);
        graphics.drawImage(new Image("combatSquare.png"), 0, 440);

    }

    private static void drawPlayer(){
        int positionY = 325;
        for (PlayerCharacter pc : player) {
            if (pc.getHealth() > 0) {
                graphics.drawImage(new Image(pc.getImageName()), 104, positionY);
            }
            positionY -= 50;
        }
    }

    private static void drawEnemy(){
        int enemiesYPosition = 325;
        for (EnemyCharacter en : enemy) {
            if (en.getHealth() > 0) {
                graphics.drawImage(new Image(en.getImageName()), enemiesXPosition, enemiesYPosition);
            }
            enemiesYPosition -= 50;
        }

    }

    private static void drawSelector() {
        if (selectEnemyToAttack) {
            int enemiesYPosition = 325 - (selectedEnemy * 50);
            graphics.drawImage(new Image("selectEnemy.png"), enemiesXPosition, enemiesYPosition);
        }
    }

    private static void drawPortraits() {
        graphics.drawImage(new Image(player[selectedCharacter].getClosestImageName()), 0, 440);
        graphics.drawImage(new Image(enemy[selectedEnemy].getClosestImageName()), 880, 440);
    }

    private static void enemyTurn() {
        enemyAttack();
        playerTurn = 3;
    }

    private static void enemyAttack() {
        for (int i = 0; i < enemy.length; i++) {
            boolean attackSuccess = new Random().nextBoolean();
            int targetPlayerIndex = selectAlivePlayerForAttack(new Random().nextInt(player.length));
            if (enemy[i].isAlive()) {
                if (attackSuccess) {
                    player[targetPlayerIndex].setHealth(player[targetPlayerIndex].getHealth() - enemy[i].getAttack());
                    message.setText("¡El enemigo número " + i + " ha atacado a " + player[targetPlayerIndex].getCharacterName() +
                            " y le ha hecho " + enemy[i].getAttack() + " de daño!");
                } else {
                    message.setText("¡El enemigo número " + i + " intentó atacar a " + player[targetPlayerIndex].getCharacterName() +
                            ", pero se resbaló y no pudo!");
                }
            }
        }
    }

    private static void setupKeyHandling() {
        combatScene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            switch (code) {
                case "W":
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
                    }
                    break;
                case "P":
                    PauseMenu.managePauseMenu();
                    break;
            }
        });
    }

    private static void setupLabels() {
        statsFont = new Font("Arial", 20);

        playerLife = createLabel(150, 450, "HP: " + player[0].getHealth(), Color.WHITE, statsFont);
        for (int i = 0; i < player.length; i++) {
            if (player[i].havesMana()) {
                playerMana = createLabel(150, 480, "MP: " + player[i].getMana(), Color.WHITE, statsFont);
                playerMana.setVisible(false);
            }
        }

        playerAttack = createLabel(150, 480, "Attack points: " + player[0].getAttack(), Color.WHITE, statsFont);
        playerTurnLabel = createLabel(20, 780, "Acciones por turno: " + playerTurn, Color.WHITE, statsFont);

        enemyLife = createLabel(735, 450, "HP: " + enemy[0].getHealth(), Color.WHITE, statsFont);
        enemyAttackL = createLabel(735, 480, "Attack points: " + enemy[0].getAttack(), Color.WHITE, statsFont);

        message = createLabel(20, 750, "¡Te has encontrado a un enemigo!", Color.WHITE, statsFont);
    }

    private static void setupButtons() {
        attack = createButton("Atacar.", 150, 550, e -> playerAttack(), statsFont);
        attack.getStyleClass().add("combat-button");
        passTurn = createButton("Pasar turno.", 150, 600, e -> playerPassTurn(), statsFont);
        passTurn.getStyleClass().add("combat-button");
        useConsumable = createButton("Usar item.", 150, 650, e -> playerUseConsumable(), statsFont);
        useConsumable.getStyleClass().add("combat-button");
        runAway = createButton("Huir.", 150, 700, e -> playerRunAway(), statsFont);
        runAway.getStyleClass().add("combat-button");
    }

    private static void setupRoot() {
        root.getChildren().addAll(canvas, playerMana, playerLife, playerAttack, enemyLife, enemyAttackL, attack, runAway, passTurn, useConsumable, message, playerTurnLabel, PauseMenu.getPauseMenu());
        root.setEffect(PauseMenu.getBrightness());
    }

    private static void playerAttack() {
        boolean attackSuccess = new Random().nextBoolean();
        if (attackSuccess) {
            selectEnemyToAttack = true;
            buttonDisabler(attack, runAway,useConsumable,passTurn);
        } else {
            message.setText("¡" + player[selectedCharacter].getCharacterName() + " ha fallado el ataque!");
        }
        playerTurn--;
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

    private static void playerPassTurn() {
        boolean enemyWillAttack = new Random().nextBoolean();
        playerTurn = 0;
        if (enemyWillAttack) {
            enemyAttack();
        } else {
            message.setText("¡Pasaste el turno, pero el enemigo no te dañó porque se tropezó!");
        }
    }

    private static void playerUseConsumable() {
        if (inventory.isEmpty()) {
            message.setText("¡No tienes consumibles! ¿¡Para qué tocas el botón?");
        } else {
            vitalityPotionButton = createButton("Usar poción de vitalidad.", 330, 550, e -> useVitalityPotion(), statsFont);
            vitalityPotionButton.getStyleClass().add("combat-button");
            vitalityPotionButton.setFocusTraversable(false);

            manaPotionButton = createButton("Usar poción de mana.",330,600, e -> useManaPotion(), statsFont);
            manaPotionButton.getStyleClass().add("combat-button");
            manaPotionButton.setFocusTraversable(false);

            shardAetherButton = createButton("Usar esquirla de Aether.",330,650, e -> useShardAether(), statsFont);
            shardAetherButton.getStyleClass().add("combat-button");
            shardAetherButton.setFocusTraversable(false);


            hideConsumablesButton = createButton("Atrás", 330, 700, e -> hideConsumables(), statsFont);
            hideConsumablesButton.getStyleClass().add("combat-button");
            hideConsumablesButton.setFocusTraversable(false);


            root.getChildren().addAll(vitalityPotionButton, manaPotionButton,shardAetherButton, hideConsumablesButton);
        }
    }

    private static void hideConsumables() {
        manaPotionButton.setVisible(false);
        vitalityPotionButton.setVisible(false);
        shardAetherButton.setVisible(false);
        hideConsumablesButton.setVisible(false);
    }

    private static void useManaPotion() {
        if (player[selectedCharacter].havesMana()) {
            Consumables potionConsumable = inventory.get(1);
            if (potionConsumable.getQuantity() > 0) {
                ManaPotion potion = new ManaPotion();
                player[selectedCharacter].setMana(player[selectedCharacter].getMana() + potion.getPointsAdded());
                message.setText("¡Has usado una poción de mana! ¡Recuperas " + potion.getPointsAdded() + " de mana!");
                potionConsumable.setQuantity(potionConsumable.getQuantity() - 1);
                playerMana.setText("MP: " + player[selectedCharacter].getMana());
                playerTurn--;
            } else {
                message.setText("¿Crees que vas a usar una poción de mana que no tienes?");
            }
        } else {
            message.setText("Su personaje no tiene mana que restaurar.");
        }

    }

    private static void useVitalityPotion() {
        Consumables potionConsumable = inventory.getFirst();
        if (potionConsumable.getQuantity() > 0) {
            VitalityPotion potion = new VitalityPotion();
            player[selectedCharacter].setHealth(player[selectedCharacter].getHealth() + potion.getPointsAdded());
            message.setText("¡Has usado una poción de vitalidad! ¡Te curas " + potion.getPointsAdded() + " puntos de vida!");
            potionConsumable.setQuantity(potionConsumable.getQuantity() - 1);
            playerLife.setText("HP: " + player[selectedCharacter].getHealth());
            playerTurn--;
        } else {
            message.setText("¿Crees que vas a usar una poción de vitalidad que no tienes?");
        }

    }


    private static void useShardAether() {
        Consumables potionConsumable = inventory.get(2);
        if (potionConsumable.getQuantity() > 0 ) {
            ShardOfAether shard = new ShardOfAether();
            player[selectedCharacter].setAttack(player[selectedCharacter].getAttack() + shard.getPointsAdded());
            message.setText("¡Has usado una esquirla de Aether! ¡Aumentas " + shard.getPointsAdded() + " puntos de ataque permanentes a "
                    +player[selectedCharacter].getCharacterName()+"!");
            potionConsumable.setQuantity(potionConsumable.getQuantity() - 1);
            playerAttack.setText("Attack points: " + player[selectedCharacter].getAttack());
            playerTurn--;
        } else {
            message.setText("¿Crees que vas a usar una esquirla de Aether que no tienes?");
        }
    }

    private static void playerRunAway() {
        boolean runAwaySuccess = new Random().nextBoolean();
        if (runAwaySuccess) {
            noRandomPosition = true;
            AudioPlayer.stopIfPlaying("combatMusic");
            window.setScene(getGameplayScene());
            Gameplay.startGameplayTimer();
            animationForCombat.stop();
            player[0].setX(64);
            player[0].setY(64);
        } else {
            boolean enemyCounterAttack = new Random().nextBoolean();
            playerTurn--;
            if (enemyCounterAttack) {
                enemyAttack();
                message.setText("¡El enemigo no te dejó huir y te hizo daño!");
            } else {
                message.setText("¡No escapaste pero el enemigo falló el ataque!");
            }
        }
    }


    public static void setDropConsumable(boolean dropConsumable) {
        Combat.dropConsumable = dropConsumable;
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