module Game {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.media;

    opens app.gameModes;
    opens app.main;
    opens app.gameplayFeatures;
    opens app.main.Roaster;
}