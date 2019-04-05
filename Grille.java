
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grille {

    JPanel grilleTab[][] = new JPanel[10][20];
    ArrayList<JButton> grilleObj = new ArrayList<JButton>();
    ArrayList<ObjPosition> listObj = new ArrayList<ObjPosition>();

    public Grille() {
        initMap();
    }

    public void initMap() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                if((j == 1 && i != 3) || (j == 18 && i != 3)
                        || (j == 3 && i != 7) || (j == 16 && i != 7)
                        || (j == 7 && i != 1) || (j == 12 && i != 1)
                        || (j == 6 && i != 1) || (j == 13 && i != 1)) {
                    grilleTab[i][j] = new JPanel();
                    //grilleTab[i][j]
                    grilleTab[i][j].setBackground(Color.GRAY);
                    grilleTab[i][j].setName("gray");
                }
                else if ((j == 9) || (j == 10)) {
                    grilleTab[i][j] = new JPanel();
                    grilleTab[i][j].setBackground(Color.gray);
                    grilleTab[i][j].setName("gray");
                }
                else {
                    grilleTab[i][j] = new JPanel();
                    grilleTab[i][j].setBackground(Color.green);
                    grilleTab[i][j].setName("green");
                }
            }
        }
        grilleTab[1][0].setBackground(Color.red);
        grilleTab[1][0].setName("red");
    }

    public void initObj() {
        this.grilleTab[8][2].setBackground(Color.white);
        this.grilleTab[8][2].setName("white");
        this.grilleTab[0][8].setBackground(Color.white);
        this.grilleTab[0][8].setName("white");
        this.grilleTab[9][0].setBackground(Color.orange);
        this.grilleTab[9][0].setName("orange");
        this.grilleTab[3][0].setBackground(Color.pink);
        this.grilleTab[3][0].setName("pink");
        this.initObjlist();
    }

    public void initObjlist() {
        listObj.add(new ObjPosition(8, 2, "sabre"));
        listObj.add(new ObjPosition(0, 8, "katana"));
        listObj.add(new ObjPosition(3, 0, "potion"));
    }

    public void removeObj(String name) {
        for (int i = 0; i < this.grilleObj.size(); ++i) {
            if (this.grilleObj.get(i).getText().equals(name)) {
                this.grilleObj.get(i).setName("n");
                this.grilleObj.get(i).setText("");
            }
        }
        for (int i = 0; i < this.listObj.size(); ++i) {
            if (this.listObj.get(i).getName().equals(name))
                this.listObj.remove(i);
        }
    }

    public void dropItem(JButton btn, String name, int x, int y) {
        btn.setText("");
        btn.setName("n");
        if (name.equals("katana") || name.equals("sabre")) {
            listObj.add(new ObjPosition(x + 1, y, name));
            grilleObj.add(btn);
            this.grilleTab[x +1][y].setBackground(Color.white);
            //this.grilleTab[x +1][y].setName("white");
        }
        if (name.equals("potion")) {
            listObj.add(new ObjPosition(x + 1, y, name));
            grilleObj.add(btn);
            this.grilleTab[x +1][y].setBackground(Color.pink);
            //this.grilleTab[x +1][y].setName("pink");
        }
    }

    public void mainToBag(String name) {
        for (JButton j :grilleObj) {
            if (j.getName().equals("n")) {
                j.setText(name);
                j.setName("y");
                return;
            }
        }

    }
}
