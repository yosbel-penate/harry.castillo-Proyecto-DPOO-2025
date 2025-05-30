module Game {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;

    opens app.gameModes;
    opens app.gameplayFeatures;
    opens app.fastFeatures;
    opens app;
}