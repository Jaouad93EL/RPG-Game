
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Multi implements Runnable {
    Player player;
    Grille grille;
    int savex;
    int savey;
    Thread thread = new Thread(this, "move");

    public Multi(Player player, Grille grille) {
        this.player = player;
        this.grille = grille;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket("172.16.15.67", 0701);
            BufferedWriter Bwriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader Breader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            boolean boucle = true;
            while (boucle) {
                String Slire = player.positionX + "-" + player.positionY + "\n";
                Bwriter.write(Slire);
                Bwriter.flush();
                String Senv = Breader.readLine();
                transformer(Senv);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            s.close();
        }
        catch (IOException e) {}
    }

    public void transformer(String yoo) {
        grille.grilleTab[savex][savey].setBackground(Color.green);
        String[] parts = yoo.split("-");
        int yS = 19-Integer.parseInt(parts[1]);
        int xS = Integer.parseInt(parts[0]);
        grille.grilleTab[xS][yS].setBackground(Color.red);
        savex = xS;
        savey = yS;
    }
}
