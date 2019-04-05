
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    public Menu() {
        this.setLayout(new GridLayout(2, 1));
        this.setSize(200, 200);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JButton arcade = new JButton("Arcade");
        arcade.addActionListener(this::actionPerformed);
        JButton multi = new JButton("multi");
        multi.addActionListener(this::multijoueur);

        this.add(arcade);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("arcade");
    }

    public void multijoueur(ActionEvent e) {
        System.out.println("multi");
    }
}