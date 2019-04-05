
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JFrame implements Runnable {

    JFrame lay = new JFrame();
    Player player;
    Monster monster;
    Layout layout;
    Grille grille;
    Multi multi;
    Server serve;
    Boolean run = true;
    Thread thread = new Thread(this, "move");


    public Game(Player player, Layout layout, Grille grille) {
        this.player = player;
        this.layout = layout;
        this.grille = grille;
        this.multi = new Multi(player, grille);
        this.serve = new Server(player, grille);
        this.monster = new Monster();

        this.setLayout(new GridLayout(3, 1));
        this.setSize(200, 200);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JButton arcade = new JButton("Arcade");
        arcade.addActionListener(this::actionPerformed);
        JButton mult = new JButton("multi");
        mult.addActionListener(this::multijoueur);
        JButton server = new JButton("server");
        server.addActionListener(this::serveur);
        this.add(arcade);
        this.add(mult);
        this.add(server);
    }

    public void actionPerformed(ActionEvent e) {
        this.goGame();
    }

    public void serveur(ActionEvent e) {
        serve.thread.start();
        goGame();
    }

    public void multijoueur(ActionEvent e) {
        multi.thread.start();
        goGame();
    }


    public void goGame() {
        this.getContentPane().removeAll();
        this.setLayout(new GridLayout(10, 20));
        this.setSize(850, 600);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.map();
        this.setLayout();
        grille.initObj();
        this.initMonster();
    }

    public void setLayout() {
        lay.setSize(250, 400);
        lay.setFocusable(true);
        lay.setLocationRelativeTo(null);
        lay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lay.setVisible(true);
        lay.setLayout(new GridLayout(11, 0));
        refreshLayout();
        lay.add(layout.main).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Object source = e.getSource();
                    JButton btn = (JButton) source;
                    String butSrcTxt = btn.getText();
                    layout.main.setText("");
                    grille.mainToBag(butSrcTxt);
                    player.stats.setAttack(50);
                    player.setWeapon(null);
                }
            }
        });
        lay.add(layout.score);
        lay.add(layout.attack);
        lay.add(layout.defence);

        for (int i = 0; i < 6; ++i) {
            grille.grilleObj.add(new JButton());
            grille.grilleObj.get(i).setName("n");
            grille.grilleObj.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Object source = e.getSource();
                        if (source instanceof JButton) {
                            JButton btn = (JButton) source;
                            String butSrcTxt = btn.getText();
                            char ca = e.toString().charAt(e.toString().length() - 1);
                            if (ca == 'y') {

                                grille.removeObj(butSrcTxt);
                                if (butSrcTxt.equals("potion"))
                                    player.stats.addStats(butSrcTxt);
                                if (!butSrcTxt.equals("potion")) {
                                    if (!layout.main.getText().isEmpty()) {
                                        grille.mainToBag(layout.main.getText());
                                        player.stats.setAttack(50);
                                    }
                                    player.stats.addStats(butSrcTxt);
                                    layout.main.setText(butSrcTxt);
                                    player.setWeapon(butSrcTxt);
                                }
                            }
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        Object source = e.getSource();
                        if (source instanceof JButton) {
                            JButton btn = (JButton) source;
                            String butSrcTxt = btn.getText();
                            grille.dropItem(btn, butSrcTxt, player.positionX, player.positionY);
                        }
                    }
                }
            });
            lay.add(grille.grilleObj.get(i));
        }

    }

    public void refreshLayout() {
        layout.attack.setText("Attaque j1 :   " + player.stats.getAttack());
        layout.defence.setText("Defence j1 :   " + player.stats.getDefence());
        layout.score.setText("score j1 :   " + player.stats.getScore());
    }

    public void playerObj() {
        for (ObjPosition o : grille.listObj)
            if (o.Xobj == player.positionX && o.Yobj == player.positionY) {
                playerObjset(o);
                grille.listObj.remove(o);
                return;
            }
    }

    public void playerObjset(ObjPosition o) {
        for (int i = 0; i < grille.grilleObj.size(); ++i) {
            if (grille.grilleObj.get(i).getName().equals("n")) {
                grille.grilleObj.get(i).setText(o.name);
                grille.grilleObj.get(i).setName("y");
                grille.grilleObj.get(i).setDisplayedMnemonicIndex(i);
                return;
            }
        }
    }

    public boolean runQuest() {
        if (player.positionX == 1 && player.positionY == 7) {
            Quiz quest = new Quiz();
            while (!quest.start()) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            quest.setVisible(false);
            if (quest.isWinOrnot()) {
                grille.grilleTab[4][8].setBackground(Color.orange);
                grille.grilleTab[4][8].setName("orange");
                player.stats.setScore(player.stats.getScore() + 100);
            }
            return false;
        }
        return true;
    }

    public void initMonster() {
        grille.grilleTab[8][8].setBackground(Color.blue);
        grille.grilleTab[8][8].setName("blue");
        grille.grilleTab[1][7].setBackground(Color.blue);
        grille.grilleTab[1][7].setName("blue");
    }

    public boolean runPlus() {
        if (player.positionX == 9 && player.positionY == 0) {
            int nombreAleatoire = 1 + (int) (Math.random() * ((100 - 1) + 1));
            PlusMinusGame plus = new PlusMinusGame(nombreAleatoire);
            while (!plus.start()) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            plus.setVisible(false);
            if (plus.isWinOrnot()) {
                grille.grilleTab[1][7].setBackground(Color.orange);
                grille.grilleTab[1][7].setName("orange");
            } else if (!plus.isWinOrnot()) {
            }
            player.stats.setScore(player.stats.getScore() + 100);
            return false;
        }
        return true;
    }

    public boolean runMental() {
        if (player.positionX == 4 && player.positionY == 8) {
            Mental men = new Mental();
            while (!men.start()) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            men.setVisible(false);
            player.stats.setScore(player.stats.getScore() + 100);
            return false;
        }
        return true;
    }

    public void running() {

        int x1 = 0;
        int x2 = 9;
        int k = 0;
        boolean plusM = true;
        boolean questM = true;
        boolean mentalM = true;

        while (run) {
            while (x1 < 10) {
                this.playerObj();
                this.refreshLayout();
                if (x1 != 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
                grille.grilleTab[7][8].setBackground(Color.green);
                grille.grilleTab[7][8].setName("green");
                if (x1 != 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
                if (plusM) {
                    plusM = runPlus();
                }
                if (questM && !plusM) {
                    questM = runQuest();
                }
                if (mentalM && !plusM && !questM) {
                    mentalM = runMental();
                }
                if ((player.positionX == 1 && player.positionY == 7) && k == 0) {
                    monster.combat(this);
                    k = 1;
                }
                if ((player.positionY == 8 && player.positionX == 8) && k == 1) {
                    monster.combat(this);
                    k = 2;
                }
                if (grille.grilleTab[x1][4].getName().equals("red") || grille.grilleTab[x2][5].getName().equals("red")) {
                    new GameOver();
                    return;
                }
                if (player.positionX == 9 && player.positionY == 8) {
                    System.exit(1);
                    return;
                }
                grille.grilleTab[x1][4].setBackground(Color.black);
                grille.grilleTab[x1][4].setName("black");
                grille.grilleTab[x2][5].setBackground(Color.black);
                grille.grilleTab[x2][5].setName("black");

                if (x1 != 0) {
                    grille.grilleTab[x1 - 1][4].setBackground(Color.green);
                    grille.grilleTab[x1 - 1][4].setName("green");
                    grille.grilleTab[x2 + 1][5].setBackground(Color.green);
                    grille.grilleTab[x2 + 1][5].setName("green");
                }
                ++x1;
                --x2;
            }
            try {
                Thread.sleep(200);
            } catch (Exception e) {
            }

            grille.grilleTab[9][4].setBackground(Color.green);
            grille.grilleTab[9][4].setName("green");
            grille.grilleTab[0][5].setBackground(Color.green);
            grille.grilleTab[0][5].setName("green");
            x1 = 0;
            x2 = 9;
        }
    }

    public void map() {
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < 20; ++j)
                this.add(grille.grilleTab[i][j]);
    }

    @Override
    public void run() {
        running();
    }
}