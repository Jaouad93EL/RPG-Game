
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlusMinusGame extends JFrame implements ActionListener {

    private int findValue;
    private int stop;
    private boolean winOrnot;
    private JPanel pan = new JPanel();
    private JLabel label1 = new JLabel("PlusMinusGame");
    private JTextField repon = new JTextField(4);
    private JButton play = new JButton("play");

    public PlusMinusGame(int findValue) {
        this.stop = 0;
        this.findValue = findValue;
        this.setSize(150, 150);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pan);
        label1.setText("---  PlusMinusGame  ---");
        play.addActionListener(this);
        repon.setSize(10, 10);
        pan.add(label1);
        pan.add(repon);
        pan.add(play);
    }

    public boolean start() {
        if (this.stop == 10) {
            this.winOrnot = false;
            return true;
        }
        if (label1.getText().equals("Win")) {
            this.winOrnot = true;
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!repon.getText().matches("[0-9]+")) {
            label1.setText("chiffre svp");
            return;
        }
        if (this.findValue == Integer.parseInt(repon.getText())) {
            label1.setText("Win");
            return;
        }
        if (this.findValue < Integer.parseInt(repon.getText()))
            label1.setText("Lower");
        if (this.findValue > Integer.parseInt(repon.getText()))
            label1.setText("Greater");
        if (this.stop == 10)
            label1.setText("Loose");
        this.stop++;
    }

    public boolean isWinOrnot() {
        return winOrnot;
    }

    public void setWinOrnot(boolean winOrnot) {
        this.winOrnot = winOrnot;
    }
}
