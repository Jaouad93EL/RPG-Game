
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Layout layout = new Layout();
        Grille grille = new Grille();
        Game game = new Game(player, layout, grille);
        game.addKeyListener(new Clavier(game));
        game.thread.start();

    }
}
