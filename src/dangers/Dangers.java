package dangers;

import game.TurnManager;
import javafx.scene.effect.Effect;

import javax.swing.text.Position;

public class Dangers {
    private Position position;
    private Position range;
    private Effect effect;
    private TurnManager duration;
    private int movement;

    public Dangers(Position position, Position range, Effect effect, TurnManager duration, int movement) {
        this.position = position;
        this.range = range;
        this.effect = effect;
        this.duration = duration;
        this.movement = movement;
    }

}