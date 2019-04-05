
import javax.swing.*;

public class GameOver extends JFrame {

    public GameOver() {
        this.setSize(200, 100);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("Test");
        label1.setText("Game Over");
        this.add(label1);
        this.setVisible(true);
    }
}
