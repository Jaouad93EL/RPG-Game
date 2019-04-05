
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Combat extends JFrame implements ActionListener {

    private JPanel pan = new JPanel();
    private JLabel label1 = new JLabel("PlusMinusGame");
    private JButton play = new JButton("play");
    private boolean looseOrnot = false;
    private int monster;
    private int player;

    public Combat(Game game) {
        this.monster = game.monster.stats.getAttack();
        this.player = game.player.stats.getAttack();

        this.setSize(250, 250);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pan);
        label1.setText( "joueur = " + player + " VS " + "monster = " + monster);
        play.addActionListener(this);
        pan.add(label1);
        pan.add(play);
    }

    public boolean start() {
        return looseOrnot;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (monster < player) {
            looseOrnot = true;
        }

    }
}

