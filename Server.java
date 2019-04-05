
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    Player player;
    Grille grille;
    int savex;
    int savey;
    Thread thread = new Thread(this, "move");

    public Server(Player player, Grille grille) {
        this.player = player;
        this.grille = grille;
    }

    @Override
    public void run() {
        try {
            ServerSocket s = new ServerSocket(0701);
            Socket ss = s.accept();
            BufferedReader Breader = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            BufferedWriter Bwriter = new BufferedWriter(new OutputStreamWriter(ss.getOutputStream()));
            boolean boucle = true;
            while (boucle) {
                String Slire = Breader.readLine();
                String Senv = player.positionX + "-" + player.positionY + "\n";
                Bwriter.write(Senv);
                Bwriter.flush();
                transformer(Slire);
            }
            ss.close();
        } catch (IOException e) {
        }
    }

    public void transformer(String yoo) {
        grille.grilleTab[savex][savey].setBackground(Color.green);
        String[] parts = yoo.split("-");
        int yC = 19 - Integer.parseInt(parts[1]);
        int xC = Integer.parseInt(parts[0]);
        grille.grilleTab[xC][yC].setBackground(Color.red);
        savex = xC;
        savey = yC;

    }
}
