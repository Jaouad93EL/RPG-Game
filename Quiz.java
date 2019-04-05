
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {

    List<Question> questions;
    int next = 0;
    private boolean winOrnot;
    private JPanel pan = new JPanel();
    private JLabel label1 = new JLabel("Quiz");
    private JLabel label2 = new JLabel("rep2");
    private JLabel label3 = new JLabel("rep3");
    private JLabel label4 = new JLabel("rep4");
    private JLabel label5 = new JLabel("rep5");
    private JTextField repon = new JTextField(8);
    private JButton play = new JButton("valider");

    public boolean start() {
        if (next == questions.size() -1)
            winOrnot = true;
        return this.winOrnot;
    }

    public Quiz() {
        this.questions = new ArrayList<>();
        this.questions.add(new Question("1 + 1", "2", "11", "0", "10", "2"));
        this.questions.add(new Question("2 + 2", "2", "11", "0", "4", "4"));
        this.questions.add(new Question("1 + 1", "2", "11", "0", "10", "2"));
        this.questions.add(new Question("1 + 1", "2", "11", "0", "10", "2"));
        this.questions.add(new Question("", "", "", "", "", ""));

        this.setSize(150, 150);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(pan);
        winOrnot = false;
        pan.setLayout(new GridLayout(7,1));
        play.addActionListener(this::actionPerformed);
        repon.setSize(10,10);
        pan.add(label1);
        pan.add(label2);
        pan.add(label3);
        pan.add(label4);
        pan.add(label5);
        pan.add(repon);
        pan.add(play);
        setTextLabel();
    }

    public void setTextLabel() {
        label1.setText(this.questions.get(next).getQuestion());
        label2.setText(this.questions.get(next).getResponse1());
        label3.setText(this.questions.get(next).getResponse2());
        label4.setText(this.questions.get(next).getResponse3());
        label5.setText(this.questions.get(next).getResponse4());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (next != questions.size() -1 && repon.getText().equals(this.questions.get(next).getAnswer())) {
            this.next++;
            setTextLabel();
        }
        else
            winOrnot = false;
    }

    public boolean isWinOrnot() {
        return winOrnot;
    }

}