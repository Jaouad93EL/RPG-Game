
import java.util.*;
import java.util.List;

public class Player {

    Stats stats;
    Weapon weapon;
    List<Item> bag;

    int positionX;
    int positionY;

    public Player() {
        this.bag = new ArrayList<>();

        this.stats = new Stats(50, 50);
        this.positionX = 1;
        this.positionY = 0;
    }

    public void setWeapon(String name) {
        weapon = new Weapon(name);
    }
}
