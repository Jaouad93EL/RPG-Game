
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Mental extends JFrame implements ActionListener {

    List<Question> questions;
    private boolean winOrnot;
    int next = 0;
    private JPanel pan = new JPanel();
    private JLabel label1 = new JLabel("Quiz");
    private JTextField repon = new JTextField(8);
    private JButton play = new JButton("valider");

    public Mental() {
        winOrnot = false;
        questions = new ArrayList<>();
        questions.add(new Question("10 * 10", "", "", "", "", "100"));
        questions.add(new Question("10 + 10", "", "", "", "", "20"));
        questions.add(new Question("", "", "", "", "", ""));

        this.setSize(150, 150);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pan);
        winOrnot = false;
        pan.setLayout(new GridLayout(3,1));
        play.addActionListener(this::actionPerformed);
        repon.setSize(10,10);
        pan.add(label1);
        pan.add(repon);
        pan.add(play);
        setTextLabel();
    }


    public void setTextLabel() {
        label1.setText(this.questions.get(next).getQuestion());
    }

    public boolean start() {
        if (next == questions.size() -1)
            winOrnot = true;
        return this.winOrnot;
    }

    public boolean isWinOrnot() {
        return winOrnot;
    }

        @Override
    public void actionPerformed(ActionEvent e) {
        if (next != questions.size() -1  && repon.getText().equals(this.questions.get(next).getAnswer())) {
            this.next++;
            setTextLabel();
        }
        else
            winOrnot = false;
    }
}
