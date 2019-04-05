
import java.awt.*;
import java.util.ArrayList;

public class Monster {

    Stats stats;

    public Monster() {
        this.stats = new Stats(40, 20);
    }

    public void combat(Game game) {
        Combat combat = new Combat(game);
        while (!combat.start()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
        combat.setVisible(false);
    }


    public Stats getStats() {
        return this.stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
