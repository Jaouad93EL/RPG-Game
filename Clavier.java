
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {

    Game game;

    public Clavier(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.mouvement(0, 1);
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            this.mouvement(0, -1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            this.mouvement(1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP){
            this.mouvement(-1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_A){
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void mouvement(int x, int y) {
        if (verifCase(this.game.player.positionX + x, this.game.player.positionY + y)) {
            game.grille.grilleTab[this.game.player.positionX][this.game.player.positionY].setBackground(Color.GREEN);
            game.grille.grilleTab[this.game.player.positionX][this.game.player.positionY].setName("green");
            game.grille.grilleTab[this.game.player.positionX + x][this.game.player.positionY + y].setBackground(Color.RED);
            game.grille.grilleTab[this.game.player.positionX + x][this.game.player.positionY + y].setName("red");
            this.game.player.positionX += x;
            this.game.player.positionY += y;
        }
    }

    public boolean verifCase(int x, int y) {
        if ((x >= 0 && x < 10) && (y >= 0 && y < 10) &&
                (!game.grille.grilleTab[x][y].getName().equals("gray")) &&
                (!game.grille.grilleTab[x][y].getName().equals("black")))
            return true;
        return false;
    }
}
